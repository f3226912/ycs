package com.ycszh.ssh.service.ydwt.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.ycszh.global.SysConst;
import com.ycszh.ssh.dao.ydwt.WscgsSjzdDao;
import com.ycszh.ssh.dao.ydwt.WtydnsUserDao;
import com.ycszh.ssh.dao.ydwt.WtydnsYwsbspbDao;
import com.ycszh.ssh.dao.ydwt.WtydnsYwsbspbLogDao;
import com.ycszh.ssh.hbm.dydj.DydjSbInfo;
import com.ycszh.ssh.hbm.ydwt.WscgsSjzd;
import com.ycszh.ssh.hbm.ydwt.WtydnsUser;
import com.ycszh.ssh.hbm.ydwt.WtydnsYwsbspb;
import com.ycszh.ssh.hbm.ydwt.WtydnsYwsbspbLog;
import com.ycszh.ssh.hbm.ydwt.YdwtDeclareAndQuit;
import com.ycszh.ssh.service.ydwt.WtydnsYwsbspbService;
import com.ycszh.util.DateUtil;

public class WtydnsYwsbspbServiceImpl implements WtydnsYwsbspbService{

	private WtydnsYwsbspbDao wybpDao;
	
	private WscgsSjzdDao sjzdDao;
	
	private WtydnsUserDao  wtydnsUserDao;
	
	private WtydnsYwsbspbLogDao wybpLogDao;
	
