<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">

        <link rel="stylesheet" href="/resources/css/normalize.min.css">
        <link rel="stylesheet" href="/resources/css/main.css">
        <script src="/resources/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
         <!--script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></scrip->
         <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script-->
         <script src="/resources/js/jquery.min.js"></script>  
         <script src="/resources/js/jquery.autocomplete.min.js"></script>
          
         
    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

        <div class="header-container">
            <header class="wrapper clearfix">
                <h1 class="title"></h1>
                <nav>
                    <ul>
                    	<li><a href="/create/">Create Stuff!</a></li> 
                    	<li><a href="/list/">List Objects</a></li> 
                        <li><a href="/query/">Search</a></li>                  
                    </ul>
                </nav>
            </header>
        </div>

        <div class="main-container">
            <div class="main wrapper clearfix">

             
            </div> <!-- #main -->
        </div> <!-- #main-container -->
<decorator:body/>
        <div class="footer-container">
            <footer class="wrapper clearfix">
             <h3>Visualize!</h3>
            <form name="menuform">
			<select name="menu1">
			<option value="/json/" selected>Timeline!</option>
			<option value="http://localhost:7474/webadmin/#/data/search/">Graph Viewer</option>			
			</select>
			<input type="button" name="Submit" value="Go" 
			onClick="top.location.href = this.form.menu1.options[this.form.menu1.selectedIndex].value;
			return false;">
			</form>
			<nav>
                    <ul>
                    <li><a href="/auth/logout">Logout</a></li>
                    <li><a href="/auth/login">Login</a></li>   
                        <li><a href="/main">Home</a></li>                  
                    </ul>
                </nav>
            </footer>
        </div>
	
        <script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.8.3.min.js"><\/script>')</script>

        <script src="/resources/js/main.js"></script>

        <script>
            var _gaq=[['_setAccount','UA-XXXXX-X'],['_trackPageview']];
            (function(d,t){var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
            g.src=('https:'==location.protocol?'//ssl':'//www')+'.google-analytics.com/ga.js';
            s.parentNode.insertBefore(g,s)}(document,'script'));
        </script>
    </body>
</html>
