package com.appmarket.controllers.search;

import com.appmarket.application.port.in.SearchUserIdUseCase;
import com.appmarket.application.port.in.SearchUserUseCase;
import com.appmarket.config.CustomMediaType;
import com.appmarket.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SearchUserController.class)
class SearchUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SearchUserIdUseCase searchUserIdUseCase;

    @MockBean
    private SearchUserUseCase searchUserUseCase;


    @Test
    void quandoTiverUmInputValido_deveRetornar200comOusuarioEncontrado() throws Exception {
        final var uuid = UUID.randomUUID();

        final var user = User.builder()
                .id(uuid)
                .name("jhon")
                .email("jhon@gmail.com")
                .password("12345678")
                .login("jhon")
                .roles(Set.of("DEFAULT"))
                .document("12345678911")
                .active(true)
                .build();

        Mockito.when(searchUserIdUseCase.searchUserId(any())).thenReturn(Optional.of(user));

        final var expectedResponse = UserDTO.buildDTO(user);

        mockMvc.perform(get("/user/" + uuid)
                .contentType(CustomMediaType.USER_V1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CustomMediaType.USER_V1))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
    }

    @Test
    void quandoTiverUmInputValidoMasNaoPossuirUsuario_deveRetornar404() throws Exception {
        final var uuid = UUID.randomUUID();

        Mockito.when(searchUserIdUseCase.searchUserId(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/user/" + uuid)
                        .contentType(CustomMediaType.USER_V1))
                .andExpect(status().isNotFound());
    }

    @Test
    void quandoTiverUmInputMasNaoPossuirUsuario_deveRetornar404() throws Exception {
        final var body = new UserRequestDTO("birobiro", "", "biro", "");

        Mockito.when(searchUserUseCase.searchUser(new SearchUserUseCase.SearchUserQuery(body.name(), body.email(), body.login(), body.document())))
                .thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .contentType(CustomMediaType.USER_V1)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isNotFound());
    }

    @Test
    void quandoTiverUmInput_deveRetornarOresultadoDaBusca() throws Exception {
        final var body = new UserRequestDTO("birobiro", "", "biro", "");

        final var user = User.builder()
                .id(UUID.randomUUID())
                .name("birobirojhon")
                .email("biro@gmail.com")
                .password("1234567890")
                .login("birobiro")
                .roles(Set.of("DEFAULT"))
                .document("12345678920")
                .active(false)
                .build();

        Mockito.when(searchUserUseCase.searchUser(new SearchUserUseCase.SearchUserQuery(body.name(), body.email(), body.login(), body.document())))
                .thenReturn(List.of(user));

        final var expectedResponse = List.of(UserDTO.buildDTO(user));

        mockMvc.perform(get("/user")
                        .contentType(CustomMediaType.USER_V1)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CustomMediaType.USER_V1))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
    }
}
