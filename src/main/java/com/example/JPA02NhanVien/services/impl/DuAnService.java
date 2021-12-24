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

import com.example.JPA02NhanVien.model.DuAn;
import com.example.JPA02NhanVien.repository.IDuAnRepository;
import com.example.JPA02NhanVien.repository.IPhanCongRepository;
import com.example.JPA02NhanVien.services.IDuAnService;

@Service
public class DuAnService implements IDuAnService {
	@Autowired
	IDuAnRepository duAnRepository;
	
	@Autowired 
	IPhanCongRepository phanCongRepository;
	
	@Override
	public List<DuAn> getAll() {
		return duAnRepository.findAll();
	}
	
	public boolean kiemTraDuAn(int duanid) {
		Optional<DuAn> optional = Optional.empty();
		if(duAnRepository.findById(duanid) != optional)
			return true;
		return false;
	}

	@Override
	public void add(DuAn duan) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		Set<ConstraintViolation<DuAn>> violations = validator.validate(duan);
		violations.forEach(x -> {System.out.println(x.getMessage());});
		
		if(violations.size() == 0) {
			duAnRepository.save(duan);
		}
		
	}

	@Override
	public void update(DuAn duan) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		Set<ConstraintViolation<DuAn>> violations = validator.validate(duan);
		violations.forEach(x -> {System.out.println(x.getMessage());});
		
		if(violations.size() == 0) {
			DuAn duAnCanSua = duAnRepository.getById(duan.getDuAnID());
			duAnCanSua = duan; // alias
			duAnRepository.save(duAnCanSua);
		}
		
	}

	@Override
	public void delete(DuAn duan) {
		duan.getLstPhanCong().forEach(x -> {
			if(x.getDuAn() == duan)
				phanCongRepository.delete(x);
		});
		duAnRepository.delete(duan);
		
	}

	@Override
	public DuAn getOne(int duanid) {
		return duAnRepository.getById(duanid);
	}

}
