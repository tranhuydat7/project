package model;

public class PhuongThucVanChuyen {
	private String maPhuongThuc;
	private String tenPhuongThuc;
	
	public PhuongThucVanChuyen() {
		// TODO Auto-generated constructor stub
	}

	public PhuongThucVanChuyen(String maPhuongThuc, String tenPhuongThuc) {
		this.maPhuongThuc = maPhuongThuc;
		this.tenPhuongThuc = tenPhuongThuc;
	}

	public String getMaPhuongThuc() {
		return maPhuongThuc;
	}

	public void setMaPhuongThuc(String maPhuongThuc) {
		this.maPhuongThuc = maPhuongThuc;
	}

	public String getTenPhuongThuc() {
		return tenPhuongThuc;
	}

	public void setTenPhuongThuc(String tenPhuongThuc) {
		this.tenPhuongThuc = tenPhuongThuc;
	}
	
	
	
}
