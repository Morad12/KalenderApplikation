package dataBaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import utilities.News;
import utilities.Termin;
import utilities.User;

public class MySqlConnetion {
	
	public static List<User> getUserList() throws Exception  {
		List<User> users = new ArrayList<User>();
		User user = new User();
		Connection conn = getconnection();
		PreparedStatement stam = conn.prepareStatement("SELECT * FROM user");
		ResultSet res = stam.executeQuery();
		while(res.next()) {
			user.setUserName(res.getString("username"));
			user.setEmail(res.getString("email"));
			user.setNachname(res.getString("nachname"));
			user.setVorname(res.getString("vorname"));
			user.setPasswort(res.getString("passwort"));
			users.add(user);
		}	
		conn.close();
		return users;
	}
	
	
	public static List<Termin> getTerminList() throws Exception {
		List<Termin> termine = new ArrayList<Termin>();
		Termin termin = new Termin();
		Connection conn = getconnection();
		PreparedStatement stam = conn.prepareStatement("SELECT * FROM termin");
		ResultSet res = stam.executeQuery();
		while(res.next()) {
			termin.setTerminId(res.getInt("termin_id"));
			termin.setTerminName(res.getString("termin_name"));
			termin.setTerminDate(res.getString("termin_date"));
			termin.setTerminTime(res.getString("termin_time"));			
			termine.add(termin);
		}	
		conn.close();
		return termine;
	}
	
	public static List<News> getNewsList() throws Exception{
		List<News> newstab = new ArrayList<News>();
		News news = new News();
		Connection conn = getconnection();
		PreparedStatement stam = conn.prepareStatement("SELECT * FROM news");
		ResultSet res = stam.executeQuery();
		while(res.next()) {
			news.setNewsId(res.getInt("newsid"));
			news.setSenderUserName(res.getString("sender"));
			news.setRecipientUserName(res.getString("recipient"));
			news.setTerminId(res.getInt("terminid"));
			newstab.add(news);
		}
		conn.close();
		return newstab;
	}
	
	public static User searchUser(String username) throws Exception {
		User user = new User();
		Connection conn = getconnection();
		PreparedStatement stam = conn.prepareStatement("SELECT * FROM user where username = ?");
		stam.setString(1, username);
		ResultSet res = stam.executeQuery();
		while(res.next()) {
			user.setUserName(res.getString("username"));
			user.setEmail(res.getString("email"));
			user.setNachname(res.getString("nachname"));
			user.setVorname(res.getString("vorname"));
			user.setPasswort(res.getString("passwort"));
		}
		conn.close();
		return user;
	}
	
	public static Termin searchTermin(int terminId) throws Exception {
		Termin termin = new Termin();
		Connection conn = getconnection();
		PreparedStatement stam = conn.prepareStatement("SELECT * FROM termin where termin_id = ?");
		stam.setInt(1, terminId);
		ResultSet res = stam.executeQuery();
		while(res.next()) {
			termin.setTerminId(res.getInt("termin_id"));
			termin.setTerminName(res.getString("termin_name"));
			termin.setTerminDate(res.getString("termin_date"));
			termin.setTerminTime(res.getString("termin_time"));			
		}	
		conn.close();
		return termin;
	}
	
	public static News searchNews(int newsId) throws Exception {
		News news = new News();
		Connection conn = getconnection();
		PreparedStatement stam = conn.prepareStatement("SELECT * FROM news where news_id = ?");
		stam.setInt(1, newsId);
		ResultSet res = stam.executeQuery();
		while(res.next()) {
			news.setNewsId(res.getInt("newsid"));
			news.setSenderUserName(res.getString("sender"));
			news.setRecipientUserName(res.getString("recipient"));
			news.setTerminId(res.getInt("terminid"));
		}	
		conn.close();
		return news;
	}
	
	public static void insertUser(User user) throws Exception {		
		Connection conn = getconnection();
		String query = "insert into user (username, email, nachname, vorname, passwort)"
		        + " values (?, ?, ?, ?, ?)";
		PreparedStatement stam = conn.prepareStatement(query);
		stam.setString(1, user.getUserName());
		stam.setString(2, user.getEmail());
		stam.setString(3, user.getNachname());
		stam.setString(4, user.getVorname());
		stam.setString(5, user.getPasswort());
		stam.execute();
		conn.close();
	}
	
	public static void insertTermin(Termin termin) throws Exception {
		Connection conn = getconnection();
		
		String str = termin.getTerminDate();
		Date date = Date.valueOf(str);
		
		String str2 = termin.getTerminTime();
		Time time = Time.valueOf(str2);
		
		String query = "insert into termin (termin_id, termin_name, termin_date, termin_time)"
		        + " values (?, ?, ?, ?)";
		PreparedStatement stam = conn.prepareStatement(query);
		stam.setInt(1,termin.getTerminId());
		stam.setString(2, termin.getTerminName());
		stam.setDate(3, date);
		stam.setTime(4, time);
		stam.execute();
		conn.close();
	}
	
