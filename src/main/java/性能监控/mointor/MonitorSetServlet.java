package 性能监控.mointor;

import 性能监控.mointor.page.MonitorPage;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MonitorSetServlet extends HttpServlet{
	private static final long serialVersionUID = -2368120987443272843L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		OutputStream outwriter = response.getOutputStream();
		String encodeing = request.getCharacterEncoding();
		
		response.setCharacterEncoding(encodeing);
		response.setHeader("content-type", "text/html;charset=" + encodeing+ "");
		response.setContentType("text/html;charset=" + encodeing + "");
		
		String countSize = request.getParameter("countSize");
		String initTime = request.getParameter("initTime");
		
		byte[]  wrtieByte = MonitorPage.setPageSet(encodeing,countSize,initTime);
		outwriter.write(wrtieByte);
	}
}
