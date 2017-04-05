package com.ycszh.ssh.service.pdaycimeigl.impl;

import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.collections.map.LinkedMap;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.dao.yanche.TXbycGpsDao;
import com.ycszh.ssh.hbm.pdaycimeigl.TPdaYcimei;
import com.ycszh.ssh.hbm.pdaycimeigl.TPdaYcimeiLog;
import com.ycszh.ssh.service.pdaycimeigl.PdaIMEIglService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.JsonUtil;
import com.ycszh.util.ToolsUtil;

public class PdaIMEIglServiceImpl implements PdaIMEIglService {

	private DefaultDao defaultDao;
	private TXbycGpsDao txbycgpsDao;
	
	/**
	 * 根据部门编号级联获取用户信息
	 */
	public String getUserByDeptId(HttpServletRequest request) throws Exception {
		String org_id = request.getParameter("ssbm");
		StringBuilder json = new StringBuilder();
		List userlist = new ArrayList();
		if(org_id != null && !org_id.equals("")){
			String sql = "select login_id,user_name from v_veh_User_ycs where org_id in ( "+
			"select org_id from v_veh_org_ycs start with org_id in ( '"+org_id+"')"+ 
			"connect by prior org_id = up_org "+
			") and login_id not like 'old%' and user_name not like '%警视通%' order by login_id ";
			userlist=this.defaultDao.findSQL(sql);
			int i = 0;
			if (userlist != null && userlist.size() > 0) {
				for (Object obj : userlist) {
					i++;
					Object[] obj1=(Object[])obj;
					json.append(obj1[0]+":"+obj1[1]);
					if(i<userlist.size()){
						json.append(",");
					}
				}
			} 
		}
		return json.toString();
	}
	
