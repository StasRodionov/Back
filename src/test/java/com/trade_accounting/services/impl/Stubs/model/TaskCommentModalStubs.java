package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.TaskComment;

import java.time.LocalDateTime;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getEmployee;
import static com.trade_accounting.services.impl.Stubs.ModelStubs.getTask;

public class TaskCommentModalStubs {
    public static TaskComment getTaskComment(Long id) {
        return TaskComment.builder()
                .id(id)
                .commentContent("Comment " + id)
                .publisher(getEmployee(1L))
                .publishedDateTime(LocalDateTime.now())
                .task(getTask(1L))
                .build();
    }
}
