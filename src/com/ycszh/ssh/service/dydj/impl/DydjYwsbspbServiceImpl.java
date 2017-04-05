package com.ycszh.ssh.service.dydj.impl;

import java.io.File;
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
import com.ycszh.ssh.dao.dydj.DydjUserDao;
import com.ycszh.ssh.dao.dydj.DydjYHUserDao;
import com.ycszh.ssh.dao.dydj.DydjYwsbspbDao;
import com.ycszh.ssh.dao.dydj.IDydjYhdbrDao;
import com.ycszh.ssh.dao.ydwt.WscgsSjzdDao;
import com.ycszh.ssh.hbm.dydj.DydjUser;
import com.ycszh.ssh.hbm.dydj.DydjYhUser;
import com.ycszh.ssh.hbm.dydj.DydjYhdbr;
import com.ycszh.ssh.hbm.dydj.DydjYhdbrLog;
import com.ycszh.ssh.hbm.ydwt.WscgsSjzd;
import com.ycszh.ssh.service.dydj.DydjYwsbspbService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.StringUtil;

public class DydjYwsbspbServiceImpl implements DydjYwsbspbService{
	
	private WscgsSjzdDao sjzdDao;
	
	private DydjUserDao  dydjUserDao;
	
	private DydjYwsbspbDao dydjYwsbspbDao;
	
	private DydjYHUserDao dydjYHUserDao;
	
