package vn.edu.quan;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AboutMe")
public class AboutMe extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Xử lý GET request và trả về thông tin cá nhân.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Thiết lập kiểu nội dung trả về là HTML
        response.setContentType("text/html; charset=UTF-8");

        // Tạo đối tượng PrintWriter để ghi nội dung HTML
        PrintWriter out = response.getWriter();

        // Tạo nội dung HTML đơn giản
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Thông tin cá nhân</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Thông tin cá nhân</h1>");
        out.println("<p><strong>Họ và tên:</strong> Đỗ Cao Minh Quân</p>");
        out.println("<p><strong>Tuổi:</strong> 21</p>");
        out.println("<p><strong>Nghề nghiệp:</strong> Sinh viên</p>");
        out.println("<p><strong>Sở thích:</strong> Thể thao, ăn uống</p>");
        out.println("<p><strong>Mô tả bản thân:</strong> Là người hòa đồng.</p>");
        out.println("</body>");
        out.println("</html>");
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Gọi lại doGet để xử lý
        doGet(request, response);
    }
}