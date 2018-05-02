package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.UserProfile;
import persistence.connection.Oracle;

public class RegisterService {

	public static UserProfile register(String login, String password, String fName, String lName, String email) {
    	UserProfile userProfile = new UserProfile();
    	userProfile.setLogin(login);
    	userProfile.setPassword(password);
    	userProfile.setFirstName(fName);
    	userProfile.setLastName(lName);
    	userProfile.setEmail(email);
    	return userProfile;
	}
	
	public static List<String> checkData(String login, String password, String firstname, String lastname,String email) {
    	List<String> errorMessages = new ArrayList<String>();
    	if(!isCorrectLogin(login)) {
    		errorMessages.add("incorrect login\n");
    	}if(!isCorrectPassword(password)) {
    		errorMessages.add("incorrect password\n");
    	}if(!isCorrectFirstName(firstname)) {
    		errorMessages.add("incorrect first name\n");
    	}if(!isCorrectLastName(lastname)) {
    		errorMessages.add("incorrect last name");
    	}if(!isCorrectEmail(email)) {
    		errorMessages.add("email not valid");
    	}
    	return errorMessages;
    }
    
    private static boolean isCorrectEmail(String email) {
    	if(email.length() != 0 && email.matches("[A-Za-z1-9]*@[A-Za-z1-9]*.[A-Za-z1-9]*"))
    		return true;
    	return false;
    }
    
    private static boolean isCorrectFirstName(String firstname) {
    	if(firstname.length() != 0)
    		return true;
    	return false;
    }
    
    private static boolean isCorrectLastName(String lastname) {
    	if(lastname.length() != 0)
    		return true;
    	return false;
    }
    
    private static boolean isCorrectLogin(String login) {
    	if(login.length() > 0)
    		return true;
    	return false;
    }
    
    
    private static boolean isCorrectPassword(String password) {
    	if(password.length() > 0)
    		return true;
    	return false;
    }
    
    public static boolean loginAlreadyExists(String login) {
    	boolean res = false;
    	Connection connection = Oracle.getConnection();
    	String sql = "SELECT login FROM userprofiles WHERE login = ?";
    	try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return res;
    }
}
