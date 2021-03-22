package com.trade_accounting.services.interfaces;

import com.trade_accounting.models.Task;
import com.trade_accounting.models.dto.TaskDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    void createAll(List<TaskDTO> dtos);

    List<TaskDTO> search(Specification<Task> specification);

    List<TaskDTO> getAll();

    Optional<TaskDTO> getById(Long id);

    TaskDTO create(TaskDTO taskDTO);

    TaskDTO update(TaskDTO taskDTO);

    void deleteById(Long id);
}
