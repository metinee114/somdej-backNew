package com.it.Entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_rent")
public class RentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rentId;
	private String rentStart;
	private String rentEnd;
	private Integer rentInsurance;
	private String rentTotalprice;
	//private String rentOther;
	private String rentLi;
	private String rentWa;
	private String stetus;
	private String cardTime;
	private String cardAddress;
	private Integer userId;
	private String roomId;
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getRentId() {
		return rentId;
	}
	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}
	public String getRentStart() {
		return rentStart;
	}
	public void setRentStart(String rentStart) {
		this.rentStart = rentStart;
	}
	public String getRentEnd() {
		return rentEnd;
	}
	public void setRentEnd(String rentEnd) {
		this.rentEnd = rentEnd;
	}
	public Integer getRentInsurance() {
		return rentInsurance;
	}
	public void setRentInsurance(Integer rentInsurance) {
		this.rentInsurance = rentInsurance;
	}
	public String getRentTotalprice() {
		return rentTotalprice;
	}
	public void setRentTotalprice(String rentTotalprice) {
		this.rentTotalprice = rentTotalprice;
	}
	public String getRentLi() {
		return rentLi;
	}
	public void setRentLi(String rentLi) {
		this.rentLi = rentLi;
	}
	public String getRentWa() {
		return rentWa;
	}
	public void setRentWa(String rentWa) {
		this.rentWa = rentWa;
	}
	public String getStetus() {
		return stetus;
	}
	public void setStetus(String stetus) {
		this.stetus = stetus;
	}
	public String getCardTime() {
		return cardTime;
	}
	public void setCardTime(String cardTime) {
		this.cardTime = cardTime;
	}
	public String getCardAddress() {
		return cardAddress;
	}
	public void setCardAddress(String cardAddress) {
		this.cardAddress = cardAddress;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	

	
}
