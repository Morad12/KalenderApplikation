package dataBaseConnection;


import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utilities.Termin;
import utilities.User;

public class test {

	public static void main(String[] args) {
		try {
			User user = new User("samira","samira@gmail,com","zamri","ahmed", "123456");
//			MySqlConnetion.insertUser(user);			
			/*User user1;
			user1 = MySqlConnetion.searchUser("lufy","email");			
			if (user1 == null) {
				user1=MySqlConnetion.searchUser("luf","username");
				System.out.println(user1);	
				if(user1 == null)
					System.out.println("leer");
			}*/
			
//			System.out.println(creatKonto(user));		
//			user.setNachname("najmi");
//			MySqlConnetion.updateUser(user, "nachname");			
//			MySqlConnetion.deleteUser("jhghk");
//			System.out.println(addTermin(termin));
//			getMyTermine("eren");
//			System.out.println(MySqlConnetion.getTerminList());
//			System.out.println(MySqlConnetion.getTermineInhaber("eren"));
			
			
//			System.out.println(MySqlConnetion.searchTerminTime("eren500", "20:15:14"));
			
//			System.out.println(getMyTermine("eren501"));
			
//			System.out.println(MySqlConnetion.getUserList());
			
//			java.util.Date uDate;
//			uDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2010-02-11 16:30:10");
			
//			Termin termin = new Termin("superman","Veranstaltung", uDate);
			
//			MySqlConnetion.insertTermin(termin);
			
//			System.out.println(MySqlConnetion.getTerminList());
			
			java.util.Date date1;
			date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2008-12-30 00:00:00");
			
			java.util.Date date2; 
			date2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-02-11 16:30:10");
			
			System.out.println(searchSpan(date1, date2, "superman"));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}
	
	public static boolean addTermin(Termin termin) throws RemoteException, Exception {				
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

	public boolean deleteTermin(int terminId) throws RemoteException, Exception {
		
		Termin null_test = MySqlConnetion.searchTermin(terminId);
		if(null_test != null) {			
			MySqlConnetion.deleteTermin(terminId);
			return true;
		}		
		return false;
	}

	
	public Termin updateTermin(int terminId, String where) throws RemoteException, Exception {
		
		Termin termin = MySqlConnetion.searchTermin(terminId);
		if(termin == null)
			System.out.println("Exception");
		MySqlConnetion.updateTermin(termin, where);	
		termin = MySqlConnetion.searchTermin(terminId);
		return termin;
	}
	
	
	public static List<Termin> getMyTermine(String username) throws RemoteException, Exception{
		User user = MySqlConnetion.searchUser(username, "username");
		if(user == null) {
			System.out.println("Exp");
			return null;
		}
		List<Termin> termine = MySqlConnetion.getTermineInhaber(username);
		return termine;
	}
	
	public static List<Termin> searchSpan(Date date_von, Date date_bis, String userName) throws RemoteException, Exception {
		List<Termin> termine = MySqlConnetion.getTermineInhaber(userName);
		List<Termin> termineSpan = new ArrayList<Termin>();
		
		for(Termin termin : termine) {
			if(termin.getDateTime().after(date_von) && termin.getDateTime().before(date_bis))
				termineSpan.add(termin);
		}	
		return termineSpan;
	}

}
