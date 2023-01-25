package uz.forLearn.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecourseNotFoundException extends RuntimeException {
    public RecourseNotFoundException(String message) {
        super(message);
    }
}
