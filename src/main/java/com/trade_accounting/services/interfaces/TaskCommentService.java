package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.dto.TaskCommentDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface TaskCommentService {

    void createAll(List<TaskCommentDto> dtos);

    List<TaskCommentDto> search(Specification<TaskComment> specification);

    List<TaskCommentDto> getAll();

    Optional<TaskCommentDto> getById(Long id);

    TaskCommentDto create(TaskCommentDto taskDTO);

    TaskCommentDto update(TaskCommentDto taskDTO);

    void deleteById(Long id);
}
