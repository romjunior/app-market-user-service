package com.appmarket.controllers.deactivate;

import com.appmarket.application.port.in.DeactivateUserUseCase;
import com.appmarket.config.CustomMediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DeactivateUserController.class)
class DeactivateUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeactivateUserUseCase deactivateUserUseCase;

    @Test
    void quandoTiverUmInputValido_deveRetornar204() throws Exception {
        final var uuid = UUID.randomUUID();

        Mockito.when(deactivateUserUseCase.deactivateUser(any())).thenReturn(Optional.of(uuid));

        mockMvc.perform(delete("/user/" + uuid)
                .contentType(CustomMediaType.USER_V1))
                .andExpect(status().isNoContent());
    }

    @Test
    void quandoTiverUmInputValidoEnaoEncontrarOusuario_deveRetornar404() throws Exception {
        final var uuid = UUID.randomUUID();

        Mockito.when(deactivateUserUseCase.deactivateUser(any())).thenReturn(Optional.empty());

        mockMvc.perform(delete("/user/" + uuid)
                        .contentType(CustomMediaType.USER_V1))
                .andExpect(status().isNotFound());
    }

    @Test
    void quandoTiverUmInputInvalido_deveRetornar400ComErro() throws Exception {
        final UUID uuid = null;

        Mockito.when(deactivateUserUseCase.deactivateUser(any())).thenReturn(Optional.empty());

        mockMvc.perform(delete("/user/" + uuid)
                        .contentType(CustomMediaType.USER_V1))
                .andExpect(status().isBadRequest());
    }
}
