<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("profile.jsp");
    	String myid=request.getParameter("id");
    	System.out.println("profile.jsp"+myid);
    	String s="select * from tbl_client where tbl_client_id='"+myid+"'";
    	rs=stmt.executeQuery(s);
    	if(rs.next())
    	{
    		String name=rs.getString("name");
    		String age=rs.getString("age");
    		String gender=rs.getString("gender");
    		String address=rs.getString("address");
    		String district=rs.getString("district");
    		String email=rs.getString("email");
    		String mobile=rs.getString("mobile");
    		String username=rs.getString("username");
    		String password=rs.getString("password");
    		
    		jo.put("k_name",name);
    		jo.put("k_age",age);
    		jo.put("k_gender",gender);
    		jo.put("k_address",address);
    		jo.put("k_district",district);
    		jo.put("k_email",email);
    		jo.put("k_mobile",mobile);
    		jo.put("k_username",username);
    		jo.put("k_password",password);
    		ja.add(jo);
    		out.println(ja);
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
    	System.out.println("profile.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
