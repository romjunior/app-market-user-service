package com.appmarket.usereditcontroller;

import com.appmarket.application.port.in.EditUserUseCase;
import com.appmarket.usercreatecontroller.UserIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
record EditUserController(EditUserUseCase editUserUseCase) {

    @PutMapping(value = "/user", consumes = "application/vnd.user.v1+json", produces = "application/vnd.user.v1+json")
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
