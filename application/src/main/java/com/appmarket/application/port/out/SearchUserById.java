package com.appmarket.application.port.out;

import com.appmarket.domain.User;

import java.util.UUID;

public interface SearchUserById {

    User searchUserById(final UUID id);
}
