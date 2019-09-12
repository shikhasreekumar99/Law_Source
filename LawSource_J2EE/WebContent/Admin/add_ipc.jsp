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
				var section=$("#section").val();
				var description=$("#description").val();
				var expansion=$("#expansion").val();
				if((section=="")||(description==""))
				{
						alert("Enter all fields");
				}
				else
				{
					$.getJSON("addipcaction.jsp?section="+section+"&description="+description+"&expansion="+expansion,function(json)
					{
							var msg=json[0].response;
							if(msg=="success")
							{
								alert("Successfully Added");
								window.location="add_ipc.jsp";
							}
							else
							{
								alert(msg);
							}
					});
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
					<h3>Add Ipc</h3>
					<div class="field_court">	
						<div class="court_details">
							Section No
						</div>
						<div class="court_details2">
							<input type="text" class="c_input" id="section">
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
					<div class="field_court">	
						<div class="court_details">
							Expansion
						</div>
						<div class="court_details2">
							<textarea class="t_input" id="expansion"></textarea>
						</div>
					</div>
					<input type="submit" value="Add" id="add" class="c_btn">
				</div>
		</div> 
</div>
</body>
</html>
