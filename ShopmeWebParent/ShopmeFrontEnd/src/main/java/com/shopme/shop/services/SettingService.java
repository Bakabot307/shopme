/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.shop.services;

import com.shopme.common.entity.Address;
import com.shopme.common.entity.Customer;
import com.shopme.common.entity.EmailSettingBag;
import com.shopme.common.entity.Setting;
import java.util.List;

/**
 *
 * @author gnaht
 */
public interface SettingService {

    public List<Setting> getGeneralSettings();

    public EmailSettingBag getEmailSettings();



}
