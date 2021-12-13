package com.appmarket.controllers.create;

import com.appmarket.application.port.in.CreateUserUseCase;
import com.appmarket.config.CustomMediaType;
import com.appmarket.config.RestErrorResponse;
import com.appmarket.exception.InconsistencyException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CreateUserController.class)
class CreateUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateUserUseCase createUserUseCase;

    @Test
    void quandoTiverUmInputValido_EntaoRetorna201ComAResponse() throws Exception {
        final var userRequest = buildRequest("12345678", "12345678");
        final var uuid = UUID.randomUUID();

        Mockito.when(createUserUseCase.createUser(any())).thenReturn(uuid);

        final var expectedResponse = UserIdResponse.of(uuid);


        mockMvc.perform(post("/user")
                .contentType(CustomMediaType.USER_V1)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
                .andExpect(content().contentType(CustomMediaType.USER_V1));
    }

    @Test
    void quandoTiverUmInputInvalido_EntaoRetorna400ComAResponseDeErroDeVlidacao() throws Exception {
        final var userRequest = buildRequest("123456789", "123456789");

        final var expectedErrorResponse = new RestErrorResponse(
                "javax.validation.ConstraintViolationException",
                Map.of(
                        "password", "tamanho deve ser entre 5 e 8",
                        "confirmedPassword", "tamanho deve ser entre 5 e 8"
                )
        );

        mockMvc.perform(post("/user")
                        .contentType(CustomMediaType.USER_V1)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedErrorResponse)));
    }

    @Test
    void quandoTiverUmErroDeNegocio_EntaoRetorna400ComRespostaDeNegocio() throws Exception {
        final var userRequest = buildRequest("1234567", "12345678");

        final var expectedErrorResponse = new RestErrorResponse(
                "PASSWORD_ALREADY_EXISTS",
                Map.of(
                        "message", "Senhas não são iguais"
                )
        );

        mockMvc.perform(post("/user")
                        .contentType(CustomMediaType.USER_V1)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedErrorResponse)));
    }

    @Test
    void quandoTiverUmErroDeInconsistencia_EntaoRetorna400ComRespostaDeNegocio() throws Exception {
        final var userRequest = buildRequest("1234567", "1234567");

        Mockito.when(createUserUseCase.createUser(any())).thenThrow(new InconsistencyException("role does not exists"));
        final var expectedErrorResponse = new RestErrorResponse(
                "INCONSISTENCY",
                Map.of(
                        "message", "role does not exists"
                )
        );

        mockMvc.perform(post("/user")
                        .contentType(CustomMediaType.USER_V1)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedErrorResponse)));
    }

    CreateUserRequest buildRequest(final String password, final String confirmedPassword) {
        return new CreateUserRequest(
                "john",
                "12345678911",
                "johnmiranda",
                "john@gmail.com",
                password,
                confirmedPassword
        );
    }

}
