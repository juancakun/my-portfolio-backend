package portfolio.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import portfolio.exception.ValidationException;
import portfolio.model.Experience;
import portfolio.repository.IExperiencesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements  IExperienceService{

    private final IExperiencesRepository experienceRepository;
    private final Validator validator;

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Experience> findById(Long id) {
        return experienceRepository.findById(id);
    }

    @Override
    @Transactional
    public Experience save(Experience experience) {

        BindingResult bindingResult = new BeanPropertyBindingResult(experience, "experience");

        validator.validate(experience, bindingResult);
        if(bindingResult.hasErrors())
            throw new ValidationException(bindingResult);

        return experienceRepository.save(experience);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        System.out.println("Eliminando experiencia por ID: " + id + " en el servicio...");
        experienceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findExperienceByPersonalInfoId(Long personalInfoId) {
        return experienceRepository.findByPersonalInfoId(personalInfoId);
    }
}