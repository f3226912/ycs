package com.ycszh.ssh.service.yanche.impl;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.junit.internal.matchers.SubstringMatcher;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.dao.yanche.TXbycGpsDao;
import com.ycszh.ssh.dao.yanche.TXbycGpsLogDao;
import com.ycszh.ssh.dao.yanche.YczdglDao;
import com.ycszh.ssh.dao.yanche.YhlxxzDao;
import com.ycszh.ssh.hbm.yanche.TXbycCode;
import com.ycszh.ssh.hbm.yanche.TXbycGps;
import com.ycszh.ssh.hbm.yanche.TXbycGpsLog;
import com.ycszh.ssh.hbm.yanche.TXbycYhlxxz;
import com.ycszh.ssh.service.yanche.YczdglService;
import com.ycszh.util.FileType;
import com.ycszh.util.ImageMarkLogoUtil;
import com.ycszh.util.ToolsUtil;
import com.ycszh.util.UploadTools;



public class YczdglServiceImpl implements YczdglService{
	private YczdglDao yczdglDao;
	private YhlxxzDao yhlxxzDao;
	private TXbycGpsDao txbycgpsDao;
	private TXbycGpsLogDao txbycgpslogDao;
	private DefaultDao defaultDao;
	private final static Logger log = Logger.getLogger(PdasmycChbaseServiceImpl.class);
	
//======================================查验表管理
	

	//查看各部门签名用户列表
	@SuppressWarnings("unchecked")
	public String getQmUserList(HttpServletRequest request,int currentpage) throws Exception{
		String sql = "select v.Login_Id,v.User_Name,v.org_name,t.czrxm,to_char(t.czrq,'yyyy-MM-dd HH24:mi:ss') czrq,t.czip,t.qmurl from v_xbyc_oriuser v "+
		"left join t_xbyc_sbyh t on v.Login_Id=t.login_id where v.login_id not like 'old%'";
		String sqlSize = "select count(1) from v_xbyc_oriuser where login_id not like 'old%' ";
		String orgidSql = "select org_id from v_xbyc_oriuser where login_id not like 'old%'";
		String org_id = request.getParameter("orgid");
		String login_id = request.getParameter("loginid");
		String user_name = request.getParameter("username");
		String nodeid = request.getParameter("nodeid");
		int fool = 0;
		List qmuserlist = new ArrayList();
		if(org_id!=null && !org_id.equals("")){
			sql+=" and v.org_id ='"+org_id+"'";
			sqlSize+=" and org_id ='"+org_id+"'";
			request.setAttribute("orgid", org_id);
			nodeid=org_id;
			fool=1;
		}
		if(login_id!=null && !login_id.equals("")){
			sql+=" and v.Login_Id='"+login_id+"'";
			sqlSize+=" and Login_Id='"+login_id+"'";
			orgidSql+=" and login_id='"+login_id+"'";
			request.setAttribute("loginid", login_id);
			fool=2;
		}
		if(user_name!=null && !user_name.equals("")){
			sql+=" and v.User_Name='"+user_name+"'";
			sqlSize+=" and User_Name='"+user_name+"'";
			orgidSql+=" and User_Name='"+user_name+"'";
			request.setAttribute("username", user_name);
			fool=2;
		}
		//获取部门编号（节点）
		if(fool==2){
			List list = new ArrayList();
			list = yczdglDao.findSQL(orgidSql);
			if(list.size()>0){
				org_id=list.get(0).toString();
				nodeid=org_id;
			}
		}
		if(nodeid!=null && !nodeid.equals("")){
			request.setAttribute("nodeid", nodeid);
		}
		int pageSize = SysConst.PAGESIZE;
		int offset = pageSize*(currentpage-1);
		String curi = request.getRequestURI();
		int size=0;
		if(fool!=0){
			size = Integer.valueOf(yczdglDao.findSQL(sqlSize).get(0).toString());
			qmuserlist = yczdglDao.findSQLByPage(sql, offset, pageSize);
		}
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentpage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("qmuserlist",qmuserlist);
		return null;
	}
	
	//初始化部门信息
	public String initDeptNode(HttpServletRequest request) throws Exception{
		String deptSql = "select org_id,org_name, zd_id,zd_name from v_xbyc_oriuser "+
		"group by  org_id,org_name,zd_id,zd_name order by zd_id";
		//部门列表
		List deptlist = yczdglDao.findSQL(deptSql.toString());
		StringBuffer node = new StringBuffer("[");
		for (int i = 0; i < deptlist.size(); i++) {
			Object[] obj = (Object[]) deptlist.get(i);
			if(obj[0]==obj[2]||obj[0].equals(obj[2])){
				node.append(" {id:'"+obj[2]+1+"', pId:'', name:'"+obj[3]+"',open:false,isParent:true }, ");
				node.append(" {id:'"+obj[0]+"', pId:'"+obj[2]+1+"', name:'"+obj[1]+"'}, ");
			}else{
				node.append(" {id:'"+obj[0]+"', pId:'"+obj[2]+1+"', name:'"+obj[1]+"'}, ");
			}
		}
		node.append(" ];");
		request.setAttribute("node", node);
		return null;
	}
	
	//电子签名管理
	public Object getUserDzqmInfo(HttpServletRequest request) throws Exception{
		String loginid = request.getParameter("loginid");
		String sql= " select v.Login_Id,v.User_Name,v.org_name,t.qmurl from v_xbyc_oriuser v " +
				"left join t_xbyc_sbyh t on v.Login_Id=t.login_id where v.login_id not like 'old%' ";
		if(loginid!=null&&!loginid.equals("")){
			sql+=" and  v.Login_Id='"+loginid+"'";
			List list = yczdglDao.findSQL(sql);
			if(list.size()>0){
				Object[] obj = (Object[]) list.get(0);
				request.setAttribute("loginid", obj[0]);
				request.setAttribute("username", obj[1]);
				request.setAttribute("dmbl", obj[2]);
				request.setAttribute("dzqmurl", obj[3]);
			}
		}
		return null;
	}
	
	/**
	 * 查看用户电子签名信息列表
	 *//*
	@SuppressWarnings("unchecked")
	public List getUserDzqm(HttpServletRequest request,int currentpage) throws Exception {
		String sql = "select v.Login_Id, v.User_Name, v.org_name,t.czrq , t.czip, t.qmurl "+
				"from v_xbyc_oriuser v left join t_xbyc_sbyh t on v.Login_Id = t.login_id "+
				"where v.login_id not like 'old%'";
		String sqlSize ="select count(1) from v_xbyc_oriuser v left join t_xbyc_sbyh t " +
				"on v.Login_Id = t.login_id where v.login_id not like 'old%'";
		String glbmSql = " select  zd_id,zd_name from v_xbyc_oriuser group by zd_id,zd_name";
		//是否查看所有用户
		String userAll = request.getParameter("userAll");
		if(userAll==null||userAll.equals("")||userAll=="no"||userAll.equals("no")){
			//只查看有签名的用户
			sql+=" and qmurl is not null";
			sqlSize+=" and t.qmurl is not null";
			userAll="no";
		}
		String loginid = request.getParameter("loginid");
		if(loginid!=null&&!loginid.equals("")){
			sql+=" and v.login_id ='"+loginid+"'";
			sqlSize+=" and v.login_id ='"+loginid+"'";
		}
		String username = request.getParameter("username");
		if(username!=null&&!username.equals("")){
			sql+=" and v.user_name like '%"+username+"%'";
			sqlSize+=" and v.user_name like '%"+username+"%'";
		}
		String glbmid = request.getParameter("glbmid");
		if(glbmid!=null&&!glbmid.equals("")){
			sql+=" and v.zd_id='"+glbmid+"'";
			sqlSize+=" and v.zd_id='"+glbmid+"'";
		}
		sql+=" order by v.org_id";
		int pageSize = SysConst.PAGESIZE;
		int offset = pageSize*(currentpage-1);
		String curi = request.getRequestURI();
		List glbmList = yczdglDao.findSQL(glbmSql);
		int size= Integer.valueOf(yczdglDao.findSQL(sqlSize).get(0).toString());
		List dzqmList = new ArrayList();
		if(size>0){
			dzqmList = yczdglDao.findSQLByPage(sql, offset, pageSize);
		}
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentpage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("dzqmList", dzqmList);
		request.setAttribute("glbmList", glbmList);
		request.setAttribute("userAll", userAll);
		request.setAttribute("loginid", loginid);
		request.setAttribute("username", username);
		request.setAttribute("glbmid", glbmid);
		return null;
	}*/
	
