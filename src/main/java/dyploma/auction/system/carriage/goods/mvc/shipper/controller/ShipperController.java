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
import dyploma.auction.system.carriage.goods.mvc.shipper.model.UserFormModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.ShipperFormValidator;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.UserFormValidator;

@Controller
public class ShipperController {

	@Autowired
	private ShipperFormValidator shipperFormValidator;

	@InitBinder("shipperForm")
	protected void initShipperFormValidator(WebDataBinder binder) {
		binder.setValidator(shipperFormValidator);
	}

	@Autowired
	private UserFormValidator userFormValidator;

	@InitBinder("userForm")
	protected void initUserFormValidator(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("message", "siema");
		return modelAndView;
	}

	@RequestMapping(value = "/registerCompany", method = RequestMethod.GET)
	public ModelAndView registerCompanyGET(
			ShipperFormModel shipperFormModelorNull, Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("registerCompany");

		if (shipperFormModelorNull == null) {

			shipperFormModelorNull = new ShipperFormModel(null, null, null,
					null, null, null, null, null, null);
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

	@RequestMapping(value = "/registerCompany", method = RequestMethod.POST)
	public ModelAndView registerCompanyPost(
			@ModelAttribute("shipperForm") @Validated ShipperFormModel shipperFormModel,
			BindingResult result) {

		if (result.hasErrors()) {

			return registerCompanyGET(shipperFormModel, 1);
		}

		else {
			return registerUserGET(null, 2);
		}
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.GET)
	public ModelAndView registerUserGET(UserFormModel userFormModelorNull,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("registerUser");
		if (userFormModelorNull == null) {

			userFormModelorNull = new UserFormModel(null, null);
		}
		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "èle wype≥ni≥eú pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc", "Stworzono firmÍ");
				break;
			case 3:
				modelAndView.addObject("wiadomosc", "Stworzono uøytkownika");
			default:
				break;
			}
		}
		modelAndView.addObject("userForm", userFormModelorNull);
		return modelAndView;
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ModelAndView registerUserPost(
			@ModelAttribute("userForm") @Validated UserFormModel userFormModel,
			BindingResult result) {

		if (result.hasErrors()) {

			return registerUserGET(userFormModel, 1);
		}

		else {
			return registerUserGET(userFormModel, 3);
		}
	}
}
