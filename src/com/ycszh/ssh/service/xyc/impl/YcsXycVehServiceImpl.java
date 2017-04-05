package com.ycszh.ssh.service.xyc.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.dao.xyc.XycVehDao;
import com.ycszh.ssh.hbm.xyc.YcsXycVeh;
import com.ycszh.ssh.hbm.xyc.YcsXycVehLog;
import com.ycszh.ssh.service.xyc.YcsXycVehService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.ToolsUtil;

public class YcsXycVehServiceImpl implements YcsXycVehService {
	
	private DefaultDao defaultDao;
	private XycVehDao xycVehDao;
	private final static Logger log = Logger.getLogger(YcsXycVehServiceImpl.class);

	public String deleteYcsXycVeh(HttpServletRequest request,String ycsXycVehId) throws Exception {
		//YcsXycVeh ycsXycVeh = (YcsXycVeh) defaultDao.getRepository(ycsXycVehId, YcsXycVeh.class);
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		if (user != null) {
			String xyci = "";
			xyci = xycVehDao.deleteXyc(ycsXycVehId, user.getName(), user.getYgxm(), user.getBmid(), "", ToolsUtil.getIpAddr(request));
			return xyci;
		} else {
			return "异常:找不到嫌疑车对象";
		}
	}

	public YcsXycVeh getYcsXycVeh(HttpServletRequest request) throws Exception {
		String ycsXycVehId = request.getParameter("YcsXycVehId");
		if(null != ycsXycVehId && !"".equals(ycsXycVehId)){
			log.info("method:getYcsXycVeh|param:ycsXycVehId="+ycsXycVehId);
			return (YcsXycVeh)defaultDao.getRepository(ycsXycVehId, YcsXycVeh.class);
		}
		return null;
	}

	public YcsXycVeh getYcsXycVeh(String ycsXycVehId) throws Exception {
		log.info("method:getYcsXycVeh|param:ycsXycVehId="+ycsXycVehId);
		return (YcsXycVeh)defaultDao.getRepository(ycsXycVehId, YcsXycVeh.class);
	}

