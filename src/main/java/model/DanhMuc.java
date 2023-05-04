package model;

public class DanhMuc {

	private String maDanhMuc;
	private String tenDanhMuc;
	private DanhMuc danhMucCha;

	public DanhMuc() {
		// TODO Auto-generated constructor stub
	}

	public DanhMuc(String maDanhMuc, String tenDanhMuc, DanhMuc danhMucCha) {
		this.maDanhMuc = maDanhMuc;
		this.tenDanhMuc = tenDanhMuc;
		this.danhMucCha = danhMucCha;
	}

	public String getMaDanhMuc() {
		return maDanhMuc;
	}

	public void setMaDanhMuc(String maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	public DanhMuc getDanhMucCha() {
		return danhMucCha;
	}

	public void setDanhMucCha(DanhMuc danhMucCha) {
		this.danhMucCha = danhMucCha;
	}

	@Override
	public String toString() {
		return "DanhMuc [maDanhMuc=" + maDanhMuc + ", tenDanhMuc=" + tenDanhMuc + ", danhMucCha=" + danhMucCha + "]";
	}

	
}