	/**
	 * 更新签名
	 */
	@SuppressWarnings("unchecked")
	public Integer updateDzqm(HttpServletRequest request,File src,String fileName) throws Exception {
		int fileType = 6;		//文件类型(6.其它)
		int size = 1048576;		//文件大小(设定为1G=1048576K)
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String loginid = request.getParameter("loginid");
		String servletPath = request.getSession().getServletContext().getRealPath("/"); 
		String dzqmUrl = servletPath+"\\images\\dzqm\\"+fileName;
		
		//根据文件格式获取文件类型（1.图片，2.文档，3.视频，4.种子，5.音乐，6.其它）
		FileInputStream fis = new FileInputStream(src);
		FileType value = FileType.getType(fis);
		fileType = FileType.isFileType(value);
		if(fileType!=1){
			//request.setAttribute("message", "2-电子签名格式不正确，只能为图片!");
			return 2;
		}
		//判断签名图片是否存在
		File dst = new File(dzqmUrl);	
		if(dst.exists()){
			dst.delete();
		}
		//执行上传
		String rest = UploadTools.uploadTemplate(src, dst, size);
		if(rest!="success" && !rest.equals("success")){
			//request.setAttribute("message", "3-电子签名上传失败!");
			return 3;
		}else{
			//查看库中该用户签名是否存在
			String sql = "select qmurl from t_xbyc_sbyh where login_id='"+loginid+"'";
			String SeqSql = "select SEQ_XBYC.NEXTVAL from dual";//获取序号
			List list = yczdglDao.findSQL(sql);
			//文件地址
			String qmurl = "/images/dzqm/"+fileName;
			if(list.size()>0){ //修改
				String upsql ="update t_xbyc_sbyh set czr='"+user.getName()+"',czrxm='"+user.getYgxm()+"',czrq=sysdate," +
						"czip='"+ToolsUtil.getIpAddr(request)+"',qmurl='"+qmurl+"' where login_id='"+loginid+"'";
				yczdglDao.updateRepositoryBySql(upsql);
				//request.setAttribute("message", "0-电子签名修改成功!");
			}else{ //添加
				//获取序号
				List seqList = yczdglDao.findSQL(SeqSql);
				Integer seq=Integer.valueOf(seqList.get(0).toString());
				//添加用户签名信息
				String addsql ="insert into t_xbyc_sbyh(sbyh_id,login_id,czr,czrxm,czbm,czrq,czip,qmurl) " +
						"values ('"+seq+"','"+loginid+"','"+user.getName()+"','"+user.getYgxm()+"'," +
								"'"+user.getBmmc()+"',sysdate,'"+ToolsUtil.getIpAddr(request)+"','"+qmurl+"')"; 
				yczdglDao.updateRepositoryBySql(addsql);
				//request.setAttribute("message", "0-电子签名添加成功!");
			}
			//添加日志信息
			List seqList1 = yczdglDao.findSQL(SeqSql);
			Integer seq1=Integer.valueOf(seqList1.get(0).toString());
			String addsql1 ="insert into t_xbyc_sbyh_log(sbyh_id,login_id,czr,czrxm,czbm,czrq,czip,qmurl) " +
			"values ('"+seq1+"','"+loginid+"','"+user.getName()+"','"+user.getYgxm()+"'," +
					"'"+user.getBmmc()+"',sysdate,'"+ToolsUtil.getIpAddr(request)+"','"+qmurl+"')"; 
			yczdglDao.updateRepositoryBySql(addsql1);
			log.info("method:updateDzqm|param:UpdateUserQm="+loginid);
		}
		return 0;
	}
	
	
	/**
	 * 获取查验员信息及签名文件
	 */
	@SuppressWarnings("unchecked")
	public List getDzqmInfo(HttpServletRequest request) throws Exception {
		//获取登录用户信息		
		String loginid = request.getParameter("loginid");
		String action = request.getParameter("action");
		String currentpage =request.getParameter("currentpage");
		String orgid = request.getParameter("orgid");
		
		//保存上级访问路径节点（用户列表）-->签名页面
		String url = null;
		url = action+"?currentpage="+currentpage+"&orgid="+orgid;
		request.setAttribute("url", url);
		request.setAttribute("action", action);
		request.setAttribute("currentpage", currentpage);
		request.setAttribute("orgid", orgid);
		
		if(loginid!=null && !loginid.equals("")){
			request.setAttribute("loginid", loginid);
		}
		//根据登录用户获取签名文件
		String sql = " select qmurl from t_xbyc_sbyh where login_id='"+loginid+"'";
		String usersql = "select login_id,user_name,org_name from v_xbyc_oriuser where login_id='"+loginid+"'";
		List qmList = this.yczdglDao.findSQL(sql);
		List userList = this.yczdglDao.findSQL(usersql);
		Object[] obj = (Object[]) userList.get(0);
		request.setAttribute("loginid", obj[0]);
		request.setAttribute("username", obj[1]);
		request.setAttribute("orgname", obj[2]);
		if(qmList.size()>0){
			String qmurl = (String) qmList.get(0);
			String servletPath = request.getSession().getServletContext().getRealPath("");  
			//String path = request.getContextPath();
			//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			String jdQmurl= servletPath+qmurl;
			File file =new File(jdQmurl); 
			if (file.exists()) {
				request.setAttribute("qmurl", qmurl);
			}
		}
		return null;
	}
	
	
//======================================验车点位置管理
	/**
	 * 删除验车位置点GPS信息
	 */
	public Integer deleteGps(HttpServletRequest request) throws Exception{
		String gpsId = request.getParameter("gpsId");
		if(gpsId!=null&&!gpsId.equals("")){
			TXbycGps gps = new TXbycGps();
			List list = this.yczdglDao.getRepositories(" from TXbycGps where gpsId = '"+gpsId+"'");
			if(list.size()>0){
				gps=(TXbycGps) list.get(0);
				this.txbycgpsDao.deleteRepository(gps);
				TXbycGpsLog gpslog = new TXbycGpsLog();
				gpslog = (TXbycGpsLog) getXclog(gpslog,gps);
				gpslog.setCznr("delete");
				TXbycGpsLog objlog = this.txbycgpslogDao.addRepository(gpslog);
				log.info("method:deleteGps|param:TXbycGps="+gps);
				return 0;
			}else{
				return 1;
			}
		}else{
			return 1;
		}
	}
	
