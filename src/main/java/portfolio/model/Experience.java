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
public class Experience {
    private Long id;
    @NotBlank(message = "El Nombre del trabajo es obligatorio.")
    private String jobTitle;
    @NotBlank(message = "El nombre de la compañia es obligatorio.")
    private String companyName;
    @NotNull(message = "La fecha de inicio es obligatoria.")
    @PastOrPresent(message = "La fecha de inicio no puede ser futura.")
    private LocalDate startDate;
    @PastOrPresent(message = "La fecha de termino no puede ser futura.")
    private LocalDate endDate;
    @NotBlank(message = "La descripción es obligatoria.")
    private String description;
    private Long personalInfoId;

    public String getFormattedEndDate() {
        return (this.endDate != null) ? this.endDate.toString() : "Presente";
    }

}
