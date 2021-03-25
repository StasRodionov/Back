package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Task;
import com.trade_accounting.models.dto.TaskDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    void createAll(List<TaskDto> dtos);

    List<TaskDto> search(Specification<Task> specification);

    List<TaskDto> getAll();

    Optional<TaskDto> getById(Long id);

    TaskDto create(TaskDto taskDTO);

    TaskDto update(TaskDto taskDTO);

    void deleteById(Long id);
}
