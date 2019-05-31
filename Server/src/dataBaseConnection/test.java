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
			
			
			
			
			

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}
	
	public boolean addTermin(Termin termin) throws RemoteException, Exception {		
		
		Termin null_test = MySqlConnetion.searchTermin(termin.getTerminId());
		if(null_test == null) {			
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
	
	
	public List<Termin> getMyTermine(String username) throws RemoteException, Exception{
		User user = MySqlConnetion.searchUser(username, "username");
		if(user == null)
			System.out.println("Exp");
		
		List<Termin> termine = MySqlConnetion.getTermineInhaber(username);
		return termine;
	}

}
