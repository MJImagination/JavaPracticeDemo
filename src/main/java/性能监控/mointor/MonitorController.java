package 性能监控.mointor;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import 性能监控.mointor.page.MonitorPage;
import 性能监控.mointor.pojo.Monitor;


/**
 * 性能监控
 * @author micktiger
 */
@Controller
@RequestMapping(value="/funmonitor")
public class MonitorController {
	/**
	 * 打印结果
     * @param model
	 * @throws IOException 
    */
    @RequestMapping(value="/show", method=RequestMethod.GET)
    public void show(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	OutputStream outwriter = response.getOutputStream();  
  		String encodeing=request.getCharacterEncoding();
  		
  		response.setCharacterEncoding(encodeing);
  		response.setHeader("content-type", "text/html;charset="+encodeing+"");
  		response.setContentType("text/html;charset="+encodeing+"");
  		
  		byte[]  wrtiebyte = MonitorPage.getDisplayPage(encodeing);
  		outwriter.write(wrtiebyte);
		Monitor.clear();
    }
    
    /**
	 * 设置队列
     * @param model
	 * @throws IOException 
    */
    @RequestMapping(value="/set", method=RequestMethod.GET)
    public void set(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	OutputStream outwriter = response.getOutputStream();
		String encodeing = request.getCharacterEncoding();
		
		response.setCharacterEncoding(encodeing);
		response.setHeader("content-type", "text/html;charset=" + encodeing+ "");
		response.setContentType("text/html;charset=" + encodeing + "");
		
		String countSize = request.getParameter("countSize");
		String initTime = request.getParameter("initTime");
		
		byte[]  wrtieByte =  MonitorPage.setPageSet(encodeing,countSize,initTime);
		outwriter.write(wrtieByte);
    }
}
