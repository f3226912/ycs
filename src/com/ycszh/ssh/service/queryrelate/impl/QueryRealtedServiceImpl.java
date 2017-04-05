package com.ycszh.ssh.service.queryrelate.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.service.queryrelate.QueryRealtedService;
import com.ycszh.util.DateUtil;

public class QueryRealtedServiceImpl implements QueryRealtedService {

	private DefaultDao defaultDao;

	public List<Object> queryResult1(HttpServletRequest request)
			throws Exception {
		String lsh = request.getParameter("lsh") == null ? "" : request
				.getParameter("lsh").trim();
		String startTime = request.getParameter("startTime") == null ? ""
				: request.getParameter("startTime").trim();
		String endTime = request.getParameter("endTime") == null ? "" : request
				.getParameter("endTime").trim();

		// 默认开始和结束时间
		if (request.getParameter("startTime") == null
				&& request.getParameter("endTime") == null) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			startTime = DateUtil.date2String(c.getTime(), "yyyy-MM-dd")
					+ " 00:00:00";
			endTime = DateUtil.date2String(c.getTime(), "yyyy-MM-dd")
					+ " 23:59:59";
		}

		// 在线驾驶证补证换证
		String sql = "select WWLSH,HBLX,SFZMHM,JSZHM,XM,DJZSDZ,YDDH,trim(SFTJHG),SJRXM,SJRDZ,SJRSJ,to_char(WSLRSJ,'yyyy-MM-dd HH24:mi:ss') from xwebfile.t_drv_lice_chan_app where 1=1 ";
		if (!startTime.equals("") && !endTime.equals("")) {
			sql += "and wslrsj between to_date('" + startTime
					+ "', 'yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime
					+ "', 'yyyy-MM-dd HH24:mi:ss')";
		}
		if (!lsh.equals("")) {
			sql += " and WWLSH like '%" + lsh + "%' ";
		}
		sql += " order by wslrsj desc ";

		String curi = request.getRequestURI();
		int pageSize = 10;
		int currentPage = new Integer(
				request.getParameter("currentpage") == null ? "1" : request
						.getParameter("currentpage"));
		int offset = pageSize * (currentPage - 1);

		List<Object> resultList = defaultDao.findSQLByPage(sql, offset,
				pageSize);
		String sqlSize = "select count(1) "
				+ sql.substring(sql.indexOf("from"));
		Integer size = defaultDao.getRepositoryBySQLListSize(sqlSize);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uri", curi);
		map.put("pageSize", pageSize);
		map.put("rscount", size);
		map.put("currentPage", currentPage);

		request.setAttribute("map", map);
		request.setAttribute("lsh", lsh);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("resultList", resultList);

