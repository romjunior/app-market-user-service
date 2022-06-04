package com.appmarket.application.service;

import com.appmarket.application.port.in.EditUserUseCase;
import com.appmarket.application.port.out.EditUser;
import com.appmarket.application.port.out.SearchUserById;
import com.appmarket.domain.User;
import com.password4j.Password;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

class EditUserServiceTest {

    @Mock
    private SearchUserById searchUserById;

    @Mock
    private EditUser editUser;

    private EditUserService editUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        editUserService = new EditUserService(searchUserById, editUser);
    }

    @Test
    void deveRetornarUmErroCasoOusuarioNaoExista() {
        final var uuid = UUID.randomUUID();
        final var command = EditUserUseCase.EditUserCommand.builder()
                .id(uuid)
                .name("nome")
                .document("12345678901")
                .email("nome@gmail.com")
                .build();
        final Optional<User> expected = Optional.empty();

        final var result = editUserService.editUser(command);

        Assertions.assertEquals(expected, result);
        Mockito.verify(searchUserById).searchUserById(Mockito.any());
    }

    @Test
    void deveRetornarUmUsuarioAlteradoComSucesso() {
        final var uuid = UUID.randomUUID();

        final var command = EditUserUseCase.EditUserCommand.builder()
                .id(uuid)
                .name("nome")
                .document("12345678901")
                .email("nome@gmail.com")
                .build();

        final var user = User.builder()
                .id(command.getId())
                .name(command.getName())
                .document(command.getDocument())
                .email(command.getEmail())
                .active(true)
                .login("nome")
                .password("123456")
                .build();

        Mockito.when(searchUserById.searchUserById(uuid)).thenReturn(Optional.of(user));
        Mockito.when(editUser.editUser(user)).thenReturn(user);
        final var resultUser = editUserService.editUser(command);

        Assertions.assertEquals(Optional.of(user), resultUser);
        Mockito.verify(searchUserById).searchUserById(Mockito.any());
        Mockito.verify(editUser).editUser(Mockito.any());
    }

    @Test
    void deveRetornarAsenhaAtual() {
        final var senhaAtualPlain = "12345";
        final var senhaAtualEncrypted = Password.hash(senhaAtualPlain).withBCrypt().getResult();
        final var result = editUserService.getPassword(senhaAtualEncrypted, null);
        Assertions.assertTrue(Password.check(senhaAtualPlain, result).withBCrypt());
    }

    @Test
    void deveRetornarAsenhaB() {
        final var senhaAtual = "12345";
        final var senhaNovo = "123";
        final var result = editUserService.getPassword(senhaAtual, senhaNovo);
        Assertions.assertTrue(Password.check(senhaNovo, result).withBCrypt());
    }

}
