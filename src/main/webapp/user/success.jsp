<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success</title>
</head>
<body>
	<%-- <jsp:include page="../header.jsp"></jsp:include> --%>
	<div class="container">
		<h1>Chúc mừng bạn đã đăng kí tài khoản thành công! Vui lòng quay
			lại</h1>
	</div>
	<script>
		setTimeout(function() {
			window.location.href = 'index.jsp';
		}, 5000);
	</script>
	<%-- <jsp:include page="../footer.jsp"></jsp:include> --%>
</body>
</html>