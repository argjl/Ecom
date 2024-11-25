package com.bothBEandFE.ecom.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bothBEandFE.ecom.Entity.User;
import com.bothBEandFE.ecom.Enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findFirstByEmail(String email);

	User findByRole(UserRole userRole);

}
