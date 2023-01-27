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
	
	
	
	//ajax����Ǯ��

	List<Pizza> getAll();
	//�̰� PizzaMapper.xml�� ���ҽ� ������ְ� (sql�� ���)
	
	Integer update(@Param("id") Integer id, @Param("name") String name, @Param("price") Integer price, @Param("calories") Double calories);
		
}	
