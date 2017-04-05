package com.ycszh.ssh.service.veh.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.ycszh.util.DateUtil;
import com.ycszh.util.StringUtil;
import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.dao.drv.SlgDrvFileUploadDao;
import com.ycszh.ssh.dao.veh.IItopscDao;
import com.ycszh.ssh.dao.veh.SlgVehDao;
import com.ycszh.ssh.hbm.drv.SlgDrvXxcjb;
import com.ycszh.ssh.hbm.drv.SlgDrvXxcjbLog;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.drv.SlgZjxxb;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspb;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspbLog;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspbTemp;
import com.ycszh.ssh.hbm.veh.DbjgFzxp;
import com.ycszh.ssh.hbm.veh.DbjgZjxxb;
import com.ycszh.ssh.hbm.veh.DbjgZjxxbLog;
import com.ycszh.ssh.hbm.veh.TemporaryLicense;
import com.ycszh.ssh.hbm.veh.VehPodbsp;
import com.ycszh.ssh.hbm.veh.VehicleTempMidInJdc;
import com.ycszh.ssh.hbm.veh.VehicleTempMidTest;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidIn;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidOut;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFlow;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFlowLog;
import com.ycszh.ssh.service.drv.SlgDrvService;
import com.ycszh.ssh.service.dydj.IDydjService;
import com.ycszh.ssh.service.veh.SlgVehService;
import com.ycszh.util.ImageUtils;

public class SlgVehServiceImpl implements SlgVehService {
	
	private final static Logger log = Logger.getLogger(SlgVehServiceImpl.class);
	private DefaultDao defaultDao;
	private SlgVehDao slgVehDao;
	private SlgDrvFileUploadDao slgDrvFileUpload;
	private SlgDrvService slgDrvService;
	private IDydjService dydjService;
	private IItopscDao itopscDao;
	private String uri = "";
	
