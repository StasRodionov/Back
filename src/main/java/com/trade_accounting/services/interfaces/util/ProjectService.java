package com.trade_accounting.services.interfaces.util;

import com.trade_accounting.models.dto.util.ProjectDto;
import com.trade_accounting.models.entity.util.Project;

import java.util.List;

public interface ProjectService extends AbstractService<ProjectDto>, SearchableService<Project, ProjectDto> {

    List<ProjectDto> searchByString(String text);
}
