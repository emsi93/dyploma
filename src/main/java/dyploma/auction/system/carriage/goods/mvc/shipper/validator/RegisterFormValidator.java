package dyploma.auction.system.carriage.goods.mvc.shipper.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dyploma.auction.system.carriage.goods.modules.ValidationUtils;
import dyploma.auction.system.carriage.goods.mvc.shipper.dao.ShipperDAOInterface;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.RegisterModel;

@Component("registerFormValidator")
@Scope("singleton")
public class RegisterFormValidator implements Validator{

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
	
	private static final String COMPANY_NAME_TOO_LONG = "Nazwa firmy jest za d³uga.";
	private static final String POSTCODE_TOO_LONG = "Kod pocztowy jest za d³ugi.";
	private static final String NAME_CITY_TOO_LONG = "Nazwa miasta jest za d³uga.";
	private static final String NIP_TOO_LONG = "NIP jest za d³ugi.";
	private static final String PHONE_NUMBER_TOO_LONG = "Numer telefonu jest za d³ugi.";
	private static final String NIP_IS_USED = "Ten NIP jest ju¿ u¿yty.";
	private static final String ERROR_NIP = "NIP mo¿e zawieraæ jedynie cyfry.";
	private static final String ERROR_FLAT_NUMBER = "Numer lokalu mo¿e zawieraæ jedynie cyfry.";
	
	@Autowired
	private ShipperDAOInterface dao;
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return RegisterModel.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		RegisterModel registerModel = (RegisterModel) target;
		
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
				registerModel.getName(), 20, NAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "surname",
				registerModel.getSurname(), 20, SURNAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "login",
				registerModel.getLogin(), 10, LOGIN_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "password",
				registerModel.getPassword(), 20, PASSWORD_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "emailUser",
				registerModel.getEmailUser(), 50, EMAIL_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "phoneNumber",
				registerModel.getPhoneNumberUser(), 15, PHONE_NUMBER_TOO_LONG);
		
		if(registerModel.getPassword().length()<8 && registerModel.getPassword().length()>0)
			ValidationUtils.reject(errors, "password", PASSWORD_TOO_SHORT);
		if(!registerModel.getPassword().equals(registerModel.getPassword2()))
			ValidationUtils.reject(errors, "password2", DIFFERENT_PASSWORD);
		if (!registerModel.getPhoneNumberUser().matches("[0-9]+")
				&& registerModel.getPhoneNumberUser().length() > 0)
			ValidationUtils.reject(errors, "phoneNumberUser", ERROR_PHONE_NUMBER);
		
		int param1 = dao.checkUniqueEmailUser(registerModel.getEmailUser());
		int param2 = dao.checkUniqueLogin(registerModel.getLogin());
		
		if(param1==1)
			ValidationUtils.reject(errors, "emailUser", EMAIL_IS_USED);
		if(param2==1)
			ValidationUtils.reject(errors, "login", LOGIN_IS_USED);
		
		ValidationUtils.rejectIfEmpty(errors, "companyName",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "country",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "postcode",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "city",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "nipNumber",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "phoneNumber",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "email",
				OBLIGATORY_FIELD_ERROR_MSG);
		
		ValidationUtils.rejectIfTooLong(errors, "companyName",
				registerModel.getName(), 50, COMPANY_NAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "postcode",
				registerModel.getPostcode(), 10, POSTCODE_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "city",
				registerModel.getCity(), 30, NAME_CITY_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "nipNumber",
				registerModel.getPostcode(), 10, NIP_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "phoneNumber",
				registerModel.getPhoneNumber(), 15, PHONE_NUMBER_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "email",
				registerModel.getEmail(), 50, EMAIL_TOO_LONG);
		
		if (!registerModel.getPhoneNumber().matches("[0-9]+")
				&& registerModel.getPhoneNumberUser().length() > 0)
			ValidationUtils.reject(errors, "phoneNumberUser", ERROR_PHONE_NUMBER);
		if (!registerModel.getNipNumber().matches("[0-9]+")
				&& registerModel.getNipNumber().length() > 0)
			ValidationUtils.reject(errors, "nipNumber", ERROR_NIP);
		if (!registerModel.getFlatNumber().matches("[0-9]+")
				&& registerModel.getFlatNumber().length() > 0)
			ValidationUtils.reject(errors, "flatNumber", ERROR_FLAT_NUMBER);
		
		int param3 = dao.checkUniqueEmailCompany(registerModel.getEmail());
		int param4 = dao.checkUniqueNip(registerModel.getNipNumber());
		
		if(param3 == 1)
			ValidationUtils.reject(errors, "email", EMAIL_IS_USED);
		if(param4 == 1)
			ValidationUtils.reject(errors, "nipNumber", NIP_IS_USED);
	}

}
