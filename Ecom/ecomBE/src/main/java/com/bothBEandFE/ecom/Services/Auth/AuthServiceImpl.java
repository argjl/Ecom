package com.bothBEandFE.ecom.Services.Auth;

import javax.annotation.PostConstruct;

import com.bothBEandFE.ecom.Entity.Order;
import com.bothBEandFE.ecom.Enums.OrderStatus;
import com.bothBEandFE.ecom.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bothBEandFE.ecom.Dto.SignUpRequest;
import com.bothBEandFE.ecom.Dto.UserDto;
import com.bothBEandFE.ecom.Entity.User;
import com.bothBEandFE.ecom.Enums.UserRole;
import com.bothBEandFE.ecom.Repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	public UserDto createUser(SignUpRequest signUpRequest) {
		User user = new User();
		user.setEmail(signUpRequest.getEmail());
		user.setName(signUpRequest.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
		user.setRole(UserRole.CUSTOMER);
		User createdUser = userRepository.save(user);

		Order order=new Order();
		order.setAmount(0L);
		order.setTotalAmount(0L);
		order.setDiscount(0L);
		order.setOrderStatus(OrderStatus.Pending);
		order.setUser(createdUser);

		orderRepository.save(order);

		UserDto userDto = new UserDto();
		userDto.setId(createdUser.getId());

		return userDto;
	}

	
	public Boolean hasUserWithEmail(String email) {
		return userRepository.findFirstByEmail(email).isPresent();
	}
	
	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if(null==adminAccount) {
			User user = new User();
			user.setEmail("admin@test.com");
			user.setName("admin");
			user.setRole(UserRole.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin@123"));
			userRepository.save(user);
		}
	}

}
