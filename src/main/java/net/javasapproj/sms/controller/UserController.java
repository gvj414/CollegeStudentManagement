package net.javasapproj.sms.controller;

import java.util.Map;

import org.apache.catalina.filters.AddDefaultCharsetFilter.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.javasapproj.sms.entity.User;
import net.javasapproj.sms.service.StudentService;
import net.javasapproj.sms.service.UserService;

@Controller
public class UserController {
	
	
	private UserService userserice;
	
	public UserController(UserService userserice) {
		super();
		this.userserice = userserice;
	}
	@GetMapping("/Teachers")
	public Object addUser(@RequestBody User user,Model model) throws Exception {
		model.addAttribute("Signup", userserice.addUser(user));
		return "Signup";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json" })
	public Map<String, String> login(@RequestBody User user) throws Exception {
		return userserice.login(user);
	} 

}
