package com.example.JPA02NhanVien.services.impl;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JPA02NhanVien.model.PhanCong;
import com.example.JPA02NhanVien.repository.IPhanCongRepository;
import com.example.JPA02NhanVien.services.IPhanCongService;

@Service
public class PhanCongService implements IPhanCongService {
	@Autowired
	IPhanCongRepository phanCongRepository;
	
	@Override
	public List<PhanCong> getAll() {
		return phanCongRepository.findAll();
	}

	@Override
	public void add(PhanCong phancong) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		Set<ConstraintViolation<PhanCong>> violations = validator.validate(phancong);
		violations.forEach(x -> {x.getMessage();});
		
		if(violations.size() == 0)
			phanCongRepository.save(phancong);
	}

}
