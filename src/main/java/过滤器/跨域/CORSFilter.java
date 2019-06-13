package 过滤器.跨域;



import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet Filter implementation class CORSFilter
 */
public class CORSFilter implements Filter {

	
	private static String allowOrigin = "";
	private static String allowMethods = "POST,GET,PUT,OPTIONS,DELETE";
	private static String maxAge = "3600";
	private static String allowHeaders = "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers";
	private static String allowCredentials = "true";
    

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse _response = (HttpServletResponse) response;
		_response.setHeader("Access-Control-Allow-Origin", allowOrigin);  
		_response.setHeader("Access-Control-Allow-Methods", allowMethods);  
		_response.setHeader("Access-Control-Max-Age", maxAge);  
		_response.setHeader("Access-Control-Allow-Headers", allowHeaders);  
		_response.setHeader("Access-Control-Allow-Credentials", allowCredentials);  
        chain.doFilter(request, _response);  
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		String _allowOrigin = fConfig.getInitParameter("allowOrigin");
		if(StringUtils.isNotEmpty(_allowOrigin)){
			allowOrigin = _allowOrigin;
		}
		String _allowMethods = fConfig.getInitParameter("allowMethods");
		if(StringUtils.isNotEmpty(_allowMethods)){
			allowMethods = _allowMethods;
		}
		String _maxAge = fConfig.getInitParameter("maxAge");
		if(StringUtils.isNotEmpty(_maxAge)){
			maxAge = _maxAge;
		}
		String _allowHeaders = fConfig.getInitParameter("allowHeaders");
		if(StringUtils.isNotEmpty(_allowHeaders)){
			allowHeaders = _allowHeaders;
		}
		String _allowCredentials = fConfig.getInitParameter("allowCredentials");
		if(StringUtils.isNotEmpty(_allowCredentials)){
			allowCredentials = _allowCredentials;
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
