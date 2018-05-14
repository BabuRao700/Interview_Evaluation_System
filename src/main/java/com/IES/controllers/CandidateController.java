package com.IES.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.IES.models.Candidate;
import com.IES.models.EvaluationForm;
import com.IES.models.EvaluationPoint;
import com.IES.models.User;
import com.IES.services.CandidateService;
import com.IES.services.UsersService;


@Controller
public class CandidateController {
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private UsersService usersService;
	
	int redirect = 0;
	int currentuserID = 0;
	 
	@RequestMapping("/candidates")
	public String getCandiates(ModelAndView model,HttpServletRequest req, Model loggedUser){
		try {
			
		 int typeOfUser=0;	
		 int currentLoggedUser = ((loggedUser != null && loggedUser.asMap().get("loginUserID") != null) ?
				 				(int)loggedUser.asMap().get("loginUserID") : 0);
		 List<Candidate> candidates= candidateService.getAllCandidates();
		 	
		 User userId=new User();
		 List<User> userList=usersService.getAllUsers();
		 
		 for (User user : userList) {
			if(user.getId() == currentLoggedUser)
			{
				typeOfUser=user.getRoleId();
				userId=user;
				currentuserID = currentLoggedUser;
				
			}
		}
		 
		 if(typeOfUser == 1)
		 {
			 req.setAttribute("candidates", candidates);
			 req.setAttribute("mode", "CANDIDATE_VIEW");
			 req.setAttribute("usermode", "HR");
			 req.setAttribute("usersList", usersService.getAllUsersByRole());
		 }
		 else if(typeOfUser == 2 || redirect == 1)
		 {
			 List<Candidate> currentInterviewerList= candidates.stream().filter(x -> x.getUser().getId() == currentuserID).collect(Collectors.toList());
			 req.setAttribute("candidates", currentInterviewerList);
			 req.setAttribute("usermode", "INTERVIEWER");
			 req.setAttribute("interviewmode","SPEC_CANDIDATE_VIEW");
			 req.setAttribute("loginUserId", userId);
			 redirect=1;
			 
		 }
		 else {
			 req.setAttribute("candidates", candidates);
			 req.setAttribute("mode", "CANDIDATE_VIEW");
			 req.setAttribute("usermode", "HR");
			 req.setAttribute("usersList", usersService.getAllUsersByRole());
		 }
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
 
		 return "candidates";
	}
	
	@RequestMapping("/updateCandidate")
	public String updateCandidate(@RequestParam int id,  HttpServletRequest req) {
		try {
		req.setAttribute("candidate", candidateService.getCandidateById(id));
		req.setAttribute("usermode", "HR");
		req.setAttribute("mode", "CANDIDATE_EDIT");
		req.setAttribute("usersList", usersService.getAllUsersByRole());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "candidates"; 
	}
	
	@RequestMapping(value = {"/saveCandidate"}, method=RequestMethod.POST)
	public String init(@ModelAttribute("candidate") Candidate candidate, BindingResult bindingResult, HttpServletRequest req) {
		try {
			/*//Candidate previusRecord=candidateService.getCandidateById(candidateId)
			candidate.isEvaluated();*/
		candidateService.addCandidate(candidate);
		req.setAttribute("candidates", candidateService.getAllCandidates());
		req.setAttribute("usermode", "HR");
		req.setAttribute("mode", "CANDIDATE_VIEW");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "candidates"; 
	} 
	
	@RequestMapping("/newCandidate")
	public String newBook(HttpServletRequest req) {
		try {
		req.setAttribute("usermode", "HR");
		req.setAttribute("mode","CANDIDATE_NEW");
		req.setAttribute("usersList", usersService.getAllUsersByRole());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "candidates";
	}
	
	@RequestMapping("/deleteCandidate")
	public String deleteBook(@RequestParam int id, HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
		candidateService.deleteCandidate(id);
		req.setAttribute("candidates", candidateService.getAllCandidates());
		req.setAttribute("usermode", "HR");
		req.setAttribute("mode", "CANDIDATE_VIEW");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "candidates";
	}
	
	@RequestMapping("/evaluateCandidate")
    public String evaluateCandidate(@RequestParam int id, 
    								@RequestParam int loginUserId, HttpServletRequest req) {
		try {
		req.setAttribute("usermode", "INTERVIEWER");
		req.setAttribute("interviewmode","EVALUATE_VIEW");
		req.setAttribute("loginUserId",usersService.getUser(currentuserID));
		req.setAttribute("candidate",candidateService.getCandidateById(id));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "candidates";	
	}
	
	@RequestMapping(value = {"/saveEvaluation"}, method=RequestMethod.POST)
	public String processEvaluation(@RequestParam int id,
									@RequestParam int loginUserId,
									@ModelAttribute("evaluationForm") EvaluationForm evaluationForm,
									HttpServletRequest req,
									ModelAndView model) {
		
		try {
		EvaluationPoint point1=new EvaluationPoint();
		point1.setId(1);
		point1.setSkillId((int)evaluationForm.getSkillId1());
		point1.setPoints((int)evaluationForm.getPoints1());
		point1.setComments((String)evaluationForm.getComments1());
		point1.setCandidateId(id);
		
		candidateService.insertEvaluationPoints(point1);
		
			
		EvaluationPoint point2=new EvaluationPoint();
		point2.setSkillId((int)evaluationForm.getSkillId2());
		point2.setPoints((int)evaluationForm.getPoints2());
		point2.setComments((String)evaluationForm.getComments2());
		point2.setCandidateId(id);
		
		candidateService.insertEvaluationPoints(point2);
		
		EvaluationPoint point3=new EvaluationPoint();
		point3.setSkillId((int)evaluationForm.getSkillId3());
		point3.setPoints((int)evaluationForm.getPoints3());
		point3.setComments((String)evaluationForm.getComments3());
		point3.setCandidateId(id);
		
		candidateService.insertEvaluationPoints(point3);
		
		candidateService.updateCandidateStatus(id);	
		
		List<Candidate> candidates= candidateService.getAllCandidates();
		 List<Candidate> currentInterviewerList= candidates.stream().filter(x -> x.getUser().getId() == currentuserID).collect(Collectors.toList());
		 req.setAttribute("candidates", currentInterviewerList);
		 req.setAttribute("usermode", "INTERVIEWER");
		 req.setAttribute("interviewmode","SPEC_CANDIDATE_VIEW");
		 req.setAttribute("loginUserId",usersService.getUser(loginUserId));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "candidates";
	}
	
	@RequestMapping("/showList")
	public String showList(@RequestParam int loginUserId,HttpServletRequest req, ModelAndView model) {
		
		 List<Candidate> candidates= candidateService.getAllCandidates();
		 List<Candidate> currentInterviewerList= candidates.stream().filter(x -> x.getUser().getId() == currentuserID).collect(Collectors.toList());
		 req.setAttribute("candidates", currentInterviewerList);
		 req.setAttribute("usermode", "INTERVIEWER");
		 req.setAttribute("interviewmode","SPEC_CANDIDATE_VIEW");
		 req.setAttribute("loginUserId",usersService.getUser(loginUserId));
		
		return "candidates";
	}
	@RequestMapping("/report")
	public String viewReport(HttpServletRequest req) {
		req.setAttribute("notEvaluatedcandidates", candidateService.getAllNotEvaluatedCandidates());
		req.setAttribute("evaluatedcandidates", candidateService.getAllEvaluatedCandidates());
		return "report";
	}
	
	@RequestMapping("/logout")
	public String signout(HttpServletRequest request)
	{
			HttpSession httpSession = request.getSession();
			httpSession.removeAttribute("User");
	        httpSession.invalidate();
	        return "redirect:/home";
	}
		

}
