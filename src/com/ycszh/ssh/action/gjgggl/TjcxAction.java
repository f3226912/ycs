package com.ycszh.ssh.action.gjgggl;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.service.gjgggl.TjcxService;
import common.Logger;

/**
 * 广告证信息管理管理
 * 
 * @author ldy
 * 
 */
public class TjcxAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(TjcxAction.class);
	private TjcxService tjcxService;

	// 按流水号查询广告证信息
	public String getLscx() throws Exception {
		tjcxService.getLscx(request);
		return "lscx";
	}

	// 按流水号查询详细信息
	public String getLscx_shwoDetail() throws Exception {
		tjcxService.getLscx_shwoDetail(request);
		return "showDetail";
	}

	public String getBusGgjgclsbByLsh() throws Exception {
		tjcxService.getBusGgjgclsbByLsh(request);
		return "lscx_detail";
	}

	// 广告证档案查询
	public String getGgzdacx() throws Exception {
		tjcxService.getGgzdacx(request);
		return "ggzdacx";
	}

	// 审核情况统计
	public String getShqktj() throws Exception {
		tjcxService.getShqktj(request);
		return "shqktj";
	}

	// 审核情况统计_详细
	public String getShqktj_detail() throws Exception {
		tjcxService.getShqktj_detail(request);
		return "shqktj_detail";
	}

	// 证件发放情况统计
	public String getZjffqktj() throws Exception {
		tjcxService.getZjffqktj(request);
		return "zjffqktj";
	}

	// 证件发放情况统计_详细
	public String getZjffqktj_detail() throws Exception {
		tjcxService.getZjffqktj_detail(request);
		return "zjffqktj_detail";
	}

	public static Logger getLogger() {
		return logger;
	}

	public TjcxService getTjcxService() {
		return tjcxService;
	}

	public void setTjcxService(TjcxService tjcxService) {
		this.tjcxService = tjcxService;
	}

}
