package model;

public class KichCo {
	private String maKichCo;
	private String tenKichCo;
	
	public KichCo() {
		// TODO Auto-generated constructor stub
	}

	public KichCo(String maKichCo, String tenKichCo) {
		this.maKichCo = maKichCo;
		this.tenKichCo = tenKichCo;
	}

	public String getMaKichCo() {
		return maKichCo;
	}

	public void setMaKichCo(String maKichCo) {
		this.maKichCo = maKichCo;
	}

	public String getTenKichCo() {
		return tenKichCo;
	}

	public void setTenKichCo(String tenKichCo) {
		this.tenKichCo = tenKichCo;
	}

	@Override
	public String toString() {
		return "KichCo [maKichCo=" + maKichCo + ", tenKichCo=" + tenKichCo + "]";
	}
	
	
	
}
