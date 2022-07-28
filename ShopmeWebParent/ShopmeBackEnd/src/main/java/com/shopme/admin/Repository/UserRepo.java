/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.Repository;


import com.shopme.common.entity.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gnaht
 */
@Repository
public interface UserRepo extends PagingAndSortingRepository<Users, String> {

    Boolean existsByUsername(String username);

    Long deleteByUsername(String username);

    Users findByUsernameAndPassword(String Username, String password);

    Users findByUsername(String Username);

    Users findById(Integer id);

    Users findByEmail(String Email);

    Long countById(Integer id);

    void deleteById(Integer id);

    @Query("UPDATE Users u SET u.active = ?2 where u.id= ?1")
    @Modifying
    void updateUserActiveStatus(Integer id, boolean active);

}
