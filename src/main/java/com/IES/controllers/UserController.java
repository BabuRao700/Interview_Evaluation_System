package com.IES.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.IES.models.User;
import com.IES.models.UserRoles;
import com.IES.services.UserRolesService;
import com.IES.services.UsersService;

@Controller
public class UserController {
	
   @Autowired	
   private UserRolesService userRolesService;
   
   @Autowired
   private UsersService usersService;
   
	@RequestMapping("/home")
     public ModelAndView getUserRoles(ModelAndView model, HttpServletRequest req) {
	   List<UserRoles> userRoles=userRolesService.getUserRoles();
	   req.setAttribute("meassage", null);
	   req.setAttribute("mode", "LOGIN");
 	   model.addObject("userRoles",userRoles);
	   model.setViewName("index");
	   return model;
   }
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	  public ModelAndView loginProcess(@ModelAttribute("user") User loginUser,RedirectAttributes redirectAttrs,HttpServletRequest req ) {
	    ModelAndView mav = null;
	    List<User> users=usersService.getAllUsers();
	    boolean isValidUser=false;
	    int loginUserID = 0;
	    for (User user : users) {
			if(user.getUserName().equals(loginUser.getUserName()) && user.getPassword().equals(loginUser.getPassword())) {
				isValidUser=true;
				loginUserID=user.getId();				
			}
	     }	    
	       if(isValidUser) {
	    	   redirectAttrs.addFlashAttribute("loginUserID", loginUserID);
	    	   return new ModelAndView("redirect:" + "http://localhost:8080/InterviewEvaluationSystem/candidates");
	       }
		   else {
			   	req.setAttribute("mode", "LOGIN");
				mav = new ModelAndView("index");
				mav.addObject("message", "UserName or Password is wrong!");
			}
	       return mav;
		}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signUp(ModelAndView model,  HttpServletRequest req) {		
		model.setViewName("index");
		req.setAttribute("mode", "SIGNUP");
		return model;
	}
	
	@RequestMapping(value ="/save", method = RequestMethod.POST)
	public String processSignUp(@ModelAttribute("user") User user, HttpServletRequest req, ModelAndView model)
	{
		try {
		     usersService.addNewUser(user);
		     req.setAttribute("mode", "LOGIN");
		     req.setAttribute("meassage", "SUCCESS");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "index";
	}
}
