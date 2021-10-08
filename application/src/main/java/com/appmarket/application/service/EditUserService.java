package com.appmarket.application.service;

import com.appmarket.application.port.in.EditUserUseCase;
import com.appmarket.application.port.out.EditUser;
import com.appmarket.application.port.out.SearchUserById;
import com.appmarket.domain.User;
import com.appmarket.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
record EditUserService(SearchUserById searchUserById,
                              EditUser editUser) implements EditUserUseCase {

    @Override
    public User editUser(EditUserCommand editUserCommand) {
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
                .map(editUser::editUser)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }

    String getPassword(final String password, final String newPassword) {
        return newPassword == null ? password : newPassword;
    }
}