	@SuppressWarnings({ "unchecked", "null", "static-access"})
	public List<YcsXycVeh> getYcsXycVehList(HttpServletRequest request,int currentPage) throws Exception {
		StringBuffer hql = new StringBuffer("from YcsXycVeh as t where 1=1 ");
		StringBuffer sqlsize = new StringBuffer("select count(0) from xyc_veh t where 1=1 ");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		// 车辆识别代号
		String clsbdh = request.getParameter("clsbdh");
		// 车辆型号
		String clxh = request.getParameter("clxhs");
		// 中文品牌型号
		String zwppxh = request.getParameter("zwppxhs");
		// 号牌号码
		String hphm = request.getParameter("hphm");
		// 号牌种类
		String hpzl = request.getParameter("hpzl");
		// 流水号
		String lsh = request.getParameter("lsh");
		// 身份证明号码
		String sfzmhm = request.getParameter("sfzmhm");
		
		//DateUtil
		DateUtil du = new DateUtil();
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<YcsXycVeh> ycsXycVehList = new ArrayList<YcsXycVeh>();
		
		// 车辆识别代号
		if (clsbdh != null && !clsbdh.equals("")) {
			hql.append(" and t.clsbdh='" + clsbdh + "'");
			sqlsize.append(" and t.clsbdh='" + clsbdh + "'");
			request.setAttribute("clsbdh", clsbdh);
		}
		
		// 车辆型号
		if (clxh != null && !clxh.equals("")) {
			clxh = clxh.replaceAll(" ", "");
			clxh = clxh.replaceAll("'", "");
			clxh = clxh.replaceAll("\"", "");
			clxh = clxh.replaceAll("，", ",");
			if (clxh.indexOf(",") >= 0) {
				String[] clxhs = null;
				if (clxh.indexOf(",") >= 0) {
					clxhs = clxh.split(",");
					hql.append("and (");
					sqlsize.append("and (");
					for (int i = 0; i < clxhs.length; i++) {
						if (i == 0) {
							hql.append(" t.clxh like '%" + clxhs[i]
									+ "%' ");
							sqlsize.append(" t.clxh like '%" + clxhs[i]
							         									+ "%' ");
						} else {
							hql.append(" or t.clxh like '%" + clxhs[i]
									+ "%' ");
							sqlsize.append(" or t.clxh like '%" + clxhs[i]
							            									+ "%' ");
						}
					}
					hql.append(")");
					sqlsize.append(")");
				}
			} else {
				hql.append(" and t.clxh like '%" + clxh + "%' ");
				sqlsize.append(" and t.clxh like '%" + clxh + "%' ");
			}
			request.setAttribute("clxhs", clxh);
		}
		// 中文品牌型号
		if (zwppxh != null && !zwppxh.equals("")) {
			zwppxh = zwppxh.replaceAll(" ", "");
			zwppxh = zwppxh.replaceAll("'", "");
			zwppxh = zwppxh.replaceAll("\"", "");
			zwppxh = zwppxh.replaceAll("，", ",");
			if (zwppxh.indexOf(",") >= 0) {
				String[] zwppxhs = null;
				if (zwppxh.indexOf(",") >= 0) {
					zwppxhs = zwppxh.split(",");
					hql.append("and (");
					sqlsize.append("and (");
					for (int i = 0; i < zwppxhs.length; i++) {
						if (i == 0) {
							hql.append(" t.zwppxh like '%" + zwppxhs[i]
									+ "%' ");
							sqlsize.append(" t.zwppxh like '%" + zwppxhs[i]
							         									+ "%' ");
						} else {
							hql.append(" or t.zwppxh like '%" + zwppxhs[i]
									+ "%' ");
							sqlsize.append(" or t.zwppxh like '%" + zwppxhs[i]
							            									+ "%' ");
						}
					}
					hql.append(")");
					sqlsize.append(")");
				}
			} else {
				hql.append(" and t.zwppxh like '%" + zwppxh + "%' ");
				sqlsize.append(" and t.zwppxh like '%" + zwppxh + "%' ");
			}
			request.setAttribute("zwppxhs", zwppxh);
		}
		
		// 身份证明号码
		if (sfzmhm != null && !sfzmhm.equals("")) {
			hql.append(" and t.sfzmhm='" + sfzmhm + "'");
			sqlsize.append(" and t.sfzmhm='" + sfzmhm + "'");
			request.setAttribute("sfzmhm", sfzmhm);
		}
		
		// 号牌号码
		if (hphm != null && !hphm.equals("")) {
			hql.append(" and t.hphm='" + hphm + "'");
			sqlsize.append(" and t.hphm='" + hphm + "'");
			request.setAttribute("hphm", hphm);
		}
		
		// 号牌种类
		if (hpzl != null && !hpzl.equals("")) {
			hql.append(" and t.hpzl='" + hpzl + "'");
			sqlsize.append(" and t.hpzl='" + hpzl + "'");
			request.setAttribute("hpzl", hpzl);
		}
		
		// 流水号
		if (lsh != null && !lsh.equals("")) {
			hql.append(" and t.lsh='" + lsh + "'");
			sqlsize.append(" and t.lsh='" + lsh + "'");
			request.setAttribute("lsh", lsh);
		}
		
		//登录时间
		if (s_date != null && e_date != null && !s_date.equals("")
				&& !e_date.equals("")) {
			hql.append(" and (t.lrrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sqlsize.append(" and (t.lrrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && e_date == null && !s_date.equals("")
				&& e_date.equals("")) {
			e_date = du.date2String(new Date());
			hql.append(" and (t.lrrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sqlsize.append(" and (t.lrrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && s_date == null && s_date.equals("")
				&& !e_date.equals("")) {
			Date d = du.getAppointDate(730);
			s_date = du.date2String(d);
			hql.append(" and (t.lrrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sqlsize.append(" and (t.lrrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}

		int size = this.defaultDao.getRepositoryBySQLListSize(sqlsize.toString());
		if (size > 0) {
			hql.append(" order by t.lrrsj desc");
			List list = null;
			list = defaultDao.findHQLByPage(hql.toString(),offset,pageSize);
			ycsXycVehList = (List<YcsXycVeh>)list;
			request.setAttribute("rscount", size);
		} else {
			request.setAttribute("rscount", 0);
		}
		// 从另外一个字典表中得到号牌种类
		List esVehCodeList = defaultDao.findSQL("select dmz,dmsm1 from es_veh_code where dmlb=7 ");
		request.setAttribute("esVehCodeList", esVehCodeList);
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("ycsXycVehList", ycsXycVehList);
		log.info("method:getUserList|param:request="+request+",currentPage="+currentPage);
		return ycsXycVehList;
	}
	
	

	public String insertOrUpdateYcsXycVeh(YcsXycVeh ycsXycVeh,HttpServletRequest request) throws Exception {
		if (ycsXycVeh != null) {
			String ycsXycVehId = ycsXycVeh.getXh();
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			if(null == ycsXycVehId || "".equals(ycsXycVehId)){
				String retstr = xycVehDao.InsertXyc(ycsXycVeh.getYylx(), ycsXycVeh.getYwyy(), ycsXycVeh.getSyr(), ycsXycVeh.getSfzmmc(), ycsXycVeh.getSfzmhm(), ycsXycVeh.getClsbdh(), ycsXycVeh.getClxh(), ycsXycVeh.getZwppxh(), ycsXycVeh.getHphm(), ycsXycVeh.getHpzl(), ycsXycVeh.getXyyy(), ycsXycVeh.getLsh(), "0", ycsXycVeh.getZt(), ycsXycVeh.getBz(), ycsXycVeh.getZrfs(), user.getName(), user.getYgxm(), user.getBmid(), "", "", ToolsUtil.getIpAddr(request), user.getName(), user.getYgxm(), user.getBmid(), "", ToolsUtil.getIpAddr(request));
				return retstr;
			}else{
				String retstr = xycVehDao.UpdateXyc(ycsXycVeh.getXh(),ycsXycVeh.getYylx(), ycsXycVeh.getYwyy(), ycsXycVeh.getSyr(), ycsXycVeh.getSfzmmc(), ycsXycVeh.getSfzmhm(), ycsXycVeh.getClsbdh(), ycsXycVeh.getClxh(), ycsXycVeh.getZwppxh(), ycsXycVeh.getHphm(), ycsXycVeh.getHpzl(), ycsXycVeh.getXyyy(), ycsXycVeh.getLsh(), "0", ycsXycVeh.getZt(), ycsXycVeh.getBz(), ycsXycVeh.getZrfs(),user.getName(), user.getYgxm(), user.getBmid(), "", ToolsUtil.getIpAddr(request));
				return retstr;
			}
		} else {
			return "异常:找不到嫌疑车对象";
		}
	}

	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public List findSQL(String sql, Class clasz) throws Exception {
		return defaultDao.findSQL(sql, clasz);
	}

	public List findSQL(String sql) throws Exception {
		return defaultDao.findSQL(sql);
	}

	public XycVehDao getXycVehDao() {
		return xycVehDao;
	}

	public void setXycVehDao(XycVehDao xycVehDao) {
		this.xycVehDao = xycVehDao;
	}
	

}
