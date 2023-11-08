package io.nurgissa.queueoverflow.token;

import io.jsonwebtoken.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TokenRepository extends JpaRepository<JwtToken, Long> {

    @Query(
            """
                SELECT t from JwtToken t inner join User  u on t.user.userid = u.userid
                where u.userid = :userId and (t.expired = false or t.revoked = false )
            """
    )
    List<JwtToken> findAllValidTokensByUser(Long userId);

    Optional<JwtToken> findByToken(String token);

}
