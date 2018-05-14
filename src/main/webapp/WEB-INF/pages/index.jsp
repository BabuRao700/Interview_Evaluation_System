<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
  <title>Interview Evaluation System</title>
  <meta charset="utf-8">
  <meta http-equiv="Cache-control" content="No-Cache">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
  </head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand/logo -->
  <a class="navbar-brand" href="#">IES</a>
</nav>

<C:choose>
<C:when test="${mode == 'LOGIN'}">		
<div class="container my-form mx-auto" style="align:center; padding-top: 200px; width=30%;" >
	<form action="/InterviewEvaluationSystem/login" method="POST" id="user">
	   <div class="row">
	   	 <div class="col-md-3"></div>
           <div class="col-md-4">
              <h3>Login</h3>
               <hr>
           </div>
      </div>
      <div class="row" style="padding-top: 1rem">
      	<div class="col-md-3"></div>
	  		<div class="form-group col-md-4">	    
	    		<input type="text" class="form-control" id="username" name="userName" placeholder="username" value="${user.userName}">
	  		</div>
	  </div>
	  <div class="row" style="padding-top: 1rem">
	  <div class="col-md-3"></div>
	  	<div class="form-group col-md-4">
	    	<input type="password" class="form-control" id="pwd" name="password" placeholder="password" value="${user.password}"> 
	  	</div>
	  </div>
	  <div class="row" style="padding-top: 1rem">
           <div class="col-md-4"></div>
             <div class="col-md-6">
                    <button type="submit" class="btn btn-success"><i class="fa fa-sign-in"></i> Login</button>
                    <a class="btn btn-link" href="/InterviewEvaluationSystem/signup">Sign Up?</a>
              </div>
      </div>
    </form>
    	<C:choose>
		<C:when test="${message != null}">
			<!-- <div style="color: red; padding-left:300px">Wrong user name or password</div> -->
			<script>alert("Username or password is wrong!");
			</script>
		</C:when>
		
		<C:when test="${meassage == 'SUCCESS'}">
			<script>alert("Registered Successfully, please login now");
			</script>
		</C:when>
		
	</C:choose>	
	</div>
	</C:when>
	</C:choose>
	
	<C:choose>
	<C:when test="${mode == 'SIGNUP'}">
	<div class="container my-form mx-auto" style="padding-left:200px;padding-top: 150px; width=30%;">	
		<form action="save" method="POST">
		<div class="col-md-6">
              <h3>Sign Up</h3>
               <hr>
           </div>
		  <div class="row col-md-6" style="margin-left: 2px; margin-bottom: 14px; ">
		  	 <input type="text" class="form-control col-md-5" id="firstName" placeholder="First Name" style="margin-right:70px;"  name="firstName"  value="${user.firstName}" required>		  	 
		  	 <input type="text" class="form-control col-md-5" id="lastName" placeholder="Last Name" name="lastName" value="${user.lastName}">
		  </div>
		  <div class="form-group col-md-6">
		    <input type="text" class="form-control" id="userName" placeholder="Enter Username" name="userName" value="${user.userName}" required>
		  </div> 
		  <div class="form-group col-md-6">
		    <input type="number" min=1 max=2 class="form-control" id="userType" placeholder="Role ID" name="roleId" value= "${user.roleId}">
		  </div>
		  <div class="form-group col-md-6">
		    <input type="password" class="form-control" id="password" placeholder="Enter Password" name="password" value="${user.password}" required>
		  </div>
		  <div class="form-group col-md-6">
		    <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password" name="confirmPassword" required>
		  </div> 
		
		  <button style="margin-left: 160px;" type="submit" class="btn btn-primary">Submit</button>
		  <a class="btn btn-link" href="home">Login</a>
    </form>
 </div>
	</C:when>
	</C:choose>	
	<script>
	
			$(document).ready(function() {
		        function disableBack() { window.history.forward() }
		        
		        window.onload = disableBack();
		        window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
		    });
	
			
			history.pushState(null, null, location.href);
		    window.onpopstate = function () {
		        history.go(1);
		    };
		    		    
			var password = document.getElementById("password")
			  , confirm_password = document.getElementById("confirmPassword");
		
			function validatePassword(){
			  if(password.value != confirm_password.value) {
			    confirm_password.setCustomValidity("Passwords Don't Match");
			  } else {
			    confirm_password.setCustomValidity('');
			  }
			}
		
			password.onchange = validatePassword;
			confirm_password.onkeyup = validatePassword;
			
			
			
	</script>
</body>
</html>

