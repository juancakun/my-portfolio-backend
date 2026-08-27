package portfolio.service;

import portfolio.model.Experience;

import java.util.List;
import java.util.Optional;

public interface IExperienceService {
    List<Experience> findAll();
    Optional<Experience> findById(Long id);
    Experience save(Experience experience);
    void deleteById(Long id);
    List<Experience> findExperienceByPersonalInfoId(Long personalInfoId);
}