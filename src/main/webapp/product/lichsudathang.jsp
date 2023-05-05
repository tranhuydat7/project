<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch sử đặt hàng</title>
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
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>
	<!-- end header -->
	<!-- <nav class="navbar navbar-expand-lg bg-body-tertiary"
		style="background-color: #e3f2fd;">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				src="/docs/5.3/assets/brand/bootstrap-logo.svg" alt="Bootstrap"
				width="30" height="24">
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav nav-underline me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Trang chủ</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Combo
							giảm giá</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Thể loại </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">Quần jean</a></li>
							<li><a class="dropdown-item" href="#">Áo thun</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="#">Áo sơ mi</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link disabled">Hết hàng</a>
					</li>
				</ul>
				<form class="d-flex" role="search">
					<input class="form-control me-2" type="search"
						placeholder="Nội dung tìm kiếm" aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Tìm</button>
					<div class="ps-2 pe-2 pt-1" style="cursor: pointer">
						<i class="bi bi-cart" style="font-size: 2rem;"></i>
					</div>
					<a class="btn btn-primary" style="white-space: nowrap;" href="">Đăng
						nhập</a>


					<div class="btn-group" style="margin-left: 0.2em">
					<button type="button" class="btn btn-info dropdown-toggle"
						data-bs-toggle="dropdown" aria-expanded="false">Tài khoản</button>
					<ul class="dropdown-menu dropdown-menu-end">
						<li><a class="dropdown-item" href="#">Đơn hàng của tôi</a></li>
						<li><a class="dropdown-item" href="#">Thông báo</a></li>
						<li><a class="dropdown-item" href="#">Thay đổi thông tin</a></li>
						<li><a class="dropdown-item" href="#">Đổi mật khẩu</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="#">Thoát tài khoản</a></li>
					</ul>
				</div>
				</form>
			</div>
		</div>
	</nav> -->
	<!-- end header -->

	<%
	Object obj = session.getAttribute("khachHang");
	KhachHang khachHang = null;
	if (obj != null)
		khachHang = (KhachHang) obj;
	if (khachHang == null) {
	%>
	<h1>Bạn chưa đăng nhập vào hệ thống. vui lòng quay lại trang chủ</h1>
	<%
	} else {
	%>
	<%
	String baoLoi = request.getAttribute("baoLoi") + "";
	if (baoLoi == null || baoLoi.equals("null")) {
		baoLoi = "";
	}

	String tenDangNhap = request.getAttribute("tenDangNhap") + "";
	if (tenDangNhap == null || tenDangNhap.equals("null")) {
		tenDangNhap = "";
	}

	String tenDangNhapp = khachHang.getTenDangNhap();

	String hoVaTen = khachHang.getHoVaTen();

	/* String gioiTinh = khachHang.getGioiTinh(); */

	/* String ngaySinh = khachHang.getNgaySinh().toString(); */

	/* String diaChiKhachHang = khachHang.getDiaChi(); */

	/* String diaChiMuaHang = khachHang.getDiaChiMuaHang(); */

	/* String diaChiNhanHang = khachHang.getDiaChiNhanHang(); */

	String dienThoai = khachHang.getSoDienThoai();

	String email = khachHang.getEmail();
	%>
	<div class="container">
		<div class="card mt-3 mb-3">
			<div></div>

			<div>
				<div class="row">
					<div class="col-md-2 mt-1 ms-1">
						<div>
							<img
								src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img4.webp"
								class="img-fluid rounded-3" alt="Shopping item"
								style="width: 170px;">
						</div>
					</div>
					<div class="col-5">
						<h4 class="mb-5">MacBook Pro</h4>
					</div>
					<div class="col-2">
						<div class="d-grid gap-2">
							<button class="btn btn-primary bg-warning" type="submit">Đến
								trang thanh toán</button>
						</div>
						<div class="d-grid gap-2">
							<button class="btn btn-primary bg-warning" type="submit">Đến
								trang thanh toán</button>
						</div>
						<div class="d-grid gap-2">
							<button class="btn btn-primary bg-warning" type="submit">Đến
								trang thanh toán</button>
						</div>
						<div class="d-grid gap-2">
							<button class="btn btn-primary bg-warning" type="submit">Đến
								trang thanh toán</button>
						</div>
						<div class="d-grid gap-2">
							<button class="btn btn-primary bg-warning" type="submit">Đến
								trang thanh toán</button>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<%
	}
	%>


	<!-- footer -->
	<footer class="mt-5">
		<div class="container">
			<div class="row">
				<div class="col-xl-3 col-lg-4 col-md-6">
					<div>
						<h3>Logo</h3>
						<p class="mb-30 footer-desc">Lorem ipsum dolor sit amet
							consectetur adipisicing elit. Ad soluta facilis eos quia optio
							iusto odit atque eum tempore, quisquam officiis vero veniam hic,</p>
					</div>
				</div>
				<div class="col-xl-2 offset-xl-1 col-lg-2 col-md-6">
					<div class="">
						<h4>Quick Link</h4>
						<ul class="list-unstyled">
							<li><a href="#" class="text-decoration-none">Home</a></li>
							<li><a href="#" class="text-decoration-none">About Us</a></li>
							<li><a href="#" class="text-decoration-none">Service</a></li>
							<li><a href="#" class="text-decoration-none">Contact</a></li>
						</ul>
					</div>
				</div>
				<div class="col-xl-3 col-lg-3 col-md-6">
					<div>
						<h4>Service</h4>
						<ul class="list-unstyled">
							<li><a href="#" class="text-decoration-none">Marketing</a></li>
							<li><a href="#" class="text-decoration-none">Branding</a></li>
							<li><a href="#" class="text-decoration-none">Web Design</a>
							</li>
							<li><a href="#" class="text-decoration-none">Graphics
									Design</a></li>
						</ul>
					</div>
				</div>
				<div class="col-xl-3 col-lg-3 col-md-6">
					<div>
						<h4>Address</h4>
						<ul class="list-unstyled">
							<li>
								<p>+017367234</p>
							</li>
							<li>
								<p>
									<a href="#">JhoneDoe@gmail.com</a>
								</p>
							</li>
							<li>
								<p>New York City in New York State New York City comprises 5
									boroughs sitting where the Hudson River meets th</p>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="d-flex justify-content-center">

				<div class="copyright">
					<p>
						Developed and maintained by <a href="#" target="_blank">company</a>
					</p>
				</div>
			</div>
		</div>
	</footer>
	<!-- end footer -->
</body>
</html>