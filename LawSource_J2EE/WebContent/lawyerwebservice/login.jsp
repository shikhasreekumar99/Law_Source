<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("login.jsp");
    	String username=request.getParameter("username");
    	String password=request.getParameter("password");
    	String id="";
    	String s="select * from tbl_lawyer  where username='"+username+"' and password='"+password+"'";
    	rs=stmt.executeQuery(s);
    	if(rs.next())
    	{
    		if(rs.getString("status").equals("0"))
    		{
    			id=rs.getString("tbl_lawyer_id");
        		jo.put("response","success");
        		jo.put("userid",id);
    		}
    		if(rs.getString("status").equals("1"))
    		{
    			id=rs.getString("tbl_lawyer_id");
        		jo.put("response","success");
        		jo.put("name",rs.getString("name"));
        		jo.put("userid",id);
    		}
    		if(rs.getString("status").equals("2"))
    		{
    			id=rs.getString("tbl_lawyer_id");
        		jo.put("response","success");
        		jo.put("userid",id);
    		}
    		
    		
    	}
    	else
    	{
    		jo.put("response","failed");
    	}
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("login.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
