package com.example.JPA02NhanVien.services;

import java.util.List;

import com.example.JPA02NhanVien.model.DuAn;

public interface IDuAnService {
	List<DuAn> getAll();
	void add(DuAn duan);
	void update(DuAn duan);
	void delete(DuAn duan);
	DuAn getOne(int duanid);
	boolean kiemTraDuAn(int duanid);
}
