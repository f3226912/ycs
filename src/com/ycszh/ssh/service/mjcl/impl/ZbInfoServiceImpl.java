package com.ycszh.ssh.service.mjcl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.log4j.Logger;

import com.ycszh.ssh.dao.mjcl.ZbInfoDao;
import com.ycszh.ssh.service.mjcl.ZbInfoService;
import com.ycszh.ssh.dao.DefaultDao;
public class ZbInfoServiceImpl implements ZbInfoService {

	private final static Logger log = Logger.getLogger(ZbInfoServiceImpl.class);

	private ZbInfoDao zbInfoDao;
	private DefaultDao defaultDao;
	public DefaultDao getDefaultDao() {
		return defaultDao;
	}
	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}
	public void getZbInfoData(HttpServletRequest request) throws Exception {

		String sfzmmc = request.getParameter("sfzmmc") == null ? "A" : request.getParameter("sfzmmc").trim().toUpperCase();
		String sfzmhm = request.getParameter("sfzmhm") == null ? "" : request.getParameter("sfzmhm").trim().toUpperCase();
		String result = "";
		String v1_zbfl="";
		String v1_zbh="";
		if (!sfzmhm.equals("")) {
	
			List<Object> r = zbInfoDao.getZbInfoData(sfzmmc, sfzmhm);
            
			List<Object> realData = new  ArrayList<Object>();
			
			List  clxg_zb = new ArrayList();

			if (r != null && r.size() > 0) {
				for (int i=0;i<r.size();++i){
						List a = (List) r.get(i);
					    v1_zbfl=a.get(1).toString();
						v1_zbh=a.get(3).toString();
						//把指标的验证过程数据返回到页面显示
						System.out.println( v1_zbh+"---"+v1_zbfl);
						StringBuffer hql = new StringBuffer("select YZ_LSH, DECODE(YZGW,'0','预录入岗','3','受理岗') as YZGW,YZ_ZBLX,YZ_ZBH,YZ_GZH,YZ_SFZMHM,yz_xm,YZ_CLSBDH,to_char(CZSJ,'yyyy-mm-dd hh24:mi:ss') as czsj,decode(verify_flag,0,'验证不通过',1,'验证通过') as verify_flag,verify_MSG from clxg_zb_verify where   (YZGW='0' OR YZGW='3') and czr='系统后台验证' AND YZ_ZBH='"+v1_zbh+"' and YZ_ZBLX='"+v1_zbfl+"' ORDER BY YZ_LSH,CZSJ");
						List tempclxg_zb = new ArrayList ();
						tempclxg_zb= this.defaultDao.findSQL(hql.toString());
					    /*
						for (int j=0;j<tempclxg_zb.size();++j)
						{   
							Object[] ob=(Object[])tempclxg_zb.get(j);
							System.out.println(ob[1]);
						}
				        */
			
						//realData.add(tempDataMap);
						clxg_zb.addAll(tempclxg_zb);
						
				}
				
				//Map<String, String> zbflMap = getZd("ZBFL");
				//Map<String, String> zbztMap = getZd("ZBZT");
           
				//for (int i = 0; i < r.size(); i++) {
					/*
					if (i == r.size() - 1) {
						result = r.get(i).toString();
					} else {
						Object object = r.get(i);
						
						if (object != null) {
							
							Map<String, String> tempDataMap = (Map<String, String>) object;
							
							if(tempDataMap.get("ID")!=null && !tempDataMap.get("ID").trim().equals("") ){
							
								v1_zbfl=tempDataMap.get("ZBFL");
								v1_zbh=tempDataMap.get("ZBH");
								
								// 翻译指标分类
								if (zbflMap != null && zbflMap.size() > 0) {
									tempDataMap.put("ZBFL", zbflMap.get(tempDataMap.get("ZBFL")));
								}

								// 翻译指标状态
								if (zbztMap != null && zbztMap.size() > 0) {
									tempDataMap.put("ZBZT", zbztMap.get(tempDataMap.get("ZBZT")));
								}
								
                               
							
							
							}
							

						}
					}
                   */ 
				//}

				request.setAttribute("r_", r);
				
				request.setAttribute("r1_", clxg_zb);
             
			}else{//没有指标信息的，直接查询验证记录表的信息显示出来
				StringBuffer hql2 = new StringBuffer("select YZ_LSH, DECODE(YZGW,'0','预录入岗','3','受理岗') as YZGW,YZ_ZBLX,YZ_ZBH,YZ_GZH,YZ_SFZMHM,yz_xm,YZ_CLSBDH,to_char(CZSJ,'yyyy-mm-dd hh24:mi:ss') as czsj,decode(verify_flag,0,'验证不通过',1,'验证通过') as verify_flag,verify_MSG from clxg_zb_verify where   (YZGW='0' OR YZGW='3') and czr='系统后台验证' AND YZ_SFZMHM='"+sfzmhm+"' ORDER BY YZ_LSH,CZSJ");
				List tempclxg_zb2 = new ArrayList ();
				tempclxg_zb2= this.defaultDao.findSQL(hql2.toString());
				clxg_zb.addAll(tempclxg_zb2);
				request.setAttribute("r1_", clxg_zb);
			}
		} else {
			result = "-查询失败:请输入正确身份证明号码";
		}

		request.setAttribute("result", result);

		request.setAttribute("sfzmmc", sfzmmc);
		request.setAttribute("sfzmhm", sfzmhm);
       
	}

	/**
	 * 获取数字字典
	 * 
	 * @param dmlb
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> getZd(String dmlb) throws Exception {

		Map<String, String> zdMap = new LinkedMap();

		if (dmlb != null && !dmlb.trim().equals("")) {
			String sql = " select a.dmz,a.dmms1 from CLXG_SJZD a where a.DMLB = '" + dmlb.trim() + "' ";
			List<Object> tempList = defaultDao.findSQL(sql);
			if (tempList != null && tempList.size() > 0) {
				for (Object object : tempList) {
					if (object != null) {
						Object[] tempObj = (Object[]) object;
						String dmz = tempObj[0] == null ? "" : tempObj[0].toString().trim();
						String dmms1 = tempObj[1] == null ? "" : tempObj[1].toString().trim();
						if (!dmz.equals("")) {
							zdMap.put(dmz, dmms1);
						}
					}
				}
			}
		}

		return zdMap;

	}

	public static Logger getLog() {
		return log;
	}

	public ZbInfoDao getZbInfoDao() {
		return zbInfoDao;
	}

	public void setZbInfoDao(ZbInfoDao zbInfoDao) {
		this.zbInfoDao = zbInfoDao;
	}

}
