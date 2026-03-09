package website;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/approve")
public class ApproveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String contact = req.getParameter("contact");

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");

        	Connection con = DriverManager.getConnection(
        	"jdbc:mysql://gondola.proxy.rlwy.net:34015/railway",
        	"root",
        	"TmCwYIDXNRKdfgrKYvgGyqxdAHRqmSgU"
        	);

            PreparedStatement ps = con.prepareStatement(
                "UPDATE users_records SET status='approved' WHERE contact=?"
            );
            ps.setString(1, contact);
            ps.executeUpdate();
            con.close();

            res.sendRedirect("admin"); // reload admin page
        } catch(Exception e) {
            e.printStackTrace();
            res.getWriter().println("Error: " + e.getMessage());
        }
    }
}
