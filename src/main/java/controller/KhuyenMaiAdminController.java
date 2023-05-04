package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.KhuyenMai;
import model.KichCo;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.KhuyenMaiDAO;
import database.KichCoDAO;

/**
 * Servlet implementation class KhuyenMaiAdminController
 */
public class KhuyenMaiAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KhuyenMaiAdminController() {
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
			themKhuyenMai(request, response);
		} else if (hanhDong.equals("sua-khuyen-mai")) {
			suaKhuyenMai(request, response);
		} else if (hanhDong.equals("list")) {
			listKhuyenMai(request, response);
		} else if (hanhDong.equals("save-khuyen-mai")) {
			saveKhuyenMai(request, response);
		} else if (hanhDong.equals("xoa-khuyen-mai")) {
			xoaKhuyenMai(request, response);
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

	private void listKhuyenMai(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String indexString = request.getParameter("index");
			int index = Integer.parseInt(indexString);
			System.out.println("index: " + index);

			KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
			List<KhuyenMai> khuyenMaisList = new ArrayList<KhuyenMai>();

			int count = khuyenMaiDAO.count1();
			// so ban ghi tren 1 trang
			int pageSize = 5;
			// trang cuoi
			int endPage = 0;
			endPage = count / pageSize;
			if (count % pageSize != 0) {
				endPage++;
			}
			index = (index - 1) * pageSize;

			khuyenMaisList = khuyenMaiDAO.selectListAndPaging(pageSize, index);

			request.setAttribute("end", endPage);
			request.setAttribute("khuyenMaisList", khuyenMaisList);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/khuyenmai/khuyenmailist.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void themKhuyenMai(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maKhuyenMai = request.getParameter("maKhuyenMai");
			String tenKhuyenMai = request.getParameter("tenKhuyenMai");
			String giamGiaTu = request.getParameter("giamGiaTu");
			String giamGiaMax = request.getParameter("giamGiaMax");
			String giamGiaPhanTram = request.getParameter("giamGiaPhanTram");
			String ngayBatDau = request.getParameter("ngayBatDau");
			String ngayKetThuc = request.getParameter("ngayKetThuc");
			System.out.println("makhuyenmai: " + maKhuyenMai);

			// convert du lieu
			int giamGiaTuConvert = Integer.parseInt(giamGiaTu);
			int giamGiaMaxConvert = Integer.parseInt(giamGiaMax);
			int giamGiaPhanTramConvert = Integer.parseInt(giamGiaPhanTram);
			LocalDate ngayBatDauDateTime = LocalDate.parse(ngayBatDau);
			LocalDate ngayKetThucDateTime = LocalDate.parse(ngayKetThuc);
			Date ngayBatDauDate = Date.valueOf(ngayBatDauDateTime);
			Date ngayKetThucDate = Date.valueOf(ngayKetThucDateTime);

			String baoLoi = "";
			String success = "";
			String failed = "";
			String url = "";
			KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();

			if (khuyenMaiDAO.kiemTraMaKhuyenMai(maKhuyenMai)) {
				baoLoi += "mã khuyến mại đã tồn tại, vui lòng chọn mã khuyến mại khác";
				url = "/admin/khuyenmai/themkhuyenmai.jsp";
			} else {
				KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, giamGiaTuConvert, giamGiaMaxConvert,
						giamGiaPhanTramConvert, ngayBatDauDate, ngayKetThucDate);

				int ketQua = khuyenMaiDAO.insert(khuyenMai);
				if (ketQua == 0) {
					url = "/admin/khuyenmai/themkhuyenmai.jsp";
					failed += "Thêm khuyến mại thất bại";
				} else {
					url = "/admin/khuyenmai/themkhuyenmai.jsp";
					success += "Thêm khuyến mại  thành công!";
				}

			}

			request.setAttribute("maKhuyenMai", maKhuyenMai);
			request.setAttribute("tenKhuyenMai", tenKhuyenMai);
			request.setAttribute("giamGiaTu", giamGiaTu);
			request.setAttribute("giamGiaMax", giamGiaMax);
			request.setAttribute("giamGiaPhanTram", giamGiaPhanTram);
			request.setAttribute("ngayBatDau", ngayBatDau);
			request.setAttribute("ngayKetThuc", ngayKetThuc);

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

	private void xoaKhuyenMai(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maKhuyenMai = request.getParameter("maKhuyenMai");

			System.out.println("makhuyenmai: " + maKhuyenMai);
			String baoLoi = "";
			String success = "";
			String url = "/khuyen-mai-admin-controller?hanhDong=list&index=1";

			KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();

			int ketQua = khuyenMaiDAO.deleteKhuyenMaiByMaKhuyenMai(maKhuyenMai);
			System.out.println("ketqua: " + ketQua);
			if (ketQua > 0) {
				success += "Xoá khuyến mại thành công!";
			} else {
				success += "Xoá khuyến mại thất bại!";
			}

			request.setAttribute("success", success);

			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void suaKhuyenMai(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maKhuyenMai = request.getParameter("maKhuyenMai");
			System.out.println("maKhuyenMai: " + maKhuyenMai);

			String baoLoi = "";
			String url = "/admin/khuyenmai/suakhuyenmai.jsp";
			KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();

			KhuyenMai khuyenMai = khuyenMaiDAO.getKhuyenMaiByMaKhuyenMai(maKhuyenMai);

			request.setAttribute("khuyenMai", khuyenMai);
			request.setAttribute("baoLoi", baoLoi);

			// chuyen trang
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void saveKhuyenMai(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maKhuyenMai = request.getParameter("maKhuyenMai");
			String tenKhuyenMai = request.getParameter("tenKhuyenMai");
			String giamGiaTu = request.getParameter("giamGiaTu");
			String giamGiaMax = request.getParameter("giamGiaMax");
			String giamGiaPhanTram = request.getParameter("giamGiaPhanTram");
			String ngayBatDau = request.getParameter("ngayBatDau");
			String ngayKetThuc = request.getParameter("ngayKetThuc");

			// convert du lieu
			int giamGiaTuConvert = Integer.parseInt(giamGiaTu);
			int giamGiaMaxConvert = Integer.parseInt(giamGiaMax);
			int giamGiaPhanTramConvert = Integer.parseInt(giamGiaPhanTram);
			LocalDate ngayBatDauDateTime = LocalDate.parse(ngayBatDau);
			LocalDate ngayKetThucDateTime = LocalDate.parse(ngayKetThuc);
			Date ngayBatDauDate = Date.valueOf(ngayBatDauDateTime);
			Date ngayKetThucDate = Date.valueOf(ngayKetThucDateTime);

			String baoLoi = "";
			String success = "";
			String url = "/khuyen-mai-admin-controller?hanhDong=list&index=1";

			KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
			KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai, tenKhuyenMai, giamGiaTuConvert, giamGiaMaxConvert,
					giamGiaPhanTramConvert, ngayBatDauDate, ngayKetThucDate);

			int ketQua = khuyenMaiDAO.update(khuyenMai);
			System.out.println("ketqua: " + ketQua);
			if (ketQua > 0) {
				success += "Sửa đổi khuyến mại thành công!";
			} else {
				success += "Sửa đổi khuyến mại thất bại!";
			}

			request.setAttribute("maKhuyenMai", maKhuyenMai);
			request.setAttribute("tenKhuyenMai", tenKhuyenMai);
			
			request.setAttribute("success", success);
			request.setAttribute("khuyenMai", khuyenMai);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
