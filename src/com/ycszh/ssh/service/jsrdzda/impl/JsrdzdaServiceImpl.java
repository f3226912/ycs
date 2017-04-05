package com.ycszh.ssh.service.jsrdzda.impl;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDpublishDao;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszDaxxb;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszDaxxbLog;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszDaxxbPhoto;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszDaxxbPhotoLog;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszDaxxbSh;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszDaxxbShLog;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszSjzd;
import com.ycszh.ssh.hbm.jsrdzda.EsDrvLicense;
import com.ycszh.ssh.service.jsrdzda.JsrdzdaService;

public class JsrdzdaServiceImpl implements JsrdzdaService {
	private DefaultDpublishDao defaultDpublishDao;
	private SessionFactory sessionFactory;
	private final static Logger log = Logger.getLogger(JsrdzdaServiceImpl.class);
	
	/**
	 * 解锁（且清空）当前用户下的锁定记录
	 * @param request
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void updateLockByCurrentUser(HttpServletRequest request ,String temp ) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String str="";
		if(temp.equals("recheck")){//复核
			str =" and cjzt = '1' ";
		}else{    //初审
			str=" and cjzt='0' ";
		}
		String hqls = " from DzdaJszDaxxb where 1=1 and isLock='1' and lockr='"+user.getName()+"'"+str +" order by cjsj desc";
		List objList = this.defaultDpublishDao.getRepositories(hqls);
		if(objList!=null && objList.size() > 0) {
			List<DzdaJszDaxxb> islockList =( List<DzdaJszDaxxb> )objList;
			for (DzdaJszDaxxb da : islockList) {
				 da.setIsLock("0");  //解除锁定
				 da.setLockr(null); 
				 da.setLockxm(null);
				 da.setLockbm(null);
				 da.setLockIptime(null);
				 da.setLockIp(null);
				 da.setLockBM_kj(null);
				 DzdaJszDaxxb xxb = (DzdaJszDaxxb)this.defaultDpublishDao.updateRepository(da);
				 DzdaJszDaxxbLog upBaLog = new DzdaJszDaxxbLog();
			     upBaLog.setCznr("解除锁定记录");		
				 upBaLog.setCzip(this.getIp(request));
				 upBaLog.setCzsj(new Date());
				 upBaLog.setCzrxm(user.getName());
				 upBaLog.setCzr(user.getYgxm());
				 upBaLog.setCzrbm(user.getBmid());
				 List<String> outList=new ArrayList<String>();
				 outList.add("synFlag");
				 outList.add("tranFlag");
				 outList.add("tranDate");
				 outList.add("photoLx");
				 this.defaultDpublishDao.addRepositoryLog(upBaLog, xxb, outList);
			}
		}		
	}
	
	
	/***
	 * 锁定15条记录
	 */
	public void lockRecoredByUser(HttpServletRequest request,String temp ) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String str ="";
		if(temp.equals("first")){
			str=" and cjzt='0' ";
		}else{
			str=" and cjzt='1' ";
		}
		String sql = "select *  from ( select *  from dzda_jsz_daxxb t  where lockr is null and islock ='0' "+str+"  order by cjsj desc ) where rownum <= 15";
		List objList = this.defaultDpublishDao.findSQL(sql, DzdaJszDaxxb.class);
		if(objList!=null && objList.size() > 0) {
			List<DzdaJszDaxxb> islockList =( List<DzdaJszDaxxb> )objList;
			for (DzdaJszDaxxb da : islockList) {
				 da.setIsLock("1");  //解除锁定
				 da.setLockr(user.getName());
				 da.setLockxm(user.getYgxm());				 
				 da.setLockIptime(new Date());
				 da.setLockIp(this.getIp(request));
				 da.setLockBM_kj(user.getBmmc());
				 da.setLockbm(user.getBmid());
				 System.out.println(da.getLockbm());
				 DzdaJszDaxxb xxb = (DzdaJszDaxxb)this.defaultDpublishDao.updateRepository(da);
				 DzdaJszDaxxbLog upBaLog = new DzdaJszDaxxbLog();
			     upBaLog.setCznr("锁定记录");		
				 upBaLog.setCzip(this.getIp(request));
				 upBaLog.setCzsj(new Date());
				 upBaLog.setCzrxm(user.getName());
				 upBaLog.setCzr(user.getYgxm());
				 upBaLog.setCzrbm(user.getBmid());
				 List<String> outList=new ArrayList<String>();
				 outList.add("synFlag");
				 outList.add("tranFlag");
				 outList.add("tranDate");
				 outList.add("photoLx");
				 this.defaultDpublishDao.addRepositoryLog(upBaLog, xxb, outList);
			}
		}
	}
	
	/**
	 * 获取取待驾校初审和车管退办的采集数据	
	 */
	@SuppressWarnings({ "unchecked"})
	public List<DzdaJszDaxxb> getJsrUserList(HttpServletRequest request,String temp ) throws Exception {	
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String str="";
		if(temp.equals("add")){//新增锁定,或页面查询时同一个查询
			str=" ,'0'";
		}
		StringBuffer sql =new StringBuffer(" from DzdaJszDaxxb t where 1=1");		
		String btnType = request.getParameter("btnType");
		if(btnType!=null && !btnType.equals("")){
			if(btnType.equals("dateConType")){//已时间日期为条件
				sql.append(" and cjzt in ('CT','JT'"+str+") and lockr='"+user.getName()+"'");
				String s_date = request.getParameter("s_date");
				String e_date = request.getParameter("e_date");			
		 		if(s_date!=null && !"".equals(s_date)){
		 			sql.append(" and t.cjsj  >= to_date('"+s_date+" 00:00:00','yyyy-MM-dd hh24:mi:ss') ");	
		 			request.setAttribute("s_date", s_date);
		 		}
		 		if(e_date!=null && !"".equals(e_date)){
		 			sql.append(" and t.cjsj <= to_date('"+e_date+" 23:59:59','yyyy-MM-dd hh24:mi:ss') ");	
		 			request.setAttribute("e_date", e_date);
		 		}
			}else if(btnType.equals("sfzConType")){ //身份证号码为条件,全表查询
				String sfzmhm = request.getParameter("sfzmhm");
				if(sfzmhm!=null && !sfzmhm.trim().equals("")){
					sql.append(" and t.sfzmhm = '"+sfzmhm+"'");
					request.setAttribute("sfzmhm",sfzmhm);
					request.setAttribute("btnType", btnType);//针对身份证查询时，需要做是否锁定提示	
				}							
		   }else{
			   sql.append(" and cjzt in ('CT','JT'"+str+") and lockr='"+user.getName()+"'");
		   }
		}else{
			sql.append(" and cjzt in ('CT','JT'"+str+") and lockr='"+user.getName()+"'");
		}
		sql.append(" order by  cjsj desc");
		List<DzdaJszDaxxb> jsrCjList = new ArrayList<DzdaJszDaxxb>();
		List<?> list = defaultDpublishDao.getRepositories(sql.toString());
		jsrCjList = (List<DzdaJszDaxxb>)list;
		if(jsrCjList.size() > 0 ){  //0待驾校初审，1待车管复核，2归档、3档案移交邮政，B补审，CT车管退办，GT归档退办
			Map<String,String> dictMap =getJszSjzdDict("ZT");				
			for (DzdaJszDaxxb dz : jsrCjList) {    
				if(btnType!=null && !btnType.equals("") && btnType.equals("sfzConType") && jsrCjList.size()==1 ){ //身份证查询的条件提示
					DzdaJszDaxxb xxb =(DzdaJszDaxxb)jsrCjList.get(0);
					if(xxb.getCjzt().equals("2")){
					   String hql = " from DzdaJszDaxxbSh where sfzmhm='"+request.getParameter("sfzmhm")+"'";
					   List shList = this.defaultDpublishDao.getRepositories(hql);
					   DzdaJszDaxxbSh sh=(DzdaJszDaxxbSh) shList.get(0);
					   xxb.setTranDate(sh.getGdrq());  //用tranDate封装归档日期
					}
					request.setAttribute("xxb", xxb);				
					request.setAttribute("loginName",user.getName());
			    }
				dz.setCjmac(dz.getCjzt());   //用 cjmac字段 填充cjzt的原值
				dz.setRemark(dz.getIsLock());//用 remark字段 填充islock的原值
				dz.setCjzt(dictMap.get(dz.getCjzt()));
				if(dz.getIsLock()!=null && !dz.getIsLock().equals("")){
					if(dz.getIsLock().equals("0")){
						dz.setIsLock("否");
					}else{
						dz.setIsLock("是");
					}
				}
			}
		}
		request.setAttribute("jsrCjList", jsrCjList);
		log.info("method:getJsrUserList");
		return jsrCjList;
	}	
    
	 
	public void lockRecordByCurrentUsr(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String sfzmhm = request.getParameter("sfzmhm");
		String hql = " from DzdaJszDaxxb where cjzt='0' and  isLock='0' and lockr is null  and sfzmhm='"+sfzmhm+"' order by cjsj desc";
		List objList = this.defaultDpublishDao.getRepositories(hql);
		List<DzdaJszDaxxb> islockList = null;
		if(objList!=null && objList.size() > 0) {			
			islockList =( List<DzdaJszDaxxb> )objList;
			for (DzdaJszDaxxb da : islockList) {
				 da.setIsLock("1");  //解除锁定
				 da.setLockr(user.getName());
				 da.setLockxm(user.getYgxm());				 
				 da.setLockIptime(new Date());
				 da.setLockIp(this.getIp(request));
				 da.setLockBM_kj(user.getBmmc());
				 da.setLockbm(user.getBmid());
				 System.out.println(da.getLockbm());
				 DzdaJszDaxxb xxb = (DzdaJszDaxxb)this.defaultDpublishDao.updateRepository(da);
				 DzdaJszDaxxbLog upBaLog = new DzdaJszDaxxbLog();
			     upBaLog.setCznr("锁定记录");		
				 upBaLog.setCzip(this.getIp(request));
				 upBaLog.setCzsj(new Date());
				 upBaLog.setCzrxm(user.getName());
				 upBaLog.setCzr(user.getYgxm());
				 upBaLog.setCzrbm(user.getBmid());
				 List<String> outList=new ArrayList<String>();
				 outList.add("synFlag");
				 outList.add("tranFlag");
				 outList.add("tranDate");
				 outList.add("photoLx");
				 this.defaultDpublishDao.addRepositoryLog(upBaLog, xxb, outList);
			}
		}
	}
	
	public List<DzdaJszDaxxb> getLockBySfz(HttpServletRequest request) throws Exception {
		String sfzmhm = request.getParameter("sfzmhm");
		String hql = " from DzdaJszDaxxb where sfzmhm='"+sfzmhm+"' order by cjsj desc";
		List jsrCjList = this.defaultDpublishDao.getRepositories(hql);
		List<DzdaJszDaxxb> islockList = null;
		if(jsrCjList!=null && jsrCjList.size() > 0) {	
			islockList =( List<DzdaJszDaxxb> )jsrCjList;
			Map<String,String> dictMap =getJszSjzdDict("ZT");		
			for (DzdaJszDaxxb dz : islockList) {   
				dz.setCjmac(dz.getCjzt());   //用 cjmac字段 填充cjzt的原值
				dz.setRemark(dz.getIsLock());//用 remark字段 填充islock的原值
			    dz.setCjzt(dictMap.get(dz.getCjzt()));
				if(dz.getIsLock()!=null && !dz.getIsLock().equals("")){
					if(dz.getIsLock().equals("0")){
						dz.setIsLock("否");
					}else{
						dz.setIsLock("是");
					}
		       }
	       }
		}
		return islockList;
	}
	
	/**
	 * 获得每一个姓名，已锁定数量，待补审的量
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List getStatList(HttpServletRequest request) throws Exception {
		String sql=" select isTable.lockr,isTable.lockrxm, isTable.isLockCount, dTable.noCheckCount " +
				   " from (select lockr, lockrxm, count(0) isLockCount  from dzda_jsz_daxxb " +
				   " where lockr is not null   and islock = '1'   group by lockr, lockrxm ) isTable" +
				   " inner join (select lockr, lockrxm, count(0) noCheckCount  from dzda_jsz_daxxb " +
				   " where lockr is not null   and islock = '1'  and cjzt in ('CT', 'JT')   group by lockr, " +
				   " lockrxm) dTable on isTable.lockr =  dTable.lockr ";	
	    List list = this.defaultDpublishDao.findSQL(sql);	    
	    
	    //---待审总数，已锁定总数，待锁总数，待补审总数 
	    String statSql="select " +
	    " (select count(0) from dzda_jsz_daxxb where cjzt='0') dsh," +
	    " (select count(0) from dzda_jsz_daxxb where islock='1') islock," +
	    " (select count(0) from dzda_jsz_daxxb where islock='0') dlock," +
	    " (select count(0) from dzda_jsz_daxxb where cjzt in ('CT','JT')) dBush  from dual";
	    List statList = this.defaultDpublishDao.findSQL(statSql);
	    if(statList!=null && statList.size()>0){
	    	Object[] titles =(Object[]) statList.get(0);
	    	request.setAttribute("showFlag", "show");
	    	request.setAttribute("titles", titles);
	    }
		return list;
	}	
	
	/**
	 * 初审温馨提示列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<DzdaJszDaxxb>  lockByCurrentUser(HttpServletRequest request ) throws Exception {
		String  sql  ="select * from ( select * from dzda_jsz_daxxb t  where lockr is null  and islock = 0 order by cjsj desc) where rownum <= 15 ";
		List<DzdaJszDaxxb> list = this.defaultDpublishDao.findSQL(sql, DzdaJszDaxxb.class);
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		if(list!=null && list.size() > 0) {
			List<DzdaJszDaxxb> islockList =( List<DzdaJszDaxxb> )list;
			for (DzdaJszDaxxb da : islockList) {
				 da.setIsLock("1");  //锁定
				 da.setLockxm(user.getName());
				 da.setLockbm(user.getBmid());
				 da.setLockIptime(null);
				 da.setLockIp(this.getIp(request));
				 da.setLockBM_kj("");
				 DzdaJszDaxxb xxb = (DzdaJszDaxxb)this.defaultDpublishDao.updateRepository(da);
				 DzdaJszDaxxbLog upBaLog = new DzdaJszDaxxbLog();
			     upBaLog.setCznr("锁定记录");		
				 upBaLog.setCzip(this.getIp(request));
				 upBaLog.setCzsj(new Date());
				 upBaLog.setCzrxm(user.getName());
				 upBaLog.setCzr(user.getYgxm());
				 upBaLog.setCzrbm(user.getBmid());
				 List<String> outList=new ArrayList<String>();
				 outList.add("synFlag");
				 outList.add("tranFlag");
				 outList.add("tranDate");
				 outList.add("photoLx");
				 this.defaultDpublishDao.addRepositoryLog(upBaLog, xxb, outList);
			}
		}	
		return list;
	}
	
	/**
	 * 根据采集序号获取驾驶人信息
	 * @param cjxh
	 * @return
	 * @throws Exception
	 */
	public DzdaJszDaxxb getJsrCjInfo(String cjxh) throws Exception {
	  if(null != cjxh && !"".equals(cjxh)){
		log.info("method:getJsrCjInfo|param:cjxh="+cjxh);
		DzdaJszDaxxb da = (DzdaJszDaxxb)defaultDpublishDao.getRepository(cjxh,DzdaJszDaxxb.class);
		Map<String,String> ywlxMap = getJszSjzdDict("YWLX");
		da.setYwyy(ywlxMap.get(da.getYwlx()));//用ywyy封装ywlx翻译值
		return da;
	  }
	  return null;
    }
	/**
	 * 根据采集序号获取驾驶人信息count(*)
	 * @param cjxh
	 * @return
	 * @throws Exception
	 */
	public int getJsrCjInfoCount(String cjxh) throws Exception {
	  if(null != cjxh && !"".equals(cjxh)){
		String hql ="select count(*) from DzdaJszDaxxb where cjxh='"+cjxh+"'";
		List list = defaultDpublishDao.getRepositories(hql);
		return Integer.parseInt(list.get(0).toString());
	  }
	  return 0;
    }
	/**
	 * 根据采集序号和资料类型获取图片列表中对应的序号
	 * @param request
	 * @return
	 */
	public Integer getPhotoXhByInfo(HttpServletRequest request) throws  Exception {
		String cjxh = request.getParameter("cjxh");
		String zllx = request.getParameter("zllx");
		String hql = "from DzdaJszDaxxbPhoto where cjxh='"+cjxh+"' and zllx='"+zllx+"'";
		List<?> list = this.defaultDpublishDao.getRepositories(hql);
		if(list!=null && list.size()>0){
			DzdaJszDaxxbPhoto to = (DzdaJszDaxxbPhoto)list.get(0);
			return Integer.parseInt(to.getXh());
		}
		return 0;
	}
	/**
	 * 根据cjxh查询某一驾驶人的档案照片
	 * @param cjxh
	 * @return
	 * @throws Exception
	 */
	public List<DzdaJszDaxxbPhoto> getPhotoListByCjxh(String cjxh,String ywlx) throws Exception {
    	List<DzdaJszDaxxbPhoto> photoList=new ArrayList<DzdaJszDaxxbPhoto>();
    	//根据ywlx 及查对应采集资料信息
    	 String sql =" select c.cjxh, c.zllx, to_number(d.dmms2) px, c.xh, c.zt, c.tbyy, c.remark, c.pic_str, d.dmz,d.dmms1 from  (" +
    	 		     " select p.cjxh, p.zllx, p.xh, p.zt, p.tbyy, p.remark, p.pic_str from dzda_jsz_daxxb cj inner join dzda_jsz_daxxb_photo p on cj.cjxh = p.cjxh  where cj.cjxh = '"+cjxh+"'" +
    	 		     "  and p.zllx is not null) c "+
    	             " right join dzda_jsz_sjzd d on c.zllx = d.dmz  where d.dmlb = 'YWLX_'||'"+ywlx+"' order by to_number(d.dmms2) asc ";
    	 List<?> list = this.defaultDpublishDao.findSQL(sql);
    	 if(list!=null && list.size()>0){
    		 Map<String,String> tbyys = this.getJszSjzdDict("TBYY_PHOTO");  
    		 Map<String,String> zllxs = this.getJszSjzdDict("YWLX_A");    	
    		 for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[])list.get(i);
				DzdaJszDaxxbPhoto po= new DzdaJszDaxxbPhoto();
				if(obj[0]!=null){
					po.setCjxh(obj[0].toString());
				}				
				if(obj[1]!=null){
					po.setZllx(obj[1].toString());
					po.setZllxMc(zllxs.get(po.getZllx()));
				}				
				if(obj[2]!=null){
					po.setOutIn(obj[2].toString());
				}
				if(obj[3]!=null){
					po.setXh(obj[3].toString());
					if(!po.getXh().equals("")){
						 List<String> ids =new ArrayList<String>();
						 //获取历次审核失败的图片id
				    	 String sql2 ="select id from (select id from dzda_jsz_daxxb_photo_log  where  xh='"+po.getXh()+"' and zt in ('JT','CT')  order by czsj desc ) where rownum <=3 order by id asc";
				    	 List<?> xhIds = this.defaultDpublishDao.findSQL(sql2);
				    	 if(xhIds!=null && xhIds.size()>0){
				    		 for (int j = 0; j < xhIds.size(); j++) {
				    			 String idObj = (String)xhIds.get(j);			    			 
				    		     ids.add(idObj);
				    		 }
				    		 po.setHistoryId(ids);//以往多少次补录记录
				    	 }
					}
				}			

				if(obj[4]!=null){
					po.setZt(obj[4].toString());	
				}							
				if(obj[5]!=null){
					String[] tyyArray =obj[5].toString().trim().split(","); //分割多个原因
					StringBuffer str =new StringBuffer();
					for (int j = 0; j < tyyArray.length; j++) {
						str.append(tbyys.get(tyyArray[j].toString())+",");
					}
				    String remark = "";
					if(obj[6]!=null ){
						remark=":"+obj[6].toString();
						po.setRemark(obj[6].toString());
					}
					po.setTbyy(str.toString()+remark);
					po.setCjmac(obj[5].toString()); //用cjMac包装员代码值
				}
				if(obj[7]!=null){
				   po.setPicStr(obj[7].toString());	
				}		
				if(obj[8]!=null){
					 po.setCjr(obj[8].toString());//用cjr替代未上传的资料类型
				}
				if(obj[9]!=null){
					 po.setZflx(obj[9].toString());
				}
				photoList.add(po);
			}
    	 }
    	return photoList;
    }	

	/**
	 * 根据采集序号图片列表
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DzdaJszDaxxbPhoto> getPhotosByxh(String cjxh) throws  Exception {
		String hql = "from DzdaJszDaxxbPhoto where cjxh='"+cjxh+"'";
		List<?> list = this.defaultDpublishDao.getRepositories(hql);
		List<DzdaJszDaxxbPhoto> allPto  =new ArrayList<DzdaJszDaxxbPhoto>();
		if(list!=null && list.size()>0){
			allPto = (List<DzdaJszDaxxbPhoto> )list;
		}		 
		return allPto;
	}
	
	
	/**
	 * 驾校初审/车管复核
	 * 更新 DZDA_JSZ_DAXXB表、
       DZDA_JSZ_DAXXB_PHOTO 表及日志表 含图片信息更改
       DZDA_JSZ_DAXXB_SH 表 的CJZT=1  退办要更新tbyy字段、更新7项审核人信息及日志表
	 * @param daxxb
	 * @param photo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public  DzdaJszSjzd jsrDaFirstCheck(DzdaJszDaxxb daxxb,List<DzdaJszDaxxbPhoto> photos,HttpServletRequest request,String type) throws Exception {
		DzdaJszSjzd result = new DzdaJszSjzd();
		List<DzdaJszDaxxbPhotoLog> photoList =new ArrayList<DzdaJszDaxxbPhotoLog>();
		User sessionU = (User) request.getSession().getAttribute(SysConst.USERBEAN);	
		int resultInt=0;//判断有多少通过量
		String isAllYY = request.getParameter("isAllYY");
		String isAllRemark = request.getParameter("isAllRemark");//其他退办原因--对应的备注信息
		Map<String,String> dictMap =getJszSjzdDict("TBYY_PHOTO");
		/***
		 * 更新photo表的图片和日志信息
		 */
		for(DzdaJszDaxxbPhoto ph : photos) {
			DzdaJszDaxxbPhoto newPhoto=(DzdaJszDaxxbPhoto)this.defaultDpublishDao.getRepository(ph.getXh(),DzdaJszDaxxbPhoto.class);
			newPhoto.setCjmac("");//在上一步查询中，有装入退办原因的值，现在要清空。
			if(isAllYY!=null && !isAllYY.equals("")){ //如果全部不合格,则只修改DZDA_JSZ_DAXXB_PHOTO图片表的状态和退办原因
				  String zllx = request.getParameter("zllx"+newPhoto.getXh());//获取资料类型
				  newPhoto.setZllx(zllx);
				  if(type.equals("cg")){//车管退办
					  newPhoto.setZt("CT");
				  }else{
					  newPhoto.setZt("JT");
				  }				 
				  newPhoto.setTbyy(isAllYY);
				  newPhoto.setRemark(isAllRemark);	
//				  //多个原因,进行分割，现已放弃使用
//				  String[] dicts = isAllYY.split(",");
//				  String  buff="";
//				  for (String str : dicts) {
//					  buff+=dictMap.get(str)+",";
//				  }
//				  if(isAllRemark!=null && isAllRemark.equals("")){
//					  buff+=":"+isAllRemark;
//				  }else{
//					  buff=buff.substring(0, buff.length()-1);
//				  }
//				  //全部退办，都必须修改DzdaJszDaxxbPhoto表中图片，并在图片上打印退办原因字样  
//			      createBazPic(buff, ph.getXh(),"dzda_jsz_daxxb_photo");//dzda_jsz_daxxb_photo表
			}else{				
				String zllx = request.getParameter("zllx"+newPhoto.getXh());//获取资料类型
				newPhoto.setZllx(zllx);
				String photoYY=  request.getParameter("photoYY"+newPhoto.getXh());//获取某张图片的退办原因
				String phRemark = request.getParameter("phRemark"+newPhoto.getXh());//获取某张其他退办原因--对应的备注信息
				if(photoYY!=null && !photoYY.equals("")){
					if(type.equals("cg")){//车管退办
						newPhoto.setZt("CT");
					}else{
						newPhoto.setZt("JT");
					}				   
					newPhoto.setTbyy(photoYY);
					newPhoto.setRemark(phRemark);
//				    //多个原因,进行分割--------
//				    String[] dicts = newPhoto.getTbyy().split(",");
//				    String  buff="";
//				    for (String str : dicts) {
//					   buff+=dictMap.get(str)+",";
//				    }
//				    if(phRemark!=null && phRemark.equals("")){
//					    buff+=":"+phRemark;
//				    }else{
//					    buff=buff.substring(0, buff.length()-1);
//				    }
//					//有退办原因的，必须修改DzdaJszDaxxbPhoto表中图片，并在图片上打印退办原因字样  
//				    createBazPic(buff, ph.getXh(),"dzda_jsz_daxxb_photo");//dzda_jsz_daxxb_photo表				   
				}else{
					if(type.equals("cg")){
						newPhoto.setZt("2"); 
					}else{
						newPhoto.setZt("1");  //没有退办原因，则审核通过 ,不打水印
					}				   
					newPhoto.setTbyy("");
					newPhoto.setRemark("");
				    resultInt++;//判断有多少通过量
				}				
			}			
		   DzdaJszDaxxbPhoto backPhoto=(DzdaJszDaxxbPhoto) this.defaultDpublishDao.updateRepository(newPhoto);
		   DzdaJszDaxxbPhotoLog photoLog = new DzdaJszDaxxbPhotoLog();
		   if(type.equals("cg")){
			   photoLog.setCznr("新增--车管复审驾驶人档案图片信息");
		   }else{
			   photoLog.setCznr("新增--驾校初审驾驶人档案图片信息");
		   }		  
		   photoLog.setCzip(this.getIp(request));
		   photoLog.setCzsj(new Date());
		   photoLog.setCzrxm(sessionU.getName());
		   photoLog.setCzr(sessionU.getYgxm());
		   photoLog.setCzrbm(sessionU.getBmid());
		   List<String> listLog =new ArrayList<String>();
		   listLog.add("zllxMc");
	       listLog.add("historyId");
		   DzdaJszDaxxbPhotoLog backLog =(DzdaJszDaxxbPhotoLog) this.defaultDpublishDao.addRepositoryLog(photoLog, backPhoto, listLog);
		   //photoList.add(backLog);
	    }
		/******************更新主表和审核主表***********************/
		DzdaJszDaxxb busObj=(DzdaJszDaxxb)this.defaultDpublishDao.getRepository(daxxb.getCjxh(),DzdaJszDaxxb.class);
		if(isAllYY!=null && !isAllYY.equals("")){
			if(type.equals("cg")){
				busObj.setCjzt("CT");   //车管退办
			}else{
				busObj.setCjzt("JT");   //驾校退办
			}			
			busObj.setTbyy(isAllYY);
			busObj.setRemark(isAllRemark);
		}else{
			if(resultInt==photos.size()){//通过量等于图片总数，表示全部审核通过
				if(type.equals("cg")){
					busObj.setCjzt("2");   //车管全部通过就归档
					String yzbm = this.getUserCurrentNumMaxStr(sessionU.getName());//生成邮政编码
					busObj.setYzbm(yzbm);
					result.setDmms1(yzbm);
				}else{
					busObj.setCjzt("1");   //全部通过 
				}				
				busObj.setTbyy("");
				busObj.setRemark("");
			}else{
				if(type.equals("cg")){
					busObj.setCjzt("CT");   //车管退办
				}else{
					busObj.setCjzt("JT");   //驾校退办
				}			
			}
		}	
		busObj.setYwlx(daxxb.getYwlx());
		busObj.setLxdh(daxxb.getLxdh());
		busObj.setLxdz(daxxb.getLxdz());
		busObj.setXm(daxxb.getXm());
		busObj.setDabh(daxxb.getDabh());
		busObj.setZjcx(daxxb.getZjcx());
		busObj.setSfzmhm(daxxb.getSfzmhm());
		busObj.setSfzmmc(daxxb.getSfzmmc());
		
		DzdaJszDaxxb upBa=(DzdaJszDaxxb)this.defaultDpublishDao.updateRepository(busObj);

		DzdaJszDaxxbLog upBaLog = new DzdaJszDaxxbLog();
		if(type.equals("cg")){
			upBaLog.setCznr("修改--车管复审驾驶人档案信息");
		}else{
			upBaLog.setCznr("修改--驾校初审驾驶人档案信息");
		}		
		upBaLog.setCzip(this.getIp(request));
		upBaLog.setCzsj(new Date());
		upBaLog.setCzrxm(sessionU.getName());
		upBaLog.setCzr(sessionU.getYgxm());
		upBaLog.setCzrbm(sessionU.getBmid());
		List<String> list =new ArrayList<String>();
		list.add("synFlag");
		list.add("tranFlag");
		list.add("tranDate");
		list.add("photoLx");
		this.defaultDpublishDao.addRepositoryLog(upBaLog, upBa, list);
		log.info("method:jsrDaFirstCheck|param:user="+sessionU);	
		
		//更新档案审核表，插入新的日志表内容
		DzdaJszDaxxbSh daSh =(DzdaJszDaxxbSh)this.defaultDpublishDao.getRepository(daxxb.getCjxh(), DzdaJszDaxxbSh.class);
		if(daSh!=null){
			daSh.setShr(sessionU.getName());
			daSh.setShrxm(sessionU.getYgxm());
			daSh.setShbm(sessionU.getBmid());
			daSh.setShrq(new Date());
			daSh.setShip(this.getIp(request));
			 //状态为2时，填充归档信息
		    if(upBa.getCjzt().equals("2")){
		    	daSh.setGdrxm(sessionU.getYgxm());
		    	daSh.setGdbm(sessionU.getBmid());
		    	daSh.setGdbmKj(sessionU.getBmmc());
		    	daSh.setGdrq(new Date());
		    	daSh.setGdip(this.getIp(request));		    	
		    }
			this.defaultDpublishDao.updateRepository(daSh);
			DzdaJszDaxxbShLog shLog = new DzdaJszDaxxbShLog();
			if(type.equals("cg")){
				shLog.setCznr("新增--车管复核驾驶人档案图片信息");
			}else{
				shLog.setCznr("新增--驾校初审审核驾驶人档案图片信息");
			}			
			shLog.setCzip(this.getIp(request));
			shLog.setCzsj(new Date());
			shLog.setCzrxm(sessionU.getName());
			shLog.setCzr(sessionU.getYgxm());
		    shLog.setCzrbm(sessionU.getBmid());
		  
		    this.defaultDpublishDao.addRepositoryLog(shLog, daSh, null);
		    resultInt++;
		}		
		result.setDmz(resultInt+"");
		return result;
	}		    
	
	//生成当前用户工作量的最大的邮政编码规则： 用户编号+年月日+序号（序号根据当前锁定人员的工作量+1，增量）
	@SuppressWarnings("unchecked")
	public String getUserCurrentNumMaxStr(String usercode) throws Exception{
		StringBuffer buffStr = new StringBuffer();
		if(usercode!=null && !usercode.equals("")){
			buffStr.append(usercode+"_");    //1
			String dateStr ="select to_char(sysdate,'yyyyMMdd') from dual";			
			List dateLs = this.defaultDpublishDao.findSQL(dateStr);
			if(dateLs!=null && dateLs.size()>0){
				buffStr.append(dateLs.get(0).toString());//2
				String work = "select count(0) from dzda_jsz_daxxb where lockr='"+usercode+"' and cjzt='2' and islock='1' and  trunc(lockiptime)=trunc(sysdate)";
				List workLs = this.defaultDpublishDao.findSQL(work);
				String num = "0";
				if(Integer.parseInt(workLs.get(0).toString())> 0){					
					if(workLs.get(0).toString().length()==1){
						num = "00"+Integer.parseInt(workLs.get(0).toString())+1;
					}else if(workLs.get(0).toString().length()==2){
						num = "0"+Integer.parseInt(workLs.get(0).toString())+1;
					}else{
						num = ""+Integer.parseInt(workLs.get(0).toString())+1;
					}					
				}else{
					num = "001";
				}
				return buffStr.append("_"+num).toString(); //3
			}
		}
		return null;		
	}
	
	/**
	 * 根据序号获取图片信息
	 * @param request
	 * @return
	 */
	public DzdaJszDaxxbPhoto getPhotoInfoByxh(HttpServletRequest request) throws  Exception {
		String xh = request.getParameter("xh");
		String hql = "from DzdaJszDaxxbPhoto where xh='"+xh+"'";
		List<?> list = this.defaultDpublishDao.getRepositories(hql);
		if(list!=null && list.size()>0){
			DzdaJszDaxxbPhoto pho = (DzdaJszDaxxbPhoto)list.get(0);
			return pho;
		}
		return null;
	}
	
	/**
	 * 驾校补审
                 只能审核上一次审核不通过的图片类型
                 对于上一次审核通过的文件只能查看
      update  DZDA_JSZ_DAXXB cjzt=B  where cjzt='CT'  或者 
      update  DZDA_JSZ_DAXXB cjzt=1  where cjzt='JT'  
      update  DZDA_JSZ_DAXXB_PHOTO zt=1  where  zt='CT' or zt='JT'
	 * @param photo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public  List<DzdaJszDaxxbPhotoLog> jsrDaBuCheck(DzdaJszDaxxb daxxb,List<DzdaJszDaxxbPhoto> photos,HttpServletRequest request) throws Exception {
		List<DzdaJszDaxxbPhotoLog> photoList =new ArrayList<DzdaJszDaxxbPhotoLog>();
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);	
		int resultInt=0;//判断有多少退办量
		Map<String,String> dictMap =getJszSjzdDict("TBYY_PHOTO");
		/***
		 * 更新photo表的图片和日志信息
		 */
		for(DzdaJszDaxxbPhoto ph : photos) {
			DzdaJszDaxxbPhoto oldPhoto=(DzdaJszDaxxbPhoto)this.defaultDpublishDao.getRepository(ph.getXh(),DzdaJszDaxxbPhoto.class);
			//获取资料类型
			String zllx = request.getParameter("zllx"+oldPhoto.getXh());
			oldPhoto.setZllx(zllx);
			//获取某张图片的退办原因
			String photoYY=  request.getParameter("photoYY"+oldPhoto.getXh());
			String phRemark = request.getParameter("phRemark"+oldPhoto.getXh());
			//补审还是不通过，只需要修改退办原因		
			if(photoYY!=null && !photoYY.equals("")){   
				oldPhoto.setTbyy(photoYY);
				oldPhoto.setRemark(phRemark);
			    //多个原因,进行分割
			    String[] dicts = oldPhoto.getTbyy().split(",");
			    String  buff="";
			    for (String str : dicts) {
				   buff+=dictMap.get(str)+",";
			    }
			    
			    if(phRemark!=null && phRemark.equals("")){
				    buff+=":"+phRemark;
			    }else{
				    buff=buff.substring(0, buff.length()-1);
			    }
				//有退办原因的，必须修改DzdaJszDaxxbPhoto表中图片，并在图片上打印退办原因字样  
			   // createBazPic(buff, ph.getXh(),"dzda_jsz_daxxb_photo");//dzda_jsz_daxxb_photo表
			    resultInt++;
			}else{
				oldPhoto.setZt("1");
				oldPhoto.setTbyy("");
				oldPhoto.setRemark("");
			}
		   DzdaJszDaxxbPhoto backPhoto=(DzdaJszDaxxbPhoto) this.defaultDpublishDao.updateRepository(oldPhoto);
		   DzdaJszDaxxbPhotoLog photoLog = new DzdaJszDaxxbPhotoLog();
		   photoLog.setCznr("新增--驾校补审驾驶人档案图片信息");
		   photoLog.setCzip(this.getIp(request));
		   photoLog.setCzsj(new Date());
		   photoLog.setCzrxm(user.getName());
		   photoLog.setCzr(user.getYgxm());
		   photoLog.setCzrbm(user.getBmid());
		   List<String> listLog =new ArrayList<String>();
		   listLog.add("zllxMc");
		   listLog.add("historyId");
		   DzdaJszDaxxbPhotoLog backLog =(DzdaJszDaxxbPhotoLog) this.defaultDpublishDao.addRepositoryLog(photoLog, backPhoto, listLog);
		   photoList.add(backLog);//装入新增的图片日志表
	    }
		/******************更新主表和审核sh主表***********************/
		DzdaJszDaxxb busObj=(DzdaJszDaxxb)this.defaultDpublishDao.getRepository(daxxb.getCjxh(),DzdaJszDaxxb.class);
		if(resultInt ==0){//没有任何退办图片就全部通过，否则不改状态
			if(busObj.getCjzt().equals("CT")){
				busObj.setCjzt("B");   //全部通过 
			}else if(busObj.getCjzt().equals("JT")){
				busObj.setCjzt("1");
				busObj.setTbyy("");
				busObj.setRemark("");
			}				
		}else{
			if(busObj.getCjzt().equals("CT")){
				busObj.setCjzt("JT");
			}
		}
		busObj.setLxdh(daxxb.getLxdh());
		busObj.setLxdz(daxxb.getLxdz());
		busObj.setXm(daxxb.getXm());
		busObj.setDabh(daxxb.getDabh());
		busObj.setZjcx(daxxb.getZjcx());
		busObj.setSfzmhm(daxxb.getSfzmhm());
		busObj.setSfzmmc(daxxb.getSfzmmc());
		
		DzdaJszDaxxb upBa=(DzdaJszDaxxb)this.defaultDpublishDao.updateRepository(busObj);
		DzdaJszDaxxbLog upBaLog = new DzdaJszDaxxbLog();
		upBaLog.setCznr("修改--驾校补审驾驶人档案信息");
		upBaLog.setCzip(this.getIp(request));
		upBaLog.setCzsj(new Date());
		upBaLog.setCzrxm(user.getName());
		upBaLog.setCzr(user.getYgxm());
		upBaLog.setCzrbm(user.getBmid());
		List<String> list =new ArrayList<String>();
		list.add("synFlag");
		list.add("tranFlag");
		list.add("tranDate");
		list.add("photoLx");
		this.defaultDpublishDao.addRepositoryLog(upBaLog, upBa, list);
		log.info("method:jsrDaFirstCheck|param:user="+user);	
		//更新档案审核表，插入新的日志表内容
		DzdaJszDaxxbSh daSh =(DzdaJszDaxxbSh)this.defaultDpublishDao.getRepository(daxxb.getCjxh(), DzdaJszDaxxbSh.class);
		if(daSh!=null){
			daSh.setShr(user.getName());
			daSh.setShrxm(user.getYgxm());
			daSh.setShbm(user.getBmid());
			daSh.setShrq(new Date());
			daSh.setShip(this.getIp(request));
			this.defaultDpublishDao.updateRepository(daSh);
			DzdaJszDaxxbShLog shLog = new DzdaJszDaxxbShLog();
			shLog.setCznr("新增--驾校补审审核驾驶人档案图片信息");
			shLog.setCzip(this.getIp(request));
			shLog.setCzsj(new Date());
			shLog.setCzrxm(user.getName());
			shLog.setCzr(user.getYgxm());
		    shLog.setCzrbm(user.getBmid());
		    this.defaultDpublishDao.addRepositoryLog(shLog, daSh, null);
		}		
		return photoList;
	}		
    	

    /**
	 * 用二进制插入blob字段,修改图片信息
	 */
	public int editeBlobByByte(String xh, byte[] byte_array,String table,String type) throws Exception {
		Connection con = null;
		Statement stc = null;
		ResultSet rs = null;
		int count =0;
			try {
				con =this.sessionFactory.getCurrentSession().connection();
				stc = con.createStatement();
				con.setAutoCommit(false);//取消自动提交
				OutputStream os = null;
				String temp ="";
				// 插入一个空对象empty_blob() 
				if(type.equals("log")){
					temp="id='"+xh+"'";
				}else{
					temp="xh='"+xh+"'";					
				}
				int c=stc.executeUpdate("update "+table+" set photo = empty_blob() where "+temp);
				//锁定数据行进行更新，注意"for update"语句   
				rs = stc.executeQuery("select photo from "+table+" where  "+temp+" for update");
				if(rs.next()){
					// 得到java.sql.Blob对象后强制转换为oracle.sql.BLOB  
					oracle.sql.BLOB blob = (oracle.sql.BLOB)rs.getBlob("photo");
					// 通过getBinaryOutputStream()方法获得向数据库中插入图片的"管道"
					os = blob.getBinaryOutputStream();
					if(null != byte_array){
						for (int i = 0; i < byte_array.length; i++) {
							os.write(byte_array[i]);
						}
					}
					count++;
				}			
				if(byte_array != null){
					byte_array = null;
				}				
				if(os!=null){
					os.flush();
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}finally{
				if(rs != null){
					rs.close();
				}
				if(stc != null){
					stc.close();
				}
				if(con != null){
					con.commit();
					con.setAutoCommit(true);
					con.close();
				}
			}		
		  return count;
	}	
	
	 /**
	 * 根据xh获取图片信息
	 * @param xh
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public byte[] getImageLBlob(String xh,String table) throws Exception {
		Connection conn = null;
		PreparedStatement proc = null;
		ResultSet rs = null;
		oracle.sql.BLOB blob = null;
		try {
			conn = this.sessionFactory.getCurrentSession().connection();
			String sql = "select photo from "+table+" where xh='"+xh+"'";
			proc = conn.prepareStatement(sql);
			rs = proc.executeQuery();
			while(rs.next()){
				blob = (oracle.sql.BLOB) rs.getBlob("photo");
			}
			byte[] byte_array = null;
			if(blob != null){
				int length = (int) blob.length();
				byte_array = blob.getBytes(1, length);
			}
			return byte_array;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (proc != null) {
				proc.close();
				proc = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}		
	}
	 /**
	 * 根据xh获取图片信息
	 * @param xh
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public byte[] getImageLogBlob(String id) throws Exception {
		Connection conn = null;
		PreparedStatement proc = null;
		ResultSet rs = null;
		oracle.sql.BLOB blob = null;
		try {
			conn = this.sessionFactory.getCurrentSession().connection();
			String sql = "select photo from dzda_jsz_daxxb_photo_log where id='"+id+"'";
			proc = conn.prepareStatement(sql);
			rs = proc.executeQuery();
			while(rs.next()){
				blob = (oracle.sql.BLOB) rs.getBlob("photo");
			}
			byte[] byte_array = null;
			if(blob != null){
				int length = (int) blob.length();
				byte_array = blob.getBytes(1, length);
			}
			return byte_array;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (proc != null) {
				proc.close();
				proc = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}		
	}
	
	/**
	 * 图片加上退办原因的水印
	 * @param bazMap
	 * @return
	 * @throws Exception
	 */
	public void createBazPic(String tbyy,String xh,String table) throws Exception {
		byte[] result = null;
		try {
			byte[] inputStream= getImageLBlob(xh,table);
			System.out.println("图片原始大小：=="+inputStream.length);
			if(inputStream!=null){
				ByteArrayInputStream in = new ByteArrayInputStream(inputStream);
				BufferedImage buffImage = ImageIO.read(in);
				if (buffImage != null) {
					Graphics g = buffImage.getGraphics();
					Font font = new Font("宋体", Font.BOLD, 25);
					g.setFont(font);
					//Color c = new Color(0, 0, 0);//若是要其他颜色的格式
					g.setColor(Color.red);
					System.out.println(tbyy.length()+"=="+tbyy.length()/15);
					if(tbyy.length()<=15){
						g.drawString("退办原因:"+tbyy, 50, 100);
					}else{
						g.drawString("退办原因:"+tbyy.substring(0, 16), 50, 100);						
					    for (int i = 1; i < tbyy.length()/15+1; i++) {
					    	if(i*15+15<tbyy.length()){
					    		g.drawString(tbyy.substring(i*15,i*15+15), 50, 100+40*i);
					    	}else{
					    		g.drawString(tbyy.substring(i*15,tbyy.length()), 50, 100+40*i);
					    	}
						}					
					}				
					
					ByteArrayOutputStream outImg = new ByteArrayOutputStream();//--可能造成图片的压缩,图片变小。。。
			        ImageIO.write(buffImage, "jpg", outImg);
				    result = outImg.toByteArray();
					System.out.println("打印文字后的大小：=="+result.length);
					this.editeBlobByByte(xh, result,table,"photo");//修改图片信息//null表示条件为xh=''
					//return result;
				}
			}
			
		} catch (Exception e) {
			System.out.println("错误");
			e.printStackTrace();
		}
		//return result;		
	}
	/**
	 * 影像采集_数据字典表(Map)
	 * @param
	 *  dmlb ='YWLX' ：业务类型
	 *  dmlb ='TBYY_PHOTO' ：图片退办原因
	 *  dmlb ='ZJCX' ：准驾车型
	 * 
	 */
    @SuppressWarnings("unchecked")
	public Map<String, String> getJszSjzdDict(String dmlb) throws Exception {
    	Map<String, String> map = new HashMap<String, String>();
    	String hql ="from DzdaJszSjzd where dmlb='"+dmlb+"' order by dmms2 asc";
    	 List<?> list = this.defaultDpublishDao.getRepositories(hql);
    	 if(list!=null && list.size()>0){
    		 List<DzdaJszSjzd> sjzds = (List<DzdaJszSjzd>)list;
    		 for (DzdaJszSjzd vo : sjzds) {
				map.put(vo.getDmz(), vo.getDmms1());
			}
    	 }
    	 return map;
    }
    /**
	 * 影像采集_数据字典表(List)
	 * @param
	 *  dmlb ='YWLX' ：业务类型
	 *  dmlb ='TBYY_PHOTO' ：图片退办原因
	 *  dmlb ='ZJCX' ：准驾车型
	 * 
	 */
    @SuppressWarnings("unchecked")
	public List<DzdaJszSjzd> getJszSjzdList(String dmlb) throws Exception {
    	String hql ="from DzdaJszSjzd where dmlb='"+dmlb+"' order by dmz asc";
    	 List<?> list = this.defaultDpublishDao.getRepositories(hql);
    	return (List<DzdaJszSjzd>)list;
    }
    
    
    
    
    /**********************车管审核驾驶人业务***************************/
    /**
     * 获取车管需要审核的信息
     * 条件 cjzt:1 or B
     */
	@SuppressWarnings("unchecked")
	public List<DzdaJszDaxxb> getcgCheckList(HttpServletRequest request,String temp) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		StringBuffer sql = new StringBuffer("from DzdaJszDaxxb t where lockr='"+user.getName()+"' ");
        if(temp.equals("add")){//表示获取待审数据时的查询
    		String btnType = request.getParameter("btnType");
    		if(btnType!=null && !btnType.equals("")){
    			if(btnType.equals("dateConType")){//已时间日期为条件
    				String s_date = request.getParameter("s_date");
    				String e_date = request.getParameter("e_date");			
    		 		if(s_date!=null && !"".equals(s_date)){
    		 			sql.append(" and t.cjsj  >= to_date('"+s_date+" 00:00:00','yyyy-MM-dd hh24:mi:ss') ");	
    		 			request.setAttribute("s_date", s_date);
    		 		}
    		 		if(e_date!=null && !"".equals(e_date)){
    		 			sql.append(" and t.cjsj <= to_date('"+e_date+" 23:59:59','yyyy-MM-dd hh24:mi:ss') ");	
    		 			request.setAttribute("e_date", e_date);
    		 		}
    			}else{ //身份证号码为条件
    				String sfzmhm = request.getParameter("sfzmhm");
    				if(sfzmhm!=null && !"".equals(sfzmhm)){
    					sql.append(" and t.sfzmhm = '"+sfzmhm+"'");
        				request.setAttribute("sfzmhm",sfzmhm);
    				}
    				
    				String cjzt= request.getParameter("cjzt");
    				if(cjzt!=null && !cjzt.equals("--")){
    					sql.append(" and t.cjzt = '"+cjzt+"'");
    					request.setAttribute("cjzt",cjzt);
    				}
//    				else{
//    					sql.append(" and cjzt in ('1','B') ");//表示获取待审后，查询cjzt为1，B的
//    				}
    			}
    		} else{
    			sql.append(" and cjzt in ('1','B') ");//表示获取待审后，查询cjzt为1，B的
    		}
        
        }else{   //初始化页面的查询
        	sql.append(" and cjzt in ('B') ");
        }
		
		List<DzdaJszDaxxb> jsrCjList = new ArrayList<DzdaJszDaxxb>();
		List<?> list = defaultDpublishDao.getRepositories(sql.toString());
		jsrCjList = (List<DzdaJszDaxxb>)list;
		if(jsrCjList.size() > 0 ){  //0待驾校初审，1待车管复核，2归档、3档案移交邮政，B补审，CT车管退办，GT归档退办
			for (DzdaJszDaxxb dz : jsrCjList) {
				dz.setCjmac(dz.getCjzt());//用 cjmac字段 填充cjzt的原值
				if(dz.getCjzt().equals("0")){
					dz.setCjzt("待驾校初审");
				}else if(dz.getCjzt().equals("JT")){
					dz.setCjzt("驾校退办");
				}else if(dz.getCjzt().equals("1")){
					dz.setCjzt("待车管复核");
				}else if(dz.getCjzt().equals("2")){
					dz.setCjzt("归档");
				}else if(dz.getCjzt().equals("3")){
				    dz.setCjzt("档案移交邮政");	
				}else if(dz.getCjzt().equals("B")){
					dz.setCjzt("补审");
				}else if(dz.getCjzt().equals("CT")){
					dz.setCjzt("车管退办");
				}else if(dz.getCjzt().equals("GT")){
					dz.setCjzt("归档退办");
				}
			}
		}
		request.setAttribute("jsrCjList", jsrCjList);		
		log.info("method:getJsrUserList");
		return jsrCjList;
	}	
	
	/***
	 * 驾驶证档案卷宗查询
	 * @return
	 * @throws Exception
	 */
	public EsDrvLicense getJsrBasicInfo(HttpServletRequest request) throws Exception {
		 EsDrvLicense dataInfo = null;
		 String sfzmhm = request.getParameter("sfzmhm");
		 String dabh = request.getParameter("dabh");
		if((sfzmhm==null || sfzmhm.equals("")) && (dabh==null || dabh.equals(""))){
			
		}else{
			 String sql="select dabh,sfzmhm,ydabh,xm,xb,csrq,zt,ljjf,lxdh,zjcx,djzsxxdz from es_drv_license where 1=1 ";
				
			 if(sfzmhm!=null && !sfzmhm.equals("")){
				 sql+=" and sfzmhm ='"+sfzmhm+"'";
				 request.setAttribute("sfzmhm",sfzmhm);
			 }
			 
		    
			 if(dabh!=null && !dabh.trim().equals("")){
				 sql+= " and dabh='"+dabh.trim()+"'";
				 request.setAttribute("dabh",dabh);
			 }
			 
			 List list = this.defaultDpublishDao.findSQL(sql);
			 if(list!=null && list.size()>0) {
					StringBuffer hql = new StringBuffer("from DzdaJszDaxxb t where 1=1 ");
					if(sfzmhm!=null && !sfzmhm.equals("")){
						hql.append(" and sfzmhm ='"+sfzmhm+"'");
						request.setAttribute("sfzmhm",sfzmhm);
					}
					if(dabh!=null && !dabh.trim().equals("")){
						 hql.append(" and dabh='"+dabh.trim()+"'");
						 request.setAttribute("dabh",dabh);
					}
					List poList = this.defaultDpublishDao.getRepositories(hql.toString());
					if(list!=null && list.size()>0){
					     List<DzdaJszDaxxb> dzdaList = (List<DzdaJszDaxxb>) poList;
							if(dzdaList.size() > 0 ){  //0待驾校初审，1待车管复核，2归档、3档案移交邮政，B补审，CT车管退办，GT归档退办
								Map<String,String> dictMap =getJszSjzdDict("ZT");
								Map<String,String> ywlxMap = getJszSjzdDict("YWLX");
								for (DzdaJszDaxxb dz : dzdaList) {
									DzdaJszDaxxbSh cjSh=(DzdaJszDaxxbSh)this.defaultDpublishDao.getRepository(dz.getCjxh(), DzdaJszDaxxbSh.class);
									if(cjSh!=null){
										dz.setLockxm(cjSh.getGdrxm());//归档人姓名
										dz.setLockbm(cjSh.getGdbm()); //归档部门
									}else{
										dz.setLockxm("");//归档人姓名
										dz.setLockbm(""); //归档部门
									}
									dz.setCjmac(dz.getCjzt());//用 cjmac字段 填充cjzt的原值
									dz.setCjzt(dictMap.get(dz.getCjzt()));
									dz.setYwlx(ywlxMap.get(dz.getYwlx()));
									//获取每一个业务的资料类型信息
									List listP = this.defaultDpublishDao.getRepositories("from DzdaJszDaxxbPhoto where cjxh='"+dz.getCjxh()+"'");
									if(listP!=null && listP.size()>0){
										List<DzdaJszDaxxbPhoto> photos =(List<DzdaJszDaxxbPhoto>)listP;
										Map<String,String> zllxMap = getJszSjzdDict("YWLX_A");
										Map<String,String> otherMap = getJszSjzdDict("YWLX_OTHER");
										for (DzdaJszDaxxbPhoto pto : photos) { 
											if(zllxMap.get(pto.getZllx()) != null){
												pto.setZflx(zllxMap.get(pto.getZllx()));//用zflx存放zllx的翻译名称
											}else{
												pto.setZflx(otherMap.get("OTHER"));    //找不到的，用
											}											
										}
										dz.setPhotoLx(photos);
									}
								}
							}
							request.setAttribute("dzdaList", dzdaList);	
					}
					
					
					 dataInfo = new EsDrvLicense();
					 Object[] objs = (Object[])list.get(0);
					 if(objs[0]!=null){
						 dataInfo.setDabh(objs[0].toString());	
					 }
					 if(objs[1]!=null){
						 dataInfo.setSfzmhm(objs[1].toString());
					 }
					 if(objs[2]!=null){
						 dataInfo.setYdabh(objs[2].toString());
					 }
					 if(objs[3]!=null){
						 dataInfo.setXm(objs[3].toString());
					 }
					 if(objs[4]!=null){
						 if(objs[4].toString().equals("1")){
							 dataInfo.setXb("男");
						 }else{
							 dataInfo.setXb("女");
						 }				 
					 }
					 if(objs[5]!=null){
						 dataInfo.setCsrq(objs[5].toString());
					 }
					 if(objs[6]!=null){
						 dataInfo.setZt(objs[6].toString());
					 }
					 if(objs[7]!=null){
						 dataInfo.setLjjf(objs[7].toString());
					 }
					 if(objs[8]!=null){
						 dataInfo.setLxdh(objs[8].toString());
					 }
					 if(objs[9]!=null){
						 dataInfo.setZjcx(objs[9].toString());
					 }
					 if(objs[10]!=null){
						 dataInfo.setDjzsxxdz(objs[10].toString());
					 }
				 }else{
					 request.setAttribute("queryResult", "noData");//查无此数据
				 }
		}
	    
		 
	     return dataInfo;
	}

	/**
	 *  根据邮政编码查询驾驶估值档案
	 * @param yzbm
	 * @return
	 * @throws Exception
	 */
	public DzdaJszDaxxb getDzdaJszInfo(String yzbm) throws Exception {
		StringBuffer hql = new StringBuffer("from DzdaJszDaxxb t where  yzbm='"+yzbm+"'");
		List list =this.defaultDpublishDao.getRepositories(hql.toString());
		if(list!=null && list.size()>0){
			return  (DzdaJszDaxxb)list.get(0);
		}
		return null;
	}
	
	public String getIp(HttpServletRequest request) throws Exception {
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
	

	public static Logger getLog() {
		return log;
	}
	
	public DefaultDpublishDao getDefaultDpublishDao() {
		return defaultDpublishDao;
	}


	public void setDefaultDpublishDao(DefaultDpublishDao defaultDpublishDao) {
		this.defaultDpublishDao = defaultDpublishDao;
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