	public static void insertNews(News news) throws Exception {
		Connection conn = getconnection();
		String query = "insert into news (news_id, sender, recipient, termin_id)"
		        + " values (?, ?, ?, ?)";
		PreparedStatement stam = conn.prepareStatement(query);
		stam.setInt(1, news.getNewsId());
		stam.setString(2, news.getSenderUserName());
		stam.setString(3, news.getRecipientUserName());
		stam.setInt(4, news.getTerminId());
		stam.execute();
		conn.close();
	}
	
	public static void updateUser(User user, String where) throws Exception {
		Connection conn = getconnection();
		PreparedStatement preparedStmt = null;
		
		switch(where) {
			case "email":
				String query1 = "update user set email = ? where username = ?";		
				preparedStmt = conn.prepareStatement(query1);
				preparedStmt.setString(1, user.getEmail());
				preparedStmt.setString(2, user.getUserName());
				break;
			case "nachname":
				String query2 = "update user set nachname = ? where username = ?";
				preparedStmt = conn.prepareStatement(query2);
				preparedStmt.setString(1, user.getNachname());
				preparedStmt.setString(2, user.getUserName());
				break;
			case "vorname":
				String query3 = "update user set vorname = ? where username = ?";
				preparedStmt = conn.prepareStatement(query3);
				preparedStmt.setString(1, user.getVorname());
				preparedStmt.setString(2, user.getUserName());
				break;
			case "passwort":
				String query4 = "update user set passwort = ? where username = ?";
				preparedStmt = conn.prepareStatement(query4);
				preparedStmt.setString(1, user.getPasswort());
				preparedStmt.setString(2, user.getUserName());
				break;
		}

		preparedStmt.executeUpdate();	      
	    conn.close();
	}
	
	public static void updateTermin(Termin termin, String where) throws Exception {
		Connection conn = getconnection();
		PreparedStatement preparedStmt = null;
		
		switch(where) {
			case "terminName":
				String query1 = "update termin set termin_name = ? where termin_id = ?";		
				preparedStmt = conn.prepareStatement(query1);
				preparedStmt.setString(1, termin.getTerminName());
				preparedStmt.setInt(2, termin.getTerminId());
				break;
			case "terminDate":
				String query2 = "update termin set termin_date = ? where termin_id = ?";
				preparedStmt = conn.prepareStatement(query2);
				preparedStmt.setString(1, termin.getTerminDate());
				preparedStmt.setInt(2, termin.getTerminId());
				break;
			case "terminTime":
				String query3 = "update termin set termin_time = ? where termin_id = ?";
				preparedStmt = conn.prepareStatement(query3);
				preparedStmt.setString(1, termin.getTerminTime());
				preparedStmt.setInt(2, termin.getTerminId());
				break;
		}

		preparedStmt.executeUpdate();	      
	    conn.close();
		
	}
	
	public static void updateNews(News news, String where) throws Exception {
		Connection conn = getconnection();
		PreparedStatement preparedStmt = null;
		
		switch(where) {
			case "sender":
				String query1 = "update news set sender = ? where news_id = ?";		
				preparedStmt = conn.prepareStatement(query1);
				preparedStmt.setString(1, news.getSenderUserName());
				preparedStmt.setInt(2, news.getNewsId());
				break;
			case "recipient":
				String query2 = "update news set recipient = ? where news_id = ?";
				preparedStmt = conn.prepareStatement(query2);
				preparedStmt.setString(1, news.getRecipientUserName());
				preparedStmt.setInt(2, news.getNewsId());
				break;
			case "terminId":
				String query3 = "update news set termin_id = ? where news_id = ?";
				preparedStmt = conn.prepareStatement(query3);
				preparedStmt.setInt(1, news.getTerminId());
				preparedStmt.setInt(2, news.getNewsId());
				break;
		}

		preparedStmt.executeUpdate();	      
	    conn.close();
		
	}
	
	public static void deleteUser(String username) throws Exception {
		Connection conn = getconnection();
		String query = "delete from user where username = ?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, username);
		preparedStmt.execute();	      
		conn.close();
	}
	
	public static void deleteTermin(int terminId) throws Exception {
		Connection conn = getconnection();
		String query = "delete from termin where termin_id = ?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, terminId);
		preparedStmt.execute();	      
		conn.close();
	}
	
	public static void deleteNews(int newsId) throws Exception {
		Connection conn = getconnection();
		String query = "delete from news where news_id = ?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, newsId);
		preparedStmt.execute();	      
		conn.close();
	}

	public static Connection getconnection() throws Exception {
		
		String driver ="com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/kalender_db";
		String username = "root";
		String password = "";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,username,password);
		System.out.println("Sql verbunden...");
		return conn;			
	}
}
