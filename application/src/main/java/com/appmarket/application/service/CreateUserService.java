package com.appmarket.application.service;

import com.appmarket.application.port.in.CreateUserUseCase;
import com.appmarket.application.port.out.CreateUser;
import com.appmarket.application.port.out.SearchUserByEmailOrLogin;
import com.appmarket.domain.User;
import com.appmarket.exception.PasswordNotMatchException;
import com.appmarket.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public record CreateUserService(SearchUserByEmailOrLogin searchUserByEmailOrLogin,
                                CreateUser createUser) implements CreateUserUseCase {

    @Override
    public UUID createUser(final CreateUserCommand command) throws UserAlreadyExistsException, PasswordNotMatchException {
        final var userList = searchUserByEmailOrLogin.searchUserByEmailOrLogin(command.getEmail(), command.getLogin());
        if (!userList.isEmpty()) {
            throw new UserAlreadyExistsException("login ou e-mail já existentes");
        }
        isTheSamePassword(command.getPassword(), command.getConfirmedPassword());
        final var user = User.builder()
                .email(command.getEmail())
                .login(command.getLogin())
                .name(command.getName())
                .password(command.getPassword())
                .document(command.getDocument())
                .active(true)
                .build();
        final var userCreated = createUser.createUser(user);

        return userCreated.id();
    }

    void isTheSamePassword(final String password, final String confirmedPassword) throws PasswordNotMatchException {
        if (!password.equals(confirmedPassword)) {
            throw new PasswordNotMatchException("Senhas não são as mesmas");
        }
    }
}
