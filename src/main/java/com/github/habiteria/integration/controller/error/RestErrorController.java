package com.github.habiteria.integration.controller.error;

import com.github.habiteria.dto.ApiErrorDto;
import com.github.habiteria.exceptions.client.actions.IllegalActionException;
import com.github.habiteria.exceptions.client.fields.IllegalFieldValueException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;

/**
 * @author Alex Ivchenko
 */
@ControllerAdvice
public class RestErrorController {
    private final MessageSource messages;

    public RestErrorController(MessageSource messages) {
        this.messages = messages;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorDto processValidationError(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalFieldValueException.class)
    public ApiErrorDto processIllegalFieldValueException(IllegalFieldValueException ex) {
        ApiErrorDto errors = new ApiErrorDto();
        errors.addFieldError(ex.getField(), resolveErrorMessage(ex.getCode().text(), ex.getArgs()));
        return errors;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalActionException.class)
    public ApiErrorDto processIllegalActionException(IllegalActionException ex) {
        ApiErrorDto errors = new ApiErrorDto();
        errors.addResourceError(ex.getResource(), resolveErrorMessage(ex.getCode().text(), ex.getArgs()));
        return errors;
    }

    private ApiErrorDto processFieldErrors(List<FieldError> fieldErrors) {
        ApiErrorDto errors = new ApiErrorDto();

        for (FieldError error : fieldErrors) {
            String field = error.getField();
            String message = resolveErrorMessage(error);
            errors.addFieldError(field, message);
        }

        return errors;
    }

    private String resolveErrorMessage(String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messages.getMessage(key, args, locale);
    }

    private String resolveErrorMessage(FieldError error) {
        Locale locale = LocaleContextHolder.getLocale();
        return messages.getMessage(error, locale);
    }
}
