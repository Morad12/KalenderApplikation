package utilities;

import java.io.Serializable;

public class User implements Serializable {

	private String userName;
	private String nachname;
	private String vorname;
	private String passwort;
	
	public User() {
		super();
		userName = "";
		nachname = "";
		vorname = "";
		passwort = "";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

}
