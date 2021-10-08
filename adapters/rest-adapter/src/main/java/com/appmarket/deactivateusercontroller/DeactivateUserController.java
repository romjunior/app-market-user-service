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
    ResponseEntity<Void> deactivateUser(@PathVariable("id") final UUID id) {
        deactivateUserUseCase.deactivateUser(DeactivateUserUseCase.DeactivateUserCommand.of(id));
        return ResponseEntity.noContent().build();
    }
}
