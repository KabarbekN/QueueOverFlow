package io.nurgissa.queueoverflow.repository.dao;

import io.nurgissa.queueoverflow.models.Question;
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
public class QuestionSearchDao {

    private final EntityManager entityManager;

    public List<Question> findAllByCriteria(
            QuestionSearchRequest questionSearchRequest
    ){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Question> questionRoot = criteriaQuery.from(Question.class);

        if (questionSearchRequest.getTitle() != null){
            Predicate predicate = criteriaBuilder
                    .like(questionRoot.get("title"), "%" + questionSearchRequest.getTitle() + "%");
            predicates.add(predicate);
        }

        if(questionSearchRequest.getContent() != null){
            Predicate predicate = criteriaBuilder
                    .like(questionRoot.get("content"), "%" + questionSearchRequest.getContent() + "%");
            predicates.add(predicate);
        }

        criteriaQuery.where(
                criteriaBuilder.or(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<Question> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();


    }
}
