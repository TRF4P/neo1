<%@taglib uri="http://www.springframework.org/tags/form"
prefix="form"%>

  <div class="main-container">
            <div class="main wrapper clearfix">
<aside>
	                    <h3>Relationships</h3>
	                     <form name="relForm">
			<select name="relOptions">
			<option value="friends with" selected>friends with</option>
			<option value="/makeRel/4/friends_with/3">works for</option>			
			</select>
			<input type="button" name="Submit" value="Go" 
			onClick="top.location.href = this.form.menu1.options[this.form.menu1.selectedIndex].value;
			return false;">
			</form>
			<iframe src="http://localhost:7474/db/data/node/${nodeId}"></iframe>
	                    </aside>
               
<h2>Submitted Student Information</h2>
   <table>
   <tr>
   <td>Display Name</td>
        <td>${displayName}</td>
    </tr>
    <tr>
        <td>First Name</td>
        <td>${firstName}</td>
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
        <td id="nodeId">${nodeId}</td>
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
<div id="vis"><img src="wheel.png"></div>
			<script src="/resources/js/d3.v2.min.js"></script>
			<script src="/resources/js/wheel.js"></script>
			<script>
			  if (top != self) top.location.replace(location);
			</script>
<form action="/delete/${nodeId}" method="get">
<button type="submit">Delete Node</button><br>
</form>
	
    </div> <!-- #main -->
        </div> <!-- #main-container -->
