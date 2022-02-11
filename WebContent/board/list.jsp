<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실</title>
</head>
<body>
<h1>자료실</h1>
s<section>
	<article>
		<table>
			<tr>
				<th class="no">No.</th>
				<th class="desc">FileDescription</th>
				<th class="filename">File Name</th>
				<th class="writer">Uploader</th>
				<th class="count">count</th>
			</tr>
			
			<c:forEach var="article" items="${articleList}">
			<tr>
				<td class="no">${article.num}</td>
				<td class="desc">
					<a href="${pageContext.request.contextPath }/board/content.do?num=${article.getNum()}">${article.description}</a></td>
				<td class="filename">${article.uploadfile }</td>
				<td class="writer">${article.writer}</td>
				<td class="count">${article.count}</td>
			</tr>
			</c:forEach>
			
			<tr>
				<td colspan="4">
					<input type="button" value="파일 올리기" onClick="window.location.href='${pageContext.request.contextPath}/board/writeForm.do'">
				</td>
			</tr>
		</table>
		
		
	</article>
</section>
</body>
</html>