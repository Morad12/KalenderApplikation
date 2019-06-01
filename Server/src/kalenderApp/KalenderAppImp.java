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

		User user = MySqlConnetion.searchUser(termin.getTerminInhaber(), "username");
		if(user == null) {
			System.out.println("Exp");
			return false;
		}
		Termin termin1 = MySqlConnetion.searchTerminTime(termin.getTerminInhaber(), termin.getDateTime());
		if(termin1 == null) {			
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
		if(user == null) {
			System.out.println("Exp");
			return null;
		}
		List<Termin> termine = MySqlConnetion.getTermineInhaber(username);
		return termine;
	}

	@Override
	public List<Termin> searchSpan(Date date_von, Date date_bis, String terminInhaber) throws RemoteException, Exception {

		List<Termin> termine = MySqlConnetion.getTermineInhaber(terminInhaber);
		List<Termin> termineSpan = new ArrayList<Termin>();
		
		for(Termin termin : termine) {
			if(termin.getDateTime().after(date_von) && termin.getDateTime().before(date_bis))
				termineSpan.add(termin);
		}	
		return termineSpan;
	}

	@Override
	public boolean userEinladen(News news) throws Exception {
		
		User null_test = MySqlConnetion.searchUser(news.getRecipientUserName(), "username");
		if(null_test == null) {
				System.out.println("Exp");
				return false;
		}
		
		Termin termin = MySqlConnetion.searchTermin(news.getTerminId());
		if(termin == null) {
			System.out.println("Exp");
			return false;
		}

		MySqlConnetion.insertNews(news);
		return true;
}

	@Override
	public List<News> getNews(String username) throws Exception {
		return MySqlConnetion.getNewsList(username);		
	}

	@Override
	public void acceptNews(News news) throws RemoteException, Exception {		
				
		Termin termin = MySqlConnetion.searchTermin(news.getTerminId());
		Termin neuTermin = new Termin(news.getRecipientUserName(), termin.getTerminName(), termin.getDateTime());
		MySqlConnetion.insertTermin(neuTermin);		
	}

	@Override
	public void refuseNews(News news) throws RemoteException, Exception {
		MySqlConnetion.deleteNews(news.getNewsId());
	}
}
