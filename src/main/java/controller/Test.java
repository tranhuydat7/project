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
import database.SanPhamDAO;
import model.DanhMuc;
import model.GioHang;
import model.KhachHang;
import model.KhuyenMai;
import model.KichCo;
import model.Mau;
import model.SanPham;

public class Test {
	public static void main(String[] args) {
		SanPhamDAO sanPhamDAO = new SanPhamDAO();

//		List<SanPham> sanPhams = sanPhamDAO.getSanPhamByDanhMucNam();
//		System.out.println(sanPhams.toString());

	}
}
