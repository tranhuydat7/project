<%@page import="database.SanPhamDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.SanPham"%>
<%@page import="java.util.List"%>
<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
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

/* .card{
	outline: 0;
	padding: 12px;
	text-align: center;
	cursor: pointer;
	width: 100%;
	font-size: 15px;
} */
</style>

</head>
<body>

	<!-- header -->
	<%@include file="header.jsp"%>
	<!-- end header -->

	<!-- body -->
	<main>
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
			<h4 class="mt-3">Sản phẩm hot</h4>

			<div class="row row-cols-1 row-cols-md-5 g-4">
				<%
			int i = 1;
			List<SanPham> listSanPham = (ArrayList) request.getAttribute("sanPhamList");
			%>
				<%
			for (SanPham sanPham : listSanPham) {
			%>
				<div class="col">
					<div class="card">
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

					<%-- <c:forEach items="${sanPhamList}" var="o">
					<div class="card">
						<img src="<%=url%>/image/product/1.png" class="card-img-top"
							alt="...">
						<div class="card-body">
							<h5 class="card-title">${o.tenSanPham }</h5>
							<p class="card-text">${o.giaBan }</p>
							<a href="#" class="btn btn-primary">Mua</a>
						</div>
					</div>
				</c:forEach> --%>
				</div>
				<%
			}
			%>
			</div>
			<!-- end product -->

			<h4 class="mt-3">Thời trang công sở</h4>
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

			<h4 class="mt-3">Hàng mới về</h4>
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

			<h4 class="mt-3">Hàng bán chạy trong tuần</h4>
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
	</main>
	<!-- and body -->



	<!-- footer -->
	<%@include file="footer.jsp"%>
	<!-- end footer -->


</body>
</html>