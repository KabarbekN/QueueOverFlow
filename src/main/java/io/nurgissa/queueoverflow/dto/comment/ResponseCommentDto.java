package io.nurgissa.queueoverflow.dto.comment;

import io.nurgissa.queueoverflow.dto.UserDto;
import io.nurgissa.queueoverflow.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseCommentDto {
    private CommentDto commentDto;
    private UserDto userDto;
}
