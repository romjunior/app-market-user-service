package com.appmarket.persistence.mapper;

import com.appmarket.domain.User;
import com.appmarket.domain.User.UserBuilder;
import com.appmarket.persistence.model.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-27T03:59:07-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class UserEntityToUserMapperImpl implements UserEntityToUserMapper {

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
}
