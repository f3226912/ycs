package com.ycszh.ssh.service.kcpb.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.LinkedMap;

import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.dao.kgpb.KgpbPbxxbDao;
import com.ycszh.ssh.hbm.kgpb.KgpbKgxxb;
import com.ycszh.ssh.hbm.kgpb.KgpbPbxxb;
import com.ycszh.ssh.service.kcpb.KgpbPbxxbService;
import com.ycszh.util.DateUtil;

/**
 * @包名:com.ycszh.ssh.service.kcpb.impl
 * @文件名:KgpbPbxxbServiceImpl.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-11-13
 * @描述:
 * @版本:V 1.0
 */
public class KgpbPbxxbServiceImpl implements KgpbPbxxbService {

	private KgpbPbxxbDao kgpbPbxxbDao;
	private DefaultDao defaultDao;
	
	public DefaultDao getDefaultDao() {
		return defaultDao;
	}
	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}
	public KgpbPbxxbDao getKgpbPbxxbDao() {
		return kgpbPbxxbDao;
	}
	public void setKgpbPbxxbDao(KgpbPbxxbDao kgpbPbxxbDao) {
		this.kgpbPbxxbDao = kgpbPbxxbDao;
	}

	public Map<String, Map> getPbInfo(HttpServletRequest request) throws Exception {
		
		String pbrq = request.getParameter("pbrq");
		
		StringBuffer sql = new StringBuffer("select id,(select xm from KGPB_KGXXB where jh = kgid) kgid,(select kcmc from KGPB_KCXXB where kcbh = kcid) kcid,to_char(pbrq,'yyyy-MM-dd')||' ('||to_char(pbrq,'day')||')',(select gdkgid from kgpb_kcxxb where gdkgid = (select id from kgpb_kgxxb where jh = kgid)) gdkgid from KGPB_PBXXB where 1=1");
		
		if(pbrq == null || "".equals(pbrq)){
			pbrq = DateUtil.date2String(new Date(), "yyyy-MM-dd");
		}
		
		sql.append(" and pbrq >= to_date('"+pbrq+"','yyyy-MM-dd') and pbrq < to_date('"+pbrq+"','yyyy-MM-dd')+6 order by pbrq desc,kcid,to_number(id)");
		request.setAttribute("pbrq", pbrq);
		
		List list =  kgpbPbxxbDao.findSQL(sql.toString());
		
		String pbrq2 = null;
		String kcid = null;
		Map pbrqMap = new LinkedMap();
		Map kcMap = new LinkedMap();
		List kgList = null;
		
		if(list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				Object[] pbInfo = (Object[])list.get(i);
				if(i == 0){
					pbrq2 = pbInfo[3]+"";
					kcid = pbInfo[2]+"";
					kgList = new ArrayList();
				}
				else{
					if(!kcid.equals(pbInfo[2]+"")){
						kcMap.put(kcid, kgList);
						kcid = pbInfo[2]+"";
						kgList = new ArrayList();
					}
					
					if(!pbrq2.equals(pbInfo[3]+"")){
						
						// 如果 kcMap的长度为0 表示这两天相连的考场ID相同
						if(kcMap.size() < 1){
							kcMap.put(kcid, kgList);
							pbrqMap.put(pbrq2, kcMap);
							kcMap = new LinkedMap();
							kgList = new ArrayList();
						}
						else{
							pbrqMap.put(pbrq2, kcMap);
						}
						
						pbrq2 = pbInfo[3]+"";
						kcMap = new LinkedMap();
					}
				}
				kgList.add(pbInfo);
			}
			kcMap.put(kcid, kgList);
			pbrqMap.put(pbrq2, kcMap);
		}
		
		return pbrqMap; 
	}

	public int updatePbInfo(HttpServletRequest request) throws Exception {
		
		String id = request.getParameter("id");
		String xpbmj = request.getParameter("xpbmj");  // 新排班民警警号
		
		KgpbPbxxb kgpbPbxxb = kgpbPbxxbDao.getRepository(id);
		// 旧排班民警
		String jpbmj = kgpbPbxxb.getKgid();
		request.setAttribute("jpbmj", jpbmj);
		
		kgpbPbxxb.setKgid(xpbmj);
		
		kgpbPbxxbDao.updateRepository(kgpbPbxxb);
		
		return 0;
	}
	
	public int dxfs(HttpServletRequest request) throws Exception{
		
		String id = request.getParameter("id");
		String xpbmj = request.getParameter("xpbmj");  // 新排班民警警号
		String jpbmj = request.getAttribute("jpbmj")+"";
		
		KgpbPbxxb kgpbPbxxb = kgpbPbxxbDao.getRepository(id);
		
		List xKgInfoList = defaultDao.findSQL("select * from KGPB_KGXXB where jh = "+kgpbPbxxb.getKgid()+"", KgpbKgxxb.class);
		if(xKgInfoList.size() > 0){
			KgpbKgxxb xKgInfo = (KgpbKgxxb)xKgInfoList.get(0);
			
			StringBuffer dxrn = new StringBuffer("手动排班：尊敬的"+xKgInfo.getXm()+"警官，由与您的排班被变更。以下是您最近3天的排班信息"); 
			
			List list = kgpbPbxxbDao.findSQL("select wm_concat(pbxx) from ( " +
					"select to_char(pbrq,'yyyy-MM-dd')||'('||to_char(Pbrq,'day')||' - '||(select kcmc from KGPB_KCXXB where kcbh = kcid)||')  ' pbxx " +
					"from KGPB_PBXXB where kgid = '"+xKgInfo.getJh()+"' order by Pbrq desc ) where rownum < 4");
			
			if(list.size() > 0){
				dxrn.append(list.get(0).toString());
				kgpbPbxxbDao.dxFs(xKgInfo.getSjhm(), dxrn.toString());
			}
		}
		
		List jKgInfoList = defaultDao.findSQL("select * from KGPB_KGXXB where jh = "+jpbmj+"", KgpbKgxxb.class);
		if(jKgInfoList.size() > 0){
			KgpbKgxxb jKgInfo = (KgpbKgxxb)jKgInfoList.get(0);
			
			StringBuffer dxrn = new StringBuffer("手动排班：尊敬的"+jKgInfo.getXm()+"警官，由与您的排班被变更。以下是您最近3天的排班信息"); 
			
			List list = kgpbPbxxbDao.findSQL("select wm_concat(pbxx) from ( " +
					"select to_char(pbrq,'yyyy-MM-dd')||'('||to_char(Pbrq,'day')||' - '||(select kcmc from KGPB_KCXXB where kcbh = kcid)||')  ' pbxx " +
					"from KGPB_PBXXB where kgid = '"+jKgInfo.getJh()+"' order by Pbrq desc ) where rownum < 4");
			
			if(list.size() > 0){
				dxrn.append(list.get(0).toString());
				kgpbPbxxbDao.dxFs(jKgInfo.getSjhm(), dxrn.toString());
			}
		}
		
		return 0;
	}

	
	public KgpbPbxxb getPbInfo(String id) throws Exception{
		
		return (KgpbPbxxb)kgpbPbxxbDao.getRepository(id);
	}
}
