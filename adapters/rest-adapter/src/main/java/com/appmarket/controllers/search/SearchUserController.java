package com.appmarket.controllers.search;

import com.appmarket.application.port.in.SearchUserIdUseCase;
import com.appmarket.application.port.in.SearchUserUseCase;
import com.appmarket.config.CustomMediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
record SearchUserController(SearchUserIdUseCase searchUserIdUseCase, SearchUserUseCase searchUserUseCase) {

    @GetMapping(path = "/user/{id}", produces = CustomMediaType.USER_V1)
    public ResponseEntity<UserDTO> searchUser(@PathVariable("id") final UUID id) {
        return searchUserIdUseCase.searchUserId(SearchUserIdUseCase.SearchUserIdCommand.of(id))
                .map(UserDTO::buildDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/user", consumes = CustomMediaType.USER_V1, produces = CustomMediaType.USER_V1)
    public ResponseEntity<List<UserDTO>> searchUserByCriteria(@RequestBody UserRequestDTO dto) {
        final var result = searchUserUseCase.searchUser(new SearchUserUseCase.SearchUserQuery(dto.name(), dto.email(), dto.login(), dto.document()));
        if(result.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result
                .stream().map(UserDTO::buildDTO)
                .toList());
    }

}
