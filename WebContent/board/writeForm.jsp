<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
<h1>파일 올리기</h1>
<section>
	<article>
		<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/board/writePro.do">
			<table>
				<tr>
					<td><input class="input" type="file" name="uploadfile" required></td>
				</tr>
				
				<tr>
					<td><input class="input" type="text" name="writer" placeholder="게시자 이름" required></td>
				</tr>
				<tr>
					<td><input class="input" type="password" name="pass" placeholder="비밀번호(20자 이내)" maxlength="20" required></td>
				</tr>
				<tr>
					<td><textarea rows="5" class="input" name="desc" placeholder="파일설명(100자 이내)" maxlength="100" required></textarea>
				</tr>
			</table>
			<input class="bt" type="submit" value="올리기"><input class="bt"	type="button" value="목록" onClick="window:location.href='${pageContext.request.contextPath}/board/list.do'">
		</form>
	</article>
</section>
</body>
</html>