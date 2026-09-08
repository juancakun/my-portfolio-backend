package portfolio.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import portfolio.exception.ValidationException;
import portfolio.model.PersonalInfo;
import portfolio.repository.IPersonalInfoRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersonalInfoServiceTest {

    @Autowired
    private IPersonalInfoService personalInfoService;
    @Autowired
    private IPersonalInfoRepository personalInfoRepository;

    @Test
    void validSavePersonalInfoTest(){
        PersonalInfo validPersonalInfo = new PersonalInfo(
                null,
                "Juan",
                "Hernandez",
                "Ingeniero en Sistemas",
                "Desarrollador de software especializado en Java y Spring Boot.",
                "https://example.com/profile.jpg",
                2,
                "juan@example.com",
                "+525512345678",
                "https://www.linkedin.com/in/juan",
                "https://github.com/juan");
        PersonalInfo savedPersonalInfo = personalInfoService.save(validPersonalInfo);

        assertNotNull(savedPersonalInfo.getId(), "El objeto debe de regresar un id");

        assertNotNull(personalInfoRepository.findById(savedPersonalInfo.getId()).orElse(null),"El objeto debe de regresar si fue guardado en la base de datos");

    }

    @Test
    void invalidSavePersonalInfoTest(){

        PersonalInfo invalidPersonalInfo = new PersonalInfo(
                null,
                "",
                "Hernandez",
                "Ingeniero en Sistemas",
                "Desarrollador de software especializado en Java y Spring Boot.",
                "https://example.com/profile.jpg",
                2,
                "juan@example.com",
                "+525512345678",
                "https://www.linkedin.com/in/juan",
                "https://github.com/juan");

        assertThrows(ValidationException.class, ()->personalInfoService.save(invalidPersonalInfo),"Debe de regresar una excepcion al no tener nombre");

    }

}
