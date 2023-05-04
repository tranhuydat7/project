<%@page import="java.util.ArrayList"%>
<%@page import="model.KhuyenMai"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách khuyến mãi</title>
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
	<div class="row">
		<div class="col-3">
			<ul class="nav flex-column">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="<%=url%>/user-admin-controller?hanhDong=list&index=1">Người dùng</a></li>
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
					href="<%=url%>/khuyen-mai-admin-controller?hanhDong=list&index=1">Khuyến mãi</a></li>
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
					<li class="breadcrumb-item active" aria-current="page">Khuyến mại</li>
				</ol>
			</nav>

			<div>
				<div class="row">
					<div class="col-auto me-auto">
						<form class="d-flex" action="<%=url%>/mau-sac-admin-controller"
							method="get" role="search">
							<input type="hidden" name="hanhDong" value="search" /> <input
								type="hidden" name="index" value="1" /> <input
								class="form-control me-2" type="search" placeholder="Search"
								aria-label="Search" name="search" id="search">
							<button class="btn btn-outline-success" type="submit">Search</button>
						</form>
					</div>

					<div class="col-auto">
						<a class="btn btn-primary"
							href="<%=url%>/admin/khuyenmai/themkhuyenmai.jsp">Thêm khuyến mại</a>
					</div>

				</div>
				<table class="table">
					<%-- <%
					String success = request.getAttribute("success") + "";
					if (success == null || success.equals("null")) {
						success = "";
					}
					%> --%>
					<thead>
						<tr>
							<th scope="col">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> STT </label>
								</div>
							</th>
							<th scope="col">Mã khuyến mại</th>
							<th scope="col">Tên khuyến mại</th>
							<th scope="col">Giảm giá từ</th>
							<th scope="col">Giảm giá tối đa</th>
							<th scope="col">Giảm giá phần trăm</th>
							<th scope="col">Ngày bắt đầu</th>
							<th scope="col">Ngày kết thúc</th>
							<th scope="col">Sự kiện</th>
						</tr>
					</thead>
					<tbody class="table-group-divider">
						<%
						int i = 1;
						List<KhuyenMai> listsKhuyenMai = (ArrayList<KhuyenMai>) request.getAttribute("khuyenMaisList");
						%>
						<%
						for (KhuyenMai khuyenMai : listsKhuyenMai) {
						%>
						<tr>
							<th scope="row">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"><%=i++%></label>
								</div>
							</th>
							<td><%=khuyenMai.getMaKhuyenMai()%></td>
							<td><%=khuyenMai.getTenKhuyenMai()%></td>
							<td><%=khuyenMai.getGiamGiaTu()%></td>
							<td><%=khuyenMai.getGiamGiaMax()%></td>
							<td><%=khuyenMai.getGiamPhanTram()%></td>
							<td><%=khuyenMai.getNgayBatDau()%></td>
							<td><%=khuyenMai.getNgayKetThuc()%></td>
							<td><a
								href="<%=url%>/khuyen-mai-admin-controller?hanhDong=sua-khuyen-mai&maKhuyenMai=<%=khuyenMai.getMaKhuyenMai()%>">
									<i class="bi bi-pencil"></i>
							</a> <a
								href="<%=url%>/khuyen-mai-admin-controller?hanhDong=xoa-khuyen-mai&maKhuyenMai=<%=khuyenMai.getMaKhuyenMai()%>"><i
									class="bi bi-trash"></i> </a></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
				<%-- <div id="msg_success" class="blue"><%=success%></div> --%>
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-end">
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
						<%
						int count = (int) request.getAttribute("end");
						for (int index = 1; index <= count; index++) {
						%>
						<li class="page-item"><a class="page-link"
							href="<%=url%>/khuyen-mai-admin-controller?hanhDong=list&index=<%=index%>"><%=index%></a></li>
						<!-- <li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li> -->
						<%
						}
						%>
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
				</nav>
			</div>
		</div>

	</div>
</body>
</html>