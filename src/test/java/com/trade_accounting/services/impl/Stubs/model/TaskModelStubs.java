package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Task;
import com.trade_accounting.services.impl.Stubs.ModelStubs;

import java.time.LocalDateTime;
import java.util.List;

public class TaskModelStubs {

    public static Task getTask(Long id) {
        return Task.builder()
                .id(id)
                .description("Some description")
                .taskEmployee(ModelStubs.getEmployee(id))
                .taskAuthor(ModelStubs.getEmployee(id))
                .creationDateTime(LocalDateTime.now())
                .deadlineDateTime(LocalDateTime.now())
                .completed(true)
                .taskComments(List.of(ModelStubs.getTaskComment(id)))
                .build();
    }
}
