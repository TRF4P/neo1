
            <div class="main wrapper clearfix">
            <ul>
				<li><a href="ppl">Create a Person</a></li>
             	<li><a href="org">Create An Organization</a></li>
             	<li><a href="event">Create An Event</a></li>
             	<li><a href="project">Create a Project</a></li>
             	</ul><br>
            <select name="reldd" id ="reld">
			<option value="friends_with">friends with</option>
			<option value="works_for">works for</option>
			<option value="attended">attended</option>	
			<option value="works_under">works under</option>	
			<option value="is_working_on">works on</option>				
			</select>
             	<form name="menu2" id="menuform">
          <br><input type="text" name="sourceId" id="sourceAjax" autocomplete="on">
			<input type="text" name="relType" id="relAjax" autocomplete="off">
           <input type="text" name="targetId" id="targetAjax" autocomplete="off"> 
           </form>
           <form name="menu3" id="menuform1" method="POST" action="/makeRelPost">
           <input type="hidden" name="sourceId" id="sourceId">
           <input type="hidden" name="relType" id="relType"> 
           <input type="hidden" name="targetId" id="targetId">   
             <input type="submit" name="Search">  
			 </form>
			 <br>
           <div id="selction-ajax"></div>
			 <br>
		
			 <script>
			 $(document).ready(function() { 
				$('#sourceAjax').autocomplete({
				    serviceUrl: '/jsonRequest',
				    onSelect: function (suggestion) {
				    	$("#sourceId").val(suggestion.data);
				    }
				})
				$('#targetAjax').autocomplete({
				    serviceUrl: '/jsonRequest',
				    onSelect: function (suggestion) {
				    	$("#targetId").val(suggestion.data);
				    }
				})
				$('#relAjax').autocomplete({
					lookup: ['works for','is working on','relates to','relates to project', 'friends with', 'owns', 'attended', 'works under', 'is working for',"sub-organization of"],
				    onSelect: function (suggestion) {
				    	$("#relType").val(suggestion.value);
				    }
				})		
			})
			 ;
			</script>
			
            </div> <!-- #main -->

  