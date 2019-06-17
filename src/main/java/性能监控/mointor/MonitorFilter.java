package 性能监控.mointor;

import 性能监控.mointor.pojo.FunItem;
import 性能监控.mointor.pojo.Monitor;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
public class MonitorFilter implements Filter{
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req; 
		long beginTime = System.currentTimeMillis();
		chain.doFilter(req, res);
		
		String encodeing=request.getCharacterEncoding();
		long time = System.currentTimeMillis() - beginTime;
		String uri =request.getRequestURI();
		String parameter=request.getQueryString();
		
        if(parameter!=null){
        	parameter=URLDecoder.decode(parameter,encodeing);
		}
       
		if(uri.indexOf("?") > -1){
			uri = uri.substring(0, uri.indexOf("?"));
		}
		if(uri.indexOf(";JSESSIONID") > -1){
			uri = uri.substring(0, uri.indexOf(";JSESSIONID"));
		}
		String	param ="Method:"+request.getMethod()+", parameter:"+parameter;
		
		Monitor.add(new FunItem(uri, param, req.getRemoteHost(),beginTime,time));
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
}
