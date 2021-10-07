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

        final var user = searchUserById.searchUserById(editUserCommand.getId());

        if (null == user)
            throw new UserNotFoundException("Usuário não encontrado");

        final var userToBeEdited = User.builder()
                .id(editUserCommand.getId())
                .name(editUserCommand.getName())
                .login(user.login())
                .email(editUserCommand.getEmail())
                .roles(user.roles())
                .document(editUserCommand.getDocument())
                .password(getPassword(user.password(), editUserCommand.getPassword()))
                .active(true)
                .build();

        return editUser.editUser(userToBeEdited);
    }

    String getPassword(final String password, final String newPassword) {
        return newPassword == null ? password : newPassword;
    }
}
