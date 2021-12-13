package com.appmarket.application.service;

import com.appmarket.application.port.in.DeactivateUserUseCase;
import com.appmarket.application.port.out.EditUser;
import com.appmarket.application.port.out.SearchUserById;
import com.appmarket.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

class DeactivateUserServiceTest {

    @Mock
    SearchUserById searchUserById;

    @Mock
    EditUser editUser;

    DeactivateUserService deactivateUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deactivateUserService = new DeactivateUserService(searchUserById, editUser);
    }

    @Test
    void deveRetornarOIDoUsuarioDesativadoCasoSejaSucesso() {
        final var command = DeactivateUserUseCase
                .DeactivateUserCommand
                .of(UUID.randomUUID());

        final var user = User.builder()
                .active(true)
                .build();

        Mockito.when(searchUserById.searchUserById(command.getId())).thenReturn(Optional.of(user));

        Mockito.when(editUser.editUser(any())).thenReturn(user);

        final var result = deactivateUserService.deactivateUser(command);

        Assertions.assertEquals(Optional.of(command.getId()), result);
    }

    @Test
    void deveRetornarVazioCasoNaoExistaOUsuario() {
        final var command = DeactivateUserUseCase
                .DeactivateUserCommand
                .of(UUID.randomUUID());

        Mockito.when(searchUserById.searchUserById(command.getId())).thenReturn(Optional.empty());

        final var result = deactivateUserService.deactivateUser(command);

        Assertions.assertEquals(Optional.empty(), result);
    }
}
