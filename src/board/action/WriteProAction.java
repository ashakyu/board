package board.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.util.*;
import board.model.BoardDAO;
import board.model.BoardDto;

public class WriteProAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
	throws Throwable{
		request.setCharacterEncoding("utf-8");
		BoardDAO dao = BoardDAO.getInstance();
		BoardDto article = new BoardDto();
		
		//파일 설정(5mb)
		int uploadFileSizeLimit = 5 * 1024 * 1024; 
		String encType="utf-8";
		
		String savePath="/board/upload";
		ServletContext context = request.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("서버상의 실제 디렉토리");
		System.out.println(uploadFilePath);
		
		//폴더가 없을 경우 생성
		File makeFolder = new File(uploadFilePath);
		if(!makeFolder.exists()) {
			makeFolder.mkdir();
			System.out.println("폴더 생성 완료");
		}else {
			System.out.println("해당 폴더가 존재합니다.");
		}
		try {
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadFilePath,
					uploadFileSizeLimit,
					encType,
					new DefaultFileRenamePolicy());
			String fileName = multi.getFilesystemName("uploadfile");
			
			if(fileName == null) {
				System.out.print("파일 업로드 실패");

			}else {
				article.setWriter(multi.getParameter("writer"));
				article.setPassword(multi.getParameter("pass"));
				article.setDescription(multi.getParameter("desc"));
				article.setUploadfile(fileName);
				
				dao.insertArticle(article);
				
				}
			}catch(Exception e){
				System.out.print("예외 발생 : "+ e);
				e.printStackTrace();
		}
		
		return "/board/writePro.jsp";
	
	}
}
