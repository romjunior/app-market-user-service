package com.appmarket.deactivateusercontroller;

import com.appmarket.application.port.in.DeactivateUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
record DeactivateUserController(DeactivateUserUseCase deactivateUserUseCase) {

    @GetMapping(path = "/user/deactivate/{id}")
    ResponseEntity<Object> deactivateUser(@PathVariable("id") final UUID id) {
        return deactivateUserUseCase.deactivateUser(DeactivateUserUseCase.DeactivateUserCommand.of(id))
                .map(uuid -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}
