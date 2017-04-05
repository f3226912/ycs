package com.ycszh.ssh.service.cljstj.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.hbm.cljstj.HosHospitalUserinfo;
import com.ycszh.ssh.service.cljstj.HospitalMessageService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.ToolsUtil;

public class HospitalMessageImpl implements HospitalMessageService {
	
	private DefaultDao defaultDao;
	private final static Logger logger = Logger.getLogger(HospitalMessageImpl.class);

	@SuppressWarnings("unchecked")
	public List<HosHospitalUserinfo> hospitalUserInfoQuery(HttpServletRequest request, int currentpage) throws Exception {
		String uri = request.getRequestURI();
		int pagesize = 12;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;
		
		String yymc = checkStrNullAndReturn(request.getParameter("yymc"));
		String zzjgdmzh = checkStrNullAndReturn(request.getParameter("zzjgdmzh"));
	
		StringBuffer sql = new StringBuffer(" select t.* from hos_hospital_userinfo t where 1=1 ");
		
			if (yymc != null && !yymc.equals("")) {
				yymc = yymc.replaceAll(" ", "");
				yymc = yymc.replaceAll("'", "");
				yymc = yymc.replaceAll("\"", "");
				yymc = yymc.replaceAll("，", ",");
				sql.append(" and t.yymc like '%"+yymc+"%' ");
				request.setAttribute("yymc", yymc);
			}
			if (zzjgdmzh != null && !zzjgdmzh.equals("")) {
				zzjgdmzh = zzjgdmzh.replaceAll(" ", "");
				zzjgdmzh = zzjgdmzh.replaceAll("'", "");
				zzjgdmzh = zzjgdmzh.replaceAll("\"", "");
				zzjgdmzh = zzjgdmzh.replaceAll("，", ",");
				sql.append(" and t.zzjgdmz = '"+zzjgdmzh+"' ");
				request.setAttribute("zzjgdmzh", zzjgdmzh);
			}
        sql.append(" order by t.yyxh desc ");
		
        List<HosHospitalUserinfo> hospitalUserinfoList = defaultDao.findSQLByPage(sql.toString(), offset, pagesize,HosHospitalUserinfo.class);
		rscount = defaultDao.getRepositoryBySQLListSize(" select count(*) from ( " + sql.toString() + " ) ");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);
		return hospitalUserinfoList;
	}
	
	@SuppressWarnings("unchecked")
	public void doctorUserInfoAuditQuery(HttpServletRequest request, int currentpage) throws Exception {
		String uri = request.getRequestURI();
		int pagesize = 12;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;
		
		String yssfzmhm = checkStrNullAndReturn(request.getParameter("yssfzmhm"));
		String yymc = checkStrNullAndReturn(request.getParameter("yymc"));
		String zzjgdmz = checkStrNullAndReturn(request.getParameter("zzjgdmz"));
	
		StringBuffer sql = new StringBuffer(" select t.*,t1.yymc from hos_doctor_userinfo t  inner join hos_hospital_userinfo t1 on t.zzjgdmz = t1.zzjgdmz where t.zzjgdmz = '"+zzjgdmz+"' ");
		   if (yssfzmhm != null && !yssfzmhm.equals("")) {
			   yssfzmhm = yssfzmhm.replaceAll(" ", "");
			   yssfzmhm = yssfzmhm.replaceAll("'", "");
			   yssfzmhm = yssfzmhm.replaceAll("\"", "");
			   yssfzmhm = yssfzmhm.replaceAll("，", ",");
				sql.append(" and t.sfzmhm = '"+yssfzmhm+"' ");
				request.setAttribute("yssfzmhm", yssfzmhm);
			}
		   if (yymc != null && !yymc.equals("")) {
				yymc = yymc.replaceAll(" ", "");
				yymc = yymc.replaceAll("'", "");
				yymc = yymc.replaceAll("\"", "");
				yymc = yymc.replaceAll("，", ",");
				sql.append(" and t1.yymc like '%"+yymc+"%' ");
				request.setAttribute("yymc", yymc);
			}
        sql.append(" order by t.xh desc ");
		
        List doctorUserInfoAuditList = defaultDao.findSQLByPage(sql.toString(), offset, pagesize);
		rscount = defaultDao.getRepositoryBySQLListSize(" select count(*) from ( " + sql.toString() + " ) ");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("doctorUserInfoAuditList", doctorUserInfoAuditList);
		request.setAttribute("map", map);
	}
	
	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}
	
	public String checkStrNullAndReturn(Object str) {
		String returnStr = "";
		try {
			returnStr = str == null ? "" : str.toString().trim();
		} catch (Exception e) {
			return "";
		}
		return returnStr;
	}

	public String insertHospitalUserInfo(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String zzjgdmz = checkStrNullAndReturn(request.getParameter("zzjgdmz"));
		String yhmm = checkStrNullAndReturn(request.getParameter("yhmm"));
		String yymc = checkStrNullAndReturn(request.getParameter("yymc"));
		String lxdh = checkStrNullAndReturn(request.getParameter("lxdh"));
		String lxdz = checkStrNullAndReturn(request.getParameter("lxdz"));
		String yylxrxm = checkStrNullAndReturn(request.getParameter("yylxrxm"));
		String yylxrsfzmhm = checkStrNullAndReturn(request.getParameter("yylxrsfzmhm"));
		String yylxrdh = checkStrNullAndReturn(request.getParameter("yylxrdh"));
		String stopDate = checkStrNullAndReturn(request.getParameter("stopDate"));
		String userlevel = checkStrNullAndReturn(request.getParameter("userlevel"));
		String yyxh = checkStrNullAndReturn(request.getParameter("yyxh"));  //医院序号
		HosHospitalUserinfo hospitalUserinfo = new HosHospitalUserinfo();
		if("".equals(yyxh)){
			hospitalUserinfo.setZzjgdmz(zzjgdmz);
			hospitalUserinfo.setYhmm(yhmm);
			hospitalUserinfo.setYymc(yymc);
			hospitalUserinfo.setLxdh(lxdh);
			hospitalUserinfo.setLxdz(lxdz);
			hospitalUserinfo.setYylxrxm(yylxrxm);
			hospitalUserinfo.setYylxrsfzmhm(yylxrsfzmhm);
			hospitalUserinfo.setYylxrdh(yylxrdh);
			hospitalUserinfo.setStopDate(DateUtil.string2Date(stopDate,"yyyy-MM-dd HH:mm:ss"));
			hospitalUserinfo.setLrr(user.getName());
			hospitalUserinfo.setLrrdm(user.getBmid());
			hospitalUserinfo.setLrrbm(user.getBmmc());
			hospitalUserinfo.setLrip(ToolsUtil.getIpAddr(request));
			hospitalUserinfo.setUserlevel(userlevel);
			hospitalUserinfo.setZt("0");
			hospitalUserinfo.setSynFlag("UW");
			 try{
				 this.defaultDao.addRepository(hospitalUserinfo);
				 logger.info("method:insertHospitalUserInfo|param:HosHospitalUserinfo"+hospitalUserinfo);
				 return "1";
			 }catch (Exception e) {
				e.printStackTrace();
				return "0";
			}
		}else{
			try{
				 HosHospitalUserinfo hUserinfo = (HosHospitalUserinfo)this.defaultDao.findSQL("select * from hos_hospital_userinfo t where t.yyxh ='"+yyxh+"' ", HosHospitalUserinfo.class).get(0);
				 hUserinfo.setZzjgdmz(zzjgdmz);
				 hUserinfo.setYhmm(yhmm);
				 hUserinfo.setYymc(yymc);
				 hUserinfo.setLxdh(lxdh);
				 hUserinfo.setLxdz(lxdz);
				 hUserinfo.setYylxrxm(yylxrxm);
				 hUserinfo.setYylxrsfzmhm(yylxrsfzmhm);
				 hUserinfo.setYylxrdh(yylxrdh);
				 hUserinfo.setStopDate(DateUtil.string2Date(stopDate,"yyyy-MM-dd HH:mm:ss"));
				 hUserinfo.setUserlevel(userlevel);
				 this.defaultDao.updateRepository(hUserinfo);
				 logger.info("method:updateHospitalUserInfo|param:HosHospitalUserinfo"+hUserinfo);
				 return "2";
			 }catch (Exception e) {
				e.printStackTrace();
				return "0";
			}
		}
	}
	
	public HosHospitalUserinfo queryHospitalUserInfoByYyxh(HttpServletRequest request) throws Exception{
		 String yyxh = checkStrNullAndReturn(request.getParameter("yyxh"));
		 HosHospitalUserinfo hUserinfo = (HosHospitalUserinfo)this.defaultDao.findSQL("select * from hos_hospital_userinfo t where t.yyxh ='"+yyxh+"' ", HosHospitalUserinfo.class).get(0);
		 return hUserinfo;
	}
	
	public String deleteHospitalUserInfoByYyxh(HttpServletRequest request) throws Exception{
		 String yyxh = checkStrNullAndReturn(request.getParameter("yyxh"));
		 HosHospitalUserinfo hUserinfo = (HosHospitalUserinfo)this.defaultDao.findSQL("select * from hos_hospital_userinfo t where t.yyxh ='"+yyxh+"' ", HosHospitalUserinfo.class).get(0);
		 if(null != hUserinfo){
			  this.defaultDao.deleteRepository(hUserinfo);
			  return "1";
		 }else {
			 return "0";
		}
	}
	
	public String deleteHospitalUserInfoAll(HttpServletRequest request) throws Exception{
		 String yyxhs = checkStrNullAndReturn(request.getParameter("yyxhs"));
		 if(!"".equals(yyxhs)){
			 String [] yyxh = yyxhs.split(",");
			 for(int i=0;i<yyxh.length;i++){
				 HosHospitalUserinfo hUserinfo = (HosHospitalUserinfo)this.defaultDao.findSQL("select * from hos_hospital_userinfo t where t.yyxh ='"+yyxh[i]+"' ", HosHospitalUserinfo.class).get(0);
				 this.defaultDao.deleteRepository(hUserinfo);
			 }
			return "1";
		 }else {
			return "0";
		}
	}
	
	public String hospitalUserStartOrStop(HttpServletRequest request) throws Exception{
		 String yyxh = checkStrNullAndReturn(request.getParameter("yyxh"));
		 String zt = checkStrNullAndReturn(request.getParameter("zt"));
		 if(!"".equals(yyxh)){
			 try{
				 this.defaultDao.updateRepositoryBySql("update hos_hospital_userinfo t set t.zt = '"+zt+"' where t.yyxh ='"+yyxh+"' ");
				 return "1";
			 }catch(Exception e){
				e.printStackTrace();
				return "0";
			}
		 }else {
			return "0";
		}
	}
	
	public String doctormessageAudit(HttpServletRequest request) throws Exception{
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String xh = checkStrNullAndReturn(request.getParameter("xh"));
		String zt = checkStrNullAndReturn(request.getParameter("zt"));
		String shr = user.getName();
		String shrbm = user.getBmmc();
		String ship = ToolsUtil.getIpAddr(request);
		 if(!"".equals(xh) && !"".equals(zt)){
			 try{
				 this.defaultDao.updateRepositoryBySql("update hos_doctor_userinfo t set t.zt = '"+zt+"',t.syn_flag = 'UW',t.shr ='"+shr+"',t.shrbm = '"+shrbm+"',t.ship ='"+ship+"' where t.xh ='"+xh+"' ");
				 if("1".equals(zt)){
					 return "1";
				 }else if("-1".equals(zt) || "TB".equals(zt)){
					 return "2";
				 }
			 }catch(Exception e){
				e.printStackTrace();
				return "0";
			}
		 }else {
			return "0";
		}
		 return "0";
	}
}