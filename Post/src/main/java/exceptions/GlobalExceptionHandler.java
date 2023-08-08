package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public ModelAndView postNotFoundExceptionHandler(PostNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("errorview");
        modelAndView.addObject("errorMessage", e.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_ACCEPTABLE);
        return modelAndView;
    }
}