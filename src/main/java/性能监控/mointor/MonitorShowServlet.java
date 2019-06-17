package 性能监控.mointor;

import 性能监控.mointor.page.MonitorPage;
import 性能监控.mointor.pojo.Monitor;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MonitorShowServlet extends HttpServlet{
	
	private static final long serialVersionUID = -1967230653057405219L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

	    OutputStream outwriter = response.getOutputStream();  
		String encodeing=request.getCharacterEncoding();
		response.setCharacterEncoding(encodeing);
		response.setHeader("content-type", "text/html;charset="+encodeing+"");
		response.setContentType("text/html;charset="+encodeing+"");
		try {
			byte[]  wrtieByte = MonitorPage.getDisplayPage(encodeing);
			outwriter.write(wrtieByte);
			Monitor.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
