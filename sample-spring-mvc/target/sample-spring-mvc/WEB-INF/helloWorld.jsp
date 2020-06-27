<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<body>
<form action="home" method="GET">
    <h1>Spring 4.0.2 MVC web service</h1>
 	 <h1>Logged In Success</h1>
    <h3>Name : ${name} </h3>
    <h3>EmailAddress : ${emailAddress} </h3>
    </P>
    <input type="submit" value="Back" >
    </form>
</body>
</html>