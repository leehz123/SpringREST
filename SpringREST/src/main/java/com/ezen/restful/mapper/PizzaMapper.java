package com.ezen.restful.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.restful.dto.Pizza;


public interface PizzaMapper {
	
	List<Pizza> getPizzaList();
	
	Pizza getPizzaById(int id);
	
	Integer updateView(int id);
	
	List<String> getPizzaNames();
	
	Pizza getPizzaByName(String name);
	
	Integer insertPizza(@Param("name") String name, @Param("price") Integer price, @Param("calories") Double calories);

	Integer updatePizza(@Param("id") Integer id, @Param("name") String name, @Param("price") Integer price, @Param("calories") Double calories);
	
	Integer deletePizza(Integer id);
	
	
	
	//ajax정답풀이

	List<Pizza> getAll();
	//이거 PizzaMapper.xml에 리소스 만들어주공 (sql문 등록)
	
	Integer update(@Param("id") Integer id, @Param("name") String name, @Param("price") Integer price, @Param("calories") Double calories);
		
}	
