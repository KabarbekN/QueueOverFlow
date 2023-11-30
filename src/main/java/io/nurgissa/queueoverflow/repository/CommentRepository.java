package io.nurgissa.queueoverflow.repository;

import io.nurgissa.queueoverflow.models.Comment;
import io.nurgissa.queueoverflow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.author = ?1")
    Optional<List<Comment>> findByAuthor(User user);

}
