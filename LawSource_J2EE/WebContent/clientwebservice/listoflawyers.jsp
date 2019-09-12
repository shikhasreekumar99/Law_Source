<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("listoflawyers.jsp");
    	String type=request.getParameter("type");
    	System.out.println(type);
    	
    		String s1="select * from tbl_lawyer where type='"+type+"'";
        	rs1=stmt1.executeQuery(s1);
        	while(rs1.next())
        	{
        		jo=new JSONObject();
        		jo.put("name","Adv."+rs1.getString("name"));
        		jo.put("id",rs1.getString("tbl_lawyer_id"));
        		jo.put("age",rs1.getString("age"));
        		jo.put("gender",rs1.getString("gender"));
        		jo.put("district",rs1.getString("district"));
        		jo.put("address",rs1.getString("address"));
        		jo.put("email","Email : "+rs1.getString("email"));
        		jo.put("mob",rs1.getString("mobile"));
        		jo.put("fees","Fees :Rs."+rs1.getString("fees")+"/-");
        		ja.add(jo);
        	}
    		
    	
    	out.println(ja);
    	System.out.println(ja);
    }
    catch(Exception e)
    {
    	System.out.println("listoflawyers.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
