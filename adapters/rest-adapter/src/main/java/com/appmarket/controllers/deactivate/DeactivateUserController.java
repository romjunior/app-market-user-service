package com.appmarket.controllers.deactivate;

import com.appmarket.application.port.in.DeactivateUserUseCase;
import com.appmarket.config.CustomMediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
record DeactivateUserController(DeactivateUserUseCase deactivateUserUseCase) {

    @DeleteMapping(path = "/user/{id}", produces = CustomMediaType.USER_V1)
    ResponseEntity<Object> deactivateUser(@PathVariable("id") final UUID id) {
        return deactivateUserUseCase.deactivateUser(DeactivateUserUseCase.DeactivateUserCommand.of(id))
                .map(uuid -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}
