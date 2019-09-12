<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("register.jsp");
    	
    	String name=request.getParameter("name");
    	String age=request.getParameter("age");
    	String gender=request.getParameter("gender");
    	String address=request.getParameter("address");
    String id=request.getParameter("id");
    	String district=request.getParameter("district");
    	String mobile=request.getParameter("mobile");
    	String username=request.getParameter("username");
    	//String fees=request.getParameter("fees");
    	String password=request.getParameter("password");
    	
    	
        		
        		String s1="update tbl_lawyer  set name='"+name+"',age='"+age+"',gender='"+gender+"',address='"+address+"',district='"+district+"',mobile='"+mobile+"',password='"+password+"'where tbl_lawyer_id='"+id+"'";
        		stmt.executeUpdate(s1);
        		jo.put("response","success");
        		ja.add(jo);
            	out.println(ja);
            	System.out.println(ja);
        	
    	
    }
    catch(Exception e)
    {
    	System.out.println("register.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
