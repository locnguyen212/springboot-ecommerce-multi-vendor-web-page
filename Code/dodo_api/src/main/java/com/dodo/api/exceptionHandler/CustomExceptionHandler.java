package com.dodo.api.exceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		System.out.println("status code: "+ status.value());
		Map<String, Object> responseBody = new LinkedHashMap<>();
		responseBody.put("timestamp", new Date());
		responseBody.put("status", status.value());
		
//		List<String> errors = ex.getBindingResult().getFieldErrors()
//	            .stream()
//	            .map(x -> x.getField()+" - "+x.getDefaultMessage())
//	            .collect(Collectors.toList());
		
		Map<String, String> errors = ex.getBindingResult().getFieldErrors()
	            .stream()
	            .collect(Collectors.toMap(
	            		k -> k.getField(), 
	            		v -> v.getDefaultMessage(),
	            		(v1, v2) -> v1+", "+v2
	            		)
		);

		responseBody.put("errors", errors);

		return new ResponseEntity<>(responseBody, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, Object> responseBody = new LinkedHashMap<>();
		responseBody.put("timestamp", new Date());
		responseBody.put("status", status.value());
		
		String error = customHttpMessageNotReadableClass(ex.getMostSpecificCause().getClass().getName()) +" - "+customHttpMessageNotReadableMessage(ex.getMostSpecificCause().getMessage());
		responseBody.put("error", error);
		return new ResponseEntity<>(responseBody, headers, status);
	}
	
	
	private String customHttpMessageNotReadableMessage(String message) {
		if(message.contains("java.util.Date")) {
			var removeIndex = message.indexOf("[");
			return message.substring(0, removeIndex-4);
		}
		var removeIndex = message.indexOf("at");
		return message.substring(0, removeIndex);
	}
	
	private String customHttpMessageNotReadableClass(String message) {
		var removeIndex = message.lastIndexOf(".");
		return message.substring(removeIndex+1);
	}
}