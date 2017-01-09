package dyploma.auction.system.carriage.goods.mvc.webapp.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import dyploma.auction.system.carriage.goods.mvc.webapp.dao.WebappDAOInterface;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.CommentWithNote;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.CompanyModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.DetailsEmployeeModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.DetailsGoodModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.EmployeeModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.FinishedTransaction;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.GoodData;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.GoodModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.GoodModelForEdit;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.GoodModelForList;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.MailModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.NewPrice;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.NoteAndComment;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.ProfileModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.PurchaseOffer;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.RegisterModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.model.UserModel;
import dyploma.auction.system.carriage.goods.mvc.webapp.validator.EditCompanyFormValidator;
import dyploma.auction.system.carriage.goods.mvc.webapp.validator.EditEmployeeFormValidator;
import dyploma.auction.system.carriage.goods.mvc.webapp.validator.EditGoodFormValidator;
import dyploma.auction.system.carriage.goods.mvc.webapp.validator.EditProfileFormValidator;
import dyploma.auction.system.carriage.goods.mvc.webapp.validator.GoodFormValidator;
import dyploma.auction.system.carriage.goods.mvc.webapp.validator.MailFormValidator;
import dyploma.auction.system.carriage.goods.mvc.webapp.validator.NewPriceFormValidator;
import dyploma.auction.system.carriage.goods.mvc.webapp.validator.NoteAndCommentFormValidator;
import dyploma.auction.system.carriage.goods.mvc.webapp.validator.RegisterFormValidator;
import dyploma.auction.system.carriage.goods.mvc.webapp.validator.UserFormValidator;

@Controller
public class WebappController {

	private static final String LOGIN_IS_USED = "Login jest juø uøyty.";
	private static final String EMAIL_IS_USED = "Adres email jest juø uøyty.";
	private static final String NIP_IS_USED = "Ten NIP jest juø uøyty.";
	private static final String NIP_WRONG_SIZE = "NIP jest za d≥ugi.";
	private static final String EMAIL_TOO_LONG = "Adres email jest za d≥ugi.";
	private static final String LOGIN_TOO_LONG = "Login jest za d≥ugi.";

	private final String emailWebapp = "przewoz.towarow.dyploma@gmail.com";
	private final String passwordEmailWebapp = "przewoztowarow";

	@Autowired
	private WebappDAOInterface dao;

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

	@Autowired
	private MailFormValidator mailFormValidator;

	@InitBinder("mailForm")
	protected void initMailFormValidator(WebDataBinder binder) {
		binder.setValidator(mailFormValidator);
	}
	
	@Autowired
	private NoteAndCommentFormValidator noteAndCommentFormValidator;
	
	@InitBinder("noteCommentForm")
	protected void initNoteAndCommentFormValidator(WebDataBinder binder) {
		binder.setValidator(noteAndCommentFormValidator);
	}

