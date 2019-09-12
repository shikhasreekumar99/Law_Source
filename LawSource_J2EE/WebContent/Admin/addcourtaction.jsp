<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("addcourtaction.jsp");
    	
    	String courtname=request.getParameter("courtname");
    	String address=request.getParameter("address");
    	String contact=request.getParameter("contact");
    	String district=request.getParameter("district");
    	
    	String s="select * from tbl_court  where court_name='"+courtname+"' and address='"+address+"' and district='"+district+"' and contactno='"+contact+"' ";
    	rs=stmt.executeQuery(s);
    	if(rs.next())
    	{	
    		jo.put("response","Details AlreadyExist");
    	}
    	else
    	{
    		s="insert into tbl_court(court_name,address,district,contactno)values('"+courtname+"','"+address+"','"+district+"','"+contact+"')";
        	stmt1.executeUpdate(s);
    		jo.put("response","success");
    	}
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("addcourtaction.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
