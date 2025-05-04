package first;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class First
 */
@WebServlet("/test")
public class First extends GenericServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public First() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();	
		//pw.println("Hello MR. Prashant ");
		pw.println("<html><body>");
		pw.println("<h1>Hello James Bond</h1> ");
		pw.println("</body></html>");
	}

}
