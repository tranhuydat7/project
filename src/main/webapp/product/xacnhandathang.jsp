<%@page import="model.KhachHang"%>
<%@page import="model.SanPham"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xác nhận nội dung đặt hàng và thay đổi</title>
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
	<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
	<!-- header -->
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
	<jsp:include page="../header.jsp"></jsp:include>
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
	String maKhachHang = khachHang.getMaKhachHang() + "";

	String hoVaTen = khachHang.getHoVaTen();

	/* String gioiTinh = khachHang.getGioiTinh(); */

	/* String ngaySinh = khachHang.getNgaySinh().toString(); */

	/* String diaChiKhachHang = khachHang.getDiaChi(); */

	/* String diaChiMuaHang = khachHang.getDiaChiMuaHang(); */

	/* String diaChiNhanHang = khachHang.getDiaChiNhanHang(); */

	String dienThoai = khachHang.getSoDienThoai();

	String email = khachHang.getEmail();
	%>


	<!-- nội dung đặt hàng -->
	<form action="<%=url%>/san-pham-controller" method="post">
		<%
		String soLuong = request.getAttribute("soLuong") + "";
		String tongTien = request.getAttribute("tongTien") + "";
		SanPham sanPham = (SanPham) request.getAttribute("sanPham");
		%>
		<input type="hidden" name="hanhDong" value="dat-hang-thanh-cong" /> <input
			type="hidden" name="maSanPham" value="<%=sanPham.getMaSanPham()%>" />
		<input type="hidden" name="tongTien" value="<%=tongTien%>" />
		<div class="container">
			<div class="row mt-3">
				<!-- left thong tin -->
				<div class="col-lg-9">
					<!-- dia chi gui hang -->
					<div>
						<div class="row">
							<div class="col-4">
								<h5>1. Địa chỉ gửi hàng</h5>
							</div>

							<div class="col-6">
								<h6>Name</h6>
								<h6>Địa chỉ gửi hàng:</h6>
							</div>

							<div class="col-2">
								<h6 style="cursor: pointer">Thay đổi</h6>
							</div>
						</div>

					</div>
					<!--  end dia chi gui hang -->
					<hr>

					<!--  phuong thuc thanh toan -->
					<div>
						<div>
							<div class="row">
								<div class="col-4">
									<h5>
										2. <span id="phuongthucthanhtoan">Phương thức thanh
											toán</span>
									</h5>
								</div>

								<div class="col-6">
									<h6 id="phuongthucmacdinh">Siêu thị tiện lợi</h6>
									<div id="card" class="card" style="visibility: hidden;">
										<!-- thanh toan qua the ngan hang -->
										<div class="mt-3 me-3 ms-3 mb-3">
											<div>
												<div class="form-check">
													<input class="form-check-input" type="radio"
														name="phuongthucthanhtoan" id="theNganHang"
														value="Thanh toán qua thẻ ngân hàng"> <label
														class="form-check-label" for="flexRadioDefault1">
														Thanh toán qua thẻ ngân hàng</label>
												</div>
											</div>
											<hr>
											<!--  end thanh toan qua the ngan hang -->

											<!--  nhan hang thanh toan -->
											<div>
												<div class="form-check">
													<input class="form-check-input" type="radio"
														name="phuongthucthanhtoan" id="sieuThiTienLoi"
														value="Thanh toán qua siêu thị tiện lợi" checked>
													<label class="form-check-label" for="flexRadioDefault1">
														Thanh toán qua siêu thị tiện lợi</label>
												</div>
											</div>
											<hr>
											<!--  end nhan hang thanh toan -->

											<!--  thanh toan qua sieu thi tien loi, ATM -->
											<div>
												<div class="form-check">
													<input class="form-check-input" type="radio"
														name="phuongthucthanhtoan" id="thanhToanTaiNha"
														value="Nhận hàng thanh toán"> <label
														class="form-check-label" for="flexRadioDefault1">
														Nhận hàng thanh toán</label>
												</div>
											</div>
											<hr>
											<!--  end thanh toan qua sieu thi tien loi, ATM -->

											<div class="d-grid gap-2 d-md-block">
												<button class="btn bg-warning" type="button"
													id="btnPhuongThuc" onclick="changePhuongThuc()">Sử
													dụng phương thức thanh toán này</button>
											</div>
										</div>
									</div>
								</div>

								<div id="btnChange" class="col-2">
									<h6 style="cursor: pointer" id="btnDong">Thay đổi</h6>
									<i class="bi bi-x-lg" style="visibility: hidden;"></i>
								</div>
							</div>
						</div>
						<!--  end phuong thuc thanh toan -->
						<hr>

						<!--  xac nhan san pham va thong tin gui -->
						<div class="mb-3">
							<h5>3. Xác nhận thông tin sản phẩm</h5>
							<div class="card">
								<div class="row mt-3 ms-3 mb-3">
									<div class="col-3">
										<div>
											<img
												src="<%=url%>/image/avatar/<%=sanPham.getAvatar()%>"
												class="img-fluid rounded-3" alt="Shopping item"
												style="width: 170px;">
										</div>
									</div>

									<div class="col-7">
										<h4 class="mb-5"><%=sanPham.getTenSanPham()%></h4>
										<div class="row">
											<div class="col-2 mt-1">
												<label class="fs-6" for="form2Example27">Số lượng:</label>
											</div>
											<div class="col-2">
												<input class="form-control fs-6" name="soLuong" id="soLuong"
													type="number" min="1" value="<%=soLuong%>"
													onchange="changeCount()" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--  end xac nhan san pham va thong tin gui -->

					</div>
				</div>
				<!-- end left thong tin -->

				<!-- button xac nhan right -->
				<div class="col-lg-3">
					<div class="card bg-primary text-white rounded-3">
						<div class="card-body">
							<div class="d-flex justify-content-between mb-4">
								<p class="small mb-2 fs-4">Tổng tiền:</p>
								<p id="tongTien" class="mb-2 fs-4"><%=tongTien%>
									VND
								</p>
								<%-- 	<input style="border: none; background-color: #0d6efd;"
									id="tongTien" class="mb-2 fs-4" value="<%=tongTien%>" /> --%>
							</div>
							<div class="d-grid gap-2">
								<button class="btn btn-primary bg-warning" type="submit">Xác
									nhận đặt hàng</button>
							</div>
						</div>
					</div>
				</div>
				<!--  end button xac nhan right -->
			</div>
		</div>
		<!-- end nội dung đặt hàng -->
	</form>

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

	<script type="text/javascript">
		var btnDong = document.getElementById("btnDong");
		var card = document.getElementById("card");
		var text = btnDong.innerText;

		var phuongthucthanhtoan = document
				.getElementById("phuongthucthanhtoan");

		function chang() {
			var text = btnDong.innerText;
			if (text == "Đóng") {
				btnDong.innerText = "Thay đổi";
				card.style.visibility = "hidden";
				phuongthucthanhtoan.innerHTML = "Phương thức thanh toán";
			} else {
				btnDong.innerText = "Đóng";
				card.style.visibility = "visible";
				phuongthucthanhtoan.innerHTML = "Chọn phương thức thanh toán";
			}
		}

		btnDong.addEventListener("click", chang)

		var macDinh = document.getElementById("phuongthucmacdinh");
		var theNganHang = document.getElementById("theNganHang");
		var sieuThi = document.getElementById("sieuThiTienLoi");
		var nhanHangThanhToan = document.getElementById("thanhToanTaiNha");
		var btnPt = document.getElementById("btnPhuongThuc");

		if (sieuThi.defaultChecked) {
			macDinh.innerHTML = sieuThi.value
		}

		function changePhuongThuc() {
			if (theNganHang.checked) {
				macDinh.innerHTML = theNganHang.value;
			} else if (nhanHangThanhToan.checked) {
				macDinh.innerHTML = nhanHangThanhToan.value;
			} else if (sieuThi.checked) {
				macDinh.innerHTML = sieuThi.value;
			}
			btnDong.innerText = "Thay đổi";
			card.style.visibility = "hidden";
			phuongthucthanhtoan.innerHTML = "Phương thức thanh toán";

		}

		/* var soLuong = document.getElementById("soLuong");
		var tongTien = document.getElementById("tongTien");
		function changeCount() {
			var soLuongParseInt = parseInt(soLuong.value);
			var tongTienParseInt = parseInt(tongTien.value);
			var result = soLuongParseInt * tongTienParseInt;
			tongTien.value =result;
		} */
	</script>
</body>
</html>