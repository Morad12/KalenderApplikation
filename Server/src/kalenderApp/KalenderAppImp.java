package kalenderApp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import utilities.News;
import utilities.Termin;
import utilities.User;

public class KalenderAppImp extends UnicastRemoteObject implements KalenderApp {

	private static User user;
	private static Termin termin;
	
	public KalenderAppImp() throws RemoteException {
		super();
	}

	@Override
	public boolean creatKonto(User user) throws RemoteException {
		return false;
	}

	@Override
	public User login(String userName, String passwort) throws RemoteException {
		return null;
	}

	@Override
	public boolean logout(User user) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateKonto(User user) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteKonto(User user) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addTermin(Termin termin) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTermin(int terminId) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Termin searchTermin(int terminId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Termin updateTermin(int terminId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Termin> searchSpan(Date von, Date bis) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean userEinladen(User user, Termin termin) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User searchUser(String userName, ArrayList<User> tab) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
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
