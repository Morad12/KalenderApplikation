package clientRMI;


import java.util.Scanner;
import java.io.Console;
import kalenderApp.KalenderApp;
import utilities.*;

public class Menu {


	private static enum Login_Create{EXIT, LOGIN, CREATE};
	private static enum News_Choice{WEITER, ANNEHMEN, ABLEHNEN};
	private static enum Konto_Utilies{ZURUECK, UPDATE, DELETE}
	private static enum Konto_Manage{ZURUECK, PASSWORT, EMAIL, VORNAME, NACHNAME, ALLES};
	private static enum Logged_Utilities{EXIT, KONTO, TERMIN};
	private static enum Ja_Nein{JA, NEIN};
	private static User user;
	private static Scanner s = new Scanner(System.in);
	private static KalenderApp stub = null;
	
	Menu() {
		
	}
	Menu(KalenderApp stub){
		//user = new User();
		Menu.stub = stub;
	}
	
	public static User getUser() {
		return user;
	}
	public static void setUser(User user) {
		Menu.user = user;
	}
	
	public static void HauptMenu() {
		user = new User();
		
		try {
			if(Login_Menu())
				Menu_Logged();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static boolean Login_Menu(){
		
		Console console = System.console();
		int eingabe = 0;
		String userName = "";
		String password = "";
		char[] pwd = null;
		String vorName = "";
		String nachName = "";
		String email = "";
		Login_Create input = null;
		String confirm = null;
		News_Choice choice = null;
		Ja_Nein antwort = null;
		
		try {
			System.out.print("\nWollen Sie:\n"
					+ "1. Login.\n"
					+ "2. Konto erstellen.\n"
					+ "0. Abbrechen.\n"
					+ "Wählen Sie: ");
			eingabe = s.nextInt();
			
			
			while(eingabe < 0 || eingabe > 2){
				
				System.out.print("Falsche Eingabe! Wiederholen: ");
				eingabe = s.nextInt();
			}
			input = Login_Create.values()[eingabe];
			
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
						System.out.println("\nWillkomen "+user.getNachname()+", Sie sind eingeloggen.");
						System.out.print("Sie haben "+stub.getNewsRecipientList(user.getUserName()).size() + " Notification(s).\n"
								+ "Wollien Sie die zeigen?(ja/nein): ");
						confirm = s.next();
						while(!confirm.equalsIgnoreCase("ja") &&
								!confirm.equalsIgnoreCase("nein")){
							
							System.out.print("Falsche Eingabe! Wiederholen: ");
							confirm = s.next();
						}
						
						antwort = Ja_Nein.valueOf(confirm.toUpperCase());
						
						if(antwort == Ja_Nein.JA) {
							for(News n : stub.getNewsRecipientList(user.getUserName())) {
								System.out.println(n.toString());
							}
							
							System.out.println("\nWollen Sie:\n"
									+ "1. Annehmen.\n"
									+ "2. Ablehnen.\n"
									+ "0. Weiter.\n");
							eingabe = s.nextInt();
							while(eingabe < 0 || eingabe > 2){
								
								System.out.print("Falsche Eingabe! Wiederholen: ");
								eingabe = s.nextInt();
							}
							
							choice = News_Choice.values()[eingabe];
							
							switch(choice) {
							case ANNEHMEN:
								break;
							case ABLEHNEN:
								break;
							case WEITER:
								break;
							}
						}
						return true;
					}
					else {
						System.out.print("Das Login ist schiefgegangen! \nWollen Sie wieder versuchen(ja) oder abbrechen(nein)? : ");
						confirm = s.next();
						while(!confirm.equalsIgnoreCase("ja") &&
								!confirm.equalsIgnoreCase("nein")){
							
							System.out.print("Falsche Eingabe! Wiederholen: ");
							confirm = s.next();
						}
						antwort = Ja_Nein.valueOf(confirm.toUpperCase());
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
						System.out.println("\nWillkomen "+user.getNachname()+", Sie sind eingeloggen.");
						return true;
					}
					else {
						System.out.print("Die Erstellung des ist schiefgegangen! \nWollen Sie wieder versuchen(ja) oder abbrechen(nein)? : ");
						confirm = s.next();
						while(!confirm.equalsIgnoreCase("ja") &&
								!confirm.equalsIgnoreCase("nein")){
							
							System.out.print("Falsche Eingabe! Wiederholen: ");
							confirm = s.next();
						}
						antwort = Ja_Nein.valueOf(confirm.toUpperCase());
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
		int eingabe = 0;
		String confirm = null;
		Konto_Utilies input = null;
		Konto_Manage manage = null;
		Ja_Nein antwort = null;
		try {
			System.out.print("\nWollen Sie Ihr Konto: \n"
					+ "1. Bearbeiten.\n"
					+ "2. Löschen.\n"
					+ "0. Zurück.\n"
					+ "Wählen Sie: ");
			eingabe = s.nextInt();
			
			while(eingabe < 0 && eingabe > 2){
				
				System.out.print("Falsche Eingabe! Wiederholen: ");
				eingabe = s.nextInt();
			}
			input = Konto_Utilies.values()[eingabe];
			
			switch(input) {
			case UPDATE:
				System.out.print("\nWas Wollen Sie bearbeiten:\n"
						+ "1. Passwort.\n"
						+ "2. Email.\n"
						+ "3. Nachname.\n"
						+ "4. Vorname.\n"
						+ "5. Alles.\n"
						+ "0. Zuruck.\n"
						+ "Wählen Sie: ");
				eingabe = s.nextInt();
				
				while(eingabe < 0 || eingabe > 5){
					
					System.out.print("Falsche Eingabe! Wiederholen: ");
					eingabe = s.nextInt();
				}
					manage = Konto_Manage.values()[eingabe];
					
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
					confirm = s.next();
					while(!confirm.equalsIgnoreCase("ja") &&
							!confirm.equalsIgnoreCase("nein")){
						
						System.out.print("Falsche Eingabe! Wiederholen: ");
						confirm = s.next();
					}
					antwort = Ja_Nein.valueOf(confirm.toUpperCase());
					
					if(antwort == Ja_Nein.JA) {
						
						if(!passwort.isEmpty() && !email.isEmpty() && !nachName.isEmpty() && !vorName.isEmpty()) {
							user.setPasswort(passwort);
							user.setEmail(email);
							user.setNachname(nachName);
							user.setVorname(vorName);
							
							if(stub.updateKonto(user,"email") != null && stub.updateKonto(user,"passwort") != null && stub.updateKonto(user,"vorname") != null && stub.updateKonto(user,"nachname") != null) {
								System.out.println("User is updated!");
								passwort = "";
								email = "";
								nachName = "";
								vorName = "";
								Menu_Logged();
							}
							else{
								System.out.println("Update is failed");
								Konto_Menu();
							}
						}
						
						if(!passwort.isEmpty()){
							user.setPasswort(passwort);
							if(stub.updateKonto(user,"passwort") != null) {
								System.out.println("User is updated!");
								passwort = "";
								Menu_Logged();
							}
							else {
								System.out.println("Update is failed");
								Konto_Menu();
							}
						}
						
						if(!email.isEmpty()) {
							
							user.setEmail(email);
							
							if(stub.updateKonto(user,"email") != null) {
								System.out.println("User is updated!");
								email = "";
								Menu_Logged();
							}
							else{
								System.out.println("Update is failed");
								Konto_Menu();
							}
						}
						
						if(!nachName.isEmpty()) {
							user.setNachname(nachName);
							if(stub.updateKonto(user,"nachname") != null) {
								System.out.println("User is updated!");
								nachName = "";
								Menu_Logged();
							}
							else{
								System.out.println("Update is failed");
							Konto_Menu();
							}
						}
						
						if(!vorName.isEmpty()) {
							user.setVorname(vorName);
							if(stub.updateKonto(user,"vorname") != null) {
								System.out.println("User is updated!");
								vorName = "";
								Menu_Logged();
							}
							else{
								System.out.println("Update is failed");
								Konto_Menu();
							}
						}
					}
					else
						Konto_Menu();
					break;
				case DELETE:
					System.out.print("Sind Sie sicher, dass Sie Ihr Konto löschen wollen?(ja/nein): ");
					confirm = s.next();
					while(!confirm.equalsIgnoreCase("ja") &&
							!confirm.equalsIgnoreCase("nein")){
						
						System.out.print("Falsche Eingabe! Wiederholen: ");
						confirm = s.next();
					}
					antwort = Ja_Nein.valueOf(confirm.toUpperCase());
					
					if(antwort == Ja_Nein.JA) {
						if(stub.deleteKonto(user)) {
							System.out.println("Ihr Konto ist gelöscht");
							user = null;
							HauptMenu();
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
		int eingabe = 0;
		Logged_Utilities input = null;
		
		try {
			System.out.print("\nWollen Sie:\n"
					+ "1. Ihr Konto verwalten.\n"
					+ "2. Ihre Termine verwalten.\n"
					+ "0. Abbrechen.\n"
					+ "Wählen Sie: ");
			eingabe = s.nextInt();
			
			while(eingabe < 0 || eingabe > 2){
				
				System.out.print("Falsche Eingabe! Wiederholen: ");
				eingabe = s.nextInt();
			}
			input = Logged_Utilities.values()[eingabe];
		
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
