/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.Implements;
import com.shopme.admin.Repository.RoleRepo;
import com.shopme.admin.services.RoleService;
import com.shopme.common.entity.Role;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gnaht
 */
@Service
@Transactional
public class RoleImp implements RoleService{

    @Autowired
    private RoleRepo roleRepo;
    
    @Override
    public List<Role> listRole() {
        return roleRepo.findAll();
    }
    
}
