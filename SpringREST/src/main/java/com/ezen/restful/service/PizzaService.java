package com.ezen.restful.service;

import java.util.List;

import com.ezen.restful.dto.Pizza;

public interface PizzaService {

	public List<Pizza> getPizzaList(); 
	
	public Pizza getPizza(int pk);
	
	public List<String> getPizzaNames();
	
	public Pizza getPizzaByName(String name);
	
	public Integer InsertPizza(Pizza pizza);
	
	public Integer updatePizza(Pizza pizza);
	
	public Integer deletePizza(Integer id);
}
