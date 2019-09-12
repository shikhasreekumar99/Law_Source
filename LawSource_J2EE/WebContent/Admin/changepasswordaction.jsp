<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("changepasswordaction.jsp");
    	
    	String opass=request.getParameter("opass");
    	String npass=request.getParameter("npass");
    	
    	String s="select * from tbl_admin  where password='"+opass+"'";
    	rs=stmt.executeQuery(s);
    	if(rs.next())
    	{	
    		s="update tbl_admin set password='"+npass+"' ";
    		stmt1.executeUpdate(s);
    		jo.put("response","success");
    	}
    	else
    	{
    		jo.put("response","failed");
    	}
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("changepasswordaction.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
