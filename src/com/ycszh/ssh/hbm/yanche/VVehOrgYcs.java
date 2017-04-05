package com.ycszh.ssh.hbm.yanche;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * VVehOrgYcs entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class VVehOrgYcs extends BaseObject {

	// Fields

	private String orgId;
	private String upOrg;
	private String orgName;
	private String plugOrgId;
	private String origin;

	// Constructors

	/** default constructor */
	public VVehOrgYcs() {
	}

	/** minimal constructor */
	public VVehOrgYcs(String orgId, String orgName) {
		this.orgId = orgId;
		this.orgName = orgName;
	}

	/** full constructor */
	public VVehOrgYcs(String orgId, String upOrg, String orgName,
			String plugOrgId, String origin) {
		this.orgId = orgId;
		this.upOrg = upOrg;
		this.orgName = orgName;
		this.plugOrgId = plugOrgId;
		this.origin = origin;
	}

	// Property accessors

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUpOrg() {
		return this.upOrg;
	}

	public void setUpOrg(String upOrg) {
		this.upOrg = upOrg;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPlugOrgId() {
		return this.plugOrgId;
	}

	public void setPlugOrgId(String plugOrgId) {
		this.plugOrgId = plugOrgId;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}