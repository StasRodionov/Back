package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.util.TaskComment;
import com.trade_accounting.models.dto.util.TaskCommentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskCommentMapper {
    //TaskCommentMapper
    TaskComment toModel(TaskCommentDto taskCommentDto);

    TaskCommentDto toDto(TaskComment taskComment);
}
