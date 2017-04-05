package com.ycszh.ssh.service.veh.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.map.HashedMap;
import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.drv.SlgDrvXxcjbDao;
import com.ycszh.ssh.dao.veh.IVehPodbspDao;
import com.ycszh.ssh.dao.veh.VehSpDao;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.drv.SlgSpxx;
import com.ycszh.ssh.hbm.drv.SlgSpxxLog;
import com.ycszh.ssh.hbm.veh.VehPodbsp;
import com.ycszh.ssh.hbm.veh.VehPodbspLog;
import com.ycszh.ssh.service.drv.SlgDrvService;
import com.ycszh.ssh.service.drv.impl.SlgSpxxServiceImpl;
import com.ycszh.ssh.service.veh.VehSpService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.StringUtil;

import common.Logger;

public class VehSpServiceImpl implements VehSpService{
	
	private String uri;
	private static final Logger logger = Logger.getLogger(SlgSpxxServiceImpl.class);
	private VehSpDao vehSpDao;
	private SlgDrvXxcjbDao slgDrvXxcjbDao;
	private IVehPodbspDao vehPodbspDao;
	private SlgDrvService slgDrvService;
	
	public Integer getSlgSpxxCount(HttpServletRequest request) throws Exception {
		
		return null;
	}
	
	public Integer deleteSlgSpxx(HttpServletRequest request, Long id,
			String cznr) throws Exception {
		if(id != null){
			SlgSpxx slgSpxx = null;
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			String userMac = request.getParameter("userMac");
			userMac = "";
			cznr = "DEL";
			try {
				slgSpxx = this.vehSpDao.getRepository(id);
				if(slgSpxx != null){
					this.vehSpDao.deleteRepository(slgSpxx);
					SlgSpxxLog slgLog = new SlgSpxxLog();
					slgLog = (SlgSpxxLog)getXclog(slgLog, slgSpxx);
					slgLog.setCzr(user.getName());
					slgLog.setCzrxm(user.getYgxm());
					slgLog.setCzbm(user.getBmid());
					slgLog.setCzrq(new Date());
					slgLog.setCzip(getLoginIp(request));
					slgLog.setCznr(cznr);
					slgLog.setCzmac(userMac);
					this.vehSpDao.addObj(slgLog, request);
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

	public SlgSpxx getSlgSpxxById(HttpServletRequest request, Long id)
			throws Exception {
		SlgSpxx slgSp = null;
		if(id != null){
			slgSp = this.vehSpDao.getRepository(id);
			return slgSp;
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SlgSpxx> getSlgSpxxList(HttpServletRequest request,
			int currentPage) throws Exception {
		int count = 0;
		Map map = new HashedMap();
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
		String jbrbm = request.getParameter("jbrbm");
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
		strCondition.append(" and s.splx = 'veh' ");
		//部门（科级）
		if(jbrbm != null && !"".equals(jbrbm)){
			List deptList =  this.slgDrvService.getDeptBelowList(jbrbm);
			if(deptList != null && deptList.size() > 0){
				String czbm = "";
				for(int i = 0; i < deptList.size(); i++){
					Object[] objs = (Object[])deptList.get(i);
					if(objs != null){
						czbm += "'"+objs[0]+"',";
					}
				}
				if(czbm.endsWith(",")){
					czbm = czbm.substring(0, czbm.length()-1);
				}
				strCondition.append(" and s.sprBm in ("+czbm+") ");
			}
			request.setAttribute("jbrbm", jbrbm);
		}

		hqlListBuff.append(strCondition.toString());
		hqlListBuff.append(" ORDER BY s.sprSj DESC ");
		count = this.vehSpDao.getRepositoryByHQLListSize(hqlCountBuff.toString()+strCondition.toString());
		slgSpxxList = new ArrayList<SlgSpxx>();
		slgSpxxList = this.vehSpDao.findHQLByPage(hqlListBuff.toString(), offset, pageSize);
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
					slg = this.vehSpDao.updateRepository(slgSpxx);
					SlgSpxxLog slgLog = new SlgSpxxLog();
					slgLog = (SlgSpxxLog)getXclog(slgLog, slg);
					slgLog.setCzr(user.getName());
					slgLog.setCzrxm(user.getYgxm());
					slgLog.setCzbm(user.getBmid());
					slgLog.setCzrq(new Date());
					slgLog.setCzip(getLoginIp(request));
					slgLog.setCznr(cznr);
					slgLog.setCzmac(userMac);
					this.vehSpDao.addObj(slgLog, request);
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
					slg = this.vehSpDao.addRepository(slgSpxx);
					SlgSpxxLog slgLog = new SlgSpxxLog();
					slgLog = (SlgSpxxLog)getXclog(slgLog, slg);
					slgLog.setCzr(user.getName());
					slgLog.setCzrxm(user.getYgxm());
					slgLog.setCzbm(user.getBmid());
					slgLog.setCzrq(new Date());
					slgLog.setCzip(getLoginIp(request));
					slgLog.setCznr(cznr);
					slgLog.setCzmac(userMac);
					this.vehSpDao.addObj(slgLog, request);
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
	
	
	public Integer insertOrUpdatePoVehSpxx(HttpServletRequest request, VehPodbsp vehPodbsp) throws Exception{
		if(vehPodbsp != null){
			Long id = vehPodbsp.getId();
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			String userMac = request.getParameter("userMac");
			vehPodbsp.setSprCode(user.getYgid());
			vehPodbsp.setSprName(user.getYgxm());
			vehPodbsp.setSprBm(user.getBmid());
			String orgid = getDeptUpid(user.getBmid());
			vehPodbsp.setSprBmkj(orgid);
			vehPodbsp.setSprSj(new Date());
			String hphmval = vehPodbsp.getHphm();
			if(!StringUtil.isNull(hphmval)){
				hphmval = hphmval.toUpperCase();
				if(hphmval.contains("粤")){
					hphmval = hphmval.replaceAll("粤", "");
				}
				vehPodbsp.setHphm(hphmval);
			}
			//格式时间
			if(vehPodbsp.getYxrq() != null){
				String formatdate = DateUtil.date2String(vehPodbsp.getYxrq());
				formatdate = formatdate+" 23:59:59";
				Date date = DateUtil.string2Date(formatdate, "yyyy-MM-dd HH:mm:ss");
				vehPodbsp.setYxrq(date);
			}
			VehPodbsp posp = null;
			if(id != null){
				//修改
				posp = this.vehPodbspDao.updateRepository(vehPodbsp);
				VehPodbspLog vehPoLog = new VehPodbspLog();
				vehPoLog = (VehPodbspLog)getXclog(vehPoLog, posp);
				vehPoLog.setCzr(user.getName());
				vehPoLog.setCzrxm(user.getYgxm());
				vehPoLog.setCzbm(user.getBmid());
				vehPoLog.setCzrq(new Date());
				vehPoLog.setCzip(getLoginIp(request));
				vehPoLog.setCznr("U");
				vehPoLog.setCzmac(userMac);
				this.vehPodbspDao.addObj(vehPoLog, request);
			}else{
				//添加
				posp = this.vehPodbspDao.addRepository(vehPodbsp);
				VehPodbspLog vehPoLog = new VehPodbspLog();
				vehPoLog = (VehPodbspLog)getXclog(vehPoLog, posp);
				vehPoLog.setCzr(user.getName());
				vehPoLog.setCzrxm(user.getYgxm());
				vehPoLog.setCzbm(user.getBmid());
				vehPoLog.setCzrq(new Date());
				vehPoLog.setCzip(getLoginIp(request));
				vehPoLog.setCznr("I");
				vehPoLog.setCzmac(userMac);
				this.vehPodbspDao.addObj(vehPoLog, request);
			}
			return 1;
		}else{
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<VehPodbsp> getVehPodbspList(HttpServletRequest request, int currentPage) throws Exception{
		int count = 0;
		Map map = new HashedMap();
		List<VehPodbsp> vehPodbsps = new ArrayList<VehPodbsp>();
		StringBuffer listhql = new StringBuffer("select t from VehPodbsp t where 1=1 ");
		StringBuffer sizesql = new StringBuffer("select count(1) from veh_podbsp t where 1=1");
		int pagesize = SysConst.PAGESIZE;
		int offset = (currentPage-1)*pagesize;
		String uri = request.getRequestURI();
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		String orgid = getDeptUpid(user.getBmid());
		List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
		hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");
		String hphm = request.getParameter("hphm");
		String hpzl = request.getParameter("hpzl");
		String czSfzmhm = request.getParameter("czSfzmhm");
		String poSfzmhm = request.getParameter("poSfzmhm");
		if(!StringUtil.isNull(hphm)){
			hphm = hphm.toUpperCase();
			if(hphm.contains("粤")){
				hphm = hphm.replaceAll("粤", "");
			}
			listhql.append(" and t.hphm = '"+hphm+"'");
			sizesql.append(" and t.hphm = '"+hphm+"'");
			request.setAttribute("hphm", hphm);
		}
		if(!StringUtil.isNull(hpzl)){
			listhql.append(" and t.hpzl = '"+hpzl+"'");
			sizesql.append(" and t.hpzl = '"+hpzl+"'");
			request.setAttribute("hpzl", hpzl);
		}
		if(!StringUtil.isNull(czSfzmhm)){
			listhql.append(" and t.czSfzmhm = '"+czSfzmhm+"'");
			sizesql.append(" and t.cz_sfzmhm = '"+czSfzmhm+"'");
			request.setAttribute("czSfzmhm", czSfzmhm);
		}
		if(!StringUtil.isNull(poSfzmhm)){
			listhql.append(" and t.poSfzmhm = '"+poSfzmhm+"'");
			sizesql.append(" and t.po_sfzmhm = '"+poSfzmhm+"'");
			request.setAttribute("poSfzmhm", poSfzmhm);
		}
		count = this.vehPodbspDao.getRepositoryBySQLListSize(sizesql.toString());
		if(count > 0){
			listhql.append(" order by t.sprSj desc ");
			vehPodbsps = this.vehPodbspDao.findHQLByPage(listhql.toString(), offset, pagesize);
			if(vehPodbsps != null && vehPodbsps.size() > 0){
				//号牌种类
				Map<String, String> hpzlMap = new HashMap<String, String>();
				if(hpzlList != null && hpzlList.size() > 0){
					for(int i = 0; i < hpzlList.size(); i++){
						SlgSjzd slgSjzd = (SlgSjzd)hpzlList.get(i);
						hpzlMap.put(slgSjzd.getDmz(), slgSjzd.getDmms1());
					}
				}
				//身份证明名称
				Map<String, String> sfzmmcMap = new HashMap<String, String>();
				sfzmmcMap.put("A", "二代居民身份证");
				sfzmmcMap.put("B", "组织机构代码证书");
				sfzmmcMap.put("C", "军官证");
				sfzmmcMap.put("D", "士兵证");
				sfzmmcMap.put("E", "军官离退休证");
				sfzmmcMap.put("F", "境外人员身份证明");
				sfzmmcMap.put("G", "外交人员身份证明");
				sfzmmcMap.put("H", "居民户口薄");
				sfzmmcMap.put("J", "单位注销证明");
				sfzmmcMap.put("K", "居住暂住证明");
				sfzmmcMap.put("L", "驻华机构证明");
				sfzmmcMap.put("M", "临时居民身份证");
				
				for(int i = 0; i < vehPodbsps.size(); i++){
					VehPodbsp podbsp = vehPodbsps.get(i);
					String hpzltext = hpzlMap.get(podbsp.getHpzl());
					if(!StringUtil.isNull(hpzltext)){
						vehPodbsps.get(i).setHpzl(hpzltext);
					}
					String czsfzmmctext = sfzmmcMap.get(podbsp.getCzSfzmmc());
					if(!StringUtil.isNull(czsfzmmctext)){
						vehPodbsps.get(i).setCzSfzmmc(czsfzmmctext);
					}
					
				}
			}
		}
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", count);
		map.put("currentpage", currentPage);
		request.setAttribute("kjbmid", orgid);
		//request.setAttribute("rscount", count);
		request.setAttribute("map", map);
		request.setAttribute("vehPodbsps", vehPodbsps);
		request.setAttribute("hpzlList", hpzlList);
		return vehPodbsps;
	}
	
	public VehPodbsp getVehPodbspById(HttpServletRequest request, Long id) throws Exception{
		VehPodbsp vehPodbsp = null;
		if(id != null){
			vehPodbsp = this.vehPodbspDao.getRepository(id);
		}
		return vehPodbsp;
	}
	
	public Integer deleteVelPoSpxx(HttpServletRequest request, Long id) throws Exception{
		if(id != null){
			VehPodbsp vehPodbsp = null;
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			//String userMac = request.getParameter("userMac");
			vehPodbsp = this.vehPodbspDao.getRepository(id);
			if(vehPodbsp != null){
				this.vehPodbspDao.deleteRepository(vehPodbsp);
				VehPodbspLog vehPoLog = new VehPodbspLog();
				vehPoLog = (VehPodbspLog)getXclog(vehPoLog, vehPodbsp);
				vehPoLog.setCzr(user.getName());
				vehPoLog.setCzrxm(user.getYgxm());
				vehPoLog.setCzbm(user.getBmid());
				vehPoLog.setCzrq(new Date());
				vehPoLog.setCzip(getLoginIp(request));
				vehPoLog.setCznr("D");
				vehPoLog.setCzmac("");
				this.vehPodbspDao.addObj(vehPoLog, request);
				return 1;
			}
			return 2;
		}else{
			return 0;
		}
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
	public String getDeptUpid(String deptid) throws Exception{
		String deptsql = "select org_id from (select t.* from v_veh_org_ycs t start with " +
					"org_id = '" + deptid + "' connect by prior up_org = org_id) " +
					"where up_org = 'C34702A8FED97CBFE040007F0100339B'";
		List deptidlist = vehSpDao.findSQL(deptsql);
		String deptids = "";
		if(null != deptidlist && deptidlist.size() > 0){
			deptids = deptidlist.get(0).toString();
		}
		return deptids;
	}
	
	@SuppressWarnings("unchecked")
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
	
	public VehSpDao getVehSpDao() {
		return vehSpDao;
	}

	public void setVehSpDao(VehSpDao vehSpDao) {
		this.vehSpDao = vehSpDao;
	}

	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	public SlgDrvXxcjbDao getSlgDrvXxcjbDao() {
		return slgDrvXxcjbDao;
	}

	public void setSlgDrvXxcjbDao(SlgDrvXxcjbDao slgDrvXxcjbDao) {
		this.slgDrvXxcjbDao = slgDrvXxcjbDao;
	}

	public IVehPodbspDao getVehPodbspDao() {
		return vehPodbspDao;
	}

	public void setVehPodbspDao(IVehPodbspDao vehPodbspDao) {
		this.vehPodbspDao = vehPodbspDao;
	}

	public SlgDrvService getSlgDrvService() {
		return slgDrvService;
	}

	public void setSlgDrvService(SlgDrvService slgDrvService) {
		this.slgDrvService = slgDrvService;
	}
	 
}
