package com.appmarket.application.service;

import com.appmarket.application.port.in.CreateUserUseCase;
import com.appmarket.application.port.out.CreateUser;
import com.appmarket.application.port.out.SearchUserByEmailOrLogin;
import com.appmarket.domain.Role;
import com.appmarket.domain.User;
import com.appmarket.exception.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
record CreateUserService(SearchUserByEmailOrLogin searchUserByEmailOrLogin,
                                CreateUser createUser) implements CreateUserUseCase {

    @Override
    public UUID createUser(final CreateUserCommand command) {
        final var userList = searchUserByEmailOrLogin.searchUserByEmailOrLogin(command.getEmail(), command.getLogin());
        if (!userList.isEmpty()) {
            throw new UserAlreadyExistsException();
        }
        final var user = User.builder()
                .email(command.getEmail())
                .login(command.getLogin())
                .name(command.getName())
                .password(command.getPassword())
                .document(command.getDocument())
                .roles(Set.of(Role.DEFAULT.name()))
                .active(true)
                .build();

        final var userCreated = createUser.createUser(user);

        log.info("Objeto criado: " + userCreated);

        return userCreated.id();
    }
}
