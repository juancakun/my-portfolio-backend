package portfolio.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private Long id;
    @NotBlank(message = "El título del proyecto es obligatorio.")
    private String title;
    @NotBlank(message = "La descripción es obligatoria.")
    private String description;
    @NotBlank(message = "La Url es obligatoria.")
    private String imageUrl;
    @NotBlank(message = "La Url es obligatoria.")
    private String projectUrl;
    private Long personalInfoId;
}
