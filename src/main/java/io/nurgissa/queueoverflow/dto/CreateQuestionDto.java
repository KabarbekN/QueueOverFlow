package io.nurgissa.queueoverflow.dto;

import io.nurgissa.queueoverflow.models.Tag;
import io.nurgissa.queueoverflow.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateQuestionDto {
    private String title;
    private String content;
    private User user;
    private Set<Tag> tags;
}
