<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("payment.jsp");
    	
    	String id=request.getParameter("id");
    	String caseid=request.getParameter("caseid");
    	
    	String s1="";
    	String s="update tbl_payment  set paymentstatus='2'  where tbl_case_id='"+caseid+"'";
        stmt.executeUpdate(s);
        
        s1="update tbl_case  set notification='1'  where tbl_case_id='"+caseid+"'";
        stmt1.executeUpdate(s1);
        
   
    	jo.put("response","success");
    	ja.add(jo);
    	
    	
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("payment.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
