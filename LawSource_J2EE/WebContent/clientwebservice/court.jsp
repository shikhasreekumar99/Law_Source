<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("ipc.jsp");
    	JSONArray ja1=new JSONArray();
    	JSONObject jo1;
    	String district=request.getParameter("district");
    	String s="select * from tbl_court where district='"+district+"' or court_name='"+district+"'";
    	String court_name="";
    	String address="",mob="";
    	rs=stmt.executeQuery(s);
    	while(rs.next())
    	{
    		jo1=new JSONObject();
    		court_name=rs.getString("court_name");
    		address=rs.getString("address");
    		mob=rs.getString("contactno");
    		jo1.put("name",court_name);
    		jo1.put("address",address);
    		jo1.put("mob",mob);
    		ja1.add(jo1);
    	}
    	
    	
    	out.println(ja1);
    	System.out.println(ja1);
    }
    catch(Exception e)
    {
    	System.out.println("court.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
