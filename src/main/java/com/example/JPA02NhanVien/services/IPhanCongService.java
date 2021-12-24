package com.example.JPA02NhanVien.services;

import java.util.List;

import com.example.JPA02NhanVien.model.PhanCong;

public interface IPhanCongService {
	List<PhanCong> getAll();
	void add(PhanCong phancong);
}
