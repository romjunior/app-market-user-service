package com.appmarket.usercreatecontroller;

import java.util.UUID;

public record UserIdResponse(UUID id) {

    public static UserIdResponse of(UUID id) {
        return new UserIdResponse(id);
    }

}
