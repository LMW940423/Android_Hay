<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String url_mysql = "jdbc:mysql://localhost/mypeople?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
 	String id_mysql = "root";
 	String pw_mysql = "qwer1234";
    String WhereDefault = "select * from friendslist";
    int count = 0;
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        Statement stmt_mysql = conn_mysql.createStatement();

        ResultSet rs = stmt_mysql.executeQuery(WhereDefault); // 
%>
		{ 
  			"friendslist"  : [ 
<%
        while (rs.next()) {
            if (count == 0) {

            }else{
%>
            , 
<%
            }
%>           
			{
			"fSepno" : "<%=rs.getString(1) %>", 
			"fName" : "<%=rs.getString(2) %>",   
			"fTel" : "<%=rs.getString(3) %>",  
			"fEmail" : "<%=rs.getString(4) %>"
			"fAddress" : "<%=rs.getString(5) %>"
			"fRelation" : "<%=rs.getString(6) %>"
			"fComment" : "<%=rs.getString(7) %>"
			"fImage" : "<%=rs.getString(8) %>"
			"fTag1" : "<%=rs.getString(9) %>"
			"fTag2" : "<%=rs.getString(10) %>"
			"fTag3" : "<%=rs.getString(11) %>"
			"fTag4" : "<%=rs.getString(12) %>"
			"fTag5" : "<%=rs.getString(13) %>"
			}

<%		
        count++;
        }
%>
		  ] 
		} 
<%		
        conn_mysql.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
	
%>
