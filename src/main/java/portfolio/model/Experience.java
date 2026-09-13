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
    private String jobTitle;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private Long personalInfoId;

    public String getFormattedEndDate() {
        return (this.endDate != null) ? this.endDate.toString() : "Presente";
    }

}
