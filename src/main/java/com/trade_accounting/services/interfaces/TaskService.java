package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Task;
import com.trade_accounting.models.dto.TaskDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface TaskService extends AbstractService<TaskDto>{

    void createAll(List<TaskDto> dtos);

    List<TaskDto> search(Specification<Task> specification);

}
