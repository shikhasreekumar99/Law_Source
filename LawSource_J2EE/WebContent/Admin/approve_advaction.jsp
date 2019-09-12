<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("approve_advaction.jsp");
    	String id=request.getParameter("id");
    	
    	String s="update tbl_lawyer set status='1' where tbl_lawyer_id="+id+"";
    	System.out.println(s);
    	stmt.executeUpdate(s);
    	
    	response.sendRedirect("approve_adv.jsp");
    }
    catch(Exception e)
    {
    	System.out.println("approve_advaction.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
