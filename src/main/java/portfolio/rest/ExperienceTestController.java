package portfolio.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import portfolio.model.Experience;
import portfolio.repository.IExperiencesRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test/experience")
@AllArgsConstructor
public class ExperienceTestController {

    private final IExperiencesRepository experiencesRepository;

    @GetMapping("/all")
    public List<Experience> findAllExperiences() {
        return experiencesRepository.findAll();
    }

    @GetMapping("/{id}")
    public Experience findExperienceById(@PathVariable Long id) {
        Optional<Experience> experience = experiencesRepository.findById(id);
        if (experience.isPresent()) {
            return experience.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Información experiencia no disponible en el ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience) {
        Experience savedExperience = experiencesRepository.save(experience);
        return new ResponseEntity<>(savedExperience, HttpStatus.CREATED);
    }

}
