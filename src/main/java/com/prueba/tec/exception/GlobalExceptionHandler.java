package com.prueba.tec.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private String handleHttpMessageNotReadableMsg = "Formato de JSON request invalido por favor revisar estructura";

	private String internalServerError = "Ha sucedido un error en el proceso del servicio";
	
	// se utiliza cuando el json request esta mal formado en su estructura	
	   @Override
	   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	       return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,ex.getLocalizedMessage(), handleHttpMessageNotReadableMsg));
	   }

	   private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
	       return new ResponseEntity<>(apiError, apiError.getStatus());
	   }
	   
	   // cuando da un error general 
	   @ExceptionHandler(RuntimeException.class)
	   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	   public ResponseEntity<Object> handleAllUncaughtException(RuntimeException exception, WebRequest request){
		   return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,exception.getLocalizedMessage(), internalServerError));
	   }

}
