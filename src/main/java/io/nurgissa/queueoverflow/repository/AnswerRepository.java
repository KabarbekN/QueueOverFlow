package io.nurgissa.queueoverflow.repository;

import io.nurgissa.queueoverflow.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByQuestionQuestionid(Long questionId);
}
