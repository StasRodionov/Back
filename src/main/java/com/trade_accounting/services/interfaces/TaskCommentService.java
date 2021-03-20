package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.dto.TaskCommentDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TaskCommentService {

    void createAll(List<TaskCommentDTO> dtos);

    List<TaskCommentDTO> search(Specification<TaskComment> specification);

    List<TaskCommentDTO> getAll();

    Optional<TaskCommentDTO> getById(Long id);

    TaskCommentDTO create(TaskCommentDTO taskDTO);

    TaskCommentDTO update(TaskCommentDTO taskDTO);

    void deleteById(Long id);
}
