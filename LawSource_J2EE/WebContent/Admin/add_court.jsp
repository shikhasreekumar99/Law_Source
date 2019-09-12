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
	$("#add").click(function()
	{
		var courtname=$("#name").val();
		var address=$("#address").val();
		var contact=$("#contact").val();
		var district=$("#district").val();
		if((courtname=="")||(address=="")||(contact=="")||(district==""))
		{
			alert("Enter all the fields");
		}
		else
		{
			$.getJSON("addcourtaction.jsp?courtname="+courtname+"&address="+address+"&contact="+contact+"&district="+district,function(json)
			{
				if(json[0].response=="success")
				{
					alert("Court Details Added Successfully!");
					window.location="add_court.jsp";
				}
				else
				{
					alert(json[0].response);
				}
			});
		}
	});
	
	
	
	
	
	
	
	$.getJSON("newuserdetails.jsp",function(json)
	{
		var i=0;
		
		$('#tablebody').append('<tr><th>New Clients</th></tr>');
		if(json.length==0)
		{
			$('#tablebody').append('<tr><td>No New Clients</td></tr>');
		}
		else
		{
			while(i<json.length)
			{
				$('#tablebody').append('<tr><td>'+json[i].type+'</td><td>'+json[i].name+'</td><td>'+json[i].email+'</td><td>'+json[i].district+'</td><td>'+json[i].mobile+'</td></tr>');
				i++;
			}
		}	
	});
		$.getJSON("newadvocatedetails.jsp",function(json)
		{
			var i=0;
			
			$('#tablebody').append('<tr><th>New Advocates</th></tr>');
			if(json.length==0)
			{
				$('#tablebody').append('<tr><td>No New Advocates</td></tr>');
			}
			else
			{
				while(i<json.length)
				{
					$('#tablebody').append('<tr><td>'+json[i].type+'</td><td>'+json[i].name+'</td><td>'+json[i].email+'</td><td>'+json[i].district+'</td><td>'+json[i].mobile+'</td></tr>');
					i++;
				}
			}	
		});
			$.getJSON("blockdetails.jsp",function(json)
				{
					var i=0;
					
					$('#tablebody').append('<tr><th>Blocked Persons</th></tr>');
					if(json.length==0)
					{
						$('#tablebody').append('<tr><td>NoBody Blocked</td></tr>');
					}
					else
					{
						while(i<json.length)
						{
							$('#tablebody').append('<tr><td>'+json[i].type+'</td><td>'+json[i].name+'</td><td>'+json[i].email+'</td><td>'+json[i].district+'</td><td>'+json[i].mobile+'</td></tr>');
							i++;
						}
					}	
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
					<h3>Add Court</h3>
					<div class="field_court">	
						<div class="court_details">
							Court Name
						</div>
						<div class="court_details2">
							<input type="text" class="c_input" id="name">
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
					<input type="submit" value="Add" id="add" class="c_btn">
				</div>
		</div> 
</div>
</body>
</html>
