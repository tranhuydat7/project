<%@page import="model.DanhMuc"%>
<%@page import="model.KichCo"%>
<%@page import="model.Mau"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.SanPham"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh mục</title>
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
	color: (internal value);
	text-decoration: none;
}

.card-text {
	color: red;
}

.card:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
	border-radius: 10px;
}

/* Add some padding inside the card container */
.container {
	padding: 2px 16px;
}

.button:hover {
	opacity: 0.7;
}

.btnLoc {
	color: black;
}

.btnLoc:hover {
	/* background: #ff9800; */
	color: orange;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	float: none;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
	text-align: left;
}

.dropdown:hover .dropdown-content {
	display: block;
}
</style>
</head>
<body>

	<!-- header -->
	<%@include file="header.jsp"%>
	<!-- end header -->

	<!-- body -->
	<div class="container">
		<!--  slider-->
		<div id="carouselExampleAutoplaying" class="carousel slide mt-2"
			data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="<%=url%>/image/slider/1.png" class="d-block w-100"
						alt="...">
				</div>
				<div class="carousel-item">
					<img src="<%=url%>/image/slider/2.png" class="d-block w-100"
						alt="...">
				</div>
				<div class="carousel-item">
					<img src="<%=url%>/image/slider/3.png" class="d-block w-100"
						alt="...">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
		<!-- end slider  -->

		<!-- product -->
		<div class="container text-center mt-3 mb-3">
			<div class="row justify-content-center">
				<div class="col-1">
					<a class="btn btn-outline-warning" href="#" type="button">NỮ</a>
				</div>
				<div class="col-1">
					<a class="btn btn-outline-warning" href="#" type="button">NAM</a>
				</div>
				<div class="col-1">
					<a class="btn btn-outline-warning" href="#" type="button">TRẺ
						EM</a>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-2">
				<div class="mb-2" style="display: none">
					<div class="row">
						<div class="col-auto mb-1">Bạn chọn</div>
						<div class="col-auto ms-auto btnLoc mb-1" style="cursor: pointer">
							<i class="bi bi-x"></i>Bỏ hết
						</div>
					</div>
					<div id="banChon">
						<div class="" style="border: none;">
							<a class="btn btn-outline-warning mb-1" type="button" href="#">abc</a>
						</div>
					</div>
				</div>
				<div class="mb-2">
					<div class="row">
						<div class="col-auto">
							<button class="btn btnLoc" type="button" style="border: none"
								data-bs-toggle="collapse" data-bs-target="#loaiSanPham"
								aria-expanded="false" aria-controls="loaiSanPham">Loại
								sản phẩm</button>
						</div>
						<div class="col-auto ms-auto">
							<button class="btn btnLoc" type="button" style="border: none"
								data-bs-toggle="collapse" data-bs-target="#loaiSanPham"
								aria-expanded="false" aria-controls="loaiSanPham">
								<i class="bi bi-chevron-down"></i>
							</button>
						</div>
					</div>
					<div class="" id="loaiSanPham">
						<div class="" style="border: none;">
							<%
							List<DanhMuc> listDanhMucs = (ArrayList) request.getAttribute("listDanhMucs");
							%>
							<%
							for (DanhMuc danhMuc : listDanhMucs) {
							%>
							<a class="btn btn-outline-warning mb-1" type="button" href="#"><%=danhMuc.getTenDanhMuc()%></a>
							<%
							}
							%>
						</div>
					</div>
				</div>
				<div class="mb-2">
					<div class="row">
						<div class="col-auto">
							<button class="btn btnLoc" type="button" style="border: none"
								data-bs-toggle="collapse" data-bs-target="#kichThuoc"
								aria-expanded="false" aria-controls="kichThuoc">Kích
								thước</button>
						</div>
						<div class="col-auto ms-auto">
							<button class="btn btnLoc" type="button" style="border: none"
								data-bs-toggle="collapse" data-bs-target="#kichThuoc"
								aria-expanded="false" aria-controls="kichThuoc">
								<i class="bi bi-chevron-down"></i>
							</button>
						</div>
					</div>
					<div class="" id="kichThuoc">
						<div class="" style="border: none;">
							<%
							List<KichCo> listKichCos = (ArrayList) request.getAttribute("listKichCos");
							%>
							<%
							for (KichCo kichCo : listKichCos) {
							%>
							<a class="btn btn-outline-warning mb-1" type="button" href="#"><%=kichCo.getMaKichCo()%></a>
							<%
							}
							%>
						</div>
					</div>
				</div>
				<div class="mb-2">
					<div class="row">
						<div class="col-auto">
							<button class="btn btnLoc" type="button" style="border: none"
								data-bs-toggle="collapse" data-bs-target="#mau"
								aria-expanded="false" aria-controls="mau">Màu</button>
						</div>
						<div class="col-auto ms-auto">
							<button class="btn btnLoc" type="button" style="border: none"
								data-bs-toggle="collapse" data-bs-target="#mau"
								aria-expanded="false" aria-controls="mau">
								<i class="bi bi-chevron-down"></i>
							</button>
						</div>
					</div>
					<div class="" id="mau">
						<div class="">
							<%
							List<Mau> listMaus = (ArrayList) request.getAttribute("listMaus");
							%>
							<%
							for (Mau mau : listMaus) {
							%>
							<a class="btn btn-outline-warning mb-1" type="button" href="#"><%=mau.getTenMau()%></a>
							<%
							}
							%>
						</div>
					</div>
				</div>
				<div class="mb-2">
					<div class="row">
						<div class="col-auto">
							<button class="btn btnLoc" type="button" style="border: none"
								data-bs-toggle="collapse" data-bs-target="#khoangGia"
								aria-expanded="false" aria-controls="khoangGia">Khoảng
								giá</button>
						</div>
						<div class="col-auto ms-auto">
							<button class="btn btnLoc" type="button" style="border: none"
								data-bs-toggle="collapse" data-bs-target="#khoangGia"
								aria-expanded="false" aria-controls="khoangGia">
								<i class="bi bi-chevron-down"></i>
							</button>
						</div>
					</div>
					<div class="" id="khoangGia">
						<div class="">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value=""
									id="nhoHon100"> <label class="form-check-label btnLoc"
									for="nhoHon100" style="cursor: pointer"> Nhỏ hơn 100000
									VND </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value=""
									id="tu100den200"> <label
									class="form-check-label btnLoc" for="tu100den200"
									style="cursor: pointer"> Từ 100000 - 200000 VND </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value=""
									id="tu200den350"> <label
									class="form-check-label btnLoc" for="tu200den350"
									style="cursor: pointer"> Từ 200000 - 350000 VND </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value=""
									id="tu350den500"> <label
									class="form-check-label btnLoc" for="tu350den500"
									style="cursor: pointer"> Từ 350000 - 500000 VND </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value=""
									id="tu500den700"> <label
									class="form-check-label btnLoc" for="tu500den700"
									style="cursor: pointer"> Từ 500000 - 700000 VND </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value=""
									id="lonhon700"> <label class="form-check-label btnLoc"
									for="lonhon700" style="cursor: pointer"> Lớn hơn 700000
									VND </label>
							</div>
						</div>
					</div>
				</div>
				<div class="mb-2">
					<div class="row">
						<div class="col-auto">
							<button class="btn btnLoc" type="button" style="border: none"
								data-bs-toggle="collapse" data-bs-target="#chatLieu"
								aria-expanded="false" aria-controls="chatLieu">Chất
								liệu</button>
						</div>
						<div class="col-auto ms-auto">
							<button class="btn btnLoc" type="button" style="border: none"
								data-bs-toggle="collapse" data-bs-target="#chatLieu"
								aria-expanded="false" aria-controls="chatLieu">
								<i class="bi bi-chevron-down"></i>
							</button>
						</div>
					</div>
					<div class="collapse" id="chatLieu">
						<div class="">
							<a class="btn btn-outline-warning" type="button" href="#">Cotton</a>
						</div>
					</div>
				</div>
			</div>
			<%
			int i = 1;
			List<SanPham> listSanPham = (ArrayList) request.getAttribute("sanPhamList");
			String baoLoi = request.getAttribute("baoLoi") + "";
			%>
			<div class="col-10">
				<div class="row">
					<div class="row justify-content-between mb-2">
						<div class="col-4 "><%=listSanPham.size()%> sản phẩm
						</div>
						<div class="col-4 ">
							<div class="row">
								<div class="col-6" style="text-align: right">Sắp xếp theo</div>
								<div class="col-6">
									<div class="dropdown">
										<button class="btn btnLoc"
											style="width: auto; border-color: #ff9800">
											<span id="macDinh">Mặc định</span> <i
												class="bi bi-chevron-down"></i>
										</button>
										<div class="dropdown-content" id="form-div">
											<a href="#" class="btn btnLoc formLap"
												onclick="onclickChang()">Mặc định</a> <a href="#"
												class="btn btnLoc formLap" onclick="onclickChang()">Từ A
												- Z</a> <a class="btn btnLoc formLap" href="#"
												onclick="onclickChang()">Giá giảm dần</a> <a
												class="btn btnLoc formLap" href="#" onclick="onclickChang()">Mới
												nhất</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


					<%
					if (listSanPham.size() == 0) {
					%>
					<h3><%=baoLoi%></h3>
					<%
					} else {
					%>
					<%
					for (SanPham sanPham : listSanPham) {
					%>
					<!-- <div class="col"> -->
					<div class="col-md-3 mb-sm-0">
						<div class="card mb-4">
							<a
								href="<%=url%>/san-pham-controller?hanhDong=san-pham-detail&maSanPham=<%=sanPham.getMaSanPham()%>">
								<img src="<%=url%>/image/avatar/<%=sanPham.getAvatar()%>"
								class="card-img-top" alt="...">
							</a>
							<div class="card-body">
								<h6>
									<a
										href="<%=url%>/san-pham-controller?hanhDong=san-pham-detail&maSanPham=<%=sanPham.getMaSanPham()%>"
										class="card-title"><%=sanPham.getTenSanPham()%></a>
								</h6>
								<p id="card-text" class="card-text"><%=sanPham.getGiaBan()%>
									VND
								</p>
								<a
									href="<%=url%>/gio-hang-controller?hanhDong=them-gio-hang&maSanPham=<%=sanPham.getMaSanPham()%>"
									class="btn bg-success d-grid gap-2" type="submit">Thêm giỏ
									hàng</a>
								<!-- <div class="row justify-content-between">
							<div class="col-3">
								<a type="button" href="#" class="btn btn-primary">Mua</a>
							</div>
							<div class="col-9">
								<a type="button" href="#" class="btn btn-primary">Thêm giỏ hàng</a>
							</div>
						</div> -->
							</div>
						</div>
					</div>

					<!-- </div> -->
					<%
					}
					}
					%>

				</div>
			</div>
		</div>

		<!-- end product -->

		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-end">
				<li class="page-item"><a class="page-link" href="#"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<%-- <%
				int count = (int) request.getAttribute("end");
				for (int index = 1; index <= count; index++) {
				%> --%>
				<%-- <li class="page-item"><a class="page-link"
					href="<%=url%>/danh-muc-admin-controller?hanhDong=list&index=<%=index%>"><%=index%></a></li> --%>
				<!-- <li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li> -->
				<%-- <%
				}
				%> --%>
				<li class="page-item"><a class="page-link" href="#"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
	</div>
	<!-- and body -->



	<!-- footer -->
	<%@include file="footer.jsp"%>
	<!-- end footer -->

	<script>
		var form = document.querySelectorAll("a.formLap")
		var macDinh = document.getElementById("macDinh");
		console.log(form)
		form.forEach(function(currentValue, currentIndex, listObj) {
			currentValue.addEventListener("click", onclickChang)
			function onclickChang() {
				macDinh.innerHTML = currentValue.innerHTML
			}
		})
	</script>

</body>
</html>