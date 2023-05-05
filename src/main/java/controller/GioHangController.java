package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GioHang;
import model.SanPham;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
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
		} else if (hanhDong.equals("xac-nhan-dat-hang")) {
//			xacNhanDatHang(request, response);
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
			String maSanPham = request.getParameter("maSanPham");
			System.out.println("masanpham: " + maSanPham);
			SanPhamDAO sanPhamDAO = new SanPhamDAO();

			if (maSanPham != null) {
				SanPham sanPham = sanPhamDAO.getSanPhamByMaSanPham(maSanPham);
				System.out.println("sp1: " + sanPham.toString());
				if (sanPham != null) {
					HttpSession httpSession = request.getSession();
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

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/sucesscart.jsp");
			requestDispatcher.forward(request, response);
		}catch(

	Exception e)
	{
		// TODO: handle exception
		e.printStackTrace();
	}
	}

	private void chiTietGioHang(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			HttpSession httpSession = request.getSession();
			GioHang gioHang = (GioHang) httpSession.getAttribute("gioHang");
//			String cartID = (String) httpSession.getAttribute("cartID");
			String url = "/product/giohang.jsp";

			System.out.println("giohang: " + gioHang.toString());
//			System.out.println("CartID: " + cartID);

//			httpSession.setAttribute("cartID", cartID);
			httpSession.setAttribute("gioHang", gioHang);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
