<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("editipcaction.jsp");
    	
    	String section=request.getParameter("section");
    	String description=request.getParameter("description");
    	
    	String s="update tbl_ipc set description='"+description+"' where section='"+section+"'";
    	stmt.executeUpdate(s);
    	jo.put("response","success");
    	
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("editipcaction.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
