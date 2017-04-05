package com.ycszh.ssh.dao.dydj.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.dydj.DydjYwsbspbDao;
import com.ycszh.ssh.dao.ydwt.WtydnsYwsbspbDao;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspb;
import com.ycszh.ssh.hbm.ydwt.WtydnsYwsbspb;
import com.ycszh.ssh.hbm.ydwt.YdwtDeclareAndQuit;

public class DydjYwsbspbDaoImpl extends BaseDaoImpl<DydjYwsbspb, Integer> implements DydjYwsbspbDao{
	

	@SuppressWarnings("unchecked")
	public int getSize(String sql) throws Exception {
		List list = this.findSQL(sql);
		return list.size();
	}
	
	@Override
	public DydjYwsbspb getRepository(Integer pk) {
		return super.getHibernateTemplate().get(DydjYwsbspb.class, pk);
	}
	/***
	 * 批次查询
	 */
	@SuppressWarnings("unchecked")
    public List getPCHItems(String sbzt) throws Exception {
    	String sql ="select distinct pch  from wtydns_ywsbspb t where pch is not null ";
    	if(null!=sbzt){
    		sql +=" and sbzt='"+sbzt+"' ";
    	}
    	sql+="  order by pch desc ";
    	List list = new ArrayList(); 
    	list =this.findSQL(sql);
       return list;
    }
	/**
	 * 得到受委托机构字典
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getQueryStjg() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		StringBuffer sql = new StringBuffer("select ws.dmz,dmz||' :'||dmsm1 from wscgs_sjzd ws where ws.dmlb = '1001' order by dmz");
		List resultList = this.findSQL(sql.toString());
		Object obj [] = new Object[2];
		for (int i = 0; i < resultList.size(); i++) {
			obj = (Object[])resultList.get(i);
			map.put(obj[0].toString(), obj[1].toString());
		}
		return map;
	}
	/**
	 * 得到受委托机构字典
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getQueryHpzl() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		StringBuffer sql = new StringBuffer("select ws.dmz,ws.dmsm1 from wscgs_sjzd ws where ws.dmlb = '7' order by ws.dmz ");
		List resultList = this.findSQL(sql.toString());
		Object obj [] = new Object[2];
		for (int i = 0; i < resultList.size(); i++) {
			obj = (Object[])resultList.get(i);
			map.put(obj[0].toString(), obj[1].toString());
		}
		return map;
	}
	
	Session session = null;
	Connection con = null;
	CallableStatement cst=null;

	/**
	 * 加密
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String getEncrypt(final String value,String p_key) throws Exception {
		
		final String sql = "{? = call PkG_ENCRYPT_DECRYPT.ENCRYPT_3KEY_MODE(?,?)}";
		String encryptValue;
		try{
			session = this.getSession();
			con = session.connection();
			cst = con.prepareCall(sql);
			
			cst.registerOutParameter(1, Types.VARCHAR);
			cst.setString(2, value);
			cst.setString(3, p_key);
			cst.execute();
			
			encryptValue = cst.getString(1);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally{
			try{cst.close();}catch(Exception e){}
// 			try{con.close();}catch(Exception e){}
		}
   		return encryptValue;
	}
	
	/**
	 * 解密
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public String getDecrypt(final String value,String p_key) throws Exception {
		
		final String sql = "{? = call PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(?,?)}";
		String encryptValue;
		try{
			session = this.getSession();
			con = session.connection();
			cst = con.prepareCall(sql);
			
			cst.registerOutParameter(1, Types.VARCHAR);
			cst.setString(2, value);
			cst.setString(3, p_key);
			cst.execute();
			
			encryptValue = cst.getString(1);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally{
			try{cst.close();}catch(Exception e){}
			try{con.close();}catch(Exception e){}
		}
		return encryptValue;
	}
	
	
	
	/**
	 * 互联网业务申报、办结、退办数据统计
	 * @param startTime :开始时间
	 * @param endTime ：结束时间
	 * @return 统计结果
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<YdwtDeclareAndQuit> getDeclareAndQuitStat(String startTime,String endTime) throws Exception{
		List<YdwtDeclareAndQuit> listStat = new ArrayList<YdwtDeclareAndQuit>();
	     StringBuffer sqlStr  =new StringBuffer(" select to_char(lrsj,'yyyy-MM-dd') lrsj , "+
	     " count(decode(sbzt,'0',0)) declareNum,"+
	     " count(decode(sbzt,'1',0)) firstTrialNum,"+
	     " count(decode(sbzt,'2',0)) recheckNum,"+
	     " count(decode(sbzt,'3',0)) moveNum,"+
	     " count(decode(sbzt,'CT',0)) cgsQuitNum  from wtydns_ywsbspb where 1=1 ");
	     if(null!=startTime && !"".equals(startTime) && null!=endTime && !"".equals(endTime)){
	    	 sqlStr.append(" and trunc(lrsj) between to_date('"+startTime+"','yyyy-MM-dd') and to_date('"+endTime+"','yyyy-MM-dd')");
	     }else{
	    	 if((null!=startTime && !"".equals(startTime)) && (null==endTime || "".equals(endTime))){
	    		 sqlStr.append(" and trunc(lrsj) = to_date('"+startTime+"','yyyy-MM-dd')");
	    	 }else if((null==startTime || "".equals(startTime)) && (null!=endTime && !"".equals(endTime))){
	    		 sqlStr.append("and trunc(lrsj) = to_date('"+endTime+"','yyyy-MM-dd')");
	    	 }
	     }
	     sqlStr.append(" group by to_char(lrsj,'yyyy-MM-dd')  order by lrsj");
	     System.out.println(sqlStr.toString());
	     try{
		     List list1= this.findSQL(sqlStr.toString());
		     if(list1.size()>0){
		  		for (int i = 0; i < list1.size(); i++) {
					Object[]  objs= (Object[])list1.get(i);
					YdwtDeclareAndQuit sb = new YdwtDeclareAndQuit();
					sb.setLrsj(objs[0].toString());
					sb.setDeclareNum(Integer.parseInt(objs[1].toString()));
					sb.setFirstTrialNum(Integer.parseInt(objs[2].toString()));
					sb.setRecheckNum(Integer.parseInt(objs[3].toString()));
					sb.setMoveNum(Integer.parseInt(objs[4].toString()));
					sb.setCgsQuitNum(Integer.parseInt(objs[5].toString()));
					listStat.add(sb);
	 		   }
		     }
	     }catch(Exception se){
	    	 se.printStackTrace();
	     }	
		return listStat;
	}	
	/**
	 * 材料移交邮政信息查询统计
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public List<YdwtDeclareAndQuit> getPosQuitStat(String startTime,String endTime) throws Exception {
		List<YdwtDeclareAndQuit>  posList  =  new ArrayList<YdwtDeclareAndQuit>();
		StringBuffer sqlStr = new StringBuffer("select to_char(lrsj,'yyyy-MM-dd') lrsj," +
				"count(decode(sbzt,'3',0)) acceptNum," +
				"count(decode(sbzt,'4',0)) sendNum," +
				"count(decode(sbzt,'YT',0)) posQuitNum from wtydns_ywsbspb where 1=1");
	     if(null!=startTime && !"".equals(startTime) && null!=endTime && !"".equals(endTime)){
	    	 sqlStr.append(" and trunc(lrsj) between to_date('"+startTime+"','yyyy-MM-dd') and to_date('"+endTime+"','yyyy-MM-dd')");
	     }else{
	    	 if((null!=startTime && !"".equals(startTime)) && (null==endTime || "".equals(endTime))){
	    		 sqlStr.append(" and trunc(lrsj) = to_date('"+startTime+"','yyyy-MM-dd')");
	    	 }else if((null==startTime || "".equals(startTime)) && (null!=endTime && !"".equals(endTime))){
	    		 sqlStr.append("and trunc(lrsj) = to_date('"+endTime+"','yyyy-MM-dd')");
	    	 }
	     }
	     sqlStr.append(" group by to_char(lrsj,'yyyy-MM-dd')  order by lrsj");
	     try{
		     List list1= this.findSQL(sqlStr.toString());
		     if(list1.size()>0){
		  		for (int i = 0; i < list1.size(); i++) {
					Object[]  objs= (Object[])list1.get(i);
					YdwtDeclareAndQuit sb = new YdwtDeclareAndQuit();
					sb.setLrsj(objs[0].toString());
					sb.setDeclareNum(Integer.parseInt(objs[1].toString()));
					sb.setFirstTrialNum(Integer.parseInt(objs[2].toString()));
					sb.setRecheckNum(Integer.parseInt(objs[3].toString()));
					posList.add(sb);
	 		    }
		     }
	     }catch(Exception se){
	    	 se.printStackTrace();
	     }	
	     return posList;
	}
}
