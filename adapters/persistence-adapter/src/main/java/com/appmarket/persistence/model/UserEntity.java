package com.appmarket.persistence.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    UUID id;
    @Column(name = "name")
    String name;
    @Column(name = "document")
    String document;
    @Column(name = "email")
    String email;
    @Column(name = "login")
    String login;
    @Column(name = "password")
    String password;
    @Column(name = "active")
    boolean active;
    @CreatedDate
    @Column(name = "creation_date", updatable = false)
    LocalDateTime creationDate;
    @LastModifiedDate
    @Column(name = "update_date")
    LocalDateTime updateDate;
}
