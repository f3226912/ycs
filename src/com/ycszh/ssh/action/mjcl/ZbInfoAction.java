package com.ycszh.ssh.action.mjcl;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.service.mjcl.ZbInfoService;

public class ZbInfoAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(ZbInfoAction.class);
	private ZbInfoService zbInfoService;

	public String getZbInfoData() throws Exception {
		zbInfoService.getZbInfoData(request);
		return "zbInfo";
	}

	public static Logger getLogger() {
		return logger;
	}

	public ZbInfoService getZbInfoService() {
		return zbInfoService;
	}

	public void setZbInfoService(ZbInfoService zbInfoService) {
		this.zbInfoService = zbInfoService;
	}

}
