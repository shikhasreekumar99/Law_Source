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
	$.getJSON("approveadvviewaction.jsp",function(json)
	{
		var i=0;
		while(i<json.length)
		{
			
			if(json[i].status=="0")
			{
					$('#tablebody').append('<tr><td>'+json[i].name+'</td><td>'+json[i].age+'</td><td>'+json[i].gender+'</td><td>'+json[i].address+'</td><td>'+json[i].fees+'</td><td> <a href="approve_advaction.jsp?id='+json[i].id+'"><input type="button" value="approve" class="approve_btn"></a> <a href="block_advaction.jsp?id='+json[i].id+'"><input type="button" class="block_btn"  value="block"></a></td> </tr>');
			}
			if(json[i].status=="1")
			{
				$('#tablebody').append('<tr><td>'+json[i].name+'</td><td>'+json[i].age+'</td><td>'+json[i].gender+'</td><td>'+json[i].address+'</td><td>'+json[i].fees+'</td> <td><a href="block_advaction.jsp?id='+json[i].id+'"><input type="button" class="block_btn" value="block"></a></td></tr>');
			}
			if(json[i].status=="2")
			{
				$('#tablebody').append('<tr><td>'+json[i].name+'</td><td>'+json[i].age+'</td><td>'+json[i].gender+'</td><td>'+json[i].address+'</td><td>'+json[i].fees+'</td> <td><a href="approve_advaction.jsp?id='+json[i].id+'"><input type="button" class="approve_btn" value="approve"></a></td></tr>');
			}
			i++;
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
      
        
        <div class="nav_tab active">
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
  
  
		 <div class="large_outer">  
		<table class="table table-bordered" >
			<thead>
				<tr>
					
					<th>Name</th>
					<th>Age</th>
					<th>Gender</th>
					<th>Address</th>
					<th>Fees</th>
					<th>      </th>
				
					
					
				
				</tr>
			</thead>
		<tbody  id="tablebody" >
		
		</tbody>
		</table>
		</div>
		</div> 
</div>
</body>
</html>
