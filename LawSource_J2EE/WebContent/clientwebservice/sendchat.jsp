<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("sendchat.jsp");
    	String lawyerid=request.getParameter("lawyerid");
    	String userid=request.getParameter("userid");
    	String msg=request.getParameter("msg");
    	
    	
    	String s="insert into tbl_chat(fromtype,fromid,toid,msg,date,time)values('client','"+userid+"','"+lawyerid+"','"+msg+"',now(),now())";
    	System.out.println(s);
    	
    	stmt.executeUpdate(s);
    	
        		jo.put("response","pass");
        	
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("sendchat.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
