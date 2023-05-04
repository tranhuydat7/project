<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
#main, #footer {
	margin-left: 300px;
}

#main {
	margin-top: 60px;
	padding: 20px 30px;
	transition: all 0.3s;
}

main {
	display: block;
}

/* *, ::after, ::before {
	box-sizing: border-box;
} */
.header .search-bar {
	min-width: 360px;
	padding: 0 20px;
}

h1, h2, h3, h4, h5, h6 {
	font-family: "Nunito", sans-serif;
}

.align-items-center {
	align-items: center !important;
}

.h1, .h2, .h3, .h4, .h5, .h6, h1, h2, h3, h4, h5, h6 {
	margin-top: 0;
	margin-bottom: 0.5rem;
	font-weight: 500;
	line-height: 1.2;
}

.red {
	color: red;
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


	<!-- main -->
	<main id="" class="container pt-3">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="pagetitle">
						<h3>Thông tin cá nhân</h3>
						<nav>
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="<%=url%>/index.jsp">Trang
										chủ</a></li>
								<!-- <li class="breadcrumb-item">Components</li> -->
								<li class="breadcrumb-item active">Thông tin cá nhân</li>
							</ol>
						</nav>
					</div>
				</div>
				<div class="col-xl-4 pb-3">
					<div class="card">
						<div class="card-body">
							<div
								class="card-body profile-card pt-4 d-flex flex-column align-items-center">

								<img src="assets/img/profile-img.jpg" alt="Ảnh đại diện"
									class="rounded-circle">
								<h2><%=tenDangNhapp%></h2>
								<!-- <h3>Web Designer</h3> -->
								<div class="social-links mt-2">
									<a href="#" class="twitter"><i class="bi bi-twitter"></i></a> <a
										href="#" class="facebook"><i class="bi bi-facebook"></i></a> <a
										href="#" class="instagram"><i class="bi bi-instagram"></i></a>
									<a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xl-8">
					<div class="card">
						<div class="card-body pt-3">
							<ul class="nav nav-tabs nav-tabs-bordered">
								<li class="nav-item">
									<button class="nav-link active" data-bs-toggle="tab"
										data-bs-target="#profile-overview">Thông tin cá nhân</button>
								</li>

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-edit">Chỉnh sửa thông tin</button>
								</li>

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-settings">Cài đặt</button>
								</li>

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-change-password">Đổi mật
										khẩu</button>
								</li>
							</ul>
							<div class="tab-content pt-2">

								<div class="tab-pane fade show active profile-overview"
									id="profile-overview">
									<!-- <h5 class="card-title">About</h5>
									<p class="small fst-italic">Sunt est soluta temporibus
										accusantium neque nam maiores cumque temporibus. Tempora
										libero non est unde veniam est qui dolor. Ut sunt iure rerum
										quae quisquam autem eveniet perspiciatis odit. Fuga sequi sed
										ea saepe at unde.</p> -->

									<h5 class="card-title">Thông tin chi tiết</h5>

									<div class="row">
										<div class="col-lg-3 col-md-4 label ">Họ và tên</div>
										<div class="col-lg-9 col-md-8"><%=hoVaTen%></div>
									</div>

									<!-- <div class="row">
										<div class="col-lg-3 col-md-4 label">Company</div>
										<div class="col-lg-9 col-md-8">Lueilwitz, Wisoky and
											Leuschke</div>
									</div> -->

									<!-- <div class="row">
										<div class="col-lg-3 col-md-4 label">Job</div>
										<div class="col-lg-9 col-md-8">Web Designer</div>
									</div> -->

									<!-- <div class="row">
										<div class="col-lg-3 col-md-4 label">Country</div>
										<div class="col-lg-9 col-md-8">USA</div>
									</div> -->

									<div class="row">
										<div class="col-lg-3 col-md-4 label">Địa chỉ</div>
										<div class="col-lg-9 col-md-8">A108 Adam Street, New
											York, NY 535022</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">Số điện thoại</div>
										<div class="col-lg-9 col-md-8"><%=dienThoai%></div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">Email</div>
										<div class="col-lg-9 col-md-8"><%=email%></div>
									</div>
								</div>
								<!-- Profile Edit Form -->
								<div class="tab-pane fade profile-edit pt-3" id="profile-edit">
									<form class="form" action="<%=url%>/khach-hang-controller"
										method="get">
										<input type="hidden" name="hanhDong" value="doi-thong-tin" />
										<div class="row mb-3">
											<label for="profileImage"
												class="col-md-4 col-lg-3 col-form-label">Ảnh đại
												diện</label>
											<div class="col-md-8 col-lg-9">
												<img src="assets/img/profile-img.jpg" alt="Ảnh đại diện">
												<div class="pt-2">
													<a href="#" class="btn btn-primary btn-sm"
														title="Upload new profile image"><i
														class="bi bi-upload"></i></a> <a href="#"
														class="btn btn-danger btn-sm"
														title="Remove my profile image"><i class="bi bi-trash"></i></a>
												</div>
											</div>
										</div>

										<div class="row mb-3">
											<label for="fullName"
												class="col-md-4 col-lg-3 col-form-label">Họ và tên</label>
											<div class="col-md-8 col-lg-9">
												<input name="hoVaTen" type="text" class="form-control"
													id="hoVaTen" value="<%=hoVaTen%>">
											</div>
										</div>

										<!-- <div class="row mb-3">
											<label for="about" class="col-md-4 col-lg-3 col-form-label">About</label>
											<div class="col-md-8 col-lg-9">
												<textarea name="about" class="form-control" id="about"
													style="height: 100px">Sunt est soluta temporibus accusantium neque nam maiores cumque temporibus. Tempora libero non est unde veniam est qui dolor. Ut sunt iure rerum quae quisquam autem eveniet perspiciatis odit. Fuga sequi sed ea saepe at unde.</textarea>
											</div>
										</div> -->

										<!-- <div class="row mb-3">
											<label for="company" class="col-md-4 col-lg-3 col-form-label">Company</label>
											<div class="col-md-8 col-lg-9">
												<input name="company" type="text" class="form-control"
													id="company" value="Lueilwitz, Wisoky and Leuschke">
											</div>
										</div> -->

										<!-- <div class="row mb-3">
											<label for="Job" class="col-md-4 col-lg-3 col-form-label">Job</label>
											<div class="col-md-8 col-lg-9">
												<input name="job" type="text" class="form-control" id="Job"
													value="Web Designer">
											</div>
										</div> -->

										<!-- <div class="row mb-3">
											<label for="Country" class="col-md-4 col-lg-3 col-form-label">Country</label>
											<div class="col-md-8 col-lg-9">
												<input name="country" type="text" class="form-control"
													id="Country" value="USA">
											</div>
										</div> -->

										<div class="row mb-3">
											<label for="Address" class="col-md-4 col-lg-3 col-form-label">Địa
												chỉ</label>
											<div class="col-md-8 col-lg-9">
												<input name="address" type="text" class="form-control"
													id="Address" value="A108 Adam Street, New York, NY 535022">
											</div>
										</div>

										<div class="row mb-3">
											<label for="Phone" class="col-md-4 col-lg-3 col-form-label">Số
												điện thoại</label>
											<div class="col-md-8 col-lg-9">
												<input name="dienThoai" type="text" class="form-control"
													id="dienThoai" value="<%=dienThoai%>">
											</div>
										</div>

										<div class="row mb-3">
											<label for="Email" class="col-md-4 col-lg-3 col-form-label">Email</label>
											<div class="col-md-8 col-lg-9">
												<input name="email" type="email" class="form-control"
													id="email" value="<%=email%>">
											</div>
										</div>

										<!-- <div class="row mb-3">
											<label for="Twitter" class="col-md-4 col-lg-3 col-form-label">Twitter
												Profile</label>
											<div class="col-md-8 col-lg-9">
												<input name="twitter" type="text" class="form-control"
													id="Twitter" value="https://twitter.com/#">
											</div>
										</div> -->

										<!-- <div class="row mb-3">
											<label for="Facebook"
												class="col-md-4 col-lg-3 col-form-label">Facebook
												Profile</label>
											<div class="col-md-8 col-lg-9">
												<input name="facebook" type="text" class="form-control"
													id="Facebook" value="https://facebook.com/#">
											</div>
										</div> -->

										<!-- <div class="row mb-3">
											<label for="Instagram"
												class="col-md-4 col-lg-3 col-form-label">Instagram
												Profile</label>
											<div class="col-md-8 col-lg-9">
												<input name="instagram" type="text" class="form-control"
													id="Instagram" value="https://instagram.com/#">
											</div>
										</div> -->

										<!-- <div class="row mb-3">
											<label for="Linkedin"
												class="col-md-4 col-lg-3 col-form-label">Linkedin
												Profile</label>
											<div class="col-md-8 col-lg-9">
												<input name="linkedin" type="text" class="form-control"
													id="Linkedin" value="https://linkedin.com/#">
											</div>
										</div> -->

										<div class="text-center">
											<button type="submit" class="btn btn-primary">Lưu
												thay đổi</button>
										</div>
									</form>
								</div>
								<!-- End Profile Edit Form -->

								<div class="tab-pane fade pt-3" id="profile-settings">

									<!-- Settings Form -->
									<form>
										<div class="row mb-3">
											<label for="fullName"
												class="col-md-4 col-lg-3 col-form-label">Email
												Notifications</label>
											<div class="col-md-8 col-lg-9">
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														id="changesMade" checked> <label
														class="form-check-label" for="changesMade">
														Changes made to your account </label>
												</div>
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														id="newProducts" checked> <label
														class="form-check-label" for="newProducts">
														Information on new products and services </label>
												</div>
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														id="proOffers"> <label class="form-check-label"
														for="proOffers"> Marketing and promo offers </label>
												</div>
												<div class="form-check">
													<input class="form-check-input" type="checkbox"
														id="securityNotify" checked disabled> <label
														class="form-check-label" for="securityNotify">
														Security alerts </label>
												</div>
											</div>
										</div>

										<div class="text-center">
											<button type="button" class="btn btn-primary">Save
												Changes</button>
										</div>
									</form>
									<!-- End settings Form -->

								</div>

								<div class="tab-pane fade pt-3" id="profile-change-password">
									<!-- Change Password Form -->
									<form class="form" action="<%=url%>/khach-hang-controller"
										method="post">
										<input type="hidden" name="hanhDong" value="doi-mat-khau" />
										<div class="row mb-3">
											<label for="currentPassword"
												class="col-md-4 col-lg-3 col-form-label">Mật khẩu
												hiện tại</label>
											<div class="col-md-8 col-lg-9">
												<input name="matKhauHienTai" type="password"
													class="form-control" id="matKhauHienTai">
											</div>
										</div>

										<div class="row mb-3">
											<label for="newPassword"
												class="col-md-4 col-lg-3 col-form-label">Mật khẩu
												mới</label>
											<div class="col-md-8 col-lg-9">
												<input name="matKhauMoi" type="password"
													class="form-control" id="matKhauMoi">
											</div>
										</div>

										<div class="row mb-3">
											<label for="renewPassword"
												class="col-md-4 col-lg-3 col-form-label">Nhập lại
												mật khẩu mới</label>
											<div class="col-md-8 col-lg-9">
												<input name="matKhauMoiNhapLai" type="password"
													class="form-control" id="matKhauMoiNhapLai">
											</div>
										</div>
										<div class="mb-3">
											<span class="red"><%=baoLoi%></span>
										</div>

										<div class="text-center">
											<button type="submit" class="btn btn-primary">Lưu
												thay đổi</button>
										</div>
									</form>
									<!-- End Change Password Form -->

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<%
	}
	%>

	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
	<!-- end footer -->



</body>
</html>