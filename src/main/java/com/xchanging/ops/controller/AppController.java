package com.xchanging.ops.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xchanging.ops.configuration.CustomSuccessHandler;
import com.xchanging.ops.model.User;
import com.xchanging.ops.service.ConfigService;
import com.xchanging.ops.service.UserService;
import com.xchanging.ops.utils.CommonUtils;
import com.xchanging.ops.utils.RefDataUtil;

@Controller
@RequestMapping("")
@SessionAttributes("roles")
public class AppController {

	private static Logger logger = LoggerFactory.getLogger(AppController.class);
	@Autowired
	UserService userService;
	@Autowired
	ConfigService service;
	
	/**
	 * This method is entry point to the application.
	 */

	@RequestMapping(value = {"/", "/login","/home"}, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		String homeUrl="login";
		if(CommonUtils.isLoggedIn()){
			CustomSuccessHandler handler = new CustomSuccessHandler();
			homeUrl=handler.setTargetUrl(SecurityContextHolder.getContext().getAuthentication());
			User user =CommonUtils.getCurrentUser();
			logger.info(user.getLoginId()+" logged in");
			model.addAttribute("user", user);
			
		}
		//model.addAttribute("version",(String)RefData.getAppProps().get("appVersion"));
		model.addAttribute("version",(String)System.getProperty("appVersion"));
		return homeUrl;
	}
	
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && CommonUtils.isLoggedIn()){ 
			User user =CommonUtils.getCurrentUser();
			new SecurityContextLogoutHandler().logout(request, response, auth);
			logger.info(user.getLoginId()+" logged out");
		}
		
		return "redirect:/login?logout";
	}
	
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("accessMessage", "You are not authorise to access this page");
		return "home";
	}
}
