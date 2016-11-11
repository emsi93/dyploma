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
import dyploma.auction.system.carriage.goods.modules.TrailersList;
import dyploma.auction.system.carriage.goods.mvc.shipper.dao.ShipperDAOInterface;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.CompanyModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.DetailsEmployeeModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.DetailsGoodModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.EmployeeModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.GoodModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.GoodModelForEdit;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.GoodModelForList;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.NewPrice;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.ProfileModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.RegisterModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.model.UserModel;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.EditCompanyFormValidator;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.EditEmployeeFormValidator;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.EditGoodFormValidator;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.EditProfileFormValidator;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.GoodFormValidator;
import dyploma.auction.system.carriage.goods.mvc.shipper.validator.NewPriceFormValidator;
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

	@Autowired
	private GoodFormValidator goodFormValidator;

	@InitBinder("goodForm")
	protected void initGoodFormValidator(WebDataBinder binder) {
		binder.setValidator(goodFormValidator);
	}

	@Autowired
	private EditGoodFormValidator editGoodFormValidator;

	@InitBinder("editGoodForm")
	protected void initEditGoodFormValidator(WebDataBinder binder) {
		binder.setValidator(editGoodFormValidator);
	}

	@Autowired
	private NewPriceFormValidator newPriceFormValidator;

	@InitBinder("priceForm")
	protected void initNewPriceFormValidator(WebDataBinder binder) {
		binder.setValidator(newPriceFormValidator);
	}

	@RequestMapping("/detailsEmployee/{id}")
	public ModelAndView detailsEmployee(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView("detailsEmployee");
		DetailsEmployeeModel detailsEmployeeModel = dao.getDetailEmployee(id);
		modelAndView.addObject("detailsEmployeeModel", detailsEmployeeModel);
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		return modelAndView;
	}

	@RequestMapping("/detailsCargo/{id}")
	public ModelAndView detailsCargo(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView("detailsCargo");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		DetailsGoodModel detailsGoodModel = dao.getDetailsGood(id);
		modelAndView.addObject("detailsCargoModel", detailsGoodModel);
		return modelAndView;
	}

	@RequestMapping(value = "/editEmployee/{id}", method = RequestMethod.GET)
	public ModelAndView editEmployeeGet(@PathVariable int id,
			DetailsEmployeeModel detailsEmployeeModelOrNull,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("editEmployee");
		detailsEmployeeModelOrNull = dao.getDetailEmployee(id);
		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "èle wype≥ni≥eú pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Edycja przebieg≥a pomyúlnie");
				break;
			default:
				break;
			}
		}
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		List<String> rolesList = new ArrayList<String>();
		rolesList.add("ROLE_ADMIN");
		rolesList.add("ROLE_USER");
		List<String> activityList = new ArrayList<String>();
		activityList.add("Tak");
		activityList.add("Nie");
		modelAndView.addObject("rolesList", rolesList);
		modelAndView.addObject("activityList", activityList);
		modelAndView.addObject("employeeForm", detailsEmployeeModelOrNull);
		return modelAndView;
	}

	@RequestMapping(value = "/editEmployee/{id}", method = RequestMethod.POST)
	public ModelAndView editEmployeePost(
			@PathVariable int id,
			@ModelAttribute("employeeForm") @Validated DetailsEmployeeModel employeeForm,
			BindingResult result) {
		String emailOLD = dao.getDetailEmployee(id).getEmail();
		String loginOLD = dao.getDetailEmployee(id).getLogin();

		if (!emailOLD.equals(employeeForm.getEmail())) {
			if (employeeForm.getEmail().length() > 50)
				result.reject("email", EMAIL_TOO_LONG);
			int param1 = dao.checkUniqueEmailUser(employeeForm.getEmail());
			if (param1 == 1)
				result.reject("email", EMAIL_IS_USED);
		}
		if (!loginOLD.equals(employeeForm.getLogin())) {
			if (employeeForm.getLogin().length() > 20)
				result.reject("login", LOGIN_TOO_LONG);
			int param2 = dao.checkUniqueLogin(employeeForm.getLogin());
			if (param2 == 1)
				result.reject("login", LOGIN_IS_USED);
		}

		if (result.hasErrors())
			return editEmployeeGet(id, employeeForm, 1);
		else {
			dao.editEmployee(employeeForm, id);
			return editEmployeeGet(id, employeeForm, 2);
		}

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerGet(RegisterModel registerModelOrNull,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("register");
		if (registerModelOrNull == null) {
			registerModelOrNull = new RegisterModel(null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null, null);
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
		List<String> countryList = null;
		countryList = EuropeCountryList.getEuropeCountryList();
		modelAndView.addObject("countryList", countryList);
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
			dao.registerCompany(registerModel,
					Integer.parseInt(registerModel.getTypeOfCompany()));
			return registerGet(registerModel, 2);
		}
	}

	@RequestMapping(value = { "index", "/" })
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	@RequestMapping("/menu")
	public ModelAndView menu() {
		ModelAndView modelAndView = new ModelAndView("menu");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		return modelAndView;
	}

	@RequestMapping("/searchCargo")
	public ModelAndView searchCargo() {
		ModelAndView modelAndView = new ModelAndView("searchCargo");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		List<GoodModelForList> goodsList = dao.getGoodsList();
		modelAndView.addObject("goodsList", goodsList);
		return modelAndView;
	}

	@RequestMapping(value = "/cargo/{id}", method = RequestMethod.GET)
	public ModelAndView cargoGet(@PathVariable int id, NewPrice newPriceOrNull,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("cargo");

		if (newPriceOrNull == null) {
			newPriceOrNull = new NewPrice(null, null, null);
		}

		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "Poda≥eú z≥π kwotÍ");
				break;
			case 2:
				modelAndView.addObject("wiadomosc", "Zmieni≥eú kwotÍ");
				break;
			default:
				break;
			}
		}
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		DetailsGoodModel detailsGoodModel = dao.getDetailsGood(id);
		modelAndView.addObject("detailsCargoModel", detailsGoodModel);
		modelAndView.addObject("priceForm", newPriceOrNull);
		return modelAndView;
	}

	@RequestMapping(value = "/cargo/{id}", method = RequestMethod.POST)
	public ModelAndView cargoPost(@PathVariable int id,
			@ModelAttribute("priceForm") @Validated NewPrice newPrice,
			BindingResult result) {
		if (result.hasErrors())
		{
			return cargoGet(id, newPrice, 1);
		}else
		{
			dao.updatePrice(id,newPrice.getPrice());
			return cargoGet(id, newPrice, 2);
		}
			
		
	}

	@RequestMapping("/cargosList")
	public ModelAndView cargosList() {
		ModelAndView modelAndView = new ModelAndView("cargosList");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		List<GoodModelForList> goodsList = dao.getGoodsList(companyID);
		modelAndView.addObject("goodsList", goodsList);
		return modelAndView;
	}

	@RequestMapping("/errorLogin")
	public ModelAndView errorLogin() {
		ModelAndView modelAndView = new ModelAndView("errorLogin");
		return modelAndView;
	}

	@RequestMapping("/employeesList")
	public ModelAndView employeesList() {
		ModelAndView modelAndView = new ModelAndView("employeesList");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Object[] role = auth.getAuthorities().toArray();
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		List<EmployeeModel> employeeList = dao.getEmployeesList(companyID);
		modelAndView.addObject("role", role[0].toString());
		modelAndView.addObject("username", auth.getName());
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		modelAndView.addObject("employeesList", employeeList);
		return modelAndView;
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUserGet(UserModel userModelOrNull,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("newUser");
		if (userModelOrNull == null) {
			userModelOrNull = new UserModel(null, null, null, null, null, null,
					null);
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
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
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
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			int userID = dao.getUserIDByLogin(auth.getName());
			int companyID = dao.getCompanyID(userID);
			dao.registerUser(userModel, companyID); // 1 - companyID
			return newUserGet(userModel, 2);
		}
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfileUserGet(ProfileModel profileModelOrNull,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("editProfile");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		int userID = dao.getUserIDByLogin(auth.getName());
		profileModelOrNull = dao.getProfileUser(userID);

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
		modelAndView.addObject("username", auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		modelAndView.addObject("profileForm", profileModelOrNull);
		return modelAndView;
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public ModelAndView editProfileUserPost(
			@ModelAttribute("profileForm") @Validated ProfileModel profileForm,
			BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		int userID = dao.getUserIDByLogin(auth.getName());
		String emailOLD = dao.getProfileUser(userID).getEmail();
		String loginOLD = dao.getProfileUser(userID).getLogin();

		if (!emailOLD.equals(profileForm.getEmail())) {
			if (profileForm.getEmail().length() > 50)
				result.reject("email", EMAIL_TOO_LONG);
			int param1 = dao.checkUniqueEmailUser(profileForm.getEmail());
			if (param1 == 1)
				result.reject("email", EMAIL_IS_USED);
		}
		if (!loginOLD.equals(profileForm.getLogin())) {
			if (profileForm.getLogin().length() > 20)
				result.reject("login", LOGIN_TOO_LONG);
			int param2 = dao.checkUniqueLogin(profileForm.getLogin());
			if (param2 == 1)
				result.reject("login", LOGIN_IS_USED);
		}
		if (result.hasErrors()) {
			return editProfileUserGet(profileForm, 1);
		}

		else {
			dao.editProfile(profileForm, userID);
			return editProfileUserGet(profileForm, 2);
		}
	}

	@RequestMapping(value = "/editCompany", method = RequestMethod.GET)
	public ModelAndView editCompanyGet(CompanyModel companyModel,
			Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("editCompany");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		int userID = dao.getUserIDByLogin(auth.getName());
		companyModel = dao.getCompanyModel(userID);

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

		modelAndView.addObject("username", auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		List<String> countryList = null;
		countryList = EuropeCountryList.getEuropeCountryList();
		modelAndView.addObject("countryList", countryList);
		modelAndView.addObject("companyForm", companyModel);
		return modelAndView;
	}

	@RequestMapping(value = "/editCompany", method = RequestMethod.POST)
	public ModelAndView editCompanyPost(
			@ModelAttribute("companyForm") @Validated CompanyModel companyModel,
			BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		int userID = dao.getUserIDByLogin(auth.getName());
		String emailOLD = dao.getCompanyModel(userID).getEmail();
		String nipOLD = dao.getCompanyModel(userID).getNipNumber();

		if (!emailOLD.equals(companyModel.getEmail())) {
			if (companyModel.getEmail().length() > 50)
				result.reject("email", EMAIL_TOO_LONG);
			int param1 = dao.checkUniqueEmailUser(companyModel.getEmail());
			if (param1 == 1)
				result.reject("email", EMAIL_IS_USED);
		}
		if (!nipOLD.equals(companyModel.getNipNumber())) {
			if (companyModel.getNipNumber().length() != 10)
				result.reject("nipNumber", NIP_WRONG_SIZE);
			int param2 = dao.checkUniqueNip(companyModel.getNipNumber());
			if (param2 == 1)
				result.reject("nipNumber", NIP_IS_USED);
		}
		if (result.hasErrors()) {
			return editCompanyGet(companyModel, 1);
		} else {
			dao.editCompany(companyModel, dao.getCompanyModel(userID).getId());
			return editCompanyGet(companyModel, 2);
		}
	}

	@RequestMapping(value = "/newCargo", method = RequestMethod.GET)
	public ModelAndView newCargoGet(GoodModel goodModelOrNull,
			Integer messageOrNull) {
		ModelAndView modelAndView = new ModelAndView("newCargo");
		if (goodModelOrNull == null) {
			goodModelOrNull = new GoodModel(null, null, null, null, null, null,
					null, null, null, null, null);
		}
		if (messageOrNull != null) {
			switch (messageOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "èle wype≥ni≥eú pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Dodanie nowego towaru przebieg≥o pomyúlnie");
				break;
			default:
				break;
			}
		}
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		List<String> countryList = null;
		countryList = EuropeCountryList.getEuropeCountryList();
		modelAndView.addObject("countryList", countryList);
		List<String> trailersList = null;
		trailersList = TrailersList.getTrailersList();
		modelAndView.addObject("trailersList", trailersList);
		modelAndView.addObject("goodForm", goodModelOrNull);
		return modelAndView;
	}

	@RequestMapping(value = "/newCargo", method = RequestMethod.POST)
	public ModelAndView newCargoPost(
			@ModelAttribute("goodForm") @Validated GoodModel goodModel,
			BindingResult result) {
		if (result.hasErrors()) {
			return newCargoGet(goodModel, 1);
		} else {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			int loginID = dao.getLoginID(auth.getName());
			dao.insertGood(goodModel, loginID);
			return newCargoGet(goodModel, 2);
		}

	}

	@RequestMapping(value = "/editCargo/{id}", method = RequestMethod.GET)
	public ModelAndView editCargoGet(@PathVariable int id,
			GoodModelForEdit goodModelForEditOrNull, Integer messageCodeOrNull) {
		ModelAndView modelAndView = new ModelAndView("editCargo");
		goodModelForEditOrNull = dao.getGoodModelForEdit(id);
		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc", "èle wype≥ni≥eú pola");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Edycja przebieg≥a pomyúlnie");
				break;
			default:
				break;
			}
		}
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		List<String> activityList = new ArrayList<String>();
		activityList.add("Tak");
		activityList.add("Nie");
		List<String> countryList = null;
		countryList = EuropeCountryList.getEuropeCountryList();
		modelAndView.addObject("countryList", countryList);
		List<String> trailersList = null;
		trailersList = TrailersList.getTrailersList();
		modelAndView.addObject("trailersList", trailersList);
		modelAndView.addObject("activityList", activityList);
		modelAndView.addObject("editGoodForm", goodModelForEditOrNull);
		return modelAndView;
	}

	@RequestMapping(value = "/editCargo/{id}", method = RequestMethod.POST)
	public ModelAndView editCargoPost(
			@PathVariable int id,
			@ModelAttribute("editGoodForm") @Validated GoodModelForEdit goodModelForEdit,
			BindingResult result) {
		if (result.hasErrors()) {
			return editCargoGet(id, goodModelForEdit, 1);
		} else {
			dao.editCargo(goodModelForEdit);
			return editCargoGet(id, goodModelForEdit, 2);
		}

	}

}
