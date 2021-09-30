package com.appmarket.application.port.in;

import com.appmarket.domain.User;

import java.util.List;

public interface SearchUserUseCase {

    List<User> searchUser(final String name, final String email, final String login, final String document);
}
