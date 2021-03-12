package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.dto.TaskCommentDTO;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.TaskCommentRepository;
import com.trade_accounting.repositories.TaskRepository;
import com.trade_accounting.services.interfaces.SearchableService;
import com.trade_accounting.utils.ModelDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TaskCommentService implements SearchableService<TaskCommentDTO, TaskComment> {

    public final TaskCommentRepository commentRepository;
    public final TaskRepository taskRepository;
    public final EmployeeRepository employeeRepository;

    @Override
    public Collection<TaskCommentDTO> search(Specification<TaskComment> specification) {
        return commentRepository.findAll(specification)
                .stream()
                .map(ModelDtoConverter::toTaskCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<TaskCommentDTO> getAll() {
        return commentRepository.findAll()
                .stream()
                .map(ModelDtoConverter::toTaskCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskCommentDTO> getById(Long id) {
        return commentRepository.findById(id)
                .map(ModelDtoConverter::toTaskCommentDTO);
    }

    @Override
    public void create(TaskCommentDTO dto) {
        var entity = ModelDtoConverter.toTaskCommentEntity(dto);

        entity.setPublisher(employeeRepository.getOne(dto.getPublisherId()));
        entity.setTask(taskRepository.getOne(dto.getTaskId()));

        var taskOption = taskRepository.findById(dto.getTaskId());

        if (taskOption.isPresent()) {
            commentRepository.save(entity);
            taskOption.get().getTaskComments().add(entity);
        }
    }

    public void createAll(Collection<TaskCommentDTO> dtos) {
        dtos.forEach(dto -> {
            var entity = ModelDtoConverter.toTaskCommentEntity(dto);
            entity.setTask(taskRepository.getOne(dto.getTaskId()));
            entity.setPublisher(employeeRepository.getOne(dto.getPublisherId()));

            var taskOption = taskRepository.findById(dto.getTaskId());

            if (taskOption.isPresent()) {
                commentRepository.save(entity);
                taskOption.get().getTaskComments().add(entity);
            }
        });

    }

    @Override
    public void update(TaskCommentDTO dto) {
        this.create(dto);
    }

    @Override
    public void deleteById(Long id) {
        var notFoundMessageFormat = "Задача с id: %d не найдена.";

        commentRepository.findById(id).ifPresentOrElse(
                taskComment -> {
                    taskComment.getTask().getTaskComments().remove(taskComment);
                    commentRepository.deleteById(id);
                },
                () -> {
                    throw new NotFoundEntityException(String.format(notFoundMessageFormat, id));
                });

    }
}
