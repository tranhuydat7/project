package model;

import java.sql.Date;

public class KhachHang {
	private int maKhachHang;
	private String tenDangNhap;
	private String password;
	private String hoVaTen;
	private String diaChi;
	private String gioiTinh;
	private String diaChiNhanHang;
	private String diaChiMuaHang;
	private Date ngaySinh;
	private String soDienThoai;
	private String email;
	private boolean dangKiNhanTin;

	public KhachHang() {
		// TODO Auto-generated constructor stub
	}

	public KhachHang(String tenDangNhap, String password, String hoVaTen, String diaChi, String gioiTinh,
			String diaChiNhanHang, String diaChiMuaHang, Date ngaySinh, String soDienThoai, String email,
			boolean dangKiNhanTin) {
		this.tenDangNhap = tenDangNhap;
		this.password = password;
		this.hoVaTen = hoVaTen;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.diaChiNhanHang = diaChiNhanHang;
		this.diaChiMuaHang = diaChiMuaHang;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.dangKiNhanTin = dangKiNhanTin;
	}

	public KhachHang(int maKhachHang, String tenDangNhap, String password, String hoVaTen, String diaChi,
			String gioiTinh, String diaChiNhanHang, String diaChiMuaHang, Date ngaySinh, String soDienThoai,
			String email, boolean dangKiNhanTin) {
		this.maKhachHang = maKhachHang;
		this.tenDangNhap = tenDangNhap;
		this.password = password;
		this.hoVaTen = hoVaTen;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.diaChiNhanHang = diaChiNhanHang;
		this.diaChiMuaHang = diaChiMuaHang;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.dangKiNhanTin = dangKiNhanTin;
	}

	public int getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChiNhanHang() {
		return diaChiNhanHang;
	}

	public void setDiaChiNhanHang(String diaChiNhanHang) {
		this.diaChiNhanHang = diaChiNhanHang;
	}

	public String getDiaChiMuaHang() {
		return diaChiMuaHang;
	}

	public void setDiaChiMuaHang(String diaChiMuaHang) {
		this.diaChiMuaHang = diaChiMuaHang;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDangKiNhanTin() {
		return dangKiNhanTin;
	}

	public void setDangKiNhanTin(boolean dangKiNhanTin) {
		this.dangKiNhanTin = dangKiNhanTin;
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", tenDangNhap=" + tenDangNhap + ", password=" + password
				+ ", hoVaTen=" + hoVaTen + ", diaChi=" + diaChi + ", gioiTinh=" + gioiTinh + ", diaChiNhanHang="
				+ diaChiNhanHang + ", diaChiMuaHang=" + diaChiMuaHang + ", ngaySinh=" + ngaySinh + ", soDienThoai="
				+ soDienThoai + ", email=" + email + ", dangKiNhanTin=" + dangKiNhanTin + "]";
	}

	
}
