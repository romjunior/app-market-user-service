package com.appmarket.persistence.mapper;

import com.appmarket.domain.User;
import com.appmarket.domain.User.UserBuilder;
import com.appmarket.persistence.model.UserEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-13T01:16:22-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( userEntity.getId() );
        user.name( userEntity.getName() );
        user.document( userEntity.getDocument() );
        user.login( userEntity.getLogin() );
        user.email( userEntity.getEmail() );
        user.password( userEntity.getPassword() );
        user.active( userEntity.isActive() );

        return user.build();
    }

    @Override
    public User toUserWithRoles(UserEntity userEntity, Set<String> roles) {
        if ( userEntity == null && roles == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        if ( userEntity != null ) {
            user.id( userEntity.getId() );
            user.name( userEntity.getName() );
            user.document( userEntity.getDocument() );
            user.login( userEntity.getLogin() );
            user.email( userEntity.getEmail() );
            user.password( userEntity.getPassword() );
            user.active( userEntity.isActive() );
        }
        if ( roles != null ) {
            Set<String> set = roles;
            if ( set != null ) {
                user.roles( new HashSet<String>( set ) );
            }
        }

        return user.build();
    }

    @Override
    public List<User> toUserList(List<UserEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( entities.size() );
        for ( UserEntity userEntity : entities ) {
            list.add( toUser( userEntity ) );
        }

        return list;
    }

    @Override
    public UserEntity toUserEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( user.id() );
        userEntity.setName( user.name() );
        userEntity.setDocument( user.document() );
        userEntity.setEmail( user.email() );
        userEntity.setLogin( user.login() );
        userEntity.setPassword( user.password() );
        userEntity.setActive( user.active() );

        return userEntity;
    }
}
