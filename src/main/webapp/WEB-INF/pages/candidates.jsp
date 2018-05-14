<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Cache-control" content="No-Cache">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script> 
</head>
<body>


<C:choose>
<C:when test="${usermode == 'HR'}">
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand/logo -->
  <a class="navbar-brand" href="#">IES</a>
  
  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="candidates">Candidates</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="newCandidate">Add Candidate</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="report">Evaluation Status</a>
    </li>
  </ul>
  <ul class="nav navbar-nav ml-auto">
       <li class="nav-item">
             <a class="nav-link" href="logout" id="log">Logout</a>
       </li>
   </ul>  
</nav>

<div class="container" style="align:center">

<C:choose>
<C:when test="${mode == 'CANDIDATE_VIEW'}">
<h2>List of Candidates</h2>
<table class="table table-dark">
    <thead>
      <tr>
        <th>First Name</th>
        <th>Technical Skills</th>
        <th>Years of Experience</th>
        <th>Interviewer</th>        
        <th>Email ID</th>
        <th>Status</th>
        <th>Edit</th>
        <th>Delete</th>
      </tr>
    </thead>
    <tbody>
      <C:forEach var="candidate" items="${candidates}">
      		<tr>
      			<td>${candidate.firstName}</td>
      			<td>${candidate.techSkills}</td>
      			<td>${candidate.yearsOfExp}</td>
      			<td>${candidate.user.firstName}</td>     			
      			<td>${candidate.emailId}</td>
      			<C:choose>
      				<C:when test="${candidate.evaluated}">
      					<td><a href="#"><i class="fa fa-check-square"></i></a> Done</td>
      				</C:when>
      				<C:otherwise>
      					<td></td>
      				</C:otherwise>     			
      			</C:choose>
      			<td><a href="/InterviewEvaluationSystem/updateCandidate?id=${candidate.id}"><i class="fa fa-pencil"></i></a></td>
      			<td><a href="/InterviewEvaluationSystem/deleteCandidate?id=${candidate.id}"><i class="fa fa-trash"></i></a></td>
      		</tr>
      </C:forEach>
    </tbody>
  </table> 
  </C:when>
 <C:when test="${mode == 'CANDIDATE_EDIT' || mode == 'CANDIDATE_NEW'}">
 <div class="container" style="padding-left:200px;">
 <h2>Enter new candidates details:</h2>
	  <form action="/InterviewEvaluationSystem/saveCandidate" method="POST">
	  <input type="hidden" class="form-control" value="${candidate.id}" name="id" id="id">
	  <input type="hidden" class="form-control" value="${candidate.evaluated}" name="evaluated" id="id">
		  <div class="form-group">
		    <input type="text" class="form-control col-md-6" id="firstName" placeholder="First name" name="firstName" value="${candidate.firstName}" required>
		  </div>
		  <div class="form-group">
		    <input type="text" class="form-control col-md-6" id="lastName" placeholder="last name" name="lastName" value="${candidate.lastName}">
		  </div>
		  <div class="form-group">
		    <input type="number" min=0 class="form-control col-md-6" id="yearsOfExp" placeholder="Years of experience" name="yearsOfExp" value="${candidate.yearsOfExp}">
		  </div>
		  <div class="form-group">
		    <input type="text" class="form-control col-md-6" id="techSkills" placeholder="Skills" name="techSkills" value="${candidate.techSkills}">
		  </div>
		  <div class="form-group">
		    <input type="email" class="form-control col-md-6" id="emailId" placeholder="Email" name="emailId" value="${candidate.emailId}">
		  </div>
		  <div class="form-group">
		    <input type="text" class="form-control col-md-6" id="phoneNumber" placeholder="Phone numbers" name="phoneNumber" value="${candidate.phoneNumber}">
		  </div>
		  <div class="form-group">	
		   <select id="user.id" name="user.id">
		   		<option value = 0>Select Interviewer</option>	  
			    <C:forEach var="interviewer" items="${usersList}">
			    	<option value="${interviewer.id}" ${interviewer.id == candidate.user.id ? "selected" : ""}>${interviewer.firstName}</option> 
	            </C:forEach>
           </select>
		  </div> 
		  
		  <button type="submit" class="btn btn-primary">Submit</button>
		  <a class="btn btn-primary" href="candidates" role="button">Back</a>
	</form>
</div>
  </C:when>
 </C:choose>
