<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("clients.jsp");
    	String id=request.getParameter("id");
    	JSONArray ja1=new JSONArray();
    	JSONObject jo1;
    	String s="select * from tbl_case where tbl_lawyer_id='"+id+"' and status='1'";
    	String casetitle="";
    	String casetype="",clint="",no="";
    	rs=stmt.executeQuery(s);
    	while(rs.next())
    	{
    		jo1=new JSONObject();
    		casetitle=rs.getString("casetitle");
    		casetype=rs.getString("casetype");
    		no=rs.getString("caseno");
    		clint=rs.getString("tbl_clint_id");
    		String s1="select * from tbl_client  where  tbl_client_id='"+clint+"'";
        	rs1=stmt1.executeQuery(s1);
        	if(rs1.next())
        	{
        		String name,phone,email;
        		name=rs1.getString("name");
        		phone=rs1.getString("mobile");
        		email=rs1.getString("email");
        		jo1.put("name",name+" "+"Case No: "+no);
        		jo1.put("mobile",phone);
        		jo1.put("email",email);
        		
        		
        	}
    		
    		jo1.put("casetitle",casetitle);
    		jo1.put("casetype",casetype);
    		ja1.add(jo1);
    	}
    	
    	
    	out.println(ja1);
    	System.out.println(ja1);
    }
    catch(Exception e)
    {
    	System.out.println("clients.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
