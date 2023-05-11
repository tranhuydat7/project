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
import model.GioHang;
import model.KhachHang;
import model.KichCo;
import model.Mau;
import model.SanPham;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import com.mysql.cj.Session;

import database.ChiTietDonHangDAO;
import database.DanhMucDAO;
import database.DonHangDAO;
import database.KhachHangDAO;
import database.KichCoDAO;
import database.MauDAO;
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
		} else if (hanhDong.equals("danh-muc-loai")) {
			danhMucTheoLoai(request, response);
		} else if (hanhDong.equals("sap-xep-theo-van")) {
			sapXepTheoVan(request, response);
		} else if (hanhDong.equals("sap-xep-gia-giam")) {
			sapXepTheoGiaGiam(request, response);
		} else if (hanhDong.equals("xac-nhan-dat-hang-tu-gio-hang")) {
			xacNhanDatHangTuGioHang(request, response);
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

	private void xacNhanDatHangTuGioHang(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			HttpSession session = request.getSession();
			GioHang gioHang = (GioHang) session.getAttribute("gioHang");
			
			
			session.setAttribute("gioHang", gioHang);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/xacnhandathang.jsp");
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

			// Thêm số lượng sản phẩm trực tiếp trong trang xác nhận đặt hàng
			String baoLoi = "";
			String countPlus = request.getParameter("countPlus");
			String countMinus = request.getParameter("countMinus");
			String maSP = request.getParameter("maSanPham");
			System.out.println("maSp: " + maSP);
			System.out.println("countMinus: " + (countMinus));
			System.out.println("countPlus: " + countPlus);
			String url = "";
			HttpSession session = request.getSession();
			KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
			if (khachHang == null) {
				baoLoi += "Bạn chưa đăng nhập hệ thống, vui lòng đăng nhập!";
				request.setAttribute("baoLoi", baoLoi);
				url += "/product/xacnhandathang.jsp";
			} else {
				if (countMinus == null && countPlus == null) {
					String maSanPham = request.getParameter("maSanPham");
					String soLuong = request.getParameter("soLuong");
					SanPhamDAO sanPhamDAO = new SanPhamDAO();
					SanPham sanPham = sanPhamDAO.getSanPhamByMaSanPham(maSanPham);

					if (session.getAttribute("gioHang") == null) {
						GioHang gioHang = new GioHang();
						System.out.println("cartID new : " + gioHang.getCartID());
						gioHang.insertToCart(sanPham, Integer.parseInt(soLuong));
						System.out.println("giohang: " + gioHang.toString());
						session.setAttribute("gioHang", gioHang);
						url += "/product/xacnhandathang.jsp";
					} else {
						GioHang gioHang = (GioHang) session.getAttribute("gioHang");
						System.out.println("giohang th2: " + gioHang.toString());
						TreeMap<SanPham, Integer> map = gioHang.getLists();
						gioHang.insertToCart(sanPham, Integer.parseInt(soLuong));

						long cartID = gioHang.getCartID();
						System.out.println("cartID: " + cartID);
						session.setAttribute("cartID", cartID);
						session.setAttribute("gioHang", gioHang);
						System.out.println("maSanPham: " + maSanPham);
						System.out.println("soLuong: " + soLuong);
						System.out.println("maSanPham: " + maSanPham);
						System.out.println("soLuong: " + soLuong);

						int soLuongConvert = Integer.parseInt(soLuong);

						int giaBan = sanPham.getGiaBan();

						int tongTien = giaBan * soLuongConvert;

						request.setAttribute("sanPham", sanPham);
						request.setAttribute("soLuong", soLuong);
						request.setAttribute("tongTien", tongTien);
						url += "/product/xacnhandathang.jsp";
					}
				} else if (countMinus != null && countPlus == null) {
					GioHang gioHang = (GioHang) session.getAttribute("gioHang");
					int count;
					TreeMap<SanPham, Integer> maps = gioHang.getLists();
					System.out.println("size: " + maps.size());

					Set<SanPham> set = maps.keySet();
					Iterator<SanPham> iterator = set.iterator();
					while (iterator.hasNext()) {
						SanPham sanPham = (SanPham) iterator.next();
						if (sanPham.getMaSanPham().equals(maSP)) {
							count = maps.get(sanPham) - 1;
							if (count == 0) {
								iterator.remove();
							} else {
								maps.put(sanPham, count);
							}
						}
					}
					gioHang.setLists(maps);
					System.out.println("size: " + maps.size());
					if (maps.size() == 0) {
						url += "/product/giohang.jsp";
//					gioHang = null;
					} else {
						url += "/product/xacnhandathang.jsp";
					}
					session.setAttribute("gioHang", gioHang);
				} else if (countMinus == null && countPlus != null) {
					GioHang gioHang = (GioHang) session.getAttribute("gioHang");
					int count;
					TreeMap<SanPham, Integer> maps = gioHang.getLists();
					for (Map.Entry<SanPham, Integer> entry : maps.entrySet()) {
						if (entry.getKey().getMaSanPham().equals(maSP)) {
							count = entry.getValue() + 1;
							System.out.println("count: " + count);
							entry.setValue(count);
							System.out.println("count: " + entry.getValue());
						}
					}
					gioHang.setLists(maps);
					session.setAttribute("gioHang", gioHang);
					url += "/product/xacnhandathang.jsp";
				}
			}
//			String maSanPham = request.getParameter("maSanPham");
//			String soLuong = request.getParameter("soLuong");
//			HttpSession session = request.getSession();
//			SanPhamDAO sanPhamDAO = new SanPhamDAO();
//			SanPham sanPham = sanPhamDAO.getSanPhamByMaSanPham(maSanPham);
//
//			if (session.getAttribute("gioHang") == null) {
//				GioHang gioHang = new GioHang();
//				System.out.println("cartID new : " + gioHang.getCartID());
//				gioHang.insertToCart(sanPham, Integer.parseInt(soLuong));
//				System.out.println("giohang: " + gioHang.toString());
//				session.setAttribute("gioHang", gioHang);
//			} else {
//				GioHang gioHang = (GioHang) session.getAttribute("gioHang");
//				System.out.println("giohang th2: " + gioHang.toString());
//				TreeMap<SanPham, Integer> map = gioHang.getLists();
//				gioHang.insertToCart(sanPham, Integer.parseInt(soLuong));
//
//				long cartID = gioHang.getCartID();
//				System.out.println("cartID: " + cartID);
//				session.setAttribute("cartID", cartID);
//				session.setAttribute("gioHang", gioHang);
//			}

//			System.out.println("maSanPham: " + maSanPham);
//			System.out.println("soLuong: " + soLuong);
//			int soLuongConvert = Integer.parseInt(soLuong);
//			int giaBan = sanPham.getGiaBan();
//			int tongTien = giaBan * soLuongConvert;
//			request.setAttribute("sanPham", sanPham);
//			request.setAttribute("soLuong", soLuong);
//			request.setAttribute("tongTien", tongTien);
//			url += "/product/xacnhandathang.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 1 san pham
//	private void datHangThanhCong(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			request.setCharacterEncoding("UTF-8");
//			response.setCharacterEncoding("UTF-8");
//			response.setContentType("text/html; charset=UTF-8");
//
//			String maSanPham = request.getParameter("maSanPham");
//			String soLuong = request.getParameter("soLuong");
//			String tongTien = request.getParameter("tongTien");
//			String phuongThucThanhToan = request.getParameter("phuongthucthanhtoan");
//			System.out.println("maSanPham: " + maSanPham);
//			System.out.println("soLuong: " + soLuong);
//			System.out.println("tongTien: " + tongTien);
//			System.out.println("phuong thuc thanh toan: " + phuongThucThanhToan);
//
//			HttpSession session = request.getSession(true);
//			KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
//			if (khachHang == null) {
//				System.out.println("null");
//			} else if (khachHang != null) {
//				System.out.println("not null");
//			}
//			System.out.println("khSession: " + khachHang.getMaKhachHang());
//			System.out.println("tendangnhap: " + khachHang.getTenDangNhap());
//			int soLuongConvert = Integer.parseInt(soLuong);
//			int tongTienConvert = Integer.parseInt(tongTien);
//
//			String url = "";
//			KhachHangDAO khachHangDAO = new KhachHangDAO();
//			SanPhamDAO sanPhamDAO = new SanPhamDAO();
//			DonHangDAO donHangDAO = new DonHangDAO();
//			ChiTietDonHangDAO chiTietDonHangDAO = new ChiTietDonHangDAO();
//			KhachHang khachHang1 = khachHangDAO.selectKhachHangByTenDangNhap(khachHang.getTenDangNhap());
//			System.out.println("kh1: " + khachHang1.getMaKhachHang());
//
//			SanPham sanPham = new SanPham();
//			sanPham.setMaSanPham(maSanPham);
//
//			Random random = new Random();
//			DonHang donHang = new DonHang();
//			int randomInt = random.nextInt();
//			if (randomInt < 0) {
//				randomInt = randomInt * (-1);
//			}
//			donHang.setMaDonHang(randomInt);
//			donHang.setNgayDatHang(Date.valueOf(LocalDate.now()));
//			Calendar calendar = Calendar.getInstance();
//			calendar.add(Calendar.DAY_OF_YEAR, 3);
//			Date date = new Date(calendar.getTimeInMillis());
//			donHang.setNgayGiaoHang(date);
//			donHang.setHinhThucThanhToan(phuongThucThanhToan);
//			System.out.println("ngaygiaohang: " + date);
//			System.out.println("date local: " + Date.valueOf(LocalDate.now()));
//			donHang.setKhachHang(khachHang1);
//			int insertDonHang = donHangDAO.insert(donHang);
//			System.out.println("madonhang: " + donHang.getMaDonHang());
//
//			ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
//			chiTietDonHang.setDonHang(donHang);
//			chiTietDonHang.setSanPham(sanPham);
//			chiTietDonHang.setSoLuong(soLuongConvert);
//			chiTietDonHang.setTongTien(tongTienConvert);
//			int insertChiTietDonHang = chiTietDonHangDAO.insert(chiTietDonHang);
//			System.out.println("madonhang: " + chiTietDonHang.getDonHang().getMaDonHang());
//
//			if (insertDonHang != 0 & insertChiTietDonHang != 0) {
//				url += "/product/dathangthanhcong.jsp";
//			} else {
//				url += "/product/sucesscart.jsp";
//			}
//
//			request.setAttribute("sanPham", sanPham);
//			request.setAttribute("soLuong", soLuong);
//			request.setAttribute("tongTien", tongTien);
//			session.setAttribute("chiTietDonHang", chiTietDonHang);
//
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
//			requestDispatcher.forward(request, response);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}

	// nhieu san pham
	private void datHangThanhCong(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

//			String maSanPham = request.getParameter("maSanPham");
			String soLuong = request.getParameter("soLuong");
			String tongTien = request.getParameter("tongTien");
			String phuongThucThanhToan = request.getParameter("phuongthucthanhtoan");
//			System.out.println("maSanPham: " + maSanPham);
			System.out.println("soLuong: " + soLuong);
			System.out.println("tongTien: " + tongTien);
			System.out.println("phuong thuc thanh toan: " + phuongThucThanhToan);

			HttpSession session = request.getSession(true);
			GioHang gioHang = (GioHang) session.getAttribute("gioHang");
			KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
			if (khachHang == null) {
				System.out.println("null");
			} else if (khachHang != null) {
				System.out.println("not null");
			}
			System.out.println("khSession: " + khachHang.getMaKhachHang());
			System.out.println("tendangnhap: " + khachHang.getTenDangNhap());
//			int soLuongConvert = Integer.parseInt(soLuong);
			int tongTienConvert = Integer.parseInt(tongTien);

			String url = "";
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			DonHangDAO donHangDAO = new DonHangDAO();
			ChiTietDonHangDAO chiTietDonHangDAO = new ChiTietDonHangDAO();
			KhachHang khachHang1 = khachHangDAO.selectKhachHangByTenDangNhap(khachHang.getTenDangNhap());
			System.out.println("kh1: " + khachHang1.getMaKhachHang());

			SanPham sanPham = new SanPham();
//			sanPham.setMaSanPham(maSanPham);

			// tạo đơn hàng: mã đơn hàng ngẫu nhiên
			Random random = new Random();
			DonHang donHang = new DonHang();
			int randomInt = random.nextInt();
			if (randomInt < 0) {
				randomInt = randomInt * (-1);
			}
			donHang.setMaDonHang(randomInt);
			// tạo ngày đặt hàng
			donHang.setNgayDatHang(Date.valueOf(LocalDate.now()));
			// tạo ngày giao hàng(sau khi đặt hàng 3 ngày)
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
			session.setAttribute("donHang", donHang);
			// end tạo đơn hàng

			TreeMap<SanPham, Integer> map = gioHang.getLists();
			for (Map.Entry<SanPham, Integer> entry : map.entrySet()) {
				ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
				chiTietDonHang.setDonHang(donHang);
				chiTietDonHang.setSanPham(entry.getKey());
				chiTietDonHang.setSoLuong(entry.getValue());
				chiTietDonHang.setTongTien(entry.getKey().getGiaBan() * entry.getValue());
				int insertChiTietDonHang = chiTietDonHangDAO.insert(chiTietDonHang);
				System.out.println("madonhang: " + chiTietDonHang.getDonHang().getMaDonHang());
				session.setAttribute("chiTietDonHang", chiTietDonHang);

			}

//			if (insertDonHang != 0 & insertChiTietDonHang != 0) {
//				url += "/product/dathangthanhcong.jsp";
//			} else {
//				url += "/product/sucesscart.jsp";
//			}

			request.setAttribute("sanPham", sanPham);
			request.setAttribute("soLuong", soLuong);
			request.setAttribute("tongTien", tongTien);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/dathangthanhcong.jsp");
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void danhMucTheoLoai(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String tenDanhMuc = request.getParameter("tenDanhMuc");
			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			List<SanPham> listSanPham = sanPhamDAO.getSanPhamByDanhMuc(tenDanhMuc);
			String baoLoi = "";

			MauDAO mauDAO = new MauDAO();
			List<Mau> listMaus = mauDAO.selectAll();

			KichCoDAO kichCoDAO = new KichCoDAO();
			List<KichCo> listKichCos = kichCoDAO.selectAll();

			DanhMucDAO danhMucDAO = new DanhMucDAO();
			List<DanhMuc> listDanhMucs = danhMucDAO.selectAllDanhMucByDanhMucCha(tenDanhMuc);

			if (listSanPham.size() == 0) {
				baoLoi += "Không có sản phẩm nào";
				request.setAttribute("baoLoi", baoLoi);
				System.out.println("list: " + listSanPham.toString());
			}

			request.setAttribute("sanPhamList", listSanPham);
			request.setAttribute("listMaus", listMaus);
			request.setAttribute("listKichCos", listKichCos);
			request.setAttribute("listDanhMucs", listDanhMucs);
			request.setAttribute("tenDanhMuc", tenDanhMuc);
			request.getRequestDispatcher("indexdanhmuctheoloai.jsp").forward(request, response);

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
			request.setAttribute("keySearch", keySearch);
			request.getRequestDispatcher("/product/searchsanpham.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void sapXepTheoVan(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String tenDanhMuc = request.getParameter("tenDanhMuc");
			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			List<SanPham> listSanPham = sanPhamDAO.sapXepTheoVan(tenDanhMuc);

			MauDAO mauDAO = new MauDAO();
			List<Mau> listMaus = mauDAO.selectAll();

			KichCoDAO kichCoDAO = new KichCoDAO();
			List<KichCo> listKichCos = kichCoDAO.selectAll();

			DanhMucDAO danhMucDAO = new DanhMucDAO();
			List<DanhMuc> listDanhMucs = danhMucDAO.selectAllDanhMucByDanhMucCha(tenDanhMuc);

			request.setAttribute("sanPhamList", listSanPham);
			request.setAttribute("tenDanhMuc", tenDanhMuc);
			request.setAttribute("listMaus", listMaus);
			request.setAttribute("listKichCos", listKichCos);
			request.setAttribute("listDanhMucs", listDanhMucs);
			request.getRequestDispatcher("indexdanhmuctheoloai.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void sapXepTheoGiaGiam(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String tenDanhMuc = request.getParameter("tenDanhMuc");
			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			List<SanPham> listSanPham = sanPhamDAO.sapXepTheogiaGiam(tenDanhMuc);

			MauDAO mauDAO = new MauDAO();
			List<Mau> listMaus = mauDAO.selectAll();

			KichCoDAO kichCoDAO = new KichCoDAO();
			List<KichCo> listKichCos = kichCoDAO.selectAll();

			DanhMucDAO danhMucDAO = new DanhMucDAO();
			List<DanhMuc> listDanhMucs = danhMucDAO.selectAllDanhMucByDanhMucCha(tenDanhMuc);

			request.setAttribute("sanPhamList", listSanPham);
			request.setAttribute("tenDanhMuc", tenDanhMuc);
			request.setAttribute("listMaus", listMaus);
			request.setAttribute("listKichCos", listKichCos);
			request.setAttribute("listDanhMucs", listDanhMucs);
			request.getRequestDispatcher("indexdanhmuctheoloai.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
