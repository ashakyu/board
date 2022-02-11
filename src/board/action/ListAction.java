package board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardDto;

public class ListAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
	throws Throwable{
		request.setCharacterEncoding("utf-8");
		BoardDAO dao = BoardDAO.getInstance();
		
		List<BoardDto> articleList = null;
		articleList = dao.getArticles();
		
		request.setAttribute("articleList", articleList);
		System.out.println("객체생성"+articleList);
		return "/board/list.jsp";
	}
}
