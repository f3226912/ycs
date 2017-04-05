package com.ycszh.ssh.hbm.yanche;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * VVehUserYcs entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class VVehUserYcs extends BaseObject {

	// Fields

	private String userId;
	private String sex;
	private String userPosition;
	private String extFieldValue;
	private String orgId;
	private String userName;
	private String pelaPhone;
	private String userOrder;
	private String loginId;
	private String plugUserId;
	private String areano;
	private String areaname;
	private String iscall;
	private String userrole;
	private String origin;
	private String orderNum;
	private String code;
	private String userCode;

	// Constructors

	/** default constructor */
	public VVehUserYcs() {
	}

	/** minimal constructor */
	public VVehUserYcs(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public VVehUserYcs(String userId, String sex, String userPosition,
			String extFieldValue, String orgId, String userName,
			String pelaPhone, String userOrder, String loginId,
			String plugUserId, String areano, String areaname, String iscall,
			String userrole, String origin, String orderNum, String code,
			String userCode) {
		this.userId = userId;
		this.sex = sex;
		this.userPosition = userPosition;
		this.extFieldValue = extFieldValue;
		this.orgId = orgId;
		this.userName = userName;
		this.pelaPhone = pelaPhone;
		this.userOrder = userOrder;
		this.loginId = loginId;
		this.plugUserId = plugUserId;
		this.areano = areano;
		this.areaname = areaname;
		this.iscall = iscall;
		this.userrole = userrole;
		this.origin = origin;
		this.orderNum = orderNum;
		this.code = code;
		this.userCode = userCode;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserPosition() {
		return this.userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public String getExtFieldValue() {
		return this.extFieldValue;
	}

	public void setExtFieldValue(String extFieldValue) {
		this.extFieldValue = extFieldValue;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPelaPhone() {
		return this.pelaPhone;
	}

	public void setPelaPhone(String pelaPhone) {
		this.pelaPhone = pelaPhone;
	}

	public String getUserOrder() {
		return this.userOrder;
	}

	public void setUserOrder(String userOrder) {
		this.userOrder = userOrder;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPlugUserId() {
		return this.plugUserId;
	}

	public void setPlugUserId(String plugUserId) {
		this.plugUserId = plugUserId;
	}

	public String getAreano() {
		return this.areano;
	}

	public void setAreano(String areano) {
		this.areano = areano;
	}

	public String getAreaname() {
		return this.areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getIscall() {
		return this.iscall;
	}

	public void setIscall(String iscall) {
		this.iscall = iscall;
	}

	public String getUserrole() {
		return this.userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}