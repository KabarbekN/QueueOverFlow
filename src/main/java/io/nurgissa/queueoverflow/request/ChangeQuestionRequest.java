package io.nurgissa.queueoverflow.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangeQuestionRequest {
    private Long questionId;
    private String newTitle;
    private String newContent;
}
