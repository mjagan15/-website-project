package website;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<html><head><title>Admin Dashboard</title>");
        out.println("<style>");
        out.println("body { font-family: Arial; background: linear-gradient(120deg,#4facfe,#00f2fe); }");
        out.println("h1 { text-align:center; color:white; margin-top:20px; }");
        out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; background: white; }");
        out.println("th, td { padding: 12px; text-align: center; border-bottom: 1px solid #ddd; }");
        out.println("th { background-color: #4facfe; color: white; }");
        out.println("a.approve { background-color: #0077ff; color: white; padding: 5px 10px; text-decoration: none; border-radius: 5px; }");
        out.println("a.approve:hover { background-color: #005bb5; }");
        out.println("</style></head><body>");

        out.println("<h1>Admin Dashboard</h1>");
        out.println("<table>");
        out.println("<tr><th>Name</th><th>Phone</th><th>Email</th><th>Status</th><th>Action</th></tr>");

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");

        	Connection con = DriverManager.getConnection(
        	"jdbc:mysql://gondola.proxy.rlwy.net:34015/railway",
        	"root",
        	"TmCwYIDXNRKdfgrKYvgGyqxdAHRqmSgU"
        	);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users_records");

            while(rs.next()) {
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                String email = rs.getString("email");
                String status = rs.getString("status");

                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + contact + "</td>");
                out.println("<td>" + email + "</td>");
                out.println("<td>" + status + "</td>");

                if(status.equals("pending")) {
                    out.println("<td><a class='approve' href='approve?contact=" + contact + "'>Approve</a></td>");
                } else {
                    out.println("<td>Approved</td>");
                }

                out.println("</tr>");
            }

            con.close();
        } catch(Exception e) {
            e.printStackTrace();
            out.println("<tr><td colspan='5'>Error: " + e.getMessage() + "</td></tr>");
        }

        out.println("</table></body></html>");
    }
}
