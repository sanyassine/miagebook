package persistence.connection;

import persistence.connection.utils.AuthentificationData;
import persistence.connection.utils.AuthentificationProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Oracle {
    private static Connection c; 
    
    private Oracle() {
    	//AuthentificationData ad = AuthentificationProvider.getInstance().get();
    	//if(ad != null) {
	        try{
	        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
	        c = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fil.univ-lille1.fr:1521:filora",
	        	"SANSAOUI","mamaroula007"); // put login and password
	        c.setAutoCommit(false);
	        }
	        catch(Exception e){
	        	Logger.getGlobal().info(e.getMessage());
	        	System.out.println(e.getMessage());
	            System.out.println("Fail connection");  
	        };
	        System.out.println("Connection success");
    	//}
    }
    
    /**
     * get a connection with a username and password
     * @param username 
     * @param password
     * @return a connection correpondant
     */
    public static Connection getConnection(){ 
        if (c == null) {
            c = new Oracle().c;
        }
        return Oracle.c;
    }; 
    
    /**
     * disconnect the connection
     */
    public void disconnect() {
    	// TODO real disconnect
    	System.out.println("Disconnect from the database");
    }

}
