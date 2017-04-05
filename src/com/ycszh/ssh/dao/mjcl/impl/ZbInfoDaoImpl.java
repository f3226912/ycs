package com.ycszh.ssh.dao.mjcl.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import oracle.jdbc.OracleTypes;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.mjcl.ZbInfoDao;
import com.ycszh.ssh.hbm.BaseObject;
import com.ycszh.ssh.dao.DefaultDao;

public class ZbInfoDaoImpl extends HibernateDaoSupport implements ZbInfoDao {

	private DefaultDao defaultDao;

	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public List<Object> getZbInfoData(String sfzmmc, String shzmhm)
			throws Exception {
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		String result = "";

		List<Object> tempData = new ArrayList<Object>();
		List tempList = new ArrayList();
		List zbList = new ArrayList();
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=100.100.21.33)(PORT = 1521))(ADDRESS=(PROTOCOL=TCP)(HOST=100.100.21.34)(PORT=1521))(LOAD_BALANCE=yes)(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=tpdb)(FAILOVER_MODE=(TYPE=SELECT)(METHOD=BASIC)(RETRIES=180)(DELAY=5))))";
			String user = "itosc";
			String password = "testitosc";

			conn = DriverManager.getConnection(url, user, password);

			if (conn != null) {
				cs = conn
						.prepareCall("{?=call clxg_webinvoke.clxg_callservice(?,?,?,?,?) }");

				cs.registerOutParameter(1, OracleTypes.INTEGER);
				cs.setString(2, "90006");
				cs.setString(3, sfzmmc + "," + shzmhm + ",");
				cs.registerOutParameter(4, OracleTypes.CURSOR);
				cs.registerOutParameter(5, OracleTypes.VARCHAR);
				cs.registerOutParameter(6, OracleTypes.VARCHAR);

				cs.execute();

				result = cs.getString(1);
				Object o_outvalue = cs.getObject(4);
				String o_hint = cs.getString(5);
				String o_rinfo = cs.getString(6);
				
				if (o_outvalue != null) {
					
					result = result == null ? "" : result.trim();
					rs = (ResultSet) o_outvalue;
					o_hint = o_hint == null ? "" : o_hint.trim();
					o_rinfo = o_rinfo == null ? "" : o_rinfo.trim();

					// 调用接口无异常
					if (result == "-100") {
						if (o_rinfo.length() > 100) {
							result = "-查询失败:" + o_rinfo.substring(0, 100);
						} else {
							result = "-查询失败:" + o_rinfo;
						}

					} else {
						
						int index_ = 1;
						Map<String, String> tempDataMap = new HashMap<String, String>();
						// 一个身份证是否只有一个指标信息
						boolean onlyOneData = true;

						String ab = "1";
						while (rs.next()) {
							
							String tempNo = rs.getString("no");
							String tempName = rs.getString("name");
							String tempValue = rs.getString("value");
							
							// 翻译指标分类和指标状态
							if (tempName.equals("zbfl")) {
								tempList.add(tempValue);//这个先插入原值，为了zbinfoserviceimp文件里要取这个值，去查下验证表的数据
								List<Object[]> ob = defaultDao.findSQL("select dmz,dmms1 from CLXG_SJZD a where DMLB ='ZBFL' AND DMZ='"+ tempValue + "'");
								if (ob != null && ob.size() > 0) {//查不到的，用原值，不用翻译
									tempValue = ob.get(0)[1].toString();
									
								}
							}
							if (tempName.equals("zbzt")) {
								List<Object[]> ob = defaultDao.findSQL("select dmz,dmms1 from CLXG_SJZD a where DMLB ='ZBZT' AND DMZ='"+ tempValue + "'");
								if (ob != null && ob.size() > 0) {
									tempValue = ob.get(0)[1].toString();
									
								}
							}

							if (tempNo.equals(ab)) {

								tempList.add(tempValue);

							} else {
								List a = new ArrayList(tempList);// 数组赋值到一个new对象里
								zbList.add(a);
								tempList.clear();
								tempList.add(tempValue);
								ab = tempNo;// 不同的序号id赋值
							}

							tempDataMap.put(tempNo + tempName, tempValue);
							/*
							 * if (!tempNo.equals(index_ + "")) { index_++;
							 * onlyOneData = false; tempData.add(tempDataMap);
							 * tempDataMap = new HashMap<String, String>();
							 * 
							 * if (tempName != null) { tempName =
							 * tempName.trim().toUpperCase(); } if (tempValue !=
							 * null) { tempValue =
							 * tempValue.trim().toUpperCase(); } if
							 * (!tempName.equals("")) {
							 * tempDataMap.put(tempNo+tempName, tempValue); }
							 * 
							 * } else { if (tempName != null) { tempName =
							 * tempName.trim().toUpperCase(); } if (tempValue !=
							 * null) { tempValue =
							 * tempValue.trim().toUpperCase(); } if
							 * (!tempName.equals("")) {
							 * tempDataMap.put(tempNo+tempName, tempValue); } }
							 */

						}
						
						if (tempList.size()!=0){
							zbList.add(tempList);
						}
						


						// 一个身份证只有一个指标信息
						//if (onlyOneData) {
						//	tempData.add(tempDataMap);

						//}

					}

				} else {
					result = "-查询失败";
				}

			}

		} catch (Exception e) {
			result = "-查询失败:" + e.getMessage();
		} finally {
			try {
				if (cs != null) {
					cs.close();
					cs = null;
				}

				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e2) {
				result = "-查询失败:" + e2.getMessage();
			}

		}

		//tempData.add(result);
		//System.out.println(zbList);
		return zbList;
		// return tempData;
	}
}
