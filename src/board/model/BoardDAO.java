package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BoardDAO {
	//싱글톤 생성
	private static BoardDAO instance = null;
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	//WriteProAction, 글 작성
	public int insertArticle(BoardDto article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int result = 0;
		String sql = "insert into BF(NUM, WRITER, UPLOADFILE, PASSWORD, DESCRIPTION, COUNT) "
				+ "values(BF_SEQ.nextval, ?, ?, ?, ?, ?)";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getUploadfile());
			pstmt.setString(3, article.getPassword());
			pstmt.setString(4, article.getDescription());
			pstmt.setInt(5, article.getCount());

			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null)try {conn.close();} catch(SQLException e) {}

		}
		return result;
	}
	//전체 목록 생성
	public List<BoardDto> getArticles(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDto> articleList = null;
		
		try {
			conn = ConnUtil.getConnection();
			pstmt=conn.prepareStatement("select * from BF order by NUM desc");
			rs=  pstmt.executeQuery();
			if(rs.next()) {
				articleList = new ArrayList<BoardDto>();
				do {
					BoardDto article = new BoardDto();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setUploadfile(rs.getString("uploadfile"));
					article.setPassword(rs.getString("password"));
					article.setDescription(rs.getString("description"));
					article.setCount(rs.getInt("count"));
					articleList.add(article);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
		return articleList; 
	}
	//ContentAction 한 게시물의 글
	public BoardDto getArticle(int num){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto article =null;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("update BF set COUNT = COUNT+1 where NUM =?");
			pstmt.setInt(1, num);
			pstmt.executeQuery();
			pstmt.close();
			pstmt= conn.prepareStatement("select * from BF where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new BoardDto();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setUploadfile(rs.getString("uploadfile"));
				article.setPassword(rs.getString("password"));
				article.setDescription(rs.getString("description"));
				article.setCount(rs.getInt("count"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
		return article;
	}
	
	//수정하기 , UpdateProAction
	public BoardDto updateGetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto article = null;
		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select * from BF where NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new BoardDto();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setUploadfile(rs.getString("uploadfile"));
				article.setPassword(rs.getString("password"));
				article.setDescription(rs.getString("description"));

			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}	
			if(conn !=null) try {conn.close();}catch(SQLException e) {}
		}
		return article;
	
	}
	 public int updateArticle(BoardDto article) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String dbpass = "";
		 String sql = "";
		 int result= -1;
		 try {
			 conn = ConnUtil.getConnection();
			 pstmt = conn.prepareStatement("select PASSWORD from BF where NUM = ?");
			 pstmt.setInt(1, article.getNum());
			 rs= pstmt.executeQuery();
			 
			 if(rs.next()) {
				 dbpass = rs.getString("password");
				 if(dbpass.equals(article.getPassword())) {
					 sql = "update BF set WRITER=?, UPLOADFILE=?, PASSWORD=?, DESCRIPTION=? "
					 		+ "where NUM =?";
					 pstmt.close();
					 pstmt =conn.prepareStatement(sql);
					 pstmt.setString(1, article.getWriter());
					 pstmt.setString(2, article.getUploadfile());
					 pstmt.setString(3, article.getPassword());
					 pstmt.setString(4, article.getDescription());
					 pstmt.setInt(5, article.getNum());
					 pstmt.executeQuery();
					 result = 1;
				 }else {
					 result = 0;
				 }
			 }
		 }catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)try {rs.close();} catch(SQLException e) {}
				if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}	
				if(conn !=null) try {conn.close();}catch(SQLException e) {}
			}
			return result;
	 }
	 public int deleteArticle(int num, String pass) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String dbpass= "";
		 int result = -1;
		 try {
			 conn = ConnUtil.getConnection();
			 pstmt = conn.prepareCall("select PASSWORD from BF where NUM=?");
			 pstmt.setInt(1, num);
			 rs = pstmt.executeQuery();

			 if(rs.next()) {
				 dbpass = rs.getString("PASSWORD");
				 if(dbpass.equals(pass)) {
					 pstmt.close();
					 pstmt= conn.prepareStatement("delete from BF where NUM=?");
					 pstmt.setInt(1, num);
					 pstmt.executeUpdate();
					 result = 1;
				 }else {
					 result = 0;
				 }
			 }
			 
		 }catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)try {rs.close();} catch(SQLException e) {}
				if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}	
				if(conn !=null) try {conn.close();}catch(SQLException e) {}
			}
			return result;
	 }
	 
	 public BoardDto deleteFile(int num) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs= null;
		 BoardDto article = null;
	
		 try {
			 conn = ConnUtil.getConnection();
			 pstmt = conn.prepareStatement("select UPLOADFILE from BF where NUM = ?");
			 pstmt.setInt(1, num);
			 rs = pstmt.executeQuery();
			 if(rs.next()) {
				 article = new BoardDto();
				 article.setUploadfile(rs.getString("uploadfile"));
			 }
		 }catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)try {rs.close();} catch(SQLException e) {}
				if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}	
				if(conn !=null) try {conn.close();}catch(SQLException e) {}
			}
			return article;
	 }
}
