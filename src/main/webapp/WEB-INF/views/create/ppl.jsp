<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a person!</title>
</head>
<body>

        <div class="main-container">
            <div class="main wrapper clearfix">

		<p> Input fields then click "create" button</p>
		<form method="POST" action="/create/addPpl">	
		Age: <br/><input type="text" name ="age" value="${age}"><br>
		First name:<br/><input type="text" name="firstName" value="${firstName}"><br>
		Last name:<br/><input type="text" name="lastName" value="${lastName}"><br>
		<input type="submit" value="Submit">
		</form>
 </div> <!-- #main -->
</div> <!-- #main-container -->

</body>
</html>