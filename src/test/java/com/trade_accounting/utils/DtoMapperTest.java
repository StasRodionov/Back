package com.trade_accounting.utils;

import com.trade_accounting.models.ProductGroup;
import com.trade_accounting.models.Task;
import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.dto.ProductGroupDto;
import com.trade_accounting.models.dto.TaskCommentDto;
import com.trade_accounting.models.dto.TaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DtoMapperTest {

    @Spy
    private DtoMapperImpl dtoMapper;

    @Test
    void productGroupToProductGroupDto() {
        ProductGroupDto productGroupDto = new ProductGroupDto();
        productGroupDto.setParentId(1L);

        ProductGroup productGroup = dtoMapper.productGroupDtoToProductGroup(productGroupDto);
        assertEquals(productGroupDto.getParentId(), productGroup.getProductGroup().getId());
    }

    @Test
    void taskToTaskDto() {
        TaskDto taskDto = new TaskDto();
        taskDto.setEmployeeId(1L);
        taskDto.setTaskAuthorId(1L);

        Task taskEmployee = dtoMapper.taskDtoToTask(taskDto);
        assertEquals(taskDto.getEmployeeId(), taskEmployee.getTaskEmployee().getId());

        Task taskAuthor = dtoMapper.taskDtoToTask(taskDto);
        assertEquals(taskDto.getTaskAuthorId(), taskAuthor.getTaskAuthor().getId());
    }

    @Test
    void taskCommentToTaskCommentDto() {
        TaskCommentDto taskCommentDto = new TaskCommentDto();
        taskCommentDto.setPublisherId(1L);
        taskCommentDto.setTaskId(1L);

        TaskComment taskCommentPublisher = dtoMapper.taskCommentDtoToTaskComment(taskCommentDto);
        assertEquals(taskCommentDto.getPublisherId(), taskCommentPublisher.getPublisher().getId());

        TaskComment taskCommentTask = dtoMapper.taskCommentDtoToTaskComment(taskCommentDto);
        assertEquals(taskCommentDto.getTaskId(), taskCommentTask.getTask().getId());

    }
}
