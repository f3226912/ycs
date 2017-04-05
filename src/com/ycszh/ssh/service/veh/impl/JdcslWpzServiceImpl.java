package com.ycszh.ssh.service.veh.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.dao.drv.SlgDrvFileUploadDao;
import com.ycszh.ssh.dao.drv.SlgYwlxDao;
import com.ycszh.ssh.dao.veh.IItopscDao;
import com.ycszh.ssh.dao.veh.SlgVehDao;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.veh.DbjgZjxxbWzp;
import com.ycszh.ssh.hbm.veh.DbjgZjxxbWzpLog;
import com.ycszh.ssh.hbm.veh.EsVehicle;
import com.ycszh.ssh.hbm.veh.SlgSjzdWzp;
import com.ycszh.ssh.hbm.veh.VehicleTempMidTest;
import com.ycszh.ssh.service.drv.SlgDrvService;
import com.ycszh.ssh.service.dydj.IDydjService;
import com.ycszh.ssh.service.veh.SlgVehWpzService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.StringUtil;

//机动车受理——无拍照
public class JdcslWpzServiceImpl implements SlgVehWpzService {
	private final static Logger log = Logger.getLogger(SlgVehServiceImpl.class);
	private DefaultDao defaultDao;
	private SlgVehDao slgVehDao;
	private SlgDrvFileUploadDao slgDrvFileUpload;
	private SlgDrvService slgDrvService;
	private IDydjService dydjService;
	private IItopscDao itopscDao;
	private DbjgZjxxbWzp dbZjxxb1;
	private SlgYwlxDao slgYwlxDao;
	private String uri = "";
	/**
	 * 受理详情信息查询
	 */
	@SuppressWarnings("unchecked")
	public List<DbjgZjxxbWzp> getSlxqWpz(HttpServletRequest request)
		throws Exception {
		String hql = " from DbjgZjxxbWzp where 1=1";
		//业务类型
		String ywlxSql = "select dmms1 from slg_sjzd_wzp where dmlb = 'JDCYWSL' and veh_drv ='VEH_YWLX'";
		//业务原因
		String ywyySql = "select dmms1 from slg_sjzd_wzp where dmlb = 'JDCYWSL' and veh_drv ='VEH_YWYY'";
		String id = request.getParameter("id");
		if(id!=null&&!id.equals("")){
			hql+=" and id='"+id+"'";
		}
		List list = this.defaultDao.getRepositories(hql);
		DbjgZjxxbWzp dbZjxxb = (DbjgZjxxbWzp) list.get(0);
		if(dbZjxxb!=null){
			//获取业务类型描述
			StringBuffer ywlx = new StringBuffer();
			String ywlxDmz = dbZjxxb.getYwlx();
			String ywyyDmz = dbZjxxb.getYwyy();
			if(ywlxDmz!=null&&!ywlxDmz.equals("")){
				ywlxSql+=" and dmz = '"+ywlxDmz+"'";
				List list1 = this.defaultDao.findSQL(ywlxSql);
				if(list1!=null){
					ywlxDmz=list1.get(0).toString();
					ywlx.append(ywlxDmz);
				}
			}
			if(ywyyDmz!=null&&!ywyyDmz.equals("")){
				ywyySql+=" and dmz = '"+ywyyDmz+"'";
				List list2 = this.defaultDao.findSQL(ywyySql);
				if(list2!=null){
					ywyyDmz=list2.get(0).toString();
					ywlx.append("("+ywyyDmz+")");
				}
			}
			dbZjxxb.setYwlx(ywlx.toString());
			request.setAttribute("dbZjxxb", dbZjxxb);
			//获取登记信息和技术参数
			request.setAttribute("hphm", dbZjxxb.getHphm());
			request.setAttribute("hpzl", dbZjxxb.getHpzl());
			EsVehicle ptdo = this.finjdcslInfo(request);
			request.setAttribute("ptdo", ptdo);
		}
		return null;
	}
	
	/**
	 * 受理信息查询
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public List<DbjgZjxxbWzp> getSlCxListWpz(HttpServletRequest request,
			int currentpage,String cztype) throws Exception {		
		int count=0;
		Map map=new HashedMap();
		StringBuffer hqlListBuff = new StringBuffer("select s from DbjgZjxxbWzp s where 1=1 ");
		StringBuffer hqlCountBuff = new StringBuffer("select count(s) from DbjgZjxxbWzp  s where 1=1 ");
		StringBuffer strCondition = new StringBuffer("");
		String hphm = request.getParameter("hphm");
		String hpzl = request.getParameter("hpzl");
		String ywlx = request.getParameter("ywlx");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String jbr = request.getParameter("jbr");
		String jbrbm = request.getParameter("jbrbm");
		String ywyy = request.getParameter("ywyy");
		String shzt = request.getParameter("shzt");
		String shr = request.getParameter("shr");
		
		DateUtil du = new DateUtil();
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentpage-1);
		List<DbjgZjxxbWzp> dbjgZjxxbList = new ArrayList<DbjgZjxxbWzp>();
		
		if("query".equals(cztype)){
			uri = request.getRequestURI();
		}		
		//号牌号码
		if(hphm != null && !"".equals(hphm)){
			strCondition.append(" and s.hphm like '%"+hphm+"%'");
			request.setAttribute("hphm", hphm);
		}
		//号牌种类
		if(hpzl != null && !"".equals(hpzl)){
			strCondition.append(" and s.hpzl = '"+hpzl+"'");
			request.setAttribute("hpzl", hpzl);
		}
		//业务类型
		if(ywlx != null && !"".equals(ywlx)){
			if(ywlx.equals("A:A")){
				strCondition.append(" and s.ywlx = 'A' and s.ywyy = 'A:A' ");
			}else if(ywlx.equals("B:B")){
				strCondition.append(" and s.ywlx = 'B' and s.ywyy = 'B:B' ");
			}else if(ywlx.equals("B:C")){
				strCondition.append(" and s.ywlx = 'B' and s.ywyy = 'B:C' ");
			}else{
				strCondition.append(" and s.ywlx = '" + ywlx + "' ");
			}
			request.setAttribute("ywlx", ywlx);
		}
		//业务原因
		if(ywyy != null && !"".equals(ywyy)){
			String[] ywyyattr = ywyy.split(",");
			ywyy = "";
			for(int j = 0; j < ywyyattr.length; j++){
				ywyy += "'"+ywyyattr[j]+"' ,";
			}
			if(ywyy.endsWith(",")){
				ywyy = ywyy.substring(0,ywyy.length() - 1);
			}
			strCondition.append("  and s.ywyy in ("+ywyy+") ");
			request.setAttribute("ywyy", ywyy);
		}
		
		if(s_date == null || s_date.equals("")){
			Calendar date = Calendar.getInstance();
			date.setTime(new Date());
			date.set(Calendar.DATE,date.get(Calendar.DATE)-30);
			s_date = DateUtil.date2String(date.getTime());
		}
		
		if(e_date == null || e_date.equals("")){
			e_date = DateUtil.date2String(new Date());
		}
		
		//受理时间
		if (s_date != null && e_date != null && !s_date.equals("")
				&& !e_date.equals("")) {
			strCondition.append(" and (s.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && e_date == null && !s_date.equals("")
				&& e_date.equals("")) {
			e_date = du.date2String(new Date());
			strCondition.append(" and (s.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && s_date == null && s_date.equals("")
				&& !e_date.equals("")) {
			Date d = du.getAppointDate(730);
			s_date = du.date2String(d);
			strCondition.append(" and (s.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		
		//经办人
		if(jbr != null && !"".equals(jbr)){
			strCondition.append(" and s.lrrxm = '" + jbr + "' ");
			request.setAttribute("jbr", jbr);
		}
		//经办人部门
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
				strCondition.append(" and s.lrbmdm in ("+czbm+") ");
			}
			request.setAttribute("jbrbm", jbrbm);
		}
		//审核状态
		if(shzt != null && !shzt.equals("")){
			strCondition.append(" and s.shJg ='"+shzt+"' ");
			request.setAttribute("shzt", shzt);
		}
		//审核人
		if(shr != null && !shr.equals("")){
			strCondition.append(" and s.shXm ='"+shr+"' ");
			request.setAttribute("shr", shr);
		}

		hqlListBuff.append(strCondition.toString());
		hqlListBuff.append(" ORDER BY s.lrsj DESC ");	
		hqlCountBuff.append(strCondition.toString());
		
		count = this.defaultDao.getRepositoryByHQLListSize(hqlCountBuff.toString());
		if(count > 0){
			if("query".equals(cztype)){
				List list = this.defaultDao.findHQLByPage(hqlListBuff.toString(), offset, pageSize);
				dbjgZjxxbList = list;
			}else if("export".equals(cztype)){
				if(count > 10000){
					map.put("uri", uri);
					map.put("pagesize", pageSize);
					map.put("rscount", count);
					map.put("currentpage", currentpage);
					request.setAttribute("rscount", count);
					request.setAttribute("map", map);
					request.setAttribute("dbjgZjxxbList", dbjgZjxxbList);
					request.setAttribute("exportData", "数据量太多无法一次导出，请筛选！");
					return dbjgZjxxbList;
				}
				dbjgZjxxbList = this.slgVehDao.getRepositories(hqlListBuff.toString());
			}
			
			//查询所有业务类型
			List<SlgSjzd> ywlxList = new ArrayList<SlgSjzd>();
			ywlxList = this.slgDrvService.getYwlxYwyy(request, "", "", "JDCYWSL", "VEH_YWLX", "");
			Map<String, String> ywlxMap = new HashMap<String, String>();
			if (ywlxList != null && ywlxList.size() > 0) {
				for (int i = 0; i < ywlxList.size(); i++) {
					SlgSjzd sjzd = (SlgSjzd) ywlxList.get(i);
					ywlxMap.put(sjzd.getDmz(), sjzd.getDmms1());
				}
			}
			
			//查询所有业务原因
			List<SlgSjzd> ywyyList = new ArrayList<SlgSjzd>();
			ywyyList = this.slgDrvService.getYwlxYwyy(request, "", "", "JDCYWSL", "VEH_YWYY", "");
			Map<String, String> ywyyMap = new HashMap<String, String>();
			if (ywyyList != null && ywyyList.size() > 0) {
				for (int i = 0; i < ywyyList.size(); i++) {
					SlgSjzd sjzd = (SlgSjzd) ywyyList.get(i);
					ywyyMap.put(sjzd.getDmz(), sjzd.getDmms1());
				}
			}
			
			for(int i = 0; i < dbjgZjxxbList.size(); i++){
				DbjgZjxxbWzp zjxx = dbjgZjxxbList.get(i);
				String ywlxval = "";
				String[] ywlxarr = null;
				String[] ywyyarr = null;
				if(!StringUtil.isNull(zjxx.getYwlx()) && !StringUtil.isNull(zjxx.getYwyy())){
					if("A".equals(zjxx.getYwlx()) && "A:A".equals(zjxx.getYwyy())){
						dbjgZjxxbList.get(i).setYwlx("注册登记");
						continue;
					}
					if("B".equals(zjxx.getYwlx()) && "B:B".equals(zjxx.getYwyy())){
						dbjgZjxxbList.get(i).setYwlx("转移登记(市内过户)");
						continue;
					}
					if("B".equals(zjxx.getYwlx()) && "B:C".equals(zjxx.getYwyy())){
						dbjgZjxxbList.get(i).setYwlx("转移登记(市外过户)");
						continue;
					}
				}
				if(!StringUtil.isNull(zjxx.getYwlx())){
					ywlxarr = zjxx.getYwlx().split(",");
				}
				if(!StringUtil.isNull(zjxx.getYwyy())){
					ywyyarr = zjxx.getYwyy().split(",");
				}
				if(ywlxarr != null){
					for(int j = 0; j < ywlxarr.length; j++){
						ywlxval += (ywlxMap.get(ywlxarr[j])==null || "".equals(ywlxMap.get(ywlxarr[j]))?ywlxarr[j]:ywlxMap.get(ywlxarr[j]));
						if(ywyyarr != null){
							String ywyystr = "";
							for(int k = 0; k < ywyyarr.length; k++){
								String ywyyval = ywyyarr[k];
								String[] ywyysplit = ywyyval.split(":");
								if(ywyysplit != null && ywyysplit.length >= 2){
									if(ywlxarr[j].equals(ywyysplit[0])){
										ywyystr += (ywyyMap.get(ywyysplit[1])==null || "".equals(ywyyMap.get(ywyysplit[1])))?ywyysplit[1]:ywyyMap.get(ywyysplit[1])+",";
									}
								}
							}
							if(ywyystr != null && !"".equals(ywyystr)){
								if(ywyystr.endsWith(",")){
									ywyystr =  ywyystr.substring(0, ywyystr.length()-1);
								}
								ywlxval += "("+ywyystr+")";
							}
						}
						ywlxval += ",";
					}
				}
				
				if(ywlxval.endsWith(",")){
					ywlxval = ywlxval.substring(0, ywlxval.length()-1);
				}
				dbjgZjxxbList.get(i).setYwlx(ywlxval);
			}
			
		}
		map.put("uri", uri);
		map.put("pagesize", pageSize);
		map.put("rscount", count);
		map.put("currentpage", currentpage);
		request.setAttribute("rscount", count);
		request.setAttribute("map", map);
		request.setAttribute("dbjgZjxxbList", dbjgZjxxbList);
		return dbjgZjxxbList;
	}
	
	//获取经办人部门
	@SuppressWarnings("unchecked")
	public List getDeptList(String orgid, String uporg, String orgname) throws Exception{
		List deptList = null;
		String sql = "select t.org_id, t.org_name from v_veh_org_ycs t where 1=1 ";
		if(!StringUtil.isNull(orgid)){
			sql += " and t.org_id = '"+orgid+"'";
		}
		if(!StringUtil.isNull(uporg)){
			sql += " and t.up_org = '"+uporg+"'";
		}
		if(!StringUtil.isNull(orgname)){
			sql += " and t.org_name like '%"+orgname+"%'";
		}
		deptList = defaultDao.findSQL(sql);
		return deptList;
		
	}
	
	/**
	 * 添加机动车受理信息
	 * dbZjxxb 	证件信息
	 * ptdo		登记信息、技术参数
	 * file1	当事人身份证头像
	 * file2	代办人身份证头像
	 */
	public Integer insertJdcslWpz(HttpServletRequest request,
			DbjgZjxxbWzp dbZjxxb, VehicleTempMidTest ptdo, File file1,
			File file2) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		if(dbZjxxb!=null){
			//办理类型 1:为本人办理 2:为他人代办
			if("1".equals(dbZjxxb.getBllx())){ 
				//(清空代办人信息)
				dbZjxxb.setDbrSfzmmc("");
				dbZjxxb.setDbrSfzCardname1("");
				dbZjxxb.setDbrSfzCardsex1("");
				dbZjxxb.setDbrSfzCardno1("");
				dbZjxxb.setDbrSfzCardaddress1("");
				file2 = null;
			}
			
			//获取录入人信息
			dbZjxxb.setLrr(user.getName());				//录入人代码
			dbZjxxb.setLrrxm(user.getYgxm());			//录入人姓名
			dbZjxxb.setLrbmdm(user.getBmid());			//部门编号
			dbZjxxb.setLrbmmc(user.getBmmc());			//部门名称
			dbZjxxb.setLrsj(new Date());				//录入时间
			dbZjxxb.setLrip(getLoginIp(request));		//录入IP	
			dbZjxxb.setLrmac(request.getLocalAddr());	//录入MAC
			dbZjxxb.setShJg("0");						//审核结果（默认状态为0:未审核）
			//如果号牌包含粤则去掉
			String hphm = dbZjxxb.getHphm();			
			if(!StringUtil.isNull(hphm)){
				hphm = hphm.toUpperCase();
				if(hphm.contains("粤")){
					hphm = hphm.replaceAll("粤", "");
				}
				dbZjxxb.setHphm(hphm);
			}
			
			//上传身份证头像
			if(null != file1){
				String strtemp = slgDrvFileUpload.uploadFile(file1);
				if(null != strtemp && !"".equals(strtemp)){
					dbZjxxb.setDsrSfzCardphoto1(strtemp);
				}else{
					Exception etemp = new Exception("异常:当事人身份证照片上传失败,请重新点击保存按钮!");
					throw etemp;
				}
			}
			if(null != file2){
				String strtemp = slgDrvFileUpload.uploadFile(file2);
				if(null != strtemp && !"".equals(strtemp)){
					dbZjxxb.setDbrSfzCardphoto1(strtemp);
				}else{
					Exception etemp = new Exception("异常:代办人身份证照片上传失败,请重新点击保存按钮!");
					throw etemp;
				}
			}
			
			dbZjxxb1=(DbjgZjxxbWzp) defaultDao.addRepository(dbZjxxb); 
			if(dbZjxxb1!=null){
				DbjgZjxxbWzpLog wpzlog = new DbjgZjxxbWzpLog();
				wpzlog=(DbjgZjxxbWzpLog) this.getXclog(wpzlog,dbZjxxb1);
				wpzlog.setCzr(user.getName());
				wpzlog.setCzrxm(user.getYgxm());
				wpzlog.setCzrbm(user.getBmid());
				wpzlog.setCznr("I");
				wpzlog.setCzsj(new Date());
				wpzlog.setCzip(getLoginIp(request));
				wpzlog.setCzmac("");
				wpzlog=(DbjgZjxxbWzpLog) defaultDao.addRepository(wpzlog); 
				if(wpzlog!=null){
					return 1;
				}else{
					return -1;
				}
			}else{
				return -1;
			}
		}	
		return null;
	}

	
	
	/**
	 * 根据号牌号码、种类获取机动车登记信息和技术参数
	 */
	@SuppressWarnings("unchecked")
	public EsVehicle finjdcslInfo(HttpServletRequest request) throws Exception{
		String hphm = request.getParameter("hphm");
		String hpzl = request.getParameter("hpzl");
		if(hphm==null||hphm.equals("")){
			hphm = (String) request.getAttribute("hphm");
		}
		if(hpzl==null||hpzl.equals("")){
			hpzl = (String) request.getAttribute("hpzl");
		}
		String hql = " from EsVehicle where 1=1";
		@SuppressWarnings("unused")
		EsVehicle wpz = new EsVehicle();
		List<EsVehicle> list = new ArrayList();	
		if(hphm!=null&&hpzl!=null&&!hphm.equals("")&&!hpzl.equals("")){
			hql+=" and hphm='"+hphm+"' and hpzl='"+hpzl+"'";
			list = slgVehDao.getRepositories(hql);
			wpz=list.get(0);
		}
		return wpz;
	}
	
	//加载业务类型及业务原因（字典表）
	@SuppressWarnings("unchecked")
	public List<SlgSjzdWzp> getYwlxYwyy_wpz(HttpServletRequest request, String dmz, String dmms2, String dmlb, String veh_drv, String is_flag) throws Exception{
		List<SlgSjzdWzp> slgSjzds = null;
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		StringBuffer hqlbuffer = new StringBuffer("from SlgSjzdWzp as s where 1=1 ");
		String con = " and s.id in (select sjzdId from SlgUserYwlx t where t.userId = '"+user.getName()+"')";
		if(dmlb != null && !"".equals(dmlb)){
			hqlbuffer.append(" and s.dmlb = '" + dmlb + "'");
		}
		if(dmz != null && !"".equals(dmz)){
			hqlbuffer.append(" and s.dmz = '"+dmz+"'");
		}
		if(dmms2 != null && !"".equals(dmms2)){
			hqlbuffer.append(" and s.dmms2 = '"+dmms2+"'");
		}
		if(veh_drv != null && !"".equals(veh_drv)){
			hqlbuffer.append(" and s.vehDrv = '"+veh_drv+"'");
		}
		List list1 = this.defaultDao.getRepositories(hqlbuffer.toString()+con+" order by s.dmz asc");
		slgSjzds = list1;
		if(slgSjzds == null || slgSjzds.size() == 0){
			if(!StringUtil.isNull(is_flag)){
				hqlbuffer.append(" and s.isFlag = '"+is_flag+"'");
			}
			hqlbuffer.append(" order by s.dmz asc");
			List list2 = this.defaultDao.getRepositories(hqlbuffer.toString());
			slgSjzds = list2;
		}
		return slgSjzds;
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
	
	
	/**
	 * 将实体表添加到日志表
	 */
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
			log.error(e);
		}
		
		return obj1;
	}
	
	
	public DefaultDao getDefaultDao() {
		return defaultDao;
	}
	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}
	public SlgVehDao getSlgVehDao() {
		return slgVehDao;
	}
	public void setSlgVehDao(SlgVehDao slgVehDao) {
		this.slgVehDao = slgVehDao;
	}
	public SlgDrvFileUploadDao getSlgDrvFileUpload() {
		return slgDrvFileUpload;
	}
	public void setSlgDrvFileUpload(SlgDrvFileUploadDao slgDrvFileUpload) {
		this.slgDrvFileUpload = slgDrvFileUpload;
	}
	public SlgDrvService getSlgDrvService() {
		return slgDrvService;
	}
	public void setSlgDrvService(SlgDrvService slgDrvService) {
		this.slgDrvService = slgDrvService;
	}
	public IDydjService getDydjService() {
		return dydjService;
	}
	public void setDydjService(IDydjService dydjService) {
		this.dydjService = dydjService;
	}
	public IItopscDao getItopscDao() {
		return itopscDao;
	}
	public void setItopscDao(IItopscDao itopscDao) {
		this.itopscDao = itopscDao;
	}
	public DbjgZjxxbWzp getDbZjxxb1() {
		return dbZjxxb1;
	}
	public void setDbZjxxb1(DbjgZjxxbWzp dbZjxxb1) {
		this.dbZjxxb1 = dbZjxxb1;
	}
	public SlgYwlxDao getSlgYwlxDao() {
		return slgYwlxDao;
	}
	public void setSlgYwlxDao(SlgYwlxDao slgYwlxDao) {
		this.slgYwlxDao = slgYwlxDao;
	}


	
}
