package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.Task;
import com.trade_accounting.models.dto.TaskDTO;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.TaskCommentRepository;
import com.trade_accounting.repositories.TaskRepository;
import com.trade_accounting.services.interfaces.SearchableService;
import com.trade_accounting.utils.ModelDtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class TaskService implements SearchableService<TaskDTO, Task> {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskCommentRepository commentRepository;

    @Override
    public Collection<TaskDTO> search(Specification<Task> specification) {
        return taskRepository.findAll(specification).stream()
                .map(ModelDtoConverter::toTaskDTO)
                .peek(dto -> dto.setCommentCount(commentRepository.countTaskCommentByTaskId(dto.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TaskDTO> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(ModelDtoConverter::toTaskDTO)
                .peek(dto -> dto.setCommentCount(commentRepository.countTaskCommentByTaskId(dto.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskDTO> getById(Long id) {
        var taskEntity = taskRepository.findById(id);

        return taskEntity
                .map(entity -> {
                    var dto = ModelDtoConverter.toTaskDTO(entity);
                    dto.setCommentCount(commentRepository.countTaskCommentByTaskId(id));
                    return dto;
                });
    }

    @Override
    public void create(TaskDTO dto) {
        String exceptionMessage = "Связанный с задачей пользователь с id: %d не найден";

        var taskEntity = ModelDtoConverter.toTaskEntity(dto);

        if (!employeeRepository.existsById(dto.getEmployeeId())) {
            throw new NotFoundEntityException(String.format(exceptionMessage, dto.getEmployeeId()));
        }

        if (!employeeRepository.existsById(dto.getTaskAuthorId())) {
            throw new NotFoundEntityException(String.format(exceptionMessage, dto.getEmployeeId()));
        }

        taskEntity.setTaskEmployee(employeeRepository.getOne(dto.getEmployeeId()));
        taskEntity.setTaskAuthor(employeeRepository.getOne(dto.getTaskAuthorId()));

        taskRepository.save(taskEntity);
    }

    public void createAll(Collection<TaskDTO> tasks) {
        var entities = tasks
                .stream()
                .map(dto -> {
                    var taskEntity = ModelDtoConverter.toTaskEntity(dto);

                    taskEntity.setTaskEmployee(employeeRepository.getOne(dto.getEmployeeId()));
                    taskEntity.setTaskAuthor(employeeRepository.getOne(dto.getTaskAuthorId()));

                    return taskEntity;
                })
                .collect(Collectors.toList());


        taskRepository.saveAll(entities);
    }

    @Override
    public void update(TaskDTO dto) {
        this.create(dto);
    }

    @Override
    public void deleteById(Long id) {
        var notFoundMessageFormat = "Задача с id: %d не найдена.";

        if (!taskRepository.existsById(id)) {
            throw new NotFoundEntityException(String.format(notFoundMessageFormat, id));
        }

        taskRepository.deleteById(id);
    }

}
