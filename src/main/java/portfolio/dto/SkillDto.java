package portfolio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SkillDto {

    private Long id;
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String name;
    @NotNull(message = "El porcentaje no puede estar vacio")
    @Min(value = 0, message = "El porcentaje no puede ser menor a 0")
    @Max(value = 100, message = "El porcentaje no puede ser mayor a 100")
    private int levelPercentage;
    @NotBlank(message = "El icono debe de ser incluido")
    private String iconClass;
    @NotNull(message = "El ID de información personal es obligatorio.")
    private Long personalInfoId;

}
