package io.nurgissa.queueoverflow.dto.answer;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDto {
    @JsonProperty("content")
    private String content;
    private String createdTime;
}