	public Integer insertOrUpdateSlgDrvXxcjb(DbjgZjxxb dbZjxxb, DydjYwsbspb dydjYwsbspb, HttpServletRequest request,File file1,File file2,File file01,File file02,File gpyfile0,File gpyfile1,File gpyfile2,File gpyfile3,File gpyfile4,File gpyfile5,File gpyfile6,File gpyfile7) throws Exception{
		int result = -1;
		if(dbZjxxb != null){
			String id = dbZjxxb.getId();
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			if("1".equals(dbZjxxb.getBllx())){
				dbZjxxb.setDbrsfzmmc("");
				dbZjxxb.setDbrSfzCardname1("");
				dbZjxxb.setDbrSfzCardsex1("");
				dbZjxxb.setDbrSfzCardno1("");
				dbZjxxb.setDbrSfzCardaddress1("");
				file2 = null;
			}
			dbZjxxb.setLrr(user.getName());
			dbZjxxb.setLrrxm(user.getYgxm());
			dbZjxxb.setLrbmdm(user.getBmid());
			dbZjxxb.setLrbmmc(user.getBmmc());
			dbZjxxb.setLrsj(new Date());
			dbZjxxb.setLrip(getLoginIp(request));
			dbZjxxb.setLrmac(request.getLocalAddr());
			String hphm = dbZjxxb.getHphm();
			if(!StringUtil.isNull(hphm)){
				hphm = hphm.toUpperCase();
				if(hphm.contains("粤")){
					hphm = hphm.replaceAll("粤", "");
				}
				dbZjxxb.setHphm(hphm);
			}
			if(id == null || "".equals(id)){
				/********************************以下代码在测试环境下需注释*****************************************/
				String basePath = request.getRealPath("/");
				File f = new File(basePath + "\\images\\shuiyin.png");
				if(null != file1){
					String strtemp = slgDrvFileUpload.uploadFile(file1);
					if(null != strtemp && !"".equals(strtemp)){
						dbZjxxb.setDsrSfzCardphoto1(strtemp);
					}else{
						Exception etemp = new Exception("异常:当事人身份证照片上传失败,请重新点击保存按钮!");
						throw etemp;
					}
				}
				if(null != file2){
					String strtemp = slgDrvFileUpload.uploadFile(file2);
					if(null != strtemp && !"".equals(strtemp)){
						dbZjxxb.setDbrSfzCardphoto1(strtemp);
					}else{
						Exception etemp = new Exception("异常:代办人身份证照片上传失败,请重新点击保存按钮!");
						throw etemp;
					}
				}
				if(null != file01){
					ImageUtils.pressImage(file01, f);
					String strtemp = slgDrvFileUpload.uploadFile(file01);
					if(null != strtemp && !"".equals(strtemp)){
						dbZjxxb.setDsrSfzSxtZp(strtemp);
					}else{
						Exception etemp = new Exception("异常:现场头像照片上传失败,请重新点击保存按钮!");
						throw etemp;
					}
				}
				if(null != file02){
					ImageUtils.pressImage(file02, f);
					String strtemp = slgDrvFileUpload.uploadFile(file02);
					if(null != strtemp && !"".equals(strtemp)){
						dbZjxxb.setDsrSfzGpyZp(strtemp);
					}else{
						Exception etemp = new Exception("异常:高拍仪照片上传失败,请重新点击保存按钮!");
						throw etemp;
					}
				}
				/********************************以上代码在测试环境下需注释*****************************************/
				String lsh = request.getParameter("lshval");
				String firtst="";
				if(lsh != null && !"".equals(lsh)){
					firtst= lsh.substring(0,1);
				}
				if(lsh != null && !"".equals(lsh)&&!"L".equals(firtst)){
					if("D".equals(firtst)){  //抵押登记
						//根据流水号查询抵押登记受理信息
						DydjYwsbspbTemp dydjYwsbspb2 = dydjService.getDydjYwwbspByLsh(request, lsh);
						if(dydjYwsbspb2 == null){
							return -1;
						}
						
						if("11".equals(dydjYwsbspb2.getSqlx())){
							//如果是代办个人，则把抵押人身份证件信息保存到当事人1身份证
							dbZjxxb.setDsrSfzCardname1(dydjYwsbspb2.getDyrSfzCardname());
							dbZjxxb.setDsrSfzCardno1(dydjYwsbspb2.getDyrSfzCardno());
							dbZjxxb.setDsrSfzCardsex1(dydjYwsbspb2.getDyrSfzCardsex());
							dbZjxxb.setDsrSfzCardaddress1(dydjYwsbspb2.getDyrSfzCardaddress());
							//上传图片
							if(!StringUtil.isNull(dydjYwsbspb2.getDyrSfzCardphotoId())){
								int photoid = Integer.parseInt(dydjYwsbspb2.getDyrSfzCardphotoId());
								byte[] bytes = dydjService.getImageBlob(request, photoid);
								if(bytes != null){
									String imgtemp = slgDrvFileUpload.uploadFileBybyte(bytes);
									dbZjxxb.setDsrSfzCardphoto1(imgtemp);
								}
							}
						}else if("12".equals(dydjYwsbspb2.getSqlx())){
							//如果是代办单位，则把抵押人组织机构证件信息保存到当事人1组织机构证,把抵押人身份证件信息保存到代办人2身份证
							dbZjxxb.setDsrZzjgZh1(dydjYwsbspb2.getDyrZzjgZh());
							dbZjxxb.setDsrZzjgFrdb1(dydjYwsbspb2.getDyrZzjgFrdb());
							dbZjxxb.setDsrZzjgYyzz1(dydjYwsbspb2.getDyrZzjgYyzz());
							dbZjxxb.setDsrZzjgDwmc1(dydjYwsbspb2.getDyrZzjgDwmc());
							dbZjxxb.setDsrZzjgDz1(dydjYwsbspb2.getDyrZzjgDz());
							dbZjxxb.setDsrZzjgNjrq1(dydjYwsbspb2.getDyrZzjgNjrq());
							dbZjxxb.setDsrZzjgNjyxq1(dydjYwsbspb2.getDyrZzjgNjyxq());
							
							dbZjxxb.setDbrSfzCardname2(dydjYwsbspb2.getDyrSfzCardname());
							dbZjxxb.setDbrSfzCardno2(dydjYwsbspb2.getDyrSfzCardno());
							dbZjxxb.setDbrSfzCardsex2(dydjYwsbspb2.getDyrSfzCardsex());
							dbZjxxb.setDbrSfzCardaddress2(dydjYwsbspb2.getDyrSfzCardaddress());
							//上传图片
							if(!StringUtil.isNull(dydjYwsbspb2.getDyrSfzCardphotoId())){
								int photoid = Integer.parseInt(dydjYwsbspb2.getDyrSfzCardphotoId());
								byte[] bytes = dydjService.getImageBlob(request, photoid);
								if(bytes != null){
									String imgtemp = slgDrvFileUpload.uploadFileBybyte(bytes);
									dbZjxxb.setDbrSfzCardphoto2(imgtemp);
								}
							}
							
						}else{
							//如果是车主自行办理，则需读取证件信息，如果是个人车辆（本人办理）则只需要读取当事人信息，如果是单位车来那个（他人代办），则还需要读取代办人信息
							dbZjxxb.setDbrZzjgZh2(dbZjxxb.getDbrZzjgZh1());
							dbZjxxb.setDbrZzjgFrdb2(dbZjxxb.getDbrZzjgFrdb1());
							dbZjxxb.setDbrZzjgYyzz2(dbZjxxb.getDbrZzjgYyzz1());
							dbZjxxb.setDbrZzjgDwmc2(dbZjxxb.getDbrZzjgDwmc1());
							dbZjxxb.setDbrZzjgDz2(dbZjxxb.getDbrZzjgDz1());
							dbZjxxb.setDbrZzjgNjrq2(dbZjxxb.getDbrZzjgNjrq1());
							dbZjxxb.setDbrZzjgNjyxq2(dbZjxxb.getDbrZzjgNjyxq1());
							
							dbZjxxb.setDbrSfzCardname2(dbZjxxb.getDbrSfzCardname1());
							dbZjxxb.setDbrSfzCardno2(dbZjxxb.getDbrSfzCardno1());
							dbZjxxb.setDbrSfzCardsex2(dbZjxxb.getDbrSfzCardsex1());
							dbZjxxb.setDbrSfzCardaddress2(dbZjxxb.getDbrSfzCardaddress1());
							dbZjxxb.setDbrSfzCardphoto2(dbZjxxb.getDbrSfzCardphoto1());
						}
						
						
						//把银行（抵押权人）组织机构证件信息保存到代办人1组织机构
						dbZjxxb.setDbrZzjgZh1(dydjYwsbspb2.getYhZzjgZh());
						dbZjxxb.setDbrZzjgFrdb1(dydjYwsbspb2.getYhZzjgFrdb());
						dbZjxxb.setDbrZzjgYyzz1(dydjYwsbspb2.getYhZzjgYyzz());
						dbZjxxb.setDbrZzjgDwmc1(dydjYwsbspb2.getYhZzjgDwmc());
						dbZjxxb.setDbrZzjgDz1(dydjYwsbspb2.getYhZzjgDz());
						dbZjxxb.setDbrZzjgNjrq1(dydjYwsbspb2.getYhZzjgNjrq());
						dbZjxxb.setDbrZzjgNjyxq1(dydjYwsbspb2.getYhZzjgNjyxq());
						//把银行（抵押权人）身份证件信息保存到代办人1身份证
						dbZjxxb.setDbrSfzCardname1(dydjYwsbspb2.getYhSfzCardname());
						dbZjxxb.setDbrSfzCardno1(dydjYwsbspb2.getYhSfzCardno());
						dbZjxxb.setDbrSfzCardsex1(dydjYwsbspb2.getYhSfzCardsex());
						dbZjxxb.setDbrSfzCardaddress1(dydjYwsbspb2.getYhSfzCardaddress());
						//上传图片
						if(!StringUtil.isNull(dydjYwsbspb2.getYhSfzCardphotoId())){
							int photoid = Integer.parseInt(dydjYwsbspb2.getYhSfzCardphotoId());
							byte[] bytes = dydjService.getImageBlob(request, photoid);
							if(bytes != null){
								String imgtemp = slgDrvFileUpload.uploadFileBybyte(bytes);
								dbZjxxb.setDbrSfzCardphoto1(imgtemp);
							}
						}
						
						dbZjxxb.setLsh(lsh);
						DbjgZjxxb zjxxb1 = this.slgVehDao.addRepository(dbZjxxb);
						//添加日志
						DbjgZjxxbLog dZjxxbLog = new DbjgZjxxbLog();
						dZjxxbLog = (DbjgZjxxbLog)getXclog(dZjxxbLog, zjxxb1);
						dZjxxbLog.setCzr(user.getName());
						dZjxxbLog.setCzrxm(user.getYgxm());
						dZjxxbLog.setCzrbm(user.getBmid());
						dZjxxbLog.setCznr("I");
						dZjxxbLog.setCzsj(new Date());
						dZjxxbLog.setCzip(getLoginIp(request));
						dZjxxbLog.setCzmac("");
						this.slgVehDao.addObj(dZjxxbLog, request);
						//回填受理主表（DYDJ_YWSBSPB ）
						String upsql = "update dydj_ywsbspb set cg_yhdm = '"+user.getName()+"', cg_yhxm = '"+user.getYgxm()+"', cg_ip = '"+getLoginIp(request)+"', cg_yhsj = to_char(sysdate, 'yyyy-MM-dd HH24:mi:ss') where id = "+dydjYwsbspb2.getId();
						this.slgVehDao.updateRepositoryBySql(upsql);
						//受理日志表（DYDJ_YWSBSPB_log）
						DydjYwsbspbLog ywsbspbLog = new DydjYwsbspbLog();
						ywsbspbLog = (DydjYwsbspbLog)getXclog(ywsbspbLog, dydjYwsbspb2);
						ywsbspbLog.setCzr(user.getName());
						ywsbspbLog.setCzrxm(user.getYgxm());
						ywsbspbLog.setCzrbm(user.getBmid());
						ywsbspbLog.setCzsj(new Date());
						ywsbspbLog.setCznr("窗口已受理");
						ywsbspbLog.setCzip(getLoginIp(request));
						this.slgVehDao.addObj(dZjxxbLog, request);
						result = 1;
						
					}else{
						//机动车受理
						if("D".equals(dbZjxxb.getYwlx()) && ("D:P".equals(dbZjxxb.getYwyy()) || "D:A".equals(dbZjxxb.getYwyy()) || "D:J".equals(dbZjxxb.getYwyy()))){
							//根据流水号从out表添加到in表
							String sql = "insert into vehicle_temp_mid_write  select * from vehicle_temp_mid_read  where lsh= '"+lsh+"'";
							this.slgVehDao.updateRepositoryBySql(sql);
						}
						//根据流水号从_in表查询信息
						VehicleTempMidIn tempMidIn = getVehicleMidInByLsh(lsh, request);
						//添加到_test表
						if(tempMidIn != null){
							VehicleTempMidTest tempMidTest = new VehicleTempMidTest();
							tempMidTest = (VehicleTempMidTest)getXclog(tempMidTest, tempMidIn);
							tempMidTest.setLocalIshandle("1");
							tempMidTest.setLocalOperat(dbZjxxb.getLrr());
							tempMidTest.setLocalOpertime(new Date());
							tempMidTest.setLocalName(dbZjxxb.getLrrxm());
							this.slgVehDao.addObj(tempMidTest, request);
							//修改_in表
							tempMidIn.setLocalIshandle("1");
							tempMidIn.setLocalOperat(dbZjxxb.getLrr());
							tempMidIn.setLocalOpertime(new Date());
							tempMidIn.setLocalName(dbZjxxb.getLrrxm());
							this.slgVehDao.updateObj(tempMidIn);
							
							dbZjxxb.setLsh(lsh);
							DbjgZjxxb zjxxb1 = this.slgVehDao.addRepository(dbZjxxb);
							//添加日志
							DbjgZjxxbLog dZjxxbLog = new DbjgZjxxbLog();
							dZjxxbLog = (DbjgZjxxbLog)getXclog(dZjxxbLog, zjxxb1);
							dZjxxbLog.setCzr(user.getName());
							dZjxxbLog.setCzrxm(user.getYgxm());
							dZjxxbLog.setCzrbm(user.getBmid());
							dZjxxbLog.setCznr("I");
							dZjxxbLog.setCzsj(new Date());
							dZjxxbLog.setCzip(getLoginIp(request));
							dZjxxbLog.setCzmac("");
							this.slgVehDao.addObj(dZjxxbLog, request);
							result = 1;
						}
					}
				
					
					//受理成功时，修改流水状态
					YwlsglVehFlow ywFlow = (YwlsglVehFlow) this.defaultDao.getRepository(lsh, YwlsglVehFlow.class);
					if(ywFlow != null){
						ywFlow.setLszt("E");
						this.defaultDao.updateRepository(ywFlow);
						YwlsglVehFlowLog log = new YwlsglVehFlowLog();
						log = (YwlsglVehFlowLog) getXclog(log, ywFlow);
						log.setCzr(user.getYgid());
						log.setCzrxm(user.getYgxm());
						log.setCzrbm(user.getBmid());
						log.setCzrbmKj(getDeptUpid(user.getBmid()));
						log.setCzip(getLoginIp(request));
						log.setCznr("U");
						log.setCzsj(new Date());
						this.defaultDao.addRepository(log);
					}
					
			
			 }else{
					DbjgZjxxb zjxxb = this.slgVehDao.addRepository(dbZjxxb);
					//添加辅助相片表
					String filecount = request.getParameter("filecount");
				if(null != filecount){
					if(!"0".equals(filecount)) {
						for(int i=0;i<Integer.parseInt(filecount);i++){
							DbjgFzxp fzxpBean = new DbjgFzxp();
							fzxpBean.setJdcxxcjid(zjxxb.getId());
							fzxpBean.setYwlxid(zjxxb.getYwlx());
							fzxpBean.setYwlxms(SlgVehServiceImpl.getYwlxMessage(zjxxb.getYwlx()));
							fzxpBean.setPzid(request.getParameter("gpyid"+i));
							fzxpBean.setPztitle(request.getParameter("gpytitle"+i));
							//获取动态拍照的图文码
							File file = null;
							switch(i) {
							case 0:
								file = gpyfile0;
								break;
							case 1:
								file = gpyfile1;
								break;
							case 2:
								file = gpyfile2;
								break;
							case 3:
								file = gpyfile3;
								break;
							case 4:
								file = gpyfile4;
								break;
							case 5:
								file = gpyfile5;
								break;
							case 6:
								file = gpyfile6;
								break;
							case 7:
								file = gpyfile7;
								break;
							default:
								break;
							}
							if(null != file){
								ImageUtils.pressImage(file, f);
								String twm = slgDrvFileUpload.uploadFile(file);
								if(null != twm && !"".equals(twm)){
									fzxpBean.setTwm(twm);
								}else{
									Exception etemp = new Exception("异常:高拍仪照片上传失败,请重新点击保存按钮!");
									throw etemp;
								}
							}
							fzxpBean.setCzr(user.getName());
							fzxpBean.setCzrxm(user.getYgxm());
							fzxpBean.setCzbm(user.getBmmc());
							fzxpBean.setCzrq(new Date());
							fzxpBean.setCzip(getLoginIp(request));
							fzxpBean.setVehOrDrv("VEH");
							
							this.defaultDao.addRepository(fzxpBean);
						}
					  }
					}
					
					VehicleTempMidInJdc vehInJdc = new VehicleTempMidInJdc();
					vehInJdc.setLsh(zjxxb.getId());
					vehInJdc.setHphm(zjxxb.getHphm());
					vehInJdc.setHpzl(zjxxb.getHpzl());
					vehInJdc.setSfzmhm(zjxxb.getDsrsfzmhm());
					vehInJdc.setSyr(zjxxb.getDsrxm());
					vehInJdc.setNetSmzmhm(zjxxb.getDbrsfzmhm());
					vehInJdc.setNetXm(zjxxb.getDbrxm());
					vehInJdc.setLocalIshandle("1");
					vehInJdc.setLocalOperat(zjxxb.getLrr());
					vehInJdc.setLocalName(zjxxb.getLrrxm());
					vehInJdc.setLocalOpertime(new Date());
					vehInJdc.setPostZj("0");
					vehInJdc.setPostTp("0");
					vehInJdc.setYwlx("B");
					vehInJdc.setYwyy("B");
					String bzval = "";
					if(zjxxb.getYwyy() != null && !"".equals(zjxxb.getYwyy())){
						String ywlxval = "";
						String ywyyval = "";
						String[] ywyys = zjxxb.getYwyy().split(",");
						if(ywyys != null && ywyys.length > 0){
							for(int m = 0; m < ywyys.length; m++){
								String ywlxs  = ywyys[m];
								if(ywlxs != null && !"".equals(ywlxs)){
									String[] ywyyarr = ywlxs.split(":");
									ywlxval = ywyyarr[0];
									ywyyval += ywyyarr[1]+",";
								}
							}
						}
						if(ywyyval.endsWith(",")){
							ywyyval = ywyyval.substring(0, ywyyval.length()-1);
						}
						bzval = ywlxval+":"+ywyyval;
					}else{
						bzval= zjxxb.getYwlx();
					}
					vehInJdc.setBz(bzval);
					this.slgVehDao.addObj(vehInJdc, request);
					//添加日志
					DbjgZjxxbLog dZjxxbLog = new DbjgZjxxbLog();
					dZjxxbLog = (DbjgZjxxbLog)getXclog(dZjxxbLog, zjxxb);
					dZjxxbLog.setCzr(user.getName());
					dZjxxbLog.setCzrxm(user.getYgxm());
					dZjxxbLog.setCzrbm(user.getBmid());
					dZjxxbLog.setCznr("I");
					dZjxxbLog.setCzsj(new Date());
					dZjxxbLog.setCzip(getLoginIp(request));
					dZjxxbLog.setCzmac("");
					this.slgVehDao.addObj(dZjxxbLog, request);
					result = 1;
				}
				
				//写入新天地审计信息
				String zbyzflag = request.getParameter("zbyzflag");
				if("1".equals(zbyzflag)){
					StringBuffer srcs = new StringBuffer("<request><head><dqgw>3</dqgw></head><body>");
					srcs.append("<lsh>"+(dbZjxxb.getLsh()==null?"":dbZjxxb.getLsh())+"</lsh>");
					srcs.append("<hphm>"+(dbZjxxb.getHphm()==null?"":dbZjxxb.getHphm())+"</hphm>");
					srcs.append("<hpzl>"+(dbZjxxb.getHpzl()==null?"":dbZjxxb.getHpzl())+"</hpzl>");
					srcs.append("<syxz>"+(dbZjxxb.getSyxz()==null?"":dbZjxxb.getSyxz())+"</syxz>");
					srcs.append("<sfzmmc>"+(dbZjxxb.getDsrsfzmmc()==null?"":dbZjxxb.getDsrsfzmmc())+"</sfzmmc>");
					srcs.append("<sfzmhm>");
					srcs.append(dbZjxxb.getDsrsfzmhm()==null?dbZjxxb.getDsrZzjgZh1():dbZjxxb.getDsrsfzmhm());
					srcs.append("</sfzmhm>");
					srcs.append("<syr>");
					srcs.append(dbZjxxb.getDsrxm());
					srcs.append("</syr>");
					srcs.append("<ip>"+(dbZjxxb.getLrip()==null?"":dbZjxxb.getLrip())+"</ip>");
					srcs.append("<sffq>"+(dbZjxxb.getSffq()==null?"":dbZjxxb.getSffq())+"</sffq>");
					srcs.append("<jhzbh>"+(dbZjxxb.getJhzbh()==null?"":dbZjxxb.getJhzbh())+"</jhzbh>");
					srcs.append("<ywlx>"+(dbZjxxb.getYwlx()==null?"":dbZjxxb.getYwlx())+"</ywlx>");
					srcs.append("<ywyy>"+(dbZjxxb.getYwyy()==null?"":dbZjxxb.getYwyy())+"</ywyy>");
					srcs.append("<hdfs>"+(dbZjxxb.getHdfs()==null?"":dbZjxxb.getHdfs())+"</hdfs>");
					srcs.append("<tyblsh>"+(dbZjxxb.getTyblsh()==null?"":dbZjxxb.getTyblsh())+"</tyblsh>");
					srcs.append("<clsbdh></clsbdh>");
					srcs.append("<cllx>"+(dbZjxxb.getCllx()==null?"":dbZjxxb.getCllx())+"</cllx>");
					srcs.append("<syq></syq>");
					srcs.append("<glbm></glbm>");
					srcs.append("<zczxlsh>"+(dbZjxxb.getLsh()==null?"":dbZjxxb.getLsh())+"</zczxlsh>");
					srcs.append("<zczxhfhljzyqchzx>"+(dbZjxxb.getZczxhfhljzyqchzx()==null?"":dbZjxxb.getZczxhfhljzyqchzx())+"</zczxhfhljzyqchzx>");
					srcs.append("<tbr>"+(dbZjxxb.getTbr()==null?"":dbZjxxb.getTbr())+"</tbr>");
					srcs.append("<tbyy>"+(dbZjxxb.getTbyy()==null?"":dbZjxxb.getTbyy())+"</tbyy>");
					srcs.append("<zblx>"+(dbZjxxb.getZblx()==null?"":dbZjxxb.getZblx())+"</zblx>");
					srcs.append("<zbh>"+(dbZjxxb.getZbh()==null?"":dbZjxxb.getZbh())+"</zbh>");
					srcs.append("</body></request>");
					String msg = this.slgVehDao.insertShenjiinfo(request, StringUtil.isNull(dbZjxxb.getZjxxblsh()) ? dbZjxxb.getLsh():dbZjxxb.getZjxxblsh(), srcs.toString());
					if(!"[0000]".equals(msg.substring(0, 6))){
						throw new Exception(msg.substring(6));
					}
				}
				
			}else{
				//修改
				DbjgZjxxb dbZjxxb2 = this.slgVehDao.updateRepository(dbZjxxb);
				//添加日志
				DbjgZjxxbLog dZjxxbLog = new DbjgZjxxbLog();
				dZjxxbLog = (DbjgZjxxbLog)getXclog(dZjxxbLog, dbZjxxb2);
				dZjxxbLog.setCzr(user.getName());
				dZjxxbLog.setCzrxm(user.getYgxm());
				dZjxxbLog.setCzrbm(user.getBmid());
				dZjxxbLog.setCznr("U");
				dZjxxbLog.setCzsj(new Date());
				dZjxxbLog.setCzip(getLoginIp(request));
				dZjxxbLog.setCzmac("");
				this.slgVehDao.addObj(dZjxxbLog, request);
				result = 1;
			}
		}
		return result;
	}
	
