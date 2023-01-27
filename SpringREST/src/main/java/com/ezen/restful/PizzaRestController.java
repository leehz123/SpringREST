package com.ezen.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.restful.dto.Pizza;
import com.ezen.restful.service.PizzaServiceImpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class PizzaRestController {
	
	@Autowired
	PizzaServiceImpl service;
	
	
	@GetMapping(value="/sample/pizza/pizzalist", produces = MediaType.APPLICATION_XML_VALUE)
	public List<Pizza> getPizzaList(){
		return service.getPizzaList();
	}
	
	
	//ex : http://localhost:8888/restful/sample/pizza/id/22로 접속
	//GET방식으로 피자의 ID와 함께 요청을 보내면 해당 피자의 정보를 JSON 형식으로 반환 
	@GetMapping(value="/sample/pizza/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public Pizza getPizzaByPid(@PathVariable Integer id) {
		//조회할 때마다 칼로리가 1씩 증가.	
		return service.getPizza(id);
	}

	@GetMapping(value="/sample/pizza/namelist")
	public List<String> getPizzaNames() {
		//List<String> nameList = service.getPizzaNames();		
		return service.getPizzaNames();
	}
		
	@GetMapping(value="/sample/pizza/name/{name}", produces= MediaType.APPLICATION_XML_VALUE)
	public Pizza getPizzaByName(@PathVariable String name) { 
		return service.getPizzaByName(name);
	}
	
	@PutMapping(value="/sample/pizza/update")
	public String updatePizza(@RequestBody Pizza pizza) {
		return service.updatePizza(pizza).toString();
	}
	


	@PostMapping(value="/sample/pizza/insert")
	public ResponseEntity<Pizza> insertPizza(@RequestBody Pizza pizza) {
		
		//name input 비워둔 채 보냈는데 200ok뜸. 왜냐면
		//컨트롤러에서 주의해야 하는게 null만 체크하면 안 됨! 빈 값도 체크해야 됨!
		//자스에서도 거르고 컨트롤러에서도 걸러주기! 
		//좀 더 편리한 방법이 없을까 하기보다 걍 노가다 하는게 나을 수도 (이거까지 패턴화 하려면 고민을 많이 해봐야겠지)
		//널값 체크 메서드를 만든다든지ㅎ 
		if(pizza.getP_name() == null || pizza.getP_name().trim().equals("") 
				|| pizza.getP_calories() == null 
				|| pizza.getP_price() == null ) { 
			return ResponseEntity.badRequest().build(); //나쁜 요청입니다. httpStatus 가 400 으로 자스에 감
		}
		
		try {
			service.InsertPizza(pizza);
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
//		//이번엔 숫자가 아니라 ResponseEntity를 리턴해보겠음
//		//ResponseEntity : 상황에 따라 원하는 응답을 만들 수 있다. (상태 코드 활용) 
//		
//		
//		ResponseEntity<Pizza> response = null;
//		
//		//ResponseEntity.ok() : Http 상태코드 200의 응답을 만든다.
//		//페이로드를 실어 보낼 수 있음 근데 페이로드의 타입은 제네릭으로 결정해줘야 함.
//		response = ResponseEntity.ok(null);
//		
//		//ResponseEntity.notFound() : Http 상태코드 404의 응답을 만든다.
//		response = ResponseEntity.notFound().build(); //낫파에는 데이터를 실어 보낼 수 없음
		
//		//자유롭게 원하는 방식으로 응답 만들기
//		response = ResponseEntity.status(HttpStatus.OK)
//					.contentType(MediaType.APPLICATION_JSON)
//					.body(service.getPizza(22)); 
//		//Http 상태코드 종류 http://www.incodom.kr/Status_code _ ex. FORBIDDEN은 404를 나타내는 상수
//		return response;
		
	}

 

	
	
	@DeleteMapping(value ="/sample/pizza/delete/{id}") 
	public Integer deletePizza(@PathVariable Integer id) {		
		return service.deletePizza(id);
	}
	
	
	

	
	
/*

//잠깐 주석처리 하겟음
	@PostMapping(value="/sample/pizza/insert")
	public String insertPizza(@RequestBody Pizza pizza) {

		try {
			return service.InsertPizza(pizza).toString();
		} catch(Exception e) {
			return "0 : " + e; 
		}
		
		//응답이 <Integer>1</Integer>로 표시돼서 이걸로 if문을 따질 수 없으니 문자열로 바꿔서 리턴하겠음

	}
	
	

	
	
	@PutMapping(value="/sample/pizza")
	public String updatePizza(@RequestBody Pizza pizza) {
		
		try {
			return service.updatePizza(pizza).toString();
		} catch (Exception e) {
			return "0 : " + e;
		}
	}

 */

	
	
/*

	//내 풀이. service없이 걍 pizzaMapper로만 데이터 가져옴
	
	@Autowired
	PizzaMapper pmapper;

	//ex : http://localhost:8888/restful/sample/pizza/read/22
	//문제1. GET방식으로 피자의 ID와 함께 요청을 보내면 해당 피자의 정보를 JSON 형식으로 반환 
	@GetMapping(value="/sample/pizza/read/{p_id}", produces = MediaType.APPLICATION_JSON_VALUE) //렌더링 오류 뜨면 produces=MediaType.APPLICATION_JSON_VALUE_UTF-8 로 대체해보기
	public Pizza getPizzaByPid(@PathVariable Integer p_id) {
	
		log.info(pmapper.findById(p_id));
		return pmapper.findById(p_id);
	}
	
	//문제2. POST방식으로 파라미터의 피자 데이터와 함께 요청을 보내면 해당 피자를 DB에 추가
	//이 문제는 chrome 주소에 요청만 받아보는 확장 프로그램 받아서 테스트 해보면 됨 
	//chrome-extension://ehafadccdcdedbhcbddihehiodgcddpl/index.html (꼭 이거 아니어도 됨)
	
	//public Integer insertPizza(Pizza pizza) {	
	//이렇게 받으면 자동 바인딩 안 된대
	//@RestController에서는 @RequestBody를 적어주지 않으면 파라미터가 자동 바인딩 되지 않는다.
	@PostMapping(value = "/sample/pizza/insert")
	public Integer insertPizza(@RequestBody Pizza pizza) {
		pmapper.insertMenu(pizza.getP_name(), pizza.getP_price(), pizza.getP_calories());
		return 3333; //리턴타입이 Integer면 그냥 숫자가 리턴 되는 거래
	}
	
	//문제3. PUT방식으로 피자 데이터를 함께 요청을 보내면 해당 피자의 정보를 수정
	//파라미터로 받아서 파라미터로 넘기래
	@PutMapping(value="/sample/pizza/update")
	public Integer updatePizza(@RequestBody Pizza pizza) {
		pmapper.updatePizza(pizza.getP_id(), pizza.getP_name(), pizza.getP_price(), pizza.getP_calories());
		return 444; 
	}
	
	//문제4. DELETE방식으로 피자의 ID와 함께 요청을 보내면 해당 피자를 DB에서 삭제
	@DeleteMapping(value="/sample/pizza/delete/{p_id}")
	public Integer deletePizza(@PathVariable Integer p_id) {
		pmapper.deleteMenu(p_id);
		return 555;
}
 */

	
}
