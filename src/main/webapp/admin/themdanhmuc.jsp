<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm danh mục</title>
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
<style type="text/css">
.red {
	color: red;
}

.blue {
	color: #7FFF00;
}
</style>
</head>
<body>
	<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
	<%
	String baoLoi = request.getAttribute("baoLoi") + "";
	if (baoLoi == null || baoLoi.equals("null")) {
		baoLoi = "";
	}

	String success = request.getAttribute("success") + "";
	if (success == null || success.equals("null")) {
		success = "";
	}
	
	String failed = request.getAttribute("failed") + "";
	if (failed == null || failed.equals("null")) {
		failed = "";
	}
	%>
	<div class="row">
		<div class="col-3">
			<ul class="nav flex-column">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="#">Người dùng</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=url%>/danh-muc-admin-controller?hanhDong=list&index=1">Danh mục</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=url%>/admin/themsanpham.jsp">Sản phẩm</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=url%>/admin/themmausac.jsp">Màu sắc</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=url%>/admin/themkichco.jsp">Kích cỡ</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=url%>/admin/themphuongthuc.jsp">Phương thức vận chuyển</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=url%>/admin/themkhuyenmai.jsp">Khuyến mãi</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Đơn hàng</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Chi tiết
						đơn hàng</a></li>
			</ul>
		</div>

		<div class="col-9 container">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a
						href="<%=url%>/admin/indexadmin.jsp">Trang chủ</a></li>
					<li class="breadcrumb-item active"><a
						href="<%=url%>/danh-muc-admin-controller?hanhDong=list&index=1">Danh mục</a></li>
					<li class="breadcrumb-item active" aria-current="page">Thêm
						danh mục</li>
				</ol>
			</nav>

			<form action="<%=url%>/danh-muc-admin-controller" method="get">
				<input type="hidden" name="hanhDong" value="them-danh-muc" />
				<div>
					<label for="maDanhMuc" class="form-label">Mã danh mục <span
						class="red">*</span></label> <input type="text" class="form-control"
						id="maDanhMuc" name="maDanhMuc" placeholder="Nhập mã danh mục"
						required>
					<div id="msg_maDanhMuc" class="red"><%=baoLoi%></div>
				</div>
				<div class="mb-3">
					<label for="tenDanhMuc" class="form-label">Tên danh mục <span
						class="red">*</span></label> <input type="text" class="form-control"
						id="tenDanhMuc" name="tenDanhMuc" placeholder="Nhập tên danh mục"
						required>
				</div>
				<div class="mb-3">
					<label for="danhMucCha" class="form-label">Danh mục cha <span
						class="red">*</span></label> <input type="text" class="form-control"
						id="danhMucCha" name="danhMucCha" placeholder="Nhập danh mục cha"
						required>
				</div>
				<div id="msg_success" class="blue"><%=success%></div>
				<div id="msg_failed" class="red"><%=failed%></div>
				<button type="submit" class="btn btn-primary mt-2">Thêm</button>
				<a class="btn btn-secondary mt-2" href="<%=url%>/danh-muc-admin-controller?hanhDong=list&index=1">Huỷ</a>
			</form>
		</div>
	</div>



</body>
</html>