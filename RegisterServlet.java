package website;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

protected void doPost(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {

String name = req.getParameter("name");
String contact = req.getParameter("contact");
String email = req.getParameter("email");

try{
	Class.forName("com.mysql.cj.jdbc.Driver");

	Connection con = DriverManager.getConnection(
			"jdbc:mysql://gondola.proxy.rlwy.net:34015/railway?useSSL=false&allowPublicKeyRetrieval=true",
			"root",
			"TmCwYIDXNRKdfgrKYvgGyqxdAHRqmSgU"
			);
	PreparedStatement ps = con.prepareStatement(
			"INSERT INTO users_records(name,contact,email,status) VALUES(?,?,?,?)");
ps.setString(1,name);
ps.setString(2,contact);
ps.setString(3,email);
ps.setString(4,"pending");

ps.executeUpdate();
ps.close();
con.close();

res.sendRedirect("checkStatus?email=" + email);
}
catch(Exception e){
e.printStackTrace();
}

}
}

