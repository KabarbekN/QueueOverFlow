package io.nurgissa.queueoverflow.repository.dao;

import io.nurgissa.queueoverflow.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserSearchDao {

    private final EntityManager entityManager;

    public List<User> findAllBySimpleQuery(
            String username,
            String email
    ){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);

        Predicate usernamePredicate = criteriaBuilder
                .like(root.get("username"), "%" + username + "%");
        Predicate emailPredicate = criteriaBuilder
                .like(root.get("email"), "%" + email + "%");

        Predicate orPredicate = criteriaBuilder.or(
                usernamePredicate,
                emailPredicate
        );

        criteriaQuery.where(orPredicate);
        TypedQuery<User> userTypedQuery = entityManager.createQuery(criteriaQuery);
        return userTypedQuery.getResultList();
    }


    public List<User> findAllByCriteria(
            SearchRequest searchRequest
    ){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<User> userRoot = criteriaQuery.from(User.class);

        if (searchRequest.getUsername() != null){
            Predicate predicate = criteriaBuilder
                    .like(userRoot.get("username"), "%" + searchRequest.getUsername() + "%");
            predicates.add(predicate);
        }

        if(searchRequest.getEmail() != null){
            Predicate predicate = criteriaBuilder
                    .like(userRoot.get("email"), "%" + searchRequest.getEmail() + "%");
            predicates.add(predicate);
        }

        criteriaQuery.where(
                criteriaBuilder.or(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();


    }
}
