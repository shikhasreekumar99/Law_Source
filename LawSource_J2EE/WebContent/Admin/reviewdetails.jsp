<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("reviewdetails.jsp");
    	
    	String s="select * from tbl_userfeedback order by tbl_userfeedback_id desc";
    	rs=stmt.executeQuery(s);
    	while(rs.next())
    	{	jo=new JSONObject();
    	//feedback,rating,l_email,name,c_email,c_name
    		jo.put("feedback",rs.getString("feedback"));
    		jo.put("rating",rs.getString("rating"));
    		
    	String s1="select * from tbl_client where tbl_client_id="+rs.getString("tbl_client_id")+"";
    	rs1=stmt1.executeQuery(s1);
    	if(rs1.next())
    	{
    		jo.put("c_email",rs1.getString("email"));
    		jo.put("c_name",rs1.getString("name"));
    	}
    	
    	
    	String s2="select * from tbl_lawyer where tbl_lawyer_id="+rs.getString("tbl_lawyer_id")+"";
    	rs1=stmt1.executeQuery(s2);
    	if(rs1.next())
    	{
    		jo.put("l_email",rs1.getString("email"));
    		jo.put("name",rs1.getString("name"));
    	}
    		
    		ja.add(jo);
    	}
    	
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("reviewdetails.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