	public static String getYwlxMessage(String ywlxdm) {
		 if("O".equals(ywlxdm)){
			  return "临时号牌";
		 }else if("S".equals(ywlxdm)){
			  return "锁定业务";
		 }else if("LF".equals(ywlxdm)){
			  return "换领登记证书";
		 }else if("EE".equals(ywlxdm)){
			  return "抵押登记(新版试点)";
		 }else if("PR".equals(ywlxdm)){
			  return "免检车核发检验标志";
		 }else if("F".equals(ywlxdm)){
			  return "停复驶业务";
		 }else if("G".equals(ywlxdm)){
			  return "注销登记";
		 }else if("I".equals(ywlxdm)){
			  return "转入业务";
		 }else if("J".equals(ywlxdm)){
			  return "校车登记";
		 }else if("K".equals(ywlxdm)){
			  return "补换牌证合格标志";
		 }else if("N".equals(ywlxdm)){
			  return "被盗抢记录";
		 }else if("P".equals(ywlxdm)){
			  return "申请合格标志";
		 }else if("Q".equals(ywlxdm)){
			  return "委托检验";
		 }else if("R".equals(ywlxdm)){
			  return "受托检验";
		 }else if("T".equals(ywlxdm)){
			  return "解除监管";
		 }else if("U".equals(ywlxdm)){
			  return "转出/注销恢复";
		 }else if("W".equals(ywlxdm)){
			  return "电子档案补录";
		 }else if("X".equals(ywlxdm)){
			  return "大吨小标更正";
		 }else if("D".equals(ywlxdm)){
			  return "变更及备案登记";
		 }else if("H".equals(ywlxdm)){
			  return "临时入境";
		 }else if("L".equals(ywlxdm)){
			  return "补换登记证书";
		 }else if("M".equals(ywlxdm)){
			  return "档案更正";
		 }else if("DP".equals(ywlxdm)){
			  return "变更联系方式";
		 }
		 return "";
	}

