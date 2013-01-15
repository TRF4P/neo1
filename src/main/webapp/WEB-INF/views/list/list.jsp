<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
 <div class="main-container">
            <div class="main wrapper clearfix">
       
            <ul>
				<li><a href="/list/ppl/0">List People</a></li>
             	<li><a href="/list/org/0">List Organizations</a></li>
             	<li><a href="/list/event/0">List Events</a></li>
             	<li><a href="/list/project/0">List Projects</a></li>
             	</ul>
             
             
			<div id="vis"><img src="wheel.png"></div>
			<script src="/resources/js/d3.v2.min.js"></script>
			<script src="/resources/js/wheel.js"></script>
			<script>
			  if (top != self) top.location.replace(location);
			</script>
            </div> <!-- #main -->
       </div>
  