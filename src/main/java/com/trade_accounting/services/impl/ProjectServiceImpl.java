package com.trade_accounting.services.impl;

import com.trade_accounting.models.Project;
import com.trade_accounting.models.dto.ProjectDto;
import com.trade_accounting.repositories.ProjectRepository;
import com.trade_accounting.services.interfaces.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectDto> getAll() {
        return projectRepository.getAll();
    }

    @Override
    public ProjectDto getById(Long id) {
        return projectRepository.getById(id);
    }

    @Override
    public void create(ProjectDto projectDto) {
        projectRepository.save(new Project(
                projectDto.getName(),
                projectDto.getCode(),
                projectDto.getDescription()
        ));
    }

    @Override
    public void update(ProjectDto projectDto) {
        projectRepository.save(new Project(
                projectDto.getId(),
                projectDto.getName(),
                projectDto.getCode(),
                projectDto.getDescription()
        ));
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
