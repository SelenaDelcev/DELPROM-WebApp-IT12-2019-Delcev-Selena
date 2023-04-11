package delprom.services;

import delprom.dtos.LoginDto;
import delprom.dtos.SignupDto;

public interface AuthService {
	
	String login(LoginDto loginDto);
	
	String register(SignupDto signupDto);

}
