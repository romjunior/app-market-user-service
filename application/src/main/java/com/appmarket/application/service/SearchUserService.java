package com.appmarket.application.service;

import com.appmarket.application.port.in.SearchUserIdUseCase;
import com.appmarket.application.port.in.SearchUserUseCase;
import com.appmarket.application.port.out.SearchUserById;
import com.appmarket.application.port.out.SearchUserByQuery;
import com.appmarket.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
record SearchUserService(SearchUserById searchUserById, SearchUserByQuery searchUserByQuery) implements SearchUserIdUseCase, SearchUserUseCase {

    @Override
    public Optional<User> searchUserId(SearchUserIdCommand command) {
        return searchUserById.searchUserById(command.getId());
    }

    @Override
    public List<User> searchUser(SearchUserQuery query) {
        return searchUserByQuery.searchUser(query.name(), query.email(), query.login(), query.document());
    }
}
