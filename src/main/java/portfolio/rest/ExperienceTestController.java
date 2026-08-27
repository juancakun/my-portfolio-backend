package portfolio.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import portfolio.model.Experience;
import portfolio.service.IExperienceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test/experience")
@AllArgsConstructor
public class ExperienceTestController {

    private final IExperienceService experienceService;

    @GetMapping("/all")
    public List<Experience> findAllExperiences() {
        return experienceService.findAll();
    }

    @GetMapping("/{id}")
    public Experience findExperienceById(@PathVariable Long id) {
        Optional<Experience> experience = experienceService.findById(id);
        if (experience.isPresent()) {
            return experience.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Información experiencia no disponible en el ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience) {
        Experience savedExperience = experienceService.save(experience);
        return new ResponseEntity<>(savedExperience, HttpStatus.CREATED);
    }

}