	/**
	 * 获取登记管理列表
	 */
	public void getTPdaYcimei(HttpServletRequest request) throws Exception {

		// 所属部门
		String ssbm = checkStrNullAndReturn(request.getParameter("ssbm"));
		String IMEI = checkStrNullAndReturn(request.getParameter("IMEI"));

		List<TPdaYcimei> tPdaYcimeis = new ArrayList<TPdaYcimei>();
		// 部门信息(车管处下的科级部门)
		Map<String, String> bmMap = getV_veh_org_ycs(null, "C34702A8FED97CBFE040007F0100339B", null);
		// 用户信息(车管处下的科级部门的用户)
		// Map<String, String> yhMap = getV_veh_User_ycs(null, null, null);
		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pagesize = SysConst.PAGESIZE;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;

		String hql = " from TPdaYcimei a where 1=1 ";
		if (!ssbm.equals("")) {
			hql += " and a.bdbmKj like '" + ssbm + "' ";
		}
		if (!IMEI.equals("")) {
			hql += " and a.imei like '%" + IMEI + "%' ";
		}

		hql += " order by a.bdbmKj desc ";

		List<Object> tempList = defaultDao.findHQLByPage(hql, offset, pagesize);
		rscount = defaultDao.getRepositoryByHQLListSize(" select count(*) " + hql);
		//存储验车详细地址
		Map<String,Object> xxdzMap = new HashMap<String, Object>();
		long xh = (currentpage - 1) * pagesize + 1;
		for (Object obj : tempList) {
			if (obj != null) {
				TPdaYcimei ycimei = (TPdaYcimei) obj;
				// 页面显示用,无实际意义
				ycimei.setXh(xh);
				// 绑定的部门
				String tempbm = checkStrNullAndReturn(ycimei.getBdbmKj());
				String gpsid = ycimei.getGpsid();
				if(gpsid!=null&&!gpsid.equals("")){
					//获得验车详细地址
					List list = this.txbycgpsDao.findSQL("select xxdz from t_xbyc_gps where gps_id ='"+gpsid+"'");
					if(list.size()>0){
						Object obj1 = list.get(0);
						String xxdz = obj1.toString();
						xxdzMap.put(ycimei.getId(), xxdz);
					}
				}
				
				
				// 绑定用户
				// String tempyh = checkStrNullAndReturn(ycimei.getBdyh());
				/*
				 * String tempYh = checkStrNullAndReturn(ycimei.getBdyh()); if
				 * (!tempYh.equals("") && tempYh.split("---") != null &&
				 * tempYh.split("---").length > 0) { String[] temp =
				 * tempYh.split("---"); String tempLoginId = temp[0]; String
				 * tempUserName = temp[1]; ycimei.setBdyh(tempUserName); }
				 */
				// 翻译部门
				ycimei.setBdbmKj(checkStrNullAndReturn(bmMap.get(tempbm)));
				// 翻译用户
				// ycimei.setBdyh(checkStrNullAndReturn(yhMap.get(tempyh)));

				tPdaYcimeis.add(ycimei);
				xh++;
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);
		request.setAttribute("xxdzMap", xxdzMap);
		request.setAttribute("ssbm", ssbm);
		request.setAttribute("IMEI", IMEI);

		request.setAttribute("tPdaYcimeis", tPdaYcimeis);
		request.setAttribute("bmMap", bmMap);

	}

	//初始化修改
	@SuppressWarnings("unchecked")
	public void getTPdaYcimeiToChange(HttpServletRequest request) throws Exception {
		String pdaId = checkStrNullAndReturn(request.getParameter("pdaId"));
		
		TPdaYcimei tPdaYcimei = null;
		// 部门信息(车管处下的科级部门)
		Map<String, String> bmMap = new LinkedMap();

		// 用户信息(车管处下的科级部门的用户)
		// Map<String, String> yhMap = new LinkedMap();
		List<String[]> yhList = new ArrayList<String[]>();

		// 用户信息(当前pdaImei用户所在部门下的所有用户)
		List<String[]> currentYhList = new ArrayList<String[]>();
		
		//验车机构信息
		List ycjgList = new ArrayList();

		if (!pdaId.equals("")) {
			Object object = defaultDao.getRepository(pdaId, TPdaYcimei.class);
			if (object != null) {
				tPdaYcimei = (TPdaYcimei) object;
				String orgId = checkStrNullAndReturn(tPdaYcimei.getBdbmKj());
				String userId = checkStrNullAndReturn(tPdaYcimei.getBdyh());
				Object[] userObj = userId.split("---");
				String user_id = userObj[0].toString();
				request.setAttribute("userId", user_id);
				request.setAttribute("gpsid", tPdaYcimei.getGpsid());
				//获取部门信息
				bmMap = getV_veh_org_ycs(null, "C34702A8FED97CBFE040007F0100339B", null);
				// yhMap = getV_veh_User_ycs(null, null, null);
				//获取全部用户信息
				/*yhList = getV_veh_User_ycs_list(null, null, null);*/
				//获取该部门下用户信息
				currentYhList = getV_veh_User_ycs_list(null, null, orgId);
				//获取全部机构名称
				ycjgList = txbycgpsDao.findSQL("select gps_id,jgmc from t_xbyc_gps group by gps_id,jgmc");
				
				// 翻译部门
				// tPdaYcimei.setBdbmKj(checkStrNullAndReturn(bmMap.get(orgId)));
				// 翻译用户
				// tPdaYcimei.setBdyh(checkStrNullAndReturn(yhMap.get(userId)));
			}

		}
		request.setAttribute("bmMap", bmMap);
		request.setAttribute("currentYhList", currentYhList);
		request.setAttribute("tPdaYcimei", tPdaYcimei);
		request.setAttribute("ycjgList", ycjgList);
	}

	/**
	 * 初始化要添加页面的数据
	 * 
	 * @param request
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void getTPdaYcimeiToAdd(HttpServletRequest request) throws Exception {
		// 部门信息(车管处下的科级部门)
		Map<String, String> bmMap = new LinkedMap();
		// 用户信息(车管处下的科级部门的用户)
		List<String[]> yhList = new ArrayList<String[]>();
		//获取部门信息
		bmMap = getV_veh_org_ycs(null, "C34702A8FED97CBFE040007F0100339B", null);
		//获取全部机构名称
		List ycjgList = txbycgpsDao.findSQL("select gps_id,jgmc from t_xbyc_gps group by gps_id,jgmc");
		request.setAttribute("bmMap", bmMap);
		request.setAttribute("yhList", yhList);
		request.setAttribute("ycjgList", ycjgList);
	}

	/**
	 * 修改数据
	 * 
	 * @param request
	 * @param tPdaYcimei
	 * @return
	 * @throws Exception
	 */
	public String updateTPdaYcimei(HttpServletRequest request, TPdaYcimei tPdaYcimei) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			if (tPdaYcimei != null) {
				String id = checkStrNullAndReturn(tPdaYcimei.getId());
				if (!id.equals("")) {
					Object obj = defaultDao.getRepository(id, TPdaYcimei.class);
					if (obj != null) {
						TPdaYcimei tempTPdaYcimei = (TPdaYcimei) obj;

						String IMEI = checkStrNullAndReturn(tPdaYcimei.getImei());
						String bdbmkj = checkStrNullAndReturn(tPdaYcimei.getBdbmKj());
						String bdyh = checkStrNullAndReturn(tPdaYcimei.getBdyh());
						String bz = checkStrNullAndReturn(tPdaYcimei.getBz());
						bz = URLDecoder.decode(bz, "UTF-8");
						String zt = checkStrNullAndReturn(tPdaYcimei.getZt());
						String ip = checkStrNullAndReturn(ToolsUtil.getIpAddr(request));
						String mac = checkStrNullAndReturn(request.getParameter("mac"));
						String zyptbj = checkStrNullAndReturn(tPdaYcimei.getZyptbj());
						String gpsid = checkStrNullAndReturn(tPdaYcimei.getGpsid());
						String dwbj = checkStrNullAndReturn(tPdaYcimei.getDwbj());
						String sjyzbj = checkStrNullAndReturn(tPdaYcimei.getSjyzbj());
						boolean isOK = true;

						if (IMEI.equals("")) {
							result = "-修改失败:IMEI号为空";
							isOK = false;
						} else {
							if (!tempTPdaYcimei.getImei().equals(IMEI)) {
								result = getIsRepeatIMEI(IMEI);
								if (result.indexOf("-") == 0) {
									isOK = false;
								}
							}
						}
						if (isOK && bdbmkj.equals("")) {
							result = "-修改失败:所属部门为空";
							isOK = false;
						}
						
						if (isOK && zt.equals("")) {
							result = "-修改失败:IMEI绑定状态为空";
							isOK = false;
						}
						if (isOK && zyptbj.equals("")) {
							result = "-修改失败:IMEI是否对接为空";
							isOK = false;
						}
						if (isOK) {
							tempTPdaYcimei.setImei(IMEI);
							tempTPdaYcimei.setBdbmKj(bdbmkj);
							tempTPdaYcimei.setBdyh(bdyh);
							tempTPdaYcimei.setBz(bz);
							tempTPdaYcimei.setZt(zt);
							tempTPdaYcimei.setZyptbj(zyptbj);
							tempTPdaYcimei.setGpsid(gpsid);
							tempTPdaYcimei.setDwbj(dwbj);
							tempTPdaYcimei.setSjyzbj(sjyzbj);
							// 修改主表数据
							try {
								TPdaYcimei tPdaYcimei1 = (TPdaYcimei) defaultDao.updateRepository(tempTPdaYcimei);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							List<String> notUpdate = new ArrayList<String>();
							notUpdate.add("czr");
							notUpdate.add("czrxm");
							notUpdate.add("czrbm");
							notUpdate.add("czrbmKj");
							notUpdate.add("czrq");
							notUpdate.add("czip");
							notUpdate.add("czmac");
							notUpdate.add("xh");

							TPdaYcimeiLog log = new TPdaYcimeiLog();
							log.setCzr(user.getName());
							log.setCzrxm(user.getYgxm());
							log.setCzrbm(user.getBmid());
							log.setCzrq(new Date());
							log.setCzip(ip);
							log.setCzmac(mac);
							log.setCznr("修改PDA验车IMEI码数据");

							// 添加日志
							try {
								defaultDao.addRepositoryLog(log, tempTPdaYcimei, notUpdate);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					} else {
						result = "-修改失败:无法获取id(" + id + ")的IMEI数据,无法修改";
					}
				} else {
					result = "-修改失败:id为空,无法获取要修改的数据";
				}
			} else {
				result = "-修改失败:无法获取要修改的数据";
			}
		} else {
			result = "-修改失败:用户未登录或登录已过期";
		}
		return result;
	}

	/**
	 * 添加新数据
	 * 
	 * @param request
	 * @param tPdaYcimei
	 * @return
	 * @throws Exception
	 */
	public String addTPdaYcimei(HttpServletRequest request, TPdaYcimei tPdaYcimei) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			if (tPdaYcimei != null) {
				
				String IMEI = checkStrNullAndReturn(tPdaYcimei.getImei());
				String bdbmkj = checkStrNullAndReturn(tPdaYcimei.getBdbmKj());
				String bz = checkStrNullAndReturn(tPdaYcimei.getBz());
				bz = URLDecoder.decode(bz, "UTF-8");
				String zt = checkStrNullAndReturn(tPdaYcimei.getZt());
				String ip = checkStrNullAndReturn(ToolsUtil.getIpAddr(request));
				String mac = checkStrNullAndReturn(request.getParameter("mac"));
				String zyptbj = checkStrNullAndReturn(tPdaYcimei.getZyptbj());
				boolean isOK = true;

				if (IMEI.equals("")) {
					result = "-添加失败:IMEI号为空";
					isOK = false;
				} else {
					result = getIsRepeatIMEI(IMEI);
					if (result.indexOf("-") == 0) {
						isOK = false;
					}
				}
				if (isOK && bdbmkj.equals("")) {
					result = "-添加失败:所属部门为空";
					isOK = false;
				}

				if (isOK && zt.equals("")) {
					result = "-添加失败:IMEI绑定状态为空";
					isOK = false;
				}
				if (isOK) {

					Date currentDate = new Date();

					tPdaYcimei.setLrsj(currentDate);
					tPdaYcimei.setCzr(user.getName());
					tPdaYcimei.setCzrxm(user.getYgxm());
					tPdaYcimei.setCzrbm(user.getBmid());
					tPdaYcimei.setCzrq(currentDate);
					tPdaYcimei.setCzip(ip);
					tPdaYcimei.setCzmac(mac);
					tPdaYcimei.setCzmac(zyptbj);
					// 修改主表数据
					defaultDao.addRepository(tPdaYcimei);

					List<String> noUpdate = new ArrayList<String>();
					noUpdate.add("xh");

					TPdaYcimeiLog log = new TPdaYcimeiLog();
					log.setCznr("添加PDA验车IMEI码数据");

					// 添加日志
					defaultDao.addRepositoryLog(log, tPdaYcimei, noUpdate);
				}
			} else {
				result = "-添加失败:要添加的数据为空";
			}
		} else {
			result = "-添加失败:用户未登录或登录已过期";
		}
		return result;
	}

