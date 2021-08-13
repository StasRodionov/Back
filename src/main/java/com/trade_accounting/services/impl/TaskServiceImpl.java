package com.trade_accounting.services.impl;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Task;
import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.dto.TaskDto;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.TaskCommentRepository;
import com.trade_accounting.repositories.TaskRepository;
import com.trade_accounting.services.interfaces.CheckEntityService;
import com.trade_accounting.services.interfaces.TaskService;
import com.trade_accounting.utils.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskCommentRepository commentRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskDto> search(Specification<Task> specification) {
        return taskRepository.findAll(specification).stream()
                .map(taskMapper::taskToTaskDto)
//                .peek(dto -> dto.setTaskCommentsIds(commentRepository.countTaskCommentByTaskId(dto.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::taskToTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getById(Long id) {
        return taskMapper.taskToTaskDto(taskRepository.getOne(id));
    }

    @Override
    public TaskDto create(TaskDto dto) {

        Task task = taskMapper.taskDtoToTask(dto);
        Employee taskEmployee = employeeRepository.getOne(dto.getEmployeeId());
        Employee taskAuthor = employeeRepository.getOne(dto.getTaskAuthorId());
        LocalDateTime creationDateTime = LocalDateTime.parse(dto.getCreationDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime deadlineDateTime = LocalDateTime.parse(dto.getDeadlineDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<TaskComment> taskComments = dto.getTaskCommentsIds().stream()
                .map(id -> commentRepository.findById(id).orElse(null)).collect(Collectors.toList());

        task.setTaskEmployee(taskEmployee);
        task.setTaskAuthor(taskAuthor);
        task.setCreationDateTime(creationDateTime);
        task.setDeadlineDateTime(deadlineDateTime);
        task.setTaskComments(taskComments);

        return taskMapper.taskToTaskDto(taskRepository.save(task));
    }


    @Override
    public void createAll(List<TaskDto> tasks) {
        var entities = tasks
                .stream()
                .map(dto -> {
                    var taskEntity = taskMapper.taskDtoToTask(dto);

                    taskEntity.setTaskEmployee(employeeRepository.getOne(dto.getEmployeeId()));
                    taskEntity.setTaskAuthor(employeeRepository.getOne(dto.getTaskAuthorId()));

                    LocalDateTime creationDateTime = LocalDateTime.parse(dto.getCreationDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    LocalDateTime deadlineDateTime = LocalDateTime.parse(dto.getDeadlineDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    taskEntity.setCreationDateTime(creationDateTime);
                    taskEntity.setDeadlineDateTime(deadlineDateTime);

                    return taskEntity;
                })
                .collect(Collectors.toList());


        taskRepository.saveAll(entities);
    }

    @Override
    public TaskDto update(TaskDto dto) {
        return this.create(dto);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
