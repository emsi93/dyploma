package dyploma.auction.system.carriage.goods.mvc.shipper.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.EditEmployeeFormValidator;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.EditProfileFormValidator;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.RegisterFormValidator;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.UserFormValidator;

@Controller
public class ShipperController {

	private static final String LOGIN_IS_USED = "Login jest ju� u�yty.";
	private static final String EMAIL_IS_USED = "Adres email jest ju� u�yty.";
	private static final String NIP_IS_USED = "Ten NIP jest ju� u�yty.";
	private static final String NIP_WRONG_SIZE = "NIP jest za d�ugi.";
	private static final String EMAIL_TOO_LONG = "Adres email jest za d�ugi.";
	private static final String LOGIN_TOO_LONG = "Login jest za d�ugi.";

	
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
	
	@Autowired
	private EditEmployeeFormValidator editEmployeeFromValidator;
	
	@InitBinder("employeeForm")
	protected void initEditEmployeeFormValidator(WebDataBinder binder) {
		binder.setValidator(editEmployeeFromValidator);
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

	@RequestMapping(value = "/editEmployee/{id}", method = RequestMethod.GET)
	public ModelAndView editEmployeeGet(@PathVariable int id, DetailsEmployeeModel detailsEmployeeModelOrNull, Integer messageCodeOrNull)
	{
		ModelAndView modelAndView = new ModelAndView("editEmployee");
		detailsEmployeeModelOrNull = dao.getDetailEmployee(id);
		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "�le wype�ni�e� pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Edycja przebieg�a pomy�lnie");
				break;
			default:
				break;
			}
		}
		List<String> rolesList = new ArrayList<String>();
		rolesList.add("ROLE_ADMIN");
		rolesList.add("ROLE_USER");
		List<String> activityList = new ArrayList<String>();
		activityList.add("Tak");
		activityList.add("Nie");
		modelAndView.addObject("rolesList", rolesList);
		modelAndView.addObject("activityList", activityList);
		modelAndView.addObject("employeeForm",detailsEmployeeModelOrNull);
		return modelAndView;
	}
	
	@RequestMapping(value = "/editEmployee/{id}", method = RequestMethod.POST)
	public ModelAndView editEmployeePost(@PathVariable int id, @ModelAttribute("employeeForm") @Validated DetailsEmployeeModel employeeForm,
			BindingResult result)
	{
		String emailOLD = dao.getDetailEmployee(id).getEmail();
		String loginOLD = dao.getDetailEmployee(id).getLogin();
		
		if(!emailOLD.equals(employeeForm.getEmail()))
		{
			if(employeeForm.getEmail().length()>50)
				result.reject("email", EMAIL_TOO_LONG);
			int param1 = dao.checkUniqueEmailUser(employeeForm.getEmail());
			if(param1==1)
				result.reject("email", EMAIL_IS_USED);
		}
		if(!loginOLD.equals(employeeForm.getLogin()))
		{
			if(employeeForm.getLogin().length()>20)
				result.reject("login",LOGIN_TOO_LONG);
			int param2 = dao.checkUniqueLogin(employeeForm.getLogin());
			if(param2==1)
				result.reject("login", LOGIN_IS_USED);
		}
		
		if(result.hasErrors())
			return editEmployeeGet(id,employeeForm,1);
		else
		{
			dao.editEmployee(employeeForm, id);
			return editEmployeeGet(id,employeeForm,2);
		}
			
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
				modelAndView.addObject("wiadomosc", "�le wype�ni�e� pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Rejestracja przebieg�a pomy�lnie");
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
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}
	@RequestMapping(value="/logout")
	public ModelAndView logout() {
		ModelAndView modelAndView = new ModelAndView("logout");
		return modelAndView;
	}

	
	@RequestMapping("/menuAdmin")
	public ModelAndView menuAdmin() {
		ModelAndView modelAndView = new ModelAndView("menuAdmin");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    modelAndView.addObject("username", auth.getName());
	    Object[] role = auth.getAuthorities().toArray();
	    modelAndView.addObject("role",role[0].toString());
		return modelAndView;
	}
	
	@RequestMapping("/menuUser")
	public ModelAndView menuUser() {
		ModelAndView modelAndView = new ModelAndView("menuUser");
		return modelAndView;
	}
	
	@RequestMapping("/errorLogin")
	public ModelAndView errorLogin() {
		ModelAndView modelAndView = new ModelAndView("errorLogin");
		return modelAndView;
	}
	
	@RequestMapping("/employeesList")
	public ModelAndView employeesList()
	{
		ModelAndView modelAndView = new ModelAndView("employeesList");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		List<EmployeeModel> employeeList = dao.getEmployeesList(companyID);
		modelAndView.addObject("employeesList", employeeList);
		return modelAndView;
	}
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUserGet(UserModel userModelOrNull,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("newUser");
		if (userModelOrNull == null) {
			userModelOrNull = new UserModel(null, null, null, null,
					null, null, null);
		}
		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "�le wype�ni�e� pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Rejestracja nowego u�ytkownika przebieg�a pomy�lnie");
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
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			int userID = dao.getUserIDByLogin(auth.getName());
			int companyID = dao.getCompanyID(userID);
			dao.registerUser(userModel, companyID); //1 - companyID
			return newUserGet(userModel, 2);
		}
	}
	
	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfileUserGet(ProfileModel profileModelOrNull,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("editProfile");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int userID = dao.getUserIDByLogin(auth.getName());
		profileModelOrNull = dao.getProfileUser(userID);
		
		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "�le wype�ni�e� pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Edycja profilu przebieg�a pomy�lnie");
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int userID = dao.getUserIDByLogin(auth.getName());
		String emailOLD = dao.getProfileUser(userID).getEmail();
		String loginOLD = dao.getProfileUser(userID).getLogin();
		
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
			dao.editProfile(profileForm,userID);
			return editProfileUserGet(profileForm, 2);
		}
	}
	
	@RequestMapping(value = "/editCompany", method = RequestMethod.GET)
	public ModelAndView editCompanyGet(CompanyModel companyModel,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("editCompany");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int userID = dao.getUserIDByLogin(auth.getName());
		companyModel = dao.getCompanyModel(userID);
		
		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "�le wype�ni�e� pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Edycja firmy przebieg�a pomy�lnie");
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int userID = dao.getUserIDByLogin(auth.getName());
		String emailOLD = dao.getCompanyModel(userID).getEmail();
		String nipOLD = dao.getCompanyModel(userID).getNipNumber();
		
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
			dao.editCompany(companyModel,dao.getCompanyModel(userID).getId());
			return editCompanyGet(companyModel, 2);
		}
	}
}
