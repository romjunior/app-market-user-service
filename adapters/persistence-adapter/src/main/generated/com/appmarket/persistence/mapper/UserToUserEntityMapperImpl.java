package com.appmarket.persistence.mapper;

import com.appmarket.domain.User;
import com.appmarket.persistence.model.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-27T03:59:07-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class UserToUserEntityMapperImpl implements UserToUserEntityMapper {

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
