package dyploma.auction.system.carriage.goods.mvc.shipper.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dyploma.auction.system.carriage.goods.modules.EuropeCountryList;
import dyploma.auction.system.carriage.goods.mvc.shipper.dao.ShipperDAOInterface;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ShipperCompanyModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ShipperCompanyFormModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ShipperUserFormModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.ShipperCompanyFormValidator;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.ShipperUserFormValidator;

@Controller
public class ShipperController {

	@Autowired
	private ShipperDAOInterface dao;

	@Autowired
	private ShipperCompanyFormValidator shipperFormValidator;

	@InitBinder("shipperForm")
	protected void initShipperFormValidator(WebDataBinder binder) {
		binder.setValidator(shipperFormValidator);
	}

	@Autowired
	private ShipperUserFormValidator userFormValidator;

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
			ShipperCompanyFormModel shipperFormModelorNull, Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("registerCompany");
		modelAndView.addObject("country",EuropeCountryList.getEuropeCountryList());
		if (shipperFormModelorNull == null) {

			shipperFormModelorNull = new ShipperCompanyFormModel(null, null, null,
					null, null, null, null, null, null, null, null, null);
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
			@ModelAttribute("shipperForm") @Validated ShipperCompanyFormModel shipperFormModel,
			BindingResult result) {

		if (result.hasErrors()) {

			return registerCompanyGET(shipperFormModel, 1);
		}

		else {
			return registerUserGET(
					new ShipperCompanyModel(shipperFormModel, 1).toString(),
					null, 2);
		}
	}

	@RequestMapping(value = "/registerUser/{company}", method = RequestMethod.GET)
	public ModelAndView registerUserGET(@PathVariable String company,
			ShipperUserFormModel userFormModelorNull, Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("registerUser");
		modelAndView.addObject("firm", company);
		modelAndView.addObject("country",EuropeCountryList.getEuropeCountryList());
		if (userFormModelorNull == null) {

			userFormModelorNull = new ShipperUserFormModel(null, null, null, null,
					null, null, null, null, null, null, null, null, null);
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

	@RequestMapping(value = "/registerUser/{company}", method = RequestMethod.POST)
	public ModelAndView registerUserPost(@PathVariable String company,
			@ModelAttribute("userForm") @Validated ShipperUserFormModel userFormModel,
			BindingResult result) {

		if (result.hasErrors()) {

			return registerUserGET(company, userFormModel, 1);
		}

		else {
			dao.insert(company, userFormModel);
			return registerUserGET(company, userFormModel, 3);
		}
	}
}
