package com.appmarket.persistence;

import com.appmarket.application.port.out.CreateUser;
import com.appmarket.application.port.out.DeactivateUser;
import com.appmarket.application.port.out.EditUser;
import com.appmarket.application.port.out.SearchUserByEmailOrLogin;
import com.appmarket.application.port.out.SearchUserById;
import com.appmarket.domain.User;
import com.appmarket.persistence.mapper.ListUserEntityToUserMapper;
import com.appmarket.persistence.mapper.UserEntityToUserMapper;
import com.appmarket.persistence.mapper.UserToUserEntityMapper;
import com.appmarket.persistence.model.RoleEnum;
import com.appmarket.persistence.repository.RoleRepository;
import com.appmarket.persistence.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class UserPersistenceAdapter implements CreateUser, EditUser, SearchUserByEmailOrLogin, SearchUserById, DeactivateUser {

    UserRepository userRepository;
    RoleRepository roleRepository;
    UserToUserEntityMapper userToUserEntityMapper;
    UserEntityToUserMapper userEntityToUserMapper;
    ListUserEntityToUserMapper listUserEntityToUserMapper;

    @Override
    @Transactional
    public User createUser(final User user) {
        final var userResult = userRepository.save(
                userToUserEntityMapper.toUserEntity(user)
        );
        final var roleDefault = roleRepository.findByName(RoleEnum.DEFAULT.name());
        final var result = roleRepository.saveRelation(userResult.getId(), roleDefault.getId());

        if (result != 1) {
            throw new RuntimeException("diferente de 1: " + result);
        }
        return userEntityToUserMapper.toUser(userResult);
    }

    @Override
    public User editUser(final User user) {
        return userEntityToUserMapper.toUser(userRepository.save(userToUserEntityMapper.toUserEntity(user)));
    }

    @Override
    public List<User> searchUserByEmailOrLogin(final String email, final String login) {
        return listUserEntityToUserMapper.toUserList(userRepository.findAllByEmailOrLogin(email, login));
    }

    @Override
    public Optional<User> searchUserById(UUID id) {
        return userRepository.findById(id)
                .map(userEntityToUserMapper::toUser);
    }

    @Override
    public void deactivateUser(User user) {
        editUser(user);
    }
}
