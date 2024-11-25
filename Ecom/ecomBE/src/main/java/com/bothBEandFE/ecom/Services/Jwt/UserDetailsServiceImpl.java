package com.bothBEandFE.ecom.Services.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bothBEandFE.ecom.Entity.User;
import com.bothBEandFE.ecom.Enums.UserRole;
import com.bothBEandFE.ecom.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findFirstByEmail(username);
        if (optionalUser.isPresent()) {
            return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(),
                    optionalUser.get().getPassword(), getAuthorities(optionalUser.get()));
        }
        throw new UsernameNotFoundException("Username Not found", null);

    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        UserRole role = user.getRole();
        System.out.println(role);
        if (role == UserRole.ADMIN) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (role == UserRole.CUSTOMER) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        }
        return Collections.emptyList();
    }

}
