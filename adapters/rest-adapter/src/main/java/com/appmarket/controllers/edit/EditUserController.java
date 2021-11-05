package com.appmarket.controllers.edit;

import com.appmarket.application.port.in.EditUserUseCase;
import com.appmarket.config.CustomMediaType;
import com.appmarket.controllers.create.UserIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
record EditUserController(EditUserUseCase editUserUseCase) {

    @PutMapping(value = "/user", consumes = CustomMediaType.USER_V1, produces = CustomMediaType.USER_V1)
    public ResponseEntity<UserIdResponse> editUser(@RequestBody final EditUserRequest request) {
        return editUserUseCase.editUser(EditUserUseCase.EditUserCommand.builder()
                .id(request.id())
                .name(request.name())
                .document(request.document())
                .email(request.email())
                .password(request.password())
                .confirmedPassword(request.confirmedPassword())
                .build())
                .map(user -> ResponseEntity.ok(UserIdResponse.of(user.id())))
                .orElse(ResponseEntity.notFound().build());
    }

}
