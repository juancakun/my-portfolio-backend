package portfolio.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import portfolio.model.PersonalInfo;
import portfolio.service.IPersonalInfoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test/personal-info")
@RequiredArgsConstructor
public class PersonalInfoTestController {

    private final IPersonalInfoService personalInfoService;

    @GetMapping("/all")
    public List<PersonalInfo> findAll() {
        return personalInfoService.findAll();
    }

    @GetMapping("/{id}")
    public PersonalInfo getPersonalInfoById(@PathVariable Long id) {
        Optional<PersonalInfo> personalInfo = personalInfoService.findById(id);

        if (personalInfo.isPresent()) {
            return personalInfo.get();
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Información personal no disponible en el ID: " + id
        );
    }

    @PostMapping
    public ResponseEntity<PersonalInfo> createPersonalInfo(
            @RequestBody PersonalInfo personalInfo) {

        PersonalInfo newPersonalInfo = personalInfoService.save(personalInfo);

        return new ResponseEntity<>(newPersonalInfo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public PersonalInfo updatePersonalInfo(
            @PathVariable Long id,
            @RequestBody PersonalInfo personalInfo) {

        personalInfo.setId(id);
        return personalInfoService.save(personalInfo);
    }

    @DeleteMapping("/{id}")
    public void deletePersonalInfo(@PathVariable Long id) {
        personalInfoService.deleteById(id);
    }
}
