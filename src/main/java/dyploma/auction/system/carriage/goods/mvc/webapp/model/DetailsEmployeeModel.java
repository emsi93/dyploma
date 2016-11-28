package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class DetailsEmployeeModel {

	private int id;
	private String name;
	private String surname;
	private String phoneNumber;
	private String email;
	private String login;
	private String activity;
	private String role;
	
	public DetailsEmployeeModel(int id, String name, String surname,
			String phoneNumber, String email, String login, int activity,
			String role) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.login = login;
		if(activity==1)
			this.activity = "Tak";
		else
			this.activity = "Nie";
		this.role = role;
	}

	public DetailsEmployeeModel()
	{
		this(0,null,null,null,null,null,0,null);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
