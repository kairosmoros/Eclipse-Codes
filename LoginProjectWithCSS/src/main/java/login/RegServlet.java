package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class RegServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static String LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/casinoroyaledb";
    public static String PASSWORD = "ShiAa#$156A3&@";
    public static String USERNAME = "root";
    Connection connection;

    public RegServlet() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String uname = request.getParameter("uname");
        String pword = request.getParameter("pword");

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO uinfo VALUES (?, ?, ?, ?)");
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, uname);
            ps.setString(4, pword);
            ps.executeUpdate();

            pw.println("<!DOCTYPE html>");
            pw.println("<html><head><title>Registration Success</title><style>");
            pw.println("body { font-family: 'Segoe UI', sans-serif; background: linear-gradient(to right, #1c1c1c, #2e2e2e); color: white; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
            pw.println(".success-box { background: rgba(255,255,255,0.08); padding: 40px; border-radius: 20px; text-align: center; box-shadow: 0 0 20px #00ffc8; }");
            pw.println(".success-box h2 { font-size: 2.5em; color: #00ffc8; margin-bottom: 20px; }");
            pw.println(".success-box p { font-size: 1.2em; margin-bottom: 25px; }");
            pw.println(".success-box a { text-decoration: none; background: #00ffc8; padding: 10px 20px; color: black; border-radius: 30px; font-weight: bold; box-shadow: 0 0 15px #00ffc8; transition: 0.3s ease; }");
            pw.println(".success-box a:hover { background: white; box-shadow: 0 0 25px #ffffff; }");
            pw.println("</style></head><body>");
            pw.println("<div class='success-box'>");
            pw.println("<h2>🎉 Registration Successful!</h2>");
            pw.println("<p>Welcome <strong>" + fname + " " + lname + "</strong>, your account has been created.</p>");
            pw.println("<a href='login.html'>Login Now</a>");
            pw.println("</div></body></html>");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
