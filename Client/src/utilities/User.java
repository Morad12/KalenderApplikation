package utilities;

import java.io.Serializable;

public class User implements Serializable {

	private String userName;
	private String email;
	private String nachname;
	private String vorname;
	private String passwort;
	
	public User() {
		super();
		this.userName = "";
		this.email = "";
		this.nachname = "";
		this.vorname = "";
		this.passwort = "";
	}
	
	public User(String userName, String email, String nachname, String vorname, String passwort) {
		super();
		this.userName = userName;
		this.email = email;
		this.nachname = nachname;
		this.vorname = vorname;
		this.passwort = passwort;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [getUserName()=" + getUserName() + ", getNachname()=" + getNachname() + ", getVorname()="
				+ getVorname() + ", getPasswort()=" + getPasswort() + ", getEmail()=" + getEmail() + "]";
	}
}
