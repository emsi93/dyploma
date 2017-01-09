package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class CommentWithNote {

	
	private String login;
	private int idGood;
	private String good;
	private String comment;
	private String data;
	private int note;
	public CommentWithNote(String login, int idGood, String good,
			String comment, String data, int note) {
		super();
		this.login = login;
		this.idGood = idGood;
		this.good = good;
		this.comment = comment;
		this.data = data;
		this.note = note;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getIdGood() {
		return idGood;
	}
	public void setIdGood(int idGood) {
		this.idGood = idGood;
	}
	public String getGood() {
		return good;
	}
	public void setGood(String good) {
		this.good = good;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	
	
}
