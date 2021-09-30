package com.appmarket.controller;

import com.appmarket.application.port.in.CreateUserUseCase;
import com.appmarket.exception.PasswordNotMatchException;
import com.appmarket.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
record CreateUserController(CreateUserUseCase createUserUseCase) {

    @PostMapping(path = "/user", consumes = "application/vnd.user.v1+json", produces = "application/vnd.user.v1+json")
    ResponseEntity<UserIdResponse> createUser(@RequestBody final UserRequest request) throws PasswordNotMatchException, UserAlreadyExistsException {

        final var response = createUserUseCase.createUser(CreateUserUseCase.CreateUserCommand.builder()
                .name(request.name())
                .document(request.document())
                .email(request.email())
                .login(request.login())
                .password(request.password())
                .confirmedPassword(request.confirmedPassword())
                .build());

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserIdResponse(response));
    }

}
