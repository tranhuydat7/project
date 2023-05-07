package model;

import java.util.Objects;

public class SanPham implements Comparable<SanPham> {
	private String maSanPham;
	private String tenSanPham;
	private int giaGoc;
	private int giaBan;
	private int soLuong;
	private String moTa;
	private DanhMuc danhMuc;
	private Mau mauSac;
	private KichCo kichCo;
	private String avatar;
	private String cachSuDung;
	private String chatLieu;
	private boolean isHot = false;

	public SanPham() {
	}

	public SanPham(String maSanPham, String tenSanPham, int giaGoc, int giaBan, int soLuong, String moTa,
			DanhMuc danhMuc, Mau mauSac, KichCo kichCo, String avatar, String cachSuDung, String chatLieu,
			boolean isHot) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.giaGoc = giaGoc;
		this.giaBan = giaBan;
		this.soLuong = soLuong;
		this.moTa = moTa;
		this.danhMuc = danhMuc;
		this.mauSac = mauSac;
		this.kichCo = kichCo;
		this.avatar = avatar;
		this.cachSuDung = cachSuDung;
		this.chatLieu = chatLieu;
		this.isHot = isHot;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public int getGiaGoc() {
		return giaGoc;
	}

	public void setGiaGoc(int giaGoc) {
		this.giaGoc = giaGoc;
	}

	public int getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(int giaBan) {
		this.giaBan = giaBan;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public DanhMuc getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(DanhMuc danhMuc) {
		this.danhMuc = danhMuc;
	}

	public Mau getMauSac() {
		return mauSac;
	}

	public void setMauSac(Mau mauSac) {
		this.mauSac = mauSac;
	}

	public KichCo getKichCo() {
		return kichCo;
	}

	public void setKichCo(KichCo kichCo) {
		this.kichCo = kichCo;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCachSuDung() {
		return cachSuDung;
	}

	public void setCachSuDung(String cachSuDung) {
		this.cachSuDung = cachSuDung;
	}

	public String getChatLieu() {
		return chatLieu;
	}

	public void setChatLieu(String chatLieu) {
		this.chatLieu = chatLieu;
	}
	
	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

	@Override
	public String toString() {
		return "SanPham [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", giaGoc=" + giaGoc + ", giaBan="
				+ giaBan + ", soLuong=" + soLuong + ", moTa=" + moTa + ", danhMuc=" + danhMuc + ", mauSac=" + mauSac
				+ ", kichCo=" + kichCo + ", avatar=" + avatar + ", cachSuDung=" + cachSuDung + ", chatLieu=" + chatLieu
				+ "]";
	}

	@Override
	public int compareTo(SanPham o) {
		return Integer.parseInt(this.maSanPham) - Integer.parseInt(o.maSanPham);
	}

}
