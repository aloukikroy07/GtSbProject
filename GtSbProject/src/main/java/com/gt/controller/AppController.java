package com.gt.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gt.model.Menu;
import com.gt.model.SubMenu;
import com.gt.model.User;
import com.gt.service.UserService;


@Controller
@CrossOrigin(origins = "*")
public class AppController {

	@Autowired
	private UserService userService;
	
	Integer loggedUser=0;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/home")
	public String showUserDataByUserId(Model model, @ModelAttribute("User") User user) throws IOException {
		List<User> userData = userService.findUserDataByUserId(user);
		
		String msg = user.getLoginMessage(); 
		
		if (!userData.isEmpty()) {
			Integer loggedInUser=userData.get(0).getIdUserKey();
			loggedUser = loggedInUser;
			return "home";
		}
		
		else if (msg != null && !msg.isEmpty()) {
			return "home";
		}
		
		else {
			return "login";
		}
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String openUserPage(Model model) throws IOException {
		List<User> getAllUsers = userService.getAllUsers();
		model.addAttribute("allUsersList", getAllUsers);
		return "users";
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String addUser(Model model, @ModelAttribute("User") User user) throws IOException {
		String result = "invalid";
		Integer loggedUserId=loggedUser;
		int userData = userService.addUser(user, loggedUserId);
		
		if (userData != '0') {
			List<User> getAllUsers = userService.getAllUsers();
			model.addAttribute("allUsersList", getAllUsers);
			return "redirect:/user";
		}
		
		else {
			List<User> getAllUsers = userService.getAllUsers();
			model.addAttribute("allUsersList", getAllUsers);
			return "redirect:/user";
		}
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/logout")
	public String UserLogOut(Model model, @ModelAttribute("User") User user) throws IOException {

		return "index";
	}
	

//	@CrossOrigin(origins = "*")
//	@RequestMapping(value = "/submenu", method = RequestMethod.POST, consumes = "application/json")
//	@ResponseBody
//	public List<Menu> showMenuByUserId(@RequestBody User user) {
//		//List<Menu> menuData = userService.findMenuByUserId(user);
//		return menuData;
//	}
	
}
