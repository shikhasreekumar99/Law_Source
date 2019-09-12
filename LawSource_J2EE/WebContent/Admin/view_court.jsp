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
	$.getJSON("courtdetails.jsp",function(json)
	{
		var i=0;
		$('#select').html('');
		$('#select').append('<option>select</option>');
		while(i<json.length)
			{
				$('#select').append('<option value='+json[i].id+'>'+json[i].name+'</option>');
				i++;
			}
		
	});
	
	$('#select').change(function()
	{
		var courtid=$('#select').val();
		$.getJSON("getcourtdetails.jsp?courtid="+courtid,function(json)
		{
			$('#address').val(json[0].address);
			$('#contact').val(json[0].contactno);
			$('#district').val(json[0].district);
			
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
        <div class="nav_tab" >
        	 IPC
        	 <div class="drop_down">
        		<ul>
        			<a href="add_ipc.jsp"><li>Add</li></a>
        			<a href="view_ipc.jsp"><li>View</li></a>
        			<a href="edit_ipc.jsp"><li>Edit</li></a>
        		</ul>
        	</div>
        </div>
     
        
        <div class="nav_tab active">
        	COURT
        	<div class="drop_down">
        		<ul>
        			<a href="add_court.jsp"><li>Add</li></a>
        			<a href="view_court.jsp"><li>View</li></a>
        			
        		</ul>
        	</div>
        </div>
       
         <a href="changepassword.jsp">
        <div class="nav_tab"  >
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
					<h3>View Court</h3>
					<div class="field_court">	
						<div class="court_details">
							Court Name
						</div>
						<div class="court_details2">
							<select class="c_input" id="select">
								
							</select>
						</div>
					</div>
					<div class="field_court">	
						<div class="court_details">
							Address
						</div>
						<div class="court_details2">
							<textarea class="t_input" id="address"></textarea>
						</div>
					</div>
					<div class="field_court">	
						<div class="court_details">
							Contact Number
						</div>
						<div class="court_details2">
							<input type="text" class="c_input" id="contact">
						</div>
					</div>
					<div class="field_court">	
						<div class="court_details">
							District
						</div>
						<div class="court_details2">
							<input type="text" class="c_input" id="district">
						</div>
					</div>
					<input type="submit" value="Ok" class="c_btn">
				</div>
		</div> 
</div>
</body>
</html>
