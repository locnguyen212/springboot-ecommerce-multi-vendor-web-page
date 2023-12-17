package com.dodo.api.helpers;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ValidateHelper {
	public static void printBindingResult(BindingResult bindingResult) {
		bindingResult.getFieldErrors()
			.stream()
			.forEach(f -> System.out.println(f.getField()+ '-' + f.getCode() + ": " + f.getDefaultMessage()));
	}
	
	public static Map<String, String> getErrors(BindingResult bindingResult){
		return bindingResult.getFieldErrors()
	            .stream()
	            .collect(Collectors.toMap(
	            		k -> k.getField(), 
	            		v -> v.getDefaultMessage(),
	            		(v1, v2) -> v1+", "+v2
	            		)
		);
	}
	
	public static Map<String, Object> getErrorResponseBody(BindingResult bindingResult) {
		Map<String, Object> responseBody = new LinkedHashMap<>();
		printBindingResult(bindingResult);
//		List<String> errors = ex.getBindingResult().getFieldErrors()
//	            .stream()
//	            .map(x -> x.getField()+" - "+x.getDefaultMessage())
//	            .collect(Collectors.toList());
		
		Map<String, String> errors = bindingResult.getFieldErrors()
	            .stream()
	            .collect(Collectors.toMap(
	            		k -> k.getField(), 
	            		v -> v.getDefaultMessage(),
	            		(v1, v2) -> v1+", "+v2
	            		)
		);

		responseBody.put("errors", errors);

		return responseBody;
	}
}
