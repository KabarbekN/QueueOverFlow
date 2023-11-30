package io.nurgissa.queueoverflow.dto.question;


import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {
    private String title;
    private String content;
    private String createdTime;
}
