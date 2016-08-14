package dyploma.auction.system.carriage.goods.mvc.shipper.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dyploma.auction.system.carriage.goods.modules.ValidationUtils;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ShipperUserFormModel;

@Component("userFormValidator")
@Scope("singleton")
public class ShipperUserFormValidator implements Validator {
	private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";
	private static final String FIELD_ERROR_MSG = "Pole zawiera inne znaki ni¿ cyfry.";
	private static final String PESEL_ERROR_MSG = "Liczba cyfr w numerze PESEL jest z³a.";

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
		ValidationUtils.rejectIfEmpty(errors, "country",
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

		if (!shipperUserFormModel.getHouseNumber().matches("[0-9]"))
			;
		ValidationUtils.reject(errors, "houseNumber", FIELD_ERROR_MSG);
		if (!shipperUserFormModel.getPhoneNumber().matches("[0-9]"))
			;
		ValidationUtils.reject(errors, "phoneNumber", FIELD_ERROR_MSG);
		if (!shipperUserFormModel.getFlatNumber().matches("[0-9]"))
			;
		ValidationUtils.reject(errors, "flatNumber", FIELD_ERROR_MSG);

		if (shipperUserFormModel.getPeselNumber().length() > 0) {
			if (shipperUserFormModel.getPeselNumber().length() == 10) {
				if (!shipperUserFormModel.getPeselNumber().matches("[0-9]"))
					ValidationUtils.reject(errors, "peselNumber",
							FIELD_ERROR_MSG);
			} else
				ValidationUtils.reject(errors, "peselNumber", PESEL_ERROR_MSG);

		}
	}

}
