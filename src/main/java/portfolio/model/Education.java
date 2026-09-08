package portfolio.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    private Long id;
    @NotBlank(message = "El título no puede estar vacío.")
    private String degree;
    @NotBlank(message = "El nombre de la institución no puede estar vacío.")
    private String institution;
    @NotNull(message = "La fecha de inicio es obligatoria.")
    @PastOrPresent(message = "La fecha de inicio no puede ser futura.")
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private Long personalInfoId;
}
