<%@ page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>

<!-- nav bar -->

<nav class="navbar navbar-expand-lg bg-body-tertiary">
	<div class="container-fluid">
		<a class="navbar-brand" href="#"> <img
			src="/docs/5.3/assets/brand/bootstrap-logo.svg" alt="Bootstrap"
			width="30" height="24">
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="<%=url %>/index-controller">Trang chủ</a></li>
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
				<!-- Example single danger button -->
				<div class="ps-2 pe-2 pt-1" style="cursor: pointer">
					<a href="<%=url%>/product/giohang.jsp"><i class="bi bi-cart" style="font-size: 1.5rem;"></i></a>
					
				</div>

				<div class="btn-group" style="margin-left: 0.2em">
					<button type="button" class="btn btn-info dropdown-toggle"
						data-bs-toggle="dropdown" aria-expanded="false">
						Xin chào
						<%=khachHang.getTenDangNhap()%></button>
					<ul class="dropdown-menu dropdown-menu-end">
						<li><a class="dropdown-item" href="#">Đơn hàng của tôi</a></li>
						<li><a class="dropdown-item" href="#">Thông báo</a></li>
						<li><a class="dropdown-item"
							href="<%=url%>/user/users_profile.jsp">Trang cá nhân</a></li>
							<li><a class="dropdown-item" href="<%=url%>/product/lichsudathang.jsp">Lịch sử đặt hàng</a></li>
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
<!-- end navbar -->