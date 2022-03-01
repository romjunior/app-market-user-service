package com.appmarket.application.port.out;

import com.appmarket.domain.User;

import java.util.List;

public interface SearchUserByQuery {

    List<User> searchUser(final String name, final String email, final String login, final String document);

}
