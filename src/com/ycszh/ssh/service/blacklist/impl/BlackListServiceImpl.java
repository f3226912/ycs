package com.ycszh.ssh.service.blacklist.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.blacklist.DbjgHmdyhDao;
import com.ycszh.ssh.dao.blacklist.DbjgHmdyhLogDao;
import com.ycszh.ssh.dao.blacklist.DbjgSjzdDao;
import com.ycszh.ssh.dao.blacklist.impl.DbjgSjzdLogDaoImpl;
import com.ycszh.ssh.hbm.blacklist.DbjgHmdyh;
import com.ycszh.ssh.hbm.blacklist.DbjgHmdyhLog;
import com.ycszh.ssh.hbm.blacklist.DbjgSjzd;
import com.ycszh.ssh.hbm.blacklist.DbjgSjzdLog;
import com.ycszh.ssh.service.blacklist.BlackListService;
import com.ycszh.util.StringUtil;
import com.ycszh.util.ToolsUtil;
/**
 * 黑名单业务管理
 * @since 2014-02-14
 * @author lzj
 * @author Administrator
 *
 */
public class BlackListServiceImpl implements BlackListService {	
	private DbjgHmdyhDao  dbjgHmdyhDao;
	
	private DbjgHmdyhLogDao dbjgHmdyhLogDao;
	
	private DbjgSjzdDao dbjgSjzdDao;
	
	private DbjgSjzdLogDaoImpl dbjgSjzdLogDao;
	
	private final static Logger log = Logger.getLogger(BlackListServiceImpl.class);
	
