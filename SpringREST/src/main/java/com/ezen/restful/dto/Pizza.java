package com.ezen.restful.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pizza {

	private Integer p_id;
	private String p_name;
	private Integer p_price;
	private Double p_calories;
	
}
