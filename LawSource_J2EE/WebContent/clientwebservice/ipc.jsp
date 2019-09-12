<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("ipc.jsp");
    	JSONArray ja1=new JSONArray();
    	JSONObject jo1;
    	String s="select * from tbl_ipc";
    	String section="";
    	String desc="";
    	rs=stmt.executeQuery(s);
    	while(rs.next())
    	{
    		jo1=new JSONObject();
    		section=rs.getString("section");
    		desc=rs.getString("description");
    		jo1.put("section",section);
    		jo1.put("description",desc);
    		jo1.put("expanssion",rs.getString("expansion"));
    		
    		ja1.add(jo1);
    	}
    	
    	
    	out.println(ja1);
    	System.out.println(ja1);
    }
    catch(Exception e)
    {
    	System.out.println("ipc.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
