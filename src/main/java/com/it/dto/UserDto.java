package com.it.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class UserDto {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserTitle() {
		return userTitle;
	}
	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserUserName() {
		return userUserName;
	}
	public void setUserUserName(String userUserName) {
		this.userUserName = userUserName;
	}
	public String getUserLasname() {
		return userLasname;
	}
	public void setUserLasname(String userLasname) {
		this.userLasname = userLasname;
	}
	public String getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserIdcard() {
		return userIdcard;
	}
	public void setUserIdcard(String userIdcard) {
		this.userIdcard = userIdcard;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getRoleId() {
		return RoleId;
	}
	public void setRoleId(String roleId) {
		RoleId = roleId;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Integer getDistrictId() {
		return DistrictId;
	}
	public void setDistrictId(Integer districtId) {
		DistrictId = districtId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	private String userTitle;
	private String userName;
	private String userPassword;
	private String userUserName;
	private String userLasname;
	private String userBirthday;
	private String userIdcard;
	private String userPhone;
	private String age;
	private String userAddress;
	private String userEmail;
	private String RoleId;
	private String zipCode;
	private Integer DistrictId;
	private String roomId;
	private String cardTime;
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
	private String cardAddress;
	
	
	
	
}
