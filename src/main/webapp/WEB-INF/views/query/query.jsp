<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
 <div class="main-container">
            <div class="main wrapper clearfix">
       
           <p>Test</p>
           <form name="menu2" id="menuform" method="GET" action="/query/ppl/0">
           Search people AJAX: <br>
          <br><input type="text" name="q" id="autocomplete-query" autocomplete="off">
           <input type="submit" name="Search">
			 </form><br>
           <div id="selction-ajax"></div>
			 <br>
			 <script>
			 $(document).ready(function() { 
				$('#autocomplete-query').autocomplete({
				    serviceUrl: '/jsonRequest',
				    onSelect: function (suggestion) {
				    	$('#autocomplete-suggestions').html('You selected: ' + suggestion.value + ', ' + suggestion.data);
				    }
				})
			})
			 ;
			</script>
					
			
			<form name="menu2" id="menuform" method="GET" action="/query/ppl/0">
			 Search People: <br/><input type="text" name="q" value="${q}"><br/>
			 <input type="submit" name="Search">
			 </form><br>
			 <form name="menu2" id="menuform" method="GET" action="/query/org/0">
			 Search Organizations: <br/><input type="text" name="q" value="${q}"><br/>
			 <input type="submit" name="Search">
			 </form><br>
			 <form name="menu2" id="menuform" method="GET" action="/query/event/0">
			 Search Events: <br/><input type="text" name="q"  value="${q}"><br/>
			 <input type="submit" name="Search">
			 </form><br>
			  <form name="menu2" id="menuform" method="GET" action="/query/project/0">
			 Search projects:  <br/><input type="text" name="q"  value="${q}"><br/>
			 <input type="submit" name="Search">
			 </form><br>
				
			<!--form name="menu2" id="menuform" method="GET" action="/query/">
			 Search: <br/><input type="text"name="q"  value="${q}"><br/>
			<select name="menu1" id="menu11">
			<option value="/query/ppl/0" selected>Search for People</option>
			<option value="/query/org/0">Search Organizations</option>		
			<option value="/query/event/0">Lookup Events</option>	
			<option value="/query/project/0">Search Projects</option>	
			</select>
			<input type="submit" name="Submit"
			onClick="top.location.href = this.form.menu1.options[this.form.menu1.selectedIndex].value;
			return false;">
			</form-->
			
		
            </div> <!-- #main -->
       </div>
 