package com.appmarket.persistence.mapper;

import com.appmarket.domain.User;
import com.appmarket.domain.User.UserBuilder;
import com.appmarket.persistence.model.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-27T03:59:07-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class ListUserEntityToUserMapperImpl implements ListUserEntityToUserMapper {

    @Override
    public List<User> toUserList(List<UserEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( entities.size() );
        for ( UserEntity userEntity : entities ) {
            list.add( userEntityToUser( userEntity ) );
        }

        return list;
    }

    protected User userEntityToUser(UserEntity userEntity) {
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
