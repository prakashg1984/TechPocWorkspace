<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<body>
<h2>Welcome Home</h2>
<P>The time on the server is ${serverTime}.</p>
<form action="halo" method="post">
		<P>User Name : </p><input type="text" name="userName"><br> 
		<P>Email Address : </p><input type="text" name="emailAddress">
		</P>
		<input type="submit" value="Login"> <button type="reset">Reset</button>
	</form>
</body>
</html>
