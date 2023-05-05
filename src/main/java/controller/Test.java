package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.catalina.core.FrameworkListener;

import database.DanhMucDAO;
import database.KhachHangDAO;
import database.KhuyenMaiDAO;
import database.KichCoDAO;
import database.MauDAO;
import model.DanhMuc;
import model.GioHang;
import model.KhachHang;
import model.KhuyenMai;
import model.KichCo;
import model.Mau;

public class Test {
	public static void main(String[] args) {

		GioHang gioHang = new GioHang();
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

		map.put(4, 5);
		map.put(1, 1);
		map.put(2, 19);
		map.put(2, 10);

//		int string = map.get(2);
//		System.out.println(string);
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());

		}
		System.out.println("bollean: "+map.containsKey(2));

	}
}
