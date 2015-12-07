package com.metaconnect.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.metaconnect.group.model.Group;

@Component
public class GroupValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Group.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "groupName",
				"group.groupName", "Name is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "groupDescription",
				"group.groupDescription", "groupDescription is required!");
	
	}

}
