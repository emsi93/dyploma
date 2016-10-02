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

import dyploma.auction.system.carriage.goods.modules.EuropeCountryList;
import dyploma.auction.system.carriage.goods.mvc.shipper.dao.ShipperDAOInterface;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.RegisterModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.RegisterFormValidator;

@Controller
public class ShipperController {

	private static int TYPE_OF_COMPANY = 1;

	@Autowired
	private ShipperDAOInterface dao;

	@Autowired
	private RegisterFormValidator registerFormValidator;

	@InitBinder("registerForm")
	protected void initRegisterFormValidator(WebDataBinder binder) {
		binder.setValidator(registerFormValidator);
	}

	@RequestMapping("/register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView("register");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerGet(RegisterModel registerModelOrNull,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("register");
		if (registerModelOrNull == null) {
			registerModelOrNull = new RegisterModel(null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null);
		}
		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "èle wype≥ni≥eú pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Rejestracja przebieg≥a pomyúlnie");
				break;
			default:
				break;
			}
		}
		modelAndView.addObject("countryList",
				EuropeCountryList.getEuropeCountryList());
		modelAndView.addObject("registerForm", registerModelOrNull);
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerPost(
			@ModelAttribute("registerForm") @Validated RegisterModel registerModel,
			BindingResult result) {
		if (result.hasErrors())
			return registerGet(registerModel, 1);
		else {
			dao.registerCompany(registerModel, TYPE_OF_COMPANY);
			return registerGet(registerModel, 2);
		}

	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	@RequestMapping("/menuAdmin")
	public ModelAndView menuAdmin() {
		ModelAndView modelAndView = new ModelAndView("menuAdmin");
		return modelAndView;
	}
}
