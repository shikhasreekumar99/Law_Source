<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("getipc.jsp");
    	
    	String s="select * from tbl_ipc ";
    	rs=stmt.executeQuery(s);
    	while(rs.next())
    	{	
    		jo=new JSONObject();
    		jo.put("section",rs.getString("section"));
    		jo.put("description",rs.getString("description"));
    		ja.add(jo);
    	}
    	
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("getipc.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
