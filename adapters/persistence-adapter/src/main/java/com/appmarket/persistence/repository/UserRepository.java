package com.appmarket.persistence.repository;

import com.appmarket.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    List<UserEntity> findAllByEmailOrLogin(final String email, final String login);
}