	private final static Logger log = Logger.getLogger(WtydnsYwsbspbServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<String>  getPCHItems(String sbzt) throws Exception{
		List<String>  list = new ArrayList<String>();
		   List items =this.wybpDao.getPCHItems(sbzt);
		   if(items.size()>0){
			   for (int i = 0; i < items.size(); i++) {
				 String obj = (String)items.get(i);
				  list.add(obj);
			   }
		   }
		 return list;
	}	
	
	@SuppressWarnings("unchecked")
	public List<WtydnsYwsbspb> getYwsbspbListByCondition(HttpServletRequest request,int currentPage) throws Exception {
      String pch = request.getParameter("pch"); //批次号
      String sfzh =request.getParameter("sfzh");//身份证号
      String hphm =request.getParameter("hphm");//号牌号码
      String hpzl =request.getParameter("hpzl");//号牌种类
      String startTime =request.getParameter("startTime");//申报时间
      String endTime = request.getParameter("endTime");
      String sbzt =request.getParameter("sbzt");//状态
      List<WtydnsYwsbspb> spbList =new ArrayList<WtydnsYwsbspb>();
      WtydnsYwsbspb pb = new WtydnsYwsbspb();
      pb.setPch(pch);
      pb.setSfzmhm(sfzh);
      pb.setHphm(hphm); 
      pb.setHpzl(hpzl);
      pb.setSbzt(sbzt);
      String countSql="select count(0) from wtydns_ywsbspb  where 1=1 ";
	  	String sql ="select id, PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(hphm,'EF57') as hphm,"+
		" PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(hpzl,'EF57') as hpzl,"+
	    " PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(clsbdh,'EF57') as clsbdh,"+
	    " PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(sfzmhm,'EF57') as sfzmhm ,cg_yhxm,stjg,sbzt,pch, "+
	    " YJ_SJRXM," +
	    " PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(SYR,'EF57') as SYR," +
	    " YJ_LXDH ," +
	    " YJ_TDDZ," +
	    " YJ_YZBM," +
	    " lrsj from  wtydns_ywsbspb where 1=1 ";
		if(!"".equals(pb.getPch()) && null !=pb.getPch()){
			sql+=" and pch like '%"+pb.getPch()+"%'";
			countSql+=" and pch like '%"+pb.getPch()+"%'";
		}
		if(!"--".equals(pb.getSbzt()) && null !=pb.getSbzt()){
			sql+=" and sbzt ='"+pb.getSbzt()+"'";
			countSql+=" and sbzt ='"+pb.getSbzt()+"'";
		}
		if(!"".equals(pb.getSfzmhm()) && null !=pb.getSfzmhm()){
			sql+=" and PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(sfzmhm,'EF57') like '%"+pb.getSfzmhm()+"%'";
			countSql+=" and PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(sfzmhm,'EF57') like '%"+pb.getSfzmhm()+"%'";
		}
		if(!"".equals(pb.getHphm()) && null !=pb.getHphm()){
			sql+=" and PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(hphm,'EF57') like '%"+pb.getHphm()+"%'";
			countSql+=" and PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(hphm,'EF57') like '%"+pb.getHphm()+"%'";
		}
		if(!"--".equals(pb.getHpzl()) && null!=pb.getHpzl()){
			sql+=" and PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(hpzl,'EF57') ='"+pb.getHpzl()+"'";
			countSql+=" and PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(hpzl,'EF57') ='"+pb.getHpzl()+"'";
		}
		if(null!= pb.getLrsj()){
			sql+=" and trunc(lrsj) = to_date('"+DateUtil.date2String(pb.getLrsj(),"yyyy-MM-dd")+"','yyyy-MM-dd')";
			countSql+=" and trunc(lrsj) = to_date('"+DateUtil.date2String(pb.getLrsj(),"yyyy-MM-dd")+"','yyyy-MM-dd')";
		}
	     if(null!=startTime && !"".equals(startTime) && null!=endTime && !"".equals(endTime)){
	    	 sql+=" and trunc(lrsj) between to_date('"+startTime+"','yyyy-MM-dd') and to_date('"+endTime+"','yyyy-MM-dd')";
	     }else{
	    	 if((null!=startTime && !"".equals(startTime)) && (null==endTime || "".equals(endTime))){
	    		 sql+=" and trunc(lrsj) = to_date('"+startTime+"','yyyy-MM-dd')";
	    		 countSql+=" and trunc(lrsj) = to_date('"+startTime+"','yyyy-MM-dd')";
	    	 }else if((null==startTime || "".equals(startTime)) && (null!=endTime && !"".equals(endTime))){
	    		 sql+="and trunc(lrsj) = to_date('"+endTime+"','yyyy-MM-dd')";
	    		 countSql+="and trunc(lrsj) = to_date('"+endTime+"','yyyy-MM-dd')";
	    	 }
	     }
		System.out.println(countSql);
		Object sizeList = this.wybpDao.findSQL(countSql).get(0);
		int size=Integer.parseInt(sizeList.toString());		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		if (size > 0) {
			List list1= this.wybpDao.findSQLByPage(sql,offset,pageSize);
			
			for (int i = 0; i < list1.size(); i++) {
				Object[]  objs= (Object[])list1.get(i);
				WtydnsYwsbspb sb = new WtydnsYwsbspb();
				sb.setId(Integer.parseInt(objs[0].toString()));
				if(objs[1]!=null){
					sb.setHphm(objs[1].toString());
				}				
				if(objs[2]!=null){
					sb.setHpzl(objs[2].toString());
				}				
				if(objs[3]!=null){
					sb.setClsbdh(objs[3].toString());
				}
				if(objs[4]!=null){
					sb.setSfzmhm(objs[4].toString());
				}				
				if(null!=objs[5]){
					sb.setCgYhxm(objs[5].toString());
				}
				if(objs[6]!=null){
					sb.setStjg(objs[6].toString());
				}
				if(objs[7]!=null){
					sb.setSbzt(objs[7].toString());
				}
				if(null!=objs[8]){
					sb.setPch(objs[8].toString());
				}
				if(objs[9]!=null){
					sb.setYjSjrxm(objs[9].toString());   //邮件收件人
				}
				if(objs[10]!=null){
					sb.setSyr(objs[10].toString());      //所有人		
				}
				if(objs[11]!=null){
					sb.setYjLxdh(objs[11].toString());   //邮寄—联系电话
				}
				if(objs[12]!=null){
					sb.setYjTddz(objs[12].toString());   //邮寄—投递地址
				}
				if(objs[13]!=null){
					sb.setYjYzbm(objs[13].toString());   //邮寄—邮政编码	
				}				
				sb.setLrsj(DateUtil.string2Date(objs[14].toString()));//录入时间
				spbList.add(sb);
			}
			Map<String,String> stjq= sjzdDao.getQueryStjg();
	  		Map<String,String> hpzls= sjzdDao.getQueryHpzl();
			for (int i = 0; i < spbList.size(); i++) {
				WtydnsYwsbspb wp = (WtydnsYwsbspb)spbList.get(i);
				wp.setStjg(stjq.get(wp.getStjg()));
				wp.setHpzl(hpzls.get(wp.getHpzl()));
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
		request.setAttribute("spbList", spbList);
        request.setAttribute("pb", pb);
        request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
	  return spbList;
	}
	public List<WtydnsYwsbspb> getYwsbspbListExcelByCondition(HttpServletRequest request) throws Exception {
	      String pch = request.getParameter("pch"); //批次号
	      String sfzh =request.getParameter("sfzh");//身份证号
	      String hphm =request.getParameter("hphm");//号牌号码
	      String hpzl =request.getParameter("hpzl");//号牌种类
	      String startTime =request.getParameter("startTime");//申报时间
	      String endTime = request.getParameter("endTime");
	      String sbzt =request.getParameter("sbzt");//状态
	      List<WtydnsYwsbspb> spbList =new ArrayList<WtydnsYwsbspb>();
	      WtydnsYwsbspb pb = new WtydnsYwsbspb();
	      pb.setPch(pch);
	      pb.setSfzmhm(sfzh);
	      pb.setHphm(hphm); 
	      pb.setHpzl(hpzl);
	      pb.setSbzt(sbzt);
		  	String sql =
		  	"select id, " +
		  	" PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(hphm,'EF57') as hphm,"+
			" PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(hpzl,'EF57') as hpzl,"+
		    " PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(clsbdh,'EF57') as clsbdh,"+
		    " PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(sfzmhm,'EF57') as sfzmhm ," +
		    " cg_yhxm,CG_YHDM,CG_YHSJ,CG_IP," +
		    " stjg," +
		    " sbzt," +
		    " pch, "+
		    " PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(SYR,'EF57') as SYR," +
		    " to_char(lrsj,'yyyy-mm-dd hh24:mi:ss'),LRIP,CLSYQ,LRZH from  wtydns_ywsbspb where 1=1 ";
			if(!"".equals(pb.getPch()) && null !=pb.getPch()){
				sql+=" and pch like '%"+pb.getPch()+"%'";
			}
			if(!"--".equals(pb.getSbzt()) && null !=pb.getSbzt()){
				sql+=" and sbzt ='"+pb.getSbzt()+"'";
			}
			if(!"".equals(pb.getSfzmhm()) && null !=pb.getSfzmhm()){
				sql+=" and PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(sfzmhm,'EF57') like '%"+pb.getSfzmhm()+"%'";
			}
			if(!"".equals(pb.getHphm()) && null !=pb.getHphm()){
				sql+=" and PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(hphm,'EF57') like '%"+pb.getHphm()+"%'";
			}
			if(!"--".equals(pb.getHpzl()) && null!=pb.getHpzl()){
				sql+=" and PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(hpzl,'EF57') ='"+pb.getHpzl()+"'";
			}
			if(null!= pb.getLrsj()){
				sql+=" and trunc(lrsj) = to_date('"+DateUtil.date2String(pb.getLrsj(),"yyyy-MM-dd")+"','yyyy-MM-dd')";
			}
		     if(null!=startTime && !"".equals(startTime) && null!=endTime && !"".equals(endTime)){
		    	 sql+=" and trunc(lrsj) between to_date('"+startTime+"','yyyy-MM-dd') and to_date('"+endTime+"','yyyy-MM-dd')";
		     }else{
		    	 if((null!=startTime && !"".equals(startTime)) && (null==endTime || "".equals(endTime))){
		    		 sql+=" and trunc(lrsj) = to_date('"+startTime+"','yyyy-MM-dd')";
		    		 
		    	 }else if((null==startTime || "".equals(startTime)) && (null!=endTime && !"".equals(endTime))){
		    		 sql+="and trunc(lrsj) = to_date('"+endTime+"','yyyy-MM-dd')";
		    	 }
		     }	
				List list1= this.wybpDao.findSQL(sql);				
				for (int i = 0; i < list1.size(); i++) {
					Object[]  objs= (Object[])list1.get(i);	
					WtydnsYwsbspb sb = new WtydnsYwsbspb();
					sb.setId(Integer.parseInt(objs[0].toString()));
					if(objs[1]!=null){
						sb.setHphm(objs[1].toString());
					}				
					if(objs[2]!=null){
						sb.setHpzl(objs[2].toString());
					}				
					if(objs[3]!=null){
						sb.setClsbdh(objs[3].toString());
					}
					if(objs[4]!=null){
						sb.setSfzmhm(objs[4].toString());
					}				
					if(null!=objs[5]){
						sb.setCgYhxm(objs[5].toString());
					}
					if(null!=objs[6]){
						sb.setCgYhdm(objs[6].toString());
					}
					if(null!=objs[7]){
						sb.setCgYhsj(objs[7].toString());
					}
					if(null!=objs[8]){
						sb.setCgIp(objs[8].toString());
					}					
					if(null!=objs[9]){
						sb.setStjg(objs[9].toString());
					}
					if(null!=objs[10]){
						sb.setSbzt(objs[10].toString());
					}
					if(null!=objs[11]){
						sb.setPch(objs[11].toString());
					}
					if(null!=objs[12]){
						sb.setSyr(objs[12].toString());
					}
					if(null!=objs[13]){
						sb.setYjYzbm(objs[13].toString());
					}
					if(null!=objs[14]){
						sb.setLrip(objs[14].toString());
					}
					if(null!=objs[15]){
						sb.setClsyq(objs[15].toString());
					}
					if(null!=objs[16]){
						sb.setLrzh(objs[16].toString());
					}
					spbList.add(sb);
				}
				Map<String,String> stjq= sjzdDao.getQueryStjg();
		  		Map<String,String> hpzls= sjzdDao.getQueryHpzl();
				for (int i = 0; i < spbList.size(); i++) {
					WtydnsYwsbspb wp = (WtydnsYwsbspb)spbList.get(i);
					wp.setStjg(stjq.get(wp.getStjg()));
					wp.setHpzl(hpzls.get(wp.getHpzl()));
					if(wp.getClsyq().equals("0")){
						wp.setClsyq("个人");
					}else{
						wp.setClsyq("公司");
					}
				}			
		  return spbList;
		}
	/**
	 * 统计
	 */
	public List<YdwtDeclareAndQuit> getYdwtDeclareAndquitStat(HttpServletRequest request) throws Exception {
		String startTime = request.getParameter("startTime");
		String endTime =request.getParameter("endTime");
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		return this.wybpDao.getDeclareAndQuitStat(startTime, endTime);
	}
	public List<YdwtDeclareAndQuit> getYdwtPosQuitStat(HttpServletRequest request) throws Exception{
		String startTime = request.getParameter("startTime");
		String endTime =request.getParameter("endTime");
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		log.info("method:getYdwtPosQuitStat|param:request="+request+"---统计");
		return this.wybpDao.getPosQuitStat(startTime, endTime);
	}
	
	/**
	 * 
	 * @param request
	 * @param currentPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<WtydnsUser> getUserList(HttpServletRequest request,int currentPage) throws Exception {
		StringBuffer hql = new StringBuffer("from WtydnsUser as t where 1=1 ");
		String yzYhdm = request.getParameter("yzYhdm");
		String zhlx = request.getParameter("zhlx");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<WtydnsUser> userList = new ArrayList<WtydnsUser>();
		// 用户名
		if (yzYhdm != null && !yzYhdm.equals("")) {
			yzYhdm = yzYhdm.replaceAll(" ", "");
			yzYhdm = yzYhdm.replaceAll("'", "");
			yzYhdm = yzYhdm.replaceAll("\"", "");
			yzYhdm = yzYhdm.replaceAll("，", ",");
			if (yzYhdm.indexOf(",") >= 0) {
				String[] userNames = null;
				if (yzYhdm.indexOf(",") >= 0) {
					userNames = yzYhdm.split(",");
					hql.append("and (");
					for (int i = 0; i < userNames.length; i++) {
						if (i == 0) {
							hql.append(" t.yzYhdm like '%" + userNames[i]
									+ "%' ");
						} else {
							hql.append(" or t.yzYhdm like '%" + userNames[i]
									+ "%' ");
						}
					}
					hql.append(")");
				}
			} else {
				hql.append(" and t.yzYhdm like '%" + yzYhdm + "%' ");
			}
			request.setAttribute("yzYhdm", yzYhdm);
		}
        if(zhlx!=null && !"--".equals(zhlx)){
        	hql.append(" and t.zhlx='"+zhlx+"'");
        	request.setAttribute("zhlx", zhlx);
        }

		int size = wtydnsUserDao.getSize(hql.toString());
		if (size > 0) {
			hql.append(" order by t.id desc");
			userList = wtydnsUserDao.findHQLByPage(hql.toString(),offset,pageSize);
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
		log.info("method:getUserList|param:request="+request+",currentPage="+currentPage);
		return userList;
	}
    /**
     * 冻结/解冻用户
     */
	public Integer freezeUser(Long id) throws Exception {
		try {
			WtydnsUser user = wtydnsUserDao.getRepository(id);
			if (user != null) {
				if("1".equals(user.getStatus())){
					user.setStatus("0");
				}else{
					user.setStatus("1");
				}
				user.setTranDate(null);
				user.setTranFlag(null);
				user.setSynFlag("UW");
				wtydnsUserDao.addRepository(user);
				log.info("method:deleteUser|param:userId="+id);
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
	 * 重置密码
	 */
	public Integer resetPwd(String id,HttpServletRequest request) throws Exception {
		try {
			WtydnsUser user = wtydnsUserDao.getRepository(Long.parseLong(id));
			if(user!=null){
				user.setYmmm("000000");//重置密码
				user.setTranDate(null);
				user.setTranFlag(null);
				user.setSynFlag("UW");
				WtydnsUser u=wtydnsUserDao.addRepository(user);
				log.info("method:resetPwd|param:user="+u);
				return 1;
			}else{
				return 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return 0;
	    }
	}
	
	
	/**
	 * 添加/修改用户信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public int addUserInfo(HttpServletRequest request,WtydnsUser user) throws Exception {
		int result=0;
		String editType = request.getParameter("editType");
		 //先查询代码是否已经存在
		String hql ="from WtydnsUser where yzYhdm='"+user.getYzYhdm()+"'";
			if("新增".equals(editType)){
				List<WtydnsUser> list =wtydnsUserDao.getRepositories(hql);
				if(list.isEmpty()){
					user.setStatus("1");//用户状态
					user.setSynFlag("UW");//内网
					user.setTranFlag(null);
					user.setTranDate(null);
					wtydnsUserDao.addRepository(user); 
					result++;	
				}else{
					result=3;//该代码已存在
				}
			}else{
				wtydnsUserDao.updateRepository(user); 
				result++;	
			}			
		return result;
	}
	
	public WtydnsYwsbspb getWtydsnIfo (int id) throws Exception {
		WtydnsYwsbspb pb =null;
		WtydnsYwsbspb returnInfo = new WtydnsYwsbspb();
		try{
			pb = this.wybpDao.getRepository(id);
			Map<String,String> hpMap = wybpDao.getQueryHpzl();
			Map<String,String> stjgMap = wybpDao.getQueryStjg();
			returnInfo.setId(pb.getId());
			returnInfo.setHphm(this.wybpDao.getDecrypt(pb.getHphm(), SysConst.P_KEY));
			String hpzl=this.wybpDao.getDecrypt(pb.getHpzl(), SysConst.P_KEY);
			returnInfo.setSyr(wybpDao.getDecrypt(pb.getSyr(), SysConst.P_KEY));
			returnInfo.setSfzmhm(wybpDao.getDecrypt(pb.getSfzmhm(), SysConst.P_KEY));
			returnInfo.setClsbdh(wybpDao.getDecrypt(pb.getClsbdh(), SysConst.P_KEY));
			returnInfo.setSbztKdbh(wybpDao.getDecrypt(pb.getSbztKdbh(), SysConst.P_KEY));//快递编号
			returnInfo.setHpzl(hpMap.get(hpzl));
			returnInfo.setStjg(stjgMap.get(pb.getStjg()));
			returnInfo.setDzzplb(pb.getDzzplb());
			returnInfo.setYjSjrxm(pb.getYjSjrxm());
			returnInfo.setYjTddz(pb.getYjTddz());
			returnInfo.setYjYzbm(pb.getYjYzbm());
			returnInfo.setYjLxdh(pb.getYjLxdh());
			returnInfo.setLrsj(pb.getLrsj());
			returnInfo.setLrip(pb.getLrip());
			if("0".endsWith(pb.getSbzt())){
				returnInfo.setSbzt("待初审");
			}else if("1".equals(pb.getSbzt())){
				returnInfo.setSbzt("已初审");
			}else if("2".equals(pb.getSbzt())){
				returnInfo.setSbzt("已复核");
			}else if("3".equals(pb.getSbzt())){
				returnInfo.setSbzt("通知书已接收");
			}else if("4".equals(pb.getSbzt())){
				returnInfo.setSbzt("快递已寄出");
			}else if("CT".equals(pb.getSbzt())){
				returnInfo.setSbzt("车管所退办");
			}else if("YT".equals(pb.getSbzt())){
				returnInfo.setSbzt("邮政退办");
			}else if("QT".equals(pb.getSbzt())){
				returnInfo.setSbzt("个人取消申请");
			}else if("QQ".equals(pb.getSbzt())){
				returnInfo.setSbzt("个人取消业务");
			}else if("CC".equals(pb.getSbzt())){
				returnInfo.setSbzt("车管业务退办");
			}else{
				returnInfo.setSbzt("");
			}
			returnInfo.setTranFlag(pb.getSbzt());   //用tranFlag属性来装sbzt的值
			returnInfo.setSbztTbly(pb.getSbztTbly());
			returnInfo.setSbztKdbh(pb.getSbztKdbh());
			returnInfo.setPch(pb.getPch());
			returnInfo.setCgYhdm(pb.getCgYhdm());
			returnInfo.setCgYhxm(pb.getCgYhxm());
			returnInfo.setCgYhsj(pb.getCgYhsj());
			returnInfo.setCgIp(pb.getCgIp());
			returnInfo.setYzYhdm(pb.getYzYhdm());
			returnInfo.setYzYhxm(pb.getYzYhxm());
			returnInfo.setYzYhsj(pb.getYzYhsj());
			returnInfo.setCgIp(pb.getYzIp());
			returnInfo.setSynFlag(pb.getSynFlag());
			returnInfo.setTranDate(pb.getTranDate());
			returnInfo.setClsyq(pb.getClsyq());
			returnInfo.setLrzh(pb.getLrzh());
		}catch(Exception se){
			se.printStackTrace();
			throw se;
		}
	
		return returnInfo;
	}
	/**
	 * 根据id获取用户信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public WtydnsUser getWtydnsUserInfo(HttpServletRequest request) throws Exception {
		String id= request.getParameter("id");
		return this.wtydnsUserDao.getRepository(Long.parseLong(id));
	}
	
	/**
	 * 根据id查询日志记录。
	 * 主要是记录改id对应的申报到邮寄整个过程。
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<WtydnsYwsbspbLog> getWtydsnLogInfo(int id) throws Exception {
		StringBuffer sql = new StringBuffer("select ID,CG_YHSJ,SBZT,PkG_ENCRYPT_DECRYPT.DECRYPT_3KEY_MODE(hphm,'EF57') as hphm,SBZT_TBLY,CZRXM from WTYDNS_YWSBSPB_LOG where ID='"+id+"' order by CG_YHSJ");
		List list = this.wybpLogDao.findSQL(sql.toString());
		List<WtydnsYwsbspbLog> pbLog= new ArrayList<WtydnsYwsbspbLog>();
		if (list.size() > 0) {			
			for (int i = 0; i < list.size(); i++) {
				Object[]  objs= (Object[])list.get(i);
				WtydnsYwsbspbLog sb = new WtydnsYwsbspbLog();
				sb.setId(Integer.parseInt(objs[0].toString()));
				if(null!=objs[1]){
					sb.setCgYhsj(objs[1].toString());
				}
				sb.setSbzt(objs[2].toString());
				sb.setHphm(objs[3].toString());
				if(null!=objs[4]){
					sb.setSbztTbly(objs[4].toString());
				}	
				sb.setCzrxm(objs[5].toString());
				pbLog.add(sb);
			}
		}
	    return pbLog;
	}
	
	 /**
	  * 查出预警数据(30天之内)
	  * @throws Exception
	  */
	@SuppressWarnings("unchecked")
	public List<DydjSbInfo> getWarnInfo(HttpServletRequest request,int currentPage) throws Exception {
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String hphm = request.getParameter("hphm");
		String hpzl = request.getParameter("hpzl");
		String stauts= request.getParameter("stauts");
		List<DydjSbInfo> doData = new ArrayList<DydjSbInfo>();
	    if(null!=stauts){
	    	String sql =" select lsh,ywyy,syr,cast(hpzl as varchar2(10)),hphm,clpp1,clxh,to_char(sqrq,'yyyy-MM-dd hh24:mi:ss') sqrq," +
	    			    " to_char(bjrq,'yyyy-MM-dd hh24:mi:ss'),glbm,clsbdh from es_veh_flow t " +
	    			    " where ywlx='Q' and sqrq >to_Date('2013-8-21','yyyy-MM-dd')";
	  		String countStr="select count(0) from es_veh_flow t " +
	  				     "where ywlx='Q' and sqrq >to_Date('2013-8-21','yyyy-MM-dd')";
	  		if(null!= hphm && !"".equals(hphm)){
	  		  	    sql+=" and hphm ='"+hphm+"'";
	  	       countStr+=" and hphm ='"+hphm+"'";
	  			request.setAttribute("hphm", hphm);
	  		}	  		
	  		if(null != s_date && !"".equals(s_date)){
		  		    sql+=" and sqrq >= to_date('"+s_date+" 00:00:00','yyyy-MM-dd hh24:mi:ss') ";	
		  	   countStr+=" and sqrq >= to_date('"+s_date+" 00:00:00','yyyy-MM-dd hh24:mi:ss') ";
		  			request.setAttribute("s_date", s_date);
	  		}
	  		if(null != e_date && !"".equals(e_date)){
	  		        sql+=" and sqrq <= to_date('"+e_date+" 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
	  		   countStr+=" and sqrq <= to_date('"+e_date+" 23:59:59','yyyy-MM-dd hh24:mi:ss') ";
	  			request.setAttribute("e_date", e_date);
	  		}
	  		
	  		if(null!=hpzl && !"".equals(hpzl)){
	  			   sql+=" and hpzl ='"+hpzl+"'";
	  		  countStr+=" and hpzl ='"+hpzl+"'";
	  			request.setAttribute("hpzl", hpzl);
	  		}
	  		sql+=" and not exists (select substr(hphm,2),hpzl from wtydns_ywsbspb_in where hphm=t.HPHM and lrsj>t.SQRQ-30 and lrsj<t.SQRQ )  order by SQRQ";
	  		
	   countStr+=" and not exists (select substr(hphm,2),hpzl from wtydns_ywsbspb_in where hphm=t.HPHM and lrsj>t.SQRQ-30 and lrsj<t.SQRQ ) ";
	  		int size =Integer.parseInt(this.wybpDao.findSQL(countStr).get(0).toString());
	  		int pageSize = SysConst.PAGESIZE;
	  		int offset = SysConst.PAGESIZE*(currentPage-1);
	  		String curi = request.getRequestURI();
	  		List list =this.wybpDao.findSQLByPage(sql, offset, pageSize);	  		
	  		if(null != list && list.size()>0){
	  			for (int i = 0; i < list.size(); i++) {
	  				DydjSbInfo info = new DydjSbInfo();
	  				Object[] objs = (Object[])list.get(i);
	  				if(objs[0]!=null){
	  					info.setLsh(objs[0].toString());
	  				}
	  				if(objs[1]!=null){
	  				    info.setYwyy(objs[1].toString());	
	  				}
	  				if(objs[2]!=null){
	  					info.setSyr(objs[2].toString());
	  				}				
	  				if(objs[3]!=null){
	  					info.setHpzl(objs[3].toString());
	  				}
	  				if(objs[4]!=null){
	  					info.setHphm(objs[4].toString());
	  				}
	  				if(objs[5]!=null){
	  					info.setClpp1(objs[5].toString());
	  				}
	  				if(objs[6]!=null){
	  					info.setClxh(objs[6].toString());
	  				}
	  				if(objs[7]!=null){
	  					info.setSqrq(DateUtil.string2Date(objs[7].toString(), "yyyy-MM-dd HH:mm:ss"));
	  				}
	  				if(objs[8]!=null){
	  					info.setBjrq(DateUtil.string2Date(objs[8].toString(), "yyyy-MM-dd HH:mm:ss"));
	  				}
	  				if(objs[9]!=null){
	  					info.setGlbm(objs[9].toString());
	  				}	
	  				if(objs[10]!=null){
	  					info.setClsbdh(objs[10].toString());
	  				}
	  				doData.add(info);
	  			}
//	  			//根据结果比对dydj_ywsbspb_in中的结果，如果找不到结果就返回出去，显示在页面上
//	  			for (DydjSbInfo in : doData) {
//	  				String inStr = "select count(0) from DYDJ_YWSBSPB_IN where ywlx='"+in.getYwyy()+"' and hpzl='"+in.getHpzl()+"' and hphm='"+in.getHphm()+"' and " +
//	  						     "(lrsj >to_date('"+DateUtil.getDateBefore(in.getSqrq(),15)+"','yyyy-MM-dd hh24:mi:ss')  and lrsj<to_date('"+DateUtil.date2String(in.getSqrq(), "yyyy-MM-dd HH:mm:ss")+"','yyyy-MM-dd hh24:mi:ss'))";
//	  				int count = Integer.parseInt(this.dydjServiceDao.findSQL(inStr).get(0).toString());
//	  				if(count==0){  //表示没有查询出来的结果
//	  					resultData.add(in);
//	  				}
//	  			}
	  		   if(doData.size()>0){
	  			   Map<String,String> deps= this.getQueryDepMc();
	  		  		Map<String,String> hpzls= this.getQueryHpzl();
	  			   for (DydjSbInfo inter : doData) {					
	  			  		inter.setHpzl(hpzls.get(inter.getHpzl()));
	  			  		inter.setGlbm(deps.get(inter.getGlbm()));
	  			    }
	  		   }
	  		}
	  		Map map=new HashMap();
			map.put("uri", curi);
			map.put("pagesize", pageSize);
			map.put("rscount", size);
			map.put("currentpage", currentPage);
			request.setAttribute("rscount", size);
			request.setAttribute("map", map);
	    }		
		
		return doData;
	}
	/**
	 * 查询号牌种类字典(hpzl)
	 * @return
	 */
	public Map<String, String> getQueryHpzl() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		StringBuffer sql = new StringBuffer("select ws.dmz,ws.dmsm1 from wscgs_sjzd ws where ws.dmlb = '7' order by ws.dmz ");
		List resultList = this.wybpDao.findSQL(sql.toString());
		Object obj [] = new Object[2];
		for (int i = 0; i < resultList.size(); i++) {
			obj = (Object[])resultList.get(i);
			map.put(obj[0].toString(), obj[1].toString());
		}
		return map;
	}
	public Map<String,String> getQueryDepMc() throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		StringBuffer sql = new StringBuffer("select glbm,bmmc from v_frm_department ");
		List resultList = this.wybpDao.findSQL(sql.toString());
		Object obj [] = new Object[2];
		for (int i = 0; i < resultList.size(); i++) {
			obj = (Object[])resultList.get(i);
			map.put(obj[0].toString(), obj[1].toString());
		}
		return map;
	}
	
	public List<WscgsSjzd> getHpzl() throws Exception{
		return this.sjzdDao.getHpzl();
	}
		
	public WtydnsYwsbspbDao getWybpDao() {
		return wybpDao;
	}

	public void setWybpDao(WtydnsYwsbspbDao wybpDao) {
		this.wybpDao = wybpDao;
	}

	public WscgsSjzdDao getSjzdDao() {
		return sjzdDao;
	}

	public void setSjzdDao(WscgsSjzdDao sjzdDao) {
		this.sjzdDao = sjzdDao;
	}


	public WtydnsUserDao getWtydnsUserDao() {
		return wtydnsUserDao;
	}

	public void setWtydnsUserDao(WtydnsUserDao wtydnsUserDao) {
		this.wtydnsUserDao = wtydnsUserDao;
	}

	public WtydnsYwsbspbLogDao getWybpLogDao() {
		return wybpLogDao;
	}

	public void setWybpLogDao(WtydnsYwsbspbLogDao wybpLogDao) {
		this.wybpLogDao = wybpLogDao;
	}

}
