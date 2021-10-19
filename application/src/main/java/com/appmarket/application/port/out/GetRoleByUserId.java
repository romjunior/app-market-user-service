package com.appmarket.application.port.out;

import java.util.Set;
import java.util.UUID;

public interface GetRoleByUserId {
    Set<String> getRolesByUserId(final UUID userId);
}
