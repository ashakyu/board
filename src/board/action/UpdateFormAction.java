package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardDto;

public class UpdateFormAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws
	Throwable{
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDAO dao = BoardDAO.getInstance();
		BoardDto article = dao.updateGetArticle(num);
		
		request.setAttribute("article", article);
		
		return "/board/updateForm.jsp";
	}
}
