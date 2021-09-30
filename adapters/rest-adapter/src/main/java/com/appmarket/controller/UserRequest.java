package com.appmarket.controller;

record UserRequest(
                String name,
                String document,
                String login,
                String email,
                String password,
                String confirmedPassword) {
}
