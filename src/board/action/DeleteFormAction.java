package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFormAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse)throws
	Throwable{
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		request.setAttribute("num", num);
		
		return "/board/deleteForm.jsp";
	}
}
