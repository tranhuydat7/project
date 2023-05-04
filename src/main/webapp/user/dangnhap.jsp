<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
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
.red{
color: red;
}
</style>
</head>
<body>
	<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
	<section class="vh-100" style="background-color: #9A616D;">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col col-xl-10">
					<div class="card" style="border-radius: 1rem;">
						<div class="row g-0">
							<div class="col-md-6 col-lg-5 d-none d-md-block">
								<img
									src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/img1.webp"
									alt="login form" class="img-fluid"
									style="border-radius: 1rem 0 0 1rem;" />
							</div>
							<div class="col-md-6 col-lg-7 d-flex align-items-center">
								<div class="card-body p-4 p-lg-5 text-black">

									<form action="<%=url%>/khach-hang-controller" method="post">
										<input type="hidden" name="hanhDong" value="dang-nhap" />
										<div class="d-flex align-items-center mb-3 pb-1">
											<span class="h3 fw-bold mb-0">Đăng nhập</span>
										</div>
										<%
										String baoLoi = request.getAttribute("baoLoi") + "";
										if (baoLoi.equals("null") || baoLoi == "") {
											baoLoi = "";
										}
										%>
										<div class="form-floating mb-3">
											<input type="text" id="tenDangNhap"
												class="form-control form-control-lg"
												placeholder="Tên đăng nhập" name="tenDangNhap" /> <label
												class="form-label" for="tenDangNhap">Tên đăng nhập</label>
										</div>

										<div class="form-floating mb-3">
											<input type="password" id="matKhau"
												class="form-control form-control-lg" placeholder="Mật khẩu"
												name="matKhau" /> <label class="form-label" for="matKhau">Mật
												khẩu</label>
										</div>
										<div class="mb-3">
											<span class="red"><%=baoLoi%></span>
										</div>

										<div class="mb-1">
											<button class="w-100 btn btn-lg btn-primary" type="submit">Đăng
												nhập</button>
										</div>

										<a class="small text-muted" href="#!">Quên mật khẩu</a>
										<!-- Register buttons -->
										<div class="text-center ">
											<p class="mt-2">or sign up with:</p>
											<button type="button" class="btn btn-link btn-floating mx-1">
												<i class="bi bi-facebook"></i>
											</button>

											<button type="button" class="btn btn-link btn-floating mx-1">
												<i class="bi bi-google"></i>
											</button>

											<button type="button" class="btn btn-link btn-floating mx-1">
												<i class="bi bi-apple"></i>
											</button>

										</div>

										<p class="mb-1 pb-lg-2 mt-2" style="color: #393f81;">
											Bạn chưa có tài khoản? Hãy <a href="<%=url %>/user/dangky.jsp"
												style="color: red;">đăng ký</a> tại đây!
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
</body>
</html>