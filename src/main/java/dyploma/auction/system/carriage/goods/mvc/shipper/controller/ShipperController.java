package dyploma.auction.system.carriage.goods.mvc.shipper.controller;

import java.util.List;

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
import dyploma.auction.system.carriage.goods.mvc.shipper.model.CompanyModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.DetailsEmployeeModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.EmployeeModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ProfileModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.RegisterModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.UserModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.EditCompanyFormValidator;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.EditProfileFormValidator;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.RegisterFormValidator;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.UserFormValidator;

@Controller
public class ShipperController {

	private static final String LOGIN_IS_USED = "Login jest juø uøyty.";
	private static final String EMAIL_IS_USED = "Adres email jest juø uøyty.";
	private static final String NIP_IS_USED = "Ten NIP jest juø uøyty.";
	private static final String NIP_WRONG_SIZE = "NIP jest za d≥ugi.";
	private static final String EMAIL_TOO_LONG = "Adres email jest za d≥ugi.";
	private static final String LOGIN_TOO_LONG = "Login jest za d≥ugi.";

	
	private static int TYPE_OF_COMPANY = 1;

	@Autowired
	private ShipperDAOInterface dao;

	@Autowired
	private RegisterFormValidator registerFormValidator;

	@InitBinder("registerForm")
	protected void initRegisterFormValidator(WebDataBinder binder) {
		binder.setValidator(registerFormValidator);
	}
	
	@Autowired
	private UserFormValidator userFormValidator;
	
	@InitBinder("userForm")
	protected void initUserFormValidator(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}
	
	@Autowired
	private EditProfileFormValidator editProfileFormValidator;
	
	@InitBinder("profileForm")
	protected void initEditProfileFormValidator(WebDataBinder binder) {
		binder.setValidator(editProfileFormValidator);
	}
	
	@Autowired
	private EditCompanyFormValidator editCompanyFormValidator;
	
	@InitBinder("companyForm")
	protected void initEditCompanyFormValidator(WebDataBinder binder) {
		binder.setValidator(editCompanyFormValidator);
	}
	
	@RequestMapping("/register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView("register");
		return modelAndView;
	}
	
	@RequestMapping("/detailsEmployee/{id}")
	public ModelAndView detailsEmployee(@PathVariable int id)
	{
		ModelAndView modelAndView = new ModelAndView("detailsEmployee");
		DetailsEmployeeModel detailsEmployeeModel = dao.getDetailEmployee(id);
		modelAndView.addObject("detailsEmployeeModel", detailsEmployeeModel);
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
	
	@RequestMapping("/employeesList")
	public ModelAndView employeesList()
	{
		ModelAndView modelAndView = new ModelAndView("employeesList");
		List<EmployeeModel> employeeList = dao.getEmployeesList(1);
		modelAndView.addObject("employeesList", employeeList);
		return modelAndView;
	}
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUserGet(UserModel userModelOrNull,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("newUser");
		List<EmployeeModel> employeeList = dao.getEmployeesList(1);
		modelAndView.addObject("employeesList", employeeList);
		if (userModelOrNull == null) {
			userModelOrNull = new UserModel(null, null, null, null,
					null, null, null);
		}
		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "èle wype≥ni≥eú pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Rejestracja nowego uøytkownika przebieg≥a pomyúlnie");
				break;
			default:
				break;
			}
		}
		modelAndView.addObject("userForm", userModelOrNull);
		return modelAndView;
	}
	
	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public ModelAndView newUserPost(
			@ModelAttribute("userForm") @Validated UserModel userModel,
			BindingResult result) {
		if (result.hasErrors())
			return newUserGet(userModel, 1);
		else {
			dao.registerUser(userModel, 1); //1 - companyID
			return newUserGet(userModel, 2);
		}
	}
	
	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfileUserGet(ProfileModel profileModelOrNull,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("editProfile");
		profileModelOrNull = dao.getProfileUser(1);
		
		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "èle wype≥ni≥eú pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Edycja profilu przebieg≥a pomyúlnie");
				break;
			default:
				break;
			}
		}
		
		modelAndView.addObject("profileForm", profileModelOrNull);
		return modelAndView;
	}
	
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public ModelAndView editProfileUserPost(
			@ModelAttribute("profileForm") @Validated ProfileModel profileForm,
			BindingResult result) {
		String emailOLD = dao.getProfileUser(1).getEmail();
		String loginOLD = dao.getProfileUser(1).getLogin();
		
		if(!emailOLD.equals(profileForm.getEmail()))
		{
			if(profileForm.getEmail().length()>50)
				result.reject("email", EMAIL_TOO_LONG);
			int param1 = dao.checkUniqueEmailUser(profileForm.getEmail());
			if(param1==1)
				result.reject("email", EMAIL_IS_USED);
		}
		if(!loginOLD.equals(profileForm.getLogin()))
		{
			if(profileForm.getLogin().length()>20)
				result.reject("login",LOGIN_TOO_LONG);
			int param2 = dao.checkUniqueLogin(profileForm.getLogin());
			if(param2==1)
				result.reject("login", LOGIN_IS_USED);
		}
		if (result.hasErrors())
		{
			return editProfileUserGet(profileForm, 1);
		}
			
		else {
			dao.editProfile(profileForm,1);
			return editProfileUserGet(profileForm, 2);
		}
	}
	
	@RequestMapping(value = "/editCompany", method = RequestMethod.GET)
	public ModelAndView editCompanyGet(CompanyModel companyModel,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("editCompany");
		companyModel = dao.getCompanyModel(1);
		
		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "èle wype≥ni≥eú pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Edycja firmy przebieg≥a pomyúlnie");
				break;
			default:
				break;
			}
		}
		modelAndView.addObject("countryList",
				EuropeCountryList.getEuropeCountryList());
		modelAndView.addObject("companyForm", companyModel);
		return modelAndView;
	}
	@RequestMapping(value = "/editCompany", method = RequestMethod.POST)
	public ModelAndView editCompanyPost(
			@ModelAttribute("companyForm") @Validated CompanyModel companyModel,
			BindingResult result) {
		String emailOLD = dao.getCompanyModel(1).getEmail();
		String nipOLD = dao.getCompanyModel(1).getNipNumber();
		
		if(!emailOLD.equals(companyModel.getEmail()))
		{
			if(companyModel.getEmail().length()>50)
				result.reject("email", EMAIL_TOO_LONG);
			int param1 = dao.checkUniqueEmailUser(companyModel.getEmail());
			if(param1==1)
				result.reject("email", EMAIL_IS_USED);
		}
		if(!nipOLD.equals(companyModel.getNipNumber()))
		{
			if(companyModel.getNipNumber().length()!=10)
				result.reject("nipNumber", NIP_WRONG_SIZE);
			int param2 = dao.checkUniqueNip(companyModel.getNipNumber());
			if(param2==1)
				result.reject("nipNumber", NIP_IS_USED);
		}
		if (result.hasErrors())
		{
			return editCompanyGet(companyModel, 1);
		}
			
		else {
			dao.editCompany(companyModel,1);
			return editCompanyGet(companyModel, 2);
		}
	}
}
