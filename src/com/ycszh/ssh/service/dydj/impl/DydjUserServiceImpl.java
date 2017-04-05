package com.ycszh.ssh.service.dydj.impl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.ycszh.global.SysConst;
import com.ycszh.ssh.dao.ydwt.WtydnsUserDao;
import com.ycszh.ssh.hbm.ydwt.WtydnsUser;
import com.ycszh.ssh.service.dydj.DydjUserService;

public class DydjUserServiceImpl implements DydjUserService {
	
	private WtydnsUserDao  wtydnsUserDao;
	private final static Logger log = Logger.getLogger(DydjUserServiceImpl.class);
	
	public Integer checkLogin(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String userName = request.getParameter("cusername").toString();
		String password = request.getParameter("cpassword").toString();
		String rand = (String) session.getAttribute("rand");
		String code = request.getParameter("ccode").toString();
		log.info("method:checkLogin|param:userName="+userName+",password="+password);
		if (rand.equals(code)) {
			List<WtydnsUser> userList = wtydnsUserDao.getUserInfo(userName, password);
			if(null != userList && 0 < userList.size()){
				WtydnsUser user = userList.get(0);
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
				user.setLrip(ip);
				request.getSession().setAttribute(SysConst.USERBEAN, user);
				return 0;
			}else{
				return 3;
			}
		}else{
			return 2;
		}
	}
	
	public Integer insertOrUpdateUser(WtydnsUser user,HttpServletRequest request) throws Exception {
		if (user != null) {
			try {
				user.setSynFlag("UW");
				user.setTranDate(null);
				user.setTranFlag(null);
				WtydnsUser u=wtydnsUserDao.addRepository(user);
//				WtydnsUser ulog = insertUserLog(user);
//				ulog.setCzr(user.getLoginname());
//				ulog.setCzrbm(user.getLrbmmc());
//				ulog.setCzsj(new Date());
//				ulog.setCznr("修改");
//				ulog.setCzip(ip);
//				//userLogDao.addRepository(ulog);
//				userDao.addYywfcUserLog(ulog);
				log.info("method:insertOrUpdateUser|param:user="+user);
					return 0;
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
				return 1;
				}
		} else { 
			return 1;
		}
	}

	public WtydnsUserDao getWtydnsUserDao() {
		return wtydnsUserDao;
	}

	public void setWtydnsUserDao(WtydnsUserDao wtydnsUserDao) {
		this.wtydnsUserDao = wtydnsUserDao;
	}

}
