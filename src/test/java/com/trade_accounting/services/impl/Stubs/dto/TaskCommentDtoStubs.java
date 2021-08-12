package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.dto.TaskCommentDto;
import com.trade_accounting.services.impl.Stubs.model.TaskCommentModalStubs;
import com.trade_accounting.utils.mapper.TaskCommentMapper;
import org.mapstruct.factory.Mappers;

public class TaskCommentDtoStubs {

    private static final TaskCommentMapper mapper = Mappers.getMapper(TaskCommentMapper.class);

    public static TaskCommentDto getDto(Long id) {
        return mapper.toDto(TaskCommentModalStubs.getTaskComment(id));
    }

}
