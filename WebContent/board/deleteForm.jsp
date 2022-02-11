<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제Form</title>
</head>
<body>
<h1>업로드 파일 삭제하기</h1>
<section>
	<article>
		<form method="post" name="deleteForm" action="${pageContext.request.contextPath }/board/deletePro.do">
		<input type="hidden" name="num" value="${num }">
			<table>
				<tr>
					<th>비밀번호</th>
					<td><input class="input" type="password" name="pass" required></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="삭제">
						<input type="button" value="취소" onClick="javascript:history.go(-1)">
					</td>
				</tr>
			</table>
		</form>
	</article>
</section>
</body>
</html>