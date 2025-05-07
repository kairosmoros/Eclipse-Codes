package test;
//this is filter
//A Filter (FirstFilter) that intercepts the same URL pattern (/test) before the request reaches the servlet.
//The filter checks for a custom header (x-api-key) and allows the request only if the value is "123genie".
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * Servlet implementation class FirstFilter
 */
@WebFilter(urlPatterns = "/test")
/* This line tells the server: "Apply this filter to requests going to /test."
So before your servlet runs, this filter will process the request.
 */
public class FirstFilter implements Filter 
/*The class implements the Filter interface, meaning it must define certain methods (init, doFilter, destroy if needed). */
{

   
    public FirstFilter() {
        
    }


	public void init(ServletConfig config) throws ServletException 
	{
		/*Called once when the filter is first created.

		You can use this to set up resources (not used here).

 */
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException
	/* CORE METHOD: doFilter()  
	 
	 * This is where filtering happens.
	 
	 * It gets triggered for every matching request (/test in this case).
	 
	 */
	
	{
		HttpServletRequest request =(HttpServletRequest)req;//here we are typecasting the request to http because the request is of type http and it contains many methods like get, put, post etc
/*    
        Casts the generic ServletRequest and ServletResponse to HTTP-specific versions.

        Now you can use methods like getHeader(), setStatus(), getWriter(), etc.
 
 */
		
		
		HttpServletResponse response=(HttpServletResponse)res;
		
		String apiKey=request.getHeader("x-api-key");
		/*Retrieves the value of the HTTP header x-api-key. */
		if("123genie".equals(apiKey))//note that the key is sent by the client here the client is not sending and tutor was having an app by which he was sending the key in the header so each time in my case it will give error since I am not able to send the key via that app or no client is writing his or her password so that I can check in the server side Thanks
		{
			chain.doFilter(req, res);
	    //If the key is "123genie", the filter calls chain.doFilter() which passes the request forward to the next filter or servlet. 
		
		}
		else {
			
			/*If the API key is missing or wrong:

Sends 401 Unauthorized

Writes a message to the client

Does NOT call chain.doFilter() → servlet is never reached */
			{
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Request is not valid : ");
			}
		}
	

}

}
