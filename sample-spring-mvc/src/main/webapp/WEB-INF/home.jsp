<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<html>
<body>
<h2>Welcome To Login</h2>
<P>The time on the server is ${serverTime}.</p>
<form action="login" method="post">
		<P>User Name : </p><input type="text" name="userName"><br> 
		<P>Password : </p><input type="password" name="password"><br> 
		</P>
		<input type="submit" value="Login"> <button type="reset">Reset</button>
		</P>
		<button type="submit" formaction="login/loadCreateUser">Create User</button>
		<% if ( request.getAttribute("status") == "failed" ) { %>
			<p style="color:red">Login Failed - Try again!!</p>
		<% } %>
	</form>
</body>
</html>
