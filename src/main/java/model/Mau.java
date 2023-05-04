package model;

public class Mau {
	private String maMau;
	private String tenMau;
	
	public Mau() {
		// TODO Auto-generated constructor stub
	}
	
	public Mau(String maMau, String tenMau) {
		this.maMau = maMau;
		this.tenMau = tenMau;
	}

	public String getMaMau() {
		return maMau;
	}

	public void setMaMau(String maMau) {
		this.maMau = maMau;
	}

	public String getTenMau() {
		return tenMau;
	}

	public void setTenMau(String tenMau) {
		this.tenMau = tenMau;
	}

	@Override
	public String toString() {
		return "Mau [maMau=" + maMau + ", tenMau=" + tenMau + "]";
	}
	
	
	
}