		return resultList;
	}

	public List<Object> queryResult2(HttpServletRequest request)
			throws Exception {
		String lsh = request.getParameter("lsh") == null ? "" : request
				.getParameter("lsh").trim();
		String startTime = request.getParameter("startTime") == null ? ""
				: request.getParameter("startTime").trim();
		String endTime = request.getParameter("endTime") == null ? "" : request
				.getParameter("endTime").trim();

		// 默认开始和结束时间
		if (request.getParameter("startTime") == null
				&& request.getParameter("endTime") == null) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			startTime = DateUtil.date2String(c.getTime(), "yyyy-MM-dd")
					+ " 00:00:00";
			endTime = DateUtil.date2String(c.getTime(), "yyyy-MM-dd")
					+ " 23:59:59";
		}

		// 驾驶人联系方式变更
		String sql = "select SERIAL_NO,NAME,ARCHIVES_NO,DRAW_DATE,IDENTITY_KIND,IDENTITY_NO,EMAIL,MOBILE_PHONE,ADDRESS,to_char(LAST_HANDLE_TIME,'yyyy-MM-dd HH24:mi:ss'),LAST_HANDLER_DESC,to_char(load_time,'yyyy-MM-dd HH24:mi:ss') from xwebfile.t_contact_change_licence where 1=1";

		if (!startTime.equals("") && !endTime.equals("")) {
			sql += "and load_time between to_date('" + startTime
					+ "', 'yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime
					+ "', 'yyyy-MM-dd HH24:mi:ss') ";
		}

		if (!lsh.equals("")) {
			sql += " and SERIAL_NO like '%" + lsh + "%' ";
		}
		sql += " order by load_time desc ";

		String curi = request.getRequestURI();
		int pageSize = 10;
		int currentPage = new Integer(
				request.getParameter("currentpage") == null ? "1" : request
						.getParameter("currentpage"));
		int offset = pageSize * (currentPage - 1);

		List<Object> resultList = defaultDao.findSQLByPage(sql, offset,
				pageSize);
		String sqlSize = "select count(1) "
				+ sql.substring(sql.indexOf("from"));
		Integer size = defaultDao.getRepositoryBySQLListSize(sqlSize);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uri", curi);
		map.put("pageSize", pageSize);
		map.put("rscount", size);
		map.put("currentPage", currentPage);

		request.setAttribute("map", map);
		request.setAttribute("lsh", lsh);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("resultList", resultList);

		return resultList;
	}

	public List<Object> queryResult3(HttpServletRequest request)
			throws Exception {
		String lsh = request.getParameter("lsh") == null ? "" : request
				.getParameter("lsh").trim();
		String startTime = request.getParameter("startTime") == null ? ""
				: request.getParameter("startTime").trim();
		String endTime = request.getParameter("endTime") == null ? "" : request
				.getParameter("endTime").trim();

		// 默认开始和结束时间
		if (request.getParameter("startTime") == null
				&& request.getParameter("endTime") == null) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			startTime = DateUtil.date2String(c.getTime(), "yyyy-MM-dd")
					+ " 00:00:00";
			endTime = DateUtil.date2String(c.getTime(), "yyyy-MM-dd")
					+ " 23:59:59";
		}

		// 机动车联系方式变更
		String sql = "select WWLSH,b.dmsm1,CPHM,SFZMHM,LXDZ,LXDH,SYR,ZCDJRQ,to_char(TJSJ,'yyyy-MM-dd HH24:mi:ss')   from xwebfile.t_jdclxfsbg a left join es_veh_code b on a.cllx=b.dmz where 1=1";

		if (!startTime.equals("") && !endTime.equals("")) {
			sql += "and a.tjsj between to_date('" + startTime
					+ "', 'yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime
					+ "', 'yyyy-MM-dd HH24:mi:ss') and b.dmlb='7' ";
		}

		if (!lsh.equals("")) {
			sql += " and a.WWLSH like '%" + lsh + "%' ";
		}
		sql += " order by tjsj desc ";

		String curi = request.getRequestURI();
		int pageSize = 10;
		int currentPage = new Integer(
				request.getParameter("currentpage") == null ? "1" : request
						.getParameter("currentpage"));
		int offset = pageSize * (currentPage - 1);

		List<Object> resultList = defaultDao.findSQLByPage(sql, offset,
				pageSize);
		String sqlSize = "select count(1) "
				+ sql.substring(sql.indexOf("from"));
		Integer size = defaultDao.getRepositoryBySQLListSize(sqlSize);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uri", curi);
		map.put("pageSize", pageSize);
		map.put("rscount", size);
		map.put("currentPage", currentPage);

		request.setAttribute("map", map);
		request.setAttribute("lsh", lsh);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("resultList", resultList);

		return resultList;
	}

	public List<Object> queryResult4(HttpServletRequest request)
			throws Exception {
		String lsh = request.getParameter("lsh") == null ? "" : request
				.getParameter("lsh").trim();
		String startTime = request.getParameter("startTime") == null ? ""
				: request.getParameter("startTime").trim();
		String endTime = request.getParameter("endTime") == null ? "" : request
				.getParameter("endTime").trim();

		// 默认开始和结束时间
		if (request.getParameter("startTime") == null
				&& request.getParameter("endTime") == null) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			startTime = DateUtil.date2String(c.getTime(), "yyyy-MM-dd")
					+ " 00:00:00";
			endTime = DateUtil.date2String(c.getTime(), "yyyy-MM-dd")
					+ " 23:59:59";
		}

		// 补换机动车行驶证
		String sql = "select WWLSH,trim(YWLX),JDCSYR,SFZMHM,HPHM,b.dmsm1,ZSXXDZ,YDDH,to_char(WWLRSJ,'yyyy-MM-dd HH24:mi:ss'),SJRXM,SJRDZ,SJRSJ from xwebfile.t_jdc_chan_app a left join es_veh_code b on a.hpzl=b.dmz where 1=1";
		if (!startTime.equals("") && !endTime.equals("")) {
			sql += "and wwlrsj between to_date('" + startTime
					+ "', 'yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime
					+ "', 'yyyy-MM-dd HH24:mi:ss')"
					+ " and a.ywlx = '1' and b.dmlb = '7' ";
		}
		if (!lsh.equals("")) {
			sql += " and a.WWLSH like '%" + lsh + "%' ";
		}
		sql += " order by wwlrsj desc ";

		String curi = request.getRequestURI();
		int pageSize = 12;
		int currentPage = new Integer(
				request.getParameter("currentpage") == null ? "1" : request
						.getParameter("currentpage"));
		int offset = pageSize * (currentPage - 1);

		List<Object> resultList = defaultDao.findSQLByPage(sql, offset,
				pageSize);
		String sqlSize = "select count(1) "
				+ sql.substring(sql.indexOf("from"));
		Integer size = defaultDao.getRepositoryBySQLListSize(sqlSize);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uri", curi);
		map.put("pageSize", pageSize);
		map.put("rscount", size);
		map.put("currentPage", currentPage);

		request.setAttribute("map", map);
		request.setAttribute("lsh", lsh);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("resultList", resultList);

		return resultList;
	}

	public List<Object> queryResult5(HttpServletRequest request)
			throws Exception {
		String lsh = request.getParameter("lsh") == null ? "" : request
				.getParameter("lsh").trim();
		String startTime = request.getParameter("startTime") == null ? ""
				: request.getParameter("startTime").trim();
		String endTime = request.getParameter("endTime") == null ? "" : request
				.getParameter("endTime").trim();

		// 默认开始和结束时间
		if (request.getParameter("startTime") == null
				&& request.getParameter("endTime") == null) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			startTime = DateUtil.date2String(c.getTime(), "yyyy-MM-dd")
					+ " 00:00:00";
			endTime = DateUtil.date2String(c.getTime(), "yyyy-MM-dd")
					+ " 23:59:59";
		}

		// 补换检验合格标志
		String sql = "select WWLSH,trim(YWLX),JDCSYR,SFZMHM,HPHM,b.dmsm1,ZSXXDZ,YDDH,to_char(WWLRSJ,'yyyy-MM-dd HH24:mi:ss'),SJRXM,SJRDZ,SJRSJ from xwebfile.t_jdc_chan_app a left join es_veh_code b on a.hpzl=b.dmz where 1=1";

		if (!startTime.equals("") && !endTime.equals("")) {
			sql += "and wwlrsj between to_date('" + startTime
					+ "', 'yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime
					+ "', 'yyyy-MM-dd HH24:mi:ss')"
					+ " and a.ywlx = '2' and b.dmlb = '7' ";
		}

		if (!lsh.equals("")) {
			sql += " and a.WWLSH like '%" + lsh + "%' ";
		}
		sql += " order by wwlrsj desc ";

		String curi = request.getRequestURI();
		int pageSize = 12;
		int currentPage = new Integer(
				request.getParameter("currentpage") == null ? "1" : request
						.getParameter("currentpage"));
		int offset = pageSize * (currentPage - 1);

		List<Object> resultList = defaultDao.findSQLByPage(sql, offset,
				pageSize);
		String sqlSize = "select count(1) "
				+ sql.substring(sql.indexOf("from"));
		Integer size = defaultDao.getRepositoryBySQLListSize(sqlSize);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uri", curi);
		map.put("pageSize", pageSize);
		map.put("rscount", size);
		map.put("currentPage", currentPage);

		request.setAttribute("map", map);
		request.setAttribute("lsh", lsh);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("resultList", resultList);

		return resultList;
	}

	public List<Object> queryResult6(HttpServletRequest request)
			throws Exception {
		String lsh = request.getParameter("lsh") == null ? "" : request
				.getParameter("lsh").trim();
		String startTime = request.getParameter("startTime") == null ? ""
				: request.getParameter("startTime").trim();
		String endTime = request.getParameter("endTime") == null ? "" : request
				.getParameter("endTime").trim();

		// 默认开始和结束时间
		if (request.getParameter("startTime") == null
				&& request.getParameter("endTime") == null) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			startTime = DateUtil.date2String(c.getTime(), "yyyy-MM-dd")
					+ " 00:00:00";
			endTime = DateUtil.date2String(c.getTime(), "yyyy-MM-dd")
					+ " 23:59:59";
		}

		// 机动车业务外网预约
		String sql = "select trim(a.hphm),b.dmsm1,CLPP1,CLXH,CLSBDH,SFZMHM,SYR,ZSXXDZ,SJHM,to_char(CHRQ,'yyyy-MM-dd HH:mi:ss'),cast(a.LSH as varchar(20)),c.vsname from vehoffice.VEHICLE_TEMP_MID_OUT  a left join es_veh_code b on a.hpzl=b.dmz left join t_drv_userinfo c on a.chid=c.vsid where 1=1";

		if (!startTime.equals("") && !endTime.equals("")) {
			sql += " and chrq between to_date('" + startTime
					+ "', 'yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime
					+ "', 'yyyy-MM-dd HH24:mi:ss')" + " and b.dmlb='7'  ";
		}

		if (!lsh.equals("")) {
			sql += " and a.LSH like '%" + lsh + "%' ";
		}
		sql += " order by CHRQ desc ";

		String curi = request.getRequestURI();
		int pageSize = 12;
		int currentPage = new Integer(
				request.getParameter("currentpage") == null ? "1" : request
						.getParameter("currentpage"));
		int offset = pageSize * (currentPage - 1);

		List resultList = defaultDao.findSQLByPage(sql, offset, pageSize);
		String sqlSize = "select count(1) "
				+ sql.substring(sql.indexOf("from"));
		Integer size = defaultDao.getRepositoryBySQLListSize(sqlSize);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uri", curi);
		map.put("pageSize", pageSize);
		map.put("rscount", size);
		map.put("currentPage", currentPage);

		request.setAttribute("map", map);
		request.setAttribute("lsh", lsh);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("resultList", resultList);

		return resultList;
	}

	public String showDetailInfo(HttpServletRequest request) throws Exception {
		String showType = request.getParameter("showType") == null ? ""
				: request.getParameter("showType").trim();
		String lsh = request.getParameter("lsh") == null ? "" : request
				.getParameter("lsh").trim();
		String sql = "";
		String returnPage = "";
		Object detailInfo = null;
		switch (showType.toCharArray()[0]) {
		case '1':
			sql = "select WWLSH,HBLX,SFZMHM,JSZHM,XM,DJZSDZ,GDDH,YDDH,SJRXM,SJRDZ,SJRSJ,to_char(WSLRSJ,'yyyy-MM-dd HH24:mi:ss'),TJYYMC,to_char(TJRQ,'yyyy-MM-dd HH24:mi:ss') from xwebfile.t_drv_lice_chan_app where WWLSH='"
					+ lsh + "' ";
			returnPage = "detailResult1";
			break;
		case '2':
			sql = "select * from xwebfile.t_contact_change_licence where SERIAL_NO='"
					+ lsh + "' ";
			returnPage = "detailResult2";
			break;
		case '3':
			sql = "select * from xwebfile.t_jdclxfsbg a left join es_veh_code b on a.cllx=b.dmz where a.WWLSH='"
					+ lsh + "' ";
			returnPage = "detailResult3";
			break;
		case '4':
			sql = "select * from xwebfile.t_jdc_chan_app a left join es_veh_code b on a.hpzl=b.dmz where a.WWLSH='"
					+ lsh + "' and a.ywlx = '1' and b.dmlb = '7' ";
			returnPage = "detailResult4";
			break;
		case '5':
			sql = "select * from xwebfile.t_jdc_chan_app a left join es_veh_code b on a.hpzl=b.dmz where a.WWLSH='"
					+ lsh + "' and a.ywlx = '2' and b.dmlb = '7' ";
			returnPage = "detailResult5";
			break;
		case '6':
			sql = "select * from vehoffice.VEHICLE_TEMP_MID_OUT  a left join es_veh_code b on a.hpzl=b.dmz where a.LSH='"
					+ lsh + "' and b.dmlb='7' ";
			returnPage = "detailResult6";
			break;
		}
		List tempList = defaultDao.findSQL(sql);
		if (tempList != null && tempList.size() > 0) {
			detailInfo = tempList.get(0);
		}
		request.setAttribute("detailInfo", detailInfo);
		return returnPage;
	}

	public void exportToExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WritableWorkbook wwb = null;
		WritableSheet ws = null;
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			Object resultNumber = request.getAttribute("resultNumber");

			String fileNameTemp = "查询结果";

			if (request.getHeader("User-Agent").toLowerCase()
					.indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),
						"ISO8859-1");
			}
			// IE
			else if (request.getHeader("User-Agent").toUpperCase().indexOf(
					"MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,
					NumberFormats.TEXT);
			format.setAlignment(Alignment.CENTRE);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),
					12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			// 创建表格标题
			ws = wwb.createSheet("查询结果", 0);
			// 预约日期分组
			ws.setColumnView(0, 24);
			ws.setColumnView(1, 24);
			ws.setColumnView(2, 24);
			ws.setColumnView(3, 24);
			ws.setColumnView(4, 24);
			ws.setColumnView(5, 24);

			ws.addCell(new Label(0, 1, "在线驾驶证补证换证", format));
			ws.addCell(new Label(1, 1, "驾驶人联系方式变更", format));
			ws.addCell(new Label(2, 1, "机动车联系方式变更", format));
			ws.addCell(new Label(3, 1, "补换机动车行驶证", format));
			ws.addCell(new Label(4, 1, "补换检验合格标志", format));
			ws.addCell(new Label(5, 1, "机动车业务外网预约", format));

			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,
					NumberFormats.TEXT);
			if (resultNumber != null) {
				Object[] temp = (Object[]) resultNumber;
				ws.addCell(new Label(0, 2, temp[0].toString(), format));
				ws.addCell(new Label(1, 2, temp[1].toString(), format));
				ws.addCell(new Label(2, 2, temp[2].toString(), format));
				ws.addCell(new Label(3, 2, temp[3].toString(), format));
				ws.addCell(new Label(4, 2, temp[4].toString(), format));
				ws.addCell(new Label(5, 2, temp[5].toString(), format));
			}

			wwb.write();

		} catch (Exception e) {
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
				}
			} catch (IOException e) {
			}
		}
	}

	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

}
