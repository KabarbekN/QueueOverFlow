package io.nurgissa.queueoverflow.repository;

import io.nurgissa.queueoverflow.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByQuestionid(Long id);
}
