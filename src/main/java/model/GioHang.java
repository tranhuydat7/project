package model;

import java.util.TreeMap;

public class GioHang {

	private TreeMap<SanPham, Integer> lists;
	private long cartID;

	public GioHang() {
		lists = new TreeMap<>();
		cartID = System.currentTimeMillis();
	}

	public GioHang(TreeMap<SanPham, Integer> lists, long cartID) {
		this.lists = lists;
		this.cartID = cartID;
	}

	public TreeMap<SanPham, Integer> getLists() {
		return lists;
	}

	public void setLists(TreeMap<SanPham, Integer> lists) {
		this.lists = lists;
	}

	public long getCartID() {
		return cartID;
	}

	public void setCartID(long cartID) {
		this.cartID = cartID;
	}

	public boolean insertToCart(SanPham sanPham, int soLuong) {
		boolean result = false;

		boolean sp = lists.containsKey(sanPham);
		if (sp) {
			int sl = lists.get(sanPham);
			soLuong += sl;
			lists.put(sanPham, soLuong);
			result = true;
		} else {
			lists.put(sanPham, soLuong);
			result = true;
		}
		return result;
	}

	@Override
	public String toString() {
		return "GioHang [lists=" + lists + ", cartID=" + cartID + "]";
	}

	
}
