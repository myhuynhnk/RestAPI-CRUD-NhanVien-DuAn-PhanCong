package com.example.JPA02NhanVien.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class NhanVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nhanVienID;
	
	@Column
	@Size(max = 20, message = "Ho ten khong duoc qua 20 ky tu")
	private String hoTen;
	
	@Column
	private String soDienThoai;
	
	@Column
	private String diaChi;
	
	@Column
	private String email;
	
	@Column
	private double heSoLuong;
	
	@OneToMany(mappedBy = "nhanVien", fetch = FetchType.EAGER)
	@JsonIgnoreProperties(value ="nhanVien")
	private List<PhanCong> listPhanCong;

	public int getNhanVienID() {
		return nhanVienID;
	}

	public void setNhanVienID(int nhanVienID) {
		this.nhanVienID = nhanVienID;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(double heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

	public List<PhanCong> getListPhanCong() {
		return listPhanCong;
	}

	public void setListPhanCong(List<PhanCong> listPhanCong) {
		this.listPhanCong = listPhanCong;
	}
	
	
}