	/**
	 * 删除数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String deleteTPdaYcimei(HttpServletRequest request) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		String id = checkStrNullAndReturn(request.getParameter("id"));

		if (user != null) {
			if (!id.equals("")) {
				Object obj = defaultDao.getRepository(id, TPdaYcimei.class);
				if (obj != null) {
					TPdaYcimei tempTPdaYcimei = (TPdaYcimei) obj;

					String ip = checkStrNullAndReturn(ToolsUtil.getIpAddr(request));
					String mac = checkStrNullAndReturn(request.getParameter("mac"));

					List<String> notUpdate = new ArrayList<String>();
					notUpdate.add("czr");
					notUpdate.add("czrxm");
					notUpdate.add("czrbm");
					notUpdate.add("czrbmKj");
					notUpdate.add("czrq");
					notUpdate.add("czip");
					notUpdate.add("czmac");
					notUpdate.add("xh");

					TPdaYcimeiLog log = new TPdaYcimeiLog();
					log.setCzr(user.getName());
					log.setCzrxm(user.getYgxm());
					log.setCzrbm(user.getBmid());
					log.setCzrq(new Date());
					log.setCzip(ip);
					log.setCzmac(mac);
					log.setCznr("删除PDA验车IMEI码数据");

					// 添加日志
					defaultDao.addRepositoryLog(log, tempTPdaYcimei, notUpdate);

					// 删除数据
					defaultDao.deleteRepository(tempTPdaYcimei);

				} else {
					result = "-删除失败:无法获取id(" + id + ")的IMEI数据";
				}
			} else {
				result = "-删除失败:id为空,无法获取要删除的数据";
			}
		} else {
			result = "-删除失败:用户未登录或登录已过期";
		}
		return result;
	}

	/**
	 * 检查是否有重复IMEI数据
	 * 
	 * @param IMEI
	 * @return
	 * @throws Exception
	 */
	private String getIsRepeatIMEI(String IMEI) throws Exception {
		String result = "";

		IMEI = checkStrNullAndReturn(IMEI);
		if (!IMEI.equals("")) {

			String hql = " select count(*) from TPdaYcimei a where a.imei='" + IMEI + "' ";
			int count = defaultDao.getRepositoryByHQLListSize(hql);
			if (count > 0) {
				result = "-已存在相同IMEI";
			}
		} else {
			result = "-IMEI号为空";
		}

		return result;
	}

