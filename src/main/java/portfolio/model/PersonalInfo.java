package portfolio.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio.")
    private String firstName;
    @NotBlank(message = "El apellido no puede estar vacío.")
    private String lastName;
    @NotBlank(message = "El título no puede estar vacío.")
    private String title;
    @NotBlank(message = "La descripción del perfíl no puede estar vacía.")
    private String profileDescription;
    @NotBlank(message = "La imagen no puede estar vacía")
    private String profileImageUrl;
    @Min(value=0, message = "Los años de experiencia no pueden ser negativos")
    private Integer yearsOfExperience;
    @Email(message = "El email no es válido")
    private String email;
    @Pattern(regexp = "^(\\+\\d{1,3})?\\d{7,15}$", message = "Formato de teléfono no válido")
    private String phone;
    @URL(message = "Por favor, introduce una dirección URL válida")
    private String linkedinUrl;
    @NotBlank(message = "El link a su linkedin es obligatorio")
    @URL(message = "Por favor, introduce una dirección URL válida")
    private String githubUrl;
}
