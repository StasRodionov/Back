package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.TaskComment;
import com.trade_accounting.models.dto.TaskCommentDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

public interface TaskCommentService extends Service<TaskCommentDTO> {

    void createAll(Collection<TaskCommentDTO> dtos);

    Collection<TaskCommentDTO> search(Specification<TaskComment> specification);
}
