<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("notification.jsp");
    	String id=request.getParameter("id");

    	String s="select * from tbl_case where tbl_clint_id='"+id+"' and notification='0'";
    	System.out.println(s);
    	
    	String status="",casetype="";
    	String casecomment="",casetitle="";
    	
    	rs=stmt.executeQuery(s);
    	
    	while(rs.next())
    	{
    		jo=new JSONObject();
    		status=rs.getString("status");
    		if(status.equals("1"))
    		{
    			jo.put("status","approved");
        		
    		}
    		else if(status.equals("2"))
    		{
    			jo.put("status","rejected");
        		
    		}
    		else
    		{
    			jo.put("status","Not examined");
        		
    		}
    		try
    		{
    			casecomment=rs.getString("comments");
    			jo.put("comment",casecomment);
    		}
    		catch(Exception e)
    		{
    			jo.put("comment","No comment found");
    		}
    		
    		casetitle=rs.getString("casetitle");
    		jo.put("casetitle",casetitle);
    		casetype=rs.getString("casetype");
    		jo.put("casetype",casetype);
    		String s1="select * from tbl_lawyer  where  tbl_lawyer_id='"+rs.getString("tbl_lawyer_id")+"'";
    		System.out.println(s1);
        	rs1=stmt1.executeQuery(s1);
        	if(rs1.next())
        	{
        		String name,phone,email;
        		name=rs1.getString("name");
        		jo.put("name",name);
        	}
        	ja.add(jo);
    	}
    	
    	
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("notification.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
