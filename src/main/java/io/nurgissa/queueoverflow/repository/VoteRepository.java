package io.nurgissa.queueoverflow.repository;


import io.nurgissa.queueoverflow.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository  extends JpaRepository<Vote, Long> {

    Optional<Vote> findByQuestionQuestionid(Long id);

    Optional<Vote> findByAnswerAnswerId(Long id);
}
