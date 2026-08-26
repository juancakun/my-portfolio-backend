package portfolio.repository;

import portfolio.model.Experience;

import java.util.List;
import java.util.Optional;

public interface IExperiencesRepository {
    Experience save(Experience experience);

    Optional<Experience> findById(Long id);

    List<Experience> findAll();

    void deleteById(Long id);

    List<Experience> findByPersonalInfoId(Long personalInfoId);
}