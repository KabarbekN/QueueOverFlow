package io.nurgissa.queueoverflow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private User author;

    @ManyToOne
    @JoinColumn(name = "questionid", nullable = false)
    @JsonIgnore
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answerid", nullable = false)
    @JsonIgnore
    private Answer answer;


    @Column(name = "value")
    private Integer value;

    public enum VoteValue {
        UPVOTE(1),
        DOWNVOTE(-1);

        private final Integer value;

        VoteValue(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }


    @Column(name = "creationtime")
    private Long createdTime;

    @Override
    public String toString() {
        return "Vote{" +
                "voteId=" + voteId +
                ", createdTime=" + createdTime +
                '}';
    }
}
