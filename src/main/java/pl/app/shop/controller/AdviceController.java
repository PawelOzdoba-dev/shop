package pl.app.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.app.shop.domain.dto.FieldErrorDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice//kontroler do przechwytywania błędów z aplikacji
@Slf4j//poziomy: info, error, debug, trace, warn
public class AdviceController {

    @ExceptionHandler(EntityNotFoundException.class)//przechwytuje bład dla danego typu
//ta adnotacja przechwytuje exception, można to zrobić w zwykłym kontrolerze i działa to w obrębie tego kontrolera.
    @ResponseStatus(HttpStatus.NOT_FOUND)//zamiast 200 zwrócimy 404
    public void handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("", e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<FieldErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        log.error("", e);
//        List<ObjectError> allErrors = e.getBindingResult().getAllErrors(); //wszystkie błędy
// dla wszystkich błędów mapujemy nazwe zmiennej i pole i wypisujemy w api
        return e.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    FieldError fieldError = (FieldError) error;
                    return new FieldErrorDto(fieldError.getField(), fieldError.getDefaultMessage());
                })
                .collect(Collectors.toList());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("", e);
//ErrorDto dla błędu aby zwróbić w body.

    }
}
