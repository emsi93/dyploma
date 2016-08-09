package dyploma.auction.system.carriage.goods.mvc.shipper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;







import dyploma.auction.system.carriage.goods.mvc.shipper.model.ShipperFormModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.ShipperFormValidator;

@Controller
public class ShipperController {

	@Autowired
	private ShipperFormValidator shipperFormValidator;

	@InitBinder("shipperForm")
	protected void initSampleFormBinder(WebDataBinder binder) {
		binder.setValidator(shipperFormValidator);
	}

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("message", "siema");
		return modelAndView;
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerGET(ShipperFormModel shipperFormModelorNull, Integer messageCodeOrNull)
	{
		ModelAndView modelAndView = new ModelAndView("register");
		
		if (shipperFormModelorNull == null) {

			shipperFormModelorNull = new ShipperFormModel(null);
		}
		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "èle wype≥ni≥eú pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc", "Zmieniono rekord");
				break;
			default:
				break;
			}
		}
		modelAndView.addObject("shipperForm", shipperFormModelorNull);
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerPost(
			@ModelAttribute("shipperForm") @Validated ShipperFormModel shipperFormModel,
			BindingResult result) {

		if (result.hasErrors()) {

			return registerGET(shipperFormModel, 1);
		}

		else {
			return registerGET(shipperFormModel, 2);
		}
	}


}
