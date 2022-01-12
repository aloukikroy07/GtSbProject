package com.gt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gt.model.User;
import com.gt.service.UserService;


@Controller
@CrossOrigin(origins = "*")
public class AppController {

	@Autowired
	private UserService userService;
	
	Integer loggedUser=0;
	String loggedUserName = "";

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/login")
	public String showUserDataByUserId(Model model, @ModelAttribute("User") User user) throws IOException {
		List<User> userData = userService.findUserDataByUserId(user);
		
		String msg = user.getLoginMessage(); 
		
		if (!userData.isEmpty()) {
			Integer loggedInUser=userData.get(0).getIdUserKey();
			String  userName = userData.get(0).getTxUserFulName();
			model.addAttribute("uName", userName);
			loggedUser = loggedInUser;
			loggedUserName = userName;
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
	@RequestMapping(value = "/home")
	public String Home(Model model) throws IOException {
		return "home";
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
		Integer loggedUserId=loggedUser;
		int userData = userService.addUser(user, loggedUserId);
		
		if (userData != 0) {
			return "redirect:/user";
		}
		
		else {
			return "redirect:/allusers";
		}
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/allusers", method = RequestMethod.GET)
	public String AllUsers(Model model) throws IOException {
		List<User> getAllUsers = userService.getAllUsers();
		model.addAttribute("allUsersList", getAllUsers);
		return "AddUser";
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String DeleteUser(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer result = userService.deleteUser(id);
		if(result == 1) {
			return "redirect:/user";
		}
		else {
			return "redirect:/user";
		}
		
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public String UpdateUser(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String uname= request.getParameter("uname");
		String fname= request.getParameter("fname");
		String eaddr= request.getParameter("eaddr");
		String mobno= request.getParameter("mobno");
		String pass= request.getParameter("pass");
		Integer result= userService.UpdateUser(loggedUser, id, uname, fname, eaddr, mobno, pass);
		if(result == 1) {
			return "redirect:/user";
		}
		else {
			return "redirect:/user";
		}
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/logout")
	public String UserLogOut(Model model, @ModelAttribute("User") User user) throws IOException {

		return "index";
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/changepassword")
	public String ChangePassword(Model model, @ModelAttribute("User") User user) throws IOException {
		return "ChangePassword";
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/changepass", method = RequestMethod.POST)
	public String ChangePass(Model model, @ModelAttribute("User") User user) throws IOException {
		String result = userService.ChangePassword(user.getOldPassword(), user.getNewPassword(), loggedUser);
		if (result.equals("failed")) {
			return "WrongOldPass";
		}
		else {
			return "index";
		}
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/permissions")
	public String Permissions(Model model) throws IOException {
		List<User> userData = userService.getAllUsers();
		model.addAttribute("users",userData);
		return "permissions";
	}
}
