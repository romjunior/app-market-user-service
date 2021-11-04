package com.appmarket.controllers.create;

import com.appmarket.application.port.in.CreateUserUseCase;
import com.appmarket.config.CustomMediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
record CreateUserController(CreateUserUseCase createUserUseCase) {

    @PostMapping(path = "/user", consumes = CustomMediaType.USER_V1, produces = CustomMediaType.USER_V1)
    ResponseEntity<UserIdResponse> createUser(@RequestBody final CreateUserRequest request) {

        final var response = createUserUseCase.createUser(CreateUserUseCase.CreateUserCommand.builder()
                .name(request.name())
                .document(request.document())
                .email(request.email())
                .login(request.login())
                .password(request.password())
                .confirmedPassword(request.confirmedPassword())
                .build());

        return ResponseEntity.status(HttpStatus.CREATED).body(UserIdResponse.of(response));
    }

}
