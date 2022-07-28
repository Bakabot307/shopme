/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopme.admin.RestController;


import com.shopme.admin.Repository.StateRepo;
import com.shopme.common.entity.Country;
import com.shopme.common.entity.State;
import com.shopme.common.entity.StateDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gnaht
 */
@RestController
public class StateRestController {

	@Autowired private StateRepo stateRepo;
	
	@GetMapping("/shop/register/list_states_by_country/{id}")
	public List<StateDto> listByCountry(@PathVariable("id") Integer countryId) {
		List<State> listStates = stateRepo.findByCountryOrderByNameAsc(new Country(countryId));
		List<StateDto> result = new ArrayList<>();
		
		for (State state : listStates) {
			result.add(new StateDto(state.getId(), state.getName()));
		}
		
		return result;
	}
}
