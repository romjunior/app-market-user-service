package com.appmarket.application.port.out;

import com.appmarket.domain.User;

public interface DeactivateUser {

    User deactivateUser(final User user);
}
