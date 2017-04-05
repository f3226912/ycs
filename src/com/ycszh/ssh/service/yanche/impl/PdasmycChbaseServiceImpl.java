package com.ycszh.ssh.service.yanche.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.yanche.PdasmycChbaseDao;
import com.ycszh.ssh.dao.yanche.PdasmycChbaseLogDao;
import com.ycszh.ssh.hbm.yanche.PdasmycChbase;
import com.ycszh.ssh.hbm.yanche.PdasmycChbaseLog;
import com.ycszh.ssh.hbm.yanche.YwlsglVehSjzd;
import com.ycszh.ssh.service.yanche.PdasmycChbaseService;

/**
 * @包名:com.ycszh.ssh.service.yanche.impl
 * @文件名:PdasmycChbaseServiceImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-10
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycChbaseServiceImpl implements PdasmycChbaseService {
	
	private PdasmycChbaseDao pdasmycChbaseDao;
	private PdasmycChbaseLogDao pdasmycChbaseLogDao;
	private final static Logger log = Logger.getLogger(PdasmycChbaseServiceImpl.class);

	public Integer insertOrUpdatePdasmycChbase(PdasmycChbase pdasmycChbase,HttpServletRequest request) throws Exception {
		if (pdasmycChbase != null) {
			String id = pdasmycChbase.getId();
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String userMac = request.getParameter("userMac");
			if(null == id || "".equals(id)){
				try {
					//去除业务范围值里的空格
					if(null != pdasmycChbase.getYwfw() && !"".equals(pdasmycChbase.getYwfw()))pdasmycChbase.setYwfw(pdasmycChbase.getYwfw().replaceAll(" ", ""));
					if(null != pdasmycChbase.getYwfwA() && !"".equals(pdasmycChbase.getYwfwA()))pdasmycChbase.setYwfwA(pdasmycChbase.getYwfwA().replaceAll(" ", ""));
					//去除上门验车部门值里的空格
					if(null != pdasmycChbase.getSmycbm() && !"".equals(pdasmycChbase.getSmycbm()))pdasmycChbase.setSmycbm(pdasmycChbase.getSmycbm().replaceAll(" ", ""));
					pdasmycChbase.setSynFlag("UW");	//设置同步标志
					PdasmycChbase pc = pdasmycChbaseDao.addRepository(pdasmycChbase);
					PdasmycChbaseLog pclog = insertPdasmycChbaseLog(pc);
					pclog.setCzr(user.getName());
					pclog.setCzrxm(user.getYgxm());
					pclog.setCzrbm(user.getBmid());
					pclog.setCzsj(new Date());
					pclog.setCzip(getLoginIp(request));
					pclog.setCznr("I");
					pclog.setCzmac(userMac);
					pdasmycChbaseLogDao.addRepository(pclog);
					log.info("method:insertOrUpdatePdasmycChbase|param:pdasmycChbase="+pdasmycChbase);
					return 0;
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e);
					throw e;
				}
			}else{
				try {
					//去除业务范围值里的空格
					if(null != pdasmycChbase.getYwfw() && !"".equals(pdasmycChbase.getYwfw()))pdasmycChbase.setYwfw(pdasmycChbase.getYwfw().replaceAll(" ", ""));
					if(null != pdasmycChbase.getYwfwA() && !"".equals(pdasmycChbase.getYwfwA()))pdasmycChbase.setYwfwA(pdasmycChbase.getYwfwA().replaceAll(" ", ""));
					//去除上门验车部门值里的空格
					if(null != pdasmycChbase.getSmycbm() && !"".equals(pdasmycChbase.getSmycbm()))pdasmycChbase.setSmycbm(pdasmycChbase.getSmycbm().replaceAll(" ", ""));
					pdasmycChbase.setSynFlag("UW");	//设置同步标志
					PdasmycChbase pc = pdasmycChbaseDao.updateRepository(pdasmycChbase);
					PdasmycChbaseLog pclog = insertPdasmycChbaseLog(pc);
					pclog.setCzr(user.getName());
					pclog.setCzrxm(user.getYgxm());
					pclog.setCzrbm(user.getBmid());
					pclog.setCzsj(new Date());
					pclog.setCzip(getLoginIp(request));
					pclog.setCznr("U");
					pclog.setCzmac(userMac);
					pdasmycChbaseLogDao.addRepository(pclog);
					log.info("method:insertOrUpdatePdasmycChbase|param:pdasmycChbase="+pdasmycChbase);
					return 0;
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e);
					return 1;
				}
			}
		} else {
			return 1;
		}
	}

	public Integer deletePdasmycChbase(String id) throws Exception {
		try {
			PdasmycChbase pdasmycChbase = pdasmycChbaseDao.getRepository(id);
			if (pdasmycChbase != null) {
				pdasmycChbaseDao.deleteRepository(pdasmycChbase);
				HttpServletRequest request = ServletActionContext.getRequest();
				User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
				PdasmycChbaseLog pclog = insertPdasmycChbaseLog(pdasmycChbase);
				pclog.setCzr(user.getName());
				pclog.setCzrxm(user.getYgxm());
				pclog.setCzrbm(user.getBmid());
				pclog.setCzsj(new Date());
				pclog.setCzip(getLoginIp(request));
				pclog.setCzmac("");
				pdasmycChbaseLogDao.addRepository(pclog);
				log.info("method:deletePdasmycChbase|param:id="+id);
				return 0;
			} else {
				return 1;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}

	public Integer deletePdasmycChbaseList(String[] ids) throws Exception {
		try {
			Collection<PdasmycChbase> pdasmycChbaselist = new ArrayList<PdasmycChbase>();
			if (null != ids) {
				for (String id : ids) {
					if(null != id && !"".equals(id)){
						PdasmycChbase pdasmycChbase = pdasmycChbaseDao.getRepository(id);
						if (pdasmycChbase != null) {
							pdasmycChbaselist.add(pdasmycChbase);
						}
					}
				}
			}
			if (pdasmycChbaselist != null && pdasmycChbaselist.size() > 0) {
				pdasmycChbaseDao.deleteRepositoryList(pdasmycChbaselist);
				log.info("method:deletePdasmycChbaseList|param:ids="+ids);
				return 0;
			} else {
				return 1;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<PdasmycChbase> getPdasmycChbaseList(HttpServletRequest request,int currentPage) throws Exception {
		StringBuffer hql = new StringBuffer("from PdasmycChbase as t where 1=1 ");
		String chid = request.getParameter("chid");
		String chmc = request.getParameter("chmc");
		String zzjgdm = request.getParameter("zzjgdm");
		String chjglx = request.getParameter("chjglx");
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<PdasmycChbase> pdasmycChbaseList = new ArrayList<PdasmycChbase>();
		

		// 车行帐户
		if (chid != null && !chid.equals("")) {
			hql.append(" and t.chid = '" + chid + "' ");
			request.setAttribute("chid", chid);
		}
		
		// 车行名称
		if (chmc != null && !chmc.equals("")) {
			hql.append(" and t.chmc = '" + chmc + "' ");
			request.setAttribute("chmc", chmc);
		}
		
		// 车行机构代码
		if (zzjgdm != null && !zzjgdm.equals("")) {
			hql.append(" and t.zzjgdm = '" + zzjgdm + "' ");
			request.setAttribute("zzjgdm", zzjgdm);
		}
		
		// 机构类型
		if (chjglx != null && !chjglx.equals("")) {
			hql.append(" and t.chjglx = '" + chjglx + "' ");
			request.setAttribute("chjglx", chjglx);
		}
				

		int size = pdasmycChbaseDao.getSize(hql.toString());
		if (size > 0) {
			hql.append(" order by t.id desc");
			pdasmycChbaseList = pdasmycChbaseDao.findHQLByPage(hql.toString(),offset,pageSize);
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
		request.setAttribute("pdasmycChbaseList", pdasmycChbaseList);
		log.info("method:getPdasmycChbaseList|param:request="+request+",currentPage="+currentPage);
		return pdasmycChbaseList;
	}

	@SuppressWarnings("unchecked")
	public List<PdasmycChbase> getPdasmycChbaseList(HttpServletRequest request) throws Exception {
		StringBuffer hql = new StringBuffer("from PdasmycChbase as t where 1=1 ");
		List<PdasmycChbase> pdasmycChbaseList = new ArrayList<PdasmycChbase>();
		pdasmycChbaseList = pdasmycChbaseDao.getRepositories(hql.toString());
		log.info("method:getPdasmycChbaseList|param:request="+request);
		return pdasmycChbaseList;
	}

	public PdasmycChbase getPdasmycChbase(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			log.info("method:getPdasmycChbase|param:id="+id);
			return pdasmycChbaseDao.getRepository(id);
		}
		return null;
	}

	public PdasmycChbase getPdasmycChbase(String id) throws Exception {
		log.info("method:getPdasmycChbase|param:id="+id);
		return pdasmycChbaseDao.getRepository(id);
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
	
	public PdasmycChbaseLog insertPdasmycChbaseLog(PdasmycChbase pdasmycChbase) throws Exception{
		PdasmycChbaseLog pdasmycChbaselog = new PdasmycChbaseLog();
		try {
			Field[] fields = pdasmycChbase.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				Field field = pdasmycChbaselog.getClass().getDeclaredField(name);
				field.set(pdasmycChbaselog, fields[i].get(pdasmycChbase));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return pdasmycChbaselog;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<YwlsglVehSjzd> getYwlsglVehSjzdList(String dmlb) throws Exception {
		List<YwlsglVehSjzd> ywlsglVehSjzdList = new ArrayList<YwlsglVehSjzd>();
		String hql = "from YwlsglVehSjzd ";
		if(null != dmlb && !"".equals(dmlb)){
			hql += "where dmlb='" + dmlb + "'";
		}
		ywlsglVehSjzdList = pdasmycChbaseDao.getRepositories(hql);
		return ywlsglVehSjzdList;
	}
	

	/**
	 * @return the pdasmycChbaseDao
	 */
	public PdasmycChbaseDao getPdasmycChbaseDao() {
		return pdasmycChbaseDao;
	}

	/**
	 * @param pdasmycChbaseDao the pdasmycChbaseDao to set
	 */
	public void setPdasmycChbaseDao(PdasmycChbaseDao pdasmycChbaseDao) {
		this.pdasmycChbaseDao = pdasmycChbaseDao;
	}

	/**
	 * @return the pdasmycChbaseLogDao
	 */
	public PdasmycChbaseLogDao getPdasmycChbaseLogDao() {
		return pdasmycChbaseLogDao;
	}

	/**
	 * @param pdasmycChbaseLogDao the pdasmycChbaseLogDao to set
	 */
	public void setPdasmycChbaseLogDao(PdasmycChbaseLogDao pdasmycChbaseLogDao) {
		this.pdasmycChbaseLogDao = pdasmycChbaseLogDao;
	}

	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}

	

	
	
}
