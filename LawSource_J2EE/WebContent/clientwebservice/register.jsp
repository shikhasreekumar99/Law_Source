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
    	String email=request.getParameter("email");
    	String district=request.getParameter("district");
    	String mobile=request.getParameter("mobile");
    	String username=request.getParameter("username");
    	String password=request.getParameter("password");
    	
    	String s="select * from tbl_client  where  username='"+username+"'";
    	rs=stmt.executeQuery(s);
    	if(rs.next())
    	{
    		jo.put("response","username");
    		ja.add(jo);
        	out.println(ja);
        	System.out.println(ja);
    	}
    	else
    	{
    		String s2="select * from tbl_client where email='"+email+"'";
        	rs=stmt.executeQuery(s2);
        	if(rs.next())
        	{
        		jo.put("response","email");
        		ja.add(jo);
            	out.println(ja);
            	System.out.println(ja);
        	}
        	else
        	{
        		
        		
        		String s1="insert into tbl_client(name,age,gender,address,district,email,mobile,username,password,status)values('"+name+"','"+age+"','"+gender+"','"+address+"','"+district+"','"+email+"','"+mobile+"','"+username+"','"+password+"','0')";
        		stmt.executeUpdate(s1);
        		jo.put("response","success");
        		ja.add(jo);
            	out.println(ja);
            	System.out.println(ja);
        	}
    	}
    	
    	
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
