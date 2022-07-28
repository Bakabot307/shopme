/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.RestController;

import com.shopme.admin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gnaht
 */
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/admin/users/check_email")
    public String CheckDuplicateEmail(@Param("email") String email, @Param("username") String username,@Param("id") Integer id) {
        return userService.isUserUnique(username,id) ? userService.isEmailUnique(email,id) ? "bothOk" : "eDuplicated"
                                                  : userService.isEmailUnique(email,id) ? "nDuplicated" : "bothDuplicated";
    }
}
