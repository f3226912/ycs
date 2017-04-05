package com.ycszh.ssh.service.jdcjsrxpk.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.service.jdcjsrxpk.JdcjsrxpkService;

public class JdcjsrxpkServiceImpl extends HibernateDaoSupport implements JdcjsrxpkService{

	private final static Logger log = Logger.getLogger(JdcjsrxpkServiceImpl.class);
	private DefaultDao defaultDao;
	
	// 根据(号牌号码 and 号牌种类) or 车辆识别代号查询图片码
	@SuppressWarnings("unchecked")
	public List getJdc(HttpServletRequest request) throws Exception {
		String hphm = request.getParameter("hphms");	// 号牌号码
		String hpzl = request.getParameter("hpzls");	// 号牌种类
		String clsbdh = request.getParameter("clsbdhs");	// 车辆识别代号
		List jdcList = new ArrayList();
		
		try {
			StringBuffer sql = new StringBuffer("select pzids,hphm,e.dmsm1,clsbdh,to_char(czrq, 'yyyy-MM-dd hh24:mi:ss') from v_xbyc_pzjl v,es_veh_code e where v.hpzl=e.dmz and e.dmlb='07' ");
			if(hphm != null && !hphm.equals("") && hpzl != null && !hpzl.equals("") && clsbdh != null && !clsbdh.equals("")){
				sql.append("and hphm='"+ hphm +"' and hpzl='"+ hpzl +"' and clsbdh='"+ clsbdh +"'");
			}else if(hphm != null && !hphm.equals("") && hpzl != null && !hpzl.equals("")){
				sql.append("and hphm='"+ hphm +"' and hpzl='"+ hpzl +"'");
			}else if(clsbdh != null && !clsbdh.equals("")){
				sql.append("and clsbdh='"+ clsbdh+"'");
			}else{
				request.setAttribute("jdcList", jdcList);
				return null;
			}
			jdcList = this.defaultDao.findSQL(sql.toString());
			request.setAttribute("jdcList", jdcList);	
			
			request.setAttribute("hphm", hphm);
			request.setAttribute("hpzl", hpzl);
			request.setAttribute("clsbdh", clsbdh);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// 根据驾驶证号查出相片回执编号,然后通过相片回执编号
	@SuppressWarnings("unchecked")
	public List getJsr(HttpServletRequest request) throws Exception {
		String jszhm = request.getParameter("jszhms");	// 驾驶证号码
		List jsrList = new ArrayList();
		try {
			StringBuffer sql = new StringBuffer("select sfzmhm,xphzbh,xm,jszhm,lxzsdz,sjrsj,to_char(lrsj, 'yyyy-MM-dd hh24:mi:ss') from v_xpk_drv_lincense ");
			if(jszhm != null && !jszhm.equals("")){
				sql.append(" where jszhm = '"+ jszhm +"'");
			}else{
				request.setAttribute("jsrList", jsrList);
				return null;
			}
			jsrList = this.defaultDao.findSQL(sql.toString());
			request.setAttribute("jsrList", jsrList);
			request.getSession().setAttribute("jsrList", jsrList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List getJsrxjxx(HttpServletRequest request) throws Exception{
		//String sfzmhm = request.getParameter("sfzmhm");	// 身份证明号码
		String sfzmhm = (String) request.getSession().getAttribute("sfzmhm");
		List jsrxjxxList = new ArrayList();
		try {
			StringBuffer sql = new StringBuffer("select sfzmhm,xm,yhly,sjhm,to_char(lrsj, 'yyyy-MM-dd hh24:mi:ss'),photo_type,photo_str,photo,shzt from v_sfrz_user_photo ");
			if(sfzmhm != null && !sfzmhm.equals("")){
				sql.append(" where sfzmhm = '"+ sfzmhm +"'");
			}else{
				request.setAttribute("jsrxjxxList", jsrxjxxList);
				return null;
			}
			jsrxjxxList = this.defaultDao.findSQL(sql.toString());
			
			String xms = null;		// 姓名
			String sfz = null;		// 身份证明号码
			String yhly = null;		// 用户来源
			String sjhm = null;		// 手机号码
			String lrsj = null;		// 录入时间
			
			List tpList = new ArrayList();	// 图片集合
			String tp = null;				// 单个图片
			
			List smList = new ArrayList();	// 图片说明集合
			String sm = null;				// 单个图片说明
			for (int i = 0; i < jsrxjxxList.size(); i++) {
				Object[] obj = (Object[]) jsrxjxxList.get(i);
				sfz = (String) obj[0];
				xms = (String) obj[1];
				yhly = (String) obj[2];
				sjhm = (String) obj[3];
				lrsj = (String)obj[4];
				
				sm = (String) obj[5];	
				smList.add(sm);			// 将单个图片说明添加进图片说明集合
				
				tp = (String) obj[6];
				tpList.add(tp);			// 将单个图片添加进图片集合
			}
			request.setAttribute("xms", xms);
			request.setAttribute("sfz", sfz);
			request.setAttribute("yhly", yhly);
			request.setAttribute("sjhm", sjhm);
			request.setAttribute("lrsj", lrsj);
			
			request.setAttribute("s", sm);
			request.setAttribute("smList", smList);
			request.setAttribute("tp", tp);
			request.setAttribute("tpList", tpList);
			
			request.setAttribute("jsrxjxxList", jsrxjxxList);
			request.getSession().setAttribute("jsrxjxxList", jsrxjxxList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public static Logger getLog() {
		return log;
	}



	

	

	
	
}
