package com.example.JPA02NhanVien.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.JPA02NhanVien.model.NhanVien;
import com.example.JPA02NhanVien.services.IDuAnService;
import com.example.JPA02NhanVien.services.INhanVienService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/nhanvien")
public class NhanVienController {
	@Autowired
	INhanVienService nhanVienService;
	
	@Autowired
	IDuAnService duAnService;


	@GetMapping(value="/hienthi", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<NhanVien> hienThi(){
		return nhanVienService.getAll();
	}
	
	@PostMapping(value="/them", produces = MediaType.APPLICATION_JSON_VALUE)
	public NhanVien themNhanVien(@RequestBody String nhanvien) {
		Gson gson = new Gson();
		NhanVien nhanVienMoi = gson.fromJson(nhanvien, NhanVien.class);
		nhanVienService.add(nhanVienMoi);
		return nhanVienMoi;
	}
	
	
	
	@PutMapping(value = "/sua", produces = MediaType.APPLICATION_JSON_VALUE)
	public String suaNhanVien(@RequestBody String nhanvien) {
		Gson gson = new Gson();
		NhanVien nhanVien = gson.fromJson(nhanvien, NhanVien.class);
		if(!nhanVienService.kiemTraNhanVien(nhanVien.getNhanVienID()))
			return "Nhân viên không tồn tại";
		else
			nhanVienService.update(nhanVien);
		return "Cập nhật thành công nhân viên có mã số "+nhanVien.getNhanVienID();
	}
	
	@GetMapping(value="/tinhluong")
	public String tinhLuong(@RequestParam int nhanvienid) {
		Locale localVN = new Locale("vi", "VN");
		NumberFormat currentFormat = NumberFormat.getCurrencyInstance(localVN);
		if(!nhanVienService.kiemTraNhanVien(nhanvienid))
			return "Nhân viên không tồn tại";
		double luong = nhanVienService.tinhLuong(nhanvienid);
		return "Nhân viên mã số "+nhanvienid +" có lương " +currentFormat.format(luong);
	}
	
	@DeleteMapping(value="/xoa")
	public String xoaNhanVien(@RequestParam int nhanvienid) {
		if(!nhanVienService.kiemTraNhanVien(nhanvienid))
			return "Nhân viên không tồn tại";
		else
			nhanVienService.delete(nhanVienService.getOne(nhanvienid));
		return "Nhân viên có mã số"+ nhanvienid +" đã được xóa ";
	}
	

}
