package portfolio.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import portfolio.exception.ValidationException;
import portfolio.model.Experience;
import portfolio.repository.IExperiencesRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExperienceServiceImplTest {

    @Mock
    private IExperiencesRepository experiencesRepository;

    @InjectMocks
    private ExperienceServiceImpl experienceService;

    @Mock
    private Validator validator;

    @Test
    void testFindAllReturnsListOfExperience(){
        //Arrage
        List<Experience> mockExperience = Arrays.asList(new Experience(), new Experience());
        when(experiencesRepository.findAll()).thenReturn(mockExperience);

        //Act
        List<Experience> experiences = experienceService.findAll();

        //Assert
        assertNotNull(experiences);
        assertEquals(2, experiences.size());
        verify(experiencesRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsExperienceWhenFound(){
        //Arrage
        Long id = 1L;
        Experience experienceMock = new Experience();
        when(experiencesRepository.findById(id)).thenReturn(Optional.of(experienceMock));

        //Act
        Optional<Experience> experience = experienceService.findById(id);

        assertTrue(experience.isPresent());
        assertEquals(experienceMock, experience.get());
        verify(experiencesRepository, times(1)).findById(id);
    }

    @Test
    void testSaveExperienceThrowsExceptionWhenInvalid(){
        //Arrage
        Experience invalidExperience = new Experience();
        doAnswer(invocationMock ->{
            BindingResult result = invocationMock.getArgument(1);
            result.rejectValue("jobTitle", "NotBlank", "El jobTitle no puede estar vacío");
            return null;
        }).when(validator).validate(any(Experience.class), any(BindingResult.class));

        //Act
        assertThrows(ValidationException.class, ()-> experienceService.save(invalidExperience));

        //Assert
        verify(experiencesRepository, never()).save(any(Experience.class));
    }

    @Test
    void testSaveExperienceSavesValidExperience(){
        //Arrage
        Experience validExperience = new Experience(
                null,
                "Test",
                "Test",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2022, 1, 1),
                "Test",
                null
        );
        when(experiencesRepository.save(any(Experience.class))).thenReturn(validExperience);

        Experience experience = experienceService.save(validExperience);

        assertNotNull(experience);
        verify(experiencesRepository, times(1)).save(validExperience);
    }

}
