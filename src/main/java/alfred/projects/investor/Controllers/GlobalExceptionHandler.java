package alfred.projects.investor.Controllers;

import alfred.projects.investor.Persistance.IncorrectPasswordException;
import alfred.projects.investor.Persistance.UserAlreadyExists;
import alfred.projects.investor.Persistance.UserDoesntExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<String> handleIncorrectPassword(IncorrectPasswordException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(UserDoesntExist.class)
    public ResponseEntity<String> handleUserDoesntExist(UserDoesntExist ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExists ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }
}
