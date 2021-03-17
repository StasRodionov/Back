package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.dto.TaskCommentDTO;
import com.trade_accounting.repositories.EmployeeRepository;
import com.trade_accounting.repositories.TaskCommentRepository;
import com.trade_accounting.repositories.TaskRepository;
import com.trade_accounting.services.interfaces.TaskCommentService;
import com.trade_accounting.utils.ModelDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TaskCommentServiceImpl implements TaskCommentService {

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
    public TaskCommentDTO create(TaskCommentDTO dto) {
        var notFoundMessageFormat = "Задача с id: %d не найдена.";

        var taskOption = taskRepository.findById(dto.getTaskId());

        if (taskOption.isPresent()) {
            var commentEntity = ModelDtoConverter.toTaskCommentEntity(dto);

            commentEntity.setPublisher(employeeRepository.getOne(dto.getPublisherId()));
            commentEntity.setTask(taskRepository.getOne(dto.getTaskId()));

            var saved = commentRepository.save(commentEntity);

            var task = taskOption.get();
            task.getTaskComments().add(commentEntity);

            return ModelDtoConverter.toTaskCommentDTO(saved);
        } else {
            throw new NotFoundEntityException(String.format(notFoundMessageFormat, dto.getPublisherId()));
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
    public TaskCommentDTO update(TaskCommentDTO dto) {
        return this.create(dto);
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
