package com.example.JPA02NhanVien.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JPA02NhanVien.model.NhanVien;
import com.example.JPA02NhanVien.model.PhanCong;
import com.example.JPA02NhanVien.repository.INhanVienRepository;
import com.example.JPA02NhanVien.repository.IPhanCongRepository;
import com.example.JPA02NhanVien.services.INhanVienService;

@Service
public class NhanVienService implements INhanVienService {
	@Autowired
	INhanVienRepository nhanVienRepository;
	
	@Autowired
	IPhanCongRepository phanCongRepository;
	
	@Override
	public List<NhanVien> getAll() {
		return nhanVienRepository.findAll();
	}
	
	public boolean kiemTraNhanVien(int nhanvienid) {
		Optional<NhanVien> optional = Optional.empty();
		if(nhanVienRepository.findById(nhanvienid) != optional)
			return true;
		return false;
	}

	@Override
	public void add(NhanVien nhanvien) {	
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		Set<ConstraintViolation<NhanVien>> violations = validator.validate(nhanvien);
		violations.forEach(x -> {System.out.println(x.getMessage());});
		
		if(violations.size() == 0) {
			nhanVienRepository.save(nhanvien);
			
//			for(PhanCong phancong : nhanvien.getListPhanCong())
//				phancong.setNhanVien(nhanvien);
//			phanCongRepository.saveAll(nhanvien.getListPhanCong());
		}
			
	}

	@Override
	public void update(NhanVien nhanvien) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		Set<ConstraintViolation<NhanVien>> violations = validator.validate(nhanvien);
		violations.forEach(x -> {System.out.println(x.getMessage());});
		
		if(violations.size() == 0) {
			NhanVien nhanVienCanSua = nhanVienRepository.getById(nhanvien.getNhanVienID());
			nhanVienCanSua = nhanvien;
			nhanVienRepository.save(nhanVienCanSua);
		}
		
	}

	@Override
	public void delete(NhanVien nhanvien) {
		nhanvien.getListPhanCong().forEach(x -> {
			if(x.getNhanVien() == nhanvien)
				phanCongRepository.delete(x);
		});
		nhanVienRepository.delete(nhanvien);
	}

	@Override
	public NhanVien getOne(int nhanvienid) {
		return nhanVienRepository.getById(nhanvienid);
	}

	@Override
	public double tinhLuong(int nhanvienid) {
		NhanVien nhanvien = nhanVienRepository.getById(nhanvienid);
		int tongGio = 0;
		for(PhanCong pc : phanCongRepository.findAll()) {
			if(pc.getNhanVien().getNhanVienID() == nhanvienid)
				tongGio += pc.getSoGioLam();
		}
		double luong = tongGio * 15 * nhanvien.getHeSoLuong();
		System.out.println(nhanvien.getHoTen() +" luong: "+luong);
		return luong;
	}
 
}
