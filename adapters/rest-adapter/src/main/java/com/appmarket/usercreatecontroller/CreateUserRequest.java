package com.appmarket.usercreatecontroller;

record CreateUserRequest(
                String name,
                String document,
                String login,
                String email,
                String password,
                String confirmedPassword) {
}
