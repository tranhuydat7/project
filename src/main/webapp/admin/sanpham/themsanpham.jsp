<%@page import="model.KichCo"%>
<%@page import="model.Mau"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.DanhMuc"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sản phẩm</title>
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
					href="<%=url%>/danh-muc-admin-controller?hanhDong=list&index=1">Danh
						mục</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=url%>/admin/themsanpham.jsp">Sản phẩm</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=url%>/mau-sac-admin-controller?hanhDong=list&index=1">Màu
						sắc</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=url%>/kich-co-admin-controller?hanhDong=list&index=1">Kích
						cỡ</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=url%>/admin/themphuongthuc.jsp">Phương thức vận chuyển</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=url%>/khuyen-mai-admin-controller?hanhDong=list&index=1">Khuyến
						mãi</a></li>
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
						href="<%=url%>/san-pham-admin-controller?hanhDong=list&index=1">Sản
							phẩm</a></li>
					<li class="breadcrumb-item active" aria-current="page">Thêm
						sản phẩm</li>
				</ol>
			</nav>

			<form action="<%=url%>/san-pham-admin-controller" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="hanhDong" value="them-san-pham" />
				<div class="row">
					<div class="col-4">
						<label for="maSanPham" class="form-label">Mã sản phẩm <span
							class="red">*</span></label> <input type="text" class="form-control"
							id="maSanPham" name="maSanPham" placeholder="Mã sản phẩm"
							required>
						<div id="msg_maDanhMuc" class="red"><%=baoLoi%></div>
					</div>
					<div class="mb-3 col-4">
						<label for="tenSanPham" class="form-label">Tên sản phẩm<span
							class="red">*</span></label> <input type="text" class="form-control"
							id="tenSanPham" name="tenSanPham" placeholder="Tên sản phẩm"
							required>
					</div>
				</div>
				<div class="form-check col-1 mb-2">
					<label class="form-check-label" for="flexRadioDefault1">Độ
						hot</label> <input class="form-check-input" type="checkbox" name="isHot"
						id="isHot">
				</div>
				<div class="row">
					<div class="mb-3 col-4">
						<label for="giaBan" class="form-label">Giá bán <span
							class="red">*</span></label> <input type="number" min="1"
							class="form-control" id="giaBan" name="giaBan"
							placeholder="Gía bán" required>
					</div>
					<div class="mb-3 col-4">
						<label for="giaGoc" class="form-label">Giá gốc <span
							class="red">*</span>
						</label> <input type="number" min="1" class="form-control" id="giaGoc"
							name="giaGoc" placeholder="Giá gốc" required>
					</div>
					<div class="mb-3 col-4">
						<label for="soLuong" class="form-label">Số lượng <span
							class="red">*</span>
						</label> <input type="number" min="1" class="form-control" id="soLuong"
							name="soLuong" placeholder="Số lượng" required>
					</div>
				</div>

				<div class="mb-3">
					<label for="moTa" class="form-label">Mô tả<span class="red">*</span></label>
					<input type="text" class="form-control" id="moTa" name="moTa"
						placeholder="Mô tả" required>
				</div>

				<div class="row">
					<div class="mb-3 col-4">
						<label for="maDanhMuc" class="form-label">Mã danh mục: <span
							class="red">*</span></label>
						<%
						List<DanhMuc> danhMucs = (ArrayList) request.getAttribute("danhMucs");
						%>
						<select name="maDanhMuc" id="maDanhMuc">
							<%
							for (DanhMuc danhMuc : danhMucs) {
							%>
							<option value="<%=danhMuc.getMaDanhMuc()%>"><%=danhMuc.getTenDanhMuc()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="mb-3 col-4">
						<%
						List<Mau> maus = (ArrayList) request.getAttribute("maus");
						%>
						<label for="maMau" class="form-label">Mã màu: <span
							class="red">*</span></label> <select name="maMau" id="maMau">
							<%
							for (Mau mau : maus) {
							%>
							<option value="<%=mau.getMaMau()%>"><%=mau.getTenMau()%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="mb-3 col-4">
						<%
						List<KichCo> kichCos = (ArrayList) request.getAttribute("kichCos");
						%>
						<label for="maKichCo" class="form-label">Mã kích cỡ: <span
							class="red">*</span></label> <select name="maKichCo" id="maKichCo">
							<%
							for (KichCo kichCo : kichCos) {
							%>
							<option value="<%=kichCo.getMaKichCo()%>"><%=kichCo.getTenKichCo()%></option>
							<%
							}
							%>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="mb-3 col-4">
						<label for="avatar" class="form-label">Avatar</label> <input
							type="file" class="form-control" id="avatar" name="avatar"
							required>
					</div>
					<div class="mb-3 col-4">
						<label for="cachSuDung" class="form-label">Cách sử dụng<span
							class="red">*</span></label> <input type="text" class="form-control"
							id="cachSuDung" name="cachSuDung" placeholder="Cách sử dụng"
							required>
					</div>
					<div class="mb-3 col-4">
						<label for="chatLieu" class="form-label">Chất liệu<span
							class="red">*</span></label> <input type="text" class="form-control"
							id="chatLieu" name="chatLieu" placeholder="Nhập chất liệu"
							required>
					</div>
				</div>

				<div id="msg_success" class="blue"><%=success%></div>
				<div id="msg_failed" class="red"><%=failed%></div>
				<button type="submit" class="btn btn-primary mt-2">Thêm</button>
				<a class="btn btn-secondary mt-2"
					href="<%=url%>/san-pham-admin-controller?hanhDong=list&index=1">Huỷ</a>
			</form>
		</div>
	</div>

</body>
</html>