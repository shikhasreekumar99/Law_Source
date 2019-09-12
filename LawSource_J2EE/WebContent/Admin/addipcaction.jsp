<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("addipc_action.jsp");
    	
    	String section=request.getParameter("section");
    	String description=request.getParameter("description");
    	String expansion=request.getParameter("expansion");
    	
    	String s="select * from tbl_ipc  where section='"+section+"'";
    	rs=stmt.executeQuery(s);
    	if(rs.next())
    	{	
    		jo.put("response","Section no: AlreadyExist");
    	}
    	else
    	{
    		s="insert into tbl_ipc(section,description,expansion)values('"+section+"','"+description+"','"+expansion+"'')";
        	stmt1.executeUpdate(s);
    		jo.put("response","success");
    	}
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("addipc_action.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
