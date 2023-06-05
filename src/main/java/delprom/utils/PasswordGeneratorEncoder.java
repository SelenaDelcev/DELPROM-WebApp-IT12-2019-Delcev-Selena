package delprom.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("Delprom1"));
		System.out.println(passwordEncoder.encode("Delprom2"));
		System.out.println(passwordEncoder.encode("Masonii0502"));
		System.out.println(passwordEncoder.encode("Kriss0212"));
		System.out.println(passwordEncoder.encode("Makii1805"));
		System.out.println(passwordEncoder.encode("Branka3011"));
		System.out.println(passwordEncoder.encode("Dule1234"));
		System.out.println(passwordEncoder.encode("KrileDap00"));
	}

}
