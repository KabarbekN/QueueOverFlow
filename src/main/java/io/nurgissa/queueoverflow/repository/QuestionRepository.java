package io.nurgissa.queueoverflow.repository;

import io.nurgissa.queueoverflow.models.Question;
import io.nurgissa.queueoverflow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByQuestionid(Long id);

    @Query("select q from Question q where q.author = ?1")
    Optional<List<Question>> findByAuthor(User user);
}
