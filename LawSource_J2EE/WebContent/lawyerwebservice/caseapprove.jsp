<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("caseapprove.jsp");
    	String cid=request.getParameter("caseid");
    	String status=request.getParameter("status");
    	String comment=request.getParameter("comment");
    	
    	
    	String s="update tbl_case  set status='"+status+"',   comments='"+comment+"' where tbl_case_id='"+cid+"'";
    	stmt.executeUpdate(s);
    	
    	if(status.equals("1"))
    	{
    		s="update tbl_payment  set paymentstatus='"+status+"'  where tbl_case_id='"+cid+"'";
        	stmt.executeUpdate(s);
    	}
    	
    	
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
