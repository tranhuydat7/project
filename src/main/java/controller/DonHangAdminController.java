package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ChiTietDonHang;
import model.DonHang;
import model.SanPham;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.ChiTietDonHangDAO;
import database.DonHangDAO;
import database.SanPhamDAO;

/**
 * Servlet implementation class DonHangAdminController
 */
public class DonHangAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonHangAdminController() {
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
		if (hanhDong.equals("them-don-hang")) {
//			themDonHang(request, response);
		} else if (hanhDong.equals("sua-don-hang")) {
//			suaDonHang(request, response);
		} else if (hanhDong.equals("list")) {
			listDonHang(request, response);
		} else if (hanhDong.equals("save-don-hang")) {
//			saveDonHang(request, response);
		} else if (hanhDong.equals("xoa-don-hang")) {
//			xoaDonHang(request, response);
		} else if (hanhDong.equals("search")) {
//			search(request, response);
		} else if (hanhDong.equals("list-ma")) {
//			listMa(request, response);
		} else if (hanhDong.equals("chi-tiet-don-hang")) {
			chiTietDonHang(request, response);
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

	private void listDonHang(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String indexString = request.getParameter("index");
			int index = Integer.parseInt(indexString);
			System.out.println("index: " + index);

			DonHangDAO donHangDAO = new DonHangDAO();
			List<DonHang> donHangList = new ArrayList<>();

			int count = donHangDAO.count1();
			// so ban ghi tren 1 trang
			int pageSize = 5;
			// trang cuoi
			int endPage = 0;
			endPage = count / pageSize;
			if (count % pageSize != 0) {
				endPage++;
			}
			index = (index - 1) * pageSize;

			donHangList = donHangDAO.selectListAndPaging(pageSize, index);

			request.setAttribute("end", endPage);
			request.setAttribute("donHangList", donHangList);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/donhang/donhanglist.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void chiTietDonHang(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maDonHang = request.getParameter("maDonHang");
			String url = "/admin/donhang/chitietdonhang.jsp";
			String baoLoi = "";
			System.out.println("maDonHang: " + maDonHang);

			ChiTietDonHangDAO chiTietDonHangDAO = new ChiTietDonHangDAO();
			ChiTietDonHang chiTietDonHang = chiTietDonHangDAO.selectChiTietDonHangByMaDonHang(maDonHang);

			request.setAttribute("chiTietDonHang", chiTietDonHang);
			request.setAttribute("baoLoi", baoLoi);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
