package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class MailModel {

	private String mailAddress;
	private String link;

	public MailModel(String mailAddress, String link) {
		super();
		this.mailAddress = mailAddress;
		this.link = link;
	}

	public MailModel() {
		this(null, null);
	}
	
	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
}
