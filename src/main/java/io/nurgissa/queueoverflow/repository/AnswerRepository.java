package io.nurgissa.queueoverflow.repository;

import io.nurgissa.queueoverflow.models.Answer;
import io.nurgissa.queueoverflow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByQuestionQuestionid(Long questionId);

    @Query("select a from Answer a where a.author = ?1")
    Optional<List<Answer>> findByAuthor(User user);
}
