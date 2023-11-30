package io.nurgissa.queueoverflow.dto.comment;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private String content;
    private String createdTime;
}
