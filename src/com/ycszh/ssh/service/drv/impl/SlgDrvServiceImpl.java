package com.ycszh.ssh.service.drv.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.ycszh.ssh.dao.drv.IDpublishDao;
import com.ycszh.ssh.dao.drv.SlgDrvFileUploadDao;
import com.ycszh.ssh.dao.drv.SlgDrvXxcjbDao;
import com.ycszh.ssh.dao.drv.SlgDrvXxcjbLogDao;
import com.ycszh.ssh.dao.drv.SlgSjzdDao;
import com.ycszh.ssh.dao.drv.SlgYwlxDao;
import com.ycszh.ssh.dao.drv.SlgZjxxbDao;
import com.ycszh.ssh.dao.drv.YujingDao;
import com.ycszh.ssh.dao.veh.IItopscDao;
import com.ycszh.ssh.hbm.drv.SlgDrvXxcjb;
import com.ycszh.ssh.hbm.drv.SlgDrvXxcjbLog;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.drv.SlgYwlx;
import com.ycszh.ssh.hbm.drv.SlgZjxxb;
import com.ycszh.ssh.service.drv.SlgDrvService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.ImageUtils;
import com.ycszh.util.StringUtil;


/**
 * @包名:com.ycszh.ssh.service.drv.impl
 * @文件名:SlgDrvServiceImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class SlgDrvServiceImpl implements SlgDrvService {
	
	private SlgDrvXxcjbDao slgDrvXxcjbDao;
	private SlgDrvXxcjbLogDao slgDrvXxcjbLogDao;
	private SlgZjxxbDao slgZjxxbDao;
	private SlgSjzdDao slgSjzdDao;
	private SlgYwlxDao slgYwlxDao;
	private YujingDao yujingDao;
	private SlgDrvFileUploadDao slgDrvFileUpload;
	private IDpublishDao dpublishDao;
	private IItopscDao itopscDao;
	private final static Logger log = Logger.getLogger(SlgDrvServiceImpl.class);

	@SuppressWarnings("deprecation")
	public Integer insertOrUpdateSlgDrvXxcjb2(SlgDrvXxcjb slgDrvXxcjb,SlgZjxxb slgZjxxb,HttpServletRequest request,File file1,File file2,File file01,File file02) throws Exception {
		if (slgDrvXxcjb != null) {
			String cjid = slgDrvXxcjb.getCjid();
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			slgDrvXxcjb.setCzr(user.getName());
			slgDrvXxcjb.setCzrxm(user.getYgxm());
			slgDrvXxcjb.setCzbm(user.getBmid());
			slgDrvXxcjb.setCzrq(new Date());
			slgDrvXxcjb.setCzip(getLoginIp(request));
			slgDrvXxcjb.setCzmac("");
			//去除业务类型中可能出现的空格
			if(null != slgDrvXxcjb.getYwyy() && !"".equals(slgDrvXxcjb.getYwyy()))slgDrvXxcjb.setYwyy(slgDrvXxcjb.getYwyy().replaceAll(" ", "").replaceAll("　", ""));
			if(null == cjid || "".equals(cjid)){
				try {
					slgDrvXxcjb.setCznr("I");
					slgDrvXxcjb.setShJg("0");
					SlgDrvXxcjb sdx = slgDrvXxcjbDao.addRepository(slgDrvXxcjb);
					SlgDrvXxcjbLog sdxlog = insertSlgDrvXxcjbLog(sdx);
					sdxlog.setOpr(user.getName());
					sdxlog.setOptime(new Date());
					sdxlog.setOpration("I");
					slgDrvXxcjbLogDao.addRepository(sdxlog);
					slgZjxxb.setCjid(sdx.getCjid());
					/********************************以下代码在测试环境下需注释*****************************************/
					String basePath = request.getRealPath("/");
					File f = new File(basePath + "\\images\\shuiyin.png");
					if(null != file1){
						String strtemp = slgDrvFileUpload.uploadFile(file1);
						if(null != strtemp && !"".equals(strtemp)){
							slgZjxxb.setCardphoto(strtemp);
						}else{
							Exception etemp = new Exception("异常:驾驶人照片上传失败,请重新点击保存按钮!");
							throw etemp;
						}
					}
					if(null != file2){
						String strtemp = slgDrvFileUpload.uploadFile(file2);
						if(null != strtemp && !"".equals(strtemp)){
							slgZjxxb.setCardphotoDbr(strtemp);
						}else{
							Exception etemp = new Exception("异常:代办人照片上传失败,请重新点击保存按钮!");
							throw etemp;
						}
					}
					if(null != file01){
						//System.out.println(file01.length());
						ImageUtils.pressImage(file01, f);
						//System.out.println(file01.length());
						String strtemp = slgDrvFileUpload.uploadFile(file01);
						if(null != strtemp && !"".equals(strtemp)){
							slgZjxxb.setSxtZp(strtemp);
						}else{
							Exception etemp = new Exception("异常:现场头像照片上传失败,请重新点击保存按钮!");
							throw etemp;
						}
					}
					if(null != file02){
						ImageUtils.pressImage(file02, f);
						String strtemp = slgDrvFileUpload.uploadFile(file02);
						if(null != strtemp && !"".equals(strtemp)){
							slgZjxxb.setGpyZp(strtemp);
						}else{
							Exception etemp = new Exception("异常:高拍仪照片上传失败,请重新点击保存按钮!");
							throw etemp;
						}
					}
					/********************************以上代码在测试环境下需注释*****************************************/
					slgZjxxbDao.addRepository(slgZjxxb);
					
					log.info("method:insertOrUpdateSlgDrvXxcjb|param:slgDrvXxcjb="+slgDrvXxcjb);
					return 0;
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e);
					throw e;
				}
			}else{
				try {
					slgDrvXxcjb.setCznr("U");
					SlgDrvXxcjb sdx = slgDrvXxcjbDao.updateRepository(slgDrvXxcjb);
					SlgDrvXxcjbLog sdxlog = insertSlgDrvXxcjbLog(sdx);
					sdxlog.setOpr(user.getName());
					sdxlog.setOptime(new Date());
					sdxlog.setOpration("U");
					slgDrvXxcjbLogDao.addRepository(sdxlog);
					log.info("method:insertOrUpdateSlgDrvXxcjb|param:slgDrvXxcjb="+slgDrvXxcjb);
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
	
	public Integer insertOrUpdateSlgDrvXxcjb(SlgDrvXxcjb slgDrvXxcjb,SlgZjxxb slgZjxxb,HttpServletRequest request,File file1,File file2,File file01,File file02) throws Exception {
		if (slgDrvXxcjb != null) {
			String cjid = slgDrvXxcjb.getCjid();
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			slgDrvXxcjb.setCzr(user.getName());
			slgDrvXxcjb.setCzrxm(user.getYgxm());
			slgDrvXxcjb.setCzbm(user.getBmid());
			slgDrvXxcjb.setCzrq(new Date());
			slgDrvXxcjb.setCzip(getLoginIp(request));
			slgDrvXxcjb.setCzmac("");
			//去除业务类型中可能出现的空格
			if(null != slgDrvXxcjb.getYwyy() && !"".equals(slgDrvXxcjb.getYwyy()))slgDrvXxcjb.setYwyy(slgDrvXxcjb.getYwyy().replaceAll(" ", "").replaceAll("　", ""));
			if(null == cjid || "".equals(cjid)){
				try {
					slgDrvXxcjb.setCznr("I");
					SlgDrvXxcjb sdx = slgDrvXxcjbDao.addRepository(slgDrvXxcjb);
					SlgDrvXxcjbLog sdxlog = insertSlgDrvXxcjbLog(sdx);
					sdxlog.setOpr(user.getName());
					sdxlog.setOptime(new Date());
					sdxlog.setOpration("I");
					slgDrvXxcjbLogDao.addRepository(sdxlog);
					slgZjxxb.setCjid(sdx.getCjid());
					/********************************以下代码在测试环境下需注释*****************************************/
					if(null != file1){
						String strtemp = slgDrvFileUpload.uploadFile(file1);
						if(null != strtemp && !"".equals(strtemp)){
							slgZjxxb.setCardphoto(strtemp);
						}else{
							Exception etemp = new Exception("异常:驾驶人照片上传失败,请重新点击保存按钮!");
							throw etemp;
						}
					}
					if(null != file2){
						String strtemp = slgDrvFileUpload.uploadFile(file2);
						if(null != strtemp && !"".equals(strtemp)){
							slgZjxxb.setCardphotoDbr(strtemp);
						}else{
							Exception etemp = new Exception("异常:代办人照片上传失败,请重新点击保存按钮!");
							throw etemp;
						}
					}
					if(null != file01){
						String strtemp = slgDrvFileUpload.uploadFile(file01);
						if(null != strtemp && !"".equals(strtemp)){
							slgZjxxb.setSxtZp(strtemp);
						}else{
							Exception etemp = new Exception("异常:现场头像照片上传失败,请重新点击保存按钮!");
							throw etemp;
						}
					}
					if(null != file02){
						String strtemp = slgDrvFileUpload.uploadFile(file02);
						if(null != strtemp && !"".equals(strtemp)){
							slgZjxxb.setGpyZp(strtemp);
						}else{
							Exception etemp = new Exception("异常:高拍仪照片上传失败,请重新点击保存按钮!");
							throw etemp;
						}
					}
					/********************************以上代码在测试环境下需注释*****************************************/
					slgZjxxbDao.addRepository(slgZjxxb);
					log.info("method:insertOrUpdateSlgDrvXxcjb|param:slgDrvXxcjb="+slgDrvXxcjb);
					return 0;
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e);
					throw e;
				}
			}else{
				try {
					slgDrvXxcjb.setCznr("U");
					SlgDrvXxcjb sdx = slgDrvXxcjbDao.updateRepository(slgDrvXxcjb);
					SlgDrvXxcjbLog sdxlog = insertSlgDrvXxcjbLog(sdx);
					sdxlog.setOpr(user.getName());
					sdxlog.setOptime(new Date());
					sdxlog.setOpration("U");
					slgDrvXxcjbLogDao.addRepository(sdxlog);
					log.info("method:insertOrUpdateSlgDrvXxcjb|param:slgDrvXxcjb="+slgDrvXxcjb);
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
	public Integer deleteSlgDrvXxcjb(String cjid) throws Exception {
		try {
			SlgDrvXxcjb slgDrvXxcjb = slgDrvXxcjbDao.getRepository(cjid);
			if (slgDrvXxcjb != null) {
				slgDrvXxcjbDao.deleteRepository(slgDrvXxcjb);
				HttpServletRequest request = ServletActionContext.getRequest();
				User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
				SlgDrvXxcjbLog slgDrvXxcjbLog = insertSlgDrvXxcjbLog(slgDrvXxcjb);
				slgDrvXxcjbLog.setOpr(user.getName());
				slgDrvXxcjbLog.setOpration("D");
				slgDrvXxcjbLog.setOptime(new Date());
				slgDrvXxcjbLogDao.addRepository(slgDrvXxcjbLog);
				log.info("method:deleteSlgDrvXxcjb|param:cjid="+cjid);
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

	public Integer deleteSlgDrvXxcjbList(String[] cjids) throws Exception {
		try {
			Collection<SlgDrvXxcjb> slgDrvXxcjblist = new ArrayList<SlgDrvXxcjb>();
			if (null != cjids) {
				for (String cjid : cjids) {
					if(null != cjid && !"".equals(cjid)){
						SlgDrvXxcjb slgDrvXxcjb = slgDrvXxcjbDao.getRepository(cjid);
						if (slgDrvXxcjb != null) {
							slgDrvXxcjblist.add(slgDrvXxcjb);
						}
					}
				}
			}
			if (slgDrvXxcjblist != null && slgDrvXxcjblist.size() > 0) {
				slgDrvXxcjbDao.deleteRepositoryList(slgDrvXxcjblist);
				log.info("method:deleteKbzApplicationList|param:cjids="+cjids);
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

	@SuppressWarnings({ "static-access", "unchecked", "null" })
	public List<SlgDrvXxcjb> getSlgDrvXxcjbList2(HttpServletRequest request,int currentPage) throws Exception {
		StringBuffer hql = new StringBuffer("from SlgDrvXxcjb as t where 1=1 ");
		StringBuffer sql = new StringBuffer("select count(0) from SLG_DRV_XXCJB t where 1=1 ");
		String xm = request.getParameter("xm");
		String sfzmhm = request.getParameter("sfzmhm");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String jszhm = request.getParameter("jszhm");
		String isnew = request.getParameter("isnew");
		String shxm=request.getParameter("shxm");
		System.out.println(shxm);
		String jbr = request.getParameter("jbrxm");
		//String name=URLDecoder.decode(jbr, "UTF-8");
		String jbrbm = request.getParameter("jbrbm");
		String shzt = request.getParameter("shzt");
		String slzt = request.getParameter("slzt");
		//DateUtil
		DateUtil du = new DateUtil();
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<SlgDrvXxcjb> slgDrvXxcjbList = new ArrayList<SlgDrvXxcjb>();
		
		if(s_date == null){
			Calendar date = Calendar.getInstance();
			date.setTime(new Date());
			date.set(Calendar.DATE,date.get(Calendar.DATE)-31);
			s_date = DateUtil.date2String(date.getTime());
		}
		
		if(e_date == null){
			e_date = DateUtil.date2String(new Date());
		}
		
		//采集日期
		if (s_date != null && e_date != null && !"".equals(s_date) && !"".equals(e_date)) {
			hql.append(" and (t.czrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.CZRQ between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !"".equals(s_date) && (e_date == null || "".equals(e_date))) {
			e_date = du.date2String(new Date());
			hql.append(" and (t.czrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.CZRQ between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !"".equals(e_date) && (s_date == null || "".equals(s_date))) {
			Date d = du.getAppointDate(-7);
			s_date = du.date2String(d);
			hql.append(" and (t.czrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.CZRQ between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}

		// 统一版流水号
//		if (lsh != null && !lsh.equals("")) {
//			hql.append(" and t.lsh = '" + lsh + "' ");
//			request.setAttribute("lsh", lsh);
//		}
		
		// 姓名
		if (xm != null && !"".equals(xm)) {
			hql.append(" and t.xm = '" + xm + "' ");
			sql.append(" and t.xm = '" + xm + "' ");
			request.setAttribute("xm", xm);
		}
				
		// 身份证明号码
		if (sfzmhm != null && !"".equals(sfzmhm)) {
			hql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			sql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			request.setAttribute("sfzmhm", sfzmhm);
		}
		
		//驾驶证号码
		if (jszhm != null && !"".equals(jszhm)) {
			hql.append(" and t.sfzmhm = '" + jszhm + "' ");
			sql.append(" and t.sfzmhm = '" + jszhm + "' ");
			request.setAttribute("jszhm", jszhm);
		}
		
		//审核状态
		if (shzt != null && !"".equals(shzt)) {
			hql.append(" and t.shJg = '" + shzt + "' ");
			sql.append(" and t.sh_jg = '" + shzt + "' ");
			request.setAttribute("shzt", shzt);
		}
		
		//受理状态
		if (slzt != null && !"".equals(slzt)) {
			hql.append(" and nvl(t.bz,'0') = '" + slzt + "' ");
			sql.append(" and nvl(t.bz,'0') = '" + slzt + "' ");
			request.setAttribute("slzt", slzt);
		}
		
		//经办人
		if(jbr != null && !"".equals(jbr)){
			hql.append(" and t.czrxm like '" + jbr + "%' ");
			sql.append(" and t.czrxm like '" + jbr + "%' ");
			request.setAttribute("jbrxm", jbr);
		}
		//审核人
		if(shxm != null && !"".equals(shxm)){
			hql.append(" and t.shXm like '" + shxm + "%' ");
			sql.append(" and t.SH_XM like '" + shxm + "%' ");
			request.setAttribute("shxm", shxm);
		}
		//经办人部门
		if(jbrbm != null && !"".equals(jbrbm)){
			List deptList =  this.getDeptBelowList(jbrbm);
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
				hql.append(" and t.czbm in ("+czbm+") ");
				sql.append(" and t.czbm in ("+czbm+") ");
			}
			request.setAttribute("jbrbm", jbrbm);
		}
		// 业务类型
//		if (ywmsMain != null && !ywmsMain.equals("")) {
//			hql.append(" and t.ywlx like '%" + ywmsMain + "%' ");
//			sql.append(" and t.ywlx like '%" + ywmsMain + "%' ");
//			request.setAttribute("ywmsMain", ywmsMain);
//		}
		
		// 档案编号
//		if (dabh != null && !dabh.equals("")) {
//			hql.append(" and t.dabh = '" + dabh + "' ");
//			sql.append(" and t.dabh = '" + dabh + "' ");
//			request.setAttribute("dabh", dabh);
//		}
		if(isnew != null && !"".equals(isnew)){
			hql.append(" and t.isnew = '" + isnew + "' ");
			sql.append(" and t.isnew = '" + isnew + "' ");
		}

		int size = slgDrvXxcjbDao.getRepositoryBySQLListSize(sql.toString());
		if (size > 0) {
			hql.append(" order by t.czrq desc");
			//System.out.println(hql.toString());
			slgDrvXxcjbList = slgDrvXxcjbDao.findHQLByPage(hql.toString(),offset,pageSize);
//			List<SlgSjzd> slgSjzdList = null;
//			for(int i = 0; i < slgDrvXxcjbList.size(); i++){
//				String ywms = "";
//				ywms = slgDrvXxcjbList.get(i).getYwmsMain();
//				if(ywms != null && !"".equals(ywms)){
//					String[] ywlxs = null;
//					ywlxs = ywms.split(",");
//					if(ywlxs != null){
//						String ywmsmain = "";
//						for(int j = 0; j < ywlxs.length; j++){
//							if(ywlxs[j] != null && !"".equals(ywlxs[j])){
//								slgSjzdList = new ArrayList<SlgSjzd>();
//								slgSjzdList = this.yujingDao.getSlgDrvXxcjbList("JSZYWSL", ywlxs[j]);
//								if(slgSjzdList.size() > 0){
//									ywmsmain += slgSjzdList.get(0).getDmms1()+",";
//								}
//							}
//						}
//						if(ywmsmain.endsWith(",")){
//							ywmsmain = ywmsmain.substring(0, ywmsmain.length()-1);
//						}
//						slgDrvXxcjbList.get(i).setYwmsMain(ywmsmain);
//					}else{
//						slgDrvXxcjbList.get(i).setYwmsMain("");
//					}
//				}else{
//					slgDrvXxcjbList.get(i).setYwmsMain("");
//				}
//			}
			SlgDrvXxcjb slgDrvXxcjb = null;
			//查询所有业务类型
			List<SlgSjzd> ywlxList = null;
			ywlxList = this.getYwlxYwyy(request, "", "", "JSZYWSL", "DRV_YWLX", "");
			if(ywlxList == null){
				ywlxList = new ArrayList<SlgSjzd>();
			}
			//查询所有业务原因
			List<SlgSjzd> ywyyList = null;
			ywyyList = this.getYwlxYwyy(request, "", "", "JSZYWSL", "DRV_YWYY", "");
			if(ywyyList == null){
				ywyyList = new ArrayList<SlgSjzd>();
			}
			for(int n = 0; n < slgDrvXxcjbList.size(); n++){
				slgDrvXxcjb = slgDrvXxcjbList.get(n);
				if(null != slgDrvXxcjb.getYwlx() && !"".equals(slgDrvXxcjb.getYwlx())){
					String ywlxval = "";
					String[] ywyyarr = null;
					String[] ywlxarr = slgDrvXxcjb.getYwlx().split(",");
//					List<SlgSjzd> slgsjzds = null;
					if(null != slgDrvXxcjb.getYwyy() && !"".equals(slgDrvXxcjb.getYwyy())){
						ywyyarr = slgDrvXxcjb.getYwyy().split(",");
					}
					for(int i = 0; i < ywlxarr.length; i++){
//						slgsjzds = new ArrayList<SlgSjzd>();
//						slgsjzds = this.getYwlxYwyy(ywlxarr[i], "", "JSZYWSL", "DRV_YWLX");
//						if(slgsjzds.size() > 0){
//							ywlxval += slgsjzds.get(0).getDmms1();
//						}
						for(int k = 0; k < ywlxList.size(); k++){
							if(ywlxarr[i].equals(ywlxList.get(k).getDmz())){
								ywlxval += ywlxList.get(k).getDmms1();
							}
						}
						if(ywyyarr != null){
							String ywyystr = "";
							for(int j = 0; j < ywyyarr.length; j++){
								String ywyyval = ywyyarr[j];
								String[] ywyysplit = ywyyval.split(":");
								if(ywlxarr[i].equals(ywyysplit[0])){
//									slgsjzds = new ArrayList<SlgSjzd>();
//									slgsjzds = this.getYwlxYwyy(ywyysplit[1], ywlxarr[i], "JSZYWSL", "DRV_YWYY");
//									if(slgsjzds.size() > 0){
//										ywyystr += slgsjzds.get(0).getDmms1();
//									}
									for(int m = 0; m < ywyyList.size(); m++){
										if(ywyysplit[0].equals(ywyyList.get(m).getDmms2()) && ywyysplit[1].equals(ywyyList.get(m).getDmz())){
											ywyystr += ywyyList.get(m).getDmms1();
										}
									}
								}
							}
							if(ywyystr != null && !"".equals(ywyystr)){
								ywlxval += "("+ywyystr+")";
							}
							
						}
						ywlxval += ",";
					}
					if(ywlxval.endsWith(",")){
						ywlxval = ywlxval.substring(0, ywlxval.length()-1);
					}
					slgDrvXxcjbList.get(n).setYwlx(ywlxval);
				}
				slgDrvXxcjb.setCzbm(getDeptUpid(slgDrvXxcjb.getCzbm()));
			}
			request.setAttribute("rscount", size);
			request.setAttribute("userkjbm", getDeptUpid(user.getBmid()));
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
		request.setAttribute("slgDrvXxcjbList", slgDrvXxcjbList);
		log.info("method:getSlgDrvXxcjbList|param:request="+request+",currentPage="+currentPage);
		return slgDrvXxcjbList;
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	public List<SlgDrvXxcjb> getSlgDrvXxcdjbExport(HttpServletRequest request, int currentPage) throws Exception{
		StringBuffer hql = new StringBuffer("from SlgDrvXxcjb as t where 1=1 ");
		StringBuffer sql = new StringBuffer("select count(0) from SLG_DRV_XXCJB t where 1=1 ");
		Map map=new HashMap();
		//String lsh = request.getParameter("lsh");
		//String ywmsMain = request.getParameter("ywmsMain");
		String xm = request.getParameter("xm");
		String sfzmhm = request.getParameter("sfzmhm");
		//String dabh = request.getParameter("dabh");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String jszhm = request.getParameter("jszhm");
		String isnew = request.getParameter("isnew");
		String jbr = request.getParameter("jbrxm");
		String jbrbm = request.getParameter("jbrbm");
		//DateUtil
		DateUtil du = new DateUtil();
		int offset = SysConst.PAGESIZE*(currentPage-1);
		int pageSize = SysConst.PAGESIZE;
		List<SlgDrvXxcjb> slgDrvXxcjbList = new ArrayList<SlgDrvXxcjb>();
		
		//采集日期
		if (s_date != null && e_date != null && !"".equals(s_date) && !"".equals(e_date)) {
			hql.append(" and (t.czrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.CZRQ between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !"".equals(s_date) && (e_date == null || "".equals(e_date))) {
			e_date = du.date2String(new Date());
			hql.append(" and (t.czrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.CZRQ between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !"".equals(e_date) && (s_date == null || "".equals(s_date))) {
			Date d = du.getAppointDate(-7);
			s_date = du.date2String(d);
			hql.append(" and (t.czrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.CZRQ between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}

		// 统一版流水号
//		if (lsh != null && !lsh.equals("")) {
//			hql.append(" and t.lsh = '" + lsh + "' ");
//			request.setAttribute("lsh", lsh);
//		}
		
		// 姓名
		if (xm != null && !"".equals(xm)) {
			hql.append(" and t.xm = '" + xm + "' ");
			sql.append(" and t.xm = '" + xm + "' ");
			request.setAttribute("xm", xm);
		}
				
		// 身份证明号码
		if (sfzmhm != null && !"".equals(sfzmhm)) {
			hql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			sql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			request.setAttribute("sfzmhm", sfzmhm);
		}
		
		//驾驶证号码
		if (jszhm != null && !"".equals(jszhm)) {
			hql.append(" and t.sfzmhm = '" + jszhm + "' ");
			sql.append(" and t.sfzmhm = '" + jszhm + "' ");
			request.setAttribute("sfzmhm", jszhm);
		}
		
		//经办人
		if(jbr != null && !"".equals(jbr)){
			hql.append(" and t.czrxm like '" + jbr + "%' ");
			sql.append(" and t.czrxm like '" + jbr + "%' ");
			request.setAttribute("jbrxm", jbr);
		}
		//经办人部门
		if(jbrbm != null && !"".equals(jbrbm)){
			List deptList =  this.getDeptBelowList(jbrbm);
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
				hql.append(" and t.czbm in ("+czbm+") ");
				sql.append(" and t.czbm in ("+czbm+") ");
			}
			request.setAttribute("jbrbm", jbrbm);
		}
		
		// 业务类型
//		if (ywmsMain != null && !ywmsMain.equals("")) {
//			hql.append(" and t.ywlx like '%" + ywmsMain + "%' ");
//			sql.append(" and t.ywlx like '%" + ywmsMain + "%' ");
//			request.setAttribute("ywmsMain", ywmsMain);
//		}
		
		// 档案编号
//		if (dabh != null && !dabh.equals("")) {
//			hql.append(" and t.dabh = '" + dabh + "' ");
//			sql.append(" and t.dabh = '" + dabh + "' ");
//			request.setAttribute("dabh", dabh);
//		}
		if(isnew != null && !"".equals(isnew)){
			hql.append(" and t.isnew = '" + isnew + "' ");
			sql.append(" and t.isnew = '" + isnew + "' ");
		}

		int size = slgDrvXxcjbDao.getRepositoryBySQLListSize(sql.toString());
		if (size > 0) {
			hql.append(" order by t.czrq desc");
			List deptList = getDeptList("", "", "");
			if(size > 10000){
				slgDrvXxcjbList = slgDrvXxcjbDao.findHQLByPage(hql.toString(),offset,pageSize);
				request.setAttribute("exportData", "数据量太多无法一次导出，请筛选！");
			}else{
				slgDrvXxcjbList = slgDrvXxcjbDao.getRepositories(hql.toString());
			}
			SlgDrvXxcjb slgDrvXxcjb = null;
			//查询所有业务类型
			List<SlgSjzd> ywlxList = null;
			ywlxList = this.getYwlxYwyy(request, "", "", "JSZYWSL", "DRV_YWLX", "");
			if(ywlxList == null){
				ywlxList = new ArrayList<SlgSjzd>();
			}
			//查询所有业务原因
			List<SlgSjzd> ywyyList = null;
			ywyyList = this.getYwlxYwyy(request, "", "", "JSZYWSL", "DRV_YWYY", "");
			if(ywyyList == null){
				ywyyList = new ArrayList<SlgSjzd>();
			}
			for(int n = 0; n < slgDrvXxcjbList.size(); n++){
				slgDrvXxcjb = slgDrvXxcjbList.get(n);
				//翻译业务类型和业务原因
				if(null != slgDrvXxcjb.getYwlx() && !"".equals(slgDrvXxcjb.getYwlx())){
					String ywlxval = "";
					String[] ywyyarr = null;
					String[] ywlxarr = slgDrvXxcjb.getYwlx().split(",");
					//List<SlgSjzd> slgsjzds = null;
					if(null != slgDrvXxcjb.getYwyy() && !"".equals(slgDrvXxcjb.getYwyy())){
						ywyyarr = slgDrvXxcjb.getYwyy().split(",");
					}
					for(int i = 0; i < ywlxarr.length; i++){
//						slgsjzds = new ArrayList<SlgSjzd>();
//						slgsjzds = this.getYwlxYwyy(ywlxarr[i], "", "JSZYWSL", "DRV_YWLX");
//						if(slgsjzds.size() > 0){
//							ywlxval += slgsjzds.get(0).getDmms1();
//						}
						for(int k = 0; k < ywlxList.size(); k++){
							if(ywlxarr[i].equals(ywlxList.get(k).getDmz())){
								ywlxval += ywlxList.get(k).getDmms1();
							}
						}
						if(ywyyarr != null){
							String ywyystr = "";
							for(int j = 0; j < ywyyarr.length; j++){
								String ywyyval = ywyyarr[j];
								String[] ywyysplit = ywyyval.split(":");
								if(ywlxarr[i].equals(ywyysplit[0])){
//									slgsjzds = new ArrayList<SlgSjzd>();
//									slgsjzds = this.getYwlxYwyy(ywyysplit[1], ywlxarr[i], "JSZYWSL", "DRV_YWYY");
//									if(slgsjzds.size() > 0){
//										ywyystr += slgsjzds.get(0).getDmms1();
//									}
									for(int m = 0; m < ywyyList.size(); m++){
										if(ywyysplit[0].equals(ywyyList.get(m).getDmms2()) && ywyysplit[1].equals(ywyyList.get(m).getDmz())){
											ywyystr += ywyyList.get(m).getDmms1();
										}
									}
								}
							}
							if(ywyystr != null && !"".equals(ywyystr)){
								ywlxval += "("+ywyystr+")";
							}
							
						}
						ywlxval += ",";
					}
					if(ywlxval.endsWith(",")){
						ywlxval = ywlxval.substring(0, ywlxval.length()-1);
					}
					slgDrvXxcjbList.get(n).setYwlx(ywlxval);
				}
				//翻译身份证名名称
				if(slgDrvXxcjb.getSfzmmc() != null){
					if("A".equals(slgDrvXxcjb.getSfzmmc())){
						slgDrvXxcjb.setSfzmmc("二代居民身份证");
					}else if("B".equals(slgDrvXxcjb.getSfzmmc())){
						slgDrvXxcjb.setSfzmmc("组织机构代码证书");
					}else if("C".equals(slgDrvXxcjb.getSfzmmc())){
						slgDrvXxcjb.setSfzmmc("军官证");
					}else if("D".equals(slgDrvXxcjb.getSfzmmc())){
						slgDrvXxcjb.setSfzmmc("士兵证");
					}else if("E".equals(slgDrvXxcjb.getSfzmmc())){
						slgDrvXxcjb.setSfzmmc("军官离退休证");
					}else if("F".equals(slgDrvXxcjb.getSfzmmc())){
						slgDrvXxcjb.setSfzmmc("外交人员身份证明");
					}else if("G".equals(slgDrvXxcjb.getSfzmmc())){
						slgDrvXxcjb.setSfzmmc("居民户口薄");
					}else if("H".equals(slgDrvXxcjb.getSfzmmc())){
						slgDrvXxcjb.setSfzmmc("单位注销证明");
					}else if("J".equals(slgDrvXxcjb.getSfzmmc())){
						slgDrvXxcjb.setSfzmmc("居住暂住证明");
					}else if("L".equals(slgDrvXxcjb.getSfzmmc())){
						slgDrvXxcjb.setSfzmmc("驻华机构证明");
					}else if("M".equals(slgDrvXxcjb.getSfzmmc())){
						slgDrvXxcjb.setSfzmmc("临时居民身份证");
					}
				}
				//翻译部门名称
				if(slgDrvXxcjb.getCzbm() != null){
					Object[] objs = null;
					String czrbm = slgDrvXxcjb.getCzbm();
					for(int f = 0; f < deptList.size(); f++){
						objs = (Object[]) deptList.get(f);
						if(objs[0].equals(czrbm)){
							slgDrvXxcjb.setCzbm(objs[1].toString());
						}
					}
				}
				//翻译审核状态
				if(slgDrvXxcjb.getShJg() != null){
					if("0".equals(slgDrvXxcjb.getShJg())){
						slgDrvXxcjb.setShJg("未审核");
					}else if("1".equals(slgDrvXxcjb.getShJg())){
						slgDrvXxcjb.setShJg("审核通过");
					}else if("2".equals(slgDrvXxcjb.getShJg())){
						slgDrvXxcjb.setShJg("审核未通过");
					}
				}
			}
			request.setAttribute("rscount", size);
		} else {
			request.setAttribute("rscount", 0);
		}
		
		map.put("uri", "/ycszhyw/drv/initSlgDrvXxcjbList2.action");
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("slgDrvXxcjbList", slgDrvXxcjbList);
		log.info("method:getSlgDrvXxcjbList|param:request="+request+",currentPage="+currentPage);
		return slgDrvXxcjbList;
	}
	
	
	@SuppressWarnings({ "static-access", "unchecked", "null" })
	public List<SlgDrvXxcjb> getSlgDrvXxcjbList(HttpServletRequest request,int currentPage) throws Exception {
		StringBuffer hql = new StringBuffer("from SlgDrvXxcjb as t where 1=1 ");
		StringBuffer sql = new StringBuffer("select count(0) from SLG_DRV_XXCJB t where 1=1 ");
		String lsh = request.getParameter("lsh");
		String ywmsMain = request.getParameter("ywmsMain");
		String xm = request.getParameter("xm");
		String sfzmhm = request.getParameter("sfzmhm");
		String dabh = request.getParameter("dabh");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		
		//DateUtil
		DateUtil du = new DateUtil();
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<SlgDrvXxcjb> slgDrvXxcjbList = new ArrayList<SlgDrvXxcjb>();
		
		//采集日期
		if (s_date != null && e_date != null && !s_date.equals("") && !e_date.equals("")) {
			hql.append(" and (t.czrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd') )");
			sql.append(" and (t.CZRQ between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && e_date == null && !s_date.equals("") && e_date.equals("")) {
			e_date = du.date2String(new Date());
			hql.append(" and (t.czrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd') )");
			sql.append(" and (t.CZRQ between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && s_date == null && s_date.equals("") && !e_date.equals("")) {
			Date d = du.getAppointDate(730);
			s_date = du.date2String(d);
			hql.append(" and (t.czrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd') )");
			sql.append(" and (t.CZRQ between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}

		// 统一版流水号
		if (lsh != null && !lsh.equals("")) {
			hql.append(" and t.lsh = '" + lsh + "' ");
			sql.append(" and t.lsh = '" + lsh + "' ");
			request.setAttribute("lsh", lsh);
		}
		
		// 姓名
		if (xm != null && !xm.equals("")) {
			hql.append(" and t.xm = '" + xm + "' ");
			sql.append(" and t.xm = '" + xm + "' ");
			request.setAttribute("xm", xm);
		}
				
		// 身份证明号码
		if (sfzmhm != null && !sfzmhm.equals("")) {
			hql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			sql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			request.setAttribute("sfzmhm", sfzmhm);
		}
		
		// 业务类型
		if (ywmsMain != null && !ywmsMain.equals("")) {
			hql.append(" and t.ywmsMain = '" + ywmsMain + "' ");
			sql.append(" and t.YWMS_MAIN = '" + ywmsMain + "' ");
			request.setAttribute("ywmsMain", ywmsMain);
		}
		
		// 档案编号
		if (dabh != null && !dabh.equals("")) {
			hql.append(" and t.dabh = '" + dabh + "' ");
			sql.append(" and t.dabh = '" + dabh + "' ");
			request.setAttribute("dabh", dabh);
		}
		hql.append(" and t.isnew is null");
		sql.append(" and t.isnew is null");
		int size = slgDrvXxcjbDao.getRepositoryBySQLListSize(sql.toString());
		if (size > 0) {
			hql.append(" order by t.czrq desc");
			//System.out.println(hql.toString());
			slgDrvXxcjbList = slgDrvXxcjbDao.findHQLByPage(hql.toString(),offset,pageSize);
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
		request.setAttribute("slgDrvXxcjbList", slgDrvXxcjbList);
		log.info("method:getSlgDrvXxcjbList|param:request="+request+",currentPage="+currentPage);
		return slgDrvXxcjbList;
	}

	@SuppressWarnings("unchecked")
	public List<SlgDrvXxcjb> getSlgDrvXxcjbList(HttpServletRequest request) throws Exception {
		StringBuffer hql = new StringBuffer("from SlgDrvXxcjb as t where 1=1 ");
		String lsh = request.getParameter("lshs");
		List<SlgDrvXxcjb> slgDrvXxcjbList = new ArrayList<SlgDrvXxcjb>();

		// 统一版流水号
		if (lsh != null && !lsh.equals("")) {
			lsh = lsh.replaceAll(" ", "");
			lsh = lsh.replaceAll("'", "");
			lsh = lsh.replaceAll("\"", "");
			lsh = lsh.replaceAll("，", ",");
			if (lsh.indexOf(",") >= 0) {
				String[] lshs = null;
				lshs = lsh.split(",");
				hql.append("and (");
				for (int i = 0; i < lshs.length; i++) {
					if (i == 0) {
						hql.append(" t.lshs like '%" + lshs[i] + "%' ");
					} else {
						hql.append(" or t.lshs like '%" + lshs[i] + "%' ");
					}
				}
				hql.append(")");
			} else {
				hql.append(" and t.lshs like '%" + lsh + "%' ");
			}
		}
		
		slgDrvXxcjbList = slgDrvXxcjbDao.getRepositories(hql.toString());
		log.info("method:getSlgDrvXxcjbList|param:request="+request);
		return slgDrvXxcjbList;
	}

	public SlgDrvXxcjb getSlgDrvXxcjb(HttpServletRequest request) throws Exception {
		String cjid = request.getParameter("cjid");
		if(null != cjid && !"".equals(cjid)){
			log.info("method:getSlgDrvXxcjb|param:cjid="+cjid);
			return slgDrvXxcjbDao.getRepository(cjid);
		}
		return null;
	}

	public SlgDrvXxcjb getSlgDrvXxcjb(String cjid) throws Exception {
		SlgDrvXxcjb sdx = slgDrvXxcjbDao.getRepository(cjid);
		if(null != sdx){
			sdx.setCzbm(getDeptName(sdx.getCzbm()));
			sdx.setShBm(getDeptName(sdx.getShBm()));
			StringBuffer jsrbsedsfzmyy = new StringBuffer("");
			StringBuffer dbrbsedsfzmyy = new StringBuffer("");
			if(sdx.getJsrbsedsfzmyy() != null && sdx.getJsrbsedsfzmyy() != ""){
				String[] yys = sdx.getJsrbsedsfzmyy().split("卍");
				if(yys[0].trim() != null && yys[0].trim() != ""){
					String[] zds = yys[0].split(",");
					if(zds != null && zds.length > 0){
						for(int i = 0; i < zds.length; i++){
							if(zds[i] != null && zds[i].trim() != ""){
								SlgSjzd slgSjzd = (SlgSjzd) this.yujingDao.getSlgDrvXxcjbList("WDKYY", zds[i].trim()).get(0);
								if(slgSjzd != null){
									jsrbsedsfzmyy .append(slgSjzd.getDmms1() + ",");
								}
							}
						}
					}
				}
				//备注
				if(yys[1].trim() != null && yys[1].trim() != "" && !",".equals(yys[1].trim())){
					jsrbsedsfzmyy.append(yys[1].trim());
				}
			}
			
			if(sdx.getDbrbsedsfzmyy() != null && sdx.getDbrbsedsfzmyy() != ""){
				String[] dbryys = sdx.getDbrbsedsfzmyy().split("卍");
				if(dbryys[0].trim() != null && dbryys[0].trim() != ""){
					String[] dbrzds = dbryys[0].split(",");
					if(dbrzds != null && dbrzds.length > 0){
						for(int i = 0; i < dbrzds.length; i++){
							if(dbrzds[i] != null && dbrzds[i].trim() != ""){
								SlgSjzd slgSjzd = (SlgSjzd) this.yujingDao.getSlgDrvXxcjbList("WDKYY", dbrzds[i].trim()).get(0);
								if(slgSjzd != null){
									dbrbsedsfzmyy .append(slgSjzd.getDmms1() + ",");
								}
							}
						}
					}
				}
				//备注
				if(dbryys[1].trim() != null && dbryys[1].trim() != "" && !",".equals(dbryys[1].trim())){
					dbrbsedsfzmyy.append(dbryys[1].trim());
				}
			}
			String jsrbsedsfzmyy1 = "";
			String dbrbsedsfzmyy1 = "";
			if(jsrbsedsfzmyy.toString().endsWith(",")){
				jsrbsedsfzmyy1 = jsrbsedsfzmyy.toString().substring(0, jsrbsedsfzmyy.toString().length()-1);
			}else{
				jsrbsedsfzmyy1 = jsrbsedsfzmyy.toString();
			}
			if(dbrbsedsfzmyy.toString().endsWith(",")){
				dbrbsedsfzmyy1 = dbrbsedsfzmyy.toString().substring(0, dbrbsedsfzmyy.toString().length()-1);
			}else{
				dbrbsedsfzmyy1 = dbrbsedsfzmyy.toString();
			}
			sdx.setJsrbsedsfzmyy(jsrbsedsfzmyy1);
			sdx.setDbrbsedsfzmyy(dbrbsedsfzmyy1);
		}
		log.info("method:getSlgDrvXxcjb|param:cjid="+cjid);
		return sdx;
	}
	
	@SuppressWarnings("unchecked")
	public SlgZjxxb getSlgZjxxb(String cjid) throws Exception{
		StringBuffer hql = new StringBuffer("from SlgZjxxb where cjid='" + cjid + "'");
		List<SlgZjxxb> slgZjxxbList = new ArrayList<SlgZjxxb>();
		slgZjxxbList = slgZjxxbDao.getRepositories(hql.toString());
		if(null != slgZjxxbList && slgZjxxbList.size() > 0){
			SlgZjxxb slgZjxxb = slgZjxxbList.get(0);
			if(null != slgZjxxb){
				return slgZjxxb;
			}
		}
		return null;
	}
	
	public SlgDrvXxcjbLog insertSlgDrvXxcjbLog(SlgDrvXxcjb slgDrvXxcjb) throws Exception{
		SlgDrvXxcjbLog slgDrvXxcjbLog = new SlgDrvXxcjbLog();
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
	
	
	@SuppressWarnings("unchecked")
	public List<SlgYwlx> getSlgYwlxList(String veh_drv, String ywms_main) throws Exception {
		StringBuffer hql = new StringBuffer("from SlgYwlx where veh_drv='" + veh_drv + "' and ywms_main='" + ywms_main + "' ");
		List<SlgYwlx> slgYwlxList = new ArrayList<SlgYwlx>();
		slgYwlxList = slgYwlxDao.getRepositories(hql.toString());
		log.info("method:getSlgYwlxList|param:veh_drv="+veh_drv+"&ywms_main=" + ywms_main);
		return slgYwlxList;
	}
	
	@SuppressWarnings("unchecked")
	public List<SlgYwlx> getSlgYwlxList() throws Exception {
		StringBuffer hql = new StringBuffer("select distinct new com.ycszh.ssh.hbm.drv.SlgYwlx(ywms_main,ywms_main_ch) from SlgYwlx");
		List<SlgYwlx> slgYwlxList = new ArrayList<SlgYwlx>();
		slgYwlxList = slgYwlxDao.getRepositories(hql.toString());
		log.info("method:getSlgYwlxList");
		return slgYwlxList;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List getDaxxInfo(String hm,String type) throws Exception{
		if(null != type && !"".equals(type) && null != hm && !"".equals(hm)){
			List list = new ArrayList();
			try {
				list = dpublishDao.getDaxxInfo(hm,type);
				return list;
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
			}
		}
		return null;
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
	
	public Integer getjia6in1(String lsh) throws Exception {
		return slgDrvXxcjbDao.getjia6in1(lsh);
	}
	
	@SuppressWarnings({ "unchecked" })
	public List getGj() throws Exception {
		String sql = "SELECT * FROM ES_DRV_CODE@edb WHERE XTLB='00' AND  DMLB='0031'";
		List list = slgDrvXxcjbDao.findSQL(sql);
		return list;
	}
	
	@SuppressWarnings({"unchecked" })
	public String getGjInfo(String gj) throws Exception {
		String sql = "SELECT dmmc1 FROM ES_DRV_CODE@edb WHERE XTLB='00' AND  DMLB='0031' and dmz='"+gj+"'";
		List list = slgDrvXxcjbDao.findSQL(sql);
		String restr = "";
		if(null != list && list.size() > 0){
			restr = list.get(0).toString();
		}else{
			restr = gj;
		}
		return restr;
	}

	@SuppressWarnings({  "unchecked" })
	public String getZtInfo(String zt) throws Exception {
		String sql = "SELECT dmmc1 FROM ES_DRV_CODE@edb WHERE XTLB='00' AND DMLB='2005' and dmz='"+zt+"'";
		List list = slgDrvXxcjbDao.findSQL(sql);
		String restr = "";
		if(null != list && list.size() > 0){
			restr = list.get(0).toString();
		}else{
			restr = zt;
		}
		return restr;
	}

	@SuppressWarnings({ "unchecked" })
	public List getZt() throws Exception {
		String sql = "SELECT * FROM ES_DRV_CODE@edb WHERE XTLB='00' AND DMLB='2005'";
		List list = slgDrvXxcjbDao.findSQL(sql);
		return list;
	}

	@SuppressWarnings({ "unchecked" })
	public List getXzqh() throws Exception {
		String sql = "SELECT dmz,dmmc1 FROM ES_DRV_CODE@edb  where XTLB='00' AND DMLB='0050' and dmz like'4403%' and dmz not in('440310','440311','440312')";
		List list = slgDrvXxcjbDao.findSQL(sql);
		return list;
	}
	
	@SuppressWarnings({ "unchecked" })
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
	
	@SuppressWarnings("unchecked")
	public List<SlgSjzd> getYwlxYwyy(HttpServletRequest request, String dmz, String dmms2, String dmlb, String veh_drv, String is_flag) throws Exception{
		List<SlgSjzd> slgSjzds = null;
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		StringBuffer hqlbuffer = new StringBuffer("from SlgSjzd as s where 1=1 ");
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
		slgSjzds = this.slgYwlxDao.getRepositories(hqlbuffer.toString()+con+" order by s.dmz asc");
		if(slgSjzds == null || slgSjzds.size() == 0){
			if(!StringUtil.isNull(is_flag)){
				hqlbuffer.append(" and s.isFlag = '"+is_flag+"'");
			}
			hqlbuffer.append(" order by s.dmz asc");
			slgSjzds = this.slgYwlxDao.getRepositories(hqlbuffer.toString());
		}
		return slgSjzds;
	}
	
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
		deptList = slgDrvXxcjbDao.findSQL(sql);
		return deptList;
		
	}
	
	@SuppressWarnings("unchecked")
	public List getDeptBelowList(String deptid) throws Exception{
		List deptList = null;
		String sql = "select org_id, up_org, org_name from  (select t.* from v_veh_org_ycs t start with org_id = '"+deptid+"' connect by prior org_id = up_org)";
		deptList = slgDrvXxcjbDao.findSQL(sql);
		return deptList;
	}
	
	
	@SuppressWarnings("unchecked")
	public String getIsChaoTen(HttpServletRequest request, String hphm, String hpzl, String jszh) throws Exception{
		String sql = "select vio_interface.sjk_dcwfyj.wfwcl_ten('"+hphm+"', '"+hpzl+"', '"+jszh+"') from dual";
		List list  = this.slgDrvXxcjbDao.findSQL(sql);
		if(list != null && list.size() > 0){
			String result = list.get(0)+"";
			if(!"0".equals(result)){
				//判断是否是机动训练大队及以下部门
				List deptList = this.getDeptBelowList("C34702A8FFDC7CBFE040007F0100339B");
				User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
				if(deptList != null){
					String temp = "";
					for(int i = 0; i < deptList.size(); i++){
						Object[] objs = (Object[])deptList.get(i);
						temp = objs[0]+""; 
						if(temp.equals(user.getBmid())){
							result = "2";
							break;
						}
					}
					return result;
				}else{
					return result;
				}
				
			}else{
				return result;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String getDeptUpid(String deptid) throws Exception{
		String deptsql = "select org_id from (select t.* from v_veh_org_ycs t start with " +
					"org_id = '" + deptid + "' connect by prior up_org = org_id) " +
					"where up_org = 'C34702A8FED97CBFE040007F0100339B'";
		List deptidlist = this.slgDrvXxcjbDao.findSQL(deptsql);
		String deptids = "";
		if(null != deptidlist && deptidlist.size() > 0){
			deptids = deptidlist.get(0).toString();
		}
		return deptids;
	}
	/*
	 * 根据中文得到id
	 */
	@SuppressWarnings("unchecked")
	public String getDeptId(String deptid) throws Exception{
		String deptsql = "select org_id from (select t.* from v_veh_org_ycs t start with " +
				"org_Name = '"+deptid+"' connect by prior up_org = org_id) " +
				"where up_org = 'C34702A8FED97CBFE040007F0100339B'";
		List deptidlist = this.slgDrvXxcjbDao.findSQL(deptsql);
		String deptids = "";
		if(null != deptidlist && deptidlist.size() > 0){
			deptids = deptidlist.get(0).toString();
		}
		return deptids;
	}
	
	
	public Integer drvShenhe(HttpServletRequest request, String shlsh,
			String shjg,String cjid,String bz1,String bz2) throws Exception {
		String invalue = "";
		if("1".equals(shjg)){
			invalue = shlsh + ",B,,";
		}else if("2".equals(shjg)){
			invalue = shlsh + ",B,delete,";
		}
		String result = itopscDao.get10178(invalue);
		if("0".equals(result)){
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			SlgDrvXxcjb slgDrvXxcjb = slgDrvXxcjbDao.getRepository(cjid);
			slgDrvXxcjb.setShLsh(shlsh);
			slgDrvXxcjb.setShJg(shjg);
			slgDrvXxcjb.setBz1(bz1);
			slgDrvXxcjb.setBz2(bz2);
			slgDrvXxcjb.setShYhm(user.getName());
			slgDrvXxcjb.setShXm(user.getYgxm());
			slgDrvXxcjb.setShBm(user.getBmid());
			slgDrvXxcjb.setShBmKj(getDeptUpid(user.getBmid()));
			slgDrvXxcjb.setShRq(new Date());
			slgDrvXxcjb.setShIp(getLoginIp(request));
			slgDrvXxcjb.setShMac("");
			SlgDrvXxcjb sdx = slgDrvXxcjbDao.updateRepository(slgDrvXxcjb);
			SlgDrvXxcjbLog sdxlog = insertSlgDrvXxcjbLog(sdx);
			sdxlog.setOpr(user.getName());
			sdxlog.setOptime(new Date());
			sdxlog.setOpration("U");
			slgDrvXxcjbLogDao.addRepository(sdxlog);
			return 1;
		}else{
			return 2;
		}
	}

	public Integer drvShenheCheck(HttpServletRequest request, String shlsh,
			String sfzmhm,String sfzmmc,String xm,String dabh) throws Exception {
		String invalue = shlsh + ",";
		Object[] objs = null;
		objs = itopscDao.get10036(invalue);
		if(null != objs){
			/*if("F".equals(sfzmmc)){
				if(null != objs[2]){
					if(objs[2].equals(xm)){
						return 1;
					}else{
						return 4;
					}
				}else{
					return 2;
				}
			}else{
				if(null != objs[1]){
					if(objs[1].equals(sfzmhm)){
						return 1;
					}else{
						return 3;
					}
				}else{
					return 2;
				}
			}*/
			if(null != objs[1] && null != objs[2]){
				if(objs[2].equals(xm) || objs[1].equals(sfzmhm) || objs[3].equals(dabh)){
					return 1;
				}else{
					return 5;
				}
			}else{
				return 2;
			}
		}
		return 0;
	}
	
	public SlgDrvXxcjbDao getSlgDrvXxcjbDao() {
		return slgDrvXxcjbDao;
	}

	public void setSlgDrvXxcjbDao(SlgDrvXxcjbDao slgDrvXxcjbDao) {
		this.slgDrvXxcjbDao = slgDrvXxcjbDao;
	}

	public SlgDrvXxcjbLogDao getSlgDrvXxcjbLogDao() {
		return slgDrvXxcjbLogDao;
	}

	public void setSlgDrvXxcjbLogDao(SlgDrvXxcjbLogDao slgDrvXxcjbLogDao) {
		this.slgDrvXxcjbLogDao = slgDrvXxcjbLogDao;
	}

	public SlgZjxxbDao getSlgZjxxbDao() {
		return slgZjxxbDao;
	}

	public void setSlgZjxxbDao(SlgZjxxbDao slgZjxxbDao) {
		this.slgZjxxbDao = slgZjxxbDao;
	}

	public SlgSjzdDao getSlgSjzdDao() {
		return slgSjzdDao;
	}

	public void setSlgSjzdDao(SlgSjzdDao slgSjzdDao) {
		this.slgSjzdDao = slgSjzdDao;
	}

	public SlgYwlxDao getSlgYwlxDao() {
		return slgYwlxDao;
	}

	public void setSlgYwlxDao(SlgYwlxDao slgYwlxDao) {
		this.slgYwlxDao = slgYwlxDao;
	}
	

	public YujingDao getYujingDao() {
		return yujingDao;
	}

	public void setYujingDao(YujingDao yujingDao) {
		this.yujingDao = yujingDao;
	}

	public static Logger getLog() {
		return log;
	}

	public SlgDrvFileUploadDao getSlgDrvFileUpload() {
		return slgDrvFileUpload;
	}

	public void setSlgDrvFileUpload(SlgDrvFileUploadDao slgDrvFileUpload) {
		this.slgDrvFileUpload = slgDrvFileUpload;
	}

	public IDpublishDao getDpublishDao() {
		return dpublishDao;
	}

	public void setDpublishDao(IDpublishDao dpublishDao) {
		this.dpublishDao = dpublishDao;
	}

	public IItopscDao getItopscDao() {
		return itopscDao;
	}

	public void setItopscDao(IItopscDao itopscDao) {
		this.itopscDao = itopscDao;
	}

	public Integer getSpxx(HttpServletRequest request, String sfzmhm, String xm) throws Exception {
		String sql = "select count(0) from slg_spxx t where splx='drv' and flag='0' and sfzmhm='"+sfzmhm+"' and xm='"+xm+"'";
		Integer spxx = slgDrvXxcjbDao.getRepositoryBySQLListSize(sql);
		return spxx;
	}

	public String getPro(HttpServletRequest request, String sfzmhm, String xm) throws Exception {
		return itopscDao.getPrc(sfzmhm, xm);
	}
	@SuppressWarnings("unchecked")
	public Map getCodeJszYwlx(String lx) throws Exception{
		String sql = "select dmz,dmms1 from SLG_SJZD t where veh_drv='"+lx+"'";//es_veh_code
		List list = slgDrvXxcjbDao.findSQL(sql); 
		Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Object[] ss = (Object[]) list.get(i);
			map.put(ss[0], ss[1]);
		}
		return map;
	}
	@SuppressWarnings("unchecked")
	public Map getCodeJszDept(String sdate,String edate,String ywlx) throws Exception{
		StringBuffer sql = new StringBuffer("select org_id,org_name from v_veh_org_ycs  where up_org='C34702A8FED97CBFE040007F0100339B' and" +
				" org_id in (select t.sh_bm_kj from slg_drv_xxcjb t where 1=1");//es_veh_code
		if (sdate != null && !"".equals(sdate)&& edate != null && !"".equals(edate)) {
			sql.append(" and t.sh_rq>= to_date('" + sdate
					+ "', 'yyyy-mm-dd') and t.sh_rq < to_date('" + edate
					+ "', 'yyyy-mm-dd')");
		} 
		// 业务类型
		if (ywlx != null && !"".equals(ywlx)) {
			sql.append(" and t.ywlx = '" + ywlx + "' ");
		}
		sql.append(" )");
		List list = slgDrvXxcjbDao.findSQL(sql.toString()); 
		Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Object[] ss = (Object[]) list.get(i);
			map.put(ss[0], ss[1]);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public void initDrvtj(HttpServletRequest request, int currentPage) throws Exception {
		List drvGzList = new ArrayList();
		StringBuffer sqlCount = new StringBuffer(
				"select count(0) from (select SH_BM_KJ from  slg_drv_xxcjb t where sh_bm_kj is not null and 1=1");
		StringBuffer sqlList = new StringBuffer("Select SH_BM_KJ,"
				+
				"shs, "
				//审核数
				+
				"shtgs, "
				//审核通过数
				+
				"shwtgs "
				//审核未通过数
				+
				"From (select SH_BM_KJ,sum(case "
				+ " when sh_jg  <> '0' then  1 else 0 "
				+ " end) shs, sum(case when sh_jg='1' then "
				+ " 1 else 0 end) shtgs, sum(case when sh_jg = '2' then "
				+ " 1 else 0 end) shwtgs from slg_drv_xxcjb t " +
						" where sh_bm_kj is not null" +
						" and 1=1");


		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String ywlx = request.getParameter("ywlx");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		String curi = request.getRequestURI();
		if (s_date != null && e_date != null && !"".equals(s_date)
				&& !"".equals(e_date)) {
			sqlCount.append(" and t.sh_rq >= to_date('" + s_date
					+ "', 'yyyy-mm-dd') and t.sh_rq < to_date('" + e_date
					+ "', 'yyyy-mm-dd')");
			sqlList.append(" and t.sh_rq>= to_date('" + s_date
					+ "', 'yyyy-mm-dd') and t.sh_rq <to_date('" + e_date
					+ "', 'yyyy-mm-dd')");

			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !"".equals(s_date)
				&& (e_date == null || "".equals(e_date))) {
			e_date = DateUtil.date2String(new Date());
			sqlCount.append(" and t.sh_rq>= to_date('" + s_date
					+ "', 'yyyy-mm-dd') and t.sh_rq < to_date('" + e_date
					+ "', 'yyyy-mm-dd')");
			sqlList.append(" and t.sh_rq>= to_date('" + s_date
					+ "', 'yyyy-mm-dd') and t.sh_rq < to_date('" + e_date
					+ "', 'yyyy-mm-dd')");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !"".equals(e_date)
				&& (s_date == null || "".equals(s_date))) {
			Date d = DateUtil.getAppointDate(-7);
			s_date = DateUtil.date2String(d);
			sqlCount.append(" and t.sh_rq>= to_date('" + s_date
					+ "', 'yyyy-mm-dd') and t.sh_rq < to_date('" + e_date
					+ "', 'yyyy-mm-dd')");
			sqlList.append(" and t.sh_rq>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')and t.sh_rq < to_date('" + e_date
					+ "', 'yyyy-mm-dd')");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
			// 开始日期与结束日期都会空，即第一次加载
		} else {
			s_date = DateUtil.date2String(new Date());
			e_date = s_date;
			sqlCount.append(" and t.sh_rq>= to_date('" + s_date
					+ "', 'yyyy-mm-dd') and t.sh_rq < to_date('" + e_date
					+ "', 'yyyy-mm-dd')");
			sqlList.append(" and t.sh_rq>= to_date('" + s_date
					+ "', 'yyyy-mm-dd') and t.sh_rq < to_date('" + e_date
					+ "', 'yyyy-mm-dd')");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		// 业务类型
		if (ywlx != null && !"".equals(ywlx)) {
			sqlCount.append(" and t.ywlx = '" + ywlx + "' ");
			sqlList.append(" and t.ywlx = '" + ywlx + "' ");
			request.setAttribute("ywlx", ywlx);
		}
		
		sqlCount.append(" group by sh_bm_kj)");
		sqlList.append(" group by sh_bm_kj)");
		int size = slgDrvXxcjbDao.getRepositoryBySQLListSize(sqlCount.toString());
		System.out.println(size+"==========");
		Map<String, String> ywlxList=getCodeJszYwlx("DRV_YWLX");
		if (size > 0) {
			sqlList.append(" order by shwtgs desc");
			drvGzList = this.slgDrvXxcjbDao.findSQLByPage(sqlList.toString(),
					offset, pageSize);
			for (int i = 0; i < drvGzList.size(); i++) {
  				Object[] objs = (Object[])drvGzList.get(i);
  				if(objs[0]!=null){
  					objs[0]=getDeptName(objs[0].toString());
  				}
  			}
		}
		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("drvGzList", drvGzList);
		request.setAttribute("ywlxList", ywlxList);
		request.getSession().setAttribute("drvGzList", drvGzList);
		
	}


	@SuppressWarnings("unchecked")
	public void initDrvtjView(HttpServletRequest request, int currentPage) throws Exception {
		List drvtjxqList = new ArrayList();
		String shbmkj=request.getParameter("shbmkj");
		String zw=request.getParameter("zw");
		if(zw!=null&&!"".equals(zw)){
			shbmkj=getDeptId(shbmkj);
		}
		String s_date = request.getParameter("sdate");
		String e_date = request.getParameter("edate");
		String shxm = request.getParameter("shxm");
		String ywlx = request.getParameter("ywlx");
		StringBuffer sqlCount = new StringBuffer(
				"select count(0) from (select SH_XM from  slg_drv_xxcjb t where t.sh_bm_kj is not null and 1=1");
		StringBuffer sqlList = new StringBuffer("Select SH_XM,"
				+
				"shs, "
				//审核数
				+
				"shtgs, "
				//审核通过数
				+
				"shwtgs "
				//审核未通过数
				+
				"From (select SH_XM,sum(case "
				+ " when sh_jg  <> '0' then  1 else 0 "
				+ " end) shs, sum(case when sh_jg='1' then "
				+ " 1 else 0 end) shtgs, sum(case when sh_jg = '2' then "
				+ " 1 else 0 end) shwtgs from slg_drv_xxcjb t " +
						" where t.sh_bm_kj is not null  and 1=1");

		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		String curi = request.getRequestURI();
		if (s_date != null && !"".equals(s_date)&& e_date != null && !"".equals(e_date)) {
			sqlCount.append(" and t.sh_rq>= to_date('" + s_date
					+ "', 'yyyy-mm-dd') and t.sh_rq < to_date('" + e_date
					+ "', 'yyyy-mm-dd')");
			sqlList.append(" and t.sh_rq>= to_date('" + s_date
					+ "', 'yyyy-mm-dd') and t.sh_rq < to_date('" + e_date
					+ "', 'yyyy-mm-dd')");
			request.setAttribute("sdate", s_date);
			request.setAttribute("edate", e_date);
		} 
		// 审核部门科级 
		if (shbmkj != null && !"".equals(shbmkj)) {
			sqlCount.append(" and t.SH_BM_KJ = '" + shbmkj + "' ");
			sqlList.append(" and t.SH_BM_KJ = '" + shbmkj + "' ");
			request.setAttribute("shbmkj", shbmkj);
		}
		
		// 业务类型
		if (ywlx != null && !"".equals(ywlx)) {
			sqlCount.append(" and t.ywlx = '" + ywlx + "' ");
			sqlList.append(" and t.ywlx = '" + ywlx + "' ");
			request.setAttribute("ywlx", ywlx);
		}
		// 审核人
		if (shxm != null && !"".equals(shxm)) {
			sqlCount.append(" and t.sh_xm = '" + shxm.trim() + "' ");
			sqlList.append(" and t.sh_xm = '" + shxm.trim() + "' ");
			request.setAttribute("shxm", shxm);
		}
		sqlCount.append(" group by sh_xm )");
		sqlList.append(" group by sh_xm )");
		int size = slgDrvXxcjbDao.getRepositoryBySQLListSize(sqlCount.toString());
		Map<String, String> deptList=getCodeJszDept(s_date,e_date,ywlx);
		if (size > 0) {
			drvtjxqList = this.slgDrvXxcjbDao.findSQLByPage(sqlList.toString(),
					offset, pageSize);
		}
		request.setAttribute("deptList", deptList);
		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("drvtjxqList", drvtjxqList);
		request.getSession().setAttribute("drvtjxqList", drvtjxqList);
		
	}
}
