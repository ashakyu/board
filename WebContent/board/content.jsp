<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드 파일 정보</title>
</head>
<body>
<h1>업로드 파일 정보</h1>
<section>
	<article>
		<table>
			<tr>
				<th class="no">No.</th>
				<th class="writer" colspan="3">Uploader</th>
				<th class="count">Count</th>
			</tr>
			<tr>
				<td>${article.num }</td>
				<td colspan="3">${article.writer }</td>
				<td>${article.count }</td>
			</tr>
			<tr>
				<th class="desc">설명</th>
				<td class="desc" colspan="4">${article.description }</td>
			</tr>
			<tr>
				<th>File</th>
				<td colspan="4"><a href="${pageContext.request.contextPath }/board/upload/${article.uploadfile}" download>
					${article.uploadfile }
				</a></td>
			</tr>
			<tr>
				<td colspan="5">
					<input type="button" value="수정" onClick="window.location.href='${pageContext.request.contextPath}/board/updateForm.do?num=${article.num}'">
					<input type="button" value="삭제" onClick="window.location.href='${pageContext.request.contextPath}/board/deleteForm.do?num=${article.num }'">
					<input type="button" value="목록" onClick="javascript:location.href='${pageContext.request.contextPath}/board/list.do'">
					
		</table>
	</article>
</section>
</body>
</html>