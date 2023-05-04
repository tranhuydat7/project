package model;

import java.sql.Date;

public class KhuyenMai {
	private String maKhuyenMai;
	private String tenKhuyenMai;
	private int giamGiaTu;
	private int giamGiaMax;
	private int giamPhanTram;
	private Date ngayBatDau;
	private Date ngayKetThuc;

	public KhuyenMai() {
		// TODO Auto-generated constructor stub
	}

	public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, int giamGiaTu, int giamGiaMax, int giamPhanTram,
			Date ngayBatDau, Date ngayKetThuc) {
		this.maKhuyenMai = maKhuyenMai;
		this.tenKhuyenMai = tenKhuyenMai;
		this.giamGiaTu = giamGiaTu;
		this.giamGiaMax = giamGiaMax;
		this.giamPhanTram = giamPhanTram;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	public int getGiamGiaTu() {
		return giamGiaTu;
	}

	public void setGiamGiaTu(int giamGiaTu) {
		this.giamGiaTu = giamGiaTu;
	}

	public int getGiamGiaMax() {
		return giamGiaMax;
	}

	public void setGiamGiaMax(int giamGiaMax) {
		this.giamGiaMax = giamGiaMax;
	}

	public int getGiamPhanTram() {
		return giamPhanTram;
	}

	public void setGiamPhanTram(int giamPhanTram) {
		this.giamPhanTram = giamPhanTram;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

}
