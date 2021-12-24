package com.example.JPA02NhanVien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JPA02NhanVien.model.DuAn;
import com.example.JPA02NhanVien.services.IDuAnService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/duan")
public class DuAnController {
	@Autowired
	IDuAnService duAnService;
	
	@GetMapping(value = "/hienthiduan", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DuAn> hienThiDuAn(){
		return duAnService.getAll();
	}
		
		
	@PostMapping(value ="/themduan", produces = MediaType.APPLICATION_JSON_VALUE)
	public DuAn themDuAn(@RequestBody String duan) {
		Gson gson = new Gson();
		DuAn duAnMoi = gson.fromJson(duan, DuAn.class);
		
		duAnService.add(duAnMoi);
		return duAnMoi;

			
	}
	
	@PutMapping(value = "/suaduan", produces = MediaType.APPLICATION_JSON_VALUE)
	public String suaDuAn(@RequestBody String duan) {
		Gson gson = new Gson();
		DuAn duAn = gson.fromJson(duan, DuAn.class);
		if(!duAnService.kiemTraDuAn(duAn.getDuAnID()))
			return "Dự án không tồn tại";
		else
			duAnService.update(duAn);
		return "Dự án số "+duAn.getDuAnID()+ "đã được cập nhật";
	}
	
	@DeleteMapping(value = "/xoaduan/{duanid}")
	public String xoaDuAn(@PathVariable int duanid) {
		if(!duAnService.kiemTraDuAn(duanid))
			return "Dự án không tồn tại";
		else
			duAnService.delete(duAnService.getOne(duanid));
		return "Dự án số "+duanid+" đã xóa thành công ";
		
	}
}
