package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.util.TaskComment;
import com.trade_accounting.models.dto.util.TaskCommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskCommentMapper {
    //TaskCommentMapper
    @Mapping(source = "publishedDateTime", target = "publishedDateTime", dateFormat = "dd-MM-yyyy HH:mm")
    TaskComment toModel(TaskCommentDto taskCommentDto);

    @Mapping(target = "publisherId", source = "publisher.id")
    @Mapping(source = "publishedDateTime", target = "publishedDateTime", dateFormat = "dd-MM-yyyy HH:mm")
    TaskCommentDto toDto(TaskComment taskComment);
}
