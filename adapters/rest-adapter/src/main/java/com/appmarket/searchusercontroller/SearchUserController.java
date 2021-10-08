package com.appmarket.searchusercontroller;

import com.appmarket.application.port.in.SearchUserIdUseCase;
import com.appmarket.domain.User;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Value
@RestController
public class SearchUserController {

    SearchUserIdUseCase searchUserIdUseCase;

    @GetMapping(path = "/user/{id}", produces = "application/vnd.user.v1+json")
    ResponseEntity<User> createUser(@PathVariable("id") final UUID id) {

        final var user = searchUserIdUseCase.searchUserId(SearchUserIdUseCase.SearchUserIdCommand.of(id));

        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
