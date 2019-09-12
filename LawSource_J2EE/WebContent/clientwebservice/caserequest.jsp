<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../databaseconnection.jsp" %>
    <%
    try
    {
    	System.out.println("register.jsp");
    	
    	String userid=request.getParameter("userid");
    	String lawid=request.getParameter("lawid");
    	String title=request.getParameter("tit");
    	String type=request.getParameter("type");
    	String num=request.getParameter("num");
    	String desc=request.getParameter("desc");
    
    	
    	
        		String s1="insert into tbl_case(tbl_clint_id,tbl_lawyer_id,casetitle,casetype,description,caseno)values('"+userid+"','"+lawid+"','"+title+"','"+type+"','"+desc+"','"+num+"')";
        		stmt.executeUpdate(s1);
        		String fees="";
        		String s="select * from tbl_case  where  tbl_clint_id='"+userid+"' and tbl_lawyer_id='"+lawid+"'";
            	rs=stmt.executeQuery(s);
            	if(rs.next())
            	{
            		String caseid=rs.getString("tbl_case_id");
            		String s2="select * from tbl_lawyer  where tbl_lawyer_id='"+lawid+"'";
                	rs=stmt.executeQuery(s2);
                	if(rs.next())
                	{
                		 fees=rs.getString("fees");
                		 String s3="insert into tbl_payment(tbl_case_id,tbl_clint_id,tbl_lawyer_id,fees)values('"+caseid+"','"+userid+"','"+lawid+"','"+fees+"')";
                 		stmt.executeUpdate(s3);
                		 
                	}
            	}
        		
        		
        		jo.put("response","success");
        		ja.add(jo);
            	out.println(ja);
            	System.out.println(ja);
        	
    	
    	
    	
    }
    catch(Exception e)
    {
    	System.out.println("caserequest.jsp");
    	System.out.println(e);
    	jo.put("response","error");
    	ja.add(jo);
    	out.println(ja);
    	System.out.println(ja);
    }
    %>
