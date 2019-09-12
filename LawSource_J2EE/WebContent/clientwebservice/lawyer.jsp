<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("lawyer.jsp");
    	JSONArray ja1=new JSONArray();
    	JSONObject jo1;
    	String district=request.getParameter("district");
    	String s="select * from tbl_lawyer where district='"+district+"' or name='"+district+"'";
    	String court_name="";
    	String address="",mob="",email="",fees="";
    	rs=stmt.executeQuery(s);
    	while(rs.next())
    	{
    		jo1=new JSONObject();
    		court_name=rs.getString("name");
    		address=rs.getString("address");
    		mob=rs.getString("mobile");
    		email=rs.getString("email");
    		fees=rs.getString("fees");
    		jo1.put("id",rs.getString("tbl_lawyer_id"));
    		jo1.put("name","Adv."+court_name);
    		jo1.put("address",address);
    		jo1.put("mob","Contact No: "+mob);
    		jo1.put("email","Email: "+email);
    		jo1.put("fees","Fees: Rs."+fees+"/-");
    		ja1.add(jo1);
    	}
    	
    	
    	out.println(ja1);
    	System.out.println(ja1);
    }
    catch(Exception e)
    {
    	System.out.println("lawyer.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
