<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("view_user_action.jsp");
    	String emailid=request.getParameter("emailid");
    	
    	String s="select * from tbl_client  where email='"+emailid+"'";
    	rs=stmt.executeQuery(s);
    	if(rs.next())
    	{	
    		jo.put("name",rs.getString("name"));
    		jo.put("age",rs.getString("age"));
    		jo.put("gender",rs.getString("gender"));
    		jo.put("address",rs.getString("address"));
    		jo.put("district",rs.getString("district"));
    		jo.put("status",rs.getString("status"));
    	}
    	else
    	{
    		jo.put("name","not available");
    		jo.put("age","not available");
    		jo.put("gender","not available");
    		jo.put("address","not available");
    		jo.put("district","not available");
    		jo.put("fees","not available");
    	}
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("view_user_action.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
