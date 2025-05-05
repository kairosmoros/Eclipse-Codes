package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static String LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/casinoroyaledb";
    public static String PASSWORD = "ShiAa#$156A3&@";
    public static String USERNAME = "root";
    Connection connection;

    public LoginServlet() {
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

        String uname = request.getParameter("uname");
        String pword = request.getParameter("pword");

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM uinfo WHERE uname=? AND pword=?");
            ps.setString(1, uname);
            ps.setString(2, pword);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pw.println("<!DOCTYPE html>");
                pw.println("<html><head><title>Welcome</title><style>");
                pw.println("body { font-family: 'Segoe UI', sans-serif; background: linear-gradient(135deg, #000000, #434343); color: white; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
                pw.println(".card { background: rgba(255, 255, 255, 0.1); padding: 40px; border-radius: 15px; text-align: center; box-shadow: 0 0 25px #00ffc8; }");
                pw.println(".card h1 { color: #00ffc8; font-size: 3em; text-shadow: 0 0 15px #00ffc8; }");
                pw.println("</style></head><body>");
                pw.println("<div class='card'>");
                pw.println("<h1>Welcome, " + uname + "!</h1>");
                pw.println("<p>You have successfully logged in 🎉</p>");
                pw.println("</div></body></html>");
            } else {
                pw.println("<!DOCTYPE html>");
                pw.println("<html><head><title>Login Failed</title><style>");
                pw.println("body { background-color: #1a1a1a; color: white; font-family: sans-serif; text-align: center; padding-top: 100px; }");
                pw.println(".error { color: red; font-size: 2em; }");
                pw.println("a { color: #00ffc8; text-decoration: none; font-weight: bold; }");
                pw.println("</style></head><body>");
                pw.println("<p class='error'>❌ User Not Valid</p>");
                pw.println("<a href='login.html'>Try Again</a>");
                pw.println("</body></html>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
