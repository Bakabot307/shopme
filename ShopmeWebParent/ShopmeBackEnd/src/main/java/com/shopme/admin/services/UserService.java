/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.services;


import com.shopme.common.entity.Role;
import com.shopme.common.entity.Users;
import com.shopme.common.exception.UserNotFoundException;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author gnaht
 */
public interface UserService {
  List<Users> findAll();

  Page<Users> listByPage(int pageNum);

  Users Login(String username, String password);

  Users add(Users user);

  void remove(String username);

  Users GetByUserName(String username);

  Users GetById(Integer id) throws UserNotFoundException;

  Users Update(Users user);

  boolean IsExists(String username);

  Role saveRole(Role role);

  void saveRoletoUser(String username, String name);

  boolean isEmailUnique(String email, Integer id);

  boolean isUserUnique(String username, Integer id);

  void deleteUser(Integer id) throws UserNotFoundException;

  void updateUserEnabledStatus(Integer id, boolean active);
  

}