	/**
	 * 提交编辑验车位子GPS信息
	 */
	public Integer editYcGps(HttpServletRequest request,TXbycGps gps) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String editType = request.getParameter("editType");
		request.setAttribute("editType", editType);
		int retult = 1;
		if(gps!=null){
			TXbycGps obj = null;
			TXbycGpsLog objlog = null;
			gps.setCzr(user.getName());
			gps.setCzrxm(user.getYgxm());
			gps.setCzbm(user.getBmmc());
			gps.setCzrq(new Date());
			gps.setCzip(ToolsUtil.getIpAddr(request));
			String gpsid = gps.getGpsId();
			TXbycGpsLog gpslog = new TXbycGpsLog();
			gpslog = (TXbycGpsLog) getXclog(gpslog,gps);
			//判断日志表中是否有该记录
			if(gpsid!=null&&!gpsid.equals("")){ //提交修改
				obj = this.txbycgpsDao.updateRepository(gps);
				gpslog.setCznr("update");
			}else{ //提交添加
				obj = this.txbycgpsDao.addRepository(gps);
				gpslog.setCznr("insert");
			}
			objlog = this.txbycgpslogDao.addRepository(gpslog);
			if(obj!=null&&objlog!=null){
				retult = 0;
			}else{
				retult = 3;
			}
		}else{
			retult = 2;
		}
		log.info("method:editYcGps|param:TXbycGps="+gps);
		return retult;
	}
	
	/**
	 * 初始化编辑验车位子GPS信息
	 */
	public String initEditycGps(HttpServletRequest request) throws Exception {
		String gpsid = request.getParameter("gpsid");
		String editType = null;
		TXbycGps gps = new TXbycGps();
		if(gpsid!=null&&!gpsid.equals("")){ //初始化修改页面
			editType="update";
			List list = this.yczdglDao.getRepositories(" from TXbycGps where gpsId = '"+gpsid+"'");
			if(list.size()>0){
				gps=(TXbycGps) list.get(0); 
			}
		}else{ //初始化添加
			editType="add";
		}
		request.setAttribute("gps", gps);
		request.setAttribute("editType", editType);
		return null;
	}
	
	/**
	 * 获得验车点位置信息列表
	 */
	@SuppressWarnings("unchecked")
	public List getYcgpsList(HttpServletRequest request, int currentpage) throws Exception {
		String hql = " from TXbycGps where 1=1";
		String sizeSql = " select count(1) from t_xbyc_gps where 1=1";
		String jgmc = request.getParameter("jgmc");
		String xxdz = request.getParameter("xxdz");
		List<TXbycGps> ycGpsList = new ArrayList<TXbycGps>();
		//List ycGpsList = new ArrayList();
		if(jgmc != null && !jgmc.equals("")){
			hql+=" and jgmc = '"+jgmc+"'";
			sizeSql+=" and jgmc = '"+jgmc+"'";
		}
		if(xxdz !=null && !xxdz.equals("")){
			hql+=" and xxdz like '%"+xxdz+"%'";
			sizeSql+=" and xxdz like '%"+xxdz+"%'";
		}
		int pageSize = SysConst.PAGESIZE;
		int offset = (currentpage-1)*pageSize;
		Object obj = yczdglDao.findSQL(sizeSql).get(0);
		int size = Integer.valueOf(obj.toString());
		if(size>0){
			ycGpsList = yczdglDao.findHQLByPage(hql, offset, pageSize);
		}
		String curi = request.getRequestURI();
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentpage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("ycGpsList", ycGpsList);
		request.setAttribute("jgmc", jgmc);
		request.setAttribute("xxdz", xxdz);
		return null;
	}
	
	/**
	 * 将实体表添加到日志表
	 */
	public Object getXclog(Object obj1, Object obj2) throws Exception{
		if(obj1 == null){
			return null;
		}
		try {
			Field[] fields = obj2.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				Field field = obj1.getClass().getDeclaredField(name);
				field.set(obj1, fields[i].get(obj2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return obj1;
	}
	
//======================================查验记录查看打印
	/**
	 * 获取查验表单信息
	 */
	public Object getCybd(HttpServletRequest request) throws Exception {
		try {
			String lsh = request.getParameter("lsh");
			if(lsh!=null&&!lsh.equals("")){
				String sql = "select * from t_xbyc_ycjl where lsh='"+lsh+"'";
				List list = this.yczdglDao.findSQL(sql);
				Object[] cybdObj = (Object[]) list.get(0);
				String ywlx = cybdObj[18].toString();
				String ywyy = cybdObj[19].toString();
				String ywlxOryy = null;
				//业务类型
				//没有对应值项[重新打刻VIN、加装/拆装操纵辅助装置、申领登记证书、补领登记证书、监督解体]
				if(ywlx=="A"&&ywyy=="A"||ywlx.equals("A")&&ywyy.equals("A")){		//注册登记
					ywlxOryy="AA";
				}else if(ywlx=="B"&&ywyy=="B"||ywlx.equals("B")&&ywyy.equals("B")){	//转移登记(市内过户)
					ywlxOryy="B";
				}else if(ywlx=="B"&&ywyy=="C"||ywlx.equals("B")&&ywyy.equals("C")){ //转移登记（市外过户）
					ywlxOryy="B";
				}else if(ywlx=="D"&&ywyy=="P"||ywlx.equals("D")&&ywyy.equals("P")){ //变更迁出
					ywlxOryy="DP";
				}else if(ywlx=="D"&&ywyy=="D"||ywlx.equals("D")&&ywyy.equals("D")){ //变更车身颜色
					ywlxOryy="DD";
				}else if(ywlx=="D"&&ywyy=="G"||ywlx.equals("D")&&ywyy.equals("G")){	//更换车身或者车架
					ywlxOryy="DG";		
				}else if(ywlx=="D"&&ywyy=="H"||ywlx.equals("D")&&ywyy.equals("H")){ //更换整车
					ywlxOryy="DH";
				}else if(ywlx=="D"&&ywyy=="F"||ywlx.equals("D")&&ywyy.equals("F")){	//更换发动机
					ywlxOryy="DL";
				}else if(ywlx=="D"&&ywyy=="I"||ywlx.equals("D")&&ywyy.equals("I")){ //变更使用性质
					ywlxOryy="DI";
				}else if(ywlx=="D"&&ywyy=="L"||ywlx.equals("D")&&ywyy.equals("L")){ //重新打刻发动机号
					ywlxOryy="DL";
				}else{
					ywlxOryy="QT";
				}
				
				//整理参数
				//String clsbdh = cybdObj[5].toString();  //车辆识别代号
				//String hpzl = cybdObj[11].toString();   //号牌种类（需关联车牌类型表）
				//使用性质
				List syxzlist = this.yczdglDao.findSQL("select dmsm from T_XBYC_CODE where dmlb=3 and dmz='"+cybdObj[4]+"'");
				String syxz=null;
				if(syxzlist.size()>0){
					syxz = (String) syxzlist.get(0);
				}
				//查验员
				List cynList = this.yczdglDao.findSQL("select user_name from v_xbyc_oriuser where login_id = '"+cybdObj[15]+"'");
				String cyName = null;
				if(cynList.size()>0){
					cyName = (String) cynList.get(0);
				}
				//验车时间
				String ycsj = cybdObj[14].toString();
				String[] ycDate = ycsj.split("-");
				//获取查验次数(用于判断是否是复检)
				Integer cycs = Integer.valueOf(cybdObj[28].toString());
				//获取号牌种类
				String hpzl = (String)this.yczdglDao.findSQL("select dmsm1 from extshare.ext_veh_code t where xtlb = '00' and dmlb = '1007' and dmz='"+cybdObj[11]+"'").get(0);
				//获得车辆类型】
				String cllx = (String)this.yczdglDao.findSQL("select dmsm from T_XBYC_CODE  where dmlb='4' and dmz = '"+cybdObj[3]+"'").get(0);
				//车身颜色
				String csys = "";
				if(cybdObj[34]!=null&&!cybdObj[34].equals("")){
					String ysDm =  (String) cybdObj[34];
					for (int i = 0; i < ysDm.length(); i++) {
						String Dmz = ysDm.substring(i, i+1);
						if(Dmz!=null&&!Dmz.equals("")){
							String ys = (String) this.yczdglDao.findSQL("select dmsm1 from extshare.ext_veh_code  where xtlb = '00' and dmlb = '1008' and dmz = '"+Dmz+"'").get(0);
							csys+=ys;
							if(i+1<ysDm.length()){
								csys+=",";
							}
						}
					}
				}
				//核定载人数
				String hdzrs = (String) cybdObj[33];
				//获取查验项查验结果
				Map<Integer,String> cyxMap = new HashMap<Integer, String>();
				String result = yczdglDao.getCyxjg(lsh);
				for (int i = 0; i < result.length(); i++) {
					String zhi = result.substring(i, i+1);
					if(zhi=="1"||zhi.equals("1")){
						zhi="√";
					}else if(zhi=="0"||zhi.equals("0")){
						zhi="X";
					}else{
						zhi="—";
					}
					cyxMap.put(i+1, zhi);
				}
				//验车图片45度(图文码)
				List zplist = this.yczdglDao.findSQL("select t.pzid from (select * from t_xbyc_photo where lsh='"+lsh+"' and pzgg=1 order by pzsj desc) t where rownum = 1");
				String zpid = null;
				if(zplist.size()>0){
					zpid=(String) zplist.get(0);
				}
				//车架号图片
				String cjh_zpid = null;
				List kym_zplist = this.yczdglDao.findSQL("select t.pzid from (select * from t_xbyc_photo where lsh='"+lsh+"' and pzgg=3 order by pzsj desc) t where rownum = 1");
				if(kym_zplist.size()>0){
					cjh_zpid=kym_zplist.get(0).toString();
				}
				//获取工程绝对路径和服务器location路径
				String servletPath = request.getSession().getServletContext().getRealPath("/");  
		        String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				String srcImgPath=servletPath+"\\images\\zhang\\zhang_di.jpg";
		    	String targerPath=servletPath+"\\images\\zhang\\zhang.jpg";
		    	//获取查验员盖章
		    	String QzUrl = ImageMarkLogoUtil.markImageByText(cyName, srcImgPath, targerPath, 15);
		    	
		    	
		    	//获取检验员电子签名
				List cyylist = this.yczdglDao.findSQL("select qmurl from t_xbyc_sbyh where login_id = '"+cybdObj[15]+"'");
				String cyyUrl=null;
				if(cyylist.size()>0){
					cyyUrl = (String) cyylist.get(0) ;
					String disurl = servletPath+cyyUrl;
					File dst = new File(disurl);	
					//判断图片是否存在
					if(cyyUrl!=null&&dst.exists()){
				    	String dzqm = basePath+cyyUrl;
				    	request.setAttribute("dzqm", dzqm);
			    	}
				}
		    	
				request.setAttribute("QzUrl", QzUrl);
				request.setAttribute("cybdObj", cybdObj);
				request.setAttribute("syxz", syxz);
				request.setAttribute("cyName", cyName);
				request.setAttribute("ycDate", ycDate);			
				request.setAttribute("cyxMap", cyxMap);
				request.setAttribute("zpid", zpid);
				request.setAttribute("cjh_zpid", cjh_zpid);
				request.setAttribute("cycs", cycs);
				request.setAttribute("hpzl", hpzl);
				request.setAttribute("cllx", cllx);
				request.setAttribute("csys", csys);
				request.setAttribute("hdzrs", hdzrs);
				request.setAttribute("ywlxOryy", ywlxOryy);
				request.setAttribute("sqr", cybdObj[9].toString());
				//判断是校车还是其它车
				String lx = cybdObj[3].toString();  //车辆类型代码值
				String xz = cybdObj[4].toString();  //使用性质代码值
				//lx="K18";
				if(lx=="K18"||lx=="K28"||lx=="K38"||lx.equals("K18")||lx.equals("K28")||lx.equals("K38")||
						xz=="O"||xz=="P"||xz=="Q"||lx.equals("O")||lx.equals("P")||lx.equals("Q")){
					String zyxc = "no";
					if(lx=="K18"||lx=="K28"||lx=="K38"||lx.equals("K18")||lx.equals("K28")||lx.equals("K38")){
						zyxc = "yes";
					}
					request.setAttribute("zyxc", zyxc);
					return "xc";
				}else{
					return "qt";
				}
			}else{
				return "exception";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}
	
	/**
	 * 获取查验记录列表
	 */
	@SuppressWarnings("unchecked")
	public List getCyjl(HttpServletRequest request, int currentpage) throws Exception {
		//获取查验记录
		String sql = "select t1.lsh,t4.dmsm cllx,t3.dmsm syxz,t2.User_Name,t1.sfzmhm,t1.ywlx,t1.ywyy,t1.ycsj,t1.cyjl " +
				"from t_xbyc_ycjl t1,v_xbyc_oriuser t2,(select dmz,dmsm from t_xbyc_code where " +
				"dmlb=3) t3,(select dmz,dmsm from t_xbyc_code where dmlb=4) t4 where " +
				"t1.login_id=t2.Login_Id and t1.syxz=t3.dmz and t1.cllx=t4.dmz";
		String sizeSql = "select count(1) from t_xbyc_ycjl where 1=1";
		String lsh = request.getParameter("lsh");
		String sfzmhm = request.getParameter("sfzmhm");
		String cllx = request.getParameter("cllx");
		String syxz = request.getParameter("syxz");
		if(lsh != null&&!lsh.equals("")){
			sql+=" and t1.lsh='"+lsh+"'";
			request.setAttribute("lsh", lsh);
		}
		if(sfzmhm!=null && !sfzmhm.equals("")){
			sql+=" and t1.sfzmhm='"+sfzmhm+"'";
			request.setAttribute("sfzmhm", sfzmhm);
		}
		if(cllx!=null && !lsh.equals("")){
			sql+=" and t1.cllx='"+cllx+"'";
			request.setAttribute("cllx", cllx);
		}
		if(syxz!=null && !syxz.equals("")){
			sql+=" and t1.syxz='"+syxz+"'";
			request.setAttribute("syxz", syxz);
		}
		int pageSize = SysConst.PAGESIZE;
		int offset = pageSize*(currentpage-1);
		String curi = request.getRequestURI();
		@SuppressWarnings("unused")
		List cyjlList = new ArrayList();
		int size = 0;
		Object obj = this.yczdglDao.findSQL(sizeSql).get(0);
		size= Integer.valueOf(obj.toString());
		if(size>0){
			try {
				sql+=" order by t1.ycsj desc";
				cyjlList = this.yczdglDao.findSQLByPage(sql, offset, pageSize);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//业务类型值封装
		Map<String,String> ywlxMap = new HashMap<String, String>();
		ywlxMap.put("A", "注册登记查验");
		ywlxMap.put("D", "变更登记及备案查验");
		ywlxMap.put("L", "申领、补领登记查验");
		ywlxMap.put("M", "档案更正查验");
		ywlxMap.put("H", "核发入境号牌行驶证查验");
		ywlxMap.put("BB", "转移登记(市内过户)");
		ywlxMap.put("BC", "转移登记(市外过户)");
		request.setAttribute("ywlxMap", ywlxMap);
		
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentpage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);	
		request.setAttribute("cyjlList", cyjlList);
		return null;
	}
	
	
//======================================用户账号使用性质/车辆类型配置管理
	/**
	 * 初始化用户类型性质编辑页面
	 */
	@SuppressWarnings("unchecked")
	public List getEditYhlxxz(HttpServletRequest request) throws Exception {
		//初始化车辆类型全部分类标识（全部）
		String cllxBsSql = "select bs from(select substr(dmz,1,1) bs from  T_XBYC_CODE where dmlb = '4') group by bs order by bs";
		//初始化全部使用性质
		String syxzSql = "select dmz,dmsm from T_XBYC_CODE where dmlb=3 order by dmz";
		
		//1.分类获取车辆类型
		List cllxBsList = this.yczdglDao.findSQL(cllxBsSql);	//车辆类型全部标识
		Map<String, List> cllxMap = new HashMap<String, List>();
		for (Object obj : cllxBsList) {
			String bs = (String) obj;
			//根据车辆类型标识获去车辆类型
			String cllxByBsSql="select t.dmz,t.dmsm from(select substr(dmz,1,1) bs,dmz,dmsm from  T_XBYC_CODE " +
					"where dmlb = '4' order by dmz) t where t.bs='"+bs+"'";
			List cllxlist = this.yczdglDao.findSQL(cllxByBsSql);
			cllxMap.put(bs, cllxlist);
		}
		request.setAttribute("cllxBsList", cllxBsList);		//车辆类型分类标识集合
		request.setAttribute("cllxMap", cllxMap);			//按标识key 获取车辆类型value
		
		//2.获取全部使用性质
		List syxzList = this.yczdglDao.findSQL(syxzSql);
		request.setAttribute("syxzList", syxzList);			//使用性质集合
		
		//3.获取该用户信息
		String loginId = request.getParameter("loginId");
		String cllxQxSql = "select u.Login_Id,u.User_Name,u.org_name,u.zd_name,q.cllx,q.syxz,q.sfqx,q.imei,q.zt " +
				"from v_xbyc_oriuser u left join T_XBYC_YHLXXZ q on u.Login_Id = q.login_id where 1=1 ";
		if(loginId!=null&&!loginId.equals("")){
			cllxQxSql+=" and u.login_id='"+loginId+"'";
			List yhlxxzList = this.yczdglDao.findSQL(cllxQxSql);
			Object[] obj = (Object[]) yhlxxzList.get(0);
			
			String cllx = (String) obj[4];	
			String syxz = (String) obj[5];	
			String Sfqx = (String) obj[6];		//是否全选
			if(cllx!=null&&!cllx.equals("")){
				String[] Cllx = cllx.split(",");	//车辆类型（用户权限）
				request.setAttribute("Cllx", Cllx);
			}
			if(syxz!=null&&!syxz.equals("")){
				String[] Syxz = syxz.split(",");	//使用性质（用户权限）
				request.setAttribute("Syxz", Syxz);
			}
			if(Sfqx==null){
				Sfqx="0";
			}
			request.setAttribute("Sfqx", Sfqx);
			request.setAttribute("UserObj", obj);
		}
		return null;
	}
	

	
	/**
	 * 获取用户账号配置类型信息
	 */
	@SuppressWarnings("unchecked")
	public List getYcyhlxxzList(HttpServletRequest request, int currentpage) throws Exception {
		String bmsql = "select zd_id,zd_name from v_xbyc_oriuser group by zd_id,zd_name";
		String yhsql = "select u.Login_Id,u.User_Name,u.org_name,u.zd_name,q.cllx,q.syxz,q.ywlx,q.imei,q.zt " +
				"from v_xbyc_oriuser u left join T_XBYC_YHLXXZ q on u.Login_Id = q.login_id where u.Login_Id not like 'old%'";
		String yhsqlSize = "select count(1) from v_xbyc_oriuser u left join T_XBYC_YHLXXZ q on u.Login_Id = q.login_id where 1=1";
		String zdid = request.getParameter("zdid");
		String userName = request.getParameter("userName");
		
		String loginId = request.getParameter("loginId");
		Boolean bool = false;
		//获取验车类标
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		List<Map<String, String>> yhbmList = new ArrayList<Map<String,String>>();
		List bmlist = yhlxxzDao.findSQL(bmsql);
		Object[] o = new Object[2];
		for (Object obj : bmlist) {
			o = (Object[]) obj;
			Map<String, String> map =  new HashMap<String, String>();
			map.put(String.valueOf(o[0]), String.valueOf(o[1]));
			yhbmList.add(map);
		}
		
		List yhlxxzlist = new ArrayList();
		if(zdid!=null&&!zdid.equals("")){
			yhsql +=" and u.zd_id='"+zdid+"'";
			yhsqlSize +=" and u.zd_id='"+zdid+"'";
			request.setAttribute("zdid", zdid);
			bool = true;
		}
		if(userName!=null&&!userName.equals("")){
			yhsql+=" and u.User_Name like '%"+userName+"%'";
			yhsqlSize+=" and u.User_Name like '%"+userName+"%'";
			request.setAttribute("userName", userName);
			bool = true;
		}
		if(loginId!=null&&!loginId.equals("")){
			yhsql+=" and u.login_id = '"+loginId+"'";
			yhsqlSize+=" and u.login_id = '"+loginId+"'";
			request.setAttribute("loginId", loginId);
			bool = true;
		}
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentpage-1);
		String curi = request.getRequestURI();
		int size = 0;
		if(bool){
			yhsql+="order by u.lv";
			Object obj = this.yhlxxzDao.findSQL(yhsqlSize).get(0);
			size = Integer.valueOf(obj.toString());
			yhlxxzlist = this.yhlxxzDao.findSQLByPage(yhsql, offset, pageSize);
		}
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentpage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);	
		request.setAttribute("yhbmList", yhbmList);
		request.setAttribute("yhlxxzlist", yhlxxzlist);
		log.info("method:getYcyhlxxzList|param:request="+request+",currentPage="+currentpage);
		return null;
	}
	
	/**
	 * 修改用户验车类型性质配置
	 */
	@SuppressWarnings("unchecked")
	public Integer updateYcyhlxxz(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String loginId = request.getParameter("loginId");	//用户账号
		String zt = request.getParameter("zt");				//状态
		String sfqx = request.getParameter("Sfqx");			//是否全选
		String syxz = request.getParameter("Syxz");			//使用性质
		String cllx = request.getParameter("Cllx");			//车辆类型
		
		//判断类型性质表T_XBYC_YHLXXZ是否存在用户信息
		String hql = " from TXbycYhlxxz where loginId='"+loginId+"'";
		List<TXbycYhlxxz> yhlxxzList = this.yhlxxzDao.getRepositories(hql);
		TXbycYhlxxz yhlxxz = new TXbycYhlxxz();;
		if(yhlxxzList.size()>0){
			yhlxxz = yhlxxzList.get(0);
		}
		yhlxxz.setZt(zt);
		yhlxxz.setSfqx(sfqx);
		if(sfqx=="0"||sfqx.equals("0")){
			yhlxxz.setSyxz(syxz);
			yhlxxz.setCllx(cllx);
		}else{
			yhlxxz.setSyxz("");
			yhlxxz.setCllx("");
		}	
		yhlxxz.setCzr(user.getName());
		yhlxxz.setCzrxm(user.getYgxm());
		yhlxxz.setCzbm(user.getBmmc());
		yhlxxz.setCzrq(new Date());
		yhlxxz.setCzip(ToolsUtil.getIpAddr(request));
		
		if(yhlxxzList.size()>0){
			yhlxxz=this.yhlxxzDao.updateRepository(yhlxxz);
		}else{
			yhlxxz=this.yhlxxzDao.addRepository(yhlxxz);
		}
		log.info("method:insertOrUpdatePdasmycVehPcb|param:TXbycYhlxxz="+yhlxxz);
		if(yhlxxz!=null){
			return 0;
		}else{
			return 1;
		}
	}
	
	
//=======================================验车字典管理/查验项目配置管理	
	/**
	 * 查验项目配置管理
	 * lyType -- syxz:使用性质，cllx:车辆类型， cyxm:查验项目，pzgg:拍照规格
	 */
	public List<TXbycCode> getCyxmOrPzgg(HttpServletRequest request,int currentpage) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String lyType = request.getParameter("lyType");
		String sql = "";
		
		return null;
	}
	
	/**
	 * 使用性质/车辆类型查询（配置）
	 * 查验项目/拍照规格查询（配置）
	 */
	@SuppressWarnings("unchecked")
	public List<TXbycCode> getSyxzOrCllx(HttpServletRequest request,int currentpage) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String lyType = request.getParameter("lyType");	//类型
		String dmz = request.getParameter("dmz");		//代码值
		String hql = " from TXbycCode where 1=1 ";
		if(dmz!=null&&!dmz.equals("")){
			hql+=" and dmz = '"+dmz+"'";
			request.setAttribute("dmz", dmz);
		}
		//判断是否是管理员（1：正常状态）
		if(user.getName()!="jst"){
			hql+=" and zt = '1'";	
		}
		List<TXbycCode> ycByTypeList = new ArrayList<TXbycCode>();
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentpage-1);
		String curi = request.getRequestURI();
		int size = 0;
		if(lyType!=null && !lyType.equals("")){
			hql+=" and dmlb = '"+lyType+"'";
			hql+=" order by sxh";			
			size = yczdglDao.getSize(hql);
			ycByTypeList = yczdglDao.findHQLByPage(hql, offset, pageSize);
		}		
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentpage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);		
		request.setAttribute("lyType", lyType);
		request.setAttribute("ycByTypeList", ycByTypeList);	
		log.info("method:getYcType|param:request="+request+",currentPage="+currentpage);
		return null;
	}
	
	
	
	
	
	/**
	 * 固定字典项目查询
	 */
	@SuppressWarnings("unchecked")
	public List<TXbycCode> getYcType(HttpServletRequest request,int currentpage) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		List<Map<String, String>> ycTypeList = new ArrayList<Map<String,String>>();
		String dmlb = request.getParameter("dmlb");		//验车类别
		String hql = " from TXbycCode where 1=1 ";
		//获取验车类类型(下拉框)
		String sql = "select dmlb,lbms from T_XBYC_CODE where dmlb!=3 and dmlb!=103 and dmlb!=4 and dmlb!=33 group by dmlb,lbms order by length(lbms)";
		List typelist = yczdglDao.findSQL(sql);
		Object[] o = new Object[2];
		for (Object obj : typelist) {
			o = (Object[]) obj;
			Map<String, String> map =  new HashMap<String, String>();
			map.put(String.valueOf(o[0]), String.valueOf(o[1]));
			ycTypeList.add(map);
		}	
		//判断是否是管理员（1：正常状态）
		if(user.getName()!="jst"){
			hql+=" and zt = '1'";	
		}
		
		//根据参数获取类标对应信息
		List<TXbycCode> ycByTypeList = new ArrayList<TXbycCode>();
		String zt = request.getParameter("zt");			//状态
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentpage-1);
		String curi = request.getRequestURI();
		int size = 0;
		if(dmlb!=null && !dmlb.equals("")){
			hql+=" and dmlb = '"+dmlb+"'";
			if(zt!=null&&!zt.equals("")){
				hql+=" and zt = '"+zt+"'";
			}
			hql+=" order by sxh";			
			size = yczdglDao.getSize(hql);
			ycByTypeList = yczdglDao.findHQLByPage(hql, offset, pageSize);
		}		
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentpage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);		
		request.setAttribute("dmlb", dmlb);
		request.setAttribute("ycTypeList", ycTypeList);	
		request.setAttribute("ycByTypeList", ycByTypeList);
		log.info("method:getYcType|param:request="+request+",currentPage="+currentpage);
		return null;
	}
	
	/**
	 * 复检条件修改
	 */
	public Integer updateFjtj(HttpServletRequest request) throws Exception{
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String dmz = request.getParameter("dmz");
		String hql = "from TXbycCode where dmlb = '40' and dmz = '"+dmz+"'";
		String hql1 = "from TXbycCode where dmlb = '40' and dmz != '"+dmz+"'";
		int retult = 0;
		if(dmz!=null&&!dmz.equals("")){
			hql+=" and dmz = '"+dmz+"'";
			hql1+=" and dmz !='"+dmz+"'";
		}
		List<TXbycCode> list1 = yczdglDao.getRepositories(hql);
		List<TXbycCode> list2 = yczdglDao.getRepositories(hql1);
		try {
			if(list1.size()>0){
				TXbycCode code = (TXbycCode) list1.get(0);
				code.setDmsm1("1");
				code.setCzr(user.getName());
				code.setCzrxm(user.getYgxm());
				code.setCzbm(user.getBmmc());
				code.setCzrq(new Date());
				code.setCzip(ToolsUtil.getIpAddr(request));
				yczdglDao.updateRepository(code);
			}
			for (Object obj : list2) {
				TXbycCode code1 = (TXbycCode) obj;
				code1.setDmsm1("");
				yczdglDao.updateRepository(code1);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	

	/**
	 * 获取查验项和查验依据详情
	 * 获取拍照规格详情和示例图
	 */
	@SuppressWarnings("unchecked")
	public List getCyyjOrPzgg(HttpServletRequest request) throws Exception{
		String typeName = null;
		String mldmz = request.getParameter("mldmz");
		String dmlb = request.getParameter("dmlb");
		if(mldmz!=null&&!mldmz.equals("")){
			List list=yczdglDao.findSQL("select dmz,dmsm from  T_XBYC_CODE where dmlb = '"+dmlb+"' and dmz='"+mldmz+"'");
			if(list.size()>0){
				Object[] obj = (Object[]) list.get(0);
				typeName=obj[1].toString();
			}
			request.setAttribute("mldmz", mldmz);
			request.setAttribute("dmlb", dmlb);
		}
		
		String cyyjId = request.getParameter("cyyjId");		//查验项代码值
		String pzggId = request.getParameter("pzggId");		//拍照规格代码值
		String dmz = request.getParameter("dmz");		//代码值
		List list = new ArrayList();
		//【根据车辆类型和使用性质】获取查验依据信息103
		if(cyyjId!=null&&!cyyjId.equals("")){	
			String[] cyyj = cyyjId.split(",");
			for (int i = 0; i < cyyj.length; i++) {
				String sql = "select dmz,dmsm,dmsm1,dmsm3 from T_XBYC_CODE where dmlb = '103' and dmz = '"+cyyj[i]+"'";
				Object[] cyObj = (Object[]) yczdglDao.findSQL(sql).get(0);
				if(dmz!=null&&!dmz.equals("")){
					if(dmz==cyyj[i].toString()||dmz.equals(cyyj[i].toString())){
						request.setAttribute("cyyjOrpzgg", cyObj[2].toString().trim());
						request.setAttribute("dmsm", cyObj[1]);
					}
				}
				list.add(cyObj);
				request.setAttribute("cyyjId", cyyjId);
			}
		}
		
		//【根据车辆类型和使用性质】获取拍照规格33
		if(pzggId!=null&&!pzggId.equals("")){
			String[] pzgg = pzggId.split(",");
			for (int i = 0; i < pzgg.length; i++) {
				String sql = "select dmz,dmsm,dmsm1,dmsm3 from T_XBYC_CODE where 1=1 and dmlb = '33' and dmz = '"+pzgg[i]+"'";
				Object[] pzObj = (Object[]) yczdglDao.findSQL(sql).get(0);
				if(dmz!=null&&!dmz.equals("")){
					if(dmz==pzgg[i].toString()||dmz.equals(pzgg[i].toString())){
						request.setAttribute("cyyjOrpzgg", pzObj[2].toString().trim());
						/*String serverPath = request.getSession().getServletContext().getRealPath("/");
						String imgUrl = serverPath+pzObj[3];*/
						request.setAttribute("shilitu", pzObj[3]);
					}
				}
				list.add(pzObj);
				request.setAttribute("pzggId", pzggId);
			}
		}
		//拼接树节点数据
		StringBuffer node = new StringBuffer("[");
		node.append(" {id:"+0+", pId:"+0+", name:'"+typeName+"',open:true,isParent:true }, ");
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			if(cyyjId!=null&&!cyyjId.equals("")){	//查验依据查看
				node.append(" {id:"+obj[0]+", pid:"+0+", name:'"+obj[1]+"'},");
			}
			if(pzggId!=null&&!pzggId.equals("")){  //拍照规格
				node.append(" {id:"+obj[0]+", pid:"+0+", name:'"+obj[1]+"'},");
			}
		}
		node.append(" ];");
		request.setAttribute("node", node);
		request.setAttribute("typeName", typeName);
		return null;
	}
	
	/**
	 * 【查验项目/拍照规格】获取目录及信息
	 */
	@SuppressWarnings("unchecked")
	public List getCyxmOrPzggML(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String typeName = null;	 //类型名称
		String dmz = request.getParameter("dmz");		//代码值
		String type = request.getParameter("type");		//业务类型
		List list = new ArrayList();
		//【查验项目】获取查验项目目录103
		if(type=="cyxmml"||type.equals("cyxmml")){
			typeName="查验项目";
			String sql = "select dmz,dmsm,dmsm1,dmsm3 from T_XBYC_CODE where dmlb = '103' ";
			if(user.getName()!="jst"){
				sql+=" and zt = '1'";
			}
			list = yczdglDao.findSQL(sql);
			if(dmz!=null){
				sql+=" and dmz = '"+dmz+"'";
				Object[] cyObj = (Object[]) yczdglDao.findSQL(sql).get(0);
				request.setAttribute("cyyjOrpzgg", cyObj[2].toString().trim());
				request.setAttribute("dmsm", cyObj[1]);
			}
		}
		
		//【拍照规格】获取拍照规格目录33
		if(type=="pzggml"||type.equals("pzggml")){
			typeName="拍照规格";
			String sql = "select dmz,dmsm,dmsm1,dmsm3 from T_XBYC_CODE where dmlb = '33' ";
			if(user.getName()!="jst"){
				sql+=" and zt = '1'";
			}
			list = yczdglDao.findSQL(sql);
			if(dmz!=null){
				sql+=" and dmz = '"+dmz+"'";
				Object[] cyObj = (Object[]) yczdglDao.findSQL(sql).get(0);
				request.setAttribute("cyyjOrpzgg", cyObj[2].toString().trim());
				request.setAttribute("dmsm", cyObj[1]);
				request.setAttribute("shilitu", cyObj[3]);
			}
		}
		//拼接树节点数据
		StringBuffer node = new StringBuffer("[");
		node.append(" {id:"+0+", pId:"+0+", name:'"+typeName+"',open:true,isParent:true }, ");
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			if(type=="cyxmml"||type.equals("cyxmml")){	//查验依据查看
				node.append(" {id:"+obj[0]+", pid:"+0+", name:'"+obj[1]+"'},");
			}
			if(type=="pzggml"||type.equals("pzggml")){  //拍照规格
				node.append(" {id:"+obj[0]+", pid:"+0+", name:'"+obj[1]+"'},");
			}
		}
		node.append(" ];");
		request.setAttribute("node", node);
		request.setAttribute("typeName", typeName);
		request.setAttribute("type", type);
		return null;
	}
	
	/**
	 * 【使用性质/车辆类型】初始化编辑页面
	 */
	@SuppressWarnings("unchecked")
	public TXbycCode getCllxOrSyxzEditInit(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		TXbycCode code = new TXbycCode();
		String type = request.getParameter("type");
		String dmlb = request.getParameter("dmlb");
		String dmz = request.getParameter("dmz");
		
		//获取全部查验项/拍照项
		String cyxsql = " select dmz,dmsm from T_XBYC_CODE where dmlb='103' ";
		String pzxsql = " select dmz,dmsm from T_XBYC_CODE where dmlb='33' ";
		if(user.getName()!="jst"){
			cyxsql+=" and zt = '1'";
			pzxsql+=" and zt = '1'";
		}
		cyxsql+=" order by sxh";
		pzxsql+=" order by sxh";
		List<Object[]> cyxListAll = this.yczdglDao.findSQL(cyxsql);	//全部查验项
		List<Object[]> pzxListAll = this.yczdglDao.findSQL(pzxsql);	//全部拍照项
		request.setAttribute("cyxListAll", cyxListAll);
		request.setAttribute("pzxListAll", pzxListAll);
		
		if(type=="1"||type.equals("1")){	  //修改
			//获取修改指定项信息
			String hql = " from TXbycCode where 1=1";
			if(user.getName()!="jst"){
				hql+=" and zt = '1'";
			}
			if(dmlb!=null&&!dmlb.equals("")||dmz!=null&&!dmz.equals("")){
				hql+=" and dmlb = '"+dmlb+"' and dmz='"+dmz+"'";
			}
			code = (TXbycCode) this.yczdglDao.getRepositories(hql).get(0);	//获取要修改的对象信息
			Object[] cyx = code.getDmsm1().split(",");
			Object[] pzx = code.getDmsm3().split(",");
			request.setAttribute("yanche", code);
			request.setAttribute("cyx", cyx);		//查验项
			request.setAttribute("pzx", pzx);		//拍照项
		}else if(type=="2"||type.equals("2")){ //添加
			
		}
		request.setAttribute("type", type);
		request.setAttribute("dmlb", dmlb);
		request.setAttribute("dmz", dmz);
		return code;
	}
	
	/**
	 * 【查验项目/拍照规格】初始化编辑页面
	 * 【其它项目】初始化编辑页面
	 */
	public TXbycCode getCyOrPzEditInit(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String dmlb = request.getParameter("dmlb");
		String dmz = request.getParameter("dmz");
		String type = request.getParameter("type");//1.修改/2.添加
		String yeType = request.getParameter("yeType"); //cy:查验项目/pz:拍照规格
		TXbycCode code = new TXbycCode();
		if(type=="1"||type.equals("1")){
			String hql = " from TXbycCode where 1=1";
			if(user.getName()!="jst"){
				hql+=" and zt = '1'";
			}
			if(dmlb!=null&&!dmlb.equals("")||dmz!=null&&!dmz.equals("")){
				hql+=" and dmlb = '"+dmlb+"' and dmz='"+dmz+"'";
			}
			code = (TXbycCode) this.yczdglDao.getRepositories(hql).get(0);	//获取要修改的对象信息
			request.setAttribute("yanche", code);
		}else{
			
		}
		request.setAttribute("type", type);
		request.setAttribute("yeType", yeType);
		request.setAttribute("dmlb", dmlb);
		return null;
	}
	

	
	/**
	 * 提交编辑
	 */
	public Integer saveOrupdateYanche(HttpServletRequest request) throws Exception {
		//参数
		try {
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String codeId = request.getParameter("codeid");
			String dmsm = request.getParameter("dmsm");			//类型名
			String zt = request.getParameter("zt");				//状态
			String dmsm2 = request.getParameter("dmsm2");		//是否全检
			String dmsm1 = request.getParameter("dmsm1");		//检查项
			String dmsm4 = request.getParameter("dmsm4");		//是否全拍
			String dmsm3 = request.getParameter("dmsm3");		//拍照规格项	
			String dmsx = request.getParameter("dmsx");			//修改权限
			String yeType = request.getParameter("yeType");		
			String type = request.getParameter("type");			//类型1.修改 /2.添加
			String fileName = null;

			if(yeType=="pz"||yeType.equals("pz")){
				fileName = (String) request.getAttribute("fileName");
				dmsm3="/images/new/"+fileName;
			}
			
			TXbycCode retult = new TXbycCode();
			if(type=="1"||type.equals("1")){//修改
				TXbycCode code = (TXbycCode) this.yczdglDao.getRepositories(" from TXbycCode where codeId = '"+codeId+"'").get(0);
				request.setAttribute("imgUrl", code.getDmsm3());//获取原先图片路径
				if(fileName!=null){
					code.setDmsm3(dmsm3);
				}
				code.setZt(zt);
				code.setDmsm(dmsm);
				code.setDmsm1(dmsm1);
				code.setDmsm2(dmsm2);				
				code.setDmsm4(dmsm4);
				code.setDmsx(dmsx);
				code.setCzr(user.getName());
				code.setCzrxm(user.getYgxm());
				code.setCzbm(user.getBmmc());
				code.setCzrq(new Date());
				code.setCzip(ToolsUtil.getIpAddr(request));
				retult=this.yczdglDao.updateRepository(code);
				request.setAttribute("yanche", code);
			}else{//添加
				String dmz = request.getParameter("dmz");
				String dmlb = request.getParameter("dmlb");	
				if(dmlb==null||dmlb.equals("")){
					dmlb=request.getParameter("dmlb1");
				}
				Object objlbms = this.yczdglDao.findSQL(" select lbms from T_XBYC_CODE where dmlb='"+dmlb+"' and rownum=1").get(0);
				String lbms = objlbms.toString();
				Object objSxh = this.yczdglDao.findSQL(" select max(sxh) from T_XBYC_CODE where dmlb = '"+dmlb+"'").get(0);
				Integer sxh = Integer.valueOf(objSxh.toString())+1;
				TXbycCode code = new TXbycCode(dmlb, lbms, dmz, dmsm, dmsm1, dmsm2, dmsm3, dmsm4, dmsx, sxh.toString(), 
						zt, user.getName(), user.getYgxm(), user.getBmmc(), new Date(), ToolsUtil.getIpAddr(request));
				retult = this.yczdglDao.addRepository(code);
				request.setAttribute("yanche", code);
			}
			if(retult!=null){
				return 0;
			}else{
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * 上传图片
	 * return 0 成功，1.不成功，2.文件已存在，3.文件过大
	 */
	public Integer uploadFile(HttpServletRequest request, File src,String fileName) throws Exception{
		try {
			int fileType = 6;		//文件类型(6.其它)
			int size = 1048576;		//文件大小(设定为1G=1048576K)
			String [] temp = fileName.split("\\.");	//拆分文件获取文件名和后缀
			//判断文件是否存在
			@SuppressWarnings("unused")
			TXbycCode code = (TXbycCode) request.getAttribute("yanche");
			String servletPath = request.getSession().getServletContext().getRealPath("/");  //获取工程绝对路径
			String dstPath = servletPath+"\\images\\new\\"+fileName;
			File dst = new File(dstPath);	
			if(dst.exists()){
				return 4;
			}
			//根据文件格式获取文件类型（1.图片，2.文档，3.视频，4.种子，5.音乐，6.其它）
			FileInputStream fis = new FileInputStream(src);
			FileType value = FileType.getType(fis);
			fileType = FileType.isFileType(value);
			if(fileType!=1){
				return 5;
			}
			
			 //上传文件(调用未设置文件大小上传接口【可改】)
		    String rest = UploadTools.uploadTemplate(src, dst, size);
		    if(rest=="failure"||rest.equals("failure")){
		      return 3;
		    }else if(rest=="big"||rest.equals("big")){
		      return 2;
		    }else{
		      return 0;
		    }

		} catch (Exception e) {
			e.printStackTrace();
			return 3;
		}
	}

	/**
	 * 判断代码值是否存在
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public Integer exitesByDmz(HttpServletRequest request) throws Exception {
		String dmlb = request.getParameter("dmlb");
		String dmz = request.getParameter("dmz");
		String sql = " select * from T_XBYC_CODE where dmlb = '"+dmlb+"' and dmz = '"+dmz+"'";
		List list = this.yczdglDao.findSQL(sql);
		if(list.size()>0){
			return 1;
		}else{
			return 0;
		}
	}
	
	
	public YczdglDao getYczdglDao() {
		return yczdglDao;
	}
	public void setYczdglDao(YczdglDao yczdglDao) {
		this.yczdglDao = yczdglDao;
	}

	public YhlxxzDao getYhlxxzDao() {
		return yhlxxzDao;
	}
	public void setYhlxxzDao(YhlxxzDao yhlxxzDao) {
		this.yhlxxzDao = yhlxxzDao;
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

	public TXbycGpsLogDao getTxbycgpslogDao() {
		return txbycgpslogDao;
	}

	public void setTxbycgpslogDao(TXbycGpsLogDao txbycgpslogDao) {
		this.txbycgpslogDao = txbycgpslogDao;
	}






	



}
