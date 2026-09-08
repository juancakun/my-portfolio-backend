package portfolio.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import portfolio.exception.ValidationException;
import portfolio.model.Skill;
import portfolio.repository.ISkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements ISkillService{

    private final ISkillRepository skillRepository;
    private final Validator validator;

    @Override
    @Transactional(readOnly = true)
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    @Transactional
    public Skill save(Skill skill) {
        BindingResult bindingResult = new BeanPropertyBindingResult(skill, "skill");
        validator.validate(skill, bindingResult);
        if(bindingResult.hasErrors())
            throw new ValidationException(bindingResult);

        return skillRepository.save(skill);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skill> findSkillsByPersonalInfoId(Long personalInfoId) {
        return skillRepository.findByPersonalInfoId(personalInfoId);
    }
}