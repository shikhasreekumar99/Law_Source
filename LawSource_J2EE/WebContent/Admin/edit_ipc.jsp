<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="css/adminstyle.css" />
<title>Smart lawyer</title>
<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
<script type="text/javascript">
$(document).ready(function()
{
	$.getJSON("getipc.jsp",function(json)
	{
		var i=0;
		if(json.length==0)
		{
			$('#select').html('');
			$('#select').append('<option>unavailable</option>');
		}
		else
		{
			$('#select').html('');
			$('#select').append('<option>select</option>');
			while(i<json.length)
			{
				$('#select').append('<option>'+json[i].section+'</option>');
				i++;
			}
		}	
	});
	$('#select').change(function()
	{
		var section=$('#select').val();
		if(section=="unavailable")
		{
			$('#select').html('');
			$('#select').append('<option>select</option>');
		}
		else if(section=="select")
		{
			alert("Choose a valid section");
		}
		else
		{
			$.getJSON("ipcdescription.jsp?section="+section,function(json)
			{
				$('#description').val(json[0].description);
			});
		}
	});
	$('#edit').click(function()
	{
				var section=$('#select').val();
				var description=$('#description').val();
				
				$.getJSON("editipcaction.jsp?section="+section+"&description="+description,function(json)
				{
					
					if(json[0].response=="success")
					{
						alert("Edited successfully");
						window.location="edit_ipc.jsp"
					}
					else
					{
						alert("Server down try later...");
					}
				});
	});
});
</script>
</head>

<body>
<div class="main">
	<div class="header">
        <div class="home_head">
             <img src="images/logo.png"/>
         </div>
		
    </div>
    <div class="navbar ">
     <a href="home.jsp">
    	<div class="nav_tab ">
        	HOME
        	
        </div>
        </a>
         
        <div class="nav_tab">
        	USER
        	<div class="drop_down">
        		<ul>
        			<a href="approve_user.jsp"><li>Approve/Block</li></a>
        			<a href="view_user.jsp"><li>View</li></a>
        		</ul>
        	</div>
        </div>
      
        
        <div class="nav_tab">
        	 ADVOCATE
        	 <div class="drop_down">
        		<ul>
        			<a href="approve_adv.jsp"><li>Approve</li></a>
        			<a href="view_adv.jsp"><li>View</li></a>
        			<a href="review.jsp"><li>Reviews And Feedbacks</li></a>
        		</ul>
        	</div>
        </div>
        <div class="nav_tab active" >
        	 IPC
        	 <div class="drop_down">
        		<ul>
        			<a href="add_ipc.jsp"><li>Add</li></a>
        			<a href="view_ipc.jsp"><li>View</li></a>
        			<a href="edit_ipc.jsp"><li>Edit</li></a>
        		</ul>
        	</div>
        </div>
     
        
        <div class="nav_tab ">
        	COURT
        	<div class="drop_down">
        		<ul>
        			<a href="add_court.jsp"><li>Add</li></a>
        			<a href="view_court.jsp"><li>View</li></a>
        			
        		</ul>
        	</div>
        </div>
       
         <a href="changepassword.jsp">
        <div class="nav_tab "  >
        	CHANGE PASSWORD
        	
        </div>
        </a>
         <a href="index.jsp">
        <div class="nav_tab">
        	
        	LOGOUT
            
        </div>
        </a>
    </div>
  
  
		  <div class="sub_court">  
				<div class="container_court">
					<h3>Edit Ipc</h3>
					<div class="field_court">	
						<div class="court_details">
							Section Number
						</div>
						<div class="court_details2">
							<select class="c_input" id="select">
								<option>select</option>
							</select>
						</div>
					</div>
					
					<div class="field_court">	
						<div class="court_details">
							 Description
						</div>
						<div class="court_details2">
							<textarea class="t_input" id="description"></textarea>
						</div>
					</div>
					<input type="submit" value="Edit" id="edit" class="c_btn">
				</div>
		</div> 
</div>
</body>
</html>
