package com.trade_accounting.utils.mapper.util;

import com.trade_accounting.models.dto.util.TaskDto;
import com.trade_accounting.models.entity.util.Task;
import com.trade_accounting.models.entity.util.TaskComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    //Task
    @Mapping(source = "employeeId", target = "taskEmployee.id")
    @Mapping(source = "taskAuthorId", target = "taskAuthor.id")
    @Mapping(source = "creationDateTime", target = "creationDateTime", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "deadlineDateTime", target = "deadlineDateTime", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "taskComments", target = "taskComments")
    Task taskDtoToTask(TaskDto taskDto);

    @Mapping(source = "taskEmployee.id", target = "employeeId")
    @Mapping(source = "taskAuthor.id", target = "taskAuthorId")
    @Mapping(source = "creationDateTime", target = "creationDateTime", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "deadlineDateTime", target = "deadlineDateTime", dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(source = "taskComments", target = "taskComments")
    TaskDto taskToTaskDto(Task task);

    default Long taskCommentsToLong(TaskComment taskComment) {
        return taskComment.getId();
    }

    default TaskComment longToTaskComment(Long id) {
        return TaskComment.builder()
                .id(id)
                .build();
    }
}
