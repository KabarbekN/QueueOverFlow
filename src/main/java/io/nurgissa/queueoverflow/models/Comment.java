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
@Builder
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    private Long commentId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "questionid")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answerid")
    private Answer answer;

    @Column(name = "creationTime")
    private Long createdTime;
}
