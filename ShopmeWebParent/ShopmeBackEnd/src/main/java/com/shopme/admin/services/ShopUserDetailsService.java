package com.shopme.admin.services;

import com.shopme.admin.Repository.UserRepo;
import com.shopme.admin.security.ShopUserDetails;
import com.shopme.common.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;




public class ShopUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userRepo.findByEmail(email);
              
		if (user != null) {
			return new ShopUserDetails(user);		}
		
		throw new UsernameNotFoundException("Could not find user with email: " + email);
	}

}
