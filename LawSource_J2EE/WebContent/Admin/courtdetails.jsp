<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("courtdetails.jsp");
    	
    	String s="select * from tbl_court  ";
    	rs=stmt.executeQuery(s);
    	
    	while(rs.next())
    	{	
    		jo=new JSONObject();
    		jo.put("name",rs.getString("court_name"));
    		jo.put("id",rs.getString("tbl_court_id"));
    		ja.add(jo);
    	}
    	
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("courtdetails.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
