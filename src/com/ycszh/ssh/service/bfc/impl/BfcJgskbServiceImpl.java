package com.ycszh.ssh.service.bfc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.global.SysConst;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.hbm.bfc.BfcJgskb;
import com.ycszh.ssh.hbm.bfc.BfcTsspb;
import com.ycszh.ssh.service.bfc.BfcJgskbService;

/**
 * @包名:com.ycszh.ssh.service.bfc.impl
 * @文件名:BfcJgskbServiceImpl.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-10-14
 * @描述:
 * @版本:V 1.0
 */
public class BfcJgskbServiceImpl implements BfcJgskbService {

	DefaultDao defaultDao;
	
	public DefaultDao getDefaultDao() {
		return defaultDao;
	}
	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}



	public void getBfcJgskbList(HttpServletRequest request,int currentPage) throws Exception {

		String sfzmhm = request.getParameter("sfzmhm");
		String startRksj = request.getParameter("startRksj");
		String endRksj = request.getParameter("endRksj");
		String sfyx = request.getParameter("sfyx");
		
		List bfcTsspbList = new ArrayList();
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		
		StringBuffer sql = new StringBuffer("select xh,hphm,(select dmsm1 from extshare.ext_veh_code where dmlb = '1007' and dmz = hpzl) hpzl,sfzmhm,syr,clsbdh,sjhm,rksj,cast(case when sfyx = 0 then '有效' else '无效' end as varchar2(20)) sfyx from bfc_jgskb where 1=1");
		StringBuffer countSql = new StringBuffer("select count(0) from bfc_jgskb where 1=1");
		
		if(sfzmhm != null && !"".equals(sfzmhm)){
			sql.append(" and sfzmhm = '"+sfzmhm+"'");
			countSql.append(" and sfzmhm = '"+sfzmhm+"'");
			request.setAttribute("sfzmhm", sfzmhm);
		}
		
		if(startRksj != null && !"".equals(startRksj)){
			sql.append(" and rksj > to_date('"+startRksj+"','yyyy-MM-dd')");
			countSql.append(" and rksj > to_date('"+startRksj+"','yyyy-MM-dd')");
			request.setAttribute("startRksj", startRksj);
		}
		
		if(endRksj != null && !"".equals(endRksj)){
			sql.append(" and rksj < to_date('"+endRksj+"','yyyy-MM-dd')");
			countSql.append(" and rksj < to_date('"+endRksj+"','yyyy-MM-dd')");
			request.setAttribute("endRksj", endRksj);
		}
		
		if(sfyx != null && !"".equals(sfyx)){
			sql.append(" and sfyx = '"+sfyx+"'");
			countSql.append(" and sfyx = '"+sfyx+"'");
			request.setAttribute("sfyx", sfyx);
		}
		
		int size = defaultDao.getRepositoryBySQLListSize(countSql.toString());
		if (size > 0) {
			bfcTsspbList = defaultDao.findSQLByPage(sql.toString(),offset,pageSize);
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
		request.setAttribute("bfcJbxxbList", bfcTsspbList);
	}
	
	public BfcJgskb getBfcJgskbInfo(String xh) throws Exception {
		
		return (BfcJgskb)defaultDao.findSQL("select xh,hphm,(select dmsm1 from extshare.ext_veh_code where dmlb = '1007' and dmz = hpzl) hpzl,sfzmmc,sfzmhm,syr,clsbdh,fun_bfc_jgskb_clzt_value(clzt) clzt,qzbfqz,ccdjrq,sjhm,rksj,yssj,case when sfyx = 0 then '有效' else '无效' end sfyx,sjlx,bz,czr,czrxm,czrbm,czrkjbm,czip,czsj,syn_flag,tran_flag,tran_date from BFC_JGSKB where xh = '"+xh+"'", BfcJgskb.class).get(0);
	}

}
