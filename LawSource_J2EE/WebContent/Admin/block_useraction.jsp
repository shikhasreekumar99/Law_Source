<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("block_useraction.jsp");
		String id=request.getParameter("id");
    	
    	String s="update tbl_client set status='2' where tbl_client_id="+id+"";
    	System.out.println(s);
    	stmt.executeUpdate(s);
    	
    	response.sendRedirect("approve_user.jsp");
    }
    catch(Exception e)
    {
    	System.out.println("block_useraction.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