	/**
	 * 获取部门信息
	 * 
	 * @param orgId
	 * @param upOrg
	 * @param orgName
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getV_veh_org_ycs(String orgId, String upOrg, String orgName) throws Exception {
		
		Map<String, String> resultMap = new LinkedMap();
		orgId = checkStrNullAndReturn(orgId);
		upOrg = checkStrNullAndReturn(upOrg);
		orgName = checkStrNullAndReturn(orgName);

		String sql = " select a.ORG_Id,a.Up_Org,a.Org_Name from v_veh_org_ycs a where 1=1 ";
		if (!orgId.equals("")) {
			sql += " and  a.ORG_Id='" + orgId + "' ";
		}
		if (!upOrg.equals("")) {
			sql += " and  a.Up_Org='" + upOrg + "' ";
		}
		if (!orgName.equals("")) {
			sql += " and  a.Org_Name='" + orgName + "' ";
		}
		sql += " order by a.Org_Name asc ";
		List<Object> tempList = defaultDao.findSQL(sql);
		for (Object obj : tempList) {
			if (obj != null) {
				Object[] tempObj = (Object[]) obj;
				String tempOrdId = checkStrNullAndReturn(tempObj[0]);
				// String tempUpOrg = checkStrNullAndReturn(tempObj[1]);
				String tempOrgName = checkStrNullAndReturn(tempObj[2]);
				if (!tempOrdId.equals("")) {
					resultMap.put(tempOrdId, tempOrgName);
				}
			}
		}

		return resultMap;
	}

	/**
	 * 查询用户信息(车管处下的科级部门用户)
	 * 
	 * @param userId
	 * @param userName
	 * @param orgId
	 *            (所属部门id)
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getV_veh_User_ycs(String userId, String userName, String orgId) throws Exception {

		Map<String, String> resultMap = new LinkedMap();
		userId = checkStrNullAndReturn(userId);
		userName = checkStrNullAndReturn(userName);
		orgId = checkStrNullAndReturn(orgId);

		StringBuffer sql = new StringBuffer();
		sql.append(" select a.User_Id,a.User_Name,a.Org_Id from v_veh_User_ycs a ");
		sql.append(" where exists ( ");
		sql.append(" select 1 from v_veh_org_ycs b where b.Up_Org = 'C34702A8FED97CBFE040007F0100339B' and a.ORG_Id = b.ORG_Id ");
		sql.append(" ) ");

		if (!userId.equals("")) {
			sql.append(" and a.User_Id='" + userId + "' ");
		}
		if (!userName.equals("")) {
			sql.append(" and a.User_Name='" + userId + "' ");
		}
		if (!orgId.equals("")) {
			sql.append(" and a.Org_Id='" + orgId + "' ");
		}

		sql.append(" order by a.User_Id asc ");

		List<Object> tempList = defaultDao.findSQL(sql.toString());
		for (Object obj : tempList) {
			if (obj != null) {
				Object[] tempObj = (Object[]) obj;
				String tempUserId = checkStrNullAndReturn(tempObj[0]);
				String tempUserName = checkStrNullAndReturn(tempObj[1]);
				if (!tempUserId.equals("")) {
					resultMap.put(tempUserId, tempUserName);
				}
			}
		}

		return resultMap;
	}
	
	/**
	 * 获取用户信息
	 * @param userId
	 * @param userName
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getV_veh_User_ycs_list(String userId, String userName, String orgId) throws Exception {

		List<String[]> resultList = new ArrayList<String[]>();
		userId = checkStrNullAndReturn(userId);
		userName = checkStrNullAndReturn(userName);
		orgId = checkStrNullAndReturn(orgId);
		if(orgId!=null && !orgId.equals("")){
			String sql = "select login_id,user_name from v_veh_User_ycs where org_id in ( "+
			"select org_id from v_veh_org_ycs start with org_id in ( '"+orgId+"')"+ 
			"connect by prior org_id = up_org "+
			") and login_id not like 'old%' and user_name not like '%警视通%' order by login_id";
			resultList=this.defaultDao.findSQL(sql);
			
		}
		return resultList;
		/*StringBuffer sql = new StringBuffer();
		sql.append(" select a.User_Id,a.User_Name,a.Org_Id,a.login_id from v_veh_User_ycs a ");
		sql.append(" where exists ( ");
		sql.append(" select 1 from v_veh_org_ycs b where b.Up_Org = 'C34702A8FED97CBFE040007F0100339B' and a.ORG_Id = b.ORG_Id ");
		sql.append(" ) ");

		if (!userId.equals("")) {
			sql.append(" and a.User_Id='" + userId + "' ");
		}
		if (!userName.equals("")) {
			sql.append(" and a.User_Name='" + userId + "' ");
		}
		if (!orgId.equals("")) {
			sql.append(" and a.Org_Id='" + orgId + "' ");
		}

		sql.append(" order by a.User_Id asc ");

		List<Object> tempList = defaultDao.findSQL(sql.toString());
		for (Object obj : tempList) {
			if (obj != null) {
				Object[] tempObj = (Object[]) obj;
				String tempUserId = checkStrNullAndReturn(tempObj[0]);
				String tempUserName = checkStrNullAndReturn(tempObj[1]);
				String tempOrgId = checkStrNullAndReturn(tempObj[2]);
				String tempLoginId = checkStrNullAndReturn(tempObj[3]);
				if (!tempUserId.equals("")) {
					String[] tempStr = { tempUserId, tempUserName, tempOrgId, tempLoginId + "---" + tempUserName };
					resultList.add(tempStr);
				}
			}
		}*/
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<TPdaYcimei> getExcel(String ssbm, String IMEI) throws Exception {

		List<TPdaYcimei> tPdaYcimeis = new ArrayList<TPdaYcimei>();
		// 部门信息(车管处下的科级部门)
		Map<String, String> bmMap = getV_veh_org_ycs(null, "C34702A8FED97CBFE040007F0100339B", null);

		String hql = " from TPdaYcimei a where 1=1 ";
		if (!ssbm.equals("")) {
			hql += " and a.bdbmKj like '" + ssbm + "' ";
		}
		if (!IMEI.equals("")) {
			hql += " and a.imei like '%" + IMEI + "%' ";
		}

		hql += " order by a.bdbmKj desc ";

		List<Object> tempList = defaultDao.getRepositories(hql);

		for (Object obj : tempList) {
			if (obj != null) {
				TPdaYcimei ycimei = (TPdaYcimei) obj;
				// 绑定的部门
				String tempbm = checkStrNullAndReturn(ycimei.getBdbmKj());
				// 翻译部门
				ycimei.setBdbmKj(checkStrNullAndReturn(bmMap.get(tempbm)));
				tPdaYcimeis.add(ycimei);
			}
		}
		return tPdaYcimeis;
	}

	/**
	 * 导出excel
	 * 
	 * @param ssbm
	 * @param IMEI
	 * @return
	 * @throws Exception
	 */
	public String getOutExcel(HttpServletResponse response, HttpServletRequest request, String ssbm, String IMEI) throws Exception {
		String result = "";

		List<TPdaYcimei> tPdaYcimeis = getExcel(ssbm, IMEI);
		try {
			if (tPdaYcimeis != null && tPdaYcimeis.size() > 0) {
				// Excel工作簿
				WritableWorkbook wwb = null;
				// Excel sheet
				WritableSheet ws = null;
				WritableCellFormat format = null;
				OutputStream out = null;
				try {
					out = response.getOutputStream();

					String fileNameTemp = "IMEI绑定数据";

					if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
						fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"), "ISO8859-1");
					}
					// IE
					else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
						fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
					} else {
						fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
					}
					response.setContentType("application/x-excel");
					response.setHeader("Content-disposition", "attachment; filename=" + fileNameTemp + ".xls");
					wwb = Workbook.createWorkbook(out);
					// 初始化表格
					format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT, NumberFormats.TEXT);
					WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD, false);
					format = new WritableCellFormat(wtf);

					// 创建表格标题
					ws = wwb.createSheet("IMEI绑定数据", 0);

