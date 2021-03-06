package eu.europeana.validation.rest.exceptions.exceptionmappers;

import eu.europeana.validation.model.ValidationResult;
import eu.europeana.validation.rest.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ymamakis on 2/24/16.
 */
@ControllerAdvice
public class ValidationExceptionController {

    /**
     * Handles specified exception
     *
     * @param e exception to be handled
     * @return the error information
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationResult handleException(ValidationException e) {
        ValidationResult error = new ValidationResult();
        error.setRecordId(e.getId());
        error.setMessage(e.getMessage());
        error.setNodeId(e.getNodeId());
        error.setSuccess(false);
        return error;
    }
}
