package dyploma.auction.system.carriage.goods.mvc.webapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dyploma.auction.system.carriage.goods.modules.ValidationUtils;
import dyploma.auction.system.carriage.goods.mvc.webapp.dao.WebappDAOInterface;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.UserModel;
@Component("userFormValidator")
@Scope("singleton")
public class UserFormValidator implements Validator{

	private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";
	private static final String LOGIN_TOO_LONG = "Login jest za d³ugi.";
	private static final String LOGIN_IS_USED = "Login jest ju¿ u¿yty.";
	private static final String EMAIL_IS_USED = "Adres email jest ju¿ u¿yty.";
	private static final String EMAIL_TOO_LONG = "Adres email jest za d³ugi.";
	private static final String NAME_TOO_LONG = "Imiê jest za d³ugie.";
	private static final String SURNAME_TOO_LONG = "Nazwisko jest za d³ugie.";
	private static final String PASSWORD_TOO_LONG = "Has³o jest za d³ugie.";
	private static final String PASSWORD_TOO_SHORT = "Podane has³o jest za krótkie.";
	private static final String DIFFERENT_PASSWORD = "Has³a nie mog¹ siê ró¿niæ.";
	private static final String ERROR_PHONE_NUMBER = "Numer telefonu mo¿e zawieraæ jedynie cyfry.";
	private static final String PHONE_NUMBER_TOO_LONG = "Numer telefonu jest za d³ugi.";

	
	@Autowired
	private WebappDAOInterface dao;
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserModel.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		UserModel userModel = (UserModel) target;
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
		ValidationUtils.rejectIfEmpty(errors, "phoneNumberUser",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "emailUser",
				OBLIGATORY_FIELD_ERROR_MSG);
		
		ValidationUtils.rejectIfTooLong(errors, "name",
				userModel.getName(), 20, NAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "surname",
				userModel.getSurname(), 20, SURNAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "login",
				userModel.getLogin(), 10, LOGIN_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "password",
				userModel.getPassword(), 20, PASSWORD_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "emailUser",
				userModel.getEmailUser(), 50, EMAIL_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "phoneNumber",
				userModel.getPhoneNumberUser(), 15, PHONE_NUMBER_TOO_LONG);
		
		if(userModel.getPassword().length()<8 && userModel.getPassword().length()>0)
			ValidationUtils.reject(errors, "password", PASSWORD_TOO_SHORT);
		if(!userModel.getPassword().equals(userModel.getPassword2()))
			ValidationUtils.reject(errors, "password2", DIFFERENT_PASSWORD);
		if (!userModel.getPhoneNumberUser().matches("[0-9]+")
				&& userModel.getPhoneNumberUser().length() > 0)
			ValidationUtils.reject(errors, "phoneNumberUser", ERROR_PHONE_NUMBER);
		
		int param1 = dao.checkUniqueEmailUser(userModel.getEmailUser());
		int param2 = dao.checkUniqueLogin(userModel.getLogin());
		
		if(param1==1)
			ValidationUtils.reject(errors, "emailUser", EMAIL_IS_USED);
		if(param2==1)
			ValidationUtils.reject(errors, "login", LOGIN_IS_USED);
		
	}

}
