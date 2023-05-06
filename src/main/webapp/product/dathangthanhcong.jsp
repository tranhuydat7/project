<%@page import="model.ChiTietDonHang"%>
<%@page import="model.SanPham"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đặt hàng</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"
	integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	<!-- end header -->
	<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>

	<%
	ChiTietDonHang chiTietDonHang = (ChiTietDonHang) session.getAttribute("chiTietDonHang");
	%>
	<div class="container mt-3">
		<div class="row">
			<div class="col-7 card mb-3 mb-lg-0">
				<div class="card-body">
					<div class="row">
						<div class="col-5 d-flex justify-content-center">
							<img src="<%=url%>/image/avatar/<%=chiTietDonHang.getSanPham().getAvatar() %>"
								class="img-fluid rounded-3 " alt="Shopping item"
								style="width: 170px;">
						</div>
						<div class="col-7 d-flex align-items-center">
							<i class="bi bi-check-circle-fill me-2 text-success"></i>
							<h5 class="mb-0">Đặt hàng thành công!</h5>
						</div>
					</div>
				</div>
			</div>

			<!-- <div class="col-5">
				<div class="card bg-primary text-white rounded-3">
					<div class="card-body">
						<div class="d-flex justify-content-between mb-4">
							<p class="small mb-2 fs-4">Tổng tiền:</p>
							<p class="mb-2 fs-4">$4818.00</p>
						</div>
						<div class="d-grid gap-2">
							<a class="btn btn-primary bg-warning" href="#">Đến
								trang thanh toán</a>
						</div>
						<div class="d-grid gap-2 mt-2">
							<a class="btn btn-primary bg-warning" href="giohang.jsp">Đến
								trang giỏ hàng</a>
						</div>

					</div>
				</div>
			</div> -->
		</div>
	</div>

	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	<!-- end footer -->

</body>
</html>