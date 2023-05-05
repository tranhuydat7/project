<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.SanPham"%>
<%@page import="java.util.Map"%>
<%@page import="model.GioHang"%>
<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Giỏ hàng</title>
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

	<%
	Long cartID = (Long) session.getAttribute("cartID");
	GioHang gioHang = (GioHang) session.getAttribute("gioHang");
	Map<SanPham, Integer> maps = gioHang.getLists();
	%>

	<form action="#" method="get">
		<div class="container mt-3">
			<div class="row">
				<div class="col-9 card mb-3 mb-lg-0">
					<div class="mb-auto p-2 mt-1 fs-3">Giỏ hàng</div>
					<div class="d-flex flex-row-reverse">
						<h6 class="mt-auto p-2 mb-0 me-5">Giá</h6>
					</div>
					<hr>
					<%
					for (Map.Entry<SanPham, Integer> entry : maps.entrySet()) {
					%>
					<div class="card-body">
						<div class="d-flex justify-content-between">
							<div class="d-flex flex-row align-items-center mb-3">
								<div>
									<img
										src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img4.webp"
										class="img-fluid rounded-3" alt="Shopping item"
										style="width: 170px;">
								</div>
								<div class="ms-3">
									<h4 class="mb-5"><%=entry.getKey().getTenSanPham()%></h4>
									<div class="row">
										<div class="col-3 mt-1">
											<label class="fs-6" for="form2Example27">Số lượng:</label>
										</div>
										<div class="col-4 ">
											<input class="form-control fs-6" type="number" min="1"
												value="<%=entry.getValue()%>" />
										</div>
										<div class="col-2 fs-6 mt-1">
											<span>Xoá</span>
										</div>
									</div>
								</div>
							</div>
							<div class="d-flex flex-row align-items-center">
								<div style="width: 80px;">
									<h5 class="mb-0"><%=entry.getKey().getGiaBan()%> VND</h5>
								</div>
								<a href="#!" style="color: #cecece;"><i
									class="fas fa-trash-alt"></i></a>
							</div>
						</div>
						<hr>
					</div>
					<%
					}
					%>
					<div class="d-flex flex-row-reverse">
						<div class="p-2" style="width: 80px;">
							<h5 class="mb-0">$1799</h5>
						</div>
						<div class="p-2">
							<h5 class="mb-0">Tổng tiền:</h5>
						</div>
					</div>
				</div>

				<div class="col-lg-3">
					<div class="card bg-primary text-white rounded-3">
						<div class="card-body">
							<div class="d-flex justify-content-between mb-4">
								<p class="small mb-2 fs-4">Tổng tiền:</p>
								<p class="mb-2 fs-4">$4818.00</p>
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
	</form>
	<%
	}
	%>

	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	<!-- end footer -->

</body>
</html>