package com.appmarket.controllers.edit;

import java.util.UUID;

record EditUserRequest(UUID id,
                       String name,
                       String document,
                       String email,
                       String password,
                       String confirmedPassword) {
}
