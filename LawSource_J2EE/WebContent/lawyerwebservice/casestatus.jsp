<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("casestatus.jsp");
    	String id=request.getParameter("id");

    	String s="select * from tbl_case where tbl_lawyer_id='"+id+"' and  status='1'";
    	System.out.println(s);
    	String casestatus="",paymentstatus="";
    	String casetitle="";
    	String casetype="",clint="",desc="",no="",cid="";
    	rs=stmt.executeQuery(s);
    	
    	while(rs.next())
    	{
    		jo=new JSONObject();
    		casestatus=rs.getString("casestatus");
    		if(casestatus.equals("0"))
    		{
    			jo.put("status","no updations available");
        		
    		}
    		else
    		{
    			jo.put("status",casestatus);
        		
    		}
    		casetitle=rs.getString("casetitle");
    		casetype=rs.getString("casetype");
    		
    		clint=rs.getString("tbl_clint_id");
    		desc=rs.getString("description");
    		no=rs.getString("caseno");
    		cid=rs.getString("tbl_case_id");
    		
    		String s1="select * from tbl_client  where  tbl_client_id='"+clint+"'";
    		System.out.println(s1);
        	rs1=stmt1.executeQuery(s1);
        	if(rs1.next())
        	{
        		String name,phone,email;
        		name=rs1.getString("name");
        		phone=rs1.getString("mobile");
        		email=rs1.getString("email");
        		jo.put("name",name);
        		jo.put("mobile",phone);
        		jo.put("email",email);
        		
        		
        	}
        	jo.put("caseid",cid);
    		jo.put("casetitle",casetitle);
    		jo.put("casetype",casetype);
    		jo.put("desc","Case No: "+no+"  "+desc);
    		
    		
    		String s2="select * from tbl_payment where tbl_case_id='"+rs.getString("tbl_case_id")+"'";
    		System.out.println(s2);
    		rs2=stmt2.executeQuery(s2);
        	if(rs2.next())
        	{
        		paymentstatus=rs2.getString("paymentstatus");
        		if(paymentstatus.equals("0"))
        		{
        			jo.put("pay","not paid");
            		
        		}
        		else if(paymentstatus.equals("2"))
        		{
        			jo.put("pay","paid");
            		
        		}
        		else
        		{
        			jo.put("pay","Payment Requested");
            		
        		}
        	}
        	ja.add(jo);
    	}
    	
    	
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("newclients.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
