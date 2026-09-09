package portfolio.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import portfolio.exception.ValidationException;
import portfolio.model.Skill;
import portfolio.repository.ISkillRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SkillServiceImplTest {

    @Mock
    private ISkillRepository skillRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private SkillServiceImpl skillService;

    @Test
    void testFindAllReturnsListOfSkills(){
        //Arrange
        List<Skill> mockSkills = Arrays.asList(new Skill(), new Skill());
        when(skillRepository.findAll()).thenReturn(mockSkills);

        //Act
        List<Skill> skills = skillService.findAll();

        //Assert
        assertNotNull(skills);
        assertEquals(2, skills.size());
        verify(skillRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsSkillWhenFound(){
        Long id = 1L;
        Skill skillMock = new Skill();
        when(skillRepository.findById(id)).thenReturn(Optional.of(skillMock));

        Optional<Skill> skillOptional = skillService.findById(id);

        assertTrue(skillOptional.isPresent());
        assertEquals(skillMock, skillOptional.get());
        verify(skillRepository, times(1)).findById(id);
    }

    @Test
    void testSaveSkillThrowsExceptionWhenInvalid(){

        Skill invalidSkill = new Skill();
        doAnswer( invocationOnMock -> {
            BindingResult result = invocationOnMock.getArgument(1);
            result.rejectValue("name", "NotBlank", "El nombre no puede estar vacío");
            return null;
        }).when(validator).validate(any(Skill.class), any(BindingResult.class));

        assertThrows(ValidationException.class, () -> skillService.save(invalidSkill),
                "Debe lanzarse una validacionException si el objeto no es válido");

        verify(skillRepository, never()).save(any(Skill.class));

    }

    @Test
    void testSaveSkillSavesValidSkill(){
        //Arrage
        Skill validSkill = new Skill(null, "Java", 90, "fab fa-java", 1L);
        when(skillRepository.save(any(Skill.class))).thenReturn(validSkill);
        doNothing().when(validator).validate(any(Skill.class), any(BindingResult.class));
        //Act
        Skill saveSkill = skillService.save(validSkill);
        //Assert
        assertNotNull(saveSkill);
        verify(skillRepository, times(1)).save(validSkill);
    }

}
