package dataBaseConnection;


import java.rmi.RemoteException;
import java.util.ArrayList;
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
			Termin termin = new Termin("eren501","2018-04-15","20:15:14","veranstaltung");
			System.out.println(addTermin(termin));
//			getMyTermine("eren");
//			System.out.println(MySqlConnetion.getTerminList());
//			System.out.println(MySqlConnetion.getTermineInhaber("eren"));
			
			
//			System.out.println(MySqlConnetion.searchTerminTime("eren500", "20:15:14"));
			
			
			

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}
	
	public static boolean addTermin(Termin termin) throws RemoteException, Exception {				
		Termin termin1 = MySqlConnetion.searchTerminTime(termin.getTerminInhaber(), termin.getTerminTime());
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
		if(user == null)
			System.out.println("Exp");
		
		List<Termin> termine = MySqlConnetion.getTermineInhaber(username);
		return termine;
	}

}
