package portfolio.service;

import portfolio.model.Skill;

import java.util.List;
import java.util.Optional;

public interface ISkillService {

    List<Skill> findAll();
    Optional<Skill> findById(Long id);
    Skill save(Skill skill);
    void deleteById(Long id);
    List<Skill> findSkillsByPersonalInfoId(Long personalInfoId);
}