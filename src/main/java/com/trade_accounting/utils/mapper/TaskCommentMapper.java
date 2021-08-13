package com.trade_accounting.utils.mapper;

import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.dto.TaskCommentDto;
import org.mapstruct.Mapper;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface TaskCommentMapper {

    default TaskComment toModel(TaskCommentDto taskCommentDto) {
        if (taskCommentDto == null) {
            return null;
        }

        return TaskComment.builder()
                .id(taskCommentDto.getId())
                .commentContent(taskCommentDto.getCommentContent())
                .build();
    }

    default TaskCommentDto toDto(TaskComment taskComment) {
        TaskCommentDto taskCommentDto = new TaskCommentDto();
        if (taskComment == null) {
            return null;
        } else {
            taskCommentDto.setId(taskComment.getId());
            taskCommentDto.setCommentContent(taskComment.getCommentContent());
            taskCommentDto.setPublishedDateTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(taskComment.getPublishedDateTime()));

            if (taskComment.getPublisher() == null) {
                return null;
            } else {
                taskCommentDto.setPublisherId(taskComment.getPublisher().getId());

                if (taskComment.getTask() == null) {
                    return null;
                } else {
                    taskCommentDto.setTaskId(taskComment.getTask().getId());
                    return taskCommentDto;
                }
            }
        }
    }
}
