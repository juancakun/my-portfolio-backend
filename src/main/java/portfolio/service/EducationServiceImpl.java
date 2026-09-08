package portfolio.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import portfolio.exception.ValidationException;
import portfolio.model.Education;
import portfolio.repository.IEducationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements IEducationService{

    private final IEducationsRepository educationRepository;
    private final Validator validator;

    @Override
    @Transactional(readOnly = true)
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    @Transactional
    public Education save(Education education) {

        BindingResult bindingResult = new BeanPropertyBindingResult(education, "education");
        validator.validate(education, bindingResult);
        if(bindingResult.hasErrors())
            throw new ValidationException(bindingResult);

        return educationRepository.save(education);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        System.out.println("Eliminando educación por ID: " + id + " en el servicio...");
        educationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findEducationByPersonalInfoId(Long personalInfoId) {
        return educationRepository.findByPersonalInfoId(personalInfoId);
    }
}