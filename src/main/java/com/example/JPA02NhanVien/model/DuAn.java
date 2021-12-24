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
public class DuAn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int duAnID;
	
	@Column
	@Size(max = 20, message = "Ten du an khong duoc qua 20 ky tu")
	private String tenDuAn;
	
	@Column
	private String moTa;
	
	@Column
	private String ghiChu;
	
	@OneToMany(mappedBy = "duAn", fetch = FetchType.EAGER)
	@JsonIgnoreProperties(value ="duAn")
	private List<PhanCong> lstPhanCong;

	public int getDuAnID() {
		return duAnID;
	}

	public void setDuAnID(int duAnID) {
		this.duAnID = duAnID;
	}

	public String getTenDuAn() {
		return tenDuAn;
	}

	public void setTenDuAn(String tenDuAn) {
		this.tenDuAn = tenDuAn;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public List<PhanCong> getLstPhanCong() {
		return lstPhanCong;
	}

	public void setLstPhanCong(List<PhanCong> lstPhanCong) {
		this.lstPhanCong = lstPhanCong;
	}
	
	
	
}
