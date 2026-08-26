package portfolio.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import portfolio.model.Education;
import portfolio.repository.IEducationsRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test/education")
@AllArgsConstructor
public class EducationTestController {

    private final IEducationsRepository educationsRepository;

    @GetMapping("/all")
    public List<Education> findAllEducations() {
        return educationsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Education findEducationById(@PathVariable Long id) {
        Optional<Education> education = educationsRepository.findById(id);
        if (education.isPresent()) {
            return education.get();
        }else  {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education not found in that Id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Education> createEducation(@RequestBody Education education) {
        Education saveEducation = educationsRepository.save(education);
        return new ResponseEntity<>(saveEducation, HttpStatus.CREATED);
    }

}
