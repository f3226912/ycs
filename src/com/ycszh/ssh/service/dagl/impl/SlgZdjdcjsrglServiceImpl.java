package com.ycszh.ssh.service.dagl.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.dao.dagl.SlgZdjdcjsrglDao;
import com.ycszh.ssh.hbm.dagl.SlgZdjdcjsygl;
import com.ycszh.ssh.hbm.dagl.SlgZdjdcjsyglLog;
import com.ycszh.ssh.service.dagl.SlgZdjdcjsrglService;
import com.ycszh.util.ToolsUtil;
/**
 * @author Administrator
 * @创建日期：2015-11-23
 * @描述：重点机动车驾驶人管理
 */
public class SlgZdjdcjsrglServiceImpl implements SlgZdjdcjsrglService{
	private static final Logger log = Logger.getLogger(SlgZdjdcjsrglServiceImpl.class); 
	private DefaultDao defaultDao;
	private SlgZdjdcjsrglDao slgZdjdcjsrglDao;

	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}
	
	
	public SlgZdjdcjsrglDao getSlgZdjdcjsrglDao() {
		return slgZdjdcjsrglDao;
	}

	public void setSlgZdjdcjsrglDao(SlgZdjdcjsrglDao slgZdjdcjsrglDao) {
		this.slgZdjdcjsrglDao = slgZdjdcjsrglDao;
	}

	//查询重点驾驶人机动车集合
	public void initZdjdcjsrList(HttpServletRequest request,int currentPage) throws Exception {
		try{
			//查询条件
			String hphm = request.getParameter("hphm");
			String hpzl = request.getParameter("hpzl");
			String jszhm = request.getParameter("jszhm");
			String xm = request.getParameter("xm");
			
			//分页所需
			int pageSize = SysConst.PAGESIZE;
			int offset = SysConst.PAGESIZE*(currentPage-1);
			String curi = request.getRequestURI();
			
			StringBuffer hql = new StringBuffer("from SlgZdjdcjsygl where 1=1");
			if(hphm!=null&&!hphm.equals("")){
				hql.append(" and hphm like '%"+hphm+"%'");
				request.setAttribute("hphm", hphm);
			}
			if(hpzl!=null&&!hpzl.equals("")){
				hql.append(" and hpzl="+hpzl);
				request.setAttribute("hpzl", hpzl);
			}
			if(jszhm!=null&&!jszhm.equals("")){
				hql.append(" and jszhm='"+jszhm+"'");
				request.setAttribute("jszhm", jszhm);
			}
			if(xm!=null&&!xm.equals("")){
				hql.append(" and xm like '%"+xm+"%'");
				request.setAttribute("xm", xm);
			}
			String hqlCount = "select count(*) "+hql.toString();
			hql.append(" order by czrq desc");
			List<Object> zdjdcjsrList = defaultDao.findHQLByPage(hql.toString(), offset, pageSize);
			int size = defaultDao.getRepositoryByHQLListSize(hqlCount);
			List hpzlList =  (List) request.getSession().getAttribute("hpzlList");
			if(hpzlList == null){
				hpzlList = getHpzl();
				request.getSession().setAttribute("hpzlList", hpzlList);
			}
			
			String oper = (String) request.getSession().getAttribute("oper");
			if("putExcel".equals(oper)){
				String msg = (String) request.getSession().getAttribute("msg");
				List<SlgZdjdcjsygl> failList = (List<SlgZdjdcjsygl>) request.getSession().getAttribute("failList");
				request.getSession().removeAttribute("oper");
				request.getSession().removeAttribute("msg");
				request.getSession().removeAttribute("failList");
				request.setAttribute("failList", failList);
				request.setAttribute("msg", msg);
			}
			
			Map map=new HashMap();
			map.put("uri", curi);
			map.put("pagesize", pageSize);
			map.put("rscount", size);
			map.put("currentpage", currentPage);
			request.setAttribute("rscount", size);
			request.setAttribute("map", map);
			request.setAttribute("zdjdcjsrList", zdjdcjsrList);
			log.info("method:initZdjdcjsrList|param:request="+request+",currentPage="+currentPage);
		}catch(Exception e){
			log.error("异常信息：class:SlgZdjdcjsrglServiceImpl|method:initZdjdcjsrList|errorinfo:"+e);
			throw e;
		}
	}

	//编辑重点驾驶人机动车集合(页面初始)
	public void initEditZdjdcjsr(HttpServletRequest request) throws Exception {
		try{
			String type = request.getParameter("type");
			String editType = "";
			SlgZdjdcjsygl zdjdcjsr = new SlgZdjdcjsygl();
			if("insert".equals(type)){
				editType = "添加";
			}else{
				String id = request.getParameter("id");
				zdjdcjsr = (SlgZdjdcjsygl) defaultDao.getRepository(id, SlgZdjdcjsygl.class);
				if("update".equals(type)){
					editType = "修改";
				}else{
					editType = "查看";
				}
			}
			
			List hpzlList =  (List) request.getSession().getAttribute("hpzlList");
			if(hpzlList == null){
				hpzlList = getHpzl();
				request.getSession().setAttribute("hpzlList", hpzlList);
			}
			request.setAttribute("editType", editType);
			request.setAttribute("zdjdcjsr", zdjdcjsr);
		}catch(Exception e){
			log.error("异常信息：class:SlgZdjdcjsrglServiceImpl|method:initEditZdjdcjsr|errorinfo:"+e);
			throw e;
		}
	}
	
	/**
	 * 查询号牌种类
	 * @throws Exception 
	 */
	public List getHpzl() throws Exception{
		String sql = "select a.dmz,a.dmsm1 from es_veh_code a where a.dmlb='07' order by dmz";
		List hpzlList = defaultDao.findSQL(sql);
		return hpzlList;
	}

	//编辑重点驾驶人机动车集合（增改操作）
	public void editZdjdcjsr(HttpServletRequest request, SlgZdjdcjsygl zdjdcjsr) throws Exception {
		try{
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			String editType = request.getParameter("editType");
			SlgZdjdcjsyglLog log = new SlgZdjdcjsyglLog();
			if("添加".equals(editType)){
				zdjdcjsr.setLrr(user.getName());
				zdjdcjsr.setCzrxm(user.getYgxm());
				zdjdcjsr.setCzrbm(user.getBmid());
				zdjdcjsr.setCzrbmKj(getDeptUpid(user.getBmid()));
				zdjdcjsr.setCzrq(new Date());
				zdjdcjsr.setCzip(ToolsUtil.getIpAddr(request));
				defaultDao.addRepository(zdjdcjsr);
				log = (SlgZdjdcjsyglLog) getRepositoryLog(log, zdjdcjsr,null);
				log.setCznr("新增");
				defaultDao.addRepository(log);
			}else{
				defaultDao.updateRepository(zdjdcjsr);
				log = (SlgZdjdcjsyglLog) getRepositoryLog(log, zdjdcjsr,null);
				log.setCznr("修改");
				log.setLrr(user.getName());
				log.setCzrxm(user.getYgxm());
				log.setCzrbm(user.getBmid());
				log.setCzrbmKj(user.getBmid());
				log.setCzrq(new Date());
				log.setCzip(ToolsUtil.getIpAddr(request));
				defaultDao.addRepository(log);
			}
		}catch(Exception e){
			log.error("异常信息：class:SlgZdjdcjsrglServiceImpl|method:editZdjdcjsr|errorinfo:"+e);
			throw e;
		}
		
	}
	
	//删除重点驾驶人机动车
	public void delZdjdcjsr(HttpServletRequest request) throws Exception {
		try{
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			String id = request.getParameter("id");
			SlgZdjdcjsygl zdjdcjsr = (SlgZdjdcjsygl) defaultDao.getRepository(id, SlgZdjdcjsygl.class);
			SlgZdjdcjsyglLog log = new SlgZdjdcjsyglLog();
			log = (SlgZdjdcjsyglLog) getRepositoryLog(log, zdjdcjsr,null);
			defaultDao.deleteRepository(zdjdcjsr);
			log.setCznr("删除");
			log.setLrr(user.getName());
			log.setCzrxm(user.getYgxm());
			log.setCzrbm(user.getBmid());
			log.setCzrbmKj(user.getBmid());
			log.setCzrq(new Date());
			log.setCzip(ToolsUtil.getIpAddr(request));
			defaultDao.addRepository(log);
		}catch(Exception e){
			log.error("异常信息：class:SlgZdjdcjsrglServiceImpl|method:delZdjdcjsr|errorinfo:"+e);
			throw e;
		}
	}
	
	public int uniqueCheck(HttpServletRequest request) throws Exception {
		try{
			String hphm = request.getParameter("hphm");
			String hpzl = request.getParameter("hpzl");
			String jszhm = request.getParameter("jszhm");
			String sql = "select count(1) from Slg_Zdjdcjsygl where 1=1";
			if(hphm!=null&&!hphm.equals("")){
				sql+=" and hphm='"+hphm+"' and hpzl='"+hpzl+"'";
			}else{
				sql+=" and jszhm='"+jszhm+"'";
			}
			int size = ((BigDecimal) defaultDao.findSQL(sql).get(0)).intValue();
			return size;
		}catch(Exception e){
			log.error("异常信息：class:SlgZdjdcjsrglServiceImpl|method:uniqueCheck|errorinfo:"+e);
			throw e;
		}
	}
	
	/**
	 * 获取实体相应日志
	 * @param t1 主表日志对象-- 对象不能为空
	 * @param t2 主表对象
	 * @param objs 日志对象中不映射的字段
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object getRepositoryLog(Object log_obj, Object obj, List list) throws Exception{
		if(log_obj == null || obj == null){
			return null;
		}
		
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			
			//设置可访问(因为字段若为private则会报无权限访问错误)
			fields[i].setAccessible(true);
			
			String name = fields[i].getName();
			if(list != null){
				if(list.contains(name)){
					continue;
				}
			}
			Field field = log_obj.getClass().getDeclaredField(name);
			
			//设置可访问
			field.setAccessible(true);
			
			field.set(log_obj, fields[i].get(obj));
		}
		return log_obj;
	}
	
	@SuppressWarnings("unchecked")
	public String getDeptUpid(String deptid) throws Exception{
		String deptsql = "select org_id from (select t.* from v_veh_org_ycs t start with " +
					"org_id = '" + deptid + "' connect by prior up_org = org_id) " +
					"where up_org = 'C34702A8FED97CBFE040007F0100339B'";
		List deptidlist = defaultDao.findSQL(deptsql);
		String deptids = "";
		if(null != deptidlist && deptidlist.size() > 0){
			deptids = deptidlist.get(0).toString();
		}
		return deptids;
	}

	//excle数据导入
	public void putExcel(List<SlgZdjdcjsygl> list, HttpServletRequest request) throws Exception {
		try{
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			StringBuffer info = new StringBuffer("");
			List<SlgZdjdcjsygl> zdjdcjsyList = new ArrayList<SlgZdjdcjsygl>();
			int successCount = 0;
			int failCount = 0;
			for (int i = 0; i < list.size(); i++) {
				SlgZdjdcjsygl zdjdcjsr = list.get(i);
				zdjdcjsr.setLrr(user.getName());
				zdjdcjsr.setCzrxm(user.getYgxm());
				zdjdcjsr.setCzrbm(user.getBmid());
				zdjdcjsr.setCzrbmKj(getDeptUpid(user.getBmid()));
				zdjdcjsr.setCzrq(new Date());
				zdjdcjsr.setCzip(ToolsUtil.getIpAddr(request));
				zdjdcjsr.setZt("0");
				String hql = "from SlgZdjdcjsygl where 1=1";
				if(zdjdcjsr.getHphm()!=null&&!zdjdcjsr.getHphm().equals("")){
					hql+=" and hphm='"+zdjdcjsr.getHphm()+"' and hpzl='"+zdjdcjsr.getHpzl()+"'";
					
				}else{
					hql+=" and jszhm='"+zdjdcjsr.getJszhm()+"'";
				}
				String hqlCount = "select count(*) "+hql;
				int size = defaultDao.getRepositoryByHQLListSize(hqlCount);
				if(size>0){
					failCount++;
					continue;
				}
				
				slgZdjdcjsrglDao.addRepository(zdjdcjsr);
				SlgZdjdcjsyglLog log = new SlgZdjdcjsyglLog();
				log = (SlgZdjdcjsyglLog) getRepositoryLog(log, zdjdcjsr,null);
				log.setCznr("导入");
				slgZdjdcjsrglDao.addRepository(log);
				successCount++;
				if(i%20==0){
					Session session = slgZdjdcjsrglDao.getCurrSession();
					session.flush();
					session.clear();
				}
			}
			String msg = "导入成功数"+successCount+"，数据已存在未导入数"+failCount;
			request.getSession().setAttribute("failList", zdjdcjsyList);
			request.getSession().setAttribute("msg", msg);
			request.getSession().setAttribute("oper", "putExcel");
		}catch(Exception e){
			log.error("异常信息：class:SlgZdjdcjsrglServiceImpl|method:putExcel|errorinfo:"+e);
			throw e;
		}
		
	}
}
