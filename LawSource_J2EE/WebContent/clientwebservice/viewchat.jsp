<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("viewchat.jsp");
    	String lawyerid=request.getParameter("lawyerid");
    	String userid=request.getParameter("userid");
    	
    	String s="select * from tbl_chat  where fromid='"+userid+"' and toid='"+lawyerid+"' and fromtype='client' or fromid='"+lawyerid+"' and toid='"+userid+"' and fromtype='lawyer'";
    	System.out.println(s);
    	
    	rs=stmt.executeQuery(s);
    	while(rs.next())
    	{
    		jo=new JSONObject();
        	jo.put("from",rs.getString("fromtype"));
        	jo.put("msg",rs.getString("msg"));
         	jo.put("time",rs.getString("date")+" at "+rs.getString("time"));
        	ja.add(jo);
    	}
    	
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("viewchat.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
