package com.shopme.admin.security.Customer;

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.example.fromzero.security.Customer;
//
//import com.example.fromzero.Repository.CustomerRepo;
//import com.example.fromzero.dto.Customer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
///**
// *
// * @author gnaht
// */
//public class CustomerUserDetailsService implements UserDetailsService {
//	
//	@Autowired private CustomerRepo repo;
//	
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Customer customer = repo.findByEmail(email);
//		if (customer == null) 
//			throw new UsernameNotFoundException("No customer found with the email " + email);
//		
//		return new CustomerUserDetails(customer);
//	}
//}