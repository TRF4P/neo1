
	 <div class="main-container">
            <div class="main wrapper clearfix">

             
  <aside>
                    <h3></h3>
		<div class="error">${error}</div>
		
		<form action="/j_spring_security_check" method="post" >
      <p>
        <label for="j_username">Login:</label><br/>
        <input id="j_username" name="j_username" type="text"
            value="${not empty param.login_error ? sessionScope['SPRING_SECURITY_LAST_USERNAME'] : '' }" />
      </p>
      <p>
        <label for="j_password">Password:</label>
        <input id="j_password" name="j_password" type="password" />
      </p>
      <p>
        <input type="checkbox" name="_spring_security_remember_me"/> Remember me
      </p>
      <input  type="submit" value="Login"/>
      <a href="/auth/registerpage">Register</a>
    </form><br/>
                    </aside>

		
  
 </div> <!-- #main -->
        </div> <!-- #main-container -->