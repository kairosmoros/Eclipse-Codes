package test;
//This is servlet 
//A Servlet (FilterDemo) that handles requests at /test
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Servlet implementation class FilterDemo
 */


@WebServlet("/test")/* here when we write @WebServlet("/test") then the container will check for that servlet where it is written and will sent request to that particular servlet so that's why no need of web.xml now onwards since mapping is not required  */
/* 
 * Maps this servlet to the URL /test.

    Requests here go through filters first (as defined
*/
public class FilterDemo extends HttpServlet 
//Defines a servlet class extending HttpServlet.
{
	private static final long serialVersionUID = 1L;
       
    public FilterDemo() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Handles HTTP GET requests.

If the request passes the filter, this message is sent in the response:
e.g., Served at: /YourProjectContext*/
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
