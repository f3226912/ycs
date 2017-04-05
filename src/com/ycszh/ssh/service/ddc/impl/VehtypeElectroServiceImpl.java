package com.ycszh.ssh.service.ddc.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.hbm.ddc.VehtypeElectro;
import com.ycszh.ssh.hbm.ddc.VehtypeElectroLog;
import com.ycszh.ssh.service.ddc.IVehtypeElectroService;
import com.ycszh.util.StringUtil;

public class VehtypeElectroServiceImpl implements IVehtypeElectroService {
	private DefaultDao defaultDao;
	
	@SuppressWarnings("unchecked")
	public List<VehtypeElectro> getVehtypeElectroList(HttpServletRequest request, int currentPage, String cztype) throws Exception{
		List<VehtypeElectro> eleCtroList = null;
		String orgid = "";
		Map map = new HashMap();
		StringBuffer listBuffer = new StringBuffer("from VehtypeElectro t where 1=1 ");
		StringBuffer countBuffer = new StringBuffer("select count(1) from vehtype_electro t where 1=1 ");
		int pagesize = SysConst.PAGESIZE;
		int offset  = (currentPage - 1)*pagesize;
		String uri = request.getRequestURI();
		String clpp = request.getParameter("clpp");
		String clxh = request.getParameter("clxh");
		String djxh = request.getParameter("djxh");
		if(!StringUtil.isNull(clpp)){
			listBuffer.append(" and t.clpp like '%"+clpp+"%'");
			countBuffer.append(" and t.clpp like '%"+clpp+"%'");
			request.setAttribute("clpp", clpp);
		}
		if(!StringUtil.isNull(clxh)){
			listBuffer.append(" and t.clxh = '"+clxh+"'");
			countBuffer.append(" and t.clxh = '"+clxh+"'");
			request.setAttribute("clxh", clxh);
		}
		if(!StringUtil.isNull(djxh)){
			listBuffer.append(" and t.djxh = '"+djxh+"'");
			countBuffer.append(" and t.djxh = '"+djxh+"'");
			request.setAttribute("djxh", djxh);
		}
		listBuffer.append(" order by t.lrsj desc");
		int count = defaultDao.getRepositoryBySQLListSize(countBuffer.toString());
		if(count > 0){
			List list = defaultDao.findHQLByPage(listBuffer.toString(), offset, pagesize);
			eleCtroList = (List<VehtypeElectro>)list;
			//翻译部门名称
			String sql = "select org_id, org_name from v_veh_org_ycs t";
			List deptList = this.defaultDao.findSQL(sql);
			Map<String, String> deptMap = new HashMap<String, String>();
			if(deptList != null && deptList.size() > 0){
				for(int i = 0; i < deptList.size(); i++){
					Object[] objs = (Object[])deptList.get(i);
					deptMap.put(objs[0]+"", objs[1]+"");
				}
				for(VehtypeElectro veh:eleCtroList){
					String deptstr = deptMap.get(veh.getLrrbm());
					veh.setLrrbm(StringUtil.isNull(deptstr)?veh.getLrrbm():deptstr);
				}
			}
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			orgid = getDeptUpid(user.getBmid());
		}
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", count);
		map.put("currentpage", currentPage);
		request.setAttribute("kjbmid", orgid);
		request.setAttribute("rscount", count);
		request.setAttribute("map", map);
		request.setAttribute("eleCtroList", eleCtroList);
		return eleCtroList;
	}
	
	public String editVehtypeElectro(HttpServletRequest request, VehtypeElectro vehElectro) throws Exception{
		if(vehElectro != null){
			//验证车辆型号和电机信号是否重复
			String yzflag = request.getParameter("yz_flag");
			String result = "0";
			if("1".equals(yzflag)){
				String sql = "select count(1) from vehtype_electro t where t.clxh = '"+vehElectro.getClxh()+"' and t.djxh = '"+vehElectro.getDjxh()+"'";
				result = this.defaultDao.findSQL(sql).get(0).toString();
			}
			if("0".equals(result)){
				User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
				vehElectro.setLrr(user.getYgid());
				vehElectro.setLrrxm(user.getYgxm());
				vehElectro.setLrrbm(user.getBmid());
				vehElectro.setLrrkjbm(getDeptUpid(user.getBmid()));
				vehElectro.setLrip(getLoginIp(request));
				if(StringUtil.isNull(vehElectro.getXh())){
					vehElectro.setLrsj(new Date());
					this.defaultDao.addRepository(vehElectro);
					//添加日志
					VehtypeElectroLog log = new VehtypeElectroLog();
					log = (VehtypeElectroLog) getXclog(log, vehElectro);
					log.setCzr(user.getYgid());
					log.setCzlx("I");
					log.setCzsj(new Date());
					this.defaultDao.addRepository(log);
				}else{
					this.defaultDao.updateRepository(vehElectro);
					//添加日志
					VehtypeElectroLog log = new VehtypeElectroLog();
					log = (VehtypeElectroLog) getXclog(log, vehElectro);
					log.setCzr(user.getYgid());
					log.setCzlx("U");
					log.setCzsj(new Date());
					this.defaultDao.addRepository(log);
				}
				return "1";
			}else{
				return "2";
			}
		}else{
			return "0";
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<VehtypeElectro> getVehtypeElectroByCondition(HttpServletRequest request) throws Exception{
		String xh = request.getParameter("xh");
		String hql = "from VehtypeElectro t where t.xh = "+xh;
		hql += " order by lrsj desc";
		List list = this.defaultDao.getRepositories(hql);
		if(list != null && list.size() > 0){
			return (List<VehtypeElectro>)list;
		}
		return null;
	}
	
	public String delVehtypeElectro(HttpServletRequest request, String xh) throws Exception{
		if(!StringUtil.isNull(xh)){
			VehtypeElectro vehElectro = (VehtypeElectro) this.defaultDao.getRepository(xh, VehtypeElectro.class);
			this.defaultDao.deleteRepository(vehElectro);
			//添加日志
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			VehtypeElectroLog log = new VehtypeElectroLog();
			log = (VehtypeElectroLog) getXclog(log, vehElectro);
			log.setCzr(user.getYgid());
			log.setCzlx("D");
			log.setCzsj(new Date());
			this.defaultDao.addRepository(log);
			return "1";
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public String getDeptUpid(String deptid) throws Exception{
		String deptsql = "select org_id from (select t.* from v_veh_org_ycs t start with " +
					"org_id = '" + deptid + "' connect by prior up_org = org_id) " +
					"where up_org = 'C34702A8FED97CBFE040007F0100339B'";
		List deptidlist = this.defaultDao.findSQL(deptsql);
		String deptids = "";
		if(null != deptidlist && deptidlist.size() > 0){
			deptids = deptidlist.get(0).toString();
		}
		return deptids;
	}
	
	public Object getXclog(Object obj1, Object obj2) throws Exception{
		if(obj1 == null){
			return null;
		}
		try {
			Field[] fields = obj2.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				if("synFlag".equals(name) || "tranFlag".equals(name) || "tranDate".equals(name)){
					continue;
				}
				Field field = obj1.getClass().getDeclaredField(name);
				field.set(obj1, fields[i].get(obj2));
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}
	
}
