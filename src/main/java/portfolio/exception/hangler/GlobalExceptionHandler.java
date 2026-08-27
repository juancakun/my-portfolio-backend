package portfolio.exception.hangler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import portfolio.exception.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex, Model model){

        model.addAttribute("errors", ex.getBindingResult().getAllErrors());
        model.addAttribute("message", "Se encontraron errores de validación");

        return "error/validation";
    }
}
