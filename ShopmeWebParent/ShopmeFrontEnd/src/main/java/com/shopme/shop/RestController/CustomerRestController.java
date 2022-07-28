/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.shop.RestController;

import com.shopme.shop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gnaht
 */
@RestController
public class CustomerRestController {

	@Autowired private CustomerService service;
	
	@PostMapping("/shop/register/check_unique")
	public String CheckDuplicate(@Param("email") String email, @Param("username") String username) {
        return service.isUsernameUnique(username) ? service.isEmailUnique(email) ? "bothOk" : "eDuplicated"
                                                  : service.isEmailUnique(email) ? "nDuplicated" : "bothDuplicated";
        
        }
}
