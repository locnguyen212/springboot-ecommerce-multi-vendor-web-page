package com.dodo.web.helpers;

import org.springframework.validation.BindingResult;

public class ValidateHelper {
	public static void printBindingResult(BindingResult bindingResult) {
		bindingResult.getFieldErrors()
			.stream()
			.forEach(f -> System.out.println(f.getField()+ '-' + f.getCode() + ": " + f.getDefaultMessage()));
	}
}
