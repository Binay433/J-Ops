package com.xchanging.ops.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xchanging.ops.model.User;
import com.xchanging.ops.model.UserProfile;
import com.xchanging.ops.service.UserProfileService;
import com.xchanging.ops.service.UserService;



@Controller
@RequestMapping("/users")
@SessionAttributes("roles")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserProfileService userProfileService;
	
	
	@Autowired
	MessageSource messageSource;

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = {"/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "userslist";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [loginid] should be implementing custom @Unique annotation 
		 * and applying it on field [loginid] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!userService.isUserLoginIdUnique(user.getId(), user.getLoginId())){
			FieldError loginidError =new FieldError("user","loginId",messageSource.getMessage("non.unique.loginId", new String[]{user.getLoginId()}, Locale.getDefault()));
		    result.addError(loginidError);
			return "registration";
		}
		
		userService.save(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		//return "success";
		return "registrationsuccess";
	}


	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{loginId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String loginId, ModelMap model) {
		User user = userService.findByLoginId(loginId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{loginId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String loginId) {

		if (result.hasErrors()) {
			return "registration";
		}
		userService.update(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		return "registrationsuccess";
	}

	
	/**
	 * This method will delete an user by it's loginId value.
	 */
	@RequestMapping(value = { "/delete-user-{loginId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String loginId) {
		userService.deleteUserByLoginId(loginId);
		return "redirect:/users/list";
	}
	

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	
	@RequestMapping(value = { "/profile-{id}" }, method = RequestMethod.GET)
	public String getUserProfile(@PathVariable Integer id,ModelMap model) {
		User user = userService.findById(id);
		model.addAttribute("msgColor", null);
		model.addAttribute("user",user);
		return "myprofile";
	}
	
	@RequestMapping(value = { "/profile-{id}" }, method = RequestMethod.POST)
	public String updateUserProfile(@Valid User user, BindingResult result,
			ModelMap model) {
		User user1 = userService.findById(user.getId());
		if(user1.getPassword().equals(user.getExistingPassword())){
			user.setPassword(user.getNewPassword());
			user.setServicepoint(user1.getServicepoint());
			user.setUserProfiles(user1.getUserProfiles());
			user.setState(user1.getState());
			userService.update(user);
			model.addAttribute("msgColor", "#e9fce9");
			model.addAttribute("message", "<h3><strong>Congratullations! </strong>Password Changed Successfully!!!</h3>");
			return "myprofile";
		}else{
			model.addAttribute("failure", "1");
			model.addAttribute("msgColor", "#ffeee6");
			model.addAttribute("message", "<h3><strong>Sorry! </strong>Password entered doesn't match existing password. Please enter existing password again!!</h3>");
			return "myprofile";
		}
	}

}
