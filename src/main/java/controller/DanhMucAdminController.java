package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DanhMuc;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.DanhMucDAO;

/**
 * Servlet implementation class DanhMucAdminController
 */
public class DanhMucAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DanhMucAdminController() {
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
		if (hanhDong.equals("them-danh-muc")) {
			themDanhMuc(request, response);
		} else if (hanhDong.equals("sua-danh-muc")) {
			suaDanhMuc(request, response);
		} else if (hanhDong.equals("list")) {
			listDanhMuc(request, response);
		} else if (hanhDong.equals("save-danh-muc")) {
			saveDanhMuc(request, response);
		} else if (hanhDong.equals("xoa-danh-muc")) {
			xoaDanhMuc(request, response);

//		}else if (hanhDong.equals("search")) {
//			timKiemDanhMuc(request, response);
		} else if (hanhDong.equals("search")) {
			search(request, response);
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

	private void themDanhMuc(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maDanhMuc = request.getParameter("maDanhMuc");
			String tenDanhMuc = request.getParameter("tenDanhMuc");
			String danhMucCha = request.getParameter("danhMucCha");

			String baoLoi = "";
			String success = "";
			String failed = "";
			String url = "";
			DanhMucDAO danhMucDAO = new DanhMucDAO();

			if (danhMucDAO.kiemTraMaDanhMuc(maDanhMuc)) {
				baoLoi += "mã danh mục đã tồn tại, vui lòng nhập mã danh mục khác";
			} else {
				DanhMuc danhMucParent = new DanhMuc();
				danhMucParent.setMaDanhMuc(danhMucCha);
				DanhMuc danhMuc = new DanhMuc(maDanhMuc, tenDanhMuc, danhMucParent);
				int ketQua = danhMucDAO.insert(danhMuc);
				if (ketQua == 0) {
					url = "/admin/themdanhmuc.jsp";
					failed += "Thêm danh mục thất bại";
				} else {
					url = "/admin/themdanhmuc.jsp";
					success += "Thêm danh mục thành công!";
				}

			}

			request.setAttribute("maDanhMuc", maDanhMuc);
			request.setAttribute("tenDanhMuc", tenDanhMuc);
			request.setAttribute("danhMucCha", danhMucCha);

			request.setAttribute("baoLoi", baoLoi);
			request.setAttribute("success", success);
			request.setAttribute("failed", failed);

			// chuyen trang
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void listDanhMuc(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String indexString = request.getParameter("index");
			int index = Integer.parseInt(indexString);
			System.out.println("index: " + index);

			DanhMucDAO danhMucDAO = new DanhMucDAO();
			List<DanhMuc> mucs = new ArrayList<DanhMuc>();

			int count = danhMucDAO.count1();
			// so ban ghi tren 1 trang
			int pageSize = 5;
			// trang cuoi
			int endPage = 0;
			endPage = count / pageSize;
			if (count % pageSize != 0) {
				endPage++;
			}
			index = (index - 1) * pageSize;

			mucs = danhMucDAO.selectListAndPaging(pageSize, index);

			request.setAttribute("end", endPage);
			request.setAttribute("listDanhMuc", mucs);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/danhmuc.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void suaDanhMuc(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maDanhMuc = request.getParameter("maDanhMuc");

			String baoLoi = "";
			String url = "/admin/suadanhmuc.jsp";
			DanhMucDAO danhMucDAO = new DanhMucDAO();

			DanhMuc danhMuc = danhMucDAO.getDanhMucByMaDanhMuc(maDanhMuc);

			request.setAttribute("danhMuc", danhMuc);

			request.setAttribute("baoLoi", baoLoi);

			// chuyen trang
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void saveDanhMuc(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maDanhMuc = request.getParameter("maDanhMuc");
			String tenDanhMuc = request.getParameter("tenDanhMuc");
			String danhmuccha = request.getParameter("danhMucCha");

			String baoLoi = "";
			String success = "";
//			String url = "/admin/suadanhmuc.jsp";
			String url = "/danh-muc-admin-controller?hanhDong=list&index=1";
			DanhMucDAO danhMucDAO = new DanhMucDAO();

			DanhMuc dmc = new DanhMuc();
			dmc.setMaDanhMuc(danhmuccha);

			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setMaDanhMuc(maDanhMuc);
			danhMuc.setTenDanhMuc(tenDanhMuc);
			danhMuc.setDanhMucCha(dmc);

			int ketQua = danhMucDAO.update(danhMuc);
			if (ketQua > 0) {
				success += "Sửa đổi danh mục thành công!";
			} else {
				success += "Sửa đổi danh mục thất bại!";
			}

			request.setAttribute("maDanhMuc", maDanhMuc);
			request.setAttribute("tenDanhMuc", tenDanhMuc);
			request.setAttribute("danhmuccha", danhmuccha);
			request.setAttribute("success", success);
			request.setAttribute("danhMuc", danhMuc);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void xoaDanhMuc(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maDanhMuc = request.getParameter("maDanhMuc");
			String baoLoi = "";
			String success = "";
//			String url = "/danh-muc-admin-controller?hanhDong=list";
			String url = "/danh-muc-admin-controller?hanhDong=list&index=1";

			DanhMucDAO danhMucDAO = new DanhMucDAO();

			int ketQua = danhMucDAO.deleteDanhMucByMaDanhMuc(maDanhMuc);
			if (ketQua > 0) {
				success += "Xoá danh mục thành công!";
			} else {
				success += "Xoá danh mục thất bại!";
			}

			request.setAttribute("success", success);

			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

//	private void timKiemDanhMuc(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			request.setCharacterEncoding("UTF-8");
//			response.setCharacterEncoding("UTF-8");
//			response.setContentType("text/html; charset=UTF-8");
//
//			String search = request.getParameter("search");
//			String baoLoi = "";
//			String success = "";
//
//			List<DanhMuc> danhMucs = new ArrayList<DanhMuc>();
//			DanhMucDAO danhMucDAO = new DanhMucDAO();
//
//			danhMucs = danhMucDAO.selectDanhMucByName(search);
//			request.setAttribute("listDanhMuc", danhMucs);
//
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/danhmuc.jsp");
//			requestDispatcher.forward(request, response);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String keySearch = request.getParameter("search");
			String indexString = request.getParameter("index");
			System.out.println("indexString: " + indexString);
			int index = Integer.parseInt(indexString);
			System.out.println("index: " + index);

			DanhMucDAO danhMucDAO = new DanhMucDAO();
			List<DanhMuc> mucs = new ArrayList<DanhMuc>();

			// tong so ban ghi
			int count = danhMucDAO.count(keySearch);
			System.out.println("count: " + count);

			// so ban ghi tren 1 trang
			int pageSize = 5;

			// trang cuoi
			int endPage = 0;
			endPage = count / pageSize;
			if (count % pageSize != 0) {
				endPage++;
			}
			index = (index - 1) * pageSize;

			mucs = danhMucDAO.searchAndPaging(keySearch, pageSize, index);

			request.setAttribute("end", endPage);
			request.setAttribute("listDanhMuc", mucs);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/danhmuc.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
