package portfolio.repository;

import portfolio.model.Project;
import portfolio.model.Skill;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository {

    Project save(Project project);
    List<Project> findAll();
    Optional<Project> findById(Long id);
    void deleteById(Long id);
    List<Project> findByPersonalInfoId(Long personalInfoId);

}
