<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("statusupdate.jsp");
    	
    	String caseid=request.getParameter("caseid");
    	String newstatus=request.getParameter("newstatus");
    	String status=request.getParameter("status");
    	
    	
    	String s="update tbl_payment  set paymentstatus='"+status+"'  where tbl_case_id='"+caseid+"'";
        stmt.executeUpdate(s);
        
        s="update tbl_case  set notification='"+0+"'  where tbl_case_id='"+caseid+"'";
        stmt.executeUpdate(s);
   
    	jo.put("response","success");
    	ja.add(jo);
    	
    	
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("caseapprove.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
