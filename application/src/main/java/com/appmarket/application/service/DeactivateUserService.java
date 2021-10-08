package com.appmarket.application.service;

import com.appmarket.application.port.in.DeactivateUserUseCase;
import com.appmarket.application.port.out.DeactivateUser;
import com.appmarket.application.port.out.SearchUserById;
import com.appmarket.domain.User;
import com.appmarket.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
record DeactivateUserService(SearchUserById searchUserById,
                                    DeactivateUser deactivateUser) implements DeactivateUserUseCase {
    @Override
    public void deactivateUser(final DeactivateUserCommand command) {
       searchUserById.searchUserById(command.getId())
               .map(User::deactivateUser)
               .ifPresentOrElse(deactivateUser::deactivateUser,
                       () -> { throw new UserNotFoundException("Usuário não encontrado"); });
    }
}
