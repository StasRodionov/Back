package com.trade_accounting.services.impl;

import com.trade_accounting.models.Task;
import com.trade_accounting.models.dto.TaskDto;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.TaskCommentRepository;
import com.trade_accounting.repositories.TaskRepository;
import com.trade_accounting.services.impl.Stubs.dto.TaskDtoStubs;
import com.trade_accounting.services.impl.Stubs.model.TaskModelStubs;
import com.trade_accounting.utils.mapper.TaskMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private TaskCommentRepository taskCommentRepository;

    @Mock
    private TaskRepository taskRepository;

    @Spy
    private TaskMapper dtoMapper;

    @Test
    void getAll() {
        when(taskRepository.findAll())
                .thenReturn(
                        List.of(
                                TaskModelStubs.getTask(1L),
                                TaskModelStubs.getTask(2L),
                                TaskModelStubs.getTask(3L)
                        ));
        List<TaskDto> taskDtos = taskService.getAll();

        assertEquals(3, taskDtos.size());
    }

    @Test
    void getById() {
        when(taskRepository.getOne(anyLong()))
                .thenReturn(TaskModelStubs.getTask(1L));

        TaskDto taskDto = taskService.getById(1L);

        assertEquals(1, taskDto.getId());
    }

    @Test
    void create() {
        saveOrUpdate();
    }

    @Test
    void update() {
        saveOrUpdate();
    }

    @Test
    void deleteById() {
        taskService.deleteById(anyLong());
        verify(taskRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(taskRepository.save(any(Task.class)))
                .thenReturn(TaskModelStubs.getTask(1L));

        TaskDto taskDto = taskService.create(TaskDtoStubs.toDto(1L));

        assertEquals(1, taskDto.getId());
        verify(taskRepository).save(any(Task.class));
    }

}
