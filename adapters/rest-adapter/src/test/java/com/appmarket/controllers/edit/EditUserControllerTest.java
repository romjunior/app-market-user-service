package com.appmarket.controllers.edit;

import com.appmarket.application.port.in.EditUserUseCase;
import com.appmarket.config.CustomMediaType;
import com.appmarket.config.RestErrorResponse;
import com.appmarket.controllers.create.UserIdResponse;
import com.appmarket.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EditUserController.class)
class EditUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EditUserUseCase editUserUseCase;

    @Test
    void dadoEdicaoValidaDeDados_deveRetornar200ComUUIDDoUsuarioAlterado() throws Exception {
        final var uuid = UUID.randomUUID();
        final var userRequest = new EditUserRequest(
                uuid,
                "jhon",
                "12345678911",
                "jhon@gmail.com",
                null,
                null
        );

        final var user = User.builder()
                .id(uuid)
                .build();

        Mockito.when(editUserUseCase.editUser(any())).thenReturn(Optional.of(user));

        mockMvc.perform(put("/user")
                .contentType(CustomMediaType.USER_V1)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CustomMediaType.USER_V1))
                .andExpect(content().json(objectMapper.writeValueAsString(UserIdResponse.of(uuid))));
    }

    @Test
    void dadoEdicaoValidaDeDadosDeUmUsuarioNaoExistente_deveRetornar404() throws Exception {
        final var uuid = UUID.randomUUID();
        final var userRequest = new EditUserRequest(
                uuid,
                "jhon",
                "12345678911",
                "jhon@gmail.com",
                null,
                null
        );

        Mockito.when(editUserUseCase.editUser(any())).thenReturn(Optional.empty());

        mockMvc.perform(put("/user")
                        .contentType(CustomMediaType.USER_V1)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    void dadoEdicaoInvalidaDeDados_deveRetornar400ComMensagemDeErro() throws Exception {
        final var userRequest = new EditUserRequest(
                null,
                "",
                "1234",
                "jhongmail.com",
                "123",
                "123"
        );

        final var expectedErrorResponse = new RestErrorResponse(
                "javax.validation.ConstraintViolationException",
                Map.of("id", "não deve ser nulo",
                        "name", "não deve estar vazio",
                        "password", "tamanho deve ser entre 5 e 8",
                        "confirmedPassword", "tamanho deve ser entre 5 e 8",
                        "email", "deve ser um endereço de e-mail bem formado",
                        "document", "tamanho deve ser entre 11 e 14"));

        mockMvc.perform(put("/user")
                        .contentType(CustomMediaType.USER_V1)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedErrorResponse)));
    }

}
