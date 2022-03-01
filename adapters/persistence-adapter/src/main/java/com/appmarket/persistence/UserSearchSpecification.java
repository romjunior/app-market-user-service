package com.appmarket.persistence;

import com.appmarket.persistence.model.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public final class UserSearchSpecification {

    private UserSearchSpecification() {}

    static Specification<UserEntity> buildSearch(final String name, final String email, final String login, final String document) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("name"), name));
            }

            if(email != null && !email.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("email"), email));
            }

            if(login != null && login.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("login"), login));
            }

            if(document != null && document.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("document"), document));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
