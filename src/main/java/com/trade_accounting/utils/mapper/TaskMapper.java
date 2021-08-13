package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.Task;
import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.dto.TaskDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    default Task taskDtoToTask(TaskDto taskdto) {

        if (taskdto == null) {
            return null;
        }

        return Task.builder()
                .id(taskdto.getId())
                .description(taskdto.getDescription())
                .completed(taskdto.isCompleted())
                .build();
    }

    default TaskDto taskToTaskDto(Task task) {

        TaskDto taskDto = new TaskDto();
        if (task == null) {
            return null;
        } else {
            taskDto.setId(task.getId());
            taskDto.setDescription(task.getDescription());
            taskDto.setCompleted(task.isCompleted());
            taskDto.setCreationDateTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(task.getCreationDateTime()));
            taskDto.setDeadlineDateTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(task.getDeadlineDateTime()));

            if (task.getTaskEmployee() == null) {
                return null;
            } else {
                taskDto.setEmployeeId(task.getTaskEmployee().getId());

                if (task.getTaskAuthor() == null) {
                    return null;
                } else {
                    taskDto.setTaskAuthorId(task.getTaskAuthor().getId());

                    if (task.getTaskComments() == null) {
                        return null;
                    } else {
                        taskDto.setTaskCommentsIds(task.getTaskComments().stream().map(TaskComment::getId).collect(Collectors.toList()));
                        return taskDto;
                    }
                }
            }
        }
    }
}