	@SuppressWarnings("unchecked")
	public VehicleTempMidIn getVehicleMidInByLsh(String lsh, HttpServletRequest request) throws Exception{
		VehicleTempMidIn vehi = null;
		List vehiCleList = null;
		//String lsh = request.getParameter("lsh");
		String hql = "from VehicleTempMidIn t where 1=1";
		if(lsh != null && !"".equals(lsh)){
			hql += " and t.lsh = '"+lsh+"'";
		}
		vehiCleList = this.slgVehDao.getRepositories(hql);
		if(vehiCleList != null && vehiCleList.size() > 0){
			vehi = (VehicleTempMidIn) vehiCleList.get(0);
		}
		return vehi; 
	}
	
	//根据流水号调用临牌接口
	public TemporaryLicense getTemporaryLicenseByLsh(String lsh, HttpServletRequest request) throws Exception{
		TemporaryLicense tLicense =  this.slgVehDao.getTemporaryLicense(lsh);
		return tLicense;
	}
	
	@SuppressWarnings("unchecked")
	public VehicleTempMidOut getVehMidOutByLsh(String lsh, HttpServletRequest request) throws Exception{
		VehicleTempMidOut vMidOut = null;
		List vehList = null;
		String hql = "from VehicleTempMidOut t where 1=1";
		if(!StringUtil.isNull(lsh)){
			hql += " and t.lsh = '"+lsh+"'";
		}
		vehList = this.slgVehDao.getRepositories(hql);
		if(vehList != null && vehList.size() > 0){
			vMidOut = (VehicleTempMidOut)vehList.get(0);
		}
		return vMidOut;
	}
	
	@SuppressWarnings("unchecked")
	public List getRepositories(String hql) throws Exception {
		return slgVehDao.getRepositories(hql);
	}
	
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
	
	//受理查询
	@SuppressWarnings({ "unchecked", "static-access" })
	public List<DbjgZjxxb> getSlCxList(HttpServletRequest request,
			int currentpage,  String cztype) throws Exception{
		int count=0;
		Map map=new HashedMap();
		StringBuffer hqlListBuff = new StringBuffer("SELECT s from DbjgZjxxb s where 1=1 ");
		StringBuffer hqlCountBuff = new StringBuffer("select count(s) from DbjgZjxxb s where 1=1 ");
		StringBuffer strCondition = new StringBuffer("");
		String lsh = request.getParameter("lsh");
		String hphm = request.getParameter("hphm");
		String hpzl = request.getParameter("hpzl");
		String ywlx = request.getParameter("ywlx");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String jbr = request.getParameter("jbr");
		String jbrbm = request.getParameter("jbrbm");
		String ywyy = request.getParameter("ywyy");
		String shzt = request.getParameter("shzt");
		String shr = request.getParameter("shr");
		
		//DateUtil
		DateUtil du = new DateUtil();
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentpage-1);
		List<DbjgZjxxb> dbjgZjxxbList = new ArrayList<DbjgZjxxb>();
		
		if("query".equals(cztype)){
			uri = request.getRequestURI();
		}		
		//流水号
		if(lsh != null && !"".equals(lsh)){
			strCondition.append(" and s.lsh = '"+lsh+"' ");
			request.setAttribute("lsh", lsh);
		}
		//号牌号码
		if(hphm != null && !"".equals(hphm)){
			strCondition.append(" and s.hphm like '%"+hphm+"%'");
			request.setAttribute("hphm", hphm);
		}
		//号牌种类
		if(hpzl != null && !"".equals(hpzl)){
			strCondition.append(" and s.hpzl = '"+hpzl+"'");
			request.setAttribute("hpzl", hpzl);
		}
		//业务类型
		if(ywlx != null && !"".equals(ywlx)){
			if(ywlx.equals("A:A")){
				strCondition.append(" and s.ywlx = 'A' and s.ywyy = 'A:A' ");
			}else if(ywlx.equals("B:B")){
				strCondition.append(" and s.ywlx = 'B' and s.ywyy = 'B:B' ");
			}else if(ywlx.equals("B:C")){
				strCondition.append(" and s.ywlx = 'B' and s.ywyy = 'B:C' ");
			}else{
				strCondition.append(" and s.ywlx = '" + ywlx + "' ");
			}
			request.setAttribute("ywlx", ywlx);
		}
		//业务原因
		if(ywyy != null && !"".equals(ywyy)){
			String[] ywyyattr = ywyy.split(",");
			ywyy = "";
			for(int j = 0; j < ywyyattr.length; j++){
				ywyy += "'"+ywyyattr[j]+"' ,";
			}
			if(ywyy.endsWith(",")){
				ywyy = ywyy.substring(0,ywyy.length() - 1);
			}
			strCondition.append("  and s.ywyy in ("+ywyy+") ");
			request.setAttribute("ywyy", ywyy);
		}
		
		if(s_date == null || s_date.equals("")){
			Calendar date = Calendar.getInstance();
			date.setTime(new Date());
			date.set(Calendar.DATE,date.get(Calendar.DATE)-30);
			s_date = DateUtil.date2String(date.getTime());
		}
		
		if(e_date == null || e_date.equals("")){
			e_date = DateUtil.date2String(new Date());
		}
		
