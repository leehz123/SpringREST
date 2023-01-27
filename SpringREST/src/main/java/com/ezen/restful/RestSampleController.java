package com.ezen.restful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.restful.dto.Pizza;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class RestSampleController {
	
	@GetMapping(value = "/test1", produces = "text/plain; charset=EUC-KR")
	public String test1() {
		return "Hello, RESTFUL!!";
	}
	
	//jackson-databind : �ڹ� ��ü�� JSON �Ǵ� XML Ÿ������ �˾Ƽ� ��ȯ���ִ� ���̺귯�� 
	
	//MediaType.APPLICATION_JSON_VALUE �� �Ǹ� "application/json; charset=EUC-KR" �� MediaType.APPLICATION_JSON_UTF8_VALUE�� ��ü �ϼ�
	//MediaType.APPLICATION_JSON_UTF8_VALUE�� �� �ϸ� ������ ��� ���� : ���� ��Ŭ ������ UTF-8�� �ƴ϶� EUC-KR�� �س��� �׷�
	//Eclipse (EUC-KR) ----utf-8�� ��ȯ�Ѵٰ� �������� �� ---> ByteStream ------ utf-8 ------> chrome (�⺻ ���ڵ� ���� utf-8)
	@GetMapping(value="/pizza", produces= MediaType.APPLICATION_JSON_VALUE) //import org.springframework.http.MediaType;
	public Pizza getPizza() {
		Pizza pizza = new Pizza();
		pizza.setP_id(13);
		pizza.setP_name("���۷δ�");
		pizza.setP_calories(333.123);
		pizza.setP_price(8000);
		return pizza;
	}
	
	//�ٵ� �� ���� xml���� json����� ���� ����. �ؼ��ϱ⿡�� �� �����ϰ�
	@GetMapping(value="/pizza2", produces= MediaType.APPLICATION_XML_VALUE)
	public Pizza getPizza2() {
		Pizza pizza = new Pizza();
		pizza.setP_id(13);
		pizza.setP_name("���۷δ�");
		pizza.setP_calories(333.123);
		pizza.setP_price(8000);
		return pizza;		
	}
	
	
	@GetMapping(value="/pizzas", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Pizza> getPizzas() {
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(new Pizza(1001, "���ξ��� ����", 12000, 1560.80));
		pizzas.add(new Pizza(1002, "��Ʈ���� ����", 13000, 1660.80));
		pizzas.add(new Pizza(1003, "���۹�Ʈ ����", 13300, 1760.80));
		pizzas.add(new Pizza(1004, "���� ����", 14000, 1860.80));
		
		return pizzas;
	}
	
	
	@GetMapping(value = "/pizza_map", produces =MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Pizza> pizzaMap() {
		Map<String, Pizza> pizzaMap = new HashMap<>();
		pizzaMap.put("menu1", new Pizza(1001, "�ϸ��� ����", 12000, 1560.80));
		pizzaMap.put("menu2", new Pizza(1001, "�䷹�ην� ����", 12000, 1560.80));
		pizzaMap.put("menu3", new Pizza(1001, "���Ǹ� ����", 12000, 1560.80));
		
		return pizzaMap;
	}
	
	
	//PizzaRestController ���� ���� Ǯ���
	
}
