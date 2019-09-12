<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("ipcdescription.jsp");
    	
    	String section=request.getParameter("section");
    	String s="select * from tbl_ipc where section='"+section+"'";
    	rs=stmt.executeQuery(s);
    	if(rs.next())
    	{	
    		jo.put("description",rs.getString("description"));
    		
    	}
    	else
    	{
    		jo.put("description","Sorry currently unavailable");
    	}
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("ipcdescription.jsp");
    	System.out.println(e);
    	jo.put("description","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
