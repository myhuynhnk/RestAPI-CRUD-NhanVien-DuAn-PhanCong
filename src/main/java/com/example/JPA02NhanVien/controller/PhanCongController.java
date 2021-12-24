package com.example.JPA02NhanVien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JPA02NhanVien.model.PhanCong;
import com.example.JPA02NhanVien.services.IDuAnService;
import com.example.JPA02NhanVien.services.INhanVienService;
import com.example.JPA02NhanVien.services.IPhanCongService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/phancong")
public class PhanCongController {
	@Autowired 
	IPhanCongService phanCongService;
	
	@Autowired
	IDuAnService duAnService;
	
	@Autowired 
	INhanVienService nhanVienService;
	
	@GetMapping(value="/hienthi", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PhanCong> hienThiPhanCong(){
		return phanCongService.getAll();
	}
	
	@PostMapping(value="/them", produces = MediaType.APPLICATION_JSON_VALUE)
	public String themPhanCong(@RequestBody String phancong) {
		Gson gson = new Gson();
		PhanCong phanCongMoi = gson.fromJson(phancong, PhanCong.class);
		
		if(!nhanVienService.kiemTraNhanVien(phanCongMoi.getNhanVien().getNhanVienID()))
			return "Nhân viên không tồn tại ";
		else if(!duAnService.kiemTraDuAn(phanCongMoi.getDuAn().getDuAnID()))
			return "Dự án không tồn tại";
		else
			phanCongService.add(phanCongMoi);
		return "Thêm thành công "+phanCongMoi.getPhanCongID();
	}
	
	
}
