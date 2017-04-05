package com.ycszh.global;

public class User {

	private String id;		// 登陆账户ID
	private String name;	// 登陆账户名
	private String ygxm;	// 用户姓名
	private String ygid;	// 用户ID
	private String bmid;	// 部门ID
	private String bmmc;	// 部门名称
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYgxm() {
		return ygxm;
	}
	public void setYgxm(String ygxm) {
		this.ygxm = ygxm;
	}
	public String getYgid() {
		return ygid;
	}
	public void setYgid(String ygid) {
		this.ygid = ygid;
	}
	public String getBmid() {
		return bmid;
	}
	public void setBmid(String bmid) {
		this.bmid = bmid;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public User() {
		super();
	}
	public User(String id, String name, String ygxm, String ygid, String bmid,
			String bmmc) {
		super();
		this.id = id;
		this.name = name;
		this.ygxm = ygxm;
		this.ygid = ygid;
		this.bmid = bmid;
		this.bmmc = bmmc;
	}
	
	
}
