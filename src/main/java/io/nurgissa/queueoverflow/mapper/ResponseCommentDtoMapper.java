package io.nurgissa.queueoverflow.mapper;


import io.nurgissa.queueoverflow.dto.comment.CommentDto;
import io.nurgissa.queueoverflow.dto.comment.ResponseCommentDto;
import io.nurgissa.queueoverflow.models.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ResponseCommentDtoMapper {
    private final CommentMapper commentMapper;
    private final UserMapper userMapper;

    public ResponseCommentDto commentToResponseCommentDto(Comment comment){
        CommentDto commentDto = commentMapper.commentToCommentDto(comment);
        commentDto.setCreatedTime(formatToDate(Long.parseLong(commentDto.getCreatedTime() ) * 1000));
        return ResponseCommentDto.builder()
                .commentDto(commentDto)
                .userDto(userMapper.userToUserDto(comment.getAuthor()))
                .build();
    }

    public String formatToDate(Long timestamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return simpleDateFormat.format(new Date(timestamp));
    }

}