	/**
	 * 获取黑名单信息列表（含分页和模糊查询）
	 * @param request
	 * @param currentPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DbjgHmdyh> getBlackYHList(HttpServletRequest request,int currentPage) throws Exception {
		StringBuffer hql = new StringBuffer("from DbjgHmdyh as t where 1=1 and synFlag !='DW' ");
		String sfzmhm = request.getParameter("sfzmhm");
		String xm = request.getParameter("xm");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<DbjgHmdyh> blackYhList = new ArrayList<DbjgHmdyh>();
		// 身份证号码
		if (sfzmhm != null && !sfzmhm.equals("")) {
			 hql.append(" and t.sfzmhm like '%" + sfzmhm+ "%' ");
			 request.setAttribute("sfzmhm", sfzmhm);
		}
		
		// 姓名
		if (xm != null && !xm.equals("")) {
			xm = xm.replaceAll(" ", "");
			xm = xm.replaceAll("'", "");
			xm = xm.replaceAll("\"", "");
			xm = xm.replaceAll("，", ",");
			if (xm.indexOf(",") >= 0) {
				String[] userNames = null;
				if (xm.indexOf(",") >= 0) {
					userNames = xm.split(",");
					hql.append("and (");
					for (int i = 0; i < userNames.length; i++) {
						if (i == 0) {
							hql.append(" t.xm like '%" + userNames[i]
									+ "%' ");
						} else {
							hql.append(" or t.xm like '%" + userNames[i]
									+ "%' ");
						}
					}
					hql.append(")");
				}
			} else {
				hql.append(" and t.xm like '%" + xm + "%' ");
			}
			request.setAttribute("xm", xm);
		}
		
		int size = dbjgHmdyhDao.getSize(hql.toString());
		if (size > 0) {
			hql.append(" order by t.czrq desc");
			blackYhList = dbjgHmdyhDao.findHQLByPage(hql.toString(),offset,pageSize);
			Map<String,String>  sdMap= this.getTypeDict("1"); //锁定
			 Map<String,String> jsMap= this.getTypeDict("2"); //解锁
			for (int i = 0; i < blackYhList.size(); i++) {
				DbjgHmdyh pb = (DbjgHmdyh)blackYhList.get(i);
				pb.setSdyy(sdMap.get(pb.getSdyy()));
				pb.setJsyy(jsMap.get(pb.getJsyy()));		    
			}			
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
		log.info("method:getBlackYHList|param:request="+request+",currentPage="+currentPage);
		return blackYhList;
	}
	
	/**
	 * 添加/修改黑名单用户信息
	 * @param request
	 * @param  
	 * @return
	 * @throws Exception
	 */
	public int addHmdUser(HttpServletRequest request,DbjgHmdyh hmYh) throws Exception {
		int result=0;
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);	
		String editType = request.getParameter("editType");
			if("新增".equals(editType.trim())){
				    String hql ="from DbjgHmdyh as t where synFlag !='DW' and  sfzmhm ='"+hmYh.getSfzmhm()+"' ";
				     List<DbjgHmdyh> yhList=this.dbjgHmdyhDao.getRepositories(hql);
				     if(null!=yhList && yhList.size()>0){
				    	 result =3;
				     }else{
				    	 hmYh.setRklx("1");  //入口类型（新增默认为1）
					     hmYh.setDqzt("1");  //当前状态   (新增默认为1：锁定)
					     if(hmYh.getSdlx().equals("2")){
					    	 hmYh.setSdyxqz(null);
					     }
					     hmYh.setCzr(user.getName());
					     hmYh.setCzrxm(user.getYgxm());
					     hmYh.setCzbm(user.getBmid());
					     hmYh.setCzbmKj(null);
						 hmYh.setCzrq(new Date());
						 hmYh.setCzip(ToolsUtil.getIpAddr(request));
						 hmYh.setSynFlag("UW");
						 hmYh.setTranFlag(null);
						 this.dbjgHmdyhDao.addRepository(hmYh);
						 
						 DbjgHmdyhLog pdLog=insertHmdYhLog(hmYh);  
						 pdLog.setCznr("新增黑名单用户");
						 pdLog.setCzr(user.getName());
						 pdLog.setCzrxm(user.getYgxm());
						 pdLog.setCzbm(user.getBmid());
						 pdLog.setCzbmKj(null);
						 pdLog.setCzrq(new Date());
						 pdLog.setCzip(ToolsUtil.getIpAddr(request));
						 pdLog.setSynFlag("UW");
						 pdLog.setTranFlag(null);
						 pdLog.setTranDate(null);
						 this.dbjgHmdyhLogDao.addRepository(pdLog);  	
						 result=1;	
				     }				     
			}
		return result;
	}
	/**
     * 根据xh删除黑名单用户信息,2014-5-28 修改成能同步到外网的update形式
     */
	public Integer delHmyhByxh(String xh,HttpServletRequest request) throws Exception {
		try {
			DbjgHmdyh hmdYh = dbjgHmdyhDao.getRepository(xh);
			if (hmdYh != null) {
				User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
				 //this.dbjgHmdyhDao.deleteRepository(hmdYh);
				hmdYh.setSynFlag("DW");
				hmdYh.setTranDate(null);
				hmdYh.setTranFlag(null);
				this.dbjgHmdyhDao.updateRepository(hmdYh);//删除改成修改形式
				 //添加日志
				 DbjgHmdyhLog pdLog=insertHmdYhLog(hmdYh);  
				 pdLog.setCznr("删除黑名单用户,修改synFlag='DW'");
				 pdLog.setCzr(user.getName());
				 pdLog.setCzrxm(user.getYgxm());
				 pdLog.setCzbm(user.getBmid());
				 pdLog.setCzbmKj(null);
				 pdLog.setCzrq(new Date());
				 pdLog.setSynFlag("UW");
				 pdLog.setTranFlag(null);
				 pdLog.setTranDate(null);
				 pdLog.setCzip(ToolsUtil.getIpAddr(request));
				 this.dbjgHmdyhLogDao.addRepository(pdLog);  				
				log.info("delHmyhByxh|param:xh="+xh);				
				return 1;
			} else {
				return 0;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error(e);
			return 0;
		}
	}
	
	/**
     * 根据xh修过黑名单用户状态
     */
	public Integer editHmyhByxh(DbjgHmdyh  pageHmd,HttpServletRequest request) throws Exception {
		try {
			DbjgHmdyh hmdYh = dbjgHmdyhDao.getRepository(pageHmd.getXh());
			if (hmdYh != null) {
				 User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
				 if(hmdYh.getDqzt().equals("1")){   //锁定----改解锁
					hmdYh.setJsbz(pageHmd.getJsbz());
					hmdYh.setJsyy(pageHmd.getJsyy());
				    hmdYh.setSdyy("");
				    hmdYh.setSdbz("");
					hmdYh.setDqzt("2");
					hmdYh.setSdyxqz(null);
				 }else{                            //解锁-----改锁定
			     	hmdYh.setSdbz(pageHmd.getSdbz());
				    hmdYh.setSdyy(pageHmd.getSdyy());
					hmdYh.setJsbz("");
					hmdYh.setJsyy("");
					hmdYh.setDqzt("1");
					hmdYh.setSdlx(pageHmd.getSdlx());  //锁定的时候才有选择锁定类型
					if(pageHmd.getSdlx().equals("1")){
						 hmdYh.setSdyxqz(pageHmd.getSdyxqz());
					}else{
						 hmdYh.setSdyxqz(null);
					}
				 }                                                                        
				 hmdYh.setSynFlag("UW");
				 hmdYh.setTranDate(null);
				 hmdYh.setTranFlag(null);
				 this.dbjgHmdyhDao.updateRepository(hmdYh);
				 //添加日志
				 DbjgHmdyhLog pdLog=insertHmdYhLog(hmdYh);  
				 pdLog.setCznr("修过黑名单用户状态");
				 pdLog.setCzr(user.getName());
				 pdLog.setCzrxm(user.getYgxm());
				 pdLog.setCzbm(user.getBmid());
				 pdLog.setCzbmKj(null);
				 pdLog.setCzrq(new Date());
				 pdLog.setSynFlag("UW");
				 pdLog.setTranFlag(null);
				 pdLog.setTranDate(null);
				 pdLog.setCzip(ToolsUtil.getIpAddr(request));
				 this.dbjgHmdyhLogDao.addRepository(pdLog);  				
				log.info("delHmyhByxh|param:xh="+hmdYh);				
				return 1;
			} else {
				return 0;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error(e);
			return 0;
		}
	}
	/**
	 * 修改字典
	 * @param pageHmd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Integer editDbjgSjzdByxh(DbjgSjzd dict,HttpServletRequest request) throws Exception {
		try {
			DbjgSjzd sjzd = this.dbjgSjzdDao.getRepository(dict.getXh());
			if (sjzd != null) {
				 User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
				 sjzd.setDmz(dict.getDmz());		
				 this.dbjgSjzdDao.updateRepository(sjzd);
				 //添加日志
				 DbjgSjzdLog pdLog=insertDictLog(sjzd);  				 
				 pdLog.setCznr("修改未备案代办员可代办数");
				 pdLog.setCzr(user.getName());
				 pdLog.setCzrxm(user.getYgxm());
				 pdLog.setCzbm(user.getBmid());
				 pdLog.setCzbmKj(null);
				 pdLog.setCzrq(new Date());
				 pdLog.setCzip(ToolsUtil.getIpAddr(request));
				 this.dbjgSjzdLogDao.addRepository(pdLog);
				
				log.info("editDbjgSjzdByxh|param:dict="+dict);				
				return 1;
			} else {
				return 0;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error(e);
			return 0;
		}
	}
	/**
	 * 修改锁定或解锁原因的代码说明
	 * @param xh
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Integer editDbjgSdbyxh(String xh,HttpServletRequest request) throws Exception {
		try {
			DbjgSjzd sjzd = this.dbjgSjzdDao.getRepository(xh);
			if (sjzd != null) {
				 User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
				 sjzd.setDmms1(request.getParameter("sd_dmms1"));
				 this.dbjgSjzdDao.updateRepository(sjzd);
				 //添加日志
				 DbjgSjzdLog pdLog=insertDictLog(sjzd);  				 
				 pdLog.setCznr("修改锁定/解锁的代码说明");
				 pdLog.setCzr(user.getName());
				 pdLog.setCzrxm(user.getYgxm());
				 pdLog.setCzbm(user.getBmid());
				 pdLog.setCzbmKj(null);
				 pdLog.setCzrq(new Date());
				 pdLog.setCzip(ToolsUtil.getIpAddr(request));
				 this.dbjgSjzdLogDao.addRepository(pdLog);
				
				log.info("editDbjgSdbyxh|param:dict="+xh);				
				return 1;
			} else {
				return 0;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error(e);
			return 0;
		}
	}
	
	
	/**
	 * 根据xh 获取 黑名单用户
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public DbjgHmdyh getHmdyhByxh(HttpServletRequest request) throws Exception {
		String xh = request.getParameter("xh");
		DbjgHmdyh hm= this.dbjgHmdyhDao.getRepository(xh);
		return hm;
	}
	
	/**
	 * 查询黑名单锁定,解锁原因,未备案代办员一天,未备案代办员一月列表字典
	 */
	@SuppressWarnings("unchecked")
	public List<DbjgSjzd> getHmdYyDict(String type,boolean flag) throws Exception{
		if("1".equals(type)){       //锁定
			type="'SDYY'";
			if(!flag){
				type+=" and (dmms2 <>'A' or dmms2 is null)";
			}
		}else if("2".equals(type)){ //解锁
			type="'JSYY'";   
			if(!flag){
				type+=" and (dmms2 <>'A' or dmms2 is null)";
			}
		}else if("day".equals(type)){
			type="'DBCS_T'";          //未备案代办员一天
		}else{
			type="'DBCS_Y'";          //未备案代办员一月
		}
		StringBuffer hql = new StringBuffer("from DbjgSjzd as ws where ws.dmlb ="+type+"");
		List<DbjgSjzd> wscgsSjzdList = new ArrayList<DbjgSjzd>();
		wscgsSjzdList = this.dbjgSjzdDao.getRepositories(hql.toString());
		return wscgsSjzdList;
	}
	/**
	 * 获取未备案代办员日，月代办字典
	 * @return
	 * @throws Exception
	 */
	public List<DbjgSjzd> getHmdDbDict(String type) throws Exception {
		String code="";
		if(type.equals("db")){
			code ="in ('DBCS_T','DBCS_Y')";
		}else if(type.endsWith("js")){
			code=" = 'JSYY' and (dmms2 <>'A' or dmms2 is null)";
		}else{
			code=" = 'SDYY' and (dmms2 <>'A' or dmms2 is null)";
		}
		StringBuffer hql = new StringBuffer("from DbjgSjzd as ws where ws.dmlb "+code+" ");
		List<DbjgSjzd> wscgsSjzdList = new ArrayList<DbjgSjzd>();
		wscgsSjzdList = this.dbjgSjzdDao.getRepositories(hql.toString());
		if(wscgsSjzdList!=null && wscgsSjzdList.size()>0){
			for (DbjgSjzd dbjgSjzd : wscgsSjzdList) {
				if(type.equals("db")){
					dbjgSjzd.setDmlb("未备案代办员可代办数");
				}else if(type.endsWith("js")){
					dbjgSjzd.setDmlb("解锁原因");
				}else{
					dbjgSjzd.setDmlb("锁定原因");
				}				
			}
		}
		return wscgsSjzdList;
	}
	
	/**
	 * 生成黑名单解锁/锁定的代码值
	 * @param type ：1表示锁定;2表示解锁
	 * @return
	 * @throws Exception
	 */
	public String getDictCode(String type) throws Exception {
		String code = "";
		String maxNoSql = "select dmz from (select dmz from dbjg_sjzd where dmlb='"+type.toUpperCase()+"' order by dmz desc) where rownum =1";
		String maxNo = this.dbjgSjzdDao.findSQL(maxNoSql).get(0).toString();
		maxNo=maxNo.substring(1); //取出首字母
		if(maxNo.length() == 1){
			maxNo = "00"+(Integer.parseInt(maxNo)+1);
		}else if(maxNo.length() == 2){
			maxNo ="0"+(Integer.parseInt(maxNo)+1);
		}else{
			maxNo=(Integer.parseInt(maxNo)+1)+"";
		}
		if(type.equals("sdyy")){//sdyy表示锁定 
			code = "A"+maxNo;
		}else{    
			code = "B"+maxNo;
		}
		return code;
	}
	/**
	 * 生成一年后的有效期止
	 * @return
	 * @throws Exception
	 */
	public Date getAutoYxqz() throws Exception {
		String autoStr ="select  to_char(add_months(sysdate,12),'yyyy-MM-dd') from dual";
		String val = this.dbjgSjzdDao.findSQL(autoStr).get(0).toString();
		Date date=StringUtil.str2Date(val, "yyyy-MM-dd");
		return date;
	}
	
	/**
	 * 查询黑名单字典(返回Map格式)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getTypeDict(String type) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		if("1".equals(type)){       //锁定
			type="SDYY";
		}else if("2".equals(type)){ //解锁
			type="JSYY";      
		}else if("day".equals(type)){
			type="DBCS_T";          //未备案代办员一天
		}else{
			type="DBCS_Y";          //未备案代办员一月
		}
		StringBuffer hql = new StringBuffer("from DbjgSjzd as ws where ws.dmlb = '"+type+"'");
		List<DbjgSjzd> wscgsSjzdList = new ArrayList<DbjgSjzd>();
		wscgsSjzdList = this.dbjgSjzdDao.getRepositories(hql.toString());
		for (int i = 0; i < wscgsSjzdList.size(); i++) {
			map.put(wscgsSjzdList.get(i).getDmz(),wscgsSjzdList.get(i).getDmms1());
		}
		return map;
	}
	/**
	 * 添加字典信息
	 * @param request
	 * @param  
	 * @return
	 * @throws Exception
	 */
	public int addDict(HttpServletRequest request,DbjgSjzd dict) throws Exception {
		int result=0;
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);	
		String type = request.getParameter("type");
		if(type.equals("sdyy")){
			dict.setDmlb("SDYY");
		}else{
			dict.setDmlb("JSYY");
		}		
		dict.setCzr(user.getName());
		dict.setCzrxm(user.getYgxm());
		dict.setCzbm(user.getBmid());
		dict.setCzbmKj(null);
		dict.setCzrq(new Date());
		dict.setCzip(ToolsUtil.getIpAddr(request));
		 this.dbjgSjzdDao.addRepository(dict);
		 
		 DbjgSjzdLog pdLog=insertDictLog(dict);  
		 pdLog.setCznr("新增字典信息");
		 pdLog.setCzr(user.getName());
		 pdLog.setCzrxm(user.getYgxm());
		 pdLog.setCzbm(user.getBmid());
		 pdLog.setCzbmKj(null);
		 pdLog.setCzrq(new Date());
		 pdLog.setCzip(ToolsUtil.getIpAddr(request));
		 this.dbjgSjzdLogDao.addRepository(pdLog);  	
		 result=1;
		return result;
	}
	/**
     * 根据xh删除字典信息
     */
	public Integer delDictByxh(String xh,HttpServletRequest request) throws Exception {
		try {
			DbjgSjzd sjzd = this.dbjgSjzdDao.getRepository(xh);
			if (sjzd != null) {
				User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
				 this.dbjgSjzdDao.deleteRepository(sjzd);
				 //添加日志
				 DbjgSjzdLog pdLog=this.insertDictLog(sjzd);  
				 pdLog.setCznr("删除黑名单用户");
				 pdLog.setCzr(user.getName());
				 pdLog.setCzrxm(user.getYgxm());
				 pdLog.setCzbm(user.getBmid());
				 pdLog.setCzbmKj(null);
				 pdLog.setCzrq(new Date());
				 pdLog.setCzip(ToolsUtil.getIpAddr(request));
				 this.dbjgSjzdLogDao.addRepository(pdLog);  				
				log.info("delDictByxh|param:xh="+xh);				
				return 1;
			} else {
				return 0;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error(e);
			return 0;
		}
	}
	
	
	/**
	 * 记录操作日志
	 * @param WtydnsYwsbspb
	 */
	public DbjgHmdyhLog insertHmdYhLog(DbjgHmdyh hm) throws Exception{
		DbjgHmdyhLog hmLog = new DbjgHmdyhLog();
		try {
			Field[] fields = hm.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				Field field = hmLog.getClass().getDeclaredField(name);
				field.set(hmLog, fields[i].get(hm));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return hmLog;
	}
	public DbjgSjzdLog insertDictLog(DbjgSjzd hm) throws Exception{
		DbjgSjzdLog hmLog = new DbjgSjzdLog();
		try {
			Field[] fields = hm.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				Field field = hmLog.getClass().getDeclaredField(name);
				field.set(hmLog, fields[i].get(hm));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return hmLog;
	}
	public DbjgHmdyhDao getDbjgHmdyhDao() {
		return dbjgHmdyhDao;
	}

	public DbjgSjzdDao getDbjgSjzdDao() {
		return dbjgSjzdDao;
	}

	public void setDbjgHmdyhDao(DbjgHmdyhDao dbjgHmdyhDao) {
		this.dbjgHmdyhDao = dbjgHmdyhDao;
	}

	public void setDbjgSjzdDao(DbjgSjzdDao dbjgSjzdDao) {
		this.dbjgSjzdDao = dbjgSjzdDao;
	}

	public DbjgHmdyhLogDao getDbjgHmdyhLogDao() {
		return dbjgHmdyhLogDao;
	}

	public DbjgSjzdLogDaoImpl getDbjgSjzdLogDao() {
		return dbjgSjzdLogDao;
	}

	public void setDbjgHmdyhLogDao(DbjgHmdyhLogDao dbjgHmdyhLogDao) {
		this.dbjgHmdyhLogDao = dbjgHmdyhLogDao;
	}

	public void setDbjgSjzdLogDao(DbjgSjzdLogDaoImpl dbjgSjzdLogDao) {
		this.dbjgSjzdLogDao = dbjgSjzdLogDao;
	}

}
