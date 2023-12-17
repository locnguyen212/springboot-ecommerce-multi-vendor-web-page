package com.dodo.api.helpers;

import java.util.Map;
import java.util.stream.Collectors;

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
}
