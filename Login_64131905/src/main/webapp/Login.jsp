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

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (username != null && password != null) {
            if ("ABC".equals(username) && "MNK".equals(password)) {
                // Nếu đúng, chuyển hướng đến trang UserProfile.html
                response.sendRedirect("UserProfile.html");
            } else {
            
                out.println("<p style='color: red;'>Tên đăng nhập hoặc mật khẩu không đúng. Vui lòng thử lại!</p>");
                out.println("<a href='Login.html'>Quay lại trang đăng nhập</a>");
            }
        } else {
          
            response.sendRedirect("Login.html");
        }
    %>
</body>
</html>