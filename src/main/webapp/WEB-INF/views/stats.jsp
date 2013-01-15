<%@taglib uri="http://www.springframework.org/tags/form"
prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>
<p> Search by ID</p>
<form method="POST" action="/idSearch">
ID: <input type="text" name ="id" value="${id}"><br>
<input type="submit" value="Submit">
</form>

<h2>Stats</h2>
   <table>
    <tr>
        <td>People Count</td>
        <td>${people}</td>
    </tr>
<tr>
        <td>Last Name</td>
        <td>${lastName}</td>
    </tr>
<tr>
        <td>age</td>
        <td>${age}</td>
<tr>
        <td>Node Type</td>
        <td>${nodeType}</td>
        </tr>
<tr>
        <td>Node Id</td>
        <td>${nodeId}</td>
        </tr>
<tr>
        <td>Last Modified</td>
        <td>${lastModified}</td>
        </tr>
<tr>
        <td>First Created</td>
        <td>${firstCreated}</td>
    </tr>
</table>  
</body>