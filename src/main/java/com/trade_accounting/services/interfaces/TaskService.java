package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Task;
import com.trade_accounting.models.dto.TaskDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

public interface TaskService extends Service<TaskDTO>{

    void createAll(Collection<TaskDTO> dtos);

    Collection<TaskDTO> search(Specification<Task> specification);
}
