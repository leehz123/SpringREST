package com.ezen.restful;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.restful.dto.Pizza;
import com.ezen.restful.service.AjaxServiceImpl;
import com.ezen.restful.service.PizzaService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class HomeController {

	@Autowired
	AjaxServiceImpl ajaxService;
	
	@Autowired
	PizzaService service;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

		
	//http://localhost:8888/restful/ajax
	@GetMapping("/ajax")
	public String ajax(Model model) {
		model.addAttribute("pizzas", ajaxService.getPizzaList());
		return "ajax";
	}	
}
