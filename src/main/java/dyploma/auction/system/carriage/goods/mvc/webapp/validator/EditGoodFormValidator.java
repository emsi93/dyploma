package dyploma.auction.system.carriage.goods.mvc.webapp.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dyploma.auction.system.carriage.goods.modules.ValidationUtils;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.GoodModelForEdit;
@Component("editGoodFormValidator")
@Scope("singleton")
public class EditGoodFormValidator implements Validator {

	private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";
	private static final String TITLE_TOO_LONG = "Podany tytu³ jest za d³ugi.";
	private static final String CONTENT_TOO_LONG = "Podana treœæ jest za d³uga.";
	private static final String CITY_TOO_LONG = "Podana nazwa miasta jest za d³uga.";
	private static final String STREET_TOO_LONG = "Podana nazwa ulicy jest za d³uga.";

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return GoodModelForEdit.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {

		GoodModelForEdit goodModel = (GoodModelForEdit) target;

		ValidationUtils.rejectIfEmpty(errors, "title",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "fromCountry",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "fromCity",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "fromStreet",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "toCountry",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "toCity",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "toStreet",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "maxPrice",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "dateOfDelivery",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "trailer",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "status",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "weight",
				OBLIGATORY_FIELD_ERROR_MSG);


		ValidationUtils.rejectIfTooLong(errors, "title", goodModel.getTitle(),
				50, TITLE_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "content",
				goodModel.getContent(), 1000, CONTENT_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "fromCity",
				goodModel.getFromCity(), 30, CITY_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "fromStreet",
				goodModel.getFromStreet(), 60, STREET_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "toCity",
				goodModel.getToCity(), 30, CITY_TOO_LONG);
		ValidationUtils.rejectIfTooLong(errors, "toStreet",
				goodModel.getToStreet(), 60, STREET_TOO_LONG);

	}

}
