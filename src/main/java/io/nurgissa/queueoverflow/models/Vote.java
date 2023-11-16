package io.nurgissa.queueoverflow.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vote")
@Builder
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voteid")
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "questionid", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answerid", nullable = false)
    private Answer answer;

    @Column(name = "creationTime")
    private Long createdTime;
}
