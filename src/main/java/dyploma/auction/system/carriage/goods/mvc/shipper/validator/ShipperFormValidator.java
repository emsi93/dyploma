package dyploma.auction.system.carriage.goods.mvc.shipper.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;




import dyploma.auction.system.carriage.goods.modules.ValidationUtils;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ShipperFormModel;
@Component("shipperFormValidator")
@Scope("singleton")
public class ShipperFormValidator implements Validator{
	private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ShipperFormModel.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "companyName", OBLIGATORY_FIELD_ERROR_MSG);
	}

}
