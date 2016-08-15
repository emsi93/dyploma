package dyploma.auction.system.carriage.goods.mvc.shipper.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dyploma.auction.system.carriage.goods.modules.ValidationUtils;
import dyploma.auction.system.carriage.goods.mvc.shipper.dao.impl.ShipperDAOImpl;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ShipperCompanyFormModel;

@Component("shipperFormValidator")
@Scope("singleton")
public class ShipperCompanyFormValidator implements Validator {
	private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";
	private static final String FIELD_ERROR_MSG = "Pole zawiera inne znaki ni¿ cyfry.";
	private static final String EMAIL_IS_USED = "Ten adres email jest ju¿ u¿yty";
	private static final String NIP_IS_USED = "Ten NIP jest ju¿ u¿yty";
	private static final String PHONE_NUMBER_IS_USED = "Ten numer telefony jest juz u¿yty";
	private static final String COMPANY_NAME_TOO_LONG = "Nazwa firmy jest za d³uga.";
	private static final String POSTCODE_TOO_LONG = "Kod pocztowy jest za d³ugi.";
	private static final String CITY_NAME_TOO_LONG = "Nazwa miejscowoœci jest za d³uga.";
	private static final String STREET_NAME_TO_LONG = "Nazwa ulicy jest za d³uga.";
	private static final String NIP_NUMBER_TO_LONG = "NIP jest za d³ugi.";
	private static final String WEBSITE_URL_TOO_LONG = "Adres strony jest za d³ugi.";
	private static final String EMAIL_TO_LONG = "Adres email jest za d³ugi.";
	private static final String DESCRIPTION_TO_LONG = "Opis firmy jest za d³ugi.";
	private static final String PHONE_NUMBER_TO_LONG = "Numer telefonu jest za d³ugi.";
	private static final String NNIP_NUMBER_TOO_SHORT = "Podany numer NIP jest za krótki.";
	private static final String PHONE_NUMBER_TOO_SHORT = "Podany numer telefonu jest za krótki.";

	@Autowired
	private ShipperDAOImpl dao;

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ShipperCompanyFormModel.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ShipperCompanyFormModel shipperCompanyFormModel = (ShipperCompanyFormModel) target;

		ValidationUtils.rejectIfEmpty(errors, "companyName",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "postcode",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "city",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "street",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "houseNumber",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "nipNumber",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "phoneNumber",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "email",
				OBLIGATORY_FIELD_ERROR_MSG);

		ValidationUtils.rejectIfTooLong(errors, "companyName",
				shipperCompanyFormModel.getCompanyName(), 50,
				COMPANY_NAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "postcode",
				shipperCompanyFormModel.getPostcode(), 10, POSTCODE_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "city",
				shipperCompanyFormModel.getCity(), 30, CITY_NAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "street",
				shipperCompanyFormModel.getStreet(), 60, STREET_NAME_TO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "nipNumber",
				shipperCompanyFormModel.getNipNumber(), 10, NIP_NUMBER_TO_LONG);
		ValidationUtils
				.rejectIfTooLong(errors, "website",
						shipperCompanyFormModel.getWebsite(), 100,
						WEBSITE_URL_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "email",
				shipperCompanyFormModel.getEmail(), 100, EMAIL_TO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "description",
				shipperCompanyFormModel.getDescription(), 1000,
				DESCRIPTION_TO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "phoneNumber",
				shipperCompanyFormModel.getPhoneNumber(), 15,
				PHONE_NUMBER_TO_LONG);
		if(shipperCompanyFormModel.getNipNumber().length()<10 && shipperCompanyFormModel.getNipNumber().length()>0)
			ValidationUtils.reject(errors, "nipNumber", NNIP_NUMBER_TOO_SHORT);
		if(shipperCompanyFormModel.getPhoneNumber().length()<9  && shipperCompanyFormModel.getPhoneNumber().length()>0)
			ValidationUtils.reject(errors, "phoneNumber", PHONE_NUMBER_TOO_SHORT);
		
		if (shipperCompanyFormModel.getHouseNumber().matches("[0-9]")
				&& shipperCompanyFormModel.getHouseNumber().length() > 0)
			ValidationUtils.reject(errors, "houseNumber", FIELD_ERROR_MSG);
		if (shipperCompanyFormModel.getPhoneNumber().matches("[0-9]")
				&& shipperCompanyFormModel.getPhoneNumber().length() > 0)
			ValidationUtils.reject(errors, "phoneNumber", FIELD_ERROR_MSG);
		if (shipperCompanyFormModel.getSuiceNumber().matches("[0-9]")
				&& shipperCompanyFormModel.getSuiceNumber().length() > 0)
			ValidationUtils.reject(errors, "suiceNumber", FIELD_ERROR_MSG);

		if (shipperCompanyFormModel.getCountry().equals(""))
			ValidationUtils.reject(errors, "country",
					OBLIGATORY_FIELD_ERROR_MSG);

		if (shipperCompanyFormModel.getNipNumber().length() > 0) {
			if (shipperCompanyFormModel.getNipNumber().length() == 10) {
				if (shipperCompanyFormModel.getNipNumber().matches("[0-9]"))
					ValidationUtils
							.reject(errors, "nipNumber", FIELD_ERROR_MSG);
			}
		}

		int param1 = dao
				.checkCompanyNipNumberUniqueValues(shipperCompanyFormModel
						.getNipNumber());
		if (param1 != 0)
			ValidationUtils.reject(errors, "nipNumber", NIP_IS_USED);
		int param2 = dao.checkCompanyEmailUniqueValues(shipperCompanyFormModel
				.getEmail());
		if (param2 != 0)
			ValidationUtils.reject(errors, "email", EMAIL_IS_USED);
		int param3 = dao
				.checkCompanyPhoneNumberUniqueValues(shipperCompanyFormModel
						.getPhoneNumber());
		if (param3 != 0)
			ValidationUtils.reject(errors, "phoneNumber", PHONE_NUMBER_IS_USED);
	}
}
