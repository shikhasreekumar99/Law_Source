<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("approved.jsp");
    	
    	String emailid=request.getParameter("emailid");
    	String type=request.getParameter("type");
    	
    	String s="";
    	
    	if(type.equals("advocate"))
    	{	
    		s="update tbl_lawyer set status='1' where email='"+emailid+"'";
    		stmt.executeUpdate(s);
    		jo.put("msg","Advocate Approved Successfully");
    	}
    	if(type.equals("user"))
    	{	
    		s="update tbl_client set status='1' where email='"+emailid+"'";
    		stmt.executeUpdate(s);
    		jo.put("msg","Client Approved Successfully");
    	}
    	
    	
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("approved.jsp");
    	System.out.println(e);
    	jo.put("msg","Error Occurs");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
