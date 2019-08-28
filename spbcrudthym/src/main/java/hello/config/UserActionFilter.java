package hello.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@WebFilter(initParams = { @WebInitParam(name = "exclude", value = "/css") })
@Component
public class UserActionFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(UserActionFilter.class);
	private static final String exclude1 = "/css";
	private static final String exclude2 = "/js";
	private static final String exclude3 = "/assets";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (!(	req.getServletPath().contains(exclude1) || 
				req.getServletPath().contains(exclude2) ||
				req.getServletPath().contains(exclude3)		)) {

			logger.info("Remote address: " + req.getRemoteAddr() + 
					", Remote user: " + req.getRemoteUser() + 
					", Servlet path: " + req.getServletPath() + 
					", Method: " + req.getMethod() + 
					", Param problem: " + req.getParameter("problemDescription"));
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
