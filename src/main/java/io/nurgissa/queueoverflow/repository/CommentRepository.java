package io.nurgissa.queueoverflow.repository;

import io.nurgissa.queueoverflow.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