	private IDydjYhdbrDao dydjYhdbrDao;
	private final static Logger log = Logger.getLogger(DydjYwsbspbServiceImpl.class);
	/**
	 * 获取邮政/车管用户信息
	 * @param request
	 * @param currentPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DydjUser> getUserList(HttpServletRequest request,int currentPage) throws Exception {
		StringBuffer buf = new StringBuffer();
		String yzYhdm = request.getParameter("yzYhdm");
		String zhlx = request.getParameter("zhlx");
		
		buf.append(" select ID, YZ_YHDM, YHXM, PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(YMMM, 'EF57') YMMM, ZHLX,");
		buf.append(" CG_QX01, CG_QX02,CG_QX03, CG_QX04, CG_QX05, CG_QX06, CG_QX07, CG_QX08, CG_QX09,  CG_QX10,");
		buf.append(" YZ_QX01,YZ_QX02, YZ_QX03, YZ_QX04, YZ_QX05, YZ_QX06, YZ_QX07, YZ_QX08, YZ_QX09, YZ_QX10, YZ_CG_JJM,");
		buf.append(" LRR3, LRR2, LRBMID, LRBMMC, LRIP, STATUS, SYN_FLAG,TRAN_FLAG, TRAN_DATE from dydj_user where 1=1 ");
		if(null!=yzYhdm && !"".equals(yzYhdm)){
			buf.append(" and YZ_YHDM like '%"+yzYhdm+"%'");
		}
		if(!"--".equals(zhlx) && null!= zhlx){
			buf.append(" and ZHLX ='"+zhlx+"'");
		}
		int size = this.dydjUserDao.findSQL(buf.toString()).size();
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		List<DydjUser> userList = new ArrayList<DydjUser>();
		String curi = request.getRequestURI();
		if (size > 0) {
			userList=this.dydjUserDao.findSQLByPage(buf.toString(), offset, pageSize, DydjUser.class);
			for (int i = 0; i < userList.size(); i++) {
				DydjUser user = userList.get(i);
				if(user.getYzQx10().equals("C34702A8FED97CBFE040007F0100339B")){
					user.setYzQx10("西丽总所");
				}
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
		request.setAttribute("userList", userList);
		request.setAttribute("yzYhdm", yzYhdm);
		request.setAttribute("zhlx", zhlx);
		log.info("method:getUserList|param:request="+request+",currentPage="+currentPage);
		return userList;
	}
	/**
	 * 获取科级以下的部门
	 * @return
	 * @throws Exception
	 */
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
			deptList = dydjUserDao.findSQL(sql);
			return deptList;
	}
	/**
	 * 添加用户信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public int addUserInfo(HttpServletRequest request,DydjUser user) throws Exception {
		User sessionU = (User) request.getSession().getAttribute(SysConst.USERBEAN);	
		String hql="from DydjUser where yzYhdm='"+user.getYzYhdm()+"'";
		List<DydjUser> list = this.dydjUserDao.getRepositories(hql);
		int result=0;
		if(list.isEmpty()){
			user.setStatus("1");//用户状态:可用
			user.setSynFlag("UW");//内网
			user.setTranDate(null);
			user.setTranFlag(null);
			user.setYzQx01("1");
			user.setYzQx02("1");
			user.setYzQx03("1");
			user.setYzQx04("1");
			user.setYzQx05("1");
			user.setYzQx06("1");			
			user.setYzQx07("1");
			user.setYzQx08("1");
			user.setYzQx09("1");
			user.setYzQx10("C34702A8FED97CBFE040007F0100339B");
			user.setCgQx01("1");
			user.setCgQx02("1");
			user.setCgQx03("1");
			user.setCgQx04("1");
			user.setCgQx05("1");
			user.setCgQx06("1");
			user.setCgQx07("1");
			user.setCgQx08("1");
			user.setCgQx09("1");
			user.setCgQx10("1");
			user.setLrr3(sessionU.getName());
	        user.setYmmm(this.dydjYwsbspbDao.getEncrypt(user.getYmmm(),SysConst.P_KEY));//加密
			dydjUserDao.addRepository(user); 
			result=1;
		}else{
			result=2;
		}
		return result;
	}
	  /**
	  * 冻结/解冻用户
	  */
	public Integer freezeUser(HttpServletRequest request,Integer id) throws Exception {
		String temp = request.getParameter("temp");
		String status ="0";
		if(!"bank".equals(temp)){
			String sqlQtr = "select status  from  DYDJ_USER where id="+id;
		   List list =this.dydjUserDao.findSQL(sqlQtr);
		   if(list!=null && list.size()>0){
			    Object obj =(Object)list.get(0);
			   if(!"1".equals(obj.toString())){
					status ="1";
				}
			    String sql ="update DYDJ_USER set status='"+status+"' , SYN_FLAG='UW' , TRAN_FLAG=null , TRAN_DATE=sysdate where id="+id;
				dydjUserDao.updateRepositoryBySql(sql);
				return 1;
		   }else{
				return 0;
		   }
		}else{
			String sqlQtr ="select status from DYDJ_YH_USER where id="+id;
			List list = this.dydjYHUserDao.findSQL(sqlQtr);
			if(list!=null && list.size()>0){
				 Object obj =(Object)list.get(0);
			     if(!"1".equals(obj.toString())){
					status ="1";
				}
			     String sql ="update  DYDJ_YH_USER set status ='"+status+"' ,  SYN_FLAG='UW' , TRAN_FLAG=null , TRAN_DATE=sysdate where id="+id;
			     dydjUserDao.updateRepositoryBySql(sql);
			     return 1;
			}else{
				return 0;
			}
		}
	}

	/**
	 * 重置密码
	 */
	public Integer resetPwd(String id,HttpServletRequest request) throws Exception {
		String temp = request.getParameter("temp");//用sql的关系，防止blob字段为空
		if(!"bank".equals(temp)){
			String sqlQtr ="select status from DYDJ_USER where id="+id; 
			List list = this.dydjYHUserDao.findSQL(sqlQtr);
			if(list!=null && list.size()>0){
				 String ymmm = dydjYwsbspbDao.getEncrypt("000000", SysConst.P_KEY);
			     String sql ="update  DYDJ_USER set YMMM ='"+ymmm+"' ,  SYN_FLAG='UW' , TRAN_FLAG=null , TRAN_DATE=sysdate where id="+id;
			     dydjUserDao.updateRepositoryBySql(sql);
			     return 1;
			}else{
				return 0;
			}
		}else{
			String sqlQtr ="select ZJJGDMZ from DYDJ_YH_USER where id="+id;
			List list = this.dydjYHUserDao.findSQL(sqlQtr);
			if(list!=null && list.size()>0){
				String zzjgdmz = list.get(0).toString().trim();
				String ymmm = dydjYwsbspbDao.getEncrypt(zzjgdmz, SysConst.P_KEY);
			     String sql ="update  DYDJ_YH_USER set YMMM ='"+ymmm+"' ,  SYN_FLAG='UW' , TRAN_FLAG=null , TRAN_DATE=sysdate where id="+id;
			     dydjUserDao.updateRepositoryBySql(sql);
			     return 1;
			}else{
				return 0;
			}
		}
			
	}
	/**
	 * 清空银行用户的mac地址
	 * @param id  :银行用户id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Integer clearnMac(String id,HttpServletRequest request) throws Exception {;
		String sqlQtr ="select status from DYDJ_YH_USER where id="+id;
		List list = this.dydjYHUserDao.findSQL(sqlQtr);
		if(list!=null && list.size()>0){	
		     String sql ="update  DYDJ_YH_USER set MAC1=null,MAC2=null,MAC3=null,SYN_FLAG='UW',TRAN_FLAG=null,TRAN_DATE=sysdate where id="+id;
		     dydjUserDao.updateRepositoryBySql(sql);
		     return 1;
		}else{
			return 0;
		}
	}
	////////---------------------以下是银行开户信息管理-----------------------------------///////
	/**
	 * 获取银行用户信息
	 * @param request
	 * @param currentPage
	 * @return	
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DydjYhUser> getYHUserList(HttpServletRequest request,int currentPage) throws Exception {
		StringBuffer buf = new StringBuffer();
		String yzYhdm = request.getParameter("yzYhdm");
		String status = request.getParameter("status");
		
		buf.append(" select ");
		buf.append(" ID,YZ_YHDM,YHXM, PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(YMMM, 'EF57') YMMM,");
		buf.append(" ZJJGDMZ,FRMC,LXRMC,LXDH,LXDZ,PHOTOIMG,MAC1,MAC2,MAC3,BZ1,BZ2,BZ3,BZ4,BZ5,BZ6,BZ7,");
		buf.append(" BZ8,BZ9,BZ10,LRR3,LRR2,LRBMID,LRBMMC,LRIP,STATUS,SYN_FLAG,");
		buf.append(" TRAN_FLAG,TRAN_DATE,YXQZ from dydj_yh_user where 1=1 ");
		if(null!=yzYhdm && !"".equals(yzYhdm)){
			buf.append(" and YZ_YHDM like '%"+yzYhdm+"%'");
		}
		if(!"--".equals(status) && null!= status){
			buf.append(" and STATUS ='"+status+"'");
		}
		int size = this.dydjYHUserDao.findSQL(buf.toString()).size();
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		List<DydjYhUser> userList = new ArrayList<DydjYhUser>();
		String curi = request.getRequestURI();
		if (size > 0) {
			userList=this.dydjYHUserDao.findSQLByPage(buf.toString(), offset, pageSize, DydjYhUser.class);
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
		request.setAttribute("yzYhdm", yzYhdm);
		request.setAttribute("status", status);
		log.info("method:getYHUserList|param:request="+request+",currentPage="+currentPage);
		return userList;
	}
	
	/**
	 * 添加银行信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<DydjYhUser>  getYhUser(HttpServletRequest request,DydjYhUser user) throws Exception {
		//先查询改银行代码是否已经存在
		String hql ="from DydjYhUser where yzYhdm='"+user.getYzYhdm()+"'";
		List<DydjYhUser> list=this.dydjYHUserDao.getRepositories(hql);
		return list;
	}
	public DydjYhUser addYhUser(HttpServletRequest request,DydjYhUser user) throws Exception {
		User sessionUser = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		user.setStatus("1");//用户状态:可用
		user.setSynFlag("UW");//内网
		user.setTranDate(null);
		user.setTranFlag(null);
		user.setLrr3(sessionUser.getName());
		user.setLrr2(sessionUser.getYgxm());
		user.setLrbmid(sessionUser.getBmid());
		user.setLrbmmc(sessionUser.getBmmc());
		user.setLrip(getLoginIp(request));
		user.setBz10(DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
        user.setYmmm(this.dydjYwsbspbDao.getEncrypt(user.getYmmm(),SysConst.P_KEY));//加密
        DydjYhUser yhUser = dydjYHUserDao.addRepository(user); 
        return yhUser;
	}
	/**
	 *  修改银行信息
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public DydjYhUser editYhUser(HttpServletRequest request,DydjYhUser pageUser) throws Exception {
		User sessionUser = (User) request.getSession().getAttribute(SysConst.USERBEAN);
        DydjYhUser user=this.dydjYHUserDao.getRepository(pageUser.getId());
		user.setStatus(pageUser.getStatus());
		user.setYxqz(pageUser.getYxqz());
		user.setYhxm(pageUser.getYhxm());
		user.setZjjgdmz(pageUser.getZjjgdmz());
		user.setFrmc(pageUser.getFrmc());
		user.setLxrmc(pageUser.getLxrmc());
		user.setLxdh(pageUser.getLxdh());
		user.setLxdz(pageUser.getLxdz());
		user.setSynFlag("UW");//内网
		user.setTranDate(null);
		user.setTranFlag(null);
		user.setLrr3(sessionUser.getName());
		user.setLrr2(sessionUser.getYgxm());
		user.setLrbmid(sessionUser.getBmid());
		user.setLrbmmc(sessionUser.getBmmc());
		user.setLrip(getLoginIp(request));
        user.setYmmm(this.dydjYwsbspbDao.getEncrypt(pageUser.getYmmm(),SysConst.P_KEY));//加密
        DydjYhUser yhUser =this.dydjYHUserDao.updateRepository(user); 
        return yhUser;
	}
	
	
	public DydjYhUser  getDydjYhUser(HttpServletRequest request) throws Exception {
		String id =request.getParameter("id");
		DydjYhUser user =new DydjYhUser();
		DydjYhUser yhUsr=this.dydjYHUserDao.getRepository(Integer.parseInt(id));
		user.setId(yhUsr.getId());
		user.setStatus(yhUsr.getStatus());
		user.setBz1(yhUsr.getBz1());
		user.setBz2(yhUsr.getBz2());
		user.setBz3(yhUsr.getBz3());
		user.setBz4(yhUsr.getBz4());
		user.setBz5(yhUsr.getBz5());
		user.setBz6(yhUsr.getBz6());
		user.setBz7(yhUsr.getBz7());
		user.setBz8(yhUsr.getBz8());
		user.setBz9(yhUsr.getBz9());
		user.setBz10(yhUsr.getBz10());
		user.setFrmc(yhUsr.getFrmc());
		user.setLxdh(yhUsr.getLxdh());
		user.setZjjgdmz(yhUsr.getZjjgdmz());
		user.setYzYhdm(yhUsr.getYzYhdm());
		user.setYhxm(yhUsr.getYhxm());
		user.setLxdz(yhUsr.getLxdz());
		user.setLxrmc(yhUsr.getLxrmc());
		user.setYxqz(yhUsr.getYxqz());
		user.setYmmm(this.dydjYwsbspbDao.getDecrypt(yhUsr.getYmmm(), SysConst.P_KEY));
	  return user;
	}
	public int editPhoto (DydjYhUser user,File file) throws Exception {
		return  this.dydjYHUserDao.uploadDydyYhphoto( file, user);
	}
	public DydjUserDao getDydjUserDao() {
		return dydjUserDao;
	}	
	
	public byte[] getImageBlob(HttpServletRequest request, Integer tpm) throws Exception{
		byte[] b = null;
		b = this.dydjYHUserDao.getImageBlob(tpm);
		return b;
	}
	public int editeBlobByByte(byte[] arry_byte,DydjYhUser yhUser) throws Exception {
		return this.dydjYHUserDao.editeBlobByByte(yhUser, arry_byte);
	}
	
	@SuppressWarnings("unchecked")
	public List getYhdbrList(HttpServletRequest request) throws Exception{
		List list = null;
		StringBuffer sqlBuffer = new StringBuffer("select t from DydjYhdbr t where 1=1 ");
		String yhUserId = request.getParameter("yhUserId");
		if(!StringUtil.isNull(yhUserId)){
			sqlBuffer.append(" and yhUserId = "+yhUserId);
			request.setAttribute("yhUserId", yhUserId);
		}
		sqlBuffer.append(" and synFlag <> 'DW' ");
		list = this.dydjYhdbrDao.getRepositories(sqlBuffer.toString());
		request.setAttribute("dydjYhdbrList", list);
		return list;
	}
	
	public DydjYhdbr getDydjYhdbrInfo(HttpServletRequest request) throws Exception{
		DydjYhdbr yhDbr = null;
		String id = request.getParameter("yhdbrid");
		if(!StringUtil.isNull(id)){
			yhDbr = this.dydjYhdbrDao.getRepository(Integer.parseInt(id));
		}
		return yhDbr;
	}
	
	public Integer delYhdbrInfo(HttpServletRequest request, String id) throws Exception{
		Integer result = null;
		if(!StringUtil.isNull(id)){
			Integer pkid = Integer.parseInt(id);
			DydjYhdbr dydjYhdbr = this.dydjYhdbrDao.getRepository(pkid);
			dydjYhdbr.setSynFlag("DW");
			dydjYhdbr.setTranFlag(null);
			dydjYhdbr.setTranDate(null);
			this.dydjYhdbrDao.updateRepository(dydjYhdbr);
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			DydjYhdbrLog log = new DydjYhdbrLog();
			log = (DydjYhdbrLog)getXclog(log, dydjYhdbr);
			log.setCzr(user.getName());
			log.setCzrxm(user.getYgxm());
			log.setCzrbm(user.getBmid());
			log.setCzrkjbm("");
			log.setCzsj(new Date());
			log.setCzip(getLoginIp(request));
			log.setCzmac("");
			this.dydjYhdbrDao.addObj(log, request);
			result = 1;
		}
		return result;
	}
	
	public Integer addYhdbrInfo(HttpServletRequest request, DydjYhdbr yhdbr) throws Exception{
		if(yhdbr != null){
			if(yhdbr.getId() == null){
				String sql = "select count(1) from dydj_yhdbr t where t.yh_user_id = "+yhdbr.getYhUserId()+" and t.dbr_sfzmhm = '"+yhdbr.getDbrSfzmhm()+"'";
				String exit = this.dydjYhdbrDao.findSQL(sql).get(0)+"";
				if(!"0".equals(exit)){
					return 2;
				}
				User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
				yhdbr.setLrr(user.getName());
				yhdbr.setLrrxm(user.getYgxm());
				yhdbr.setLrrbm(user.getBmid());
				yhdbr.setLrrkjbm("");
				yhdbr.setLrsj(new Date());
				yhdbr.setLrip(getLoginIp(request));
				this.dydjYhdbrDao.addRepository(yhdbr);
				DydjYhdbrLog log = new DydjYhdbrLog();
				log = (DydjYhdbrLog)getXclog(log, yhdbr);
				log.setCzr(user.getName());
				log.setCzrxm(user.getYgxm());
				log.setCzrbm(user.getBmid());
				log.setCzrkjbm("");
				log.setCzsj(new Date());
				log.setCzip(getLoginIp(request));
				log.setCzmac("");
				this.dydjYhdbrDao.addObj(log, request);
				return 1;
			}else{
				User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
				yhdbr.setLrr(user.getName());
				yhdbr.setLrrxm(user.getYgxm());
				yhdbr.setLrrbm(user.getBmid());
				yhdbr.setLrrkjbm("");
				yhdbr.setLrsj(new Date());
				yhdbr.setLrip(getLoginIp(request));
				this.dydjYhdbrDao.updateRepository(yhdbr);
				DydjYhdbrLog log = new DydjYhdbrLog();
				log = (DydjYhdbrLog)getXclog(log, yhdbr);
				log.setCzr(user.getName());
				log.setCzrxm(user.getYgxm());
				log.setCzrbm(user.getBmid());
				log.setCzrkjbm("");
				log.setCzsj(new Date());
				log.setCzip(getLoginIp(request));
				log.setCzmac("");
				this.dydjYhdbrDao.addObj(log, request);
				return 1;
			}
			
		}
		return null;
	}
	
	public Integer updateYhdbrInfo(HttpServletRequest request, String id, String flag) throws Exception{
		Integer result = null;
		if(!StringUtil.isNull(id)){
			String sql = "update dydj_yhdbr set flag = '"+flag+"', syn_flag = 'UW', tran_flag = null, tran_date = null where id = " + id;
			this.dydjYhdbrDao.updateRepositoryBySql(sql);
			result = 1;
		}
		return result;
	} 
	
	public Object getXclog(Object obj1, Object obj2) throws Exception{
		if(obj1 == null){
			return null;
		}
		try {
			Field[] fields = obj2.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				if(name.equals("synFlag") || name.equals("tranFlag") || name.equals("tranDate")){
					continue;
				}
				Field field = obj1.getClass().getDeclaredField(name);
				field.set(obj1, fields[i].get(obj2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return obj1;
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

	public void setDydjUserDao(DydjUserDao dydjUserDao) {
		this.dydjUserDao = dydjUserDao;
	}

	public List<WscgsSjzd> getHpzl() throws Exception{
		return this.sjzdDao.getHpzl();
	}
		
	public WscgsSjzdDao getSjzdDao() {
		return sjzdDao;
	}

	public void setSjzdDao(WscgsSjzdDao sjzdDao) {
		this.sjzdDao = sjzdDao;
	}
	public DydjYwsbspbDao getDydjYwsbspbDao() {
		return dydjYwsbspbDao;
	}
	public void setDydjYwsbspbDao(DydjYwsbspbDao dydjYwsbspbDao) {
		this.dydjYwsbspbDao = dydjYwsbspbDao;
	}

	public DydjYHUserDao getDydjYHUserDao() {
		return dydjYHUserDao;
	}

	public void setDydjYHUserDao(DydjYHUserDao dydjYHUserDao) {
		this.dydjYHUserDao = dydjYHUserDao;
	}
	public IDydjYhdbrDao getDydjYhdbrDao() {
		return dydjYhdbrDao;
	}
	public void setDydjYhdbrDao(IDydjYhdbrDao dydjYhdbrDao) {
		this.dydjYhdbrDao = dydjYhdbrDao;
	}

}
