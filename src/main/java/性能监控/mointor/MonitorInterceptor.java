package 性能监控.mointor;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor; 
import org.springframework.web.servlet.ModelAndView;
import 工具类.common.IPUtils;
import 性能监控.mointor.pojo.FunItem;
import 性能监控.mointor.pojo.Monitor;


public class MonitorInterceptor implements HandlerInterceptor {
	
	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long beginTime = System.currentTimeMillis();
		startTimeThreadLocal.set(beginTime);	
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long beginTime = startTimeThreadLocal.get();
		long time = System.currentTimeMillis() - beginTime; 
		
		String ip = IPUtils.getIpAddress(request);
		String encodeing=request.getCharacterEncoding();
		String uri = request.getRequestURI();
		
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
	
		Monitor.add(new FunItem(uri, param, ip, beginTime, time));
	}
}
