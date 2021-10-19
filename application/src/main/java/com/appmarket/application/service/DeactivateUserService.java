package com.appmarket.application.service;

import com.appmarket.application.port.in.DeactivateUserUseCase;
import com.appmarket.application.port.out.DeactivateUser;
import com.appmarket.application.port.out.SearchUserById;
import com.appmarket.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
record DeactivateUserService(SearchUserById searchUserById,
                                    DeactivateUser deactivateUser) implements DeactivateUserUseCase {
    @Override
    public Optional<UUID> deactivateUser(final DeactivateUserCommand command) {
       return searchUserById.searchUserById(command.getId())
               .map(User::deactivateUser)
               .map(deactivateUser::deactivateUser)
               .map(user -> command.getId());
    }
}
