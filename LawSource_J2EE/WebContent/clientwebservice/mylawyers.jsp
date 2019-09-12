<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("mylawyers.jsp");
    	String id=request.getParameter("id");

    	String s="select distinct tbl_lawyer_id from tbl_case where tbl_clint_id='"+id+"' ";
    	System.out.println(s);
    	rs=stmt.executeQuery(s);
    	
    	while(rs.next())
    	{
    		jo=new JSONObject();
    		String lawyer=rs.getString("tbl_lawyer_id");
    		String s1="select * from tbl_lawyer  where  tbl_lawyer_id='"+lawyer+"'";
    		System.out.println(s1);
        	rs1=stmt1.executeQuery(s1);
        	if(rs1.next())
        	{
        		jo.put("name",rs1.getString("name"));
        		jo.put("lawyerid",lawyer);
        	}
    		ja.add(jo);
    	}
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("mylawyers.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
