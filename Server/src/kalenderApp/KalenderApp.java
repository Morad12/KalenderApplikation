package kalenderApp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utilities.News;
import utilities.Termin;
import utilities.User;

public interface KalenderApp extends Remote {
	
	public boolean creatKonto(User user) throws RemoteException;
	public User login(String userName, String passwort) throws RemoteException;
	public boolean logout(User user) throws RemoteException;
	public boolean updateKonto(User user) throws RemoteException;
	public boolean deleteKonto(User user) throws RemoteException;
	
	public boolean addTermin(Termin termin) throws RemoteException;
	public boolean deleteTermin(int terminId) throws RemoteException;
	public Termin updateTermin(int terminId) throws RemoteException;
	public List<Termin> searchSpan(Date von, Date bis) throws RemoteException;
	
	public boolean userEinladen(User user, Termin termin) throws RemoteException;
	public List<News> getNews(User user) throws RemoteException;
	public boolean deleteNews(int newsId) throws RemoteException;
		
}
