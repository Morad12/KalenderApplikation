package dataBaseConnection;


import java.util.ArrayList;
import java.util.List;

import utilities.User;

public class test {

	public static void main(String[] args) {
		try {
			System.out.println(MySqlConnetion.getUser("lufy").toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
