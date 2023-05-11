<%@page import="model.DonHang"%>
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
<style>
a:link, a:visited {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

.border-btn {
	border-radius: 50%;
	color: black;
	border: 2px solid #e7e7e7;
	color: black;
}
</style>

</head>
<body>
	<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>

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
	int tongTien = 0;
	%>
	<div class="container mt-3" id="display2" style="display: none">
		<h3>Không có sản phẩm nào trong giỏ hàng của bạn</h3>
	</div>
	<form id="display1" action="<%=url%>/san-pham-controller" method="get">
	<input type="hidden" name="hanhDong" value="xac-nhan-dat-hang-tu-gio-hang" /> 
	<%-- <input type="hidden" name="maSanPham" value="<%=sanPham.getMaSanPham()%>" /> --%>
		<div class="container mt-3">
			<div class="row">
				<%
				/* DonHang donHang = (DonHang) session.getAttribute("donHang"); */
				if (gioHang == null
				/* || donHang != null */
				) {
				%>
				<!-- <div class="d-flex flex-row-reverse">
						<div class="p-2" style="width: 80px;">
							<h5 class="mb-0">VND</h5>
						</div>
						<div class="p-2">
							<h5 class="mb-0">Tổng tiền:</h5>
						</div>
					</div> -->
				<%
				} else if (gioHang != null) {
				int tongSP = 0;
				for (Map.Entry<SanPham, Integer> entry : maps.entrySet()) {
					tongSP += entry.getValue();
				}
				%>

				<div class="col-lg-9 card mb-3 mb-lg-0">
					<div class="mb-auto p-2 mt-1 fs-3">Giỏ hàng ( <%=tongSP %> sản phẩm )</div>
					<div class="d-flex flex-row-reverse">
						<h6 class="mt-auto p-2 mb-0 me-5">Giá</h6>
					</div>
					<hr>

					<%
					for (Map.Entry<SanPham, Integer> entry : maps.entrySet()) {
						tongTien += entry.getKey().getGiaBan() * entry.getValue();
					%>
					<div class="card-body">
						<div class="d-flex justify-content-between">
							<div class="d-flex flex-row align-items-center mb-3">
								<div>
									<img
										src="<%=url%>/image/avatar/<%=entry.getKey().getAvatar()%> "
										class="img-fluid rounded-3" alt="Shopping item"
										style="width: 170px;">
								</div>
								<div class="ms-3">
									<a style="font-size: 30px;" class="mb-5"
										href="<%=url%>/san-pham-controller?hanhDong=san-pham-detail&maSanPham=<%=entry.getKey().getMaSanPham()%> "><%=entry.getKey().getTenSanPham()%></a>
									<div class="row">
										<div class="col-3 mt-1">
											<label class="fs-6" for="form2Example27">Số lượng:</label>
										</div>
										<div class="col-4 ">
											<div class="row">
												<div class="col-3 me-2">
													<a
														href="<%=url%>/gio-hang-controller?hanhDong=chi-tiet-gio-hang&maSanPham=<%=entry.getKey().getMaSanPham()%>&countMinus=1"
														class="btn border-btn"> <i class="bi bi-dash-lg"></i>
													</a>
												</div>
												<div class="col-2 ms-3">
													<span id="giaSanPham" class=" fs-6"><%=entry.getValue()%></span>
												</div>
												<div class="col-3">
													<a
														href="<%=url%>/gio-hang-controller?hanhDong=chi-tiet-gio-hang&maSanPham=<%=entry.getKey().getMaSanPham()%>&countPlus=1"
														class="btn border-btn"> <i class="bi bi-plus-lg"></i>
													</a>
												</div>
											</div>
										</div>
										<div class="col-2 fs-6 mt-1 ms-3">
											<!-- <span>Xoá</span> -->
											<a
												href="<%=url%>/gio-hang-controller?hanhDong=xoa-sanpham-trong-gio-hang&maSanPham=<%=entry.getKey().getMaSanPham()%>">Xoá</a>
										</div>
									</div>
								</div>
							</div>
							<div class="d-flex flex-row align-items-center">
								<div style="width: 80px;">
									<h5 class="mb-0"><%=entry.getKey().getGiaBan() * entry.getValue()%>
										VND
									</h5>
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
							<h5 class="mb-0"><%=tongTien%>
								VND
							</h5>
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
								<p class="mb-2 fs-4">
									<span id="spann"><%=tongTien%></span> VND
								</p>
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
	}
	%>

	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	<!-- end footer -->

	<script>
		var str = "&lt;%=tongTien%&gt;";
		var tT = document.getElementById("spann");
		var display1 = document.getElementById("display1");
		var display2 = document.getElementById("display2");
		if (tT.innerHTML == 0) {
			display1.style.display = "none";
			display2.style.display = "block";
		}
	</script>

</body>
</html>