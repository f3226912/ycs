package com.ycszh.ssh.hbm.veh;

import com.ycszh.ssh.hbm.BaseObject;

@SuppressWarnings("serial")
public class TemporaryLicense extends BaseObject {
	
	private String bookNumber;  //预约号码
	private String idNumber;   //身份证号码
	private String name;   //车主姓名
	private String address; //居住地址
	private String phoneNumber; //电话号码
	private String chineseBrand; //中文品牌
	private String vehicleType;  //车辆型号
	private String carType; //车辆类型
	private String chassisNumber; //车架号后四位
	private String engineNumber; //发动机号
	private String passengerNumber; //载客人数
	private String appointmentDate; //预约日期
	
	public String getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getChineseBrand() {
		return chineseBrand;
	}
	public void setChineseBrand(String chineseBrand) {
		this.chineseBrand = chineseBrand;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getChassisNumber() {
		return chassisNumber;
	}
	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	public String getPassengerNumber() {
		return passengerNumber;
	}
	public void setPassengerNumber(String passengerNumber) {
		this.passengerNumber = passengerNumber;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
}