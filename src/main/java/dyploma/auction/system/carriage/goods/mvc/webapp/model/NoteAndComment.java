package dyploma.auction.system.carriage.goods.mvc.webapp.model;

public class NoteAndComment {

	private int note;
	private String comment;
	
	public NoteAndComment(int note, String comment) {
		super();
		this.note = note;
		this.comment = comment;
	} 
	
	public NoteAndComment()
	{
		this(0,null);
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
