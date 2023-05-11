package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GioHang;
import model.KhachHang;
import model.SanPham;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import database.SanPhamDAO;

/**
 * Servlet implementation class GioHangController
 */
public class GioHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GioHangController() {
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
		if (hanhDong.equals("them-gio-hang")) {
			themGioHang(request, response);
		} else if (hanhDong.equals("chi-tiet-gio-hang")) {
			chiTietGioHang(request, response);
		} else if (hanhDong.equals("xoa-sanpham-trong-gio-hang")) {
			xoaSPTrongGioHang(request, response);
		} else if (hanhDong.equals("dat-hang-thanh-cong")) {
//			datHangThanhCong(request, response);
		} else if (hanhDong.equals("xoa-san-pham")) {
//			xoaSanPham(request, response);
		} else if (hanhDong.equals("search")) {
//			search(request, response);
		} else if (hanhDong.equals("list-ma")) {
//			listMa(request, response);
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

	private void themGioHang(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			int soLuong = 1;
			String baoLoi = "";
			String maSanPham = request.getParameter("maSanPham");
			String sL = request.getParameter("soLuong");
			System.out.println("sl: " + sL);
			HttpSession httpSession = request.getSession();
			KhachHang khachHang = (KhachHang) httpSession.getAttribute("khachHang");
			System.out.println("masanpham: " + maSanPham);
			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			if (khachHang == null) {
				baoLoi += "Bạn chưa đăng nhập hệ thống, vui lòng đăng nhập!";
				request.setAttribute("baoLoi", baoLoi);
			} else {
				if (maSanPham != null) {
					SanPham sanPham = sanPhamDAO.getSanPhamByMaSanPham(maSanPham);
					System.out.println("sp1: " + sanPham.toString());
					if (sanPham != null) {
						request.setAttribute("sanPham", sanPham);
						if (httpSession.getAttribute("gioHang") == null) {
							GioHang gioHang = new GioHang();
							System.out.println("cartID new : " + gioHang.getCartID());
							gioHang.insertToCart(sanPham, soLuong);
							System.out.println("giohang: " + gioHang.toString());
							httpSession.setAttribute("gioHang", gioHang);
						} else {
							GioHang gioHang = (GioHang) httpSession.getAttribute("gioHang");
							System.out.println("giohang th2: " + gioHang.toString());
							TreeMap<SanPham, Integer> map = gioHang.getLists();
							gioHang.insertToCart(sanPham, soLuong);

							long cartID = gioHang.getCartID();
							System.out.println("cartID: " + cartID);
							httpSession.setAttribute("cartID", cartID);
							httpSession.setAttribute("gioHang", gioHang);
						}
					}
				}
			}

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/sucesscart.jsp");
			requestDispatcher.forward(request, response);
		} catch (

		Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void chiTietGioHang(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String countPlus = request.getParameter("countPlus");
			String countMinus = request.getParameter("countMinus");
//			int countMinusConvert = Integer.parseInt(countMinus);
			String maSP = request.getParameter("maSanPham");
			System.out.println("maSp: " + maSP);
			System.out.println("countMinus: " + (countMinus));
			System.out.println("countPlus: " + countPlus);
			String url = "/product/giohang.jsp";
			HttpSession httpSession = request.getSession();
			if (countMinus == null && countPlus == null) {
				if (httpSession.getAttribute("gioHang") == null) {
					GioHang gioHang = new GioHang();
					httpSession.setAttribute("gioHang", gioHang);
				} else {
					GioHang gioHang = (GioHang) httpSession.getAttribute("gioHang");
//			String cartID = (String) httpSession.getAttribute("cartID");

					System.out.println("giohang: " + gioHang.toString());
//			System.out.println("CartID: " + cartID);

//			httpSession.setAttribute("cartID", cartID);
					httpSession.setAttribute("gioHang", gioHang);
				}
			} else if (countMinus != null && countPlus == null) {
				GioHang gioHang = (GioHang) httpSession.getAttribute("gioHang");
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

//				for (Map.Entry<SanPham, Integer> entry : maps.entrySet()) {
//					System.out.println("entry: " + entry.getKey().toString());
//					if (entry.getKey().getMaSanPham().equals(maSP)) {
//						count = entry.getValue() - 1;
//						if (count == 0) {
//							maps.remove(entry.getKey());
//							System.out.println("count =0: " + count);
//						} else {
//							entry.setValue(count);
//							System.out.println("count #0: " + count);
//						}
//						System.out.println("count: " + count);
//						System.out.println("count: " + entry.getValue());
//					}
//				}
				gioHang.setLists(maps);
				httpSession.setAttribute("gioHang", gioHang);
			} else if (countMinus == null && countPlus != null) {
				GioHang gioHang = (GioHang) httpSession.getAttribute("gioHang");
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
				httpSession.setAttribute("gioHang", gioHang);
			}

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void xoaSPTrongGioHang(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maSanPham = request.getParameter("maSanPham");
			System.out.println("maSP: " + maSanPham);
			HttpSession httpSession = request.getSession();
			GioHang gioHang = (GioHang) httpSession.getAttribute("gioHang");
			System.out.println("gioHang: " + gioHang.toString());
			TreeMap<SanPham, Integer> map = gioHang.getLists();
			String url = "/product/giohang.jsp";

			for (Map.Entry<SanPham, Integer> entry : map.entrySet()) {
				if (entry.getKey().getMaSanPham().equals(maSanPham)) {
					map.remove(entry.getKey());
					httpSession.setAttribute("gioHang", gioHang);
					break;
				}
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
