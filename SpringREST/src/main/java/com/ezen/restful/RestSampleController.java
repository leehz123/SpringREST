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
	
	//jackson-databind : 자바 객체를 JSON 또는 XML 타입으로 알아서 변환해주는 라이브러리 
	
	//MediaType.APPLICATION_JSON_VALUE 안 되면 "application/json; charset=EUC-KR" 나 MediaType.APPLICATION_JSON_UTF8_VALUE로 대체 하셈
	//MediaType.APPLICATION_JSON_UTF8_VALUE로 안 하면 깨지는 사람 이유 : 현재 이클 설정이 UTF-8이 아니라 EUC-KR로 해놔서 그럼
	//Eclipse (EUC-KR) ----utf-8로 변환한다고 명시해줘야 됨 ---> ByteStream ------ utf-8 ------> chrome (기본 인코딩 값이 utf-8)
	@GetMapping(value="/pizza", produces= MediaType.APPLICATION_JSON_VALUE) //import org.springframework.http.MediaType;
	public Pizza getPizza() {
		Pizza pizza = new Pizza();
		pizza.setP_id(13);
		pizza.setP_name("페퍼로니");
		pizza.setP_calories(333.123);
		pizza.setP_price(8000);
		return pizza;
	}
	
	//근데 딱 봐도 xml보다 json방식이 보기 편함. 해석하기에도 덜 복잡하고
	@GetMapping(value="/pizza2", produces= MediaType.APPLICATION_XML_VALUE)
	public Pizza getPizza2() {
		Pizza pizza = new Pizza();
		pizza.setP_id(13);
		pizza.setP_name("페퍼로니");
		pizza.setP_calories(333.123);
		pizza.setP_price(8000);
		return pizza;		
	}
	
	
	@GetMapping(value="/pizzas", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Pizza> getPizzas() {
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(new Pizza(1001, "파인애플 피자", 12000, 1560.80));
		pizzas.add(new Pizza(1002, "민트초코 피자", 13000, 1660.80));
		pizzas.add(new Pizza(1003, "페퍼민트 피자", 13300, 1760.80));
		pizzas.add(new Pizza(1004, "보약 피자", 14000, 1860.80));
		
		return pizzas;
	}
	
	
	@GetMapping(value = "/pizza_map", produces =MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Pizza> pizzaMap() {
		Map<String, Pizza> pizzaMap = new HashMap<>();
		pizzaMap.put("menu1", new Pizza(1001, "하리보 피자", 12000, 1560.80));
		pizzaMap.put("menu2", new Pizza(1001, "페레로로쉐 피자", 12000, 1560.80));
		pizzaMap.put("menu3", new Pizza(1001, "게피맛 피자", 12000, 1560.80));
		
		return pizzaMap;
	}
	
	
	//PizzaRestController 에서 문제 풀어보셈
	
}
