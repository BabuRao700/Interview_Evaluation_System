<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta http-equiv="Cache-control" content="No-Cache">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>


<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="#">IES</a>
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="candidates">Candidates</a>
    </li>
  </ul>
  
  <ul class="nav navbar-nav ml-auto">
       <li class="nav-item">
             <a class="nav-link" href="logout">Logout</a>
       </li>
   </ul>  
   
</nav>

<div class="container" style="align:center">
<h3>Candidates yet to be evaluated:</h3>
<table class="table table-dark">
    <thead>
      <tr>
        <th>First Name</th>
        <th>Technical Skills</th>
        <th>Years of Experience</th>
        <th>Interviewer</th>
        <th>Email ID</th>
      </tr>
    </thead>
    <tbody>
      <C:forEach var="candidate" items="${notEvaluatedcandidates}">
      		<tr>
      			<td>${candidate.firstName}</td>
      			<td>${candidate.techSkills}</td>
      			<td>${candidate.yearsOfExp}</td>
      			<td>${candidate.user.firstName}</td>
      			<td>${candidate.emailId}</td>
      		</tr>
      </C:forEach>
    </tbody>
  </table>
  </div> 

  
<div class="container" style="align:center; margin-top:200px;">
<h3>Evaluated candidates:</h3>
<table class="table">
    <thead>
      <tr>
        <th>First Name</th>
        <th>Technical Skills</th>
        <th>Years of Experience</th>
        <th>Interviewer</th>
      </tr>
    </thead>
    <tbody>
      <C:forEach var="candidate" items="${evaluatedcandidates}">
      		<tr class="table-success">
      			<td>${candidate.firstName}</td>
      			<td>${candidate.techSkills}</td>
      			<td>${candidate.yearsOfExp}</td>
      			<td>${candidate.user.firstName}</td>
      		</tr>
      </C:forEach>
    </tbody>
  </table>
  </div> 
 </body>
 </html>
  