</div>
</C:when>
</C:choose>
<C:choose>
<C:when test="${usermode == 'INTERVIEWER'}">
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="#">IES</a>
   <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="showList?loginUserId=${loginUserId.id}">Candidates</a>
    </li>
  </ul> 
   <ul class="nav navbar-nav ml-auto">
       <li class="nav-item">
             <a class="nav-link" href="logout" id="log">Logout</a>
       </li>
   </ul> 
   
</nav>
 <div class="container" style="align:center">

<C:choose>
<C:when test="${interviewmode == 'SPEC_CANDIDATE_VIEW'}">
<h2>List of Candidates</h2>
<table class="table table-dark">
    <thead>
      <tr>
        <th>First Name</th>
        <th>Technical Skills</th>
        <th>Years of Experience</th>
        <th>Status</th>
        <th>Evaluate</th>

      </tr>
    </thead>
    <tbody>
      <C:forEach var="candidate" items="${candidates}">
      		<tr>
      			<td>${candidate.firstName}</td>
      			<td>${candidate.techSkills}</td>
      			<td>${candidate.yearsOfExp}</td>
      			<C:choose>
      				<C:when test="${candidate.evaluated}">
      					<td><a href="#"><i class="fa fa-check-square"></i></a> Done</td>
      				</C:when>
      				<C:otherwise>
      					<td></td>
      				</C:otherwise>     			
      			</C:choose>
      			<C:choose>
      				<C:when test="${candidate.evaluated}">
      					<td>Evaluated</td>
      				</C:when>
      				<C:otherwise>
      					<td><a href="/InterviewEvaluationSystem/evaluateCandidate?id=${candidate.id}&loginUserId=${loginUserId.id}"><i class="fa fa-pencil"></i></a></td>
      				</C:otherwise>     			
      			</C:choose>     			
      		</tr>
      </C:forEach>
    </tbody>
  </table> 
  </C:when>
  </C:choose>
  
<C:choose>
<C:when test="${interviewmode == 'EVALUATE_VIEW'}">

<div class="container" style="padding-left:250px;">
<h2>Evaluate the candidate: ${candidate.firstName}</h2>
	<form action="saveEvaluation?id=${candidate.id}&loginUserId=${loginUserId.id}" method="POST">
	 <div class="row" style= "margin-top:10px;" >
		    <input type="number" min=1 max=3 class="form-control col-md-1" id="skill1" placeholder="skill one" name="skillId1" value="1" style="margin-right:10px;" value="${evaluationForm.skillId1}" />
		    <input type="text" class="form-control col-sm-2" id="skill1" placeholder="score" name="points1" style="margin-right:10px;" value="${evaluationForm.points1}" required/>
		    <input type="text" class="form-control col-sm-2" id="comments1" placeholder="comments" name="comments1" style="margin-right:10px;" value="${evaluationForm.comments1}" />
	</div>
	<div class="row" style= "margin-top:10px;" >
		    <input type="number" min=1 max=3 class="form-control col-md-1" id="skill2" placeholder="skill two" name="skillId2" value="2" style="margin-right:10px;"  value="${evaluationForm.skillId2}" />
		    <input type="text" class="form-control col-sm-2" id="skill2" placeholder="score" name="points2" style="margin-right:10px;" value="${evaluationForm.points2}" required/>
		    <input type="text" class="form-control col-sm-2" id="comments2" placeholder="comments" name="comments2" style="margin-right:10px;" value="${evaluationForm.comments2}" />
	</div>
	<div class="row" style= "margin-top:10px;">
		    <input type="number" min=1 max=3 class="form-control col-md-1" id="skill3" placeholder="skill three" name="skillId3" value="3" style="margin-right:10px;" value="${evaluationForm.skillId3}" />
		    <input type="text" class="form-control col-sm-2" id="skill3" placeholder="score" name="points3" style="margin-right:10px;" value="${evaluationForm.points3}" required/>
		    <input type="text" class="form-control col-sm-2" id="comments3" placeholder="comments" name="comments3" style="margin-right:10px;" value="${evaluationForm.comments3}" />
	</div>
	
		<button type="submit" class="btn btn-primary" style= "margin-top:10px; margin-left:150px">Submit</button>
	</form>
</div>	
  </C:when>
  </C:choose>
  </div>
</C:when>
</C:choose>
<!-- <script type="text/javascript">

var element = document.getElementById('log');
element.onclick = logout;
function logout(){
	console.log("Hello Babu");
	var url = window.location.href;
	window.history.go(-window.history.length);
	window.location.href = url;
}
</script> -->

</body>
</html>