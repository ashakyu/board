<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result == 1 }">
	<meta http-equiv="Refresh" content = "0;url=${pageContext.request.contextPath }/board/list.do">
</c:if>

<c:if test="${check != 1 }"> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 실패</title>
</head>
<body>
	<h2>비밀번호가 다릅니다.</h2>
	<br><br>
	<a href="javascript:history.go(-1)">[이전으로 돌아가기]</a>
</c:if>
</body>
</html>