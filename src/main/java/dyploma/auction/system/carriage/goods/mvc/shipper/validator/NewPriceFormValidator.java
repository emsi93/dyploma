package dyploma.auction.system.carriage.goods.mvc.shipper.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import dyploma.auction.system.carriage.goods.modules.ValidationUtils;
import dyploma.auction.system.carriage.goods.mvc.shipper.dao.ShipperDAOInterface;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.NewPrice;

@Component("priceFormValidator")
@Scope("singleton")
public class NewPriceFormValidator implements Validator {

	private static final String OBLIGATORY_FIELD_ERROR_MSG = "Pole jest wymagane.";
	private static final String PRICE_ERROR = "Podana kwota jest b³êdna.";

	@Autowired
	private ShipperDAOInterface dao;

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return NewPrice.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		NewPrice newPrice = (NewPrice) target;

		ValidationUtils.rejectIfEmpty(errors, "price",
				OBLIGATORY_FIELD_ERROR_MSG);

		
		if (newPrice.getActualPrice().equals("")) {
			if (newPrice.getMaxPrice() < newPrice.getPrice()) {
				ValidationUtils.reject(errors, "actualPrice", PRICE_ERROR);
			}
		} else {
			if (Double.parseDouble(newPrice.getActualPrice()) < newPrice
					.getPrice()) {
				ValidationUtils.reject(errors, "actualPrice", PRICE_ERROR);
			}
		}

	}

}
