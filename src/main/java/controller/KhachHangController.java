package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.KhachHang;
import util.MaHoa;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Collection;


import database.KhachHangDAO;

/**
 * Servlet implementation class KhachHangController
 */
public class KhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KhachHangController() {
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
		if (hanhDong.equals("dang-nhap")) {
			dangNhap(request, response);
		} else if (hanhDong.equals("dang-xuat")) {
			dangXuat(request, response);
		} else if (hanhDong.equals("dang-ky")) {
			dangKy(request, response);
		} else if (hanhDong.equals("doi-mat-khau")) {
			doiMatKhau(request, response);
		} else if (hanhDong.equals("doi-thong-tin")) {
			doiThongTin(request, response);
		} else if (hanhDong.equals("thay-doi-anh")) {
//			thayDoiAnh(request, response);
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

	private void dangNhap(HttpServletRequest request, HttpServletResponse response) {
		try {
			String tenDangNhap = request.getParameter("tenDangNhap");
			String password = request.getParameter("matKhau");
			password = MaHoa.toSHA1(password);

			KhachHang khachHang = new KhachHang();
			khachHang.setTenDangNhap(tenDangNhap);
			khachHang.setPassword(password);

			KhachHangDAO khachHangDAO = new KhachHangDAO();
			KhachHang kh = khachHangDAO.selectByUserAndPassWord(khachHang);
			String url = "";
			if (kh != null) {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("khachHang", kh);
				url = "/index-controller";
			} else {
				request.setAttribute("baoLoi", "Tên đăng nhập hoặc mật khẩu không đúng");
				url = "/user/dangnhap.jsp";
			}

			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void dangXuat(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();

		// huy bo session
		httpSession.invalidate();

		try {
			// chuyển hướng đến trang chủ sau khi huỷ session
			response.sendRedirect("/Bai4_Bootstrap/index-controller");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void dangKy(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String tenDangNhap = request.getParameter("tenDangNhap");
			String hoVaTen = request.getParameter("hoVaTen");
			String email = request.getParameter("email");
			String dienThoai = request.getParameter("dienThoai");
			String matKhau = request.getParameter("matKhau");
			String matKhauNhapLai = request.getParameter("matKhauNhapLai");

			// check tên đăng nhập trùng
			String baoLoi = "";
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			String url = "";

			if (khachHangDAO.kiemTraTenDangNhap(tenDangNhap)) {
				baoLoi += "tên đăng nhập đã tồn tại, vui lòng chọn tên đăng nhập khác";
			}

			if (khachHangDAO.kiemTraKhoangTrangHaiDauTenDangNhap(tenDangNhap)) {
				baoLoi += "tên đăng nhập có khoảng trắng ở hai đầu, vui lòng nhập lại tên đăng nhập!";
			}

			if (khachHangDAO.kiemTraKhoangTrangTrongTenDangNhap(tenDangNhap)) {
				baoLoi += "tên đăng nhập có chứa khoảng trắng, vui lòng nhập lại tên đăng nhập!";
			}

			if (!matKhau.equals(matKhauNhapLai)) {
				baoLoi += "mật khẩu không khớp";
			} else {
				matKhau = MaHoa.toSHA1(matKhau);
			}

			if (baoLoi.length() > 0) {
				url = "/user/dangky.jsp";
			} else {
				KhachHang khachHang = new KhachHang(tenDangNhap, matKhau, hoVaTen, null, null, null, null, null,
						dienThoai, email, false);
				khachHangDAO.insert(khachHang);
				url = "/user/success.jsp";
			}

			// forward value
			request.setAttribute("tenDangNhap", tenDangNhap);
			request.setAttribute("hoVaTen", hoVaTen);
			request.setAttribute("dienThoai", dienThoai);
			request.setAttribute("email", email);

			// forward lỗi
			request.setAttribute("baoLoi", baoLoi);

			// chuyển trang
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doiMatKhau(HttpServletRequest request, HttpServletResponse response) {
		try {
			String matKhauHienTai = request.getParameter("matKhauHienTai");
			String matKhauMoi = request.getParameter("matKhauMoi");
			String matKhauMoiNhapLai = request.getParameter("matKhauMoiNhapLai");

			String matKhauHienTaiSha1 = MaHoa.toSHA1(matKhauHienTai);

			String baoLoi = "";
			String url = "";

			// kiem tra mat khau cu co giong hay khong
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("khachHang");
			KhachHang khachHang = null;
			if (obj != null)
				khachHang = (KhachHang) obj;
			if (khachHang == null) {
				baoLoi = "Bạn chưa đăng nhập vào hệ thống";
				url = "/user/users_profile.jsp";
			} else {
				if (!matKhauHienTaiSha1.equals(khachHang.getPassword())) {
					baoLoi = "Mật khẩu hiện tại không chính xác";
					url = "/user/users_profile.jsp";
				} else {
					if (!matKhauMoi.equals(matKhauMoiNhapLai)) {
						baoLoi = "Mật khẩu nhập lại không chính xác";
						url = "/user/users_profile.jsp";
					} else {
						String matKhauMoiSha1 = MaHoa.toSHA1(matKhauMoi);
						khachHang.setPassword(matKhauMoiSha1);
						KhachHangDAO khachHangDAO = new KhachHangDAO();
						if (khachHangDAO.changePassword(khachHang)) {
							baoLoi = "Mật khẩu đã thay đổi thành công";
							url = "/user/users_profile.jsp";
						} else {
							baoLoi = "Quá trình thay đổi mật khẩu không thành công";
							System.out.println("ma khach hang: " + khachHang.getMaKhachHang());
							System.out.println("ten dang nhap: " + khachHang.getTenDangNhap());
							url = "/user/users_profile.jsp";
						}
					}
				}
			}
			request.setAttribute("baoLoi", baoLoi);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doiThongTin(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String hoVaTen = request.getParameter("hoVaTen");
			String dienThoai = request.getParameter("dienThoai");
			String email = request.getParameter("email");
			

			// check tên đăng nhập trùng
			String baoLoi = "";
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			String url = "";

			if (baoLoi.length() > 0) {
				url = "/register.jsp";
			} else {
				Object obj = request.getSession().getAttribute("khachHang");
				KhachHang khachHang = null;
				if (obj != null)
					khachHang = (KhachHang) obj;
				if (khachHang != null) {
//					int maKhachHang = khachHang.getMaKhachHang();
					String tenDangNhap=khachHang.getTenDangNhap();
					khachHang = new KhachHang();
//					khachHang.setMaKhachHang(maKhachHang);
					khachHang.setTenDangNhap(tenDangNhap);
					khachHang.setHoVaTen(hoVaTen);
					khachHang.setSoDienThoai(dienThoai);
					khachHang.setEmail(email);
					
					System.out.println("hovaten: " + khachHang.getHoVaTen());
					System.out.println("tendangnhap: " + khachHang.getTenDangNhap());
					
					khachHangDAO.updateInfo(khachHang);
					KhachHang khachHangUpdate = khachHangDAO.selectById(khachHang);
					request.getSession().setAttribute("khachHang", khachHangUpdate);
					url = "/user/users_profile.jsp";
				}
			}

			// forward value
			request.setAttribute("hoVaTen", hoVaTen);
			request.setAttribute("dienThoai", dienThoai);
			request.setAttribute("email", email);

			// forward lỗi
			request.setAttribute("baoLoi", baoLoi);

			// chuyển trang
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	private void thayDoiAnh(HttpServletRequest request, HttpServletResponse response) {
//		Object obj = request.getSession().getAttribute("khachHang");
//		KhachHang khachHang = null;
//		if (obj != null)
//			khachHang = (KhachHang) obj;
//		if (khachHang != null) {
//			try {
//				File file = new File(
//						"/Users/admin/eclipse-workspace/Bai27_Bookstore_p15_SuaLoiDuongDan/src/main/webapp/avatar");
//				String folder = getServletContext().getRealPath("avatar");
//				System.out.println("folder: " + folder);
//				Part filePart = request.getPart("duongDanAnh");
//				String fileName = filePart.getSubmittedFileName();
//
//				System.out.println("fileName: " + fileName);
//				System.out.println("filePart: " + filePart.getName());
//				System.out.println("url: " + folder + "/" + fileName);
//				Collection<Part> lists = request.getParts();
//				for (Part part : lists) {
////					String paths= folder+"/"+fileName;
//					part.write(file + "/" + fileName);
//					System.out.println("part: " + part.getName());
//					khachHang.setDuongDanAnh(fileName);
//					KhachHangDAO khachHangDAO = new KhachHangDAO();
//					khachHangDAO.updateImage(khachHang);
//					khachHang = khachHangDAO.selectById(khachHang);
//
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//
//			try {
//				request.setCharacterEncoding("UTF-8");
//				response.setCharacterEncoding("UTF-8");
//				response.setContentType("text/html; charset=UTF-8");
//
//				// nhận được đường dẫn đã cấu hình ở file xml
//				String folder = getServletContext().getRealPath("/khachhangView/avatar");
//				System.out.println(folder);
//				File file;
//				int maxFileSize = 5000 * 1024;
//				int maxMemSize = 5000 * 1024;
//				String contentType = request.getContentType();
//
//				if (contentType.indexOf(contentType) >= 0) {
//					DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//					// quy định dung lượng tối da cho 1 file
//				diskFileItemFactory.setSizeThreshold(maxMemSize);
//
//					// tao 1 file
//					ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
//				upload.setSizeMax(maxFileSize);
//
////			List<FileItem> files = upload.parseRequest(request);
//					
//					Collection<Part> files = request.getParts();
//
//					for (Part fileItem : files) {
//						String fileName = fileItem.getName();
//						String path = folder + "/" + fileName;
//						file = new File(path);
//			    		fileItem.write(file);
//						khachHang.setDuongDanAnh(fileName);
//						KhachHangDAO khachHangDAO = new KhachHangDAO();
//						khachHangDAO.update(khachHang);
//						khachHang = khachHangDAO.selectById(khachHang);
//
//					}
//
//				}
//
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

}
