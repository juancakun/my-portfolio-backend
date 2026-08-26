package portfolio.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import portfolio.model.PersonalInfo;
import portfolio.repository.IPersonalInfoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test/personal-info")
public class PersonalInfoTestController {

    private final IPersonalInfoRepository personalInfoRepository;

    public PersonalInfoTestController(IPersonalInfoRepository iPersonalInfoRepository) {
        this.personalInfoRepository = iPersonalInfoRepository;
    }

    @GetMapping("/all")
    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    @GetMapping("/{id}")
    public PersonalInfo getPersonalInfoById(@PathVariable Long id) {
        Optional<PersonalInfo> personalInfo = personalInfoRepository.findById(id);
        if (personalInfo.isPresent()) {
            return personalInfo.get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Información personal no disponible en el ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<PersonalInfo> createPersonalInfo(@RequestBody PersonalInfo personalInfo) {
        PersonalInfo newPersonalInfo = personalInfoRepository.save(personalInfo);
        return new ResponseEntity<>(newPersonalInfo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public PersonalInfo updatePersonalInfo(@PathVariable Long id, @RequestBody PersonalInfo personalInfo) {
        personalInfo.setId(id);
        return personalInfoRepository.save(personalInfo);
    }

}
