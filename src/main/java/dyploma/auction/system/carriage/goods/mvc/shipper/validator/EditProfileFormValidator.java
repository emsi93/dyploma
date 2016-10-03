package dyploma.auction.system.carriage.goods.mvc.shipper.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dyploma.auction.system.carriage.goods.modules.ValidationUtils;
import dyploma.auction.system.carriage.goods.mvc.shipper.dao.ShipperDAOInterface;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ProfileModel;
@Component("editProfileFormValidator")
@Scope("singleton")
public class EditProfileFormValidator implements Validator{

	private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";
	private static final String LOGIN_TOO_LONG = "Login jest za d³ugi.";
	private static final String EMAIL_TOO_LONG = "Adres email jest za d³ugi.";
	private static final String NAME_TOO_LONG = "Imiê jest za d³ugie.";
	private static final String SURNAME_TOO_LONG = "Nazwisko jest za d³ugie.";
	private static final String PASSWORD_TOO_LONG = "Has³o jest za d³ugie.";
	private static final String PASSWORD_TOO_SHORT = "Podane has³o jest za krótkie.";
	private static final String DIFFERENT_PASSWORD = "Has³a nie mog¹ siê ró¿niæ.";
	private static final String ERROR_PHONE_NUMBER = "Numer telefonu mo¿e zawieraæ jedynie cyfry.";
	private static final String PHONE_NUMBER_TOO_LONG = "Numer telefonu jest za d³ugi.";
	
	@Autowired
	private ShipperDAOInterface dao;
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ProfileModel.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ProfileModel profileModel = (ProfileModel) target;
		
		ValidationUtils.rejectIfEmpty(errors, "name",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "surname",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "login",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "password",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "password2",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "phoneNumber",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "email",
				OBLIGATORY_FIELD_ERROR_MSG);
		
		ValidationUtils.rejectIfTooLong(errors, "name",
				profileModel.getName(), 20, NAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "surname",
				profileModel.getSurname(), 20, SURNAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "login",
				profileModel.getLogin(), 10, LOGIN_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "password",
				profileModel.getPassword(), 20, PASSWORD_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "email",
				profileModel.getEmail(), 50, EMAIL_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "phoneNumber",
				profileModel.getPhoneNumber(), 15, PHONE_NUMBER_TOO_LONG);
		
		if(profileModel.getPassword().length()<8 && profileModel.getPassword().length()>0)
			ValidationUtils.reject(errors, "password", PASSWORD_TOO_SHORT);
		if(!profileModel.getPassword().equals(profileModel.getPassword2()))
			ValidationUtils.reject(errors, "password2", DIFFERENT_PASSWORD);
		if (!profileModel.getPhoneNumber().matches("[0-9]+")
				&& profileModel.getPhoneNumber().length() > 0)
			ValidationUtils.reject(errors, "phoneNumber", ERROR_PHONE_NUMBER);
		
	
		
	}

}
