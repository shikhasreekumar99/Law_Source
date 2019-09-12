<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="css/adminstyle.css" />
<title></title>
<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
<script type="text/javascript">
	$(document).ready(function()
			{
		
		$("#but").click(function()
				{
			var username=$("#username").val();
			var password=$("#password").val();
			
			$.getJSON("index_action.jsp?username="+username+"&password="+password,function(json)
					{
					var msg=json[0].response;
					if(msg=="success")
					{
						alert(msg);
						window.location="home.jsp";
					}
					else
					{
						alert(msg);
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
        <div class="outer outer1"> 
   		 <div class="firsthead">
        	LOGIN HERE.....
        </div>
       
    	<div class="tab1">
        	USERNAME
        </div>
        <div class="tab1">
        	<input type="text" name="name" id="username"/>
        </div>
        <div class="tab1">
        	PASSWORD
        </div>
        <div class="tab1">
        	<input type="password" name="pass" id="password"/>
        </div>
        
        <div class="button">
        	<input type="submit" value="login" class="button" style="margin-left:30px;" id="but"/>
        </div>
       
  
    </div>
   
</div>
</body>
</html>
