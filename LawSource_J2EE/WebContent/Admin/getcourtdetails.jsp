<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("getcourtdetails.jsp");	
    	String id=request.getParameter("courtid");
    	String s="select * from tbl_court where tbl_court_id="+id+" ";
    	rs=stmt.executeQuery(s);
    	if(rs.next())
    	{	
    		jo=new JSONObject();
    		jo.put("address",rs.getString("address"));
    		jo.put("district",rs.getString("district"));
    		jo.put("contactno",rs.getString("contactno"));
    		ja.add(jo);
    	}
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("getcourtdetails.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
