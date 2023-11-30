package io.nurgissa.queueoverflow.repository.dao;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuestionSearchRequest {
    private String title;
    private String content;
}
