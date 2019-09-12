<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("approveedvviewaction.jsp");
    	
    	String s="select * from tbl_lawyer ";
    	rs=stmt.executeQuery(s);
    	while(rs.next())
    	{	jo=new JSONObject();
    	
    		jo.put("id",rs.getString("tbl_lawyer_id"));
    		jo.put("name",rs.getString("name"));
    		jo.put("age",rs.getString("age"));
    		jo.put("email",rs.getString("email"));
    		jo.put("mobile",rs.getString("mobile"));
    		jo.put("gender",rs.getString("gender"));
    		jo.put("address",rs.getString("address"));
    		jo.put("fees",rs.getString("fees"));
    		jo.put("status",rs.getString("status"));
    		ja.add(jo);
    	}
    	
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("approveedvviewaction.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
