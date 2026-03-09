package website;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/checkStatus")
public class CheckStatusServlet extends HttpServlet {

 protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

  String email = req.getParameter("email");

  try{

	  Class.forName("com.mysql.cj.jdbc.Driver");

	  Connection con = DriverManager.getConnection(
	  "jdbc:mysql://gondola.proxy.rlwy.net:34015/railway",
	  "root",
	  "TmCwYIDXNRKdfgrKYvgGyqxdAHRqmSgU"
	  );

   PreparedStatement ps = con.prepareStatement(
   "SELECT status FROM users_records WHERE email=?"
   );

   ps.setString(1,email);

   ResultSet rs = ps.executeQuery();

   if(rs.next()){

    String status = rs.getString("status");

    if(status.equals("approved")){
        res.sendRedirect("home.htm");
    }else{

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<h2>Waiting for Admin Approval...</h2>");
        out.println("<p>Checking automatically...</p>");

        out.println("<script>");
        out.println("setTimeout(function(){");
        out.println("location.reload();");
        out.println("},3000);");
        out.println("</script>");

    }

   }

  }catch(Exception e){
   e.printStackTrace();
  }

 }
}
