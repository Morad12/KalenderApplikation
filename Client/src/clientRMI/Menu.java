package clientRMI;


import java.util.Scanner;
import java.io.Console;
import kalenderApp.KalenderApp;
import utilities.User;

public class Menu {


	private static enum Login_Create{LOGIN, CREATE, EXIT};
	private static enum Konto_Utilies{UPDATE, DELETE, ZURUECK};
	private static enum Konto_Manage{PASSWORT, EMAIL, VORNAME, NACHNAME, ALLES, ZURUECK};
	private static enum Logged_Utilities{KONTO, TERMIN, EXIT};
	private static enum Ja_Nein{JA, NEIN};
	private static User user;
	private static Scanner s = new Scanner(System.in);
	private static KalenderApp stub = null;
	
	Menu() {
		
	}
	Menu(KalenderApp stub){
		user = new User();
		Menu.stub = stub;
	}
	
	public static User getUser() {
		return user;
	}
	public static void setUser(User user) {
		Menu.user = user;
	}
	
	public static boolean Login_Menu(){
		
		Console console = System.console();
		String eingabe = "";
		String userName = "";
		String password = "";
		char[] pwd = null;
		String vorName = "";
		String nachName = "";
		String email = "";
		Login_Create input = null;
		Ja_Nein antwort = null;
		
		System.out.print("Wollen Sie sich loggen(login), ein Konto erstellen(create) oder Abbrechen(exit)? : ");
		eingabe = s.next();
		
		
		while(!eingabe.equalsIgnoreCase("login") &&
				!eingabe.equalsIgnoreCase("create") &&
				!eingabe.equalsIgnoreCase("exit")){
			
			System.out.print("Falsche Eingabe! Wiederholen: ");
			eingabe = s.next();
		}
		input = Login_Create.valueOf(eingabe.toUpperCase());
		try {
			switch(input) {
			case LOGIN:
				do {
					System.out.print("Geben Sie ihren Benutzernamen: ");
					userName = s.next();
					System.out.print("Geben Sie ihr Passwort: ");
					if(console == null) {
						password = s.next();
						user = stub.login(userName, password);
					}
					else {
						pwd = console.readPassword();
						user = stub.login(userName, String.valueOf(pwd));
					}
					
					if(user != null) {
						System.out.println("Willkomen "+user.getNachname()+", Sie sind eingeloggen.");
						return true;
					}
					else {
						System.out.print("Das Login ist schiefgegangen! \nWollen Sie wieder versuchen(ja) oder abbrechen(nein)? : ");
						eingabe = s.next();
						while(!eingabe.equalsIgnoreCase("ja") &&
								!eingabe.equalsIgnoreCase("nein")){
							
							System.out.print("Falsche Eingabe! Wiederholen: ");
							eingabe = s.next();
						}
						antwort = Ja_Nein.valueOf(eingabe.toUpperCase());
					}
				}while(user == null && antwort != Ja_Nein.NEIN);
				
				if(antwort == Ja_Nein.NEIN) {
					System.out.println("Auf wiedersehen!");
					System.exit(0);	
				}
				
				break;
			case CREATE:
				boolean created = false;
				do {
					System.out.print("Geben Sie ihren Benutzernamen: ");
					userName = s.next();
					System.out.print("Geben Sie ihr Passwort: ");
					if(console != null) {
						pwd = console.readPassword();
						password = String.valueOf(pwd);
					}
					else {
						password = s.next();
					}
					System.out.print("Geben Sie ihren Nachname: ");
					nachName = s.next();
					System.out.print("Geben Sie ihren Vorname: ");
					vorName = s.next();
					System.out.print("Geben Sie ihr Email: ");
					email = s.next();
					user = new User(userName, email, nachName, vorName, password);
					created = stub.creatKonto(user);
					if(created) {
						System.out.println("Das Konto is Created!");
						System.out.println("Willkomen "+user.getNachname()+", Sie sind eingeloggen.");
						return true;
					}
					else {
						System.out.print("Die Erstellung des ist schiefgegangen! \nWollen Sie wieder versuchen(ja) oder abbrechen(nein)? : ");
						eingabe = s.next();
						while(!eingabe.equalsIgnoreCase("ja") &&
								!eingabe.equalsIgnoreCase("nein")){
							
							System.out.print("Falsche Eingabe! Wiederholen: ");
							eingabe = s.next();
						}
						antwort = Ja_Nein.valueOf(eingabe.toUpperCase());
					}
				
				}while(!created && antwort != Ja_Nein.NEIN);
				
				if(antwort == Ja_Nein.NEIN) {
					System.out.println("Auf wiedersehen!");
					System.exit(0);
				}
				
				break;
			case EXIT:
				System.out.println("Auf wiedersehen!");
				System.exit(0);
				break;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}

	public static void Konto_Menu() {
		
		Console console = System.console();
		char[] pwd = null;
		String passwort = "";
		String nachName = "";
		String vorName = "";
		String email = "";
		String eingabe = "";
		Konto_Utilies input = null;
		Konto_Manage manage = null;
		Ja_Nein antwort = null;
		
		System.out.print("Wollen Sie Ihr Konto bearbeiten(update), löschen(delete) oder zurück(zurueck): ");
		eingabe = s.next();
		
		while(!eingabe.equalsIgnoreCase("update") &&
				!eingabe.equalsIgnoreCase("delete") &&
				!eingabe.equalsIgnoreCase("zurueck")){
			
			System.out.print("Falsche Eingabe! Wiederholen: ");
			eingabe = s.next();
		}
		input = Konto_Utilies.valueOf(eingabe.toUpperCase());
		try {
			switch(input) {
			case UPDATE:
				System.out.print("Wollen Sie Passwort(passwort), Email(email), Nachname(nachname), Vorname(vorname), alles(alles) bearbeiten oder zurueck?: ");
				eingabe = s.next();
				
				while(!eingabe.equalsIgnoreCase("passwort") &&
						!eingabe.equalsIgnoreCase("email") &&
						!eingabe.equalsIgnoreCase("nachname") &&
						!eingabe.equalsIgnoreCase("vorname") &&
						!eingabe.equalsIgnoreCase("alles") &&
						!eingabe.equalsIgnoreCase("zurueck")){
					
					System.out.print("Falsche Eingabe! Wiederholen: ");
					eingabe = s.next();
				}
					manage = Konto_Manage.valueOf(eingabe.toUpperCase());
					
					switch(manage) {
					case PASSWORT:
						System.out.print("Geben Sie ihr neues Passwort: ");
						
						if(console == null) {
							passwort = s.next();
						}
						else {
							pwd = console.readPassword();
							passwort = String.valueOf(pwd);
						}
						break;
					case EMAIL:
						System.out.print("Geben Sie ihr neues Email: ");
						email = s.next();
						break;
					case NACHNAME:
						System.out.print("Geben Sie ihren neuen Nachname: ");
						nachName = s.next();
						break;
					case VORNAME:
						System.out.print("Geben Sie ihren neuen Vorname: ");
						vorName = s.next();
						break;
					case ALLES:
						System.out.print("Geben Sie ihr neues Passwort: ");
						if(console != null) {
							pwd = console.readPassword();
							passwort = String.valueOf(pwd);
						}
						else {
							passwort = s.next();
						}
						System.out.print("Geben Sie ihren neuen Nachname: ");
						nachName = s.next();
						System.out.print("Geben Sie ihren neuen Vorname: ");
						vorName = s.next();
						System.out.print("Geben Sie ihr neues Email: ");
						email = s.next();
						break;
					case ZURUECK:
						Menu_Logged();
						break;
					}
					
					System.out.print("Sind Sie sicher, dass Sie Ihr Konto bearbeiten wollen?(ja/nein): ");
					eingabe = s.next();
					while(!eingabe.equalsIgnoreCase("ja") &&
							!eingabe.equalsIgnoreCase("nein")){
						
						System.out.print("Falsche Eingabe! Wiederholen: ");
						eingabe = s.next();
					}
					antwort = Ja_Nein.valueOf(eingabe.toUpperCase());
					
					if(antwort == Ja_Nein.JA) {
						
						if(!passwort.isEmpty()){
							user.setPasswort(passwort);
							if(stub.updateKonto(user,"passwort") != null) {
								System.out.println("User is updated!");
								Konto_Menu();;
						}
						else
							Konto_Menu();;
						}
						
						if(!email.isEmpty()) {
							System.out.println("Before: " +user.getUserName()+", "+user.getPasswort() + ", " + user.getNachname() + ", "+user.getVorname() + ", "+user.getEmail());
							user.setEmail(email);
							System.out.println("Before: " +user.getUserName()+", "+user.getPasswort() + ", " + user.getNachname() + ", "+user.getVorname() + ", "+user.getEmail());
							
							if(stub.updateKonto(user,"email") != null) {
								System.out.println("User is updated!");
								Konto_Menu();
							}
							else
								Konto_Menu();
						}
						
						if(!nachName.isEmpty()) {
							System.out.println("Before: " +user.getUserName()+", "+user.getPasswort() + ", " + user.getNachname() + ", "+user.getVorname() + ", "+user.getEmail());
							user.setNachname(nachName);
							System.out.println("Before: " +user.getUserName()+", "+user.getPasswort() + ", " + user.getNachname() + ", "+user.getVorname() + ", "+user.getEmail());
						
							if(stub.updateKonto(user,"nachname") != null) {
								System.out.println("User is updated!");
								Konto_Menu();
							}
						else
							Konto_Menu();
						}
						
						if(!vorName.isEmpty()) {
							user.setVorname(vorName);
							if(stub.updateKonto(user,"vorname") != null) {
								System.out.println("User is updated!");
								Konto_Menu();
							}
						else
							Konto_Menu();
						}
						
						if(!passwort.isEmpty() && !email.isEmpty() && !nachName.isEmpty() && !vorName.isEmpty()) {
							user.setPasswort(passwort);
							user.setEmail(email);
							user.setNachname(nachName);
							user.setVorname(vorName);
							
							if(stub.updateKonto(user,"email") != null && stub.updateKonto(user,"passwort") != null && stub.updateKonto(user,"vorname") != null && stub.updateKonto(user,"nachname") != null) {
								System.out.println("User is updated!");
								Konto_Menu();
							}
							else
								Konto_Menu();
						}
						else
							Konto_Menu();
					}
					else
						Konto_Menu();
					break;
				case DELETE:
					System.out.print("Sind Sie sicher, dass Sie Ihr Konto löschen wollen?: ");
					eingabe = s.next();
					while(!eingabe.equalsIgnoreCase("ja") &&
							!eingabe.equalsIgnoreCase("nein")){
						
						System.out.print("Falsche Eingabe! Wiederholen: ");
						eingabe = s.next();
					}
					antwort = Ja_Nein.valueOf(eingabe.toUpperCase());
					if(antwort == Ja_Nein.JA) {
						if(stub.deleteKonto(user)) {
							System.out.println("Ihr Konto ist gelöscht");
							System.out.println("Username: "+user.getUserName());
							Login_Menu();
						}
						else
							System.out.println("Das Löschen ist schiefgegangen");
						}
					else
						Menu_Logged();
					break;
				case ZURUECK:
					Menu_Logged();
					break;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return;
	}
	
	public static void Termin_Menu() {
		
		return;
	}

	public static void Menu_Logged() {
		String eingabe = null;
		Logged_Utilities input = null;
		
		System.out.print("Wollen Sie Ihr Konto(konto), Ihre Termine(termin) bearbeiten oder Abbrechen(exit)?: ");
		eingabe = s.next();
		
		while(!eingabe.equalsIgnoreCase("konto") &&
				!eingabe.equalsIgnoreCase("termin") &&
				!eingabe.equalsIgnoreCase("exit")){
			
			System.out.print("Falsche Eingabe! Wiederholen: ");
			eingabe = s.next();
		}
		input = Logged_Utilities.valueOf(eingabe.toUpperCase());
		try {
			switch(input) {
			case KONTO:
				Konto_Menu();
				break;
			case TERMIN:
				break;
			case EXIT:
				System.out.println("Auf wiedersehen!");
				System.exit(0);
				break;
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
