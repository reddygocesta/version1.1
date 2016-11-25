package com.sensiple.contactsrepository.utils;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {

	private static final Random RANDOM = new SecureRandom();

	public static final int PASSWORD_LENGTH = 8;

	/**
	 * Generate a random String suitable for use as a temporary password.
	 * 
	 * @return String suitable for use as a temporary password
	 */
	public static String geRandomtPassword() {

		final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*_=+-/";

		String password = "";

		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			int index = (int) (RANDOM.nextDouble() * letters.length());
			password += letters.substring(index, index + 1);
		}

		return password;
	}

}
