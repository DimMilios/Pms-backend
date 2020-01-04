//package com.pms.controller.exceptions;
//
//import org.hibernate.exception.ConstraintViolationException;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.NoSuchElementException;
//
//@ControllerAdvice
//public class ExcHandler extends ResponseEntityExceptionHandler {
//
//    public ExcHandler() {
//        super();
//    }
//
//    @ExceptionHandler({ ConstraintViolationException.class })
//    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
//        final String bodyOfResponse = "This should be application specific";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler({ DataIntegrityViolationException.class })
//    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
//        logger.error(request);
//        final String bodyOfResponse = "This should be application specific";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler(value = { EntityNotFoundException.class, MyResourceNotFoundException.class, NoSuchElementException.class})
//    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
//        final String bodyOfResponse = "not found";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
//    }
//
//    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
//    /*500*/public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
//        logger.error("500 Status Code", ex);
//        final String bodyOfResponse = "500 error";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
//    }
//}
