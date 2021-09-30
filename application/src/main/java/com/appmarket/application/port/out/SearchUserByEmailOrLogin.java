package com.appmarket.application.port.out;

import com.appmarket.domain.User;

import java.util.List;

public interface SearchUserByEmailOrLogin {

    List<User> searchUserByEmailOrLogin(final String email, final String login);

}
