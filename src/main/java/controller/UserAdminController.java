package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.KhachHang;
import model.KhuyenMai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.KhachHangDAO;
import database.KhuyenMaiDAO;

/**
 * Servlet implementation class UserAdminController
 */
public class UserAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAdminController() {
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
		if (hanhDong.equals("them-khuyen-mai")) {
//			themKhuyenMai(request, response);
		} else if (hanhDong.equals("sua-khuyen-mai")) {
//			suaKhuyenMai(request, response);
		} else if (hanhDong.equals("list")) {
			listUser(request, response);
		} else if (hanhDong.equals("save-khuyen-mai")) {
//			saveKhuyenMai(request, response);
		} else if (hanhDong.equals("xoa-khuyen-mai")) {
//			xoaKhuyenMai(request, response);
		} else if (hanhDong.equals("search")) {
//			search(request, response);
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

	private void listUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String indexString = request.getParameter("index");
			int index = Integer.parseInt(indexString);
			System.out.println("index: " + index);

			KhachHangDAO khachHangDAO = new KhachHangDAO();
			List<KhachHang> khachHangList = new ArrayList<KhachHang>();

			int count = khachHangDAO.count1();
			// so ban ghi tren 1 trang
			int pageSize = 5;
			// trang cuoi
			int endPage = 0;
			endPage = count / pageSize;
			if (count % pageSize != 0) {
				endPage++;
			}
			index = (index - 1) * pageSize;

			khachHangList = khachHangDAO.selectListAndPaging(pageSize, index);

			request.setAttribute("end", endPage);
			request.setAttribute("khachHangList", khachHangList);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/user/userlist.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
