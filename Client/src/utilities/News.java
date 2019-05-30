package utilities;

import java.io.Serializable;

public class News implements Serializable {

	private User sender;
	private User recepient;
	private Termin termin;
	
	public News() {
		super();
		sender = null;
		recepient = null;
		termin = null;	
	}
	
	public User getSender() {
		return sender;
	}
	
	public void setSender(User sender) {
		this.sender = sender;
	}
	
	public User getRecepient() {
		return recepient;
	}
	
	public void setRecepient(User recepient) {
		this.recepient = recepient;
	}
	
	public Termin getTermin() {
		return termin;
	}
	
	public void setTermin(Termin termin) {
		this.termin = termin;
	}
}