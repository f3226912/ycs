package com.ycszh.ssh.service.bfc.impl;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.bfc.BfcJbxxbDao;
import com.ycszh.ssh.dao.bfc.BfcJbxxbLogDao;
import com.ycszh.ssh.dao.drv.SlgSjzdDao;
import com.ycszh.ssh.hbm.bfc.BfcJbxxb;
import com.ycszh.ssh.hbm.bfc.BfcJbxxbLog;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.service.bfc.BfcJbxxbService;

public class BfcJbxxbServiceImpl implements BfcJbxxbService {
	
	private BfcJbxxbDao bfcJbxxbDao;
	private BfcJbxxbLogDao bfcJbxxbLogDao;
	private SlgSjzdDao slgSjzdDao;
	private final static Logger log = Logger.getLogger(BfcJbxxbServiceImpl.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BfcJbxxb> getBfcJbxxbList(HttpServletRequest request,
			int currentPage) throws Exception {
		StringBuffer hql = new StringBuffer("from BfcJbxxb as t where 1=1 ");
		StringBuffer sql = new StringBuffer("select count(0) from BFC_JBXXB t where 1=1 ");
		String sfzmhm = request.getParameter("sfzmhm");
		String hphm = request.getParameter("hphm");
		String hpzl = request.getParameter("hpzl");
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<BfcJbxxb> bfcJbxxbList = new ArrayList<BfcJbxxb>();
		
		List<SlgSjzd> slgSjzdList = new ArrayList<SlgSjzd>();
		//号牌种类数据字典集合
		slgSjzdList = getSjzdList("7");
		request.setAttribute("slgSjzdList", slgSjzdList);
		Map<String,String> mapsjzd = new HashMap<String,String>();
		for (SlgSjzd ss : slgSjzdList) {
			mapsjzd.put(ss.getDmz(), ss.getDmms1());
		}
		
		
		
		// 身份证明号码
		if (sfzmhm != null && !"".equals(sfzmhm)) {
			sfzmhm = sfzmhm.replaceAll(" ", "");
			sfzmhm = sfzmhm.replaceAll("'", "");
			sfzmhm = sfzmhm.replaceAll("\"", "");
			sfzmhm = sfzmhm.replaceAll("，", ",");
			hql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			sql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			request.setAttribute("sfzmhm", sfzmhm);
		}
				
		// 号牌号码
		if (hphm != null && !"".equals(hphm)) {
			hphm = hphm.replaceAll(" ", "");
			hphm = hphm.replaceAll("'", "");
			hphm = hphm.replaceAll("\"", "");
			hphm = hphm.replaceAll("，", ",");
			hql.append(" and t.hphm = '" + hphm + "' ");
			sql.append(" and t.hphm = '" + hphm + "' ");
			request.setAttribute("hphm", hphm);
		}
		
		// 号牌种类
		if (hpzl != null && !"".equals(hpzl)) {
			hql.append(" and t.hpzl = '" + hpzl + "' ");
			sql.append(" and t.hpzl = '" + hpzl + "' ");
			request.setAttribute("hpzl", hpzl);
		}
		
		int size = bfcJbxxbDao.getRepositoryBySQLListSize(sql.toString());
		if (size > 0) {
			bfcJbxxbList = bfcJbxxbDao.findHQLByPage(hql.toString(),offset,pageSize);
			for(BfcJbxxb bfcJbxxb : bfcJbxxbList){
				//号牌种类读取字典
				bfcJbxxb.setHpzl(mapsjzd.get(bfcJbxxb.getHpzl()));
			}
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
		request.setAttribute("bfcJbxxbList", bfcJbxxbList);
		log.info("method:getBfcJbxxbList|param:request="+request+",currentPage="+currentPage);
		return bfcJbxxbList;
	}

	public Integer UpdateBfcJbxxbZt(HttpServletRequest request)
			throws Exception {
		try {
			String xh = request.getParameter("xh");
			String zt = request.getParameter("zt");
			String bz = request.getParameter("bz");
			if(null != bz && !"".equals(bz))bz = URLDecoder.decode(bz,"UTF-8");
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			if(null != xh && !"".equals(xh)){
				BfcJbxxb bj = bfcJbxxbDao.getRepository(xh);
				bj.setSfyx(zt);
				bj.setBz(bz);
				bj.setSynFlag("UW");
				bj.setTranDate(null);
				bj.setTranFlag(null);
				bfcJbxxbDao.updateRepository(bj);
				BfcJbxxbLog bjlog = insertBfcJbxxbLog(bj);
				bjlog.setCzr(user.getName());
				bjlog.setCzrxm(user.getYgxm());
				bjlog.setCzrbm(user.getBmid());
				bjlog.setCzsj(new Date());
				bjlog.setCzip(getLoginIp(request));
				bjlog.setCznr("U");
				bfcJbxxbLogDao.addRepository(bjlog);
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	
	
	public BfcJbxxbLog insertBfcJbxxbLog(BfcJbxxb slgDrvXxcjb) throws Exception{
		BfcJbxxbLog slgDrvXxcjbLog = new BfcJbxxbLog();
		try {
			Field[] fields = slgDrvXxcjb.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				Field field = slgDrvXxcjbLog.getClass().getDeclaredField(name);
				field.set(slgDrvXxcjbLog, fields[i].get(slgDrvXxcjb));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return slgDrvXxcjbLog;
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
	public List<SlgSjzd> getSjzdList(String dmlb) {
		String hql = "from SlgSjzd where dmlb='" + dmlb + "' order by dmz asc";
		List<SlgSjzd> sjzdlist = new ArrayList<SlgSjzd>();
		try {
			sjzdlist = slgSjzdDao.getRepositories(hql);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return sjzdlist;
	}

	@SuppressWarnings("unchecked")
	public String getSjzdList(String dmlb, String dmz) {
		String hql = "from SlgSjzd where dmlb='" + dmlb + "' and dmz='" + dmz + "'";
		List<SlgSjzd> sjzdlist = new ArrayList<SlgSjzd>();
		try {
			sjzdlist = slgSjzdDao.getRepositories(hql);
			if(null != sjzdlist && sjzdlist.size() > 0){
				SlgSjzd os = sjzdlist.get(0);
				if(null != os && null != os.getDmms1()){
					return os.getDmms1();
				}
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return the bfcJbxxbDao
	 */
	public BfcJbxxbDao getBfcJbxxbDao() {
		return bfcJbxxbDao;
	}

	/**
	 * @param bfcJbxxbDao the bfcJbxxbDao to set
	 */
	public void setBfcJbxxbDao(BfcJbxxbDao bfcJbxxbDao) {
		this.bfcJbxxbDao = bfcJbxxbDao;
	}

	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}

	/**
	 * @return the bfcJbxxbLogDao
	 */
	public BfcJbxxbLogDao getBfcJbxxbLogDao() {
		return bfcJbxxbLogDao;
	}

	/**
	 * @param bfcJbxxbLogDao the bfcJbxxbLogDao to set
	 */
	public void setBfcJbxxbLogDao(BfcJbxxbLogDao bfcJbxxbLogDao) {
		this.bfcJbxxbLogDao = bfcJbxxbLogDao;
	}

	/**
	 * @return the slgSjzdDao
	 */
	public SlgSjzdDao getSlgSjzdDao() {
		return slgSjzdDao;
	}

	/**
	 * @param slgSjzdDao the slgSjzdDao to set
	 */
	public void setSlgSjzdDao(SlgSjzdDao slgSjzdDao) {
		this.slgSjzdDao = slgSjzdDao;
	}

}
