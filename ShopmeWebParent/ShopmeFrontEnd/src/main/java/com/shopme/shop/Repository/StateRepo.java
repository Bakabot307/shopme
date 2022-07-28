/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.shop.Repository;

import com.shopme.common.entity.Country;
import com.shopme.common.entity.State;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author gnaht
 */

public interface StateRepo extends CrudRepository<State, Integer> {
	
	public List<State> findByCountryOrderByNameAsc(Country country);
}
