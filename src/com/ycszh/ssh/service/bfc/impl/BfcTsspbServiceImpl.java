package com.ycszh.ssh.service.bfc.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.hbm.bfc.BfcTsspb;
import com.ycszh.ssh.hbm.bfc.BfcTsspbLog;
import com.ycszh.ssh.service.bfc.BfcTsspbService;
import com.ycszh.util.ToolsUtil;

/**
 * @包名:com.ycszh.ssh.service.bfc.impl
 * @文件名:BfcTsspbServiceImp.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-10-13
 * @描述:
 * @版本:V 1.0
 */
/**
 * @author caocheng
 *
 */
public class BfcTsspbServiceImpl implements BfcTsspbService {

	private DefaultDao defaultDao;
	
	
	public DefaultDao getDefaultDao() {
		return defaultDao;
	}
	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public int addBfcTsspb(BfcTsspb bfcTsspb,HttpServletRequest request) {
		try{
			defaultDao.addRepository(bfcTsspb);
			addBfcTsspbLog(request, "I", bfcTsspb);
			return 0;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	// 添加操作日志
	private void addBfcTsspbLog(HttpServletRequest request,String czrn,BfcTsspb bfcTsspb) throws Exception{
		// 得到登陆用户
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		List list = new ArrayList();
		list.add("xh");
		list.add("czip");
		list.add("czr");
		list.add("czrxm");
		list.add("czrbm");
		list.add("czrkjbm");
		list.add("czsj");
		
		BfcTsspbLog bfcTsspbLog = new BfcTsspbLog();
		
		bfcTsspbLog.setCzip(ToolsUtil.getIpAddr(request));
		bfcTsspbLog.setCzr(user.getYgid());
		bfcTsspbLog.setCzrxm(user.getYgxm());
		bfcTsspbLog.setCzrbm(user.getBmid());
		bfcTsspbLog.setCzsj(new Date());
		
		// 拿到用户处级部门
		List deptlist = defaultDao.findSQL("select t.org_id from vehoffice.v_veh_org_ycs t"
												+" where org_id in (select t.org_id"
													+" from vehoffice.v_veh_org_ycs t"
													+" where level = '2'"
													+" start with org_id='C34702A8FED77CBFE040007F0100339B'"
													+" connect by prior org_id = up_org )"
												+" start with org_id='"+user.getBmid()+"'"
												+" connect by prior up_org = org_id");
		bfcTsspbLog.setCzrkjbm(deptlist.get(0)+"");
		
		bfcTsspbLog.setZbid(bfcTsspb.getXh());
		bfcTsspbLog.setCznr(czrn);
		defaultDao.addRepositoryLog(bfcTsspbLog,bfcTsspb,list);
	}

	public List getBfcTsspbInfoByPar(HttpServletRequest request) throws Exception{
		StringBuffer hql = new StringBuffer("from BfcTsspb as t where 1=1 ");
		
		String xh = request.getParameter("xh");
		if(xh != null && !"".equals(xh)){
			hql.append(" and xh = '"+xh+"'");
		}
		
		return defaultDao.getRepositories(hql.toString());
	}
	
	public int delBfcTsspbInfo(String id,HttpServletRequest request) throws Exception {
		BfcTsspb bfcTsspb = (BfcTsspb)defaultDao.getRepository(id, BfcTsspb.class);
		defaultDao.deleteRepository(bfcTsspb);
		
		addBfcTsspbLog(request, "D", bfcTsspb);
		
		return 0;
	}

	public void getBfcTsspbInfo(HttpServletRequest request,int currentPage) throws Exception {
		StringBuffer sql = new StringBuffer("select * from BFC_TSSPB t where 1=1");
//		StringBuffer hql = new StringBuffer("from BfcTsspb t where 1=1");
		StringBuffer sqlCount = new StringBuffer("select count(0) from BFC_TSSPB t where 1=1 ");
		String sfzmhm = request.getParameter("sfzmhm");
		String spmj = request.getParameter("spmj");
		String startRksj = request.getParameter("startRksj");
		String endRksj = request.getParameter("endRksj");
		
		// 用户点击查单级别
		String jb = request.getParameter("jb");
		
		// 得到登陆用户
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		
		List bfcTsspbList = new ArrayList();
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		
		if(sfzmhm != null && !"".equals(sfzmhm)){
			sql.append(" and t.sfzmhm = '"+sfzmhm+"'");
//			hql.append(" and t.sfzmhm = '"+sfzmhm+"'");
			sqlCount.append(" and t.sfzmhm = '"+sfzmhm+"'");
			request.setAttribute("sfzmhm", sfzmhm);
		}
		
		if(spmj != null && !"".equals(spmj)){
			sql.append(" and t.m_spid = '"+spmj+"'");
//			hql.append(" and t.MSpid = '"+spmj+"'");
			sqlCount.append(" and t.m_spid = '"+spmj+"'");
			request.setAttribute("spmj", spmj);
		}
		
		if(startRksj != null && !"".equals(startRksj)){
			sql.append(" and t.m_spsj > to_date('"+startRksj+"','yyyy-MM-dd')");
			sqlCount.append(" and t.m_spsj > to_date('"+startRksj+"','yyyy-MM-dd')");
			request.setAttribute("startRksj", startRksj);
		}
		
		if(endRksj != null && !"".equals(endRksj)){
			sql.append(" and t.m_spsj < to_date('"+endRksj+"','yyyy-MM-dd')");
			sqlCount.append(" and t.m_spsj < to_date('"+endRksj+"','yyyy-MM-dd')");
			request.setAttribute("endRksj", endRksj);
		}
		
		// 如果是处级或者是科级
		if("1".equals(jb) || "2".equals(jb)){
			// 查询该账号部门及子部门下的所有审批信息
			sql.append(" and t.m_spbmid in (select org_id from vehoffice.v_veh_org_ycs"
											+" connect by prior org_id = up_org "
											+" start with org_id='"+user.getBmid()+"')");
			
//			hql.append(" and t.MSpbmid in (select org_id from vehoffice.v_veh_org_ycs"
//					+" connect by prior org_id = up_org "
//					+" start with org_id='"+user.getBmid()+"')");
			
			sqlCount.append(" and t.m_spbmid in (select org_id from vehoffice.v_veh_org_ycs"
					+" connect by prior org_id = up_org "
					+" start with org_id='"+user.getBmid()+"')");
			
			
		}
		else{
			// 其他则只查询该部门下所有信息
			sql.append(" and t.m_spbmid = '"+user.getBmid()+"'");
//			hql.append(" and t.MSpbmid = '"+user.getBmid()+"'");
			sqlCount.append(" and t.m_spbmid = '"+user.getBmid()+"'");
		}
		request.setAttribute("jb", jb);
		
		int size = defaultDao.getRepositoryBySQLListSize(sqlCount.toString());
		if (size > 0) {
			bfcTsspbList = defaultDao.findSQLByPage(sql.toString(),offset,pageSize,BfcTsspb.class);
			request.setAttribute("rscount", size);
		} else {
			request.setAttribute("rscount", 0);
		}
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("bfcJbxxbList", bfcTsspbList);
	}
	
	
	public List getBfcTsspbInfoBySql(String sql) throws Exception {
		return defaultDao.findSQL(sql);
	}
	
	
	public void updateTsspbInfo(BfcTsspb bfcTsspb,HttpServletRequest request) throws Exception {
		
		defaultDao.updateRepository(bfcTsspb);
		
		addBfcTsspbLog(request, "U", bfcTsspb);
		
	}
	
	public void getBfcTsspbInfo2(HttpServletRequest request, int currentpage)
			throws Exception {
		StringBuffer sql = new StringBuffer("select * from BFC_TSSPB t where 1=1");
//		StringBuffer hql = new StringBuffer("from BfcTsspb t where 1=1");
		StringBuffer sqlCount = new StringBuffer("select count(0) from BFC_TSSPB t where 1=1 ");
		String sfzmhm = request.getParameter("sfzmhm");
		String startRksj = request.getParameter("startRksj");
		String endRksj = request.getParameter("endRksj");
		String bm = request.getParameter("bm");
		
		List bfcTsspbList = new ArrayList();
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentpage-1);
		String curi = request.getRequestURI();
		
		if(sfzmhm != null && !"".equals(sfzmhm)){
			sql.append(" and t.sfzmhm = '"+sfzmhm+"'");
			sqlCount.append(" and t.sfzmhm = '"+sfzmhm+"'");
			request.setAttribute("sfzmhm", sfzmhm);
		}
		
		if(startRksj != null && !"".equals(startRksj)){
			sql.append(" and t.m_spsj > to_date('"+startRksj+"','yyyy-MM-dd')");
			sqlCount.append(" and t.m_spsj > to_date('"+startRksj+"','yyyy-MM-dd')");
			request.setAttribute("startRksj", startRksj);
		}
		
		if(endRksj != null && !"".equals(endRksj)){
			sql.append(" and t.m_spsj < to_date('"+endRksj+"','yyyy-MM-dd')");
			sqlCount.append(" and t.m_spsj < to_date('"+endRksj+"','yyyy-MM-dd')");
			request.setAttribute("endRksj", endRksj);
		}
		
		if(bm != null && !"".equals(bm)){
			
			sql.append(" and t.m_spbmid in (select org_id from vehoffice.v_veh_org_ycs"
					+" connect by prior org_id = up_org "
					+" start with org_id='"+bm+"')");
			
			sqlCount.append(" and t.m_spbmid in (select org_id from vehoffice.v_veh_org_ycs"
					+" connect by prior org_id = up_org "
					+" start with org_id='"+bm+"')");
			request.setAttribute("bm", bm);
		}
		
		
		int size = defaultDao.getRepositoryBySQLListSize(sqlCount.toString());
		if (size > 0) {
			bfcTsspbList = defaultDao.findSQLByPage(sql.toString(),offset,pageSize,BfcTsspb.class);
			request.setAttribute("rscount", size);
		} else {
			request.setAttribute("rscount", 0);
		}
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentpage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("bfcJbxxbList", bfcTsspbList);
		
	}
	public BfcTsspb getBfcTsspbByXh(String xh) throws Exception {
		
		return (BfcTsspb)defaultDao.getRepository(xh, BfcTsspb.class);
	}

}
