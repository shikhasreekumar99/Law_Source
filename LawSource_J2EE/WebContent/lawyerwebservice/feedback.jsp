<%@include file="../databaseconnection.jsp" %>

<%@page import="org.json.simple.JSONArray" %>
<%@page import="org.json.simple.JSONObject" %>

<%

try
{
System.out.println("feedback.jsp");
String s1="",s2="",s3="",s4="",s5="",s6="",s7="",s8="",s9="",s10="",s11="",s12="",s13="",s14="",s15="";

JSONArray ja1=new JSONArray();
JSONObject jo1;
s1=request.getParameter("userid");
s2=request.getParameter("feedback");


stmt.executeUpdate("insert into tbl_lawyerfeedback(tbl_lawyer_id,feedback)values('"+s1+"','"+s2+"')");
	jo=new JSONObject();
	jo.put("response","success");
	ja.add(jo);
System.out.println(ja);
out.println(ja);
}
catch(Exception e)
{
	System.out.println("clients.jsp");
	System.out.println(e);
	jo.put("response","error");
	ja.add(jo);
	out.println(ja);
	System.out.println(ja);
}

%>