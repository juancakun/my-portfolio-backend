package portfolio.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {

    private Long id;
    @NotBlank(message = "El título no puede estar vacío.")
    private String degree;
    @NotBlank(message = "El nombre de la institución no puede estar vacío.")
    private String institution;
    @NotNull(message = "La fecha de inicio es obligatoria.")
    @PastOrPresent(message = "La fecha de inicio no puede ser futura.")
    private LocalDate startDate;
    @PastOrPresent(message = "La fecha de termino no puede ser futura.")
    private LocalDate endDate;
    @NotBlank(message = "La descripción es obligatoria.")
    private String description;
    @NotNull(message = "El ID de información personal es obligatorio.")
    private Long personalInfoId;

}
