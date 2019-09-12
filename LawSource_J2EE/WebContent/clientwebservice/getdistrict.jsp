<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("getdistrict.jsp");
    	JSONArray ja1=new JSONArray();
    	JSONObject jo1;
    	String s="select distinct district from tbl_court";
    	String district="",court="";
    	
    	rs=stmt.executeQuery(s);
    	while(rs.next())
    	{
    		jo1=new JSONObject();
    		district=rs.getString("district");
    		
    	
    		jo1.put("district",district);
    		
    		ja1.add(jo1);
    	}
    	String s1="select distinct court_name from tbl_court";
    	
    	
    	rs=stmt.executeQuery(s1);
    	while(rs.next())
    	{
    		jo1=new JSONObject();
    		
    		
    		court=rs.getString("court_name");
    		
    		jo1.put("district",court);
    		ja1.add(jo1);
    	}
    	
    	out.println(ja1);
    	System.out.println(ja1);
    }
    catch(Exception e)
    {
    	System.out.println("getdistrict.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
