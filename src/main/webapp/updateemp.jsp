<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Emp</title>
</head>
<body>
<form action="Employees" method="post">
		<center>
			<div>
				ID : <input type='text' name='id'>
			</div>
			<div>
				First Name : <input type='text' name='fname'>
			</div>
<div>
				Last Name : <input type='text' name='lname'>
			</div>
			<div>
				E-mail : <input type='text' name='email'>
			</div>
			<div>
				Hire Date : <input type='text' name='hdate'>
			</div>
			<div>
				Job ID : <input type='text' name='jobid'>
			</div>
			<div>
				Salary : <input type='text' name='salary'>
			</div>
			<div>
			 <input
				type='submit' value='Update' name='submit'>
				</div>
		</center>
</body>
</html>