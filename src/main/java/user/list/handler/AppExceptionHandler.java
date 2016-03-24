package user.list.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleError404(NoHandlerFoundException e) {
        if (e.getRequestURL().endsWith("/403")) {
            return "403";
        }
        return "404";
    }

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(Exception e) {
        return "500";
    }

}
