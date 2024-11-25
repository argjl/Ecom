package com.bothBEandFE.ecom.Services.Auth;

import com.bothBEandFE.ecom.Dto.SignUpRequest;
import com.bothBEandFE.ecom.Dto.UserDto;

public interface AuthService {

	UserDto createUser(SignUpRequest signUpRequest);

	Boolean hasUserWithEmail(String email);
}
