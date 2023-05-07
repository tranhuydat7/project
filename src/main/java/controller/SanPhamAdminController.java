package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.DanhMuc;
import model.KhuyenMai;
import model.KichCo;
import model.Mau;
import model.SanPham;
import util.JDBCUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.core.FrameworkListener;

import database.DanhMucDAO;
import database.KhuyenMaiDAO;
import database.KichCoDAO;
import database.MauDAO;
import database.SanPhamDAO;

/**
 * Servlet implementation class SanPhamAdminController
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class SanPhamAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SanPhamAdminController() {
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
		if (hanhDong.equals("them-san-pham")) {
			themSanPham(request, response);
		} else if (hanhDong.equals("sua-san-pham")) {
			suaSanPham(request, response);
		} else if (hanhDong.equals("list")) {
			listSanPham(request, response);
		} else if (hanhDong.equals("save-san-pham")) {
			saveSanPham(request, response);
		} else if (hanhDong.equals("xoa-san-pham")) {
			xoaSanPham(request, response);
		} else if (hanhDong.equals("search")) {
			search(request, response);
		} else if (hanhDong.equals("list-ma")) {
			listMa(request, response);
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

	private void listSanPham(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String indexString = request.getParameter("index");
			int index = Integer.parseInt(indexString);
			System.out.println("index: " + index);

			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			List<SanPham> sanPhamList = new ArrayList<SanPham>();

			int count = sanPhamDAO.count1();
			// so ban ghi tren 1 trang
			int pageSize = 5;
			// trang cuoi
			int endPage = 0;
			endPage = count / pageSize;
			if (count % pageSize != 0) {
				endPage++;
			}
			index = (index - 1) * pageSize;

			sanPhamList = sanPhamDAO.selectListAndPaging(pageSize, index);

			request.setAttribute("end", endPage);
			request.setAttribute("sanPhamList", sanPhamList);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/sanpham/sanphamlist.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void listMa(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			DanhMucDAO danhMucDAO = new DanhMucDAO();
			MauDAO mauDAO = new MauDAO();
			KichCoDAO kichCoDAO = new KichCoDAO();

			List<DanhMuc> danhMucs = new ArrayList<DanhMuc>();
			danhMucs = danhMucDAO.selectListOrByName();

			List<Mau> maus = new ArrayList<Mau>();
			maus = mauDAO.selectListOrByName();

			List<KichCo> kichCos = new ArrayList<KichCo>();
			kichCos = kichCoDAO.selectListOrByName();

			request.setAttribute("danhMucs", danhMucs);
			request.setAttribute("maus", maus);
			request.setAttribute("kichCos", kichCos);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/sanpham/themsanpham.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void themSanPham(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maSanPham = request.getParameter("maSanPham");
			String tenSanPham = request.getParameter("tenSanPham");
			String giaBan = request.getParameter("giaBan");
			String giaGoc = request.getParameter("giaGoc");
			String soLuong = request.getParameter("soLuong");
			String moTa = request.getParameter("moTa");
			String maDanhMuc = request.getParameter("maDanhMuc");
			String maMau = request.getParameter("maMau");
			String maKichCo = request.getParameter("maKichCo");
			String cachSuDung = request.getParameter("cachSuDung");
			String chatLieu = request.getParameter("chatLieu");
			Part part = request.getPart("avatar");
			String isHot = request.getParameter("isHot");
			System.out.println("isHot: " + isHot);

			// convert du lieu
			int giaBanConvert = Integer.parseInt(giaBan);
			int giaGocConvert = Integer.parseInt(giaGoc);
			int soLuongConvert = Integer.parseInt(soLuong);
			boolean isHotSP = false;
			if (isHot != null) {
				isHotSP = true;
				System.out.println("ishotsp:" + isHotSP);
			}

			String baoLoi = "";
			String success = "";
			String failed = "";
			String url = "";
			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
			part.write("/Users/admin/eclipse-workspace/DaYeShop/src/main/webapp/image/avatar" + "/" + fileName);

			if (sanPhamDAO.kiemTraMaSanPham(maSanPham)) {
				baoLoi += "mã sản phẩm đã tồn tại, vui lòng chọn mã sản phẩm khác";
				url = "/san-pham-admin-controller?hanhDong=list-ma";
			} else {
				DanhMuc danhMuc = new DanhMuc();
				danhMuc.setMaDanhMuc(maDanhMuc);
				Mau mau = new Mau();
				mau.setMaMau(maMau);
				KichCo kichCo = new KichCo();
				kichCo.setMaKichCo(maKichCo);

				SanPham sanPham = new SanPham(maSanPham, tenSanPham, giaGocConvert, giaBanConvert, soLuongConvert, moTa,
						danhMuc, mau, kichCo, fileName, cachSuDung, chatLieu, isHotSP);

				int ketQua = sanPhamDAO.insert(sanPham);
				if (ketQua == 0) {
					url = "/san-pham-admin-controller?hanhDong=list-ma";
					failed += "Thêm sản phẩm thất bại";
				} else {
					url = "/san-pham-admin-controller?hanhDong=list&index=1";
					success += "Thêm sản phẩm thành công!";
				}

			}

			request.setAttribute("maSanPham", maSanPham);
			request.setAttribute("tenSanPham", tenSanPham);
			request.setAttribute("giaBan", giaBan);
			request.setAttribute("giaGoc", giaGoc);
			request.setAttribute("soLuong", soLuong);
			request.setAttribute("moTa", moTa);
			request.setAttribute("maDanhMuc", maDanhMuc);
			request.setAttribute("maMau", maMau);
			request.setAttribute("maKichCo", maKichCo);
//			request.setAttribute("avatar", avatar);
			request.setAttribute("cachSuDung", cachSuDung);
			request.setAttribute("chatLieu", chatLieu);

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

	private void suaSanPham(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maSanPham = request.getParameter("maSanPham");
			System.out.println("maSanPham: " + maSanPham);

			String baoLoi = "";
			String url = "/admin/sanpham/suasanpham.jsp";
			SanPhamDAO sanPhamDAO = new SanPhamDAO();

			SanPham sanPham = sanPhamDAO.getSanPhamByMaSanPham(maSanPham);
			System.out.println("maDanhMuc: " + sanPham.getDanhMuc().getMaDanhMuc());
			System.out.println("maMau: " + sanPham.getMauSac().getMaMau());
			System.out.println("maKichCo: " + sanPham.getKichCo().getMaKichCo());

			request.setAttribute("sanPham", sanPham);
			request.setAttribute("baoLoi", baoLoi);

			// chuyen trang
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void saveSanPham(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maSanPham = request.getParameter("maSanPham");
			String tenSanPham = request.getParameter("tenSanPham");
			String giaBan = request.getParameter("giaBan");
			String giaGoc = request.getParameter("giaGoc");
			String soLuong = request.getParameter("soLuong");
			String moTa = request.getParameter("moTa");
			String maDanhMuc = request.getParameter("maDanhMuc");
			String maMau = request.getParameter("maMau");
			String maKichCo = request.getParameter("maKichCo");
			String avatar = request.getParameter("avatar");
			String cachSuDung = request.getParameter("cachSuDung");
			String chatLieu = request.getParameter("chatLieu");
			String isHot = request.getParameter("isHot");
			System.out.println("isHot: " + isHot);
			boolean isHotSP = false;
			if (isHot != null) {
				isHotSP = true;
				System.out.println("ishotsp:" + isHotSP);
			}

			// convert du lieu
			int giaBanConvert = Integer.parseInt(giaBan);
			int giaGocConvert = Integer.parseInt(giaGoc);
			int soLuongConvert = Integer.parseInt(soLuong);

			String baoLoi = "";
			String success = "";
			String url = "/san-pham-admin-controller?hanhDong=list&index=1";

			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			DanhMuc danhMuc = new DanhMuc();
			danhMuc.setMaDanhMuc(maDanhMuc);
			Mau mau = new Mau();
			mau.setMaMau(maMau);
			KichCo kichCo = new KichCo();
			kichCo.setMaKichCo(maKichCo);
			SanPham sanPham = new SanPham(maSanPham, tenSanPham, giaGocConvert, giaBanConvert, soLuongConvert, moTa,
					danhMuc, mau, kichCo, avatar, cachSuDung, chatLieu, isHotSP);

			int ketQua = sanPhamDAO.update(sanPham);
			System.out.println("ketqua: " + ketQua);
			if (ketQua > 0) {
				success += "Sửa đổi khuyến mại thành công!";
			} else {
				success += "Sửa đổi khuyến mại thất bại!";
			}

			request.setAttribute("success", success);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void xoaSanPham(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String maSanPham = request.getParameter("maSanPham");

			System.out.println("maSanPham: " + maSanPham);
			String baoLoi = "";
			String success = "";
			String url = "/san-pham-admin-controller?hanhDong=list&index=1";

			SanPhamDAO sanPhamDAO = new SanPhamDAO();

			int ketQua = sanPhamDAO.deleteSanPhamByMaSanPham(maSanPham);
			System.out.println("ketqua: " + ketQua);
			if (ketQua > 0) {
				success += "Xoá sản phẩm thành công!";
			} else {
				success += "Xoá sản phẩm thất bại!";
			}

			request.setAttribute("success", success);

			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

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

			SanPhamDAO sanPhamDAO = new SanPhamDAO();
			List<SanPham> sanPhams = new ArrayList<SanPham>();

			// tong so ban ghi
			int count = sanPhamDAO.count(keySearch);
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

			sanPhams = sanPhamDAO.searchAndPaging(keySearch, pageSize, index);

			request.setAttribute("end", endPage);
			request.setAttribute("sanPhamList", sanPhams);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/sanpham/sanphamlist.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
