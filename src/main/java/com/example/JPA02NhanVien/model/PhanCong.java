package com.example.JPA02NhanVien.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class PhanCong {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int phanCongID;
	
	@Column
	@Min(value = 0, message = "So gio lam phai la so nguyen duong")
	private int soGioLam;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="nhanVienID", foreignKey = @ForeignKey(name ="fk_nhanvien_phancong"))
	@JsonIgnoreProperties(value ="listPhanCong")
	private NhanVien nhanVien;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="duAnID", foreignKey = @ForeignKey(name ="fk_duan_phancong"))
	@JsonIgnoreProperties(value ="lstPhanCong")
	private DuAn duAn;

	public int getPhanCongID() {
		return phanCongID;
	}

	public void setPhanCongID(int phanCongID) {
		this.phanCongID = phanCongID;
	}

	public int getSoGioLam() {
		return soGioLam;
	}

	public void setSoGioLam(int soGioLam) {
		this.soGioLam = soGioLam;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public DuAn getDuAn() {
		return duAn;
	}

	public void setDuAn(DuAn duAn) {
		this.duAn = duAn;
	}
	
	
	
	

}

