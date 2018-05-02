package persistence.connection.utils;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class AuthentificationProvider {
	
	private static String authentificationFilename = null;
	
	public static void setAuthFile(String path) {
		authentificationFilename = path;
	}
	
	private static AuthentificationProvider INSTANCE;
	private AuthentificationProvider() {}
	public static AuthentificationProvider getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new AuthentificationProvider();
		}
		return INSTANCE;
	}
	
	/**
	 * get authentification data from xml file or user input if file incorrect
	 * @return
	 */
	public AuthentificationData get() {
		AuthentificationData ad = null;
		try {
			ad = provideFromFile();
		} catch (IOException e) {
			//ad = provideFromUser();
		}
		return ad;
	}
	
	/**
	 * provide authentifiation data from user input
	 * @return
	 */
	private AuthentificationData provideFromUser() {
		String login = ScannerAuthReader.getInstance().getLogin();
		String password = ScannerAuthReader.getInstance().getPassword();
		return new AuthentificationData(login, password);
	}
	
	/**
	 * provide authentification data from xml file throws IOException if incorrect file
	 * @return
	 * @throws IOException
	 */
	private AuthentificationData provideFromFile() throws IOException {
		if(authentificationFilename != null) {
			XMLAuthReader xmlAuthReader = XMLAuthReader.getInstance();
			String[] attributes = {"login","password"};
			Map<String, String> map;
			try {
				map = xmlAuthReader.getXMLData(authentificationFilename, "auth", attributes);
			} catch (SAXException e) {
				throw new IOException("problem when reading xml");
			} catch (ParserConfigurationException e) {
				throw new IOException("problem when reading xml (parsing)");
			}
			if(!map.containsKey("login") || !map.containsKey("password"))
				throw new IOException("login and password attributes are needed");
			return new AuthentificationData(map.get("login"), map.get("password"));
		}return null;
	}
}
