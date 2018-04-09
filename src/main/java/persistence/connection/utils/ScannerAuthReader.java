package persistence.connection.utils;

import java.util.Scanner;

public class ScannerAuthReader {
	
	private static ScannerAuthReader INSTANCE;
	private ScannerAuthReader() {}
	public static ScannerAuthReader getInstance() {
		if(INSTANCE == null)
			INSTANCE = new ScannerAuthReader();
		return INSTANCE;
	}

	/**
	 * get password from user input
	 * @return
	 */
	public String getPassword() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("password?");
		String password = scanner.next();
		scanner.close();
		return password;
	}
	
	/**
	 * get login from user input
	 * @return
	 */
	public String getLogin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Login?");
		String login = scanner.next();
		scanner.close();
		return login;
	}
}
