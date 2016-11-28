package dyploma.auction.system.carriage.goods.mvc.webapp.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dyploma.auction.system.carriage.goods.modules.ValidationUtils;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.CompanyModel;

@Component("editCompanyFormValidator")
@Scope("singleton")
public class EditCompanyFormValidator implements Validator {
	private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";
	private static final String COMPANY_NAME_TOO_LONG = "Nazwa firmy jest za d³uga.";
	private static final String POSTCODE_TOO_LONG = "Kod pocztowy jest za d³ugi.";
	private static final String NAME_CITY_TOO_LONG = "Nazwa miasta jest za d³uga.";
	private static final String NIP_TOO_LONG = "NIP jest za d³ugi.";
	private static final String PHONE_NUMBER_TOO_LONG = "Numer telefonu jest za d³ugi.";
	private static final String ERROR_NIP = "NIP mo¿e zawieraæ jedynie cyfry.";
	private static final String ERROR_FLAT_NUMBER = "Numer lokalu mo¿e zawieraæ jedynie cyfry.";
	private static final String ERROR_PHONE_NUMBER = "Numer telefonu mo¿e zawieraæ jedynie cyfry.";
	private static final String EMAIL_TOO_LONG = "Adres email jest za d³ugi.";
	private static final String DESCRIPTION_IS_TO_LONG = "Opis firmy jest za d³ugi.";
	private static final String WEBSITE_IS_TO_LONG = "Adres strony jest za d³ugi.";

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return CompanyModel.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		CompanyModel companyModel = (CompanyModel) target;

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
				companyModel.getCompanyName(), 50, COMPANY_NAME_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "postcode",
				companyModel.getPostcode(), 10, POSTCODE_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "city", companyModel.getCity(),
				30, NAME_CITY_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "nipNumber",
				companyModel.getPostcode(), 10, NIP_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "phoneNumber",
				companyModel.getPhoneNumber(), 15, PHONE_NUMBER_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "email",
				companyModel.getEmail(), 50, EMAIL_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "description",
				companyModel.getDescription(), 1000, DESCRIPTION_IS_TO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "website",
				companyModel.getWebsite(), 100, WEBSITE_IS_TO_LONG);

		if (!companyModel.getPhoneNumber().matches("[0-9]+")
				&& companyModel.getPhoneNumber().length() > 0)
			ValidationUtils.reject(errors, "phoneNumberUser",
					ERROR_PHONE_NUMBER);
		if (!companyModel.getNipNumber().matches("[0-9]+")
				&& companyModel.getNipNumber().length() > 0)
			ValidationUtils.reject(errors, "nipNumber", ERROR_NIP);
		if (!companyModel.getFlatNumber().matches("[0-9]+")
				&& companyModel.getFlatNumber().length() > 0)
			ValidationUtils.reject(errors, "flatNumber", ERROR_FLAT_NUMBER);
	}

}
