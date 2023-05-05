package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ChiTietDonHang;
import model.DanhMuc;
import model.DonHang;
import model.KhachHang;
import model.SanPham;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.mysql.cj.Session;

import database.ChiTietDonHangDAO;
import database.DanhMucDAO;
import database.DonHangDAO;
import database.KhachHangDAO;
import database.SanPhamDAO;

/**
 * Servlet implementation class SanPhamController
 */
public class SanPhamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SanPhamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String hanhDong = request.getParameter("hanhDong");
		if (hanhDong.equals("search-san-pham")) {
			searchSanPham(request, response);
		} else if (hanhDong.equals("san-pham-detail")) {
			detailSanPham(request, response);
		} else if (hanhDong.equals("xac-nhan-dat-hang")) {
			xacNhanDatHang(request, response);
		} else if (hanhDong.equals("dat-hang-thanh-cong")) {
			datHangThanhCong(request, response);
		} else if (hanhDong.equals("danh-muc")) {
			danhMuc(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void detailSanPham(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maSanPham = request.getParameter("maSanPham");
			System.out.println("masanpham: " + maSanPham);

			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			SanPham sanPham = new SanPham();
			sanPham = sanPhamDAO.getSanPhamByMaSanPham(maSanPham);

			request.setAttribute("sanPham", sanPham);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/productdetail.jsp");
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void xacNhanDatHang(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maSanPham = request.getParameter("maSanPham");
			String soLuong = request.getParameter("soLuong");

			System.out.println("maSanPham: " + maSanPham);
			System.out.println("soLuong: " + soLuong);

			int soLuongConvert = Integer.parseInt(soLuong);

			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			SanPham sanPham = new SanPham();
			sanPham = sanPhamDAO.getSanPhamByMaSanPham(maSanPham);
			int giaBan = sanPham.getGiaBan();

			int tongTien = giaBan * soLuongConvert;

			request.setAttribute("sanPham", sanPham);
			request.setAttribute("soLuong", soLuong);
			request.setAttribute("tongTien", tongTien);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/xacnhandathang.jsp");
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void datHangThanhCong(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maSanPham = request.getParameter("maSanPham");
			String soLuong = request.getParameter("soLuong");
			String tongTien = request.getParameter("tongTien");
			String phuongThucThanhToan = request.getParameter("phuongthucthanhtoan");
			System.out.println("maSanPham: " + maSanPham);
			System.out.println("soLuong: " + soLuong);
			System.out.println("tongTien: " + tongTien);
			System.out.println("phuong thuc thanh toan: " + phuongThucThanhToan);

			HttpSession session = request.getSession(true);
			KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
			if (khachHang == null) {
				System.out.println("null");
			} else if (khachHang != null) {
				System.out.println("not null");
			}
			System.out.println("khSession: " + khachHang.getMaKhachHang());
			System.out.println("tendangnhap: " + khachHang.getTenDangNhap());
			int soLuongConvert = Integer.parseInt(soLuong);
			int tongTienConvert = Integer.parseInt(tongTien);

			String url = "";
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			DonHangDAO donHangDAO = new DonHangDAO();
			ChiTietDonHangDAO chiTietDonHangDAO = new ChiTietDonHangDAO();
			KhachHang khachHang1 = khachHangDAO.selectKhachHangByTenDangNhap(khachHang.getTenDangNhap());
			System.out.println("kh1: " + khachHang1.getMaKhachHang());

			SanPham sanPham = new SanPham();
			sanPham.setMaSanPham(maSanPham);

			Random random = new Random();
			DonHang donHang = new DonHang();
			int randomInt = random.nextInt();
			if (randomInt < 0) {
				randomInt = randomInt * (-1);
			}
			donHang.setMaDonHang(randomInt);
			donHang.setNgayDatHang(Date.valueOf(LocalDate.now()));
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, 3);
			Date date = new Date(calendar.getTimeInMillis());
			donHang.setNgayGiaoHang(date);
			donHang.setHinhThucThanhToan(phuongThucThanhToan);
			System.out.println("ngaygiaohang: " + date);
			System.out.println("date local: " + Date.valueOf(LocalDate.now()));
			donHang.setKhachHang(khachHang1);
			int insertDonHang = donHangDAO.insert(donHang);
			System.out.println("madonhang: " + donHang.getMaDonHang());

			ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
			chiTietDonHang.setDonHang(donHang);
			chiTietDonHang.setSanPham(sanPham);
			chiTietDonHang.setSoLuong(soLuongConvert);
			chiTietDonHang.setTongTien(tongTienConvert);
			int insertChiTietDonHang = chiTietDonHangDAO.insert(chiTietDonHang);
			System.out.println("madonhang: " + chiTietDonHang.getDonHang().getMaDonHang());

			if (insertDonHang != 0 & insertChiTietDonHang != 0) {
				url += "/product/dathangthanhcong.jsp";
			} else {
				url += "/product/sucesscart.jsp";
			}

			request.setAttribute("sanPham", sanPham);
			request.setAttribute("soLuong", soLuong);
			request.setAttribute("tongTien", tongTien);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void danhMuc(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String tenDanhMuc = request.getParameter("tenDanhMuc");
			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			List<SanPham> listSanPham = sanPhamDAO.getSanPhamByDanhMuc(tenDanhMuc);

			request.setAttribute("sanPhamList", listSanPham);
			request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void searchSanPham(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String keySearch = request.getParameter("keySearch");
			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			List<SanPham> listSanPham = sanPhamDAO.getSanPhamByKey(keySearch);

			request.setAttribute("sanPhamList", listSanPham);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
