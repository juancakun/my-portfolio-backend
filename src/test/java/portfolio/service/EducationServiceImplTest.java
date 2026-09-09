package portfolio.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import portfolio.exception.ValidationException;
import portfolio.model.Education;
import portfolio.repository.IEducationsRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EducationServiceImplTest {

    @Mock
    private IEducationsRepository educationsRepository;

    @InjectMocks
    private EducationServiceImpl educationService;

    @Mock
    private Validator validator;

    @Test
    void testFindAllReturnsListOfEducations(){
        //Arrage
        List<Education> mockEducations = Arrays.asList(new Education(), new Education());
        when(educationsRepository.findAll()).thenReturn(mockEducations);

        //Act
        List<Education> educations = educationService.findAll();

        //Assert
        assertNotNull(educations);
        assertEquals(2, educations.size());
        verify(educationsRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsEducationWhenFound(){

        //Arrage
        Long id = 1L;
        Education educationMock = new Education();
        when(educationsRepository.findById(id)).thenReturn(Optional.of(educationMock));

        //Act
        Optional<Education> education = educationService.findById(id);

        //Assert
        assertTrue(education.isPresent());
        assertEquals(educationMock, education.get());
        verify(educationsRepository, times(1)).findById(id);

    }

    @Test
    void testSaveEducationThrowsExceptionWhenInvalid(){
        //Arrage
        Education invalidEducation = new Education();
        doAnswer(invocationMock-> {
            BindingResult bindingResult = invocationMock.getArgument(1);
            bindingResult.rejectValue("degree", "NotBlank", "El degree no puede estar vacío");
            return null;
        }).when(validator).validate(any(Education.class), any(BindingResult.class));

        //Act
        assertThrows(ValidationException.class, () -> educationService.save(invalidEducation));

        //Assert
        verify(educationsRepository, never()).save(any(Education.class));

    }

    @Test
    void testSaveSkillSavesValidSkill(){
        //Arrage
        Education validEducation = new Education(
                null,
                "Test",
                "Test",
                LocalDate.of(2020, 1, 1),
                null,
                null,
                null
        );
        when(educationsRepository.save(any(Education.class))).thenReturn(validEducation);
        //Act
        Education saveEducation = educationService.save(validEducation);

        assertNotNull(saveEducation);
        verify(educationsRepository, times(1)).save(validEducation);

    }

}
