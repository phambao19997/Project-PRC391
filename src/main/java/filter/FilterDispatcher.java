package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class FilterDispatcher
 */
@WebFilter("/*")
public class FilterDispatcher implements Filter {
	private final String welcomePage ="welcome-page.jsp";
	private FilterConfig filterConfig = null;

    /**
     * Default constructor. 
     */
    public FilterDispatcher() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		String url = welcomePage;
		
		try {
			int lastIndex = uri.lastIndexOf("/");
			String resourse = uri.substring(lastIndex+1);
			if (resourse.length() > 0) {
				url = resourse.substring(0,1).toUpperCase()
						+resourse.substring(1)+"Servlet";
				if (resourse.lastIndexOf("html") > 0 || resourse.lastIndexOf("jsp") > 0){
					url = resourse;
				}
			}
			if (url != null) {
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(request, response);
			} else 
				chain.doFilter(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}
