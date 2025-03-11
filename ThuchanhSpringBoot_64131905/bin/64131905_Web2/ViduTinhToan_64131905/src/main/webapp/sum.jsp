<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
//Lay tham so tu URL
//http://localhost:8080/ViDuTinhToan/sum/jsp?a5&b=6
String strA = request.getParameter("a");
int valueA = Integer.parseInt(strA);

String strB =request.getParameter("b");
int valueB = Integer.parseInt(strB);

out.print("TONG CUA");
out.print(valueA);
out.print("va ");
out.print(valueB);
out.print("la ");
out.print(valueA + valueB);
%>
</body>
</html>