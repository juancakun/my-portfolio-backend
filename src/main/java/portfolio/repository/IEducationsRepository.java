package portfolio.repository;

import portfolio.model.Education;

import java.util.List;
import java.util.Optional;

public interface IEducationsRepository {
    Education save(Education personalInfo);

    Optional<Education> findById(Long id);

    List<Education> findAll();

    void deleteById(Long id);

    List<Education> findByPersonalInfoId(Long personalInfoId);
}
