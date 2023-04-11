package delprom.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("DELPROM1"));
		System.out.println(passwordEncoder.encode("DELPROM2"));
		System.out.println(passwordEncoder.encode("mašonii"));
		System.out.println(passwordEncoder.encode("kriss"));
		System.out.println(passwordEncoder.encode("makii"));
		System.out.println(passwordEncoder.encode("branka3011"));
		System.out.println(passwordEncoder.encode("dule123"));
		System.out.println(passwordEncoder.encode("kriledap"));
	}

}
