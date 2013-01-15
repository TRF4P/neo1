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
		<form method="GET" action="/create/addProject">
			Project's Name: <br><input type="text" name ="projectName" value="${projectName}"><br>
		 	Project's Start Date: <br><input type="date" name = "startDate" value="${startDate}"><br>	
			Project's End Date: <br><input type="date" name = "endDate" value="${endDate}"><br>		
			Project's IOC Date: <br><input type="date" name="iocDate" value="${iocDate}">				
			<input type="submit" value="Submit">
		</form>
 </div> <!-- #main -->
</div> <!-- #main-container -->

</body>
</html>