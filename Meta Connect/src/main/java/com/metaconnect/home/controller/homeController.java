package com.metaconnect.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MetaConnect
 *
 */
@Controller
public class homeController {
	
	/** 
	 * @return will return to index page
	 */

	@RequestMapping("/")
	public String index(){
		
		return "index";
	}
	
}
