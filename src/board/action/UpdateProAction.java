package board.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDAO;
import board.model.BoardDto;

public class UpdateProAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
	throws Throwable{
		request.setCharacterEncoding("utf-8");
		
		BoardDto article = new BoardDto();
		BoardDAO dao = BoardDAO.getInstance();
		
		int uploadFileSizeLimit = 5 * 1024 * 1024;
		String encType = "utf-8";
		
		String savePath="/board/upload";
		ServletContext context = request.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		

		try {
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit,
					encType, new DefaultFileRenamePolicy());
			
			
			String fileName = multi.getFilesystemName("uploadfile");
			if(fileName == null) {
				System.out.println("파일 업로드 실패");
			}else {
				int num = Integer.parseInt(multi.getParameter("num"));
				article.setNum(num);
				article.setWriter(multi.getParameter("writer"));
				article.setPassword(multi.getParameter("pass"));
				article.setDescription(multi.getParameter("desc"));
				article.setUploadfile(fileName);
				
				int check = dao.updateArticle(article);
				request.setAttribute("check", new Integer(check));
			}
			
		}catch(Exception e){
			System.out.print("예외 발생 : "+ e);
			e.printStackTrace();
		}
		
		return "/board/updatePro.jsp";
	}
}
