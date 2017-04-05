package com.ycszh.ssh.service.jyzwcj.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.hbm.jyzwcj.PoliceFingerInfo;
import com.ycszh.ssh.hbm.jyzwcj.PoliceFingerInfoLog;
import com.ycszh.ssh.service.jyzwcj.JyzwcjService;

public class JyzwcjServiceImpl implements JyzwcjService{

	private DefaultDao defaultDao;
	
	private final static Logger log = Logger.getLogger(JyzwcjServiceImpl.class);
	
	/**
	 * 部门信息查询
	 */
	@SuppressWarnings("unchecked")
	public List bmjyList(HttpServletRequest request, int currentPage)throws Exception {
		StringBuffer sqlbm = new StringBuffer("select * from v_frm_department t where 1=1 "); 			//视图部门
		StringBuffer sqlyh = new StringBuffer("select v.yhdh,v.xm,v.bmmc,p.zt from v_frm_sysuser v left join POLICE_FINGER_INFO p on v.yhdh=p.jh where 1=1 ");		//视图用户
		//StringBuffer sqlyh = new StringBuffer("select p.jh,p.xm,v.glbm,v.bmmc,p.zt from POLICE_FINGER_INFO p,v_frm_department v where 1=1 and p.bmbh=v.glbm ");  //用户表+部门视图的部门名称
		
		String xms = request.getParameter("xms")==null?"":request.getParameter("xms").trim();
		String zhs = request.getParameter("zhs")==null?"":request.getParameter("zhs").trim();
		String glbms = request.getParameter("glbms")==null?"":request.getParameter("glbms").trim();
		String cxzt = request.getParameter("cxzt")==null?"":request.getParameter("cxzt").trim();
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		String curi = request.getRequestURI();
		
		List userList = new ArrayList();
		if(cxzt.equals("1")){
			// 用户帐号
			if (zhs != null && !zhs.equals("")) {
				zhs = zhs.replaceAll(" ", "");
				zhs = zhs.replaceAll("'", "");
				zhs = zhs.replaceAll("\"", "");
				zhs = zhs.replaceAll("，", ",");
				sqlyh.append(" and v.yhdh like '%" + zhs + "%' ");
				request.setAttribute("zhs", zhs);
			}
			// 用户名称
			if (xms != null && !xms.equals("")) {
				xms = xms.replaceAll(" ", "");
				xms = xms.replaceAll("'", "");
				xms = xms.replaceAll("\"", "");
				xms = xms.replaceAll("，", ",");
				sqlyh.append(" and v.xm like '%" + xms + "%' ");
				request.setAttribute("xms", xms);
			}
		}else{
			// 管理部门
			if (glbms != null && !glbms.equals("")) {
				glbms = glbms.replaceAll(" ", "");
				glbms = glbms.replaceAll("'", "");
				glbms = glbms.replaceAll("\"", "");
				glbms = glbms.replaceAll("，", ",");
				sqlyh.append(" and v.glbm = '" + glbms + "' ");
				request.setAttribute("glbms", glbms);
			}
		}
		sqlyh.append(" order by glbm");
		request.setAttribute("zhs", zhs);
		request.setAttribute("xms", xms);
		
		int size = 0;
		List lists = new ArrayList();
		
		//获取视图的用户
		List yhsize = this.defaultDao.findSQL(sqlyh.toString());
		
		if(yhsize != null && !yhsize.equals("")){
			size = yhsize.size();
		}
		if(size > 0){
			lists = this.defaultDao.findSQLByPage(sqlyh.toString(), offset, pageSize);
		}
		
		//部门列表
		List listBbm = defaultDao.findSQL(sqlbm.toString());
		StringBuffer node = new StringBuffer("[");
		for (int i = 0; i < listBbm.size(); i++) {
			Object[] obj = (Object[]) listBbm.get(i);
			if(obj[0].equals("440300000000")){
				node.append(" {id:"+obj[0]+", pId:"+obj[14]+", name:'"+obj[1]+"',open:true,isParent:true }, ");
			}else{
				node.append(" {id:"+obj[0]+", pId:"+obj[14]+", name:'"+obj[1]+"'}, ");
			}
		}
		node.append(" ];");
		
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("userList", lists);
		request.setAttribute("node", node);
		request.setAttribute("userid", yhsize.get(0));
		
		return userList;
	}

