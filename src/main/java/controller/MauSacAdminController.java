package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DanhMuc;
import model.Mau;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.DanhMucDAO;
import database.MauDAO;

/**
 * Servlet implementation class MauSacAdminController
 */
public class MauSacAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MauSacAdminController() {
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
		if (hanhDong.equals("them-mau-sac")) {
			themMauSac(request, response);
		} else if (hanhDong.equals("sua-mau-sac")) {
			suaMauSac(request, response);
		} else if (hanhDong.equals("list")) {
			listMauSac(request, response);
		} else if (hanhDong.equals("save-mau-sac")) {
			saveMauSac(request, response); // chua xong
		} else if (hanhDong.equals("xoa-mau-sac")) {
			xoaMauSac(request, response);
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

	private void listMauSac(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String indexString = request.getParameter("index");
			int index = Integer.parseInt(indexString);
			System.out.println("index: " + index);

			MauDAO mauDAO = new MauDAO();
			List<Mau> maus = new ArrayList<Mau>();

			int count = mauDAO.count1();
			// so ban ghi tren 1 trang
			int pageSize = 5;
			// trang cuoi
			int endPage = 0;
			endPage = count / pageSize;
			if (count % pageSize != 0) {
				endPage++;
			}
			index = (index - 1) * pageSize;

			maus = mauDAO.selectListAndPaging(pageSize, index);

			request.setAttribute("end", endPage);
			request.setAttribute("listMau", maus);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/mausac/mausaclist.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void themMauSac(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maMau = request.getParameter("maMau");
			String tenMau = request.getParameter("tenMau");

			String baoLoi = "";
			String success = "";
			String failed = "";
			String url = "";
			MauDAO mauDAO = new MauDAO();

			if (mauDAO.kiemTraMaMau(maMau)) {
				baoLoi += "mã màu đã tồn tại, vui lòng chọn mã màu khác";
			} else {
				Mau mau = new Mau(maMau, tenMau);

				int ketQua = mauDAO.insert(mau);
				if (ketQua == 0) {
					url = "/admin/mausac/themmausac.jsp";
					failed += "Thêm màu sắc thất bại";
				} else {
					url = "/admin/mausac/themmausac.jsp";
					success += "Thêm thêm màu thành công!";
				}

			}

			request.setAttribute("maMau", maMau);
			request.setAttribute("tenMau", tenMau);

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

	private void suaMauSac(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maMau = request.getParameter("maMau");
			System.out.println("mamau: " + maMau);

			String maMauNew = "#" + maMau;

			String baoLoi = "";
			String url = "/admin/mausac/suamausac.jsp";
			MauDAO mauDAO = new MauDAO();

			Mau mau = mauDAO.getMauSacByMaMauSac(maMauNew);
			System.out.println(mau.toString());

			request.setAttribute("mau", mau);
			request.setAttribute("baoLoi", baoLoi);

			// chuyen trang
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void saveMauSac(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maMau = request.getParameter("maMau");
			String tenMau = request.getParameter("tenMau");

			String baoLoi = "";
			String success = "";
			String url = "/mau-sac-admin-controller?hanhDong=list&index=1";
			MauDAO mauDAO = new MauDAO();

			Mau mau = new Mau();

			mau.setMaMau(maMau);
			mau.setTenMau(tenMau);

			int ketQua = mauDAO.update(mau);
			if (ketQua > 0) {
				success += "Sửa đổi danh mục thành công!";
			} else {
				success += "Sửa đổi danh mục thất bại!";
			}

			request.setAttribute("maMau", maMau);
			request.setAttribute("tenMau", tenMau);
			request.setAttribute("success", success);
			request.setAttribute("mau", mau);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void xoaMauSac(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maMau = request.getParameter("maMau");

			System.out.println("mamau: " + maMau);
			String baoLoi = "";
			String success = "";
			String url = "/mau-sac-admin-controller?hanhDong=list&index=1";

			String maMauNew = "#" + maMau;
			System.out.println("mamaunew: " + maMauNew);
			MauDAO mauDAO = new MauDAO();

			int ketQua = mauDAO.deleteMauByMaMau(maMauNew);
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

}
