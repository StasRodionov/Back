package com.trade_accounting.services.impl;

import com.trade_accounting.models.Employee;
import com.trade_accounting.models.Task;
import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.dto.TaskCommentDto;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.TaskCommentRepository;
import com.trade_accounting.repositories.TaskRepository;
import com.trade_accounting.services.interfaces.TaskCommentService;
import com.trade_accounting.utils.mapper.TaskCommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TaskCommentServiceImpl implements TaskCommentService {

    private final TaskCommentRepository taskCommentRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final CheckEntityServiceImpl checkEntityService;
    private final TaskCommentMapper taskCommentMapper;

    @Override
    public List<TaskCommentDto> search(Specification<TaskComment> specification) {
        return executeSearch(taskCommentRepository, taskCommentMapper::toDto, specification);
    }

    @Override
    public List<TaskCommentDto> getAll() {
        return taskCommentRepository.findAll()
                .stream()
                .map(taskCommentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskCommentDto getById(Long id) {
//        return taskCommentRepository.findById(id)
//                .map(taskCommentMapper::toDto).orElse(new TaskCommentDto());
        return taskCommentMapper.toDto(taskCommentRepository.getOne(id));
    }

    @Override
    public TaskCommentDto create(TaskCommentDto dto) {
//        checkEntityService.checkExistsTaskById(dto.getTaskId());
//        var task = taskRepository.findById(dto.getTaskId()).get();
//
//        var commentEntity = taskCommentMapper.toModel(dto);
//
//        commentEntity.setPublisher(employeeRepository.getOne(dto.getPublisherId()));
//        commentEntity.setTask(taskRepository.getOne(dto.getTaskId()));
//
//        var saved = commentRepository.save(commentEntity);
//        dto.setId(saved.getId());
//        task.getTaskComments().add(commentEntity);
//
//        return taskCommentMapper.toDto(saved);
        TaskComment taskComment = taskCommentMapper.toModel(dto);
        Employee employee = employeeRepository.getOne(dto.getPublisherId());
        Task task = taskRepository.getOne(dto.getTaskId());

        LocalDateTime time = LocalDateTime.parse(dto.getPublishedDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        taskComment.setPublisher(employee);
        taskComment.setTask(task);
        taskComment.setPublishedDateTime(time);

        return taskCommentMapper.toDto(taskCommentRepository.save(taskComment));

    }

    public void createAll(List<TaskCommentDto> dtos) {
        dtos.forEach(dto -> {
            var entity = taskCommentMapper.toModel(dto);
            entity.setTask(taskRepository.getOne(dto.getTaskId()));
            entity.setPublisher(employeeRepository.getOne(dto.getPublisherId()));

            var taskOption = taskRepository.findById(dto.getTaskId());

            if (taskOption.isPresent()) {
                taskCommentRepository.save(entity);
                taskOption.get().getTaskComments().add(entity);
            }
        });

    }

    @Override
    public TaskCommentDto update(TaskCommentDto dto) {
        return this.create(dto);
    }

    @Override
    public void deleteById(Long id) {
//        checkEntityService.checkExistsTaskCommentById(id);
//        taskCommentRepository.findById(id).ifPresent(taskComment -> {
//            var task = taskComment.getTask();
//            if (task != null) {
//                task.getTaskComments().remove(taskComment);
//            }
//            taskCommentRepository.deleteById(id);
//        });
        taskCommentRepository.deleteById(id);
    }
}

