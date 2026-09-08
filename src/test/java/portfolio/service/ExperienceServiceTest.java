package portfolio.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import portfolio.exception.ValidationException;
import portfolio.model.Experience;
import portfolio.repository.IExperiencesRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ExperienceServiceTest {

    @Autowired
    private IExperienceService experienceService;
    @Autowired
    private IExperiencesRepository experiencesRepository;

    @Test
    void validSaveExperienceTest(){

        Experience validExperience = new Experience(
                null,"Tecnico","Knadian",
                LocalDate.of(2026,8,20),LocalDate.of(2026, 8, 26),
                "Información",1L);

        Experience savedExperience = experienceService.save(validExperience);

        assertNotNull(savedExperience.getId(), "El objeto debe de tener id");

        assertNotNull(experiencesRepository.findById(savedExperience.getId()).orElse(null), "El objeto debe de estar guardado en la base de datos");

    }

    @Test
    void invalidSaveExperienceTest(){
        Experience invalidExperience = new Experience(
                null,"","Knadian",
                LocalDate.of(2026,8,20),LocalDate.of(2026, 8, 26),
                "Información",1L
        );

        assertThrows(ValidationException.class, ()->experienceService.save(invalidExperience),"Debe de saltar una validación el no tener titulo del trabajo.");

    }

}
