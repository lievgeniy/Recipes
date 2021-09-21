package recipes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecipeExceptionHandler {
    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<RecipeError> handleException(RecipeNotFoundException e) {
        RecipeError error = new RecipeError(e.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadParametersException.class)
    public ResponseEntity<RecipeError> handleBadParametersException(BadParametersException e) {
        RecipeError error = new RecipeError(e.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Error> handleBadParametersException(ItemNotFoundException e) {
        Error error = new Error(e.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Error> handleUnauthorizedException(UnauthorizedException e) {
        Error error = new Error(e.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
