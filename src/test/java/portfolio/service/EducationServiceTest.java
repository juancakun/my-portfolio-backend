package portfolio.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import portfolio.exception.ValidationException;
import portfolio.model.Education;
import portfolio.repository.IEducationsRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EducationServiceTest {

    @Autowired
    private IEducationService educationService;
    @Autowired
    private IEducationsRepository educationsRepository;

    @Test
    void validSaveEducation(){

        Education validEducation = new Education(null, "Ingeniero en Sistemas", "Instituto Politecnico Nacional", LocalDate.of(2020, 8, 27), LocalDate.of(2026, 8, 27), "Estudiante", 1L);
        Education savedEducation = educationService.save(validEducation);

        assertNotNull(savedEducation.getId(), "El objeto guardado debe de tener un id asignado");

        assertNotNull(educationsRepository.findById(savedEducation.getId()).orElse(null), "El objeto debe de estar guardado en la base de datos");

    }

    @Test
    void invalidSaveEducation(){

        Education invalidEducation = new Education(null, "", "Instituto Politecnico Nacional", LocalDate.of(2020, 8, 27), LocalDate.of(2026, 8, 27), "Estudiante", 1L);

        assertThrows(ValidationException.class, ()-> educationService.save(invalidEducation) ,"Debe lanzarse una ValidationException cuando el nombre este vació.");

    }

}
