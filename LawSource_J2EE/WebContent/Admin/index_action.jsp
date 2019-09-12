<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("index_action.jsp");
    	String username=request.getParameter("username");
    	String password=request.getParameter("password");
    	String id="";
    	String s="select * from tbl_admin  where username='"+username+"' and password='"+password+"'";
    	rs=stmt.executeQuery(s);
    	if(rs.next())
    	{	
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
    	System.out.println("index_action.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
