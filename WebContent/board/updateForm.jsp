<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>업로드 파일 수정하기</h1>
	<section>
		<article>
			<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/board/updatePro.do">
				<table>
					<tr>
						<td>${article.uploadfile }</td>
					</tr>
				
					<tr>
						<td><input class="input" type="file" name="uploadfile"></td>
					</tr>
					
					<tr>
						<td>${article.writer }</td>
					</tr>
					
					<tr>
						<td>
							<input class="input" type="password" name="pass" placeholder="비밀번호 입력" maxlength="20" required>
						</td>
					</tr>
					
					<tr>
						<td>
							<textarea rows="5" class="input" name="desc" placeholder="파일설명" maxlength="200">
								${article.description }
							</textarea>
						</td>
					</tr>
				</table>
				<input type="hidden" name="num" value="${article.num }">
				<input type="hidden" name="writer" value="${article.writer }">
				<input class="bt" type="submit" value="수정">
				<input class="bt" type="button" value="목록" onClick="window.location='${pageContext.request.contextPath}/board/list.do'">
				
			</form>
		</article>
	</section>
</body>
</html>