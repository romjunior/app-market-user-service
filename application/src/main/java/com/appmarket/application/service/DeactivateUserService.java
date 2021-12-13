package com.appmarket.application.service;

import com.appmarket.application.port.in.DeactivateUserUseCase;
import com.appmarket.application.port.out.EditUser;
import com.appmarket.application.port.out.SearchUserById;
import com.appmarket.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
record DeactivateUserService(SearchUserById searchUserById,
                                    EditUser editUser) implements DeactivateUserUseCase {
    @Override
    public Optional<UUID> deactivateUser(final DeactivateUserCommand command) {
       return searchUserById.searchUserById(command.getId())
               .map(User::deactivateUser)
               .map(editUser::editUser)
               .map(user -> command.getId());
    }
}
