package com.example.JPA02NhanVien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JPA02NhanVien.model.NhanVien;

@Repository
public interface INhanVienRepository extends JpaRepository<NhanVien, Integer> {

}
