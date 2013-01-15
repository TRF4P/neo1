<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Entity List</title>
</head>
<body>

        <div class="main-container">
            <div class="main wrapper clearfix">

		<p> Input fields then click "create" button</p>
		<form method="POST" action="/create/addEvent">
		Name of Event: <br/><input type="text" name ="eventName" value="${eventName}"><br/>
		Event Start Date: <br/><input type="date" name = "startDate" value="${startDate}"><br/>
		<label for="meeting">Event Start Time: <br/></label><input name="startTime" type="time" value="${startTime}"/><br/>		
		Event End Date: <br/><input type="date" name="endDate" value="${endDate}"><br/>
		<label for="meeting">Event End Time: <br/></label><input name="endTime" type="time" value="${startTime}"/><br/>
		<input type="submit" value="Submit">
		</form>
 </div> <!-- #main -->
</div> <!-- #main-container -->

</body>
</html>