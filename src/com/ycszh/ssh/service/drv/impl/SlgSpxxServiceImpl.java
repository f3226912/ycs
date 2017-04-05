package com.ycszh.ssh.service.drv.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.drv.SlgDrvXxcjbDao;
import com.ycszh.ssh.dao.drv.SlgSpxxDao;
import com.ycszh.ssh.hbm.drv.SlgSpxx;
import com.ycszh.ssh.hbm.drv.SlgSpxxLog;
import com.ycszh.ssh.service.drv.SlgSpxxService;
import com.ycszh.util.DateUtil;

import common.Logger;

public class SlgSpxxServiceImpl implements SlgSpxxService {
	
	private String uri;
	private static final Logger logger = Logger.getLogger(SlgSpxxServiceImpl.class);
	private SlgSpxxDao slgSpDao;
	private SlgDrvXxcjbDao slgDrvXxcjbDao;
	
	public Integer getSlgSpxxCount(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<SlgSpxx> getSlgSpxxList(HttpServletRequest request,
			int currentPage) throws Exception {
		int count = 0;
		Map map = new HashedMap();
		DateUtil du = new DateUtil();
		StringBuffer hqlListBuff = new StringBuffer("SELECT s FROM SlgSpxx s  WHERE 1=1 ");
		StringBuffer hqlCountBuff = new StringBuffer("SELECT COUNT(s) FROM SlgSpxx s WHERE 1=1");
		StringBuffer strCondition = new StringBuffer("");
		List<SlgSpxx> slgSpxxList = null;
		int pageSize = SysConst.PAGESIZE;
		int offset = (currentPage-1)*pageSize;
		uri = request.getRequestURI();
		String splx = request.getParameter("splx");
		String sfzmhm = request.getParameter("sfzmhm");
		String xm = request.getParameter("xm");
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		String orgid = getDeptUpid(user.getBmid());
		//审批类型
		if(splx != null && !"".equals(splx)){
			strCondition.append(" AND s.splx2 = '" + splx + "' ");
			request.setAttribute("splx", splx);
		}
		//身份证明号码
		if(sfzmhm != null && !"".equals(sfzmhm)){
			strCondition.append(" AND s.sfzmhm = '"+sfzmhm+"'");
			request.setAttribute("sfzmhm", sfzmhm);
		}
		//姓名
		if(xm != null && !"".equals(xm)){
			strCondition.append(" AND s.xm like '%"+xm+"%'");
			request.setAttribute("xm", xm);
		}
		strCondition.append(" and s.splx = 'drv' ");
		//部门（科级）
//		if(orgid != null && !"".equals(orgid)){
//			strCondition.append(" AND s.sprBmkj = '"+orgid+"'");
//		}
		hqlListBuff.append(strCondition.toString());
		hqlListBuff.append(" ORDER BY s.sprSj DESC ");
		count = this.slgSpDao.getRepositoryByHQLListSize(hqlCountBuff.toString()+strCondition.toString());
		slgSpxxList = new ArrayList<SlgSpxx>();
		slgSpxxList = this.slgSpDao.findHQLByPage(hqlListBuff.toString(), offset, pageSize);
		//翻译部门名称
		for(int i = 0; i < slgSpxxList.size(); i++){
			String deptid = null;
			deptid = slgSpxxList.get(i).getSprBm();
			if(deptid != null && !"".equals(deptid)){
				slgSpxxList.get(i).setSprBmmc(this.getDeptName(deptid));
			}else{
				slgSpxxList.get(i).setSprBmmc("");
			}
		}
		map.put("uri", uri);
		map.put("pagesize", pageSize);
		map.put("rscount", count);
		map.put("currentpage", currentPage);
		request.setAttribute("kjbmid", orgid);
		request.setAttribute("rscount", count);
		request.setAttribute("map", map);
		request.setAttribute("slgSpxxList", slgSpxxList);
		return slgSpxxList;
	}
	
	public Integer deleteSlgSpxx(HttpServletRequest request, Long id, String cznr) throws Exception {
		if(id != null){
			SlgSpxx slgSpxx = null;
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			String userMac = request.getParameter("userMac");
			userMac = "";
			cznr = "DEL";
			try {
				slgSpxx = this.slgSpDao.getRepository(id);
				if(slgSpxx != null){
					this.slgSpDao.deleteRepository(slgSpxx);
					SlgSpxxLog slgLog = new SlgSpxxLog();
					slgLog = (SlgSpxxLog)getXclog(slgLog, slgSpxx);
					slgLog.setCzr(user.getName());
					slgLog.setCzrxm(user.getYgxm());
					slgLog.setCzbm(user.getBmid());
					slgLog.setCzrq(new Date());
					slgLog.setCzip(getLoginIp(request));
					slgLog.setCznr(cznr);
					slgLog.setCzmac(userMac);
					this.slgSpDao.addObj(slgLog, request);
					return 1;
				}
				return 2;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				throw e;
			}
		}else{
			return 0;
		}
		
	}

	public Integer insertOrUpdateSlgSpxx(HttpServletRequest request,
			SlgSpxx slgSpxx, String cznr) throws Exception {
		if(slgSpxx != null){
			Long id = slgSpxx.getId();
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			String userMac = request.getParameter("userMac");
			userMac = "";
			cznr = "SEL";
			slgSpxx.setSprCode(user.getYgid());
			slgSpxx.setSprName(user.getYgxm());
			slgSpxx.setSprBm(user.getBmid());
			String orgid = getDeptUpid(user.getBmid());
			slgSpxx.setSprBmkj(orgid);
			slgSpxx.setSplx("drv");
			slgSpxx.setSprSj(new Date());
			//格式时间
			if(slgSpxx.getYxsj() != null && !"".equals(slgSpxx.getYxsj())){
				String formatdate = DateUtil.date2String(slgSpxx.getYxsj());
				formatdate = formatdate+" 23:59:59";
				Date date = DateUtil.string2Date(formatdate, "yyyy-MM-dd HH:mm:ss");
				slgSpxx.setYxsj(date);
				
			}
			if(id != null && id > 0){
				//修改
				SlgSpxx slg = null;
				try {
					cznr = "UPDATE";
					slg = this.slgSpDao.updateRepository(slgSpxx);
					SlgSpxxLog slgLog = new SlgSpxxLog();
					slgLog = (SlgSpxxLog)getXclog(slgLog, slg);
					slgLog.setCzr(user.getName());
					slgLog.setCzrxm(user.getYgxm());
					slgLog.setCzbm(user.getBmid());
					slgLog.setCzrq(new Date());
					slgLog.setCzip(getLoginIp(request));
					slgLog.setCznr(cznr);
					slgLog.setCzmac(userMac);
					this.slgSpDao.addObj(slgLog, request);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					throw e;
				}
			}else{
				//添加
				SlgSpxx slg = null;
				try {
					cznr = "INSERT";
					slg = this.slgSpDao.addRepository(slgSpxx);
					SlgSpxxLog slgLog = new SlgSpxxLog();
					slgLog = (SlgSpxxLog)getXclog(slgLog, slg);
					slgLog.setCzr(user.getName());
					slgLog.setCzrxm(user.getYgxm());
					slgLog.setCzbm(user.getBmid());
					slgLog.setCzrq(new Date());
					slgLog.setCzip(getLoginIp(request));
					slgLog.setCznr(cznr);
					slgLog.setCzmac(userMac);
					this.slgSpDao.addObj(slgLog, request);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					throw e;
				}
			}
			return 1;
		}else{
			return 0;
		}
	}
	
	public SlgSpxx getSlgSpxxById(HttpServletRequest request, Long id) throws Exception{
		SlgSpxx slgSp = null;
		if(id != null){
			slgSp = this.slgSpDao.getRepository(id);
			return slgSp;
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String getDeptUpid(String deptid) throws Exception{
		String deptsql = "select org_id from (select t.* from v_veh_org_ycs t start with " +
					"org_id = '" + deptid + "' connect by prior up_org = org_id) " +
					"where up_org = 'C34702A8FED97CBFE040007F0100339B'";
		List deptidlist = slgSpDao.findSQL(deptsql);
		String deptids = "";
		if(null != deptidlist && deptidlist.size() > 0){
			deptids = deptidlist.get(0).toString();
		}
		return deptids;
	}
	
	public String getLoginIp(HttpServletRequest request) throws Exception{
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	@SuppressWarnings("unchecked")
	public SlgSpxx getSlgSpxxByCondition(HttpServletRequest request) throws Exception{
		String sfzmhm = request.getParameter("sfzmhm");
		String xm = request.getParameter("xm");
		String splx2 = request.getParameter("splx2");
		String splx = request.getParameter("splx");
		String yxsj = request.getParameter("yxsj");
		StringBuffer hql = new StringBuffer("FROM SlgSpxx s WHERE 1=1 ");
		List<SlgSpxx> slgList = new ArrayList<SlgSpxx>();
		if(sfzmhm != null && !"".equals(sfzmhm.trim())){
			hql.append(" AND s.sfzmhm = '"+sfzmhm+"'");
		}
		if(xm != null && !"".equals(xm.trim())){
			hql.append(" AND s.xm = '"+xm+"'");
		}
		if(splx2 != null && !"".equals(splx2.trim())){
			hql.append(" AND s.splx2 = '"+splx2+"'");
		}
		//如果为0，是否有效和时间段都不控制，如果为1，则表示代理人，时间段控制，如果为2，表示当事人，是否有效控制
		if(yxsj != null && !"".equals(yxsj.trim()) && "1".equals(yxsj)){
			hql.append(" AND s.yxsj >= sysdate ");
		}else if("2".equals(yxsj)){
			hql.append(" AND s.flag = '0'");
		}
		if(splx != null && !"".equals(splx.trim())){
			hql.append(" AND s.splx = '"+splx+"'");
		}
		slgList = this.slgSpDao.getRepositories(hql.toString());
		if(slgList != null && slgList.size() > 0){
			return slgList.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public SlgSpxx getSlgSpxxByCondition(HttpServletRequest request, String sfzmhm, String xm, String splx, String splx2, String yxsj) throws Exception{
		StringBuffer hql = new StringBuffer("FROM SlgSpxx s WHERE 1=1 ");
		List<SlgSpxx> slgList = new ArrayList<SlgSpxx>();
		if(sfzmhm != null && !"".equals(sfzmhm.trim())){
			hql.append(" AND s.sfzmhm = '"+sfzmhm+"'");
		}
		if(xm != null && !"".equals(xm.trim())){
			hql.append(" AND s.xm = '"+xm+"'");
		}
		if(splx2 != null && !"".equals(splx2.trim())){
			hql.append(" AND s.splx2 = '"+splx2+"'");
		}
		if(splx != null && !"".equals(splx.trim())){
			hql.append(" AND s.splx = '"+splx+"'");
		}
		slgList = this.slgSpDao.getRepositories(hql.toString());
		if(slgList != null && slgList.size() > 0){
			return slgList.get(0);
		}
		return null;
	}
	
	public void updateSlgSpxx(HttpServletRequest request, String sfzmhm, String xm, String splx, String splx2, String cjsj) throws Exception{
		try {
			String sql = "UPDATE SLG_SPXX SET FLAG = 1";
			if(cjsj != null && !"".equals(cjsj)){
				sql += " , CJSJ = to_date('"+cjsj + "', 'yyyy-MM-dd HH24:mi:ss')";
			}
			sql += " WHERE 1=1 ";
			if(sfzmhm != null && !"".equals(sfzmhm)){
				sql += " AND SFZMHM = '"+sfzmhm+"' ";
			}
			if(xm != null && !"".equals(xm)){
				sql += " AND XM = '"+xm+"' ";
			}
			if(splx != null && !"".equals(splx)){
				sql += " AND SPLX = '"+splx+"' ";
			}
			if(splx2 != null && !"".equals(splx2)){
				sql += " AND SPLX2 = '"+splx2+"' ";
			}
			sql += " AND FLAG = 0";
			this.slgSpDao.updateRepositoryBySql(sql);
			String userMac = request.getParameter("userMac");
			userMac = "";
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			SlgSpxx slgSpxx = this.getSlgSpxxByCondition(request, sfzmhm, xm, splx, splx2, "");
			if(slgSpxx != null){
				Date cjsjdate = DateUtil.string2Date(cjsj, "yyyy-MM-dd HH:mm:ss");
				slgSpxx.setCjsj(cjsjdate);
				slgSpxx.setFlag("1");
				SlgSpxxLog slgLog = new SlgSpxxLog();
				slgLog = (SlgSpxxLog)getXclog(slgLog, slgSpxx);
				slgLog.setCzr(user.getName());
				slgLog.setCzrxm(user.getYgxm());
				slgLog.setCzbm(user.getBmid());
				slgLog.setCzrq(new Date());
				slgLog.setCzip(getLoginIp(request));
				slgLog.setCznr("UPDATE");
				slgLog.setCzmac(userMac);
				this.slgSpDao.addObj(slgLog, request);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public String getDeptName(String deptid) throws Exception {
		String sql = "select t.org_name from v_veh_org_ycs t where t.org_id='"+deptid+"'";
		List list = slgDrvXxcjbDao.findSQL(sql);
		String restr = "";
		if(null != list && list.size() > 0){
			restr = list.get(0).toString();
		}else{
			restr = deptid;
		}
		return restr;
	}
	
	public Object getXclog(Object obj1, Object obj2) throws Exception{
		if(obj1 == null){
			return null;
		}
		try {
			Field[] fields = obj2.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				Field field = obj1.getClass().getDeclaredField(name);
				field.set(obj1, fields[i].get(obj2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		return obj1;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	public SlgSpxxDao getSlgSpDao() {
		return slgSpDao;
	}

	public void setSlgSpDao(SlgSpxxDao slgSpDao) {
		this.slgSpDao = slgSpDao;
	}

	public SlgDrvXxcjbDao getSlgDrvXxcjbDao() {
		return slgDrvXxcjbDao;
	}

	public void setSlgDrvXxcjbDao(SlgDrvXxcjbDao slgDrvXxcjbDao) {
		this.slgDrvXxcjbDao = slgDrvXxcjbDao;
	}
	
}
