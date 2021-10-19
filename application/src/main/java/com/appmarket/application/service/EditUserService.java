package com.appmarket.application.service;

import com.appmarket.application.port.in.EditUserUseCase;
import com.appmarket.application.port.out.EditUser;
import com.appmarket.application.port.out.SearchUserById;
import com.appmarket.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
record EditUserService(SearchUserById searchUserById,
                              EditUser editUser) implements EditUserUseCase {

    @Override
    public Optional<User> editUser(EditUserCommand editUserCommand) {
        return searchUserById.searchUserById(editUserCommand.getId())
                .map(user -> User.builder()
                        .id(editUserCommand.getId())
                        .name(editUserCommand.getName())
                        .login(user.login())
                        .email(editUserCommand.getEmail())
                        .roles(user.roles())
                        .document(editUserCommand.getDocument())
                        .password(getPassword(user.password(), editUserCommand.getPassword()))
                        .active(true)
                        .build())
                .map(editUser::editUser);
    }

    String getPassword(final String password, final String newPassword) {
        return newPassword == null ? password : newPassword;
    }
}
