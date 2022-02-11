package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
	throws Throwable{
		
		return "/board/writeForm.jsp";
	}
}
