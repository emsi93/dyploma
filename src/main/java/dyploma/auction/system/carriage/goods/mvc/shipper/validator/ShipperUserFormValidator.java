package dyploma.auction.system.carriage.goods.mvc.shipper.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dyploma.auction.system.carriage.goods.modules.ValidationUtils;
import dyploma.auction.system.carriage.goods.mvc.shipper.dao.ShipperDAOInterface;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ShipperUserFormModel;

@Component("userFormValidator")
@Scope("singleton")
public class ShipperUserFormValidator implements Validator {
	private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";
	private static final String FIELD_ERROR_MSG = "Pole zawiera inne znaki ni¿ cyfry.";
	private static final String LOGIN_IS_USED = "Ten login jest ju¿ u¿yty";
	private static final String PESEL_IS_USED = "Ten PESEL jest ju¿ u¿yty";
	private static final String EMAIL_IS_USED = "Ten adres email jest ju¿ u¿yty";
	private static final String PHONE_NUMBER_IS_USED = "Ten numer telefonu jest ju¿ u¿yty";
	private static final String POSTCODE_TOO_LONG = "Kod pocztowy jest za d³ugi.";
	private static final String CITY_NAME_TOO_LONG = "Nazwa miejscowoœci jest za d³uga.";
	private static final String STREET_NAME_TO_LONG = "Nazwa ulicy jest za d³uga.";
	private static final String PESEL_NUMBER_TO_LONG = "PESEL jest za d³ugi.";
	private static final String EMAIL_TO_LONG = "Adres email jest za d³ugi.";
	private static final String NAME_TOO_LONG = "Imiê jest za d³ugie.";
	private static final String SURNAME_TOO_LONG = "Nazwisko jest za d³ugie.";
	private static final String LOGIN_TOO_LONG = "Login jest za d³ugi.";
	private static final String PASSWORD_TOO_LONG = "Has³o jest za d³ugie.";
	private static final String PHONE_NUMBER_TO_LONG = "Numer telefonu jest za d³ugi.";
	private static final String LOGIN_TOO_SHORT = "Podany login jest za krótki.";
	private static final String PASSWORD_TOO_SHORT = "Podane has³o jest za krótkie.";
	private static final String PHONE_NUMBER_TOO_SHORT = "Podany numer telefonu jest za krótki.";
	private static final String PESEL_NUMBER_TOO_SHORT = "Podany pesel jest za krótki.";

	@Autowired
	private ShipperDAOInterface dao;

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ShipperUserFormModel.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {

		ShipperUserFormModel shipperUserFormModel = (ShipperUserFormModel) target;

		ValidationUtils.rejectIfEmpty(errors, "name",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "surname",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "login",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "password",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "postcode",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "city",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "street",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "houseNumber",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "peselNumber",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "phoneNumber",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "email",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfTooLong(errors, "name",
				shipperUserFormModel.getName(), 20, NAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "surname",
				shipperUserFormModel.getSurname(), 30, SURNAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "login",
				shipperUserFormModel.getLogin(), 10, LOGIN_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "password",
				shipperUserFormModel.getPassword(), 30, PASSWORD_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "postcode",
				shipperUserFormModel.getPostcode(), 10, POSTCODE_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "city",
				shipperUserFormModel.getCity(), 30, CITY_NAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "street",
				shipperUserFormModel.getStreet(), 60, STREET_NAME_TO_LONG);
		ValidationUtils
				.rejectIfTooLong(errors, "peselNumber",
						shipperUserFormModel.getPeselNumber(), 11,
						PESEL_NUMBER_TO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "email",
				shipperUserFormModel.getEmail(), 100, EMAIL_TO_LONG);
		ValidationUtils
				.rejectIfTooLong(errors, "phoneNumber",
						shipperUserFormModel.getPhoneNumber(), 15,
						PHONE_NUMBER_TO_LONG);
		
		if(shipperUserFormModel.getLogin().length()<6 && shipperUserFormModel.getLogin().length()>0)
			ValidationUtils.reject(errors, "login", LOGIN_TOO_SHORT);
		if(shipperUserFormModel.getPassword().length()<8 && shipperUserFormModel.getPassword().length()>0)
			ValidationUtils.reject(errors, "password", PASSWORD_TOO_SHORT);
		if(shipperUserFormModel.getPhoneNumber().length()<9 && shipperUserFormModel.getPhoneNumber().length()>0)
			ValidationUtils.reject(errors, "phoneNumber", PHONE_NUMBER_TOO_SHORT);
		if(shipperUserFormModel.getPeselNumber().length()<10 && shipperUserFormModel.getPeselNumber().length()>0)
			ValidationUtils.reject(errors, "peselNumber", PESEL_NUMBER_TOO_SHORT);
		if (shipperUserFormModel.getHouseNumber().matches("[0-9]")
				&& shipperUserFormModel.getHouseNumber().length() > 0)
			ValidationUtils.reject(errors, "houseNumber", FIELD_ERROR_MSG);
		if (shipperUserFormModel.getPhoneNumber().matches("[0-9]")
				&& shipperUserFormModel.getPhoneNumber().length() > 0)
			ValidationUtils.reject(errors, "phoneNumber", FIELD_ERROR_MSG);
		if (shipperUserFormModel.getFlatNumber().matches("[0-9]")
				&& shipperUserFormModel.getFlatNumber().length() > 0)
			ValidationUtils.reject(errors, "flatNumber", FIELD_ERROR_MSG);
		if (shipperUserFormModel.getCountry().equals(""))
			ValidationUtils.reject(errors, "country",
					OBLIGATORY_FIELD_ERROR_MSG);
		if (shipperUserFormModel.getPeselNumber().length() > 0) {
			if (shipperUserFormModel.getPeselNumber().length() == 11) {
				if (shipperUserFormModel.getPeselNumber().matches("[0-9]"))
					ValidationUtils.reject(errors, "peselNumber",
							FIELD_ERROR_MSG);
			}
		}
		int param1 = dao.checkUserLoginUniqueValues(shipperUserFormModel
				.getLogin());
		if (param1 != 0)
			ValidationUtils.reject(errors, "login", LOGIN_IS_USED);
		int param2 = dao.checkUserPeselUniqueValues(shipperUserFormModel
				.getPeselNumber());
		if (param2 != 0)
			ValidationUtils.reject(errors, "peselNumber", PESEL_IS_USED);
		int param3 = dao.checkUserEmailUniqueValues(shipperUserFormModel
				.getEmail());
		if (param3 != 0)
			ValidationUtils.reject(errors, "email", EMAIL_IS_USED);
		int param4 = dao.checkUserPhoneNumberUniqueValues(shipperUserFormModel
				.getPhoneNumber());
		if (param4 != 0)
			ValidationUtils.reject(errors, "phoneNumber", PHONE_NUMBER_IS_USED);
	}
}
