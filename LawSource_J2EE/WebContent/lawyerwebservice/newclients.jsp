<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("newclients.jsp");
    	String id=request.getParameter("id");
    	JSONArray ja1=new JSONArray();
    	JSONObject jo1;
    	String s="select * from tbl_case where tbl_lawyer_id='"+id+"' and  status='0'";
    	System.out.println(s);
    	String casetitle="";
    	String casetype="",clint="",desc="",no="",cid="";
    	
    	rs=stmt.executeQuery(s);
    	while(rs.next())
    	{
    		jo1=new JSONObject();
    		casetitle=rs.getString("casetitle");
    		casetype=rs.getString("casetype");
    		
    		clint=rs.getString("tbl_clint_id");
    		desc=rs.getString("description");
    		no=rs.getString("caseno");
    		cid=rs.getString("tbl_case_id");
    		String s1="select * from tbl_client  where  tbl_client_id='"+clint+"'";
        	rs1=stmt1.executeQuery(s1);
        	if(rs1.next())
        	{
        		String name,phone,email;
        		name=rs1.getString("name");
        		phone=rs1.getString("mobile");
        		email=rs1.getString("email");
        		jo1.put("name",name);
        		jo1.put("mobile",phone);
        		jo1.put("email",email);
        		
        		
        	}
        	jo1.put("caseid",cid);
    		jo1.put("casetitle",casetitle);
    		jo1.put("casetype",casetype);
    		jo1.put("desc","Case No: "+no+"  "+desc);
    		ja1.add(jo1);
    	}
    	
    	
    	out.println(ja1);
    	System.out.println(ja1);
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
