package portfolio.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import portfolio.exception.ValidationException;
import portfolio.model.PersonalInfo;
import portfolio.repository.IPersonalInfoRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonalInfoServiceImplTest {

    @Mock
    private IPersonalInfoRepository personalInfoRepository;

    @InjectMocks
    private PersonalInfoServiceImpl personalInfoService;

    @Mock
    private Validator validator;

    @Test
    void testFindAllReturnsListOfPersonalInfo(){
        //Arrage
        List<PersonalInfo> personalInfoListMock = Arrays.asList(new PersonalInfo(), new PersonalInfo());
        when(personalInfoRepository.findAll()).thenReturn(personalInfoListMock);

        //Act
        List<PersonalInfo> personalInfos = personalInfoService.findAll();

        //Assert
        assertNotNull(personalInfos);
        assertEquals(2, personalInfos.size());
        verify(personalInfoRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsPersonalInfoWhenFound(){
        //Arrage
        Long id = 1L;
        PersonalInfo mockPersonalInfo =  new PersonalInfo();
        when(personalInfoRepository.findById(id)).thenReturn(Optional.of(mockPersonalInfo));

        //Act
        Optional<PersonalInfo> personalInfo = personalInfoService.findById(id);

        //Assert
        assertTrue(personalInfo.isPresent());
        assertEquals(mockPersonalInfo, personalInfo.get());
        verify(personalInfoRepository, times(1)).findById(id);
    }

    @Test
    void testSavePersonalInfoThrowsExceptionWhenInvalid(){

        //Arrage
        PersonalInfo personalInfo = new PersonalInfo();
        doAnswer(invocationMock ->{
            BindingResult result = invocationMock.getArgument(1);
            result.rejectValue("firstName", "NotBlank", "El nombre no puede estar vacio");
            return null;
        }).when(validator).validate(any(PersonalInfo.class), any(BindingResult.class));

        //Act
        assertThrows(ValidationException.class, ()-> personalInfoService.save(personalInfo));

        //Assert
        verify(personalInfoRepository, never()).save(any(PersonalInfo.class));

    }

    @Test
    void testSaveExperienceSavesValidPersonalInfo(){
        //Arrage
        PersonalInfo validPersonalInfo = new PersonalInfo(
                null,
                "Juan",
                "Hernández",
                "Ingeniero en Sistemas Computacionales",
                "Desarrollador de software con experiencia en Java, Spring Boot y desarrollo de aplicaciones móviles.",
                "https://example.com/profile.jpg",
                1,
                "juan@example.com",
                "+525512345678",
                "https://www.linkedin.com/in/juan",
                "https://github.com/juan"
        );
        when(personalInfoRepository.save(any(PersonalInfo.class))).thenReturn(validPersonalInfo);

        PersonalInfo personalInfo = personalInfoService.save(validPersonalInfo);

        assertNotNull(personalInfo);
        verify(personalInfoRepository, times(1)).save(validPersonalInfo);
    }


}
