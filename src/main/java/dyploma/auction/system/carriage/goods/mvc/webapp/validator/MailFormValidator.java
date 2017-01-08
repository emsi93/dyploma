package dyploma.auction.system.carriage.goods.mvc.webapp.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dyploma.auction.system.carriage.goods.modules.ValidationUtils;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.MailModel;

@Component("mailFormValidator")
@Scope("singleton")
public class MailFormValidator implements Validator {

	private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MailModel.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
	
		
		ValidationUtils.rejectIfEmpty(errors, "mailAddress",
				OBLIGATORY_FIELD_ERROR_MSG);
		ValidationUtils.rejectIfEmpty(errors, "link",
				OBLIGATORY_FIELD_ERROR_MSG);
	}

}