		//受理时间
		if (s_date != null && e_date != null && !s_date.equals("")
				&& !e_date.equals("")) {
			strCondition.append(" and (s.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && e_date == null && !s_date.equals("")
				&& e_date.equals("")) {
			e_date = du.date2String(new Date());
			strCondition.append(" and (s.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && s_date == null && s_date.equals("")
				&& !e_date.equals("")) {
			Date d = du.getAppointDate(730);
			s_date = du.date2String(d);
			strCondition.append(" and (s.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		
		//经办人
		if(jbr != null && !"".equals(jbr)){
			strCondition.append(" and s.lrrxm = '" + jbr + "' ");
			request.setAttribute("jbr", jbr);
		}
		//经办人部门
		if(jbrbm != null && !"".equals(jbrbm)){
			List deptList =  this.slgDrvService.getDeptBelowList(jbrbm);
			if(deptList != null && deptList.size() > 0){
				String czbm = "";
				for(int i = 0; i < deptList.size(); i++){
					Object[] objs = (Object[])deptList.get(i);
					if(objs != null){
						czbm += "'"+objs[0]+"',";
					}
				}
				if(czbm.endsWith(",")){
					czbm = czbm.substring(0, czbm.length()-1);
				}
				strCondition.append(" and s.lrbmdm in ("+czbm+") ");
			}
			request.setAttribute("jbrbm", jbrbm);
		}
		
		if(shzt != null && !shzt.equals("")){
			strCondition.append(" and s.shjg ='"+shzt+"' ");
			request.setAttribute("shzt", shzt);
		}
		
		if(shr != null && !shr.equals("")){
			strCondition.append(" and s.shxm ='"+shr+"' ");
			request.setAttribute("shr", shr);
		}

		hqlListBuff.append(strCondition.toString());
		hqlListBuff.append(" ORDER BY s.lrsj DESC ");
		count = this.slgVehDao.getRepositoryByHQLListSize(hqlCountBuff.toString()+strCondition.toString());
		if(count > 0){
			if("query".equals(cztype)){
				dbjgZjxxbList = this.slgVehDao.findHQLByPage(hqlListBuff.toString(), offset, pageSize);
			}else if("export".equals(cztype)){
				if(count > 10000){
					map.put("uri", uri);
					map.put("pagesize", pageSize);
					map.put("rscount", count);
					map.put("currentpage", currentpage);
					request.setAttribute("rscount", count);
					request.setAttribute("map", map);
					request.setAttribute("dbjgZjxxbList", dbjgZjxxbList);
					request.setAttribute("exportData", "数据量太多无法一次导出，请筛选！");
					return dbjgZjxxbList;
				}
				dbjgZjxxbList = this.slgVehDao.getRepositories(hqlListBuff.toString());
			}
			
			
			
			//查询所有业务类型
			List<SlgSjzd> ywlxList = new ArrayList<SlgSjzd>();
			ywlxList = this.slgDrvService.getYwlxYwyy(request, "", "", "JDCYWSL", "VEH_YWLX", "");
			Map<String, String> ywlxMap = new HashMap<String, String>();
			if (ywlxList != null && ywlxList.size() > 0) {
				for (int i = 0; i < ywlxList.size(); i++) {
					SlgSjzd sjzd = (SlgSjzd) ywlxList.get(i);
					ywlxMap.put(sjzd.getDmz(), sjzd.getDmms1());
				}
			}
			
			//查询所有业务原因
			List<SlgSjzd> ywyyList = new ArrayList<SlgSjzd>();
			ywyyList = this.slgDrvService.getYwlxYwyy(request, "", "", "JDCYWSL", "VEH_YWYY", "");
			Map<String, String> ywyyMap = new HashMap<String, String>();
			if (ywyyList != null && ywyyList.size() > 0) {
				for (int i = 0; i < ywyyList.size(); i++) {
					SlgSjzd sjzd = (SlgSjzd) ywyyList.get(i);
					ywyyMap.put(sjzd.getDmz(), sjzd.getDmms1());
				}
			}
			
			for(int i = 0; i < dbjgZjxxbList.size(); i++){
				DbjgZjxxb zjxx = dbjgZjxxbList.get(i);
				String ywlxval = "";
				String[] ywlxarr = null;
				String[] ywyyarr = null;
				if(!StringUtil.isNull(zjxx.getYwlx()) && !StringUtil.isNull(zjxx.getYwyy())){
					if("A".equals(zjxx.getYwlx()) && "A:A".equals(zjxx.getYwyy())){
						dbjgZjxxbList.get(i).setYwlx("注册登记");
						continue;
					}
					if("B".equals(zjxx.getYwlx()) && "B:B".equals(zjxx.getYwyy())){
						dbjgZjxxbList.get(i).setYwlx("转移登记(市内过户)");
						continue;
					}
					if("B".equals(zjxx.getYwlx()) && "B:C".equals(zjxx.getYwyy())){
						dbjgZjxxbList.get(i).setYwlx("转移登记(市外过户)");
						continue;
					}
				}
				if(!StringUtil.isNull(zjxx.getYwlx())){
					ywlxarr = zjxx.getYwlx().split(",");
				}
				if(!StringUtil.isNull(zjxx.getYwyy())){
					ywyyarr = zjxx.getYwyy().split(",");
				}
				if(ywlxarr != null){
					for(int j = 0; j < ywlxarr.length; j++){
						ywlxval += (ywlxMap.get(ywlxarr[j])==null || "".equals(ywlxMap.get(ywlxarr[j]))?ywlxarr[j]:ywlxMap.get(ywlxarr[j]));
						if(ywyyarr != null){
							String ywyystr = "";
							for(int k = 0; k < ywyyarr.length; k++){
								String ywyyval = ywyyarr[k];
								String[] ywyysplit = ywyyval.split(":");
								if(ywyysplit != null && ywyysplit.length >= 2){
									if(ywlxarr[j].equals(ywyysplit[0])){
										ywyystr += (ywyyMap.get(ywyysplit[1])==null || "".equals(ywyyMap.get(ywyysplit[1])))?ywyysplit[1]:ywyyMap.get(ywyysplit[1])+",";
									}
								}
							}
							if(ywyystr != null && !"".equals(ywyystr)){
								if(ywyystr.endsWith(",")){
									ywyystr =  ywyystr.substring(0, ywyystr.length()-1);
								}
								ywlxval += "("+ywyystr+")";
							}
						}
						ywlxval += ",";
					}
				}
				
				if(ywlxval.endsWith(",")){
					ywlxval = ywlxval.substring(0, ywlxval.length()-1);
				}
				dbjgZjxxbList.get(i).setYwlx(ywlxval);
			}
			
		}
		map.put("uri", uri);
		map.put("pagesize", pageSize);
		map.put("rscount", count);
		map.put("currentpage", currentpage);
		request.setAttribute("rscount", count);
		request.setAttribute("map", map);
		request.setAttribute("dbjgZjxxbList", dbjgZjxxbList);
		return dbjgZjxxbList;
	}
	//var info = window.open('<%=request.getContextPath()%>/veh/veh_initSlCx.action?id=' + id + '&ywlx=' + ywlx,'info','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
	//受理详细查询
	public DbjgZjxxb getDbjgZjxxb(HttpServletRequest request,String id) throws Exception {
		log.info("method:getDbjgZjxxb|param:Id="+id);
		DbjgZjxxb dbjg=slgVehDao.getRepository(id);
		return dbjg;
	}
	
	@SuppressWarnings("unchecked")
	public List getBajl(HttpServletRequest request, String sfzmhm) throws Exception{
		List list = null;
		if(!StringUtil.isNull(sfzmhm)){
			String sql = "select * from dbjg_v_dby t where sfzmhm = '"+sfzmhm+"' ";
			list = this.slgVehDao.findSQL(sql);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<VehPodbsp> getPoDbspList(HttpServletRequest request, String czsfzhm, String dbsfzhm, String hphm, String hpzl) throws Exception{
		List<VehPodbsp> list = null;
		if(StringUtil.isNull(czsfzhm) && StringUtil.isNull(dbsfzhm) && StringUtil.isNull(hphm) && StringUtil.isNull(hpzl)){
			return null;
		}else{
			StringBuffer hqlBuffer = new StringBuffer("SELECT s from VehPodbsp s where 1=1");
			if(!StringUtil.isNull(czsfzhm)){
				hqlBuffer.append(" and s.czSfzmhm = '"+czsfzhm+"' ");
			}
			if(!StringUtil.isNull(dbsfzhm)){
				hqlBuffer.append(" and s.poSfzmhm = '"+dbsfzhm+"' ");
			}
			if(!StringUtil.isNull(hphm)){
				hphm = hphm.toUpperCase();
				if(hphm.contains("粤")){
					hphm = hphm.replaceAll("粤", "");
				}
				hqlBuffer.append(" and s.hphm = '"+hphm+"' ");
			}
			if(!StringUtil.isNull(hpzl)){
				hqlBuffer.append(" and s.hpzl = '"+hpzl+"' ");
			}
			hqlBuffer.append(" and s.yxrq >= sysdate");
			hqlBuffer.append(" order by s.sprSj desc");
			list = this.slgVehDao.getRepositories(hqlBuffer.toString());
			
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public String getIsBlack(HttpServletRequest request, String sfzmhm, String xm, String rkyy, String sdlx, String sdyxqz, String sdzt) throws Exception{
		 String result = "0";
		 if(StringUtil.isNull(sfzmhm)){
			 sfzmhm = request.getParameter("sfzmhm");
		 }
		 if(StringUtil.isNull(xm)){
			 xm = request.getParameter("xm");
		 }
		 if(StringUtil.isNull(rkyy)){
			 rkyy = request.getParameter("rkyy");
		 }
		 if(StringUtil.isNull(sdlx)){
			 sdlx = request.getParameter("sdlx");
		 }
		 if(StringUtil.isNull(sdyxqz)){
			 sdyxqz = request.getParameter("sdyxqz");
		 }
		 if(StringUtil.isNull(sdzt)){
			 sdzt = request.getParameter("sdzt");
		 }
		 String sql = "select xh, sfzmhm, xm, rkyy, sdlx, sdyxqz, sdzt from dbjg_hmdyh where 1=1";
		 if(!StringUtil.isNull(sfzmhm)){
			 sql += " and sfzmhm = '"+sfzmhm+"'";
		 }
		 if(!StringUtil.isNull(xm)){
			 sql += " and xm = '"+xm+"'";
		 }
		 if(!StringUtil.isNull(rkyy)){
			 sql += " and rkyy = '"+rkyy+"'";
		 }
		 if(!StringUtil.isNull(sdlx)){
			 sql += " and sdlx = '"+sdlx+"'";
		 }
//		 if(!StringUtil.isNull(sdyxqz)){
//			 sql += " and sdyxqz >= sysdate";
//		 }
		 if(!StringUtil.isNull(sdzt)){
			 sql += " and sdzt = '"+sdzt+"'";
		 }
		 List list = this.slgVehDao.findSQL(sql);
		 if(list != null && list.size() > 0){
			 List<SlgSjzd> sjzdList = this.slgDrvService.getYwlxYwyy(request, "HMD_TS", "", "HMD", "", ""); 
			 result = sjzdList.get(0).getDmms1();
		 }
		 return result;
	}
	
	public String getIsBlackByFun(HttpServletRequest request, String ywlx, String ywzl, String hphm, String hpzl, String dsrsfzmhm, String dbrsfzmhm) throws Exception{
		String result = "";
		if(StringUtil.isNull(ywlx)){
			ywlx = request.getParameter("ywlx");
		}
		if(StringUtil.isNull(ywzl)){
			ywzl = request.getParameter("ywzl");
		}
		if(StringUtil.isNull(hphm)){
			hphm = request.getParameter("hphm");
			if(!StringUtil.isNull(hphm)){
				hphm = hphm.toUpperCase();
			}
		}
		if(StringUtil.isNull(hpzl)){
			hpzl = request.getParameter("hpzl");
		}
		if(StringUtil.isNull(dsrsfzmhm)){
			dsrsfzmhm = request.getParameter("dsrsfzmhm");
		}
		if(StringUtil.isNull(dbrsfzmhm)){
			dbrsfzmhm = request.getParameter("dbrsfzmhm");
		}
		
		result = this.slgVehDao.getIsBlackByFun(ywlx, ywzl, hphm, hpzl,dsrsfzmhm, dbrsfzmhm);
		return result;
	}
	
	public String getIsYuyue(HttpServletRequest request, String xml)throws Exception{
		if(xml == null || "".equals(xml)){
			xml = "<request>";
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String jh = user.getName();
			String lsh = request.getParameter("lsh");
			String hphm = request.getParameter("hphm");
			String hpzl = request.getParameter("hpzl");
			String dsrsfzmhm = request.getParameter("dsrsfzmhm");
			String ywlx = request.getParameter("ywlx");
			String ywyy = request.getParameter("ywyy");
			String type = request.getParameter("type");
			String dqgw = request.getParameter("dqgw");
			String imei = request.getParameter("imei");
			String bz = request.getParameter("bz");
			String clsbdh = request.getParameter("clsbdh");
			//业务类型如果是临时号牌，<hphm>节点的值应该是取车辆识别代号的后四位
			//为<clsbdh>节点赋值
			if("O".equals(ywlx)){
			   if(null != clsbdh || !"".equals(clsbdh)){
					hphm = clsbdh.substring(clsbdh.length()-4);  //取后4位
				}
			}else if("I".equals(ywlx)){
				if(null == clsbdh || "".equals(clsbdh)){
					clsbdh ="";
				}
			}
			xml = xml + "<head><jh>"+jh+"</jh><type>"+type+"</type><dqgw>"+dqgw+"</dqgw></head>";
			xml = xml + "<body><lsh>"+lsh+"</lsh><hphm>"+hphm+"</hphm><hpzl>"+hpzl+"</hpzl>";
			xml = xml + "<dsrsfzmhm>"+dsrsfzmhm+"</dsrsfzmhm><ywlx>"+ywlx+"</ywlx><ywyy>"+ywyy+"</ywyy><imei>"+imei+"</imei>";
			xml = xml +	"<bz>"+bz+"</bz><clsbdh>"+clsbdh+"</clsbdh></body>";
			xml = xml + "</request>";
		}
		String result = this.slgVehDao.jszYuyue(request, xml);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public String getIsyanche(HttpServletRequest request) throws Exception{
		String lsh = request.getParameter("lsh");
		if(!StringUtil.isNull(lsh)){
			String sql = "select  check_yh from v_data_check  where lsh='"+lsh+"'";
			List list = this.slgVehDao.findSQL(sql);
			if(list != null && list.size() > 0){
				String chechYh = list.get(0)+"";
				String deptsql = "select user_id, user_name, org_id from v_veh_user_ycs where login_id='"+chechYh+"'";
				list = this.slgVehDao.findSQL(deptsql);
				if(list != null && list.size() > 0){
					Object[] objs = (Object[])list.get(0);
					String org_id = objs[2]+"";
					//判断当前用户是否为机管科
					User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
					String jgksql = "select * from ( select * from v_veh_org_ycs "
						+"start with org_id in ('C34702A8FFEB7CBFE040007F0100339B', 'C34702A8FFED7CBFE040007F0100339B') connect by prior org_id = up_org)  a "
						+"where a.org_id = '"+user.getBmid()+"'";
					list = this.slgVehDao.findSQL(jgksql);
					if(list != null && list.size() > 0){
						//是机管科或者档案科，验证验车的账号是否为审验科
						String syksql = "select * from (select * from v_veh_org_ycs "
							+"start with org_id = 'C34702A8FEF07CBFE040007F0100339B' connect by prior org_id = up_org) a "
							+"where a.org_id = '"+org_id+"'";
						list = this.slgVehDao.findSQL(syksql);
						if(list != null && list.size() > 0){
							return "1";
						}else{
							return "2";
						}
					}else{
						//不是机管科，比较验车账号和受理账户是否为同一科级部门
						String kjbmsql = "select org_id from (select t.* from v_veh_org_ycs t start with org_id = '"+org_id
										+"' connect by prior up_org = org_id) where up_org = 'C34702A8FED97CBFE040007F0100339B'";
						list = this.slgVehDao.findSQL(kjbmsql);
						if(list != null && list.size() > 0){
							String yhKjOrg = list.get(0)+"";
							kjbmsql = "select org_id from (select t.* from v_veh_org_ycs t start with org_id = '"+user.getBmid()
									+"' connect by prior up_org = org_id) where up_org = 'C34702A8FED97CBFE040007F0100339B'";
							list = this.slgVehDao.findSQL(kjbmsql);
							if(list != null && list.size() > 0){
								String userKjOrg = list.get(0)+"";
								if(yhKjOrg.equals(userKjOrg)){
									return "1";
								}else{
									return "3";
								}
							}else{
								return "3";
							}
						}else{
							return "3";
						}
					}
				}else{
					return "3";
				}
				
			}else{
				return "1";
			}
		}
		return "0";
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
	
	public String getIsneedzb(HttpServletRequest request) throws Exception{
		String hphm = request.getParameter("hphm");
		String hpzl = request.getParameter("hpzl"); 
		String ywlx = request.getParameter("ywlx"); 
		String ywyy = request.getParameter("ywyy");
		String sfzmhm = request.getParameter("sfzmhm"); 
		String xm = request.getParameter("xm");
		String sfzmmc = request.getParameter("sfzmmc");
		return this.slgVehDao.getIsneedzb(request, hphm, hpzl, ywlx, ywyy, sfzmhm, xm, sfzmmc);
	}
	
	@SuppressWarnings("unchecked")
	public String getIsyanzzb(HttpServletRequest request) throws Exception{
		String xml = "<request>";
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String jh = user.getName();
		String lsh = request.getParameter("lsh");
		String hphm = request.getParameter("hphm");
		String hpzl = request.getParameter("hpzl");
		String dsrsfzmhm = request.getParameter("dsrsfzmhm");
		String ywlx = request.getParameter("ywlx");
		String ywyy = request.getParameter("ywyy");
		String dqgw = request.getParameter("dqgw");
		String bz = request.getParameter("bz");
		String clsbdh = request.getParameter("clsbdh");
		String xm = request.getParameter("xm");
		String zbh = request.getParameter("zbh");
		String zblx = request.getParameter("zblx");
		String gzh = request.getParameter("gzh");
		if(StringUtil.isNull(clsbdh)){
			String sql = "select clsbdh from es_vehicle where hphm = '"+hphm+"' and hpzl = '"+hpzl+"' ";
			List list = this.defaultDao.findSQL(sql);
			if(list != null && list.size() > 0){
				clsbdh = list.get(0).toString();
			}
		}
		xml = xml + "<head><jh>"+jh+"</jh><dqgw>"+dqgw+"</dqgw></head>";
		xml = xml + "<body><lsh>"+lsh.trim()+"</lsh><hphm>"+(hphm==null?"":hphm)+"</hphm><hpzl>"+(hpzl==null?"":hpzl)+"</hpzl><clsbdh>"+(clsbdh==null?"":clsbdh)+"</clsbdh><dsrsfzmhm>"
			+(dsrsfzmhm==null?"":dsrsfzmhm)+"</dsrsfzmhm><xm>"+(xm==null?"":xm)+"</xm><ywlx>"+(ywlx==null?"":ywlx)+"</ywlx><ywyy>"+(ywyy==null?"":ywyy)+"</ywyy><zbh>"+(zbh==null?"":zbh)+"</zbh><zblx>"+(zblx==null?"":zblx)+"</zblx><gzh>"+(gzh==null?"":gzh)+"</gzh><bz>"+(bz==null?"":bz)+"</bz></body>";
		xml = xml + "</request>";
		String result = this.slgVehDao.getIsyanzzb(request, xml);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List getClxgSzjdList(HttpServletRequest request) throws Exception{
		String sql = "select dmz, dmms1 from CLXG_SJZD  where  Dmlb ='ZBFL' order by Bz";
		List list = this.defaultDao.findSQL(sql);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List getCllxList(String dmlb, String dmz) throws Exception{
		String sql = " select dmz, dmsm1 from es_veh_code  where 1=1";
		if(!StringUtil.isNull(dmlb)){
			sql += " and dmlb = '"+dmlb+"'";
		}
		if(!StringUtil.isNull(dmz)){
			sql += " and dmz = '"+dmz+"'";
		}
		List list = this.defaultDao.findSQL(sql);
		return list;
	}
	
	//新加查询车辆类型（'K3%' OR 'K4%'）
	@SuppressWarnings("unchecked")
	public List getZrCllxList() throws Exception{
		List list = this.defaultDao.findSQL(" select dmz,dmsm1 from es_veh_code where 1=1 and dmlb = '4' and dmz like 'K3%' or dmz like 'K4%' ");
		return list;
	}
	
	public Object[] getTyblshinfo(HttpServletRequest request) throws Exception{
		Object[] objs = null;
		String lsh = request.getParameter("tyblsh");
		if(!StringUtil.isNull(lsh)){
			objs = this.itopscDao.getTyblshinfo(lsh);
		}
		return objs;
	}
	
	public String getZjxxblsh() throws Exception{
		String lsh = "";
		String seq_sql = "select 'Z' || to_char(sysdate, 'yyMMdd') || lpad(SEQ_ZJXXB_LSH.NEXTVAL, 6, 0) seq from dual";
		lsh = this.defaultDao.findSQL(seq_sql).get(0).toString();
		return lsh;
	}
	
	@SuppressWarnings("unchecked")
	public List getEsvehflow(HttpServletRequest request) throws Exception{
		List list = null;
		String lsh = request.getParameter("lsh");
		String sql = "select lsh, cast(xh as varchar2(50)) xh, cast(ywlx as varchar2(50)) ywlx, ywyy, syr, cast(hpzl as varchar2(50)) hpzl, hphm, clpp1, clxh, cast(cllx as varchar2(50)) cllx, xzqh, sqrq, bjrq, cast(xygw as varchar2(50)) xygw, ywlc, cast(lszt as varchar2(50)) lszt, glbm, clsbdh, (select dmms1 from slg_sjzd where dmz = ywlx and dmlb = 'JDCYWSL' and veh_drv = 'VEH_YWLX') ywlxmc  from es_veh_flow  where lsh = '"+lsh+"'";
		list = this.defaultDao.findSQL(sql);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List getEsVehicle(HttpServletRequest request) throws Exception{
		List list = null;
		String hphm = request.getParameter("hphm");
		String hpzl = request.getParameter("hpzl");
		//String sql = "select hphm, hpzl, cj, cp, xh, sfzmhm, sfzmmc, czmc, ccdjrq, cllx, zt, csys, fdjh from es_vehicle where hphm = '"+hphm+"' and hpzl = '"+hpzl+"'";
		String sql = "select hphm, cast(hpzl as varchar2(50)) hpzl, sfzmhm, czmc, clsbdh , cast(cllx as varchar2(50)) cllx, cp from es_vehicle where hphm = '"+hphm+"' and hpzl = '"+hpzl+"' ";
		list = this.defaultDao.findSQL(sql);
		if(list == null || list.size() == 0){
			sql = " select hphm, cast(hpzl as varchar2(50)) hpzl, sfzmhm, syr czmc, clsbdh, cast(cllx as varchar2(50)) cllx, clpp1 cp  from extshare.cgs_vehicle_temp where hphm = '"+hphm+"' and hpzl = '"+hpzl+"' ";
			list = this.defaultDao.findSQL(sql);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List getClsyxzList(HttpServletRequest request, String xtlb, String dmlb) throws Exception{
		String sql = "select dmz, dmsm1 from es_drv_code where xtlb='"+xtlb+"' and dmlb='"+dmlb+"' ";
		List list = this.defaultDao.findSQL(sql);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List getXglxList(HttpServletRequest request) throws Exception{
		String sql = "select dmz, dmms1 from CLXG_SJZD  where  Dmlb='XGCLLX'";
		List list = this.defaultDao.findSQL(sql);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List getClxgsjzd2List(HttpServletRequest request) throws Exception{
		String xtlb = request.getParameter("xtlb");
		String dmlb = request.getParameter("dmlb");
		String sql = "select dmz, dmms1 from CLXG_SJZD  where xtlb = '"+xtlb+"' and dmlb='"+dmlb+"'";
		List list = this.defaultDao.findSQL(sql);
		return list;
	}
	
	public String getZblist(HttpServletRequest request) throws Exception{
		String zblx = request.getParameter("zblx");
		String gzh = request.getParameter("gzh");
		String sfzmhm = request.getParameter("sfzmhm");
		String clsbdh = request.getParameter("clsbdh");
		String xml = "<request><head><zblx>"+zblx+"</zblx><gzh>"+gzh+"</gzh><sfzmhm>"+sfzmhm+"</sfzmhm><clsbdh>"+clsbdh+"</clsbdh></head><body></body></request>";
		String result = this.slgVehDao.getZblist(request, xml);
		return result;
	}
	
	public String getAlterinfo(HttpServletRequest request, String ywlx, String ywyy, String lsh, String hphm, String hpzl) throws Exception{
		String sql = "select clxg_buesines_schedule.func_alterinfo('"+ywlx+"', '"+ywyy+"', '"+lsh+"', '"+hphm+"', '"+hpzl+"') from dual";
		return this.defaultDao.findSQL(sql).toString();
	}
	
	@SuppressWarnings("unchecked")
	public DbjgZjxxb getDbjgzjxxByCondition(HttpServletRequest request) throws Exception{
		DbjgZjxxb zjxx = null;
		String tyblsh = request.getParameter("tyblsh");
		String hql = " from DbjgZjxxb t where 1=1 ";
		if(!StringUtil.isNull(tyblsh)){
			List list = this.defaultDao.getRepositories(hql+" and t.tyblsh = '"+tyblsh+"'");
			if(list != null && list.size() > 0){
				zjxx = (DbjgZjxxb)list.get(0);
			}else{
				list = this.defaultDao.getRepositories(hql+" and t.lsh = '"+tyblsh+"'");
				if(list != null && list.size() > 0){
					zjxx = (DbjgZjxxb)list.get(0);
				}
			}
		}
		return zjxx;
	}
	
	@SuppressWarnings("unchecked")
	public String getIsSamedept(HttpServletRequest request) throws Exception{
		String dqyhbmid = request.getParameter("dqyhbmid");
		String databmid = request.getParameter("databmid");
		if(!StringUtil.isNull(dqyhbmid)){
			List deptList =  this.slgDrvService.getDeptBelowList(dqyhbmid);
			if(deptList != null && deptList.size() > 0){
				for(int i = 0; i < deptList.size(); i++){
					Object[] objs = (Object[])deptList.get(i);
					if(objs != null && objs[0].equals(databmid)){
						return "1";
					}
				}
			}
		}
		return "0";
	}
	
	@SuppressWarnings("unchecked")
	public List<DbjgZjxxb> getZjxxbList(HttpServletRequest request) throws Exception{
		List<DbjgZjxxb> zjxxList = null;
		String lsh = request.getParameter("lsh");
		String hql = " from DbjgZjxxb t where 1=1 ";
		String counthql = "select count(t) from DbjgZjxxb t where 1=1 ";
		if(!StringUtil.isNull(lsh)){
			hql = hql + " and (t.lsh = '"+lsh+"' or tyblsh = '"+lsh+"')";
			counthql = counthql + " and (t.lsh = '"+lsh+"' or tyblsh = '"+lsh+"')";
			request.setAttribute("lsh", lsh);
		}
		int count = this.defaultDao.getRepositoryByHQLListSize(counthql);
		if(count > 10000){
			request.setAttribute("exportData", "查询数据太多，请根据条件筛选!");
			return null;
		}
		List list = this.defaultDao.getRepositories(hql);
		zjxxList = (List<DbjgZjxxb>)list;
		//查询所有业务类型
		List<SlgSjzd> ywlxList = new ArrayList<SlgSjzd>();
		ywlxList = this.slgDrvService.getYwlxYwyy(request, "", "", "JDCYWSL", "VEH_YWLX", "");
		Map<String, String> ywlxMap = new HashMap<String, String>();
		if (ywlxList != null && ywlxList.size() > 0) {
			for (int i = 0; i < ywlxList.size(); i++) {
				SlgSjzd sjzd = (SlgSjzd) ywlxList.get(i);
				ywlxMap.put(sjzd.getDmz(), sjzd.getDmms1());
			}
		}
		
		//查询所有业务原因
		List<SlgSjzd> ywyyList = new ArrayList<SlgSjzd>();
		ywyyList = this.slgDrvService.getYwlxYwyy(request, "", "", "JDCYWSL", "VEH_YWYY", "");
		Map<String, String> ywyyMap = new HashMap<String, String>();
		if (ywyyList != null && ywyyList.size() > 0) {
			for (int i = 0; i < ywyyList.size(); i++) {
				SlgSjzd sjzd = (SlgSjzd) ywyyList.get(i);
				ywyyMap.put(sjzd.getDmz(), sjzd.getDmms1());
			}
		}
		
		//查询所有指标类型
		List zblxList = new ArrayList();
		zblxList = this.getClxgSzjdList(request);
		Map<String, String> zblxMap = new HashMap<String, String>();
		if (zblxList != null && zblxList.size() > 0) {
			for (int i = 0; i < zblxList.size(); i++) {
				Object[] objs = (Object[])zblxList.get(i);
				zblxMap.put(objs[0]+"", objs[1]+"");
			}
		}
		
		for(int i = 0; i < zjxxList.size(); i++){
			DbjgZjxxb zjxx = zjxxList.get(i);
			String ywlxval = "";
			String[] ywlxarr = null;
			String[] ywyyarr = null;
			if(!StringUtil.isNull(zjxx.getYwlx()) && !StringUtil.isNull(zjxx.getYwyy())){
				if("A".equals(zjxx.getYwlx()) && "A:A".equals(zjxx.getYwyy())){
					zjxxList.get(i).setYwlx("注册登记");
				}
				if("B".equals(zjxx.getYwlx()) && "B:B".equals(zjxx.getYwyy())){
					zjxxList.get(i).setYwlx("转移登记(市内过户)");
				}
				if("B".equals(zjxx.getYwlx()) && "B:C".equals(zjxx.getYwyy())){
					zjxxList.get(i).setYwlx("转移登记(市外过户)");
				}
			}
			if(!StringUtil.isNull(zjxx.getYwlx())){
				ywlxarr = zjxx.getYwlx().split(",");
			}
			if(!StringUtil.isNull(zjxx.getYwyy())){
				ywyyarr = zjxx.getYwyy().split(",");
			}
			if(ywlxarr != null){
				for(int j = 0; j < ywlxarr.length; j++){
					ywlxval += (ywlxMap.get(ywlxarr[j])==null || "".equals(ywlxMap.get(ywlxarr[j]))?ywlxarr[j]:ywlxMap.get(ywlxarr[j]));
					if(ywyyarr != null){
						String ywyystr = "";
						for(int k = 0; k < ywyyarr.length; k++){
							String ywyyval = ywyyarr[k];
							String[] ywyysplit = ywyyval.split(":");
							if(ywyysplit != null && ywyysplit.length >= 2){
								if(ywlxarr[j].equals(ywyysplit[0])){
									ywyystr += (ywyyMap.get(ywyysplit[1])==null || "".equals(ywyyMap.get(ywyysplit[1])))?ywyysplit[1]:ywyyMap.get(ywyysplit[1])+",";
								}
							}
						}
						if(ywyystr != null && !"".equals(ywyystr)){
							if(ywyystr.endsWith(",")){
								ywyystr =  ywyystr.substring(0, ywyystr.length()-1);
							}
							ywlxval += "("+ywyystr+")";
						}
					}
					ywlxval += ",";
				}
			}
			
			if(ywlxval.endsWith(",")){
				ywlxval = ywlxval.substring(0, ywlxval.length()-1);
			}
			zjxxList.get(i).setYwlx(ywlxval);
			zjxxList.get(i).setZblx(zblxMap.get(zjxx.getZblx()) == null?zjxx.getZblx():zblxMap.get(zjxx.getZblx()));
		}
		return zjxxList;
	}
	
	public String tbZbinfo(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		if(!StringUtil.isNull(id)){
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String tbyy = request.getParameter("tbyy");
			DbjgZjxxb dbZjxxb = (DbjgZjxxb)this.defaultDao.getRepository(id, DbjgZjxxb.class);
			dbZjxxb.setTbyy(tbyy);
			dbZjxxb.setTbr(user.getName());
			dbZjxxb.setTbrxm(user.getYgxm());
			dbZjxxb.setTbbm(user.getBmid());
			dbZjxxb.setTbrq(new Date());
			this.defaultDao.updateRepository(dbZjxxb);
			
			//添加日志
			DbjgZjxxbLog dZjxxbLog = new DbjgZjxxbLog();
			dZjxxbLog = (DbjgZjxxbLog)getXclog(dZjxxbLog, dbZjxxb);
			dZjxxbLog.setCzr(user.getName());
			dZjxxbLog.setCzrxm(user.getYgxm());
			dZjxxbLog.setCzrbm(user.getBmid());
			dZjxxbLog.setCznr("U");
			dZjxxbLog.setCzsj(new Date());
			dZjxxbLog.setCzip(getLoginIp(request));
			dZjxxbLog.setCzmac("");
			this.slgVehDao.addObj(dZjxxbLog, request);
			
			StringBuffer srcs = new StringBuffer("<request><head><dqgw>3</dqgw></head><body>");
			srcs.append("<lsh>"+(dbZjxxb.getLsh()==null?"":dbZjxxb.getLsh())+"</lsh>");
			srcs.append("<hphm>"+(dbZjxxb.getHphm()==null?"":dbZjxxb.getHphm())+"</hphm>");
			srcs.append("<hpzl>"+(dbZjxxb.getHpzl()==null?"":dbZjxxb.getHpzl())+"</hpzl>");
			srcs.append("<syxz>"+(dbZjxxb.getSyxz()==null?"":dbZjxxb.getSyxz())+"</syxz>");
			srcs.append("<sfzmmc>"+(dbZjxxb.getDsrsfzmmc()==null?"":dbZjxxb.getDsrsfzmmc())+"</sfzmmc>");
			srcs.append("<sfzmhm>");
			srcs.append(dbZjxxb.getDsrsfzmhm()==null?dbZjxxb.getDsrZzjgZh1():dbZjxxb.getDsrsfzmhm());
			srcs.append("</sfzmhm>");
			srcs.append("<syr>");
			srcs.append(dbZjxxb.getDsrxm());
			srcs.append("</syr>");
			srcs.append("<ip>"+(dbZjxxb.getLrip()==null?"":dbZjxxb.getLrip())+"</ip>");
			srcs.append("<sffq>"+(dbZjxxb.getSffq()==null?"":dbZjxxb.getSffq())+"</sffq>");
			srcs.append("<jhzbh>"+(dbZjxxb.getJhzbh()==null?"":dbZjxxb.getJhzbh())+"</jhzbh>");
			srcs.append("<ywlx>TB</ywlx>");
			srcs.append("<ywyy>"+(dbZjxxb.getYwyy()==null?"":dbZjxxb.getYwyy())+"</ywyy>");
			srcs.append("<hdfs>"+(dbZjxxb.getHdfs()==null?"":dbZjxxb.getHdfs())+"</hdfs>");
			srcs.append("<tyblsh>"+(dbZjxxb.getTyblsh()==null?"":dbZjxxb.getTyblsh())+"</tyblsh>");
			srcs.append("<clsbdh></clsbdh>");
			srcs.append("<cllx>"+(dbZjxxb.getCllx()==null?"":dbZjxxb.getCllx())+"</cllx>");
			srcs.append("<syq></syq>");
			srcs.append("<glbm></glbm>");
			srcs.append("<zczxlsh>"+(dbZjxxb.getLsh()==null?"":dbZjxxb.getLsh())+"</zczxlsh>");
			srcs.append("<zczxhfhljzyqchzx>"+(dbZjxxb.getZczxhfhljzyqchzx()==null?"":dbZjxxb.getZczxhfhljzyqchzx())+"</zczxhfhljzyqchzx>");
			srcs.append("<tbr>"+(dbZjxxb.getTbr()==null?"":dbZjxxb.getTbr())+"</tbr>");
			srcs.append("<tbyy>"+(dbZjxxb.getTbyy()==null?"":dbZjxxb.getTbyy())+"</tbyy>");
			srcs.append("<zblx>"+(dbZjxxb.getZblx()==null?"":dbZjxxb.getZblx())+"</zblx>");
			srcs.append("<zbh>"+(dbZjxxb.getZbh()==null?"":dbZjxxb.getZbh())+"</zbh>");
			srcs.append("</body></request>");
			String msg = this.slgVehDao.insertShenjiinfo(request, StringUtil.isNull(dbZjxxb.getZjxxblsh()) ? dbZjxxb.getLsh():dbZjxxb.getZjxxblsh(), srcs.toString());
			if(!"[0000]".equals(msg.substring(0, 6))){
				throw new Exception(msg.substring(6));
			}
			return "1";
		}
		return "-1";
	}
	
	public String getTbyanz(HttpServletRequest request) throws Exception{
		String lsh = request.getParameter("lsh");
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><request><head><user>"+user.getName()+"</user></head><body><lsh>"+lsh+"</lsh><userdept>"+user.getBmid()+"</userdept></body></request>";
		return this.slgVehDao.getTbyanz(request, xml);
	}
	
	//机动车审核验证
	public Integer vehShenheCheck(HttpServletRequest request, String shlsh,
			String hphm, String hpzl) throws Exception {
		String invalue = shlsh + ",";
		Object[] objs = null;
		objs = itopscDao.get10059(invalue);
		if(null != objs){
			if(null != objs[0] && null != objs[1]){
				if(objs[0].equals(hphm)&&objs[1].equals(hpzl)){
					return 1;
				}else{
					return 3;
				}
			}else{
				return 2;
			}
		}
		return 0;
	}
	
	//机动车业务审核
	public Integer vehShenhe(HttpServletRequest request, String shlsh,
			String shjg, String cjid,String hphm,String hpzl) throws Exception {
		String invalue = "";
		String result = "0";
		if("1".equals(shjg)){
			 invalue = shlsh + ","+hpzl+","+hphm+",1,";
			 result = itopscDao.get10149(invalue);
		}
		if("0".equals(result)){
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			DbjgZjxxb dbjgZjxxb = slgVehDao.getRepository(cjid);
			dbjgZjxxb.setShlsh(shlsh);
			dbjgZjxxb.setShjg(shjg);
			dbjgZjxxb.setShyhm(user.getName());
			dbjgZjxxb.setShxm(user.getYgxm());
			dbjgZjxxb.setShbm(user.getBmid());
			dbjgZjxxb.setShbmkj(getDeptUpid(user.getBmid()));
			dbjgZjxxb.setShrq(new Date());
			dbjgZjxxb.setShip(getLoginIp(request));
			dbjgZjxxb.setShmac("");
			slgVehDao.updateRepository(dbjgZjxxb);
			DbjgZjxxbLog dzlog = new DbjgZjxxbLog();
			dzlog.setCzr(user.getName());
			dzlog.setCzrxm(user.getYgxm());
			dzlog.setCzrbm(user.getBmid());
			dzlog.setCznr("审核");
			dzlog.setCzsj(new Date());
			dzlog.setCzip(getLoginIp(request));
			dzlog.setCzmac("");
			defaultDao.addRepositoryLog(dzlog, dbjgZjxxb, null);
			return 1;
		}else{
			return 2;
		}
	}
	
	//根据业务类型获取态图拍摄区域的值
	@SuppressWarnings("unchecked")
	public List getDtpzByYwlx(HttpServletRequest request) throws Exception{
		String ywlx = request.getParameter("ywlx");
		List list = this.defaultDao.findSQL("select id,ywlxid,ywlxms,title,url from dbjg_dtpz_zd d where d.ywlxid ='"+ywlx+"' order by d.id");
		return list;
	}
	
	public SlgVehDao getSlgVehDao() {
		return slgVehDao;
	}
	public void setSlgVehDao(SlgVehDao slgVehDao) {
		this.slgVehDao = slgVehDao;
	}
	public SlgDrvFileUploadDao getSlgDrvFileUpload() {
		return slgDrvFileUpload;
	}
	public void setSlgDrvFileUpload(SlgDrvFileUploadDao slgDrvFileUpload) {
		this.slgDrvFileUpload = slgDrvFileUpload;
	}

	public SlgDrvService getSlgDrvService() {
		return slgDrvService;
	}

	public void setSlgDrvService(SlgDrvService slgDrvService) {
		this.slgDrvService = slgDrvService;
	}

	public IDydjService getDydjService() {
		return dydjService;
	}

	public void setDydjService(IDydjService dydjService) {
		this.dydjService = dydjService;
	}

	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public IItopscDao getItopscDao() {
		return itopscDao;
	}

	public void setItopscDao(IItopscDao itopscDao) {
		this.itopscDao = itopscDao;
	}
}
