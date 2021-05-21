package com.trade_accounting.services.impl;

import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.Position;
import com.trade_accounting.models.Project;
import com.trade_accounting.models.dto.ProjectDto;
import com.trade_accounting.repositories.ProjectRepository;
import com.trade_accounting.services.interfaces.ProjectService;
import com.trade_accounting.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final DtoMapper dtoMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, DtoMapper dtoMapper) {
        this.projectRepository = projectRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<ProjectDto> getAll() {
        return projectRepository.findAll().stream()
                .map(dtoMapper::projectToProjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if(project.isEmpty()){
            throw new NotFoundEntityException("There is not project with “id”");
        }
        return dtoMapper.projectToProjectDto(project.get());

    }

    @Override
    public ProjectDto create(ProjectDto projectDto) {
        Project project = projectRepository.save(
                dtoMapper.projectDtoToProject(projectDto)
        );

        return dtoMapper.projectToProjectDto(project);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto) {
        return create(projectDto);
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
