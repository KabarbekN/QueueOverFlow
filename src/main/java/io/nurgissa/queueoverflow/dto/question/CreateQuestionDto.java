package io.nurgissa.queueoverflow.dto.question;

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
    private Set<Long> tagIds;
}
