<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("newuserdetails.jsp.jsp");
    	
    	String s="select * from tbl_client where status='0' ";
    	rs=stmt.executeQuery(s);
    	while(rs.next())
    	{	jo=new JSONObject();
    		jo.put("type","Client");
    		jo.put("name",rs.getString("name"));
    		jo.put("age",rs.getString("age"));
    		jo.put("email",rs.getString("email"));
    		jo.put("mobile",rs.getString("mobile"));
    		jo.put("gender",rs.getString("gender"));
    		jo.put("address",rs.getString("address"));
    		jo.put("district",rs.getString("district"));
    		ja.add(jo);
    	}
    	
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("newuserdetails.jsp.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
