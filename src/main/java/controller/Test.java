package controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.core.FrameworkListener;

import database.DanhMucDAO;
import database.KhachHangDAO;
import database.KhuyenMaiDAO;
import database.KichCoDAO;
import database.MauDAO;
import model.DanhMuc;
import model.KhachHang;
import model.KhuyenMai;
import model.KichCo;
import model.Mau;

public class Test {
	public static void main(String[] args) {

		KhachHangDAO khachHangDAO = new KhachHangDAO();

		KhachHang khachHang = new KhachHang();
		khachHang = khachHangDAO.selectKhachHangByTenDangNhap("dat123");
		System.out.println(khachHang.toString());
	}
}
