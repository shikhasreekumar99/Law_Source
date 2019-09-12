<%@include file="../databaseconnection.jsp" %>
<%

try
{
System.out.println("feedback.jsp");
String s1="",s2="";

s1=request.getParameter("userid");
s2=request.getParameter("feedback");
String rating=request.getParameter("rating");
String lawyerid=request.getParameter("lawyerid");

stmt.executeUpdate("insert into tbl_userfeedback(tbl_client_id,feedback,tbl_lawyer_id,rating)values('"+s1+"','"+s2+"','"+lawyerid+"','"+rating+"')");
	jo=new JSONObject();
	jo.put("response","success");
	ja.add(jo);
System.out.println(ja);
out.println(ja);
}
catch(Exception e)
{
	System.out.println("feedback.jsp");
	System.out.println(e);
	jo.put("response","error");
	ja.add(jo);
	out.println(ja);
	System.out.println(ja);
}

%>