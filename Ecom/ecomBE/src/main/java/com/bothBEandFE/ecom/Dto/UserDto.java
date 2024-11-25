package com.bothBEandFE.ecom.Dto;

import com.bothBEandFE.ecom.Enums.UserRole;

import lombok.Data;

@Data
public class UserDto {

	private Long id;
	
	private String email;
	
	private String name;
	
	private UserRole userRole;
	
}
