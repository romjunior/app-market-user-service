package com.appmarket.application.service;

import com.appmarket.application.port.in.SearchUserIdUseCase;
import com.appmarket.application.port.out.SearchUserById;
import com.appmarket.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
record SearchUserService(SearchUserById searchUserById) implements SearchUserIdUseCase {

    @Override
    public Optional<User> searchUserId(SearchUserIdCommand command) {
        return searchUserById.searchUserById(command.getId());
    }
}
