package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.entity.util.Task;
import com.trade_accounting.models.dto.util.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    //Task
    @Mappings({
            @Mapping(source = "employeeId", target = "taskEmployee.id"),
            @Mapping(source = "taskAuthorId", target = "taskAuthor.id")

    })
    Task taskDtoToTask(TaskDto taskDto);

    @Mappings({
            @Mapping(source = "taskEmployee.id", target = "employeeId"),
            @Mapping(source = "taskAuthor.id", target = "taskAuthorId")
    })
    TaskDto taskToTaskDto(Task task);
}
