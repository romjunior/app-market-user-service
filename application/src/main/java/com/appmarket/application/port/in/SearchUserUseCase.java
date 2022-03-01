package com.appmarket.application.port.in;

import com.appmarket.domain.User;

import java.util.List;

public interface SearchUserUseCase {

    List<User> searchUser(final SearchUserQuery query);

    record SearchUserQuery(String name, String email, String login, String document) {
    }
}
