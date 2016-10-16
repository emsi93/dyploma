package dyploma.auction.system.carriage.goods.mvc.shipper.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dyploma.auction.system.carriage.goods.modules.ValidationUtils;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.DetailsEmployeeModel;

@Component("editEmployeeFormValidator")
@Scope("singleton")
public class EditEmployeeFormValidator implements Validator{

	private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";
	private static final String LOGIN_TOO_LONG = "Login jest za d³ugi.";
	private static final String EMAIL_TOO_LONG = "Adres email jest za d³ugi.";
	private static final String NAME_TOO_LONG = "Imiê jest za d³ugie.";
	private static final String SURNAME_TOO_LONG = "Nazwisko jest za d³ugie.";
	private static final String ERROR_PHONE_NUMBER = "Numer telefonu mo¿e zawieraæ jedynie cyfry.";
	private static final String PHONE_NUMBER_TOO_LONG = "Numer telefonu jest za d³ugi.";
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return DetailsEmployeeModel.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		DetailsEmployeeModel detailsEmployeeModel = (DetailsEmployeeModel) target;
		
		ValidationUtils.rejectIfEmpty(errors, "name",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "surname",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "login",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "activity",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "role",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "phoneNumber",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "email",
				OBLIGATORY_FIELD_ERROR_MSG);
		
		ValidationUtils.rejectIfTooLong(errors, "name",
				detailsEmployeeModel.getName(), 20, NAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "surname",
				detailsEmployeeModel.getSurname(), 20, SURNAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "login",
				detailsEmployeeModel.getLogin(), 10, LOGIN_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "email",
				detailsEmployeeModel.getEmail(), 50, EMAIL_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "phoneNumber",
				detailsEmployeeModel.getPhoneNumber(), 15, PHONE_NUMBER_TOO_LONG);
		
		if (!detailsEmployeeModel.getPhoneNumber().matches("[0-9]+")
				&& detailsEmployeeModel.getPhoneNumber().length() > 0)
			ValidationUtils.reject(errors, "phoneNumber", ERROR_PHONE_NUMBER);
	}

}
