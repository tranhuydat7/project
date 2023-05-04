<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
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
</style>
</head>
<body>
	<%
	String baoLoi = request.getAttribute("baoLoi") + "";
	if (baoLoi == null || baoLoi.equals("null")) {
		baoLoi = "";
	}

	/* String tenDangNhap = request.getAttribute("tenDangNhap") + "";
	if (tenDangNhap == null || tenDangNhap.equals("null")) {
		tenDangNhap = "";
	}
	String hoVaTen = request.getAttribute("hoVaTen") + "";
	if (hoVaTen == null || hoVaTen.equals("null")) {
		hoVaTen = "";
	}
	String dienThoai = request.getAttribute("dienThoai") + "";
	if (dienThoai == null || dienThoai.equals("null")) {
		dienThoai = "";
	}
	String email = request.getAttribute("email") + "";
	if (email == null || email.equals("null")) {
		email = "";
	} */
	%>

	<section class="vh-100" style="background-color: #9A616D;">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col col-xl-10">
					<div class="card" style="border-radius: 1rem;">
						<div class="row g-0">
							<div class="col-md-6 col-lg-6 d-none d-md-block">
								<img
									src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/img1.webp"
									alt="login form" class="img-fluid"
									style="border-radius: 1rem 0 0 1rem;" />
							</div>
							<%
String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
							<div class="col-md-6 col-lg-6 align-items-center mt-3 mb-3">
								<div class="ms-3 me-3 text-black">
									<form action="<%=url1%>/khach-hang-controller" method="post">
										<input type="hidden" name="hanhDong" value="dang-ky" />
										<div class="d-flex align-items-center mb-3 pb-1">
											<span class="h3 fw-bold mb-0">Đăng ký</span>
										</div>

										<div class="form-floating mb-2">
											<input type="text" id="hoVaTen"
												class="form-control form-control-lg" placeholder="Họ và tên"
												name="hoVaTen" required="required" /> <label
												class="form-label" for="hoVaTen">Họ và tên <span
												class="red">*</span></label>
										</div>
										<div class="form-floating mb-2">
											<input type="text" id="tenDangNhap"
												class="form-control form-control-lg"
												placeholder="Tên đăng nhập" name="tenDangNhap"
												required="required" />
											<div id="msg_tenDangNhap" class="red"><%=baoLoi%></div>
											<label class="form-label" for="tenDangNhap">Tên đăng
												nhập <span class="red">*</span>
											</label>
										</div>

										<div class="form-floating mb-2">
											<input type="text" id="email"
												class="form-control form-control" placeholder="Email"
												name="email" required="required" /> <label
												class="form-label" for="email">Email <span
												class="red">*</span></label>
										</div>
										<div class="form-floating mb-2">
											<input type="text" id="soDienThoai"
												class="form-control form-control"
												placeholder="Số điện thoại" name="soDienThoai" /> <label
												class="form-label" for="soDienThoai">Số điện thoại</label>
										</div>

										<div class="form-floating mb-2">
											<input type="password" id="matKhau"
												class="form-control form-control-lg" placeholder="Mật khẩu"
												name="matKhau" required="required"
												onkeyup="kiemTraMatKhau()" /> <label class="form-label"
												for="matKhau">Mật khẩu <span class="red">*</span></label>
										</div>

										<div class="form-floating mb-2">
											<input type="password" id="matKhauNhapLai"
												class="form-control form-control-lg"
												placeholder="Nhập lại mật khẩu" name="matKhauNhapLai"
												required="required" onkeyup="kiemTraMatKhau()" /><span
												id="msg" class="red"></span> <label class="form-label"
												for="matKhauNhapLai">Nhập lại mật khẩu <span
												class="red">*</span>
											</label>
										</div>

										<div class="form-check d-flex mb-2">
											<input class="form-check-input me-2" type="checkbox" value=""
												id="form2Example3cg" /> <label class="form-check-label"
												for="form2Example3g"> Đồng ý với <a href="#!"
												class="text-body"><u>Điều khoản dịch vụ</u></a>
											</label>
										</div>

										<div class="mb-1">
											<button class="w-100 btn btn-lg btn-primary" type="submit">Đăng
												ký</button>
										</div>

										<p class="mb-1 pb-lg-2 mt-2" style="color: #393f81;">
											Bạn đã có tài khoản? Hãy <a href="dangnhap.jsp"
												style="color: red;">đăng nhập</a> tại đây!
										</p>
									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<script type="text/javascript">
		function kiemTraMatKhau() {
			matKhau = document.getElementById("matKhau").value;
			matKhauNhapLai = document.getElementById("matKhauNhapLai").value;
			if (matKhau != matKhauNhapLai) {
				document.getElementById("msg").innerText = "mật khẩu không khớp";
				return false;
			} else {
				document.getElementById("msg").innerText = "";
				return true;
			}
		}
	</script>


</body>
</html>