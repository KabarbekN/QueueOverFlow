package io.nurgissa.queueoverflow.mapper;


import io.nurgissa.queueoverflow.dto.TagDto;
import io.nurgissa.queueoverflow.models.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDto tagToTagDto(Tag tag);

    Tag tagDtoToTag(TagDto tagDto);
}
