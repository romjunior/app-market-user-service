package com.appmarket.controllers.create;

record CreateUserRequest(
                String name,
                String document,
                String login,
                String email,
                String password,
                String confirmedPassword) {
}
