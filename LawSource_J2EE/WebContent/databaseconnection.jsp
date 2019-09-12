<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    Class.forName("com.mysql.jdbc.Driver");
    Connection con =DriverManager.getConnection("Jdbc:mysql://localhost:3306/lawsource","root","1234");
    Statement stmt=con.createStatement();
    Statement stmt1=con.createStatement();
    Statement stmt2=con.createStatement();
    ResultSet rs,rs1,rs2;
    JSONArray ja=new JSONArray();
    JSONObject jo=new JSONObject();
    
    %>