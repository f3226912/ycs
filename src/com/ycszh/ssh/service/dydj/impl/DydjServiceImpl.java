package com.ycszh.ssh.service.dydj.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.dydj.DydjYwsbspbDao;
import com.ycszh.ssh.dao.dydj.IDydjServiceDao;
import com.ycszh.ssh.hbm.dydj.DydjSbInfo;
import com.ycszh.ssh.hbm.dydj.DydjYhUser;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspb;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspbTemp;
import com.ycszh.ssh.hbm.ydwt.WtydnsYwsbspb;
import com.ycszh.ssh.service.dydj.IDydjService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.StringUtil;

public class DydjServiceImpl implements IDydjService {
	private IDydjServiceDao dydjServiceDao;
	private DydjYwsbspbDao dydjYwsbspbDao;
	private String uri = "";

	@SuppressWarnings("unchecked")
	public DydjYwsbspbTemp getDydjYwwbspByLsh(HttpServletRequest request , String lsh)
			throws Exception {
		DydjYwsbspbTemp dydjYwsbspb2 = null;
		if(!StringUtil.isNull(lsh)){
			String hql = "from DydjYwsbspb t where t.lsh = '"+lsh+"'";
			List list = dydjServiceDao.getRepositories(hql);
			if(list != null && list.size() > 0){
				DydjYwsbspb dydjYwsbspb = (DydjYwsbspb) list.get(0);
				dydjYwsbspb2 = new DydjYwsbspbTemp();
				dydjYwsbspb2 = (DydjYwsbspbTemp) getXclog(dydjYwsbspb2, dydjYwsbspb);
				//解密字段
				if(!StringUtil.isNull(dydjYwsbspb2.getHphm())){
					dydjYwsbspb2.setHphm(dydjYwsbspbDao.getDecrypt(dydjYwsbspb2.getHphm(), SysConst.P_KEY));
				}
				if(!StringUtil.isNull(dydjYwsbspb2.getHpzl())){
					dydjYwsbspb2.setHpzl(dydjYwsbspbDao.getDecrypt(dydjYwsbspb2.getHpzl(), SysConst.P_KEY));
				}
				if(!StringUtil.isNull(dydjYwsbspb2.getClsbdh())){
					dydjYwsbspb2.setClsbdh(dydjYwsbspbDao.getDecrypt(dydjYwsbspb2.getClsbdh(), SysConst.P_KEY));
				}
				if(!StringUtil.isNull(dydjYwsbspb2.getDjzsbh())){
					dydjYwsbspb2.setDjzsbh(dydjYwsbspbDao.getDecrypt(dydjYwsbspb2.getDjzsbh(), SysConst.P_KEY));
				}
				if(!StringUtil.isNull(dydjYwsbspb2.getZhth())){
					dydjYwsbspb2.setZhth(dydjYwsbspbDao.getDecrypt(dydjYwsbspb2.getZhth(), SysConst.P_KEY));
				}
				if(!StringUtil.isNull(dydjYwsbspb2.getDyhth())){
					dydjYwsbspb2.setDyhth(dydjYwsbspbDao.getDecrypt(dydjYwsbspb2.getDyhth(), SysConst.P_KEY));
				}
			}
		}
		return dydjYwsbspb2;
	}
	
	@SuppressWarnings("unchecked")
	public String getDeptOrgId(HttpServletRequest request) throws Exception{
		String orgId = "";
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		String deptsql = "select org_id from (select t.* from v_veh_org_ycs t start with " +
				"org_id = '" + user.getBmid() + "' connect by prior up_org = org_id) " +
				"where up_org = 'C34702A8FED97CBFE040007F0100339B'";
		List deptidlist = dydjServiceDao.findSQL(deptsql);
		if(null != deptidlist && deptidlist.size() > 0){
			orgId = deptidlist.get(0).toString();
		}
		return orgId;
	}
	
	public byte[] getImageBlob(HttpServletRequest request, Integer tpm) throws Exception{
		byte[] b = null;
		b = this.dydjServiceDao.getImageBlob(tpm);
		return b;
	}
	
	public byte[] getUserImageBlob(HttpServletRequest request, String yzyhdm) throws Exception{
		byte[] b = null;
		b = this.dydjServiceDao.getUserImageBlob(yzyhdm);
		return b;
	}
	 /**
	  * 查出预警数据(30天之内)
	  * @throws Exception
	  */
	@SuppressWarnings("unchecked")
	public List<DydjSbInfo> getWarnInfo(HttpServletRequest request,int currentPage) throws Exception {
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String hphm = request.getParameter("hphm");
		String hpzl = request.getParameter("hpzl");
		String stauts= request.getParameter("stauts");
		List<DydjSbInfo> doData = new ArrayList<DydjSbInfo>();
	    if(null!=stauts){
	    	String sql =" select lsh,ywyy,syr,cast(hpzl as varchar2(10)),hphm,clpp1,clxh," +
	    			    " to_char(sqrq,'yyyy-MM-dd hh24:mi:ss') sqrq,to_char(bjrq,'yyyy-MM-dd hh24:mi:ss'),glbm,clsbdh from es_veh_flow t " +
	    			    " where ywlx='E' and ywyy in ('A','B') and sqrq >to_Date('2014-4-1','yyyy-MM-dd')";
	    	
	  	String countStr=" select count(0) from es_veh_flow t " +
	  			        " where ywlx='E' and ywyy in ('A','B') and sqrq >to_Date('2014-4-1','yyyy-MM-dd')";
	  		if(null!= hphm && !"".equals(hphm)){
		  			sql+=" and hphm ='"+hphm+"'";
		  	   countStr+=" and hphm ='"+hphm+"'";
	  			request.setAttribute("hphm", hphm);
	  		}	  		
	  		if(null != s_date && !"".equals(s_date)){
		  		    sql+=" and sqrq >= to_date('"+s_date+" 00:00:00','yyyy-MM-dd hh24:mi:ss') ";	
		  	   countStr+=" and sqrq >= to_date('"+s_date+" 00:00:00','yyyy-MM-dd hh24:mi:ss') ";
		  		request.setAttribute("s_date",s_date);
	  		}
	  		if(null != e_date && !"".equals(e_date)){
	  		        sql+=" and sqrq <= to_date('"+e_date+" 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
	  		   countStr+=" and sqrq <= to_date('"+e_date+" 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
	  		    request.setAttribute("e_date",e_date);
	  		}
	  		
	  		if(null!=hpzl && !"".equals(hpzl)){
	  			    sql+=" and hpzl ='"+hpzl+"'";
	  		   countStr+=" and hpzl ='"+hpzl+"'";
	  		    request.setAttribute("hpzl", hpzl);
	  		}            
	  		sql+=" and not exists (select hphm from DYDJ_YWSBSPB_in where hphm=t.HPHM  and lrsj>t.SQRQ-90 and lrsj<t.SQRQ )  order by SQRQ";
	  		
	   countStr+=" and not exists (select hphm from DYDJ_YWSBSPB_in where hphm=t.HPHM  and lrsj>t.SQRQ-90 and lrsj<t.SQRQ ) ";
	  		int size =Integer.parseInt(this.dydjServiceDao.findSQL(countStr).get(0).toString());
	  		int pageSize = SysConst.PAGESIZE;
	  		int offset = SysConst.PAGESIZE*(currentPage-1);
	  		String curi = request.getRequestURI();
	  		List list =this.dydjServiceDao.findSQLByPage(sql, offset, pageSize);	  		
	  		if(null != list && list.size()>0){
	  			for (int i = 0; i < list.size(); i++) {
	  				DydjSbInfo info = new DydjSbInfo();
	  				Object[] objs = (Object[])list.get(i);
	  				if(objs[0]!=null){
	  					info.setLsh(objs[0].toString());
	  				}
	  				if(objs[1]!=null){
	  				    info.setYwyy(objs[1].toString());	
	  				}
	  				if(objs[2]!=null){
	  					info.setSyr(objs[2].toString());
	  				}				
	  				if(objs[3]!=null){
	  					info.setHpzl(objs[3].toString());
	  				}
	  				if(objs[4]!=null){
	  					info.setHphm(objs[4].toString());
	  				}
	  				if(objs[5]!=null){
	  					info.setClpp1(objs[5].toString());
	  				}
	  				if(objs[6]!=null){
	  					info.setClxh(objs[6].toString());
	  				}
	  				if(objs[7]!=null){
	  					info.setSqrq(DateUtil.string2Date(objs[7].toString(), "yyyy-MM-dd HH:mm:ss"));
	  				}
	  				if(objs[8]!=null){
	  					info.setBjrq(DateUtil.string2Date(objs[8].toString(), "yyyy-MM-dd HH:mm:ss"));
	  				}
	  				if(objs[9]!=null){
	  					info.setGlbm(objs[9].toString());
	  				}	
	  				if(objs[10]!=null){
	  					info.setClsbdh(objs[10].toString());
	  				}
	  				doData.add(info);
	  			}

	  		   if(doData.size()>0){
	  			   Map<String,String> deps= this.getQueryDepMc();
	  		  		Map<String,String> hpzls= this.getQueryHpzl();
	  			   for (DydjSbInfo inter : doData) {					
	  			  		inter.setHpzl(hpzls.get(inter.getHpzl()));
	  			  		inter.setGlbm(deps.get(inter.getGlbm()));
	  					if("A".equals(inter.getYwyy())){
	  						inter.setYwyy("抵押登记");
	  					}else if("B".equals(inter.getYwyy())){
	  						inter.setYwyy("解除抵押登记");
	  					}
	  			    }
	  		   }
	  		}
	  		Map map=new HashMap();
			map.put("uri", curi);
			map.put("pagesize", pageSize);
			map.put("rscount", size);
			map.put("currentpage", currentPage);
			request.setAttribute("rscount", size);
			request.setAttribute("map", map);
	    }		
		
		return doData;
	}
	
	@SuppressWarnings("unchecked")
	public List getDydjsbspList(HttpServletRequest request, int currentpage, String cztype) throws Exception{
		List dydjsbspList = null;
		int count=0;
		Map map=new HashMap();
		StringBuffer hqlListBuff = new StringBuffer("select id, lsh, ywlx, sqlx, yhsl_lsh, hphm, hpzl, clsbdh, djzsbh,zhth, dyhth, yh_zzjg_zh,yh_zzjg_frdb,yh_zzjg_yyzz, ");
		hqlListBuff.append(" yh_zzjg_dwmc, yh_zzjg_dz,yh_zzjg_njrq,yh_zzjg_njyxq, yh_sfz_cardname, yh_sfz_cardsex,yh_sfz_cardno, yh_sfz_cardaddress, ");
		hqlListBuff.append(" yh_sfz_cardphoto_id, xx_zd1,xx_zd2, xx_zd3, dyr_sfz_cardname, dyr_sfz_cardsex,dyr_sfz_cardno, dyr_sfz_cardaddress, ");
		hqlListBuff.append(" dyr_sfz_cardphoto_id, dyr_zzjg_zh, dyr_zzjg_frdb, dyr_zzjg_yyzz, dyr_zzjg_dwmc,dyr_zzjg_dz, dyr_zzjg_njrq, ");
		hqlListBuff.append(" dyr_zzjg_njyxq,yh_cg_pch, yh_cg_pch_sl, qj_qjrxm, qj_tddz,qj_yzbm, qj_lxdh, yj_sjrxm,yj_tddz, yj_yzbm, ");
		hqlListBuff.append(" yj_lxdh, yzqjr_xm,yzqjr_lxdh, sb_sm, lrzh, lrxm, to_char(lrsj, 'yyyy-MM-dd HH24:mi:ss'),lrip, sbzt, sbzt_tbly, sbzt_kdbh,cg_yhdm, ");
		hqlListBuff.append(" cg_yhxm,cg_yhsj,cg_ip, yz_yhdm, yz_yhxm, yz_yhsj,yz_ip, syn_flag, tran_flag,tran_date, ycs_deptid, ycs_deptname ");
		hqlListBuff.append(" from dydj_ywsbspb_in s where lsh is not null ");
		
		StringBuffer hqlCountBuff = new StringBuffer("select count(1) from dydj_ywsbspb_in s where lsh is not null ");
		StringBuffer strCondition = new StringBuffer("");
		String lsh = request.getParameter("lsh");
		String hphm = request.getParameter("hphm");
		String hpzl = request.getParameter("hpzl");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String lrzh = request.getParameter("lrzh");
		String yhZzjgzh = request.getParameter("yhZzjgzh");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentpage-1);
		if("query".equals(cztype)){
			uri = request.getRequestURI();
		}	
		//流水号
		if(lsh != null && !"".equals(lsh)){
			strCondition.append(" and s.lsh = '"+lsh+"' ");
			request.setAttribute("lsh", lsh);
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
		//受理时间
		if(StringUtil.isNull(hphm)){
			if (s_date != null && e_date != null && !s_date.equals("")
					&& !e_date.equals("")) {
				strCondition.append(" and (s.lrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				request.setAttribute("s_date", s_date);
				request.setAttribute("e_date", e_date);
			} else if (s_date != null && !s_date.equals("") && (e_date == null || "".equals(e_date))) {
				e_date = DateUtil.date2String(new Date());
				strCondition.append(" and (s.lrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				request.setAttribute("s_date", s_date);
				request.setAttribute("e_date", e_date);
			} else if (e_date != null && !e_date.equals("") && (s_date == null || "".equals(s_date))) {
				strCondition.append(" and s.lrsj <= to_date('" + e_date
						+ "','yyyy-MM-dd')+1 ");
				request.setAttribute("s_date", s_date);
				request.setAttribute("e_date", e_date);
			}
		}
		
		//经办人
		if(lrzh != null && !"".equals(lrzh)){
			strCondition.append(" and s.lrzh like '%" + lrzh + "%' ");
			request.setAttribute("lrzh", lrzh);
		}
		//银行组织机构代码证
		if(!StringUtil.isNull(yhZzjgzh)){
			strCondition.append(" and s.yh_zzjg_zh like '%" + yhZzjgzh + "%' ");
			request.setAttribute("yhZzjgzh", yhZzjgzh);
		}
		hqlListBuff.append(strCondition.toString());
		hqlListBuff.append(" ORDER BY s.lrsj DESC ");
		count = this.dydjServiceDao.getRepositoryBySQLListSize(hqlCountBuff.toString()+strCondition.toString());
		if(count > 0){
			if("query".equals(cztype)){
				dydjsbspList = this.dydjServiceDao.findSQLByPage(hqlListBuff.toString(), offset, pageSize);
			}else if("export".equals(cztype)){
				if(count > 10000){
					map.put("uri", uri);
					map.put("pagesize", pageSize);
					map.put("rscount", count);
					map.put("currentpage", currentpage);
					request.setAttribute("rscount", count);
					request.setAttribute("map", map);
					request.setAttribute("dydjsbspList", dydjsbspList);
					request.setAttribute("exportData", "数据量太多无法一次导出，请筛选！");
					return dydjsbspList;
				}
				dydjsbspList = this.dydjServiceDao.findSQL(hqlListBuff.toString());
			}
		}
		map.put("uri", uri);
		map.put("pagesize", pageSize);
		map.put("rscount", count);
		map.put("currentpage", currentpage);
		request.setAttribute("rscount", count);
		request.setAttribute("map", map);
		request.setAttribute("dydjsbspList", dydjsbspList);
		return dydjsbspList;
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
		}
		
		return obj1;
	}
	/**
	 * 查询号牌种类字典(hpzl)
	 * @return
	 */
	public Map<String, String> getQueryHpzl() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		StringBuffer sql = new StringBuffer("select ws.dmz,ws.dmsm1 from wscgs_sjzd ws where ws.dmlb = '7' order by ws.dmz ");
		List resultList = this.dydjServiceDao.findSQL(sql.toString());
		Object obj [] = new Object[2];
		for (int i = 0; i < resultList.size(); i++) {
			obj = (Object[])resultList.get(i);
			map.put(obj[0].toString(), obj[1].toString());
		}
		return map;
	}
	public Map<String,String> getQueryDepMc() throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		StringBuffer sql = new StringBuffer("select glbm,bmmc from v_frm_department ");
		List resultList = this.dydjServiceDao.findSQL(sql.toString());
		Object obj [] = new Object[2];
		for (int i = 0; i < resultList.size(); i++) {
			obj = (Object[])resultList.get(i);
			map.put(obj[0].toString(), obj[1].toString());
		}
		return map;
	}
	public IDydjServiceDao getDydjServiceDao() {
		return dydjServiceDao;
	}

	public void setDydjServiceDao(IDydjServiceDao dydjServiceDao) {
		this.dydjServiceDao = dydjServiceDao;
	}

	public DydjYwsbspbDao getDydjYwsbspbDao() {
		return dydjYwsbspbDao;
	}

	public void setDydjYwsbspbDao(DydjYwsbspbDao dydjYwsbspbDao) {
		this.dydjYwsbspbDao = dydjYwsbspbDao;
	}
	
}