	/**
	 * 获取用户/警员信息
	 */
	@SuppressWarnings("unchecked")
	public List getJYxx(HttpServletRequest request) throws Exception {
		String userId = request.getParameter("userid");
		if(null != userId && !"".equals(userId)){
			List listJy = this.defaultDao.findSQL("select v.yhdh,v.xm,v.glbm,v.bmmc,p.zt,p.czr,p.czr_xm,p.czr_bm,to_char(p.czsj, 'yyyy-MM-dd hh24:mi:ss') from v_frm_sysuser v left join POLICE_FINGER_INFO p on v.yhdh=p.jh where 1=1 and v.yhdh="+"'"+userId+"'");
			request.setAttribute("listJY", listJy);
			return listJy;
		}
		return null;
	}
	
	/**
	 * 将指纹采集数据添加到指定的采集表及其日志表
	 */
	public Integer insertFinger(PoliceFingerInfo p, HttpServletRequest request) throws Exception {
		// 获取采集页面信息
		String jh = request.getParameter("jh");
		String xm = request.getParameter("xm");
		String bmbh = request.getParameter("glbm");
		String zw1 = request.getParameter("fingerOne");
		String zw2 = request.getParameter("fingerTwo");
		String zw3 = request.getParameter("fingerThree");
		String zw4 = request.getParameter("fingerFour");
		String isZT = request.getParameter("zt");
		String zt = "1";

		// 获得当前操作用户信息
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);			
		String czrbh = user.getName();
		String czrbm = user.getBmmc();
		String czrxm = user.getYgxm();
		String czrkjbm = request.getParameter("czrkjbm");
		Date sj = new Date();
		String ip = request.getRemoteAddr();	// 得到当前操作用户的IP
		String mac = null;
		//String mac = Runtime.getRuntime().exec("winver").toString();	// 得到当前操作用户电脑/MAC的值
		
		PoliceFingerInfo pf = new PoliceFingerInfo();
		pf.setJh(jh);
		pf.setXm(xm);
		pf.setBmbh(bmbh);
		pf.setFinger1(zw1);
		pf.setFinger2(zw2);
		pf.setFinger3(zw3);
		pf.setFinger4(zw4);
		pf.setZt(zt);
		pf.setCzr(czrbh);
		pf.setCzrBm(czrbm);
		pf.setCzrXm(czrxm);
		pf.setCzrKjbm(czrkjbm);
		pf.setCzsj(sj);
		pf.setCzip(ip);
		pf.setCzmac(mac);
		// 状态判断：已采集更新，未采集添加
		if(isZT.equals("已采集")){
			this.defaultDao.updateRepository(pf);
		}else if(isZT.equals("未采集")){
			this.defaultDao.addRepository(pf);
		}
		
		// 添加日志表内容
		if (pf != null) {
			PoliceFingerInfo u = (PoliceFingerInfo)this.defaultDao.addRepository(pf);
			PoliceFingerInfoLog ulog = new PoliceFingerInfoLog();
			ulog.setJh(u.getJh());
			ulog.setXm(u.getXm());
			ulog.setBmbh(u.getBmbh());
			ulog.setFinger1(u.getFinger1());
			ulog.setFinger2(u.getFinger2());
			ulog.setFinger3(u.getFinger3());
			ulog.setFinger4(u.getFinger4());
			ulog.setZt(u.getZt());
			ulog.setCzr(u.getCzr());
			ulog.setCzrBm(u.getCzrBm());
			ulog.setCzrXm(u.getCzrXm());
			ulog.setCzrKjbm(u.getCzrKjbm());
			ulog.setCzsj(u.getCzsj());
			ulog.setCzip(ip);
			ulog.setCzmac(u.getCzmac());
			ulog.setCznr("指纹采集");
			defaultDao.addRepositoryLog(ulog, u, null);
			return 0;
		} else {
			return 1;
		}
	}

	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public static Logger getLog() {
		return log;
	}





	
	

}
