package com.bothBEandFE.ecom.Controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponseWrapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bothBEandFE.ecom.Dto.AuthenticationRequest;
import com.bothBEandFE.ecom.Dto.SignUpRequest;
import com.bothBEandFE.ecom.Dto.UserDto;
import com.bothBEandFE.ecom.Entity.User;
import com.bothBEandFE.ecom.Repository.UserRepository;
import com.bothBEandFE.ecom.Services.Auth.AuthService;
import com.bothBEandFE.ecom.Utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private static final String HEADER_STRING = "Authorization";

	private static final String TOKEN_PREFIX = "Bearer ";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthService authService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
			HttpServletResponseWrapper response) throws IOException, JSONException {
		try {
			System.out.println(authenticationRequest.getEmail());
			System.out.println(authenticationRequest.getPassword());
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(), authenticationRequest.getPassword()));
			
		} catch (BadCredentialsException e) {
			throw new UsernameNotFoundException("Incorrect Username or Password");
			//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect Username or Password");
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

		final String jwt = jwtUtil.generateToken(userDetails.getUsername());

		if (optionalUser.isPresent()) {
			response.getWriter().write(new JSONObject().put("userId", optionalUser.get().getId())
					.put("role", optionalUser.get().getRole()).toString());

			response.addHeader("Access-Control-Expose-Headers", "Authorization");
			response.addHeader("Access-Control-Allow-Headers",
					"Authorization, X-PINGOTHER, Origin, " + "X-Requested-With, Content-Type, Accept, X-Custom-header");
			response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
			System.out.println(response);
		}
//		return ResponseEntity.status(HttpStatus.OK).body("Correct Username");

	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest) {
		if (authService.hasUserWithEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>("User Already Exists", HttpStatus.NOT_ACCEPTABLE);
		}

		UserDto userDto = authService.createUser(signUpRequest);
		return new ResponseEntity<>(userDto, HttpStatus.OK);

	}
}
