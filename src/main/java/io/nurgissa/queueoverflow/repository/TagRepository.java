package io.nurgissa.queueoverflow.repository;

import io.nurgissa.queueoverflow.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
