<%@page import="model.DonHang"%>
<%@page import="model.SanPham"%>
<%@page import="java.util.Map"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="model.GioHang"%>
<%@ page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/myjavascipt.css" />
<style>
<%@include file="/myjavascipt.css"%>

</style>
<!-- nav bar -->
<header>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
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
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="<%=url%>/index-controller">TRANG CHỦ</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=url%>/san-pham-controller?hanhDong=danh-muc-loai&tenDanhMuc=Nam">NAM</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=url%>/san-pham-controller?hanhDong=danh-muc-loai&tenDanhMuc=nu">NỮ</a></li>
					<!-- <li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" role="button"
					data-bs-toggle="dropdown" aria-expanded="false">Nữ </a> -->
					<!-- <ul class="dropdown-menu">
						<li><a class="dropdown-item" href="#">Quần jean</a></li>
						<li><a class="dropdown-item" href="#">Áo thun</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="#">Áo sơ mi</a></li>
					</ul></li> -->
					<li class="nav-item"><a class="nav-link"
						href="<%=url%>/san-pham-controller?hanhDong=danh-muc-loai&tenDanhMuc=treem">TRẺ
							EM</a></li>
					<li class="nav-item"><a class="nav-link" href="#">GIÁ TỐT</a></li>
					<li class="nav-item"><a class="nav-link" href="#">MỚI</a></li>
				</ul>
				<form class="d-flex" role="search"
					action="<%=url%>/san-pham-controller" method="get">
					<input type="hidden" name="hanhDong" value="search-san-pham" /> <input
						role="search" class="form-control me-2" type="search"
						placeholder="Nội dung tìm kiếm" aria-label="Search"
						name="keySearch" required>
					<button class="btn btn-outline-success" type="submit">Tìm</button>
					<%
				Object obj = session.getAttribute("khachHang");
				KhachHang khachHang = null;
				if (obj != null)
					khachHang = (KhachHang) obj;
				if (khachHang == null) {
				%>
					<a class="btn btn-primary ms-1 me-1" style="white-space: nowrap;"
						href="<%=url%>/user/dangnhap.jsp">Đăng nhập</a> <a
						class="btn btn-primary" style="white-space: nowrap;"
						href="<%=url%>/user/dangky.jsp">Đăng ký</a>
					<%
				} else {
				%>
					<%
				GioHang gioHang = (GioHang) session.getAttribute("gioHang");
				/* String cartID = (String) session.getAttribute("cartID"); */
				%>
					<%
				if (gioHang == null) {
				%>
					<div class="ps-2 pe-2 pt-1" style="cursor: pointer">
						<a class="notification"
							href="<%=url%>/gio-hang-controller?hanhDong=chi-tiet-gio-hang">
							<i class="bi bi-cart" style="font-size: 1.6rem;"></i><span
							class="badge">0</span>
						</a>
					</div>
					<%
				} else {
				Map<SanPham, Integer> maps = gioHang.getLists();

				/* DonHang donHang = (DonHang) session.getAttribute("donHang"); */

				int dem = 0;
				/* if (donHang != null) { */
				%>
					<%-- <div class="ps-2 pe-2 pt-1" style="cursor: pointer">
					<a class="notification"
						href="<%=url%>/gio-hang-controller?hanhDong=chi-tiet-gio-hang">
						<i class="bi bi-cart" style="font-size: 1.6rem;"></i> <span
						class="badge">0</span>
					</a>
				</div> --%>
					<%
				/* } else { */
				for (Map.Entry<SanPham, Integer> entry : maps.entrySet()) {
					dem += entry.getValue();
				}
				%>



					<!-- Example single danger button -->
					<%-- <%
				if (gioHang == null) {
				%> --%>
					<%-- <div class="ps-2 pe-2 pt-1" style="cursor: pointer">
					<a
						href="<%=url%>/gio-hang-controller?hanhDong=chi-tiet-gio-hang&cartID="><i
						class="bi bi-cart" style="font-size: 1.5rem;"></i></a>
				</div> --%>
					<%-- <%
				} else {
				%> --%>

					<div class="ps-2 pe-2 pt-1" style="cursor: pointer">
						<a class="notification"
							href="<%=url%>/gio-hang-controller?hanhDong=chi-tiet-gio-hang">
							<i class="bi bi-cart" style="font-size: 1.6rem;"></i> <span
							class="badge"><%=dem%></span>
						</a>
					</div>
					<%
				}
				/* } */
				%>
					<%-- <%
				}
				%> --%>

					<div class="btn-group" style="margin-left: 0.2em">
						<%-- <button type="button" class="btn btn-info dropdown-toggle"
						data-bs-toggle="dropdown" aria-expanded="false">
						Xin chào
						<%=khachHang.getTenDangNhap()%></button> --%>
						<img src="<%=url%>/image/avatar/<%=khachHang.getDuongDanAnh()%>"
							alt="Ảnh đại diện" class="btn btn-info dropdown-toggle"
							data-bs-toggle="dropdown" aria-expanded="false"
							style="border-radius: 50%; width: 50px; height: 40px">
						<ul class="dropdown-menu dropdown-menu-end">
							<li><a class="dropdown-item" href="#">Đơn hàng của tôi</a></li>
							<li><a class="dropdown-item" href="#">Thông báo</a></li>
							<li><a class="dropdown-item"
								href="<%=url%>/user/users_profile.jsp">Trang cá nhân</a></li>
							<li><a class="dropdown-item"
								href="<%=url%>/product/lichsudathang.jsp">Lịch sử đặt hàng</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item"
								href="<%=url%>/khach-hang-controller?hanhDong=dang-xuat">Thoát
									tài khoản</a></li>
						</ul>
					</div>
					<%
				}
				%>
				</form>
			</div>
		</div>
	</nav>
</header>
<!-- end navbar -->