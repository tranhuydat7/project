package model;

public class ChiTietDonHang {
	private String maChiTietDonHang;
	private DonHang donHang;
	private SanPham sanPham;
	private int soLuong;
	private double tongTien;
	private PhuongThucVanChuyen phuongThucVanChuyen;
	
	public ChiTietDonHang() {
		// TODO Auto-generated constructor stub
	}

	public ChiTietDonHang(String maChiTietDonHang, DonHang donHang, SanPham sanPham, int soLuong, double tongTien,
			PhuongThucVanChuyen phuongThucVanChuyen) {
		super();
		this.maChiTietDonHang = maChiTietDonHang;
		this.donHang = donHang;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
		this.phuongThucVanChuyen = phuongThucVanChuyen;
	}

	public String getMaChiTietDonHang() {
		return maChiTietDonHang;
	}

	public void setMaChiTietDonHang(String maChiTietDonHang) {
		this.maChiTietDonHang = maChiTietDonHang;
	}

	public DonHang getDonHang() {
		return donHang;
	}

	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public PhuongThucVanChuyen getPhuongThucVanChuyen() {
		return phuongThucVanChuyen;
	}

	public void setPhuongThucVanChuyen(PhuongThucVanChuyen phuongThucVanChuyen) {
		this.phuongThucVanChuyen = phuongThucVanChuyen;
	}
	
}
