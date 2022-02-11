package board.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardDto;

public class DeleteProAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
	throws Throwable{
		request.setCharacterEncoding("utf-8");
		int num= Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDto article = dao.deleteFile(num);
		String fileName = article.getUploadfile();
		System.out.println(fileName);
		
		String savePath="/board/upload/";
		ServletContext context = request.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println(uploadFilePath+fileName);
		
		if(fileName != null) {
				File file = new File(uploadFilePath+fileName);
				if(file.exists()) {
					file.delete();
					System.out.println("파일 삭제 완료");
				}else {
					System.out.println("파일 삭제 실패");
				}
			}
		
		int result = dao.deleteArticle(num, pass);
		request.setAttribute("result", new Integer(result));
		
		return "/board/deletePro.jsp";
		
	}
}