					// 设置宽度
					ws.setColumnView(0, 20);
					ws.setColumnView(1, 20);
					ws.setColumnView(2, 20);
					ws.setColumnView(3, 20);
					ws.setColumnView(4, 20);
					ws.setColumnView(5, 20);
					ws.setColumnView(6, 20);

					// 设置列名
					ws.addCell(new Label(0, 0, "序号", format));
					ws.addCell(new Label(1, 0, "所属部门", format));
					ws.addCell(new Label(2, 0, "所属用户", format));
					ws.addCell(new Label(3, 0, "IMEI号", format));
					ws.addCell(new Label(4, 0, "备注", format));
					ws.addCell(new Label(5, 0, "录入时间", format));
					ws.addCell(new Label(6, 0, "状态", format));

					format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT, NumberFormats.TEXT);

					// 预约日期分组数据
					for (int i = 0; i < tPdaYcimeis.size(); i++) {
						TPdaYcimei tPdaYcimei = tPdaYcimeis.get(i);
						ws.addCell(new Label(0, i + 1, (i + 1) + "", format));
						ws.addCell(new Label(1, i + 1, tPdaYcimei.getBdbmKj(), format));
						ws.addCell(new Label(2, i + 1, tPdaYcimei.getBdyh(), format));
						ws.addCell(new Label(3, i + 1, tPdaYcimei.getImei(), format));
						ws.addCell(new Label(4, i + 1, tPdaYcimei.getBz(), format));
						String tempLrsj = DateUtil.date2String(tPdaYcimei.getLrsj(), "yyyy-MM-dd HH:mm:ss");
						ws.addCell(new Label(5, i + 1, tempLrsj, format));

						String tempZt = checkStrNullAndReturn(tPdaYcimei.getZt());
						if (tempZt.equals("1")) {
							ws.addCell(new Label(6, i + 1, "正常", format));
						} else {
							ws.addCell(new Label(6, i + 1, "停用", format));
						}
					}
					wwb.write();
				} catch (Exception e) {
					result = "-" + e.getMessage();
				} finally {
					try {
						if (wwb != null) {
							wwb.close();
						}
						if (out != null) {
							out.close();
						}

					} catch (Exception e) {
						result = "-" + e.getMessage();
					}
				}

			} else {
				result = "-查无数据";
			}
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}

		result = "SUCCESS";

		return result;
	}

	public String checkStrNullAndReturn(Object str) {
		String returnStr = "";
		try {
			returnStr = str == null ? "" : str.toString().trim();
		} catch (Exception e) {
			return "";
		}
		return returnStr;
	}

	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public TXbycGpsDao getTxbycgpsDao() {
		return txbycgpsDao;
	}

	public void setTxbycgpsDao(TXbycGpsDao txbycgpsDao) {
		this.txbycgpsDao = txbycgpsDao;
	}
	


}
