package com.appmarket.controllers.search;

import com.appmarket.application.port.in.SearchUserIdUseCase;
import com.appmarket.config.CustomMediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
record SearchUserController(SearchUserIdUseCase searchUserIdUseCase) {

    @GetMapping(path = "/user/{id}", produces = CustomMediaType.USER_V1)
    public ResponseEntity<UserDTO> searchUser(@PathVariable("id") final UUID id) {
        return searchUserIdUseCase.searchUserId(SearchUserIdUseCase.SearchUserIdCommand.of(id))
                .map(UserDTO::buildDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
