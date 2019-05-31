package kalenderApp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataBaseConnection.MySqlConnetion;
import utilities.News;
import utilities.Termin;
import utilities.User;

public class KalenderAppImp extends UnicastRemoteObject implements KalenderApp {
	
	public KalenderAppImp() throws RemoteException {
		super();
	}

	@Override
	public boolean creatKonto(User user) throws RemoteException, Exception {
		
		User null_test = MySqlConnetion.searchUser(user.getUserName(), "username");
		if(null_test == null) {
			null_test = MySqlConnetion.searchUser(user.getEmail(), "email");
			if(null_test == null) {
				MySqlConnetion.insertUser(user);
				return true;
			}
		}
		return false;
	}

	@Override
	public User login(String usernameORemail, String passwort) throws RemoteException, Exception{
				
		User user = MySqlConnetion.searchUser(usernameORemail, "username");
		if(user == null) {
			user = MySqlConnetion.searchUser(usernameORemail, "email");
			if(user == null) {
				System.out.println("Exiption user nicht gefunden(spaeter)");
			}
		}
		if(user.getPasswort().equals(passwort)) {
			System.out.println("sie sind eingeloggen");
			return user;
		}
		else 
			System.out.println("passwort falsch !");
			
		return null;
	}

	@Override
	public boolean logout(User user) throws RemoteException, Exception {
		
		User null_test = MySqlConnetion.searchUser(user.getUserName(), "username");
		if(null_test == null) {
			null_test = MySqlConnetion.searchUser(user.getEmail(), "email");
			if(null_test == null) {				
				return false;
			}
		}
		
		return true;
	}

	@Override
	public User updateKonto(User user, String where) throws RemoteException, Exception {		
		
		User userReturn = MySqlConnetion.searchUser(user.getUserName(), "username");
		if(userReturn == null) {
			userReturn = MySqlConnetion.searchUser(user.getEmail(), "email");
			if(userReturn == null) {				
				System.out.println("Exeption");
			}
		}
		MySqlConnetion.updateUser(user, where);
		userReturn = MySqlConnetion.searchUser(user.getUserName(), "username");
		if(userReturn == null)
			userReturn = MySqlConnetion.searchUser(user.getEmail(), "email");
		return userReturn;
	}

	@Override
	public boolean deleteKonto(User user) throws RemoteException, Exception {
		
		User null_test = MySqlConnetion.searchUser(user.getUserName(), "username");
		if(null_test == null) {
			null_test = MySqlConnetion.searchUser(user.getEmail(), "email");
			if(null_test == null) {	
				System.out.println("Exception spaeter");
				return false;
			}
		}
		MySqlConnetion.deleteUser(user.getUserName());
		return true;
	}

	@Override
	public boolean addTermin(Termin termin) throws RemoteException, Exception {		
		
		Termin null_test = MySqlConnetion.searchTermin(termin.getTerminId());
		if(null_test == null) {			
			MySqlConnetion.insertTermin(termin);
			return true;
		}		
		return false;
	}

	@Override
	public boolean deleteTermin(int terminId) throws RemoteException, Exception {
		
		Termin null_test = MySqlConnetion.searchTermin(terminId);
		if(null_test != null) {			
			MySqlConnetion.deleteTermin(terminId);
			return true;
		}		
		return false;
	}

	@Override
	public Termin updateTermin(int terminId, String where) throws RemoteException, Exception {
		
		Termin termin = MySqlConnetion.searchTermin(terminId);
		if(termin == null)
			System.out.println("Exception");
		MySqlConnetion.updateTermin(termin, where);	
		termin = MySqlConnetion.searchTermin(terminId);
		return termin;
	}
	
	@Override
	public List<Termin> getMyTermine(String username) throws RemoteException, Exception{
		User user = MySqlConnetion.searchUser(username, "username");
		if(user == null)
			System.out.println("Exp");
		
		List<Termin> termine = MySqlConnetion.getTermineInhaber(username);
		return termine;
	}

	@Override
	public List<Termin> searchSpan(String date_von, String date_bis) throws RemoteException, Exception {
		/*List<Termin> termine = MySqlConnetion.getTerminList();
		List<Termin> termineReturn;
		
		for(Termin termin : termine) {
			termin.getTerminDate()
		}*/
		
		
		return null;
	}

	@Override
	public boolean userEinladen(News news) throws RemoteException {
//		String sender, String recipient, int terminId
		/*User null_test = MySqlConnetion.searchUser(recipient, "username");
		if(null_test == null) {
			null_test = MySqlConnetion.searchUser(recipient, "email");
			if(null_test == null) {
				System.out.println("Exp");
				return false;
			}
		}
		
		Termin termin = MySqlConnetion.searchTermin(terminId);
		if(termin == null) {
			MySqlConnetion.insertTermin(termin);
			MySqlConnetion.insertNews(sender, recipient, terminId);
			return true;
		}
		else 
			System.out.println("Exp");*/
		
		return false;
	}


	@Override
	public ArrayList<News> getNews(User user) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteNews(int newsId) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
