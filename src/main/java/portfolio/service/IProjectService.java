package portfolio.service;

import portfolio.model.Project;

import java.util.List;

public interface IProjectService {

    List<Project> findAll();
    Project save(Project project);

}
