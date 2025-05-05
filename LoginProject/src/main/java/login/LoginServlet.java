package login;//this package i only set and same can be set to register servlet though not necessary but having same package name for both organises the code

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/userdb";
    public static String PASSWORD = "ShiAa#$156A3&@";
    public static String USERNAME = "root";
    Connection connection;
    public LoginServlet() {
        super();
       
    }

	public void init(ServletConfig config) throws ServletException {
		  try {
			 connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String uname= request.getParameter("uname");
		 String pword= request.getParameter("pword"); 
		
		 /* 🔁 What happens when you use request.getParameter("uname") or request.getParameter("pword")?
It retrieves the data temporarily from the request (what the user typed and submitted).

This data exists only while the request is being processed.

Once the response is sent, that data is gone — it's not saved anywhere permanently.

💾 Who handles permanent storage?
🔐 The database (like MySQL, PostgreSQL, etc.) is responsible for permanently storing the username, password, and other user details.

👇 Example:
If you want to save the data in the database, you'd do something like:

Connection con = DriverManager.getConnection(...);
PreparedStatement ps = con.prepareStatement("INSERT INTO users (name, password) VALUES (?, ?)");
ps.setString(1, name);      // name from request
ps.setString(2, pword);     // password from request
ps.executeUpdate();
🔹 Now the data is saved in the database and can be retrieved anytime later.

*/ 
		 
		 
		 try {
			 PreparedStatement ps =connection.prepareStatement("Select * from uinfo where uname=? AND pword=?");
			 ps.setString(1, uname);
			 ps.setString(2, pword);
			 
			 ResultSet rs=ps.executeQuery();
			 PrintWriter pw =response.getWriter();
			 /*
			   The browser sends a request to your servlet.
			   The servlet processes the data and may  talks to the database.
			   The PrintWriter lets you send a reply (like a "Welcome" message) back to the browser.
			   
			   The user types something in the browser and hits submit.

           	   That message goes to the server.

			   Your servlet catches it and wants to send a message back (like “Welcome Mr. Prashant”).

			   But you can’t shout — you need a pen and paper to write back.

			   🖊️ That pen is: PrintWriter pw = response.getWriter();
			   Now with pw.println(...) → you write HTML or text that the browser will show.
			   
			   */
			pw.println("<html><body bgcolor=black text=white><center>");
			 if(rs.next())
			 {
				 pw.println("Welcome : " +uname);
			 }
			 else
			 {
				 pw.println("User not found in the database");
			 }
			 pw.println("</center></body><html>");
			 /*✅ HTML Tag Closing Rule: “Last Opened, First Closed”
This rule is like stacking plates:

The last plate you put on the top is the first one you take off.

Similarly, in HTML, the last tag you open must be the first tag you close.

🧱 Example:
<html>
  <body>
    <center>
      Hello World
    </center>
  </body>
</html>
Tag Opening Order:
<html> — opened first

<body> — opened second

<center> — opened third

So, Closing Order:
</center> — closed first

</body> — closed second

</html> — closed last

✅ This is the correct way.

❌ If you close in the wrong order:
<html>
  <body>
    <center>
  </body>
</center>
</html>
This will confuse the browser — and it may display things incorrectly.

🧠 Rule in short:
Always close the tags in the reverse order you opened them.

 */
		} 
		 catch (Exception e) {
			e.printStackTrace();
		}
		 
	}

}
