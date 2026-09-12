package portfolio.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.model.Project;
import portfolio.repository.IProjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService implements IProjectService{

    private final IProjectRepository projectRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    @Transactional
    public Project save(Project project) {
        return projectRepository.save(project);
    }
}
