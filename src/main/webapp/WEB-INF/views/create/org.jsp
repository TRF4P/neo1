8<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a person!</title>
</head>
<body>
<script>
$(document).ready(function(){
  $("button").click(function(){
    $("#div1").load("demo_test.txt");
  });
});
</script>

        <div class="main-container">
            <div class="main wrapper clearfix">

		<p> Input fields then click "create" button</p>
		<form method="POST" action="/create/addOrg">
		Organization's Full Name: <br/><input type="text" name ="fName" value="${fName}"><br/>
		Organization's Acronym: <br/><input type="text" name="aName" value="${aName}"><br/>
		<input type="submit" value="Submit">
		</form>
		<form>
       <fieldset>
          <legend>Selecting elements</legend>
          <p>
             <label>Select list</label>
             <select id = "myList">
               <option value = "1">one</option>
               <option value = "2">two</option>
               <option value = "3">three</option>
               <option value = "4">four</option>
             </select>
          </p>
       </fieldset>
    </form>
 </div> <!-- #main -->
</div> <!-- #main-container -->

</body>
</html>