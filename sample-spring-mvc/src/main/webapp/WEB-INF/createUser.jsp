<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<body>
<h2>Create a New User</h2>
<P>The time on the server is ${serverTime}.</p>
<form action="createUser" method="post">
		<P>User Name : </p><input type="text" name="userName"><br> 
		<P>Email Address : </p><input type="text" name="emailAddress">
		</P>
		<input type="submit" value="Create User"> <button type="submit" formaction="../home" formmethod="get">Go Back</button>
		<% if ( request.getAttribute("status") == "Success" ) { %>
			<p style="color:green">User Created successfully - Goback to Login</p>
		<% } %>
	</form>
</body>
</html>
