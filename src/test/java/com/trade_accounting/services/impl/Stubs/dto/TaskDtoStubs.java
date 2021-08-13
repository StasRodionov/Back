package com.trade_accounting.services.impl.Stubs.dto;

import com.trade_accounting.models.Task;
import com.trade_accounting.models.dto.TaskDto;
import com.trade_accounting.services.impl.Stubs.model.TaskModelStubs;
import com.trade_accounting.utils.mapper.TaskMapper;
import org.mapstruct.factory.Mappers;

public class TaskDtoStubs {

    private static final TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    public static TaskDto toDto(Long id) {
        return taskMapper.taskToTaskDto(TaskModelStubs.getTask(id));

    }

}
