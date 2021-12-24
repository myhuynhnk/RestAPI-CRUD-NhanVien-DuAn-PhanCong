package com.example.JPA02NhanVien.services;

import java.util.List;

import com.example.JPA02NhanVien.model.NhanVien;

public interface INhanVienService {
	List<NhanVien> getAll();
	void add(NhanVien nhanvien);
	void update(NhanVien nhanvien);
	void delete(NhanVien nhanvien);
	NhanVien getOne(int nhanvienid);
	double tinhLuong(int nhanvienid);
	boolean kiemTraNhanVien(int nhanvienid);
}
