package io.nurgissa.queueoverflow.repository;

import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByUserid(Long id);

    @Query("select u from User u where u.username = ?1")
    Optional<User> findByUsername(String username);

}
