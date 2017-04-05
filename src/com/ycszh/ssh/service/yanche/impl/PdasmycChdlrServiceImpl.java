package com.ycszh.ssh.service.yanche.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.yanche.PdasmycChdlrDao;
import com.ycszh.ssh.hbm.yanche.PdasmycChdlr;
import com.ycszh.ssh.hbm.yanche.YwlsglVehSjzd;
import com.ycszh.ssh.service.yanche.PdasmycChdlrService;
import com.ycszh.util.DateUtil;

/**
 * @包名:com.ycszh.ssh.service.yanche.impl
 * @文件名:PdasmycChdlrServiceImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-16
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycChdlrServiceImpl implements PdasmycChdlrService {
	private PdasmycChdlrDao pdasmycChdlrDao;
	private final static Logger log = Logger.getLogger(PdasmycChdlrServiceImpl.class);

	public Integer insertOrUpdatePdasmycChdlr(PdasmycChdlr pdasmycChdlr,HttpServletRequest request) throws Exception {
		if (pdasmycChdlr != null) {
			String id = pdasmycChdlr.getId();
			if(null == id || "".equals(id)){
				try {
					pdasmycChdlr.setSynFlag("UW");	//设置同步标志
					pdasmycChdlrDao.addRepository(pdasmycChdlr);
					log.info("method:insertOrUpdatePdasmycChdlr|param:pdasmycChdlr="+pdasmycChdlr);
					return 0;
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e);
					throw e;
				}
			}else{
				try {
					pdasmycChdlr.setSynFlag("UW");	//设置同步标志
					pdasmycChdlrDao.updateRepository(pdasmycChdlr);
					log.info("method:insertOrUpdatePdasmycChdlr|param:pdasmycChdlr="+pdasmycChdlr);
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

	public Integer deletePdasmycChdlr(String id) throws Exception {
		try {
			PdasmycChdlr pdasmycChdlr = pdasmycChdlrDao.getRepository(id);
			if (pdasmycChdlr != null) {
				pdasmycChdlrDao.deleteRepository(pdasmycChdlr);
				log.info("method:deletePdasmycChdlr|param:id="+id);
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

	public Integer deletePdasmycChdlrList(String[] ids) throws Exception {
		try {
			Collection<PdasmycChdlr> pdasmycChdlrlist = new ArrayList<PdasmycChdlr>();
			if (null != ids) {
				for (String id : ids) {
					if(null != id && !"".equals(id)){
						PdasmycChdlr pdasmycChdlr = pdasmycChdlrDao.getRepository(id);
						if (pdasmycChdlr != null) {
							pdasmycChdlrlist.add(pdasmycChdlr);
						}
					}
				}
			}
			if (pdasmycChdlrlist != null && pdasmycChdlrlist.size() > 0) {
				pdasmycChdlrDao.deleteRepositoryList(pdasmycChdlrlist);
				log.info("method:deletePdasmycChdlrList|param:ids="+ids);
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

	@SuppressWarnings({ "unchecked", "rawtypes", "null", "static-access" })
	public List<PdasmycChdlr> getPdasmycChdlrList(HttpServletRequest request,int currentPage) throws Exception {
		StringBuffer hql = new StringBuffer("from PdasmycChdlr as t where 1=1 ");
		String dlrxm = request.getParameter("dlrxm");
		String dlrsfzmhm = request.getParameter("dlrsfzmhm");
		String chmc = request.getParameter("chmc");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<PdasmycChdlr> pdasmycChdlrList = new ArrayList<PdasmycChdlr>();
		
		//DateUtil
		DateUtil du = new DateUtil();

		// 代理人姓名
		if (dlrxm != null && !dlrxm.equals("")) {
			hql.append(" and t.dlrxm = '" + dlrxm + "' ");
			request.setAttribute("dlrxm", dlrxm);
		}
		
		// 代理人身份证明号码
		if (dlrsfzmhm != null && !dlrsfzmhm.equals("")) {
			hql.append(" and t.dlrsfzmhm = '" + dlrsfzmhm + "' ");
			request.setAttribute("dlrsfzmhm", dlrsfzmhm);
		}
		
		// 车行名称
		if (chmc != null && !chmc.equals("")) {
			hql.append(" and t.chmc = '" + chmc + "' ");
			request.setAttribute("chmc", chmc);
		}
		
		
		//登录时间
		if (s_date != null && e_date != null && !s_date.equals("")
				&& !e_date.equals("")) {
			hql.append(" and (t.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && e_date == null && !s_date.equals("")
				&& e_date.equals("")) {
			e_date = du.date2String(new Date());
			hql.append(" and (t.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && s_date == null && s_date.equals("")
				&& !e_date.equals("")) {
			Date d = du.getAppointDate(730);
			s_date = du.date2String(d);
			hql.append(" and (t.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
				

		int size = pdasmycChdlrDao.getSize(hql.toString());
		if (size > 0) {
			hql.append(" order by t.lrsj desc");
			pdasmycChdlrList = pdasmycChdlrDao.findHQLByPage(hql.toString(),offset,pageSize);
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
		request.setAttribute("pdasmycChdlrList", pdasmycChdlrList);
		log.info("method:getPdasmycChdlrList|param:request="+request+",currentPage="+currentPage);
		return pdasmycChdlrList;
	}

	@SuppressWarnings("unchecked")
	public List<PdasmycChdlr> getPdasmycChdlrList(HttpServletRequest request) throws Exception {
		StringBuffer hql = new StringBuffer("from PdasmycChdlr as t where 1=1 ");
		List<PdasmycChdlr> pdasmycChdlrList = new ArrayList<PdasmycChdlr>();
		pdasmycChdlrList = pdasmycChdlrDao.getRepositories(hql.toString());
		log.info("method:getPdasmycChdlrList|param:request="+request);
		return pdasmycChdlrList;
	}

	public PdasmycChdlr getPdasmycChdlr(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			log.info("method:getPdasmycChdlr|param:id="+id);
			return pdasmycChdlrDao.getRepository(id);
		}
		return null;
	}

	public PdasmycChdlr getPdasmycChdlr(String id) throws Exception {
		log.info("method:getPdasmycChdlr|param:id="+id);
		return pdasmycChdlrDao.getRepository(id);
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
	public List<YwlsglVehSjzd> getYwlsglVehSjzdList(String dmlb) throws Exception {
		List<YwlsglVehSjzd> ywlsglVehSjzdList = new ArrayList<YwlsglVehSjzd>();
		String hql = "from YwlsglVehSjzd ";
		if(null != dmlb && !"".equals(dmlb)){
			hql += "where dmlb='" + dmlb + "'";
		}
		ywlsglVehSjzdList = pdasmycChdlrDao.getRepositories(hql);
		return ywlsglVehSjzdList;
	}
	
	public Integer dlrshzt(HttpServletRequest request,String id, String shzt,String shbz) throws Exception {
		try {
			PdasmycChdlr pdasmycChdlr = pdasmycChdlrDao.getRepository(id);
			if(null != pdasmycChdlr){
				User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
				pdasmycChdlr.setShzt(shzt);
				pdasmycChdlr.setShr(user.getName());
				pdasmycChdlr.setShrxm(user.getYgxm());
				pdasmycChdlr.setShsj(new Date());
				pdasmycChdlr.setShbz(shbz);
				pdasmycChdlr.setShip(getLoginIp(request));
				pdasmycChdlr.setShbm(user.getBmid());
				pdasmycChdlr.setSynFlag("UW");
				pdasmycChdlr.setTranDate(null);
				pdasmycChdlr.setTranFlag(null);
				pdasmycChdlrDao.updateRepository(pdasmycChdlr);
				return 0;
			}else{
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	

	/**
	 * @return the pdasmycChdlrDao
	 */
	public PdasmycChdlrDao getPdasmycChdlrDao() {
		return pdasmycChdlrDao;
	}

	/**
	 * @param pdasmycChdlrDao the pdasmycChdlrDao to set
	 */
	public void setPdasmycChdlrDao(PdasmycChdlrDao pdasmycChdlrDao) {
		this.pdasmycChdlrDao = pdasmycChdlrDao;
	}

	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}

	
}
