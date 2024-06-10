package spring.angular.backend.app.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SpringUtils {

	/**
	 * bCryptEncoder
	 * 
	 * @param password
	 * @return
	 */
	public static String bCryptEncoder(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPassword = encoder.encode(password);
		return hashedPassword;
	}

}
