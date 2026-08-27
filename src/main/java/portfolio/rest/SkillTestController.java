package portfolio.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import portfolio.model.Skill;
import portfolio.service.ISkillService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test/skill")
@AllArgsConstructor
public class SkillTestController {

    private final ISkillService skillService;

    @GetMapping("/all")
    public List<Skill> allSkills() {
        return skillService.findAll();
    }

    @GetMapping("/{id}")
    public Skill findById(@PathVariable Long id) {
        Optional<Skill> skill = skillService.findById(id);
        if (skill.isPresent()) {
            return skill.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Información de skill no disponible en el ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        Skill savedSkill = skillService.save(skill);
        return new ResponseEntity<>(savedSkill, HttpStatus.CREATED);
    }
}
