/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.Implements;


import com.shopme.admin.Repository.SettingRepo;
import com.shopme.admin.services.SettingService;
import com.shopme.common.entity.EmailSettingBag;
import com.shopme.common.entity.Setting;
import com.shopme.common.entity.SettingCategory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gnaht
 */
@Service
public class SettingImp implements SettingService {

    @Autowired
    private SettingRepo settingRepo;

    @Override
    public List<Setting> getGeneralSettings() {
        return settingRepo.findByTwoCategories(SettingCategory.GENERAL, SettingCategory.CURRENCY);
    }

    @Override
    public EmailSettingBag getEmailSettings() {
        List<Setting> settings = settingRepo.findByCategory(SettingCategory.MAIL_SERVER);
        System.out.println("setting" + settings);
        settings.addAll(settingRepo.findByCategory(SettingCategory.MAIL_TEMPLATES));

        return new EmailSettingBag(settings);
    }
}
