package io.nurgissa.queueoverflow.dto.answer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAnswerDto {
    private Long questionId;
    private String content;
}
