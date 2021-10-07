package com.appmarket.application.service;

import com.appmarket.application.port.in.CreateUserUseCase;
import com.appmarket.application.port.out.CreateUser;
import com.appmarket.application.port.out.SearchUserByEmailOrLogin;
import com.appmarket.domain.User;
import com.appmarket.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Set;
import java.util.UUID;

class CreateUserServiceTest {

    @Mock
    private SearchUserByEmailOrLogin searchUserByEmailOrLogin;

    @Mock
    private CreateUser createUser;

    private CreateUserService createUserService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        createUserService = new CreateUserService(searchUserByEmailOrLogin, createUser);
    }

    @Test
    void deveRetornarUmaExceptionDeUsuarioJaExistenteNaBaseCasoOLoginOuEmailJaEstejaCadastradoNaBase() {
        final var email = "teste@teste.com";
        final var login = "teste";

        final var command = CreateUserUseCase.CreateUserCommand
                .builder()
                .name("teste")
                .document("12345678901")
                .email(email)
                .login(login)
                .password("12345")
                .confirmedPassword("12345")
                .build();

        final var userExists = List.of(User.builder()
                .name(command.getName())
                .document(command.getDocument())
                .login(command.getLogin())
                .password(command.getPassword())
                .active(true)
                .roles(Set.of("DEFAULT"))
                .build());

        Mockito.when(searchUserByEmailOrLogin.searchUserByEmailOrLogin(email, login)).thenReturn(userExists);

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> {
            createUserService.createUser(command);
        });
        Mockito.verify(searchUserByEmailOrLogin).searchUserByEmailOrLogin(email, login);
    }

    @Test
    void deveRetornarUmUUIDComSucessoCasoOusuarioSejaCadastradoComSucesso() {

        final var email = "teste@teste.com";
        final var login = "teste";

        final var command = CreateUserUseCase.CreateUserCommand
                .builder()
                .name("teste")
                .document("12345678901")
                .email(email)
                .login(login)
                .password("12345")
                .confirmedPassword("12345")
                .build();

        final var user = User.builder()
                .id(UUID.randomUUID())
                .name(command.getName())
                .document(command.getDocument())
                .login(command.getLogin())
                .password(command.getPassword())
                .active(true)
                .roles(Set.of("DEFAULT"))
                .build();

        Mockito.when(searchUserByEmailOrLogin.searchUserByEmailOrLogin(email, login)).thenReturn(List.of());

        Mockito.when(createUser.createUser(Mockito.any())).thenReturn(user);

        final var expectedUUID = createUserService.createUser(command);

        Assertions.assertNotNull(expectedUUID);
        Assertions.assertTrue(expectedUUID instanceof UUID);
        Mockito.verify(searchUserByEmailOrLogin).searchUserByEmailOrLogin(email, login);
        Mockito.verify(createUser).createUser(Mockito.any());
    }
}
