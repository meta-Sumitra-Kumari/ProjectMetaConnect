package com.metaconnect.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.metaconnect.user.model.User;

@Component
public class UserValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
				"user.firstName", "Please Enter Correct First Name !");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
				"user.lastName", "Please Enter correct Last Name.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address",
				"user.address", "Please Enter correct Address.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender",
				"user.gender", "Please Select Gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber",
				"user.phoneNumber", "Please Enter valid phoneNumber.");
	}
}
