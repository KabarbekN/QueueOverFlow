package io.nurgissa.queueoverflow.mapper;

import io.nurgissa.queueoverflow.dto.comment.CommentDto;
import io.nurgissa.queueoverflow.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDto commentToCommentDto(Comment comment);
}