	@RequestMapping("/detailsEmployee/{id}")
	public ModelAndView detailsEmployee(@PathVariable int id)
			throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
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
	public ModelAndView detailsCargo(@PathVariable int id)
			throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
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
		List<PurchaseOffer> purchaseOffer = dao.getPurchaseOffer(id);
		modelAndView.addObject("purchaseOffer", purchaseOffer);
		return modelAndView;
	}

	@RequestMapping(value = "/editEmployee/{id}", method = RequestMethod.GET)
	public ModelAndView editEmployeeGet(@PathVariable int id,
			DetailsEmployeeModel detailsEmployeeModelOrNull,
			Integer messageCodeOrNull) throws DataAccessException,
			ParseException {
		dao.checkDateOfGoods();
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
		if (typeOfCompany == 1) {

			rolesList.add("ROLE_ADMIN_SHIPPER");
			rolesList.add("ROLE_USER_SHIPPER");
		} else {
			rolesList.add("ROLE_ADMIN_CARRIER");
			rolesList.add("ROLE_USER_CARRIER");
		}

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
			BindingResult result) throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
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
			Integer messageCodeOrNull) throws DataAccessException,
			ParseException {
		dao.checkDateOfGoods();
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
		countryList = dao.getCountries();
		modelAndView.addObject("countryList", countryList);
		modelAndView.addObject("registerForm", registerModelOrNull);
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerPost(
			@ModelAttribute("registerForm") @Validated RegisterModel registerModel,
			BindingResult result) throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		if (result.hasErrors())
			return registerGet(registerModel, 1);
		else {
			dao.registerCompany(registerModel,
					Integer.parseInt(registerModel.getTypeOfCompany()));
			return registerGet(registerModel, 2);
		}
	}

	@RequestMapping(value = { "index", "/" })
	public ModelAndView index() throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}

	@RequestMapping("/charts")
	public ModelAndView charts() throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("charts");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());

		List<GoodData> goodData = dao.getGoodData(companyID);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < goodData.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", goodData.get(i).getName());
			jsonObject.put("actualPrice", goodData.get(i).getActualPrice());
			jsonArray.put(jsonObject);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("GoodList", jsonArray);
		modelAndView.addObject("goodData", jsonObject);
		modelAndView.addObject("size", goodData.size());
		return modelAndView;
	}

	@RequestMapping("/about")
	public ModelAndView about() throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("about");
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	@RequestMapping("/menu")
	public ModelAndView menu() throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
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
	
	@RequestMapping("/commentList")
	public ModelAndView commentList() throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("commentList");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		List<CommentWithNote> commentsWithNotes = dao.getCommentsWithNotes(companyID);
		modelAndView.addObject("commentsWithNotes", commentsWithNotes);
		return modelAndView;
	}

	@RequestMapping("/finishedTransaction")
	public ModelAndView finishedTransaction() throws DataAccessException,
			ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("finishedTransaction");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		List<FinishedTransaction> finishedTransactions = dao
				.getFinishedTransaction(companyID, typeOfCompany);

		JSONArray jsonA = new JSONArray();
		for (int i = 0; i < finishedTransactions.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", finishedTransactions.get(i).getId());
			jsonObject.put("idGood", finishedTransactions.get(i).getIdGood());
			jsonObject.put("title", finishedTransactions.get(i).getGoodName());
			jsonObject.put("to", finishedTransactions.get(i).getToCountry()
					+ ", " + finishedTransactions.get(i).getToCity());
			jsonObject.put("login", finishedTransactions.get(i).getLogin());
			jsonObject.put("dateTransaction", finishedTransactions.get(i)
					.getDateTransaction());
			jsonObject.put("price", finishedTransactions.get(i).getPrice());
			jsonA.put(jsonObject);

		}
		modelAndView.addObject("jsonA", jsonA);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		return modelAndView;
	}

	@RequestMapping("/aboutCompany/{id}")
	public ModelAndView aboutCompany(@PathVariable int id)
			throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("aboutCompany");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		CompanyModel companyModel = dao.getInfoCompany(id);
		modelAndView.addObject("companyModel", companyModel);
		List<CommentWithNote> commentsWithNotes = dao.getCommentsWithNotes(id);
		modelAndView.addObject("commentsWithNotes", commentsWithNotes);
		return modelAndView;
	}

	@RequestMapping("/searchCargo")
	public ModelAndView searchCargo() throws DataAccessException,
			ParseException {
		dao.checkDateOfGoods();
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
		JSONArray jsonA = new JSONArray();
		for (int i = 0; i < goodsList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", goodsList.get(i).getId());
			jsonObject.put("title", goodsList.get(i).getTitle());
			jsonObject.put("from", goodsList.get(i).getFromCountry() + ", "
					+ goodsList.get(i).getFromCity());
			jsonObject.put("to", goodsList.get(i).getToCountry() + ", "
					+ goodsList.get(i).getToCity());
			jsonObject.put("dateAdding", goodsList.get(i).getDateAdding());
			jsonObject
					.put("dateDelivery", goodsList.get(i).getDateOfDelivery());
			jsonObject.put("prices", goodsList.get(i).getMaxPrice() + "z≥/"
					+ goodsList.get(i).getActualPrice() + "z≥");
			jsonA.put(jsonObject);

		}
		modelAndView.addObject("goodsList", goodsList);
		modelAndView.addObject("jsonA", jsonA);
		return modelAndView;
	}

	@RequestMapping(value = "/cargo/{id}", method = RequestMethod.GET)
	public ModelAndView cargoGet(@PathVariable int id, NewPrice newPriceOrNull,
			Integer messageCodeOrNull) throws DataAccessException,
			ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("cargo");

		if (newPriceOrNull == null) {
			newPriceOrNull = new NewPrice(0, null, null, null);
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
		List<PurchaseOffer> purchaseOffer = dao.getPurchaseOffer(id);
		modelAndView.addObject("purchaseOffer", purchaseOffer);
		return modelAndView;
	}

	@RequestMapping(value = "/cargo/{id}", method = RequestMethod.POST)
	public ModelAndView cargoPost(@PathVariable int id,
			@ModelAttribute("priceForm") @Validated NewPrice newPrice,
			BindingResult result) throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		if (result.hasErrors()) {
			return cargoGet(id, newPrice, 1);
		} else {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			int userID = dao.getUserIDByLogin(auth.getName());
			dao.updatePrice(id, newPrice.getPrice(), userID);
			return cargoGet(id, newPrice, 2);
		}

	}

	@RequestMapping("/cargosList")
	public ModelAndView cargosList() throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
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
		JSONArray jsonA = new JSONArray();
		for (int i = 0; i < goodsList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", goodsList.get(i).getId());
			jsonObject.put("title", goodsList.get(i).getTitle());
			jsonObject.put("from", goodsList.get(i).getFromCountry() + ", "
					+ goodsList.get(i).getFromCity());
			jsonObject.put("to", goodsList.get(i).getToCountry() + ", "
					+ goodsList.get(i).getToCity());
			jsonObject.put("dateAdding", goodsList.get(i).getDateAdding());
			jsonObject
					.put("dateDelivery", goodsList.get(i).getDateOfDelivery());
			jsonObject.put("prices", goodsList.get(i).getMaxPrice() + "z≥/"
					+ goodsList.get(i).getActualPrice() + "z≥");
			jsonA.put(jsonObject);

		}
		modelAndView.addObject("jsonA", jsonA);
		return modelAndView;
	}

	@RequestMapping("/errorLogin")
	public ModelAndView errorLogin() throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("errorLogin");
		return modelAndView;
	}

	@RequestMapping("/employeesList")
	public ModelAndView employeesList() throws DataAccessException,
			ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("employeesList");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Object[] role = auth.getAuthorities().toArray();
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyID = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyID);
		List<EmployeeModel> employeeList = dao.getEmployeesList(companyID);
		JSONArray jsonA = new JSONArray();
		for (int i = 0; i < employeeList.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", employeeList.get(i).getId());
			jsonObject.put("name", employeeList.get(i).getName());
			jsonObject.put("surname", employeeList.get(i).getSurname());
			jsonObject.put("phoneNumber", employeeList.get(i).getPhoneNumber());
			jsonObject.put("email", employeeList.get(i).getEmail());
			jsonA.put(jsonObject);
		}
		modelAndView.addObject("role", role[0].toString());
		modelAndView.addObject("username", auth.getName());
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		modelAndView.addObject("jsonA", jsonA);
		return modelAndView;
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUserGet(UserModel userModelOrNull,
			Integer messageCodeOrNull) throws DataAccessException,
			ParseException {
		dao.checkDateOfGoods();
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
			BindingResult result) throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		if (result.hasErrors())
			return newUserGet(userModel, 1);
		else {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			int userID = dao.getUserIDByLogin(auth.getName());
			int companyID = dao.getCompanyID(userID);
			int typeCompany = dao.getTypeOfCompany(companyID);
			dao.registerUser(userModel, companyID, typeCompany); // 1 -
																	// companyID
			return newUserGet(userModel, 2);
		}
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfileUserGet(ProfileModel profileModelOrNull,
			Integer messageCodeOrNull) throws DataAccessException,
			ParseException {
		dao.checkDateOfGoods();
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
			BindingResult result) throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
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
			Integer messageCodeOrNull) throws DataAccessException,
			ParseException {
		dao.checkDateOfGoods();
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
		countryList = dao.getCountries();
		modelAndView.addObject("countryList", countryList);
		modelAndView.addObject("companyForm", companyModel);
		return modelAndView;
	}

	@RequestMapping(value = "/editCompany", method = RequestMethod.POST)
	public ModelAndView editCompanyPost(
			@ModelAttribute("companyForm") @Validated CompanyModel companyModel,
			BindingResult result) throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
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
			Integer messageOrNull) throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("newCargo");
		if (goodModelOrNull == null) {
			goodModelOrNull = new GoodModel(null, null, null, null, null, null,
					null, null, null, null, null, null, null, null);
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
		List<String> countryList = dao.getCountries();
		modelAndView.addObject("countryList", countryList);
		List<String> trailersList = dao.getTrailers();
		modelAndView.addObject("trailersList", trailersList);
		List<String> typesGoods = dao.getTypesOfGoods();
		modelAndView.addObject("typesGoods", typesGoods);
		modelAndView.addObject("goodForm", goodModelOrNull);
		return modelAndView;
	}

	@RequestMapping(value = "/newCargo", method = RequestMethod.POST)
	public ModelAndView newCargoPost(
			@ModelAttribute("goodForm") @Validated GoodModel goodModel,
			BindingResult result) throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
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
			GoodModelForEdit goodModelForEditOrNull, Integer messageCodeOrNull)
			throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
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
		List<String> countryList = dao.getCountries();
		modelAndView.addObject("countryList", countryList);
		List<String> trailersList = dao.getTrailers();
		modelAndView.addObject("trailersList", trailersList);
		List<String> typesGoods = dao.getTypesOfGoods();
		modelAndView.addObject("typesGoods", typesGoods);
		modelAndView.addObject("activityList", activityList);
		modelAndView.addObject("editGoodForm", goodModelForEditOrNull);
		return modelAndView;
	}

	@RequestMapping(value = "/editCargo/{id}", method = RequestMethod.POST)
	public ModelAndView editCargoPost(
			@PathVariable int id,
			@ModelAttribute("editGoodForm") @Validated GoodModelForEdit goodModelForEdit,
			BindingResult result) throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		if (result.hasErrors()) {
			return editCargoGet(id, goodModelForEdit, 1);
		} else {
			dao.editCargo(goodModelForEdit);
			return editCargoGet(id, goodModelForEdit, 2);
		}

	}

	@RequestMapping(value = "/detailsTransaction/{id}", method = RequestMethod.GET)
	public ModelAndView detailsTransactionGet(@PathVariable int id,
			MailModel mailModelOrNull, Integer messageCodeOrNull)
			throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("detailsTransaction");

		if (mailModelOrNull == null) {
			mailModelOrNull = new MailModel(null, null);
		}

		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc",
						"Wys≥anie trasy nie powiod≥o siÍ.");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Trasa zosta≥a wys≥ana na podany adres e-mail.");
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
		PurchaseOffer purchaseOffer = dao.getPurchaseOffer(id, 1);
		modelAndView.addObject("loginCustomer", purchaseOffer.getLogin());
		modelAndView.addObject("dataTransaction", purchaseOffer.getData());
		modelAndView.addObject("detailsCargoModel", detailsGoodModel);
		modelAndView.addObject("mailForm", mailModelOrNull);
		return modelAndView;
	}

	@RequestMapping(value = "/detailsTransaction/{id}", method = RequestMethod.POST)
	public ModelAndView detailsTransactionPost(@PathVariable int id,
			@ModelAttribute("mailForm") @Validated MailModel mailModel,
			BindingResult result) throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		if (result.hasErrors()) {
			return detailsTransactionGet(id, mailModel, 1);
		} else {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			sendMail(mailModel, id, auth.getName());
			return detailsTransactionGet(id, mailModel, 2);
		}

	}

	@RequestMapping("/detailsTransaction2/{id}")
	public ModelAndView detailsTransaction2(@PathVariable int id)
			throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("detailsTransaction2");
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
		PurchaseOffer purchaseOffer = dao.getPurchaseOffer(id, 1);
		modelAndView.addObject("loginCustomer", purchaseOffer.getLogin());
		modelAndView.addObject("dataTransaction", purchaseOffer.getData());
		modelAndView.addObject("detailsCargoModel", detailsGoodModel);
		CompanyModel companyModel = dao.getCompanyModel(dao.getLoginID(purchaseOffer.getLogin()));
		modelAndView.addObject("companyModel",companyModel);
		return modelAndView;
	}
	
	
	public void sendMail(MailModel mailModel, int idGood, String userName) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(emailWebapp,
								passwordEmailWebapp);
					}
				});

		try {

			String link = mailModel.getLink().replaceAll("\\s", "%20");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailWebapp));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mailModel.getMailAddress()));
			message.setSubject("Trasa towaru o numerze: " + idGood);
			message.setText("Trasa towaru o numerze: " + idGood + "," + "\n\n "
					+ link + "\n\n " + userName + ".");
			Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value = "/newNote/{id}", method = RequestMethod.GET)
	public ModelAndView newNoteGet(@PathVariable int id,
			NoteAndComment noteAndCommentOrNull, Integer messageCodeOrNull)
			throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		ModelAndView modelAndView = new ModelAndView("newNote");

		if (noteAndCommentOrNull == null) {
			noteAndCommentOrNull = new NoteAndComment(0, null);
		}

		if (messageCodeOrNull != null) {
			switch (messageCodeOrNull) {
			case 1:
				modelAndView.addObject("wiadomosc",
						"Wystawienie komentarza nie powiod≥o siÍ.");
				break;
			case 2:
				modelAndView.addObject("wiadomosc",
						"Komentarz zosta≥ wystawiony pomyúlnie.");
				break;
			default:
				break;
			}
		}
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		modelAndView.addObject("username", auth.getName());
		int userID = dao.getUserIDByLogin(auth.getName());
		int companyId = dao.getCompanyID(userID);
		int typeOfCompany = dao.getTypeOfCompany(companyId);
		modelAndView.addObject("typeOfCompany", String.valueOf(typeOfCompany));
		Object[] role = auth.getAuthorities().toArray();
		modelAndView.addObject("role", role[0].toString());
		modelAndView.addObject("noteCommentForm", noteAndCommentOrNull);
		List<Integer> notes = new ArrayList<Integer>();
		notes.add(1);
		notes.add(2);
		notes.add(3);
		notes.add(4);
		notes.add(5);
		modelAndView.addObject("notes", notes);
		modelAndView.addObject("goodID",id);
		int companyIDByGoodID = dao.getCompanyID(id, typeOfCompany);
		String	companyName = dao.getCompanyName(companyIDByGoodID);
		modelAndView.addObject("companyName",companyName);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/newNote/{id}", method = RequestMethod.POST)
	public ModelAndView newNotePost(@PathVariable int id,
			@ModelAttribute("noteCommentForm") @Validated NoteAndComment noteAndComment,
			BindingResult result) throws DataAccessException, ParseException {
		dao.checkDateOfGoods();
		if (result.hasErrors()) {
			return newNoteGet(id, noteAndComment, 1);
		} else {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			int userID = dao.getUserIDByLogin(auth.getName());
			int companyId = dao.getCompanyID(userID);
			int typeOfCompany = dao.getTypeOfCompany(companyId);
			int companyID2 = dao.getCompanyID(id,typeOfCompany);
			dao.insertNoteComment(companyID2,userID,id,noteAndComment);
			return newNoteGet(id, noteAndComment, 2);
		}

	}
	
	
	
	
}
