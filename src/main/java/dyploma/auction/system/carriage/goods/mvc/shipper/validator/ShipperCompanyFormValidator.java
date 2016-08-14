package dyploma.auction.system.carriage.goods.mvc.shipper.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dyploma.auction.system.carriage.goods.modules.ValidationUtils;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ShipperCompanyFormModel;

@Component("shipperFormValidator")
@Scope("singleton")
public class ShipperCompanyFormValidator implements Validator {
	private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";
	private static final String FIELD_ERROR_MSG = "Pole zawiera inne znaki ni¿ cyfry.";
	private static final String NIP_ERROR_MSG = "Liczba cyfr w numerze NIP jest z³a.";

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ShipperCompanyFormModel.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ShipperCompanyFormModel shipperCompanyFormModel = (ShipperCompanyFormModel) target;

		ValidationUtils.rejectIfEmpty(errors, "companyName",
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
		ValidationUtils.rejectIfEmpty(errors, "nipNumber",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "phoneNumber",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "email",
				OBLIGATORY_FIELD_ERROR_MSG);

		if (!shipperCompanyFormModel.getHouseNumber().matches("[0-9]")
				&& shipperCompanyFormModel.getHouseNumber().length() > 0)
			ValidationUtils.reject(errors, "houseNumber", FIELD_ERROR_MSG);
		if (!shipperCompanyFormModel.getPhoneNumber().matches("[0-9]")
				&& shipperCompanyFormModel.getPhoneNumber().length() > 0)
			ValidationUtils.reject(errors, "phoneNumber", FIELD_ERROR_MSG);
		if (!shipperCompanyFormModel.getSuiceNumber().matches("[0-9]")
				&& shipperCompanyFormModel.getSuiceNumber().length() > 0)
			ValidationUtils.reject(errors, "flatNumber", FIELD_ERROR_MSG);

		if (shipperCompanyFormModel.getNipNumber().length() > 0) {
			if (shipperCompanyFormModel.getNipNumber().length() == 10) {
				if (!shipperCompanyFormModel.getNipNumber().matches("[0-9]"))
					ValidationUtils
							.reject(errors, "nipNumber", FIELD_ERROR_MSG);
			} else
				ValidationUtils.reject(errors, "nipNumber", NIP_ERROR_MSG);

		}

	}

}
