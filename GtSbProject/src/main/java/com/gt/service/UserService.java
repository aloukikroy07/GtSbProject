package com.gt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gt.model.User;
import com.gt.repository.UserRepositoryClass;

@Service
public class UserService {
	
	
	@Autowired
	UserRepositoryClass userRepository;
	
	public List<User> findUserDataByUserId(User u) {
		List<User> userData = userRepository.findUserById(u);
		return userData;
	}
	
	public int addUser(User u, Integer loggedUser) {
		int result = userRepository.addUser(u, loggedUser);
		return result;
	}
	
	public List<User> getAllUsers() {
		List<User> userData = userRepository.getAllUsers();
		return userData;
	}
	
	
	public Integer deleteUser(Integer id) {
		Integer result = userRepository.deleteUser(id);
		return result;
	}
	
	public Integer UpdateUser(Integer loggedUser, Integer id, String uname, String fname, String eaddr, String mobno, String pass) {
		Integer result = userRepository.UpdateUser(loggedUser, id, uname, fname, eaddr, mobno, pass);
		return result;
	}
	
	public String ChangePassword(String oldPassword, String newPassword, Integer userID) {
		String result = userRepository.ChangePassword(oldPassword, newPassword, userID);
		return result;
	}
	
	public List<User> GetAllUsers(){
		List<User> userData = userRepository.getAllUsers();
		return userData;
	}

}
