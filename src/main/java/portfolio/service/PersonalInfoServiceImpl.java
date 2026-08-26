package portfolio.service;

import org.springframework.stereotype.Service;
import portfolio.model.PersonalInfo;
import portfolio.repository.IPersonalInfoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalInfoServiceImpl implements IPersonalInfoService{

    private final IPersonalInfoRepository personalInfoRepository;

    public PersonalInfoServiceImpl(IPersonalInfoRepository iPersonalInfoRepository) {
        this.personalInfoRepository = iPersonalInfoRepository;
    }

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {

        return personalInfoRepository.save(personalInfo);
    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        return personalInfoRepository.findById(id);
    }

    @Override
    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        personalInfoRepository.deleteById(id);
    }
}
