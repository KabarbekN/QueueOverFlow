package io.nurgissa.queueoverflow.token;


import io.nurgissa.queueoverflow.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "token")
public class JwtToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenid;

    private String token;


    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean expired;

   private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
}
