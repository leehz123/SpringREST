package com.ezen.restful.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.restful.dto.Pizza;
import com.ezen.restful.mapper.PizzaMapper;

@Service
public class PizzaServiceImpl implements PizzaService {

	@Autowired
	PizzaMapper mapper;
	
	@Override
	public List<Pizza> getPizzaList() {
		return mapper.getPizzaList();
	}
	
	@Override
	public Pizza getPizza(int id) {
		mapper.updateView(id);		
		return mapper.getPizzaById(id);
	}


	@Override
	public List<String> getPizzaNames() {
		return mapper.getPizzaNames();
	}


	@Override
	public Pizza getPizzaByName(String name) {
		return mapper.getPizzaByName(name);
	}


	@Override
	public Integer InsertPizza(Pizza pizza) {
		return mapper.insertPizza(pizza.getP_name(), pizza.getP_price(), pizza.getP_calories());
	}

	@Override
	public Integer updatePizza(Pizza pizza) {
		return mapper.update(pizza.getP_id(), pizza.getP_name(), pizza.getP_price(), pizza.getP_calories());
	}
	
	@Override
	public Integer deletePizza(Integer id) {
		return mapper.deletePizza(id);
	}

}
