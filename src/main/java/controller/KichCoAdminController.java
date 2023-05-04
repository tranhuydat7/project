package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.KichCo;
import model.Mau;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.KichCoDAO;
import database.MauDAO;

/**
 * Servlet implementation class KichCoAdminController
 */
public class KichCoAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KichCoAdminController() {
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
		if (hanhDong.equals("them-kich-co")) {
			themKichCo(request, response);
		} else if (hanhDong.equals("sua-kich-co")) {
			suaKichCo(request, response);
		} else if (hanhDong.equals("list")) {
			listKichCo(request, response);
		} else if (hanhDong.equals("save-kich-co")) {
			saveKichCo(request, response);
		} else if (hanhDong.equals("xoa-kich-co")) {
			xoaKichCo(request, response);
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

	private void listKichCo(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String indexString = request.getParameter("index");
			int index = Integer.parseInt(indexString);
			System.out.println("index: " + index);

			KichCoDAO kichCoDAO = new KichCoDAO();
			List<KichCo> kichCos = new ArrayList<KichCo>();

			int count = kichCoDAO.count1();
			// so ban ghi tren 1 trang
			int pageSize = 5;
			// trang cuoi
			int endPage = 0;
			endPage = count / pageSize;
			if (count % pageSize != 0) {
				endPage++;
			}
			index = (index - 1) * pageSize;

			kichCos = kichCoDAO.selectListAndPaging(pageSize, index);

			request.setAttribute("end", endPage);
			request.setAttribute("listKichCo", kichCos);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/kichco/kichcolist.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void themKichCo(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maKichCo = request.getParameter("maKichCo");
			String tenKichCo = request.getParameter("tenKichCo");

			String baoLoi = "";
			String success = "";
			String failed = "";
			String url = "";
			KichCoDAO kichCoDAO = new KichCoDAO();

			if (kichCoDAO.kiemTraMaKichCo(maKichCo)) {
				baoLoi += "mã màu đã tồn tại, vui lòng chọn mã màu khác";
			} else {
				KichCo kichCo = new KichCo(maKichCo, tenKichCo);

				int ketQua = kichCoDAO.insert(kichCo);
				if (ketQua == 0) {
					url = "/admin/kichco/themkichco.jsp";
					failed += "Thêm kích cỡ thất bại";
				} else {
					url = "/admin/kichco/themkichco.jsp";
					success += "Thêm kích cỡ thành công!";
				}

			}

			request.setAttribute("maKichCo", maKichCo);
			request.setAttribute("tenKichCo", tenKichCo);

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

	private void xoaKichCo(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maKichCo = request.getParameter("maKichCo");

			System.out.println("mamau: " + maKichCo);
			String baoLoi = "";
			String success = "";
			String url = "/kich-co-admin-controller?hanhDong=list&index=1";

			KichCoDAO kichCoDAO = new KichCoDAO();

			int ketQua = kichCoDAO.deleteKichCoByMaKichCo(maKichCo);
			System.out.println("ketqua: " + ketQua);
			if (ketQua > 0) {
				success += "Xoá màu thành công!";
			} else {
				success += "Xoá màu thất bại!";
			}

			request.setAttribute("success", success);

			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void suaKichCo(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maKichCo = request.getParameter("maKichCo");
			System.out.println("maKichCo: " + maKichCo);

			String baoLoi = "";
			String url = "/admin/kichco/suakichco.jsp";
			KichCoDAO kichCoDAO = new KichCoDAO();

			KichCo kichCo = kichCoDAO.getKichCoByMaKichCo(maKichCo);
			System.out.println(kichCo.toString());

			request.setAttribute("kichCo", kichCo);
			request.setAttribute("baoLoi", baoLoi);

			// chuyen trang
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void saveKichCo(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maKichCo = request.getParameter("maKichCo");
			String tenKichCo = request.getParameter("tenKichCo");

			String baoLoi = "";
			String success = "";
			String url = "/kich-co-admin-controller?hanhDong=list&index=1";

			KichCoDAO kichCoDAO = new KichCoDAO();
			KichCo kichCo = new KichCo(maKichCo, tenKichCo);

			int ketQua = kichCoDAO.update(kichCo);
			System.out.println("ketqua: "+ketQua);
			if (ketQua > 0) {
				success += "Sửa đổi danh mục thành công!";
			} else {
				success += "Sửa đổi danh mục thất bại!";
			}

			request.setAttribute("maKichCo", maKichCo);
			request.setAttribute("tenKichCo", tenKichCo);
			request.setAttribute("success", success);
			request.setAttribute("kichCo", kichCo);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
