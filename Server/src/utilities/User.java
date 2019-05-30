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
	
	public String toString() {		
		String ret;
		ret = "UserName: "+getUserName()+"; "
		+"Email: "+getEmail()+"; "
		+"Nachname: "+getNachname()+"; "
		+"Vorname: "+getVorname()+"; "
		+"passwort: "+getPasswort()+"\n";
		return ret;
	}

}
