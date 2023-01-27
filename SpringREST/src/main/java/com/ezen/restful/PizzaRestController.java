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
	
	
	//ex : http://localhost:8888/restful/sample/pizza/id/22�� ����
	//GET������� ������ ID�� �Բ� ��û�� ������ �ش� ������ ������ JSON �������� ��ȯ 
	@GetMapping(value="/sample/pizza/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public Pizza getPizzaByPid(@PathVariable Integer id) {
		//��ȸ�� ������ Į�θ��� 1�� ����.	
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
		
		//name input ����� ä ���´µ� 200ok��. �ֳĸ�
		//��Ʈ�ѷ����� �����ؾ� �ϴ°� null�� üũ�ϸ� �� ��! �� ���� üũ�ؾ� ��!
		//�ڽ������� �Ÿ��� ��Ʈ�ѷ������� �ɷ��ֱ�! 
		//�� �� ���� ����� ������ �ϱ⺸�� �� �밡�� �ϴ°� ���� ���� (�̰ű��� ����ȭ �Ϸ��� ����� ���� �غ��߰���)
		//�ΰ� üũ �޼��带 ����ٵ����� 
		if(pizza.getP_name() == null || pizza.getP_name().trim().equals("") 
				|| pizza.getP_calories() == null 
				|| pizza.getP_price() == null ) { 
			return ResponseEntity.badRequest().build(); //���� ��û�Դϴ�. httpStatus �� 400 ���� �ڽ��� ��
		}
		
		try {
			service.InsertPizza(pizza);
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
//		//�̹��� ���ڰ� �ƴ϶� ResponseEntity�� �����غ�����
//		//ResponseEntity : ��Ȳ�� ���� ���ϴ� ������ ���� �� �ִ�. (���� �ڵ� Ȱ��) 
//		
//		
//		ResponseEntity<Pizza> response = null;
//		
//		//ResponseEntity.ok() : Http �����ڵ� 200�� ������ �����.
//		//���̷ε带 �Ǿ� ���� �� ���� �ٵ� ���̷ε��� Ÿ���� ���׸����� ��������� ��.
//		response = ResponseEntity.ok(null);
//		
//		//ResponseEntity.notFound() : Http �����ڵ� 404�� ������ �����.
//		response = ResponseEntity.notFound().build(); //���Ŀ��� �����͸� �Ǿ� ���� �� ����
		
//		//�����Ӱ� ���ϴ� ������� ���� �����
//		response = ResponseEntity.status(HttpStatus.OK)
//					.contentType(MediaType.APPLICATION_JSON)
//					.body(service.getPizza(22)); 
//		//Http �����ڵ� ���� http://www.incodom.kr/Status_code _ ex. FORBIDDEN�� 404�� ��Ÿ���� ���
//		return response;
		
	}

 

	
	
	@DeleteMapping(value ="/sample/pizza/delete/{id}") 
	public Integer deletePizza(@PathVariable Integer id) {		
		return service.deletePizza(id);
	}
	
	
	

	
	
/*

//��� �ּ�ó�� �ϰ���
	@PostMapping(value="/sample/pizza/insert")
	public String insertPizza(@RequestBody Pizza pizza) {

		try {
			return service.InsertPizza(pizza).toString();
		} catch(Exception e) {
			return "0 : " + e; 
		}
		
		//������ <Integer>1</Integer>�� ǥ�õż� �̰ɷ� if���� ���� �� ������ ���ڿ��� �ٲ㼭 �����ϰ���

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

	//�� Ǯ��. service���� �� pizzaMapper�θ� ������ ������
	
	@Autowired
	PizzaMapper pmapper;

	//ex : http://localhost:8888/restful/sample/pizza/read/22
	//����1. GET������� ������ ID�� �Բ� ��û�� ������ �ش� ������ ������ JSON �������� ��ȯ 
	@GetMapping(value="/sample/pizza/read/{p_id}", produces = MediaType.APPLICATION_JSON_VALUE) //������ ���� �߸� produces=MediaType.APPLICATION_JSON_VALUE_UTF-8 �� ��ü�غ���
	public Pizza getPizzaByPid(@PathVariable Integer p_id) {
	
		log.info(pmapper.findById(p_id));
		return pmapper.findById(p_id);
	}
	
	//����2. POST������� �Ķ������ ���� �����Ϳ� �Բ� ��û�� ������ �ش� ���ڸ� DB�� �߰�
	//�� ������ chrome �ּҿ� ��û�� �޾ƺ��� Ȯ�� ���α׷� �޾Ƽ� �׽�Ʈ �غ��� �� 
	//chrome-extension://ehafadccdcdedbhcbddihehiodgcddpl/index.html (�� �̰� �ƴϾ ��)
	
	//public Integer insertPizza(Pizza pizza) {	
	//�̷��� ������ �ڵ� ���ε� �� �ȴ�
	//@RestController������ @RequestBody�� �������� ������ �Ķ���Ͱ� �ڵ� ���ε� ���� �ʴ´�.
	@PostMapping(value = "/sample/pizza/insert")
	public Integer insertPizza(@RequestBody Pizza pizza) {
		pmapper.insertMenu(pizza.getP_name(), pizza.getP_price(), pizza.getP_calories());
		return 3333; //����Ÿ���� Integer�� �׳� ���ڰ� ���� �Ǵ� �ŷ�
	}
	
	//����3. PUT������� ���� �����͸� �Բ� ��û�� ������ �ش� ������ ������ ����
	//�Ķ���ͷ� �޾Ƽ� �Ķ���ͷ� �ѱⷡ
	@PutMapping(value="/sample/pizza/update")
	public Integer updatePizza(@RequestBody Pizza pizza) {
		pmapper.updatePizza(pizza.getP_id(), pizza.getP_name(), pizza.getP_price(), pizza.getP_calories());
		return 444; 
	}
	
	//����4. DELETE������� ������ ID�� �Բ� ��û�� ������ �ش� ���ڸ� DB���� ����
	@DeleteMapping(value="/sample/pizza/delete/{p_id}")
	public Integer deletePizza(@PathVariable Integer p_id) {
		pmapper.deleteMenu(p_id);
		return 555;
}
 */

	
}
