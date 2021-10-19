package com.appmarket.application.port.out;

import java.util.UUID;

public interface AddRole {

    void addRoleToUser(final UUID id, final String role);

}
