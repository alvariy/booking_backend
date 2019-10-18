package com.tw.apistackbase.handler;

import com.tw.apistackbase.error.CustomError;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public CustomError handleNotFoundException(NotFoundException e) {
        CustomError customError = new CustomError();
        customError.setCode(404);
        customError.setMessage(e.getMessage());
        return customError;
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public CustomError handleBadRequestException(NotFoundException e) {
        CustomError customError = new CustomError();
        customError.setCode(400);
        customError.setMessage(e.getMessage());
        return customError;
    }

}
