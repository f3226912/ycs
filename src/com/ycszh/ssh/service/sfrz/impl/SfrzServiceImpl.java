package com.ycszh.ssh.service.sfrz.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.dao.sfrz.FileUploadDao;
import com.ycszh.ssh.dao.sfrz.SfrzCjxxbDao;
import com.ycszh.ssh.hbm.ezxfw.EzDrvLiceChanApp;
import com.ycszh.ssh.hbm.rsc.RscDby;
import com.ycszh.ssh.hbm.rsc.RscDbyLog;
import com.ycszh.ssh.hbm.rsc.RscDbyZjxxb;
import com.ycszh.ssh.hbm.sfrz.SfrzCjxxb;
import com.ycszh.ssh.hbm.sfrz.SfrzCjxxbPhoto;
import com.ycszh.ssh.hbm.sfrz.SfrzSjzd;
import com.ycszh.ssh.hbm.sfrz.SfrzUserinfo;
import com.ycszh.ssh.service.sfrz.SfrzService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.ImageUtils;

public class SfrzServiceImpl implements SfrzService {
	private FileUploadDao fileUploadDao;
	private DefaultDao defaultDao;
	private SfrzCjxxbDao sfrzCjxxbDao;
	@SuppressWarnings("unchecked")
	public List getSfrzCjxxbList(HttpServletRequest request, int currentPage)
			throws Exception {
		List sfrzCjxxbList = new ArrayList();
		StringBuffer hql = new StringBuffer("from SfrzCjxxb as t where 1=1 ");
		StringBuffer sql = new StringBuffer("select count(1) from Sfrz_Cjxxb t where 1=1 ");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String lsh = request.getParameter("lsh");
		String xm = request.getParameter("xm");
		String rzly = request.getParameter("rzly");
		String rzjs = request.getParameter("rzjs");
		String sfzmhm = request.getParameter("sfzmhm");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		if (s_date != null && e_date != null && !"".equals(s_date) && !"".equals(e_date)) {
			hql.append(" and (t.shsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.shsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !"".equals(s_date) && (e_date == null || "".equals(e_date))) {
			e_date = DateUtil.date2String(new Date());
			hql.append(" and (t.shsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.shsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !"".equals(e_date) && (s_date == null || "".equals(s_date))) {
			Date d = DateUtil.getAppointDate(-7);
			s_date = DateUtil.date2String(d);
			hql.append(" and (t.shsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.shsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		// 采集流水号
		if (lsh != null && !"".equals(lsh)) {
			hql.append(" and t.cid = '" + lsh + "' ");
			sql.append(" and t.cid = '" + lsh + "' ");
			request.setAttribute("lsh", lsh);
		}
		// 姓名
		if (xm != null && !"".equals(xm)) {
			hql.append(" and t.xm = '" + xm + "' ");
			sql.append(" and t.xm = '" + xm + "' ");
			request.setAttribute("xm", xm);
		}
		//身份证明号码
		if(sfzmhm != null && !"".equals(sfzmhm)){
			hql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			sql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			request.setAttribute("sfzmhm", sfzmhm);
		}
		//认证来源
		if(rzly != null && !"".equals(rzly)){
			hql.append(" and t.rzly = '" + rzly + "' ");
			sql.append(" and t.rzly = '" + rzly + "' ");
			request.setAttribute("rzly", rzly);
		}
		//认证角色
		if(rzjs != null && !"".equals(rzjs)){
			hql.append(" and t.rzjs = '" + rzjs + "' ");
			sql.append(" and t.rzjs = '" + rzjs + "' ");
			request.setAttribute("rzjs", rzjs);
		}
		
		hql.append(" order by t.shsj desc ");
		int size = defaultDao.getRepositoryBySQLListSize(sql.toString());
		List<SfrzSjzd> rzlySjzdList = getSfrzSjzd("","","YHLY");
		Map<String, String> rzlySjzdMap = new HashMap<String, String>();
		if (rzlySjzdList != null && rzlySjzdList.size() > 0) {
			for (int i = 0; i < rzlySjzdList.size(); i++) {
				SfrzSjzd sjzd = (SfrzSjzd) rzlySjzdList.get(i);
				rzlySjzdMap.put(sjzd.getDmz(), sjzd.getDmsm());
			}
		}
		List<SfrzSjzd> rzjsSjzdList = getSfrzSjzd("","","RZJS");
		Map<String, String> rzjsSjzdMap = new HashMap<String, String>();
		if (rzjsSjzdList != null && rzjsSjzdList.size() > 0) {
			for (int i = 0; i < rzjsSjzdList.size(); i++) {
				SfrzSjzd sjzd = (SfrzSjzd) rzjsSjzdList.get(i);
				rzjsSjzdMap.put(sjzd.getDmz(), sjzd.getDmsm());
			}
		}
		if(size > 0){
			sfrzCjxxbList = this.defaultDao.findHQLByPage(hql.toString(), offset, pageSize);
			for(int i = 0; i < sfrzCjxxbList.size(); i++){
				SfrzCjxxb sc = (SfrzCjxxb)sfrzCjxxbList.get(i);
				if(null != sc){
					String rzlyval = rzlySjzdMap.get(sc.getRzly());
					sc.setRzly(rzlyval);
					
					String rzjsval = rzjsSjzdMap.get(sc.getRzjs());
					sc.setRzjs(rzjsval);
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
		request.setAttribute("sfrzCjxxbList", sfrzCjxxbList);
		request.setAttribute("rzlySjzdList", rzlySjzdList);
		request.setAttribute("rzjsSjzdList", rzjsSjzdList);
		return sfrzCjxxbList;
	}

	@SuppressWarnings("deprecation")
	public Map<String,String> insertOrUpdateSfrzCjxxb(SfrzCjxxb sfrzCjxxb, File file1, File file2,
			File file01, File file02, File file03, File file04, File file05,
			File file06,File file07,File file08,File file09, HttpServletRequest request) throws Exception {
		Map<String,String> rtmap = new HashMap<String,String>();
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		if(sfrzCjxxb != null){
			String cid = sfrzCjxxb.getCid();
			String basePath = request.getRealPath("/");
			File f = new File(basePath + "\\images\\shuiyin.png");
			String imagstr = "";
			if(null != file1){
				String strtemp = fileUploadDao.uploadFile(file1);
				if(null != strtemp && !"".equals(strtemp)){
					//String retxml2 = sfrzCjxxbDao.insertOrUpdateSfrzCjxxbPhoto(objectToXml2(rcid,strtemp,"1","1"));
					if("".equals(imagstr)){
						imagstr += "1+" + strtemp;
					}else{
						imagstr += ",1+" + strtemp;
					}
				}else{
					Exception etemp = new Exception("异常:当事人身份证照片上传失败,请重新点击保存按钮!");
					throw etemp;
				}
			}
			if(null != file2){
				String strtemp = fileUploadDao.uploadFile(file2);
				if(null != strtemp && !"".equals(strtemp)){
					if("".equals(imagstr)){
						imagstr += "3+" + strtemp;
					}else{
						imagstr += ",3+" + strtemp;
					}
				}else{
					Exception etemp = new Exception("异常:代办人身份证照片上传失败,请重新点击保存按钮!");
					throw etemp;
				}
			}
			if(null != file01){
				ImageUtils.pressImage(file01, f);
				String strtemp = fileUploadDao.uploadFile(file01);
				if(null != strtemp && !"".equals(strtemp)){
					if("".equals(imagstr)){
						imagstr += "2+" + strtemp;
					}else{
						imagstr += ",2+" + strtemp;
					}
				}else{
					Exception etemp = new Exception("异常:现场头像照片上传失败,请重新点击保存按钮!");
					throw etemp;
				}
			}
			if(null != file02){
				ImageUtils.pressImage(file02, f);
				String strtemp = fileUploadDao.uploadFile(file02);
				if(null != strtemp && !"".equals(strtemp)){
					if("".equals(imagstr)){
						imagstr += "19+" + strtemp;
					}else{
						imagstr += ",19+" + strtemp;
					}
				}else{
					Exception etemp = new Exception("异常:证件正面图片（身份证、居住证、驾驶证、行驶证）照片上传失败,请重新点击保存按钮!");
					throw etemp;
				}
			}
			/*if(null != file03){
				ImageUtils.pressImage(file03, f);
				String strtemp = fileUploadDao.uploadFile(file03);
				if(null != strtemp && !"".equals(strtemp)){
					if("".equals(imagstr)){
						imagstr += "11+" + strtemp;
					}else{
						imagstr += ",11+" + strtemp;
					}
				}else{
					Exception etemp = new Exception("异常:驾驶证照片上传失败,请重新点击保存按钮!");
					throw etemp;
				}
			}
			if(null != file04){
				ImageUtils.pressImage(file04, f);
				String strtemp = fileUploadDao.uploadFile(file04);
				if(null != strtemp && !"".equals(strtemp)){
					if("".equals(imagstr)){
						imagstr += "12+" + strtemp;
					}else{
						imagstr += ",12+" + strtemp;
					}
				}else{
					Exception etemp = new Exception("异常:行驶证照片上传失败,请重新点击保存按钮!");
					throw etemp;
				}
			}*/
			if(null != file05){
				ImageUtils.pressImage(file05, f);
				String strtemp = fileUploadDao.uploadFile(file05);
				if(null != strtemp && !"".equals(strtemp)){
					if("".equals(imagstr)){
						imagstr += "5+" + strtemp;
					}else{
						imagstr += ",5+" + strtemp;
					}
				}else{
					Exception etemp = new Exception("异常:面签照片上传失败,请重新点击保存按钮!");
					throw etemp;
				}
			}
			if(null != file06){
				ImageUtils.pressImage(file06, f);
				String strtemp = fileUploadDao.uploadFile(file06);
				if(null != strtemp && !"".equals(strtemp)){
					if("".equals(imagstr)){
						imagstr += "15+" + strtemp;
					}else{
						imagstr += ",15+" + strtemp;
					}
				}else{
					Exception etemp = new Exception("异常:签名照片上传失败,请重新点击保存按钮!");
					throw etemp;
				}
			}
			/*if(null != file07){
				ImageUtils.pressImage(file07, f);
				String strtemp = fileUploadDao.uploadFile(file07);
				if(null != strtemp && !"".equals(strtemp)){
					if("".equals(imagstr)){
						imagstr += "10+" + strtemp;
					}else{
						imagstr += ",10+" + strtemp;
					}
				}else{
					Exception etemp = new Exception("异常:身份证件反面照片上传失败,请重新点击保存按钮!");
					throw etemp;
				}
			}
			if(null != file08){
				ImageUtils.pressImage(file08, f);
				String strtemp = fileUploadDao.uploadFile(file08);
				if(null != strtemp && !"".equals(strtemp)){
					if("".equals(imagstr)){
						imagstr += "13+" + strtemp;
					}else{
						imagstr += ",13+" + strtemp;
					}
				}else{
					Exception etemp = new Exception("异常:居住证正面照片上传失败,请重新点击保存按钮!");
					throw etemp;
				}
			}
			if(null != file09){
				ImageUtils.pressImage(file09, f);
				String strtemp = fileUploadDao.uploadFile(file09);
				if(null != strtemp && !"".equals(strtemp)){
					if("".equals(imagstr)){
						imagstr += "14+" + strtemp;
					}else{
						imagstr += ",14+" + strtemp;
					}
				}else{
					Exception etemp = new Exception("异常:居住证反面照片上传失败,请重新点击保存按钮!");
					throw etemp;
				}
			}*/
			sfrzCjxxb.setSynFlag(imagstr);
			String finger1=request.getParameter("finger1");
			String finger2=request.getParameter("finger2");
			String finger3=request.getParameter("finger3");
			String finger4=request.getParameter("finger4");
			sfrzCjxxb.setFinger1(finger1);
			sfrzCjxxb.setFinger2(finger2);
			sfrzCjxxb.setFinger3(finger3);
			sfrzCjxxb.setFinger4(finger4);
			System.out.println(finger1+"====");
			if(null != cid && !"".equals(cid)){
				sfrzCjxxb.setShrdm(user.getName());
				sfrzCjxxb.setShrxm(user.getYgxm());
				sfrzCjxxb.setShbm(user.getBmid());
				sfrzCjxxb.setShbmKj(getDeptUpid(user.getBmid()));
				sfrzCjxxb.setShsj(new Date());
				sfrzCjxxb.setShip(getLoginIp(request));
				sfrzCjxxb.setShmac("");
			}else{
				sfrzCjxxb.setLrr(user.getName());
				sfrzCjxxb.setLrrxm(user.getYgxm());
				sfrzCjxxb.setLrbmdm(user.getBmid());
				sfrzCjxxb.setLrbmmc(user.getBmmc());
				sfrzCjxxb.setLrsj(new Date());
				sfrzCjxxb.setLrip(getLoginIp(request));
				sfrzCjxxb.setLrmac("");
				sfrzCjxxb.setShrdm(user.getName());
				sfrzCjxxb.setShrxm(user.getYgxm());
				sfrzCjxxb.setShbm(user.getBmid());
				sfrzCjxxb.setShbmKj(getDeptUpid(user.getBmid()));
				sfrzCjxxb.setShsj(new Date());
				sfrzCjxxb.setShip(getLoginIp(request));
				sfrzCjxxb.setShmac("");
			}
			String retxml = sfrzCjxxbDao.insertOrUpdateSfrzCjxxb(objectToXml(sfrzCjxxb));
			Map<String,String> retmap = parseXml(retxml);
			@SuppressWarnings("unused")
			String code = retmap.get("code");
			@SuppressWarnings("unused")
			String msg = retmap.get("msg");
			@SuppressWarnings("unused")
			String rcid = retmap.get("cid");
			rtmap = retmap;
		}
		return rtmap;
	}
	
	public String objectToXml(SfrzCjxxb sfrzCjxxb) throws Exception{
		StringBuffer sb = new StringBuffer();
		if(null != sfrzCjxxb){
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><request>");
			sb.append("<CID>" + (null != sfrzCjxxb.getCid() ? sfrzCjxxb.getCid() : "") + "</CID>");
			sb.append("<XM>" + (null != sfrzCjxxb.getXm() ? sfrzCjxxb.getXm() : "") + "</XM>");
			sb.append("<SFZMMC>" + (null != sfrzCjxxb.getSfzmmc() ? sfrzCjxxb.getSfzmmc() : "") + "</SFZMMC>");
			sb.append("<SFZMHM>" + (null != sfrzCjxxb.getSfzmhm() ? sfrzCjxxb.getSfzmhm() : "") + "</SFZMHM>");
			sb.append("<XB>" + (null != sfrzCjxxb.getXb() ? sfrzCjxxb.getXb() : "") + "</XB>");
			sb.append("<GJ>" + (null != sfrzCjxxb.getGj() ? sfrzCjxxb.getGj() : "") + "</GJ>");
			sb.append("<MZ>" + (null != sfrzCjxxb.getMz() ? sfrzCjxxb.getMz() : "") + "</MZ>");
			sb.append("<JG>" + (null != sfrzCjxxb.getJg() ? sfrzCjxxb.getJg() : "") + "</JG>");
			sb.append("<TXDZ>" + (null != sfrzCjxxb.getTxdz() ? sfrzCjxxb.getTxdz() : "") + "</TXDZ>");
			sb.append("<DZYX>" + (null != sfrzCjxxb.getDzyx() ? sfrzCjxxb.getDzyx() : "") + "</DZYX>");
			sb.append("<GDDH>" + (null != sfrzCjxxb.getGddh() ? sfrzCjxxb.getGddh() : "") + "</GDDH>");
			sb.append("<YDDH>" + (null != sfrzCjxxb.getYddh() ? sfrzCjxxb.getYddh() : "") + "</YDDH>");
			sb.append("<WX>" + (null != sfrzCjxxb.getWx() ? sfrzCjxxb.getWx() : "") + "</WX>");
			sb.append("<WB>" + (null != sfrzCjxxb.getWb() ? sfrzCjxxb.getWb() : "") + "</WB>");
			sb.append("<QQ>" + (null != sfrzCjxxb.getQq() ? sfrzCjxxb.getQq() : "") + "</QQ>");
			sb.append("<RZLY>" + (null != sfrzCjxxb.getRzly() ? sfrzCjxxb.getRzly() : "") + "</RZLY>");
			sb.append("<RZJS>" + (null != sfrzCjxxb.getRzjs() ? sfrzCjxxb.getRzjs() : "") + "</RZJS>");
			sb.append("<DSR_SFZ_CARDNAME1>" + (null != sfrzCjxxb.getDsrSfzCardname1() ? sfrzCjxxb.getDsrSfzCardname1() : "") + "</DSR_SFZ_CARDNAME1>");
			sb.append("<DSR_SFZ_CARDSEX1>" + (null != sfrzCjxxb.getDsrSfzCardsex1() ? sfrzCjxxb.getDsrSfzCardsex1() : "") + "</DSR_SFZ_CARDSEX1>");
			sb.append("<DSR_SFZ_CARDNO1>" + (null != sfrzCjxxb.getDsrSfzCardno1() ? sfrzCjxxb.getDsrSfzCardno1() : "") + "</DSR_SFZ_CARDNO1>");
			sb.append("<DSR_SFZ_CARDADDRESS1>" + (null != sfrzCjxxb.getDsrSfzCardaddress1() ? sfrzCjxxb.getDsrSfzCardaddress1() : "") + "</DSR_SFZ_CARDADDRESS1>");
			sb.append("<DSR_SFZ_YY1>" + (null != sfrzCjxxb.getDsrSfzYy1() ? sfrzCjxxb.getDsrSfzYy1() : "") + "</DSR_SFZ_YY1>");
			sb.append("<DSR_ZZJG_ZH1>" + (null != sfrzCjxxb.getDsrZzjgZh1() ? sfrzCjxxb.getDsrZzjgZh1() : "") + "</DSR_ZZJG_ZH1>");
			sb.append("<DSR_ZZJG_FRDB1>" + (null != sfrzCjxxb.getDsrZzjgFrdb1() ? sfrzCjxxb.getDsrZzjgFrdb1() : "") + "</DSR_ZZJG_FRDB1>");
			sb.append("<DSR_ZZJG_YYZZ1>" + (null != sfrzCjxxb.getDsrZzjgYyzz1() ? sfrzCjxxb.getDsrZzjgYyzz1() : "") + "</DSR_ZZJG_YYZZ1>");
			sb.append("<DSR_ZZJG_DWMC1>" + (null != sfrzCjxxb.getDsrZzjgDwmc1() ? sfrzCjxxb.getDsrZzjgDwmc1() : "") + "</DSR_ZZJG_DWMC1>");
			sb.append("<DSR_ZZJG_DZ1>" + (null != sfrzCjxxb.getDsrZzjgDz1() ? sfrzCjxxb.getDsrZzjgDz1() : "") + "</DSR_ZZJG_DZ1>");
			sb.append("<DSR_ZZJG_NJRQ1>" + (null != sfrzCjxxb.getDsrZzjgNjrq1() ? sfrzCjxxb.getDsrZzjgNjrq1() : "") + "</DSR_ZZJG_NJRQ1>");
			sb.append("<DSR_ZZJG_NJYXQ1>" + (null != sfrzCjxxb.getDsrZzjgNjyxq1() ? sfrzCjxxb.getDsrZzjgNjyxq1() : "") + "</DSR_ZZJG_NJYXQ1>");
			sb.append("<DSR_ZZJG_YY1>" + (null != sfrzCjxxb.getDsrZzjgYy1() ? sfrzCjxxb.getDsrZzjgYy1() : "") + "</DSR_ZZJG_YY1>");
			sb.append("<DBR_SFZ_CARDNAME1>" + (null != sfrzCjxxb.getDbrSfzCardname1() ? sfrzCjxxb.getDbrSfzCardname1() : "") + "</DBR_SFZ_CARDNAME1>");
			sb.append("<DBR_SFZ_CARDSEX1>" + (null != sfrzCjxxb.getDbrSfzCardsex1() ? sfrzCjxxb.getDbrSfzCardsex1() : "") + "</DBR_SFZ_CARDSEX1>");
			sb.append("<DBR_SFZ_CARDNO1>" + (null != sfrzCjxxb.getDbrSfzCardno1() ? sfrzCjxxb.getDbrSfzCardno1() : "") + "</DBR_SFZ_CARDNO1>");
			sb.append("<DBR_SFZ_CARDADDRESS1>" + (null != sfrzCjxxb.getDbrSfzCardaddress1() ? sfrzCjxxb.getDbrSfzCardaddress1() : "") + "</DBR_SFZ_CARDADDRESS1>");
			sb.append("<DBR_SFZ_YY1>" + (null != sfrzCjxxb.getDbrSfzYy1() ? sfrzCjxxb.getDbrSfzYy1() : "") + "</DBR_SFZ_YY1>");
			sb.append("<DBR_ZZJG_ZH1>" + (null != sfrzCjxxb.getDbrZzjgZh1() ? sfrzCjxxb.getDbrZzjgZh1() : "") + "</DBR_ZZJG_ZH1>");
			sb.append("<DBR_ZZJG_FRDB1>" + (null != sfrzCjxxb.getDbrZzjgFrdb1() ? sfrzCjxxb.getDbrZzjgFrdb1() : "") + "</DBR_ZZJG_FRDB1>");
			sb.append("<DBR_ZZJG_YYZZ1>" + (null != sfrzCjxxb.getDbrZzjgYyzz1() ? sfrzCjxxb.getDbrZzjgYyzz1() : "") + "</DBR_ZZJG_YYZZ1>");
			sb.append("<DBR_ZZJG_DWMC1>" + (null != sfrzCjxxb.getDbrZzjgDwmc1() ? sfrzCjxxb.getDbrZzjgDwmc1() : "") + "</DBR_ZZJG_DWMC1>");
			sb.append("<DBR_ZZJG_DZ1>" + (null != sfrzCjxxb.getDbrZzjgDz1() ? sfrzCjxxb.getDbrZzjgDz1() : "") + "</DBR_ZZJG_DZ1>");
			sb.append("<DBR_ZZJG_NJRQ1>" + (null != sfrzCjxxb.getDbrZzjgNjrq1() ? sfrzCjxxb.getDbrZzjgNjrq1() : "") + "</DBR_ZZJG_NJRQ1>");
			sb.append("<DBR_ZZJG_NJYXQ1>" + (null != sfrzCjxxb.getDbrZzjgNjyxq1() ? sfrzCjxxb.getDbrZzjgNjyxq1() : "") + "</DBR_ZZJG_NJYXQ1>");
			sb.append("<DBR_ZZJG_YY1>" + (null != sfrzCjxxb.getDbrZzjgYy1() ? sfrzCjxxb.getDbrZzjgYy1() : "") + "</DBR_ZZJG_YY1>");
			sb.append("<LRR>" + (null != sfrzCjxxb.getLrr() ? sfrzCjxxb.getLrr() : "") + "</LRR>");
			sb.append("<LRRXM>" + (null != sfrzCjxxb.getLrrxm() ? sfrzCjxxb.getLrrxm() : "") + "</LRRXM>");
			sb.append("<LRBMDM>" + (null != sfrzCjxxb.getLrbmdm() ? sfrzCjxxb.getLrbmdm() : "") + "</LRBMDM>");
			sb.append("<LRBMMC>" + (null != sfrzCjxxb.getLrbmmc() ? sfrzCjxxb.getLrbmmc() : "") + "</LRBMMC>");
			sb.append("<LRIP>" + (null != sfrzCjxxb.getLrip() ? sfrzCjxxb.getLrip() : "") + "</LRIP>");
			sb.append("<LRMAC>" + (null != sfrzCjxxb.getLrmac() ? sfrzCjxxb.getLrmac() : "") + "</LRMAC>");
			sb.append("<SHRDM>" + (null != sfrzCjxxb.getShrdm() ? sfrzCjxxb.getShrdm() : "") + "</SHRDM>");
			sb.append("<SHRXM>" + (null != sfrzCjxxb.getShrxm() ? sfrzCjxxb.getShrxm() : "") + "</SHRXM>");
			sb.append("<SHBM>" + (null != sfrzCjxxb.getShbm() ? sfrzCjxxb.getShbm() : "") + "</SHBM>");
			sb.append("<SHBM_KJ>" + (null != sfrzCjxxb.getShbmKj() ? sfrzCjxxb.getShbmKj() : "") + "</SHBM_KJ>");
			sb.append("<SHSJ>" + (null != sfrzCjxxb.getShsj() ? sfrzCjxxb.getShsj() : "") + "</SHSJ>");
			sb.append("<SHIP>" + (null != sfrzCjxxb.getShip() ? sfrzCjxxb.getShip() : "") + "</SHIP>");
			sb.append("<SHMAC>" + (null != sfrzCjxxb.getShmac() ? sfrzCjxxb.getShmac() : "") + "</SHMAC>");
			sb.append("<PHOTOS>" + (null != sfrzCjxxb.getSynFlag() ? sfrzCjxxb.getSynFlag() : "") + "</PHOTOS>");
			sb.append("<DJZSDZ>" + (null != sfrzCjxxb.getDjzsdz() ? sfrzCjxxb.getDjzsdz() : "") + "</DJZSDZ>");
			sb.append("<XPHZ>" + (null != sfrzCjxxb.getXphz() ? sfrzCjxxb.getXphz() : "") + "</XPHZ>");
			sb.append("<JZZDZ>" + (null != sfrzCjxxb.getJzzdz() ? sfrzCjxxb.getJzzdz() : "") + "</JZZDZ>");
			sb.append("<SFZMHMYXQ>" + (null != sfrzCjxxb.getSfzmhmyxq() ? sfrzCjxxb.getSfzmhmyxq() : "") + "</SFZMHMYXQ>");
			sb.append("<JZZYXQ>" + (null != sfrzCjxxb.getJzzyxq() ? sfrzCjxxb.getJzzyxq() : "") + "</JZZYXQ>");
			sb.append("<SFSH>" + (null != sfrzCjxxb.getSfsh() ? sfrzCjxxb.getSfsh() : "") + "</SFSH>");
			sb.append("<JZZHM>" + (null != sfrzCjxxb.getJzzhm() ? sfrzCjxxb.getJzzhm() : "") + "</JZZHM>");
			sb.append("<RELATHION>"+(null != sfrzCjxxb.getTranFlag() ? sfrzCjxxb.getTranFlag() : "")+"</RELATHION>");
			sb.append("<FINGER1>"+(null != sfrzCjxxb.getFinger1() ? sfrzCjxxb.getFinger1() : "")+"</FINGER1>");
			sb.append("<FINGER2>"+(null != sfrzCjxxb.getFinger2() ? sfrzCjxxb.getFinger2() : "")+"</FINGER2>");
			sb.append("<FINGER3>"+(null != sfrzCjxxb.getFinger3() ? sfrzCjxxb.getFinger3() : "")+"</FINGER3>");
			sb.append("<FINGER4>"+(null != sfrzCjxxb.getFinger4() ? sfrzCjxxb.getFinger4() : "")+"</FINGER4>");
			sb.append("</request>");
			return sb.toString();
		}
		return null;
	}
	
	public String objectToXml2(String cid,String photo_str,String picnum,String photo_type) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("<xml version=\"1.0\"  encode=\"utf-8\"><request>");
		sb.append("<CID>"+cid+"</CID>");
		sb.append("<PHOTO_STR>"+photo_str+"</PHOTO_STR>");
		sb.append("<PHOTO_TYPE>"+photo_type+"</PHOTO_TYPE>");
		sb.append("<PICNUM>"+picnum+"</PICNUM>");
		sb.append("</request>");
		return sb.toString();
	}
	
	public static Map<String,String> parseXml(String strxml) throws Exception{
		//strxml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><return><head><code>0000</code><msg>采集成功，您的采集流水号为：1234</msg><body><cid>1234</cid></body></head></return>";
		Document document = DocumentHelper.parseText(strxml);
		Element root = document.getRootElement();
		Element head = root.element("head");
		Element body = head.element("body");
		String code = head.element("code").getText();
		String msg = head.element("msg").getText();
		Map<String,String> retmap = new HashMap<String,String>();
		retmap.put("code", code);
		retmap.put("msg", msg);
		if("0000".equals(code)){
			String cid = body.element("cid").getText();
			retmap.put("cid", cid);
		}
		
		return retmap;
	}
	
	public SfrzCjxxb parseXml2(String strxml) throws Exception{
		//strxml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><return><head><code>0000</code><msg>采集成功，您的采集流水号为：1234</msg><body><cid>1234</cid></body></head></return>";
		Document document = DocumentHelper.parseText(strxml);
		Element root = document.getRootElement();
		Element head = root.element("head");
		Element body = head.element("body");
		String code = head.element("code").getText();
		String msg = head.element("msg").getText();
		SfrzCjxxb sc = new SfrzCjxxb();
		if(!"0000".equals(code)){
			String xm = body.element("XM").getText();
			String sfzmmc = body.element("SFZMMC").getText();
			String sfzmhm = body.element("SFZMHM").getText();
			String xb = body.element("XB").getText();
			String gj = body.element("GJ").getText();
			String mz = body.element("MZ").getText();
			String jg = body.element("JG").getText();
			String txdz = body.element("TXDZ").getText();
			String dzyx = body.element("DZYX").getText();
			String gddh = body.element("GDDH").getText();
			String yddh = body.element("YDDH").getText();
			String wx = body.element("WX").getText();
			String wb = body.element("WB").getText();
			String qq = body.element("QQ").getText();
			String rzly = body.element("RZLY").getText();
			String rzjs = body.element("RZJS").getText();
			
			String djzsdz = body.element("DJZSDZ").getText();
			String xphz = body.element("XPHZ").getText();
			String jzzdz = body.element("JZZDZ").getText();
			String sfzmhmyxq = body.element("SFZMHMYXQ").getText();
			String jzzyxq = body.element("JZZYXQ").getText();
			String sfsh = body.element("SFSH").getText();
			String jzzhm = body.element("JZZHM").getText();
			
			sc.setXm(xm);
			sc.setSfzmmc(sfzmmc);
			sc.setSfzmhm(sfzmhm);
			sc.setXb(xb);
			sc.setGj(gj);
			sc.setMz(mz);
			sc.setJg(jg);
			sc.setTxdz(txdz);
			sc.setDzyx(dzyx);
			sc.setGddh(gddh);
			sc.setYddh(yddh);
			sc.setWx(wx);
			sc.setWb(wb);
			sc.setQq(qq);
			sc.setRzly(rzly);
			sc.setRzjs(rzjs);
			sc.setSynFlag(msg);
			sc.setTranFlag(code);
			
			sc.setDjzsdz(djzsdz);
			sc.setXphz(xphz);
			sc.setJzzdz(jzzdz);
			sc.setSfzmhmyxq(sfzmhmyxq);
			sc.setJzzyxq(jzzyxq);
			sc.setSfsh(sfsh);
			sc.setJzzhm(jzzhm);
		}else{
			sc.setSynFlag(msg);
			sc.setTranFlag(code);
		}
		return sc;
	}
	
	public SfrzCjxxb getSfrzCjxxb(String cid) throws Exception{
		StringBuffer sb = new StringBuffer();
		SfrzCjxxb sc = new SfrzCjxxb();
		if(null != cid){
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><request>");
			sb.append("<CID>" + cid + "</CID>");
			sb.append("<SFZMHM>" + "" + "</SFZMHM>");
			sb.append("</request>");
			String resstr = sfrzCjxxbDao.getSfrzCjxxb(sb.toString());
			sc = parseXml2(resstr);
		}
		return sc;
	}
	
	public SfrzCjxxb getSfrzCjxxbInfo(String cid) throws Exception{
		SfrzCjxxb sc = (SfrzCjxxb) defaultDao.getRepository(cid, SfrzCjxxb.class);
		if(null != sc){
			if(null != sc.getShbm()){
				sc.setShbm(getDeptName(sc.getShbm()));
			}
		}
		return sc;
	}
	
	
	@SuppressWarnings("unchecked")
	public Map getPhoto(String cid) throws Exception{
		Map map = new HashMap();
		if(null != cid){
			List list = defaultDao.getRepositories("from SfrzCjxxbPhoto where cid = '" + cid +"'");
			if(null != list && list.size() > 0){
				for(int i = 0;i < list.size(); i ++){
					SfrzCjxxbPhoto scp = (SfrzCjxxbPhoto)list.get(i);
					if("1".equals(scp.getIsJm())){
						map.put("a"+scp.getPhotoType(), scp.getPhotoStr());
					}else{
						map.put("b"+scp.getPhotoType(), scp.getPid());
						map.put("a"+scp.getPhotoType(), "");
					}
				}
			}
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Map getPhotoBlob(String cid) throws Exception{
		Map map = new HashMap();
		if(null != cid){
			List list = defaultDao.getRepositories("from SfrzCjxxbPhoto where cid = '" + cid +"'");
			if(null != list && list.size() > 0){
				for(int i = 0;i < list.size(); i ++){
					SfrzCjxxbPhoto scp = (SfrzCjxxbPhoto)list.get(i);
					map.put("a"+scp.getPhotoType(), scp.getPid());
				}
			}
		}
		return map;
	}
	
	public Integer getJzzinfo(String jzzno,String xm) throws Exception{
		return sfrzCjxxbDao.getJzzinfo(jzzno, xm);
	}
	
	@SuppressWarnings("unchecked")
	public List<SfrzSjzd> getSfrzSjzd(String dmz, String dmsm, String dmlb) throws Exception{
		List sfrzSjzds = null;
		StringBuffer hqlbuffer = new StringBuffer("from SfrzSjzd s where 1=1 ");
		if(dmlb != null && !"".equals(dmlb)){
			hqlbuffer.append(" and s.dmlb = '" + dmlb + "'");
		}
		if(dmz != null && !"".equals(dmz)){
			hqlbuffer.append(" and s.dmz = '"+dmz+"'");
		}
		if(dmsm != null && !"".equals(dmsm)){
			hqlbuffer.append(" and s.dmsm = '"+dmsm+"'");
		}
		hqlbuffer.append(" order by s.dmz asc");
		sfrzSjzds = this.defaultDao.getRepositories(hqlbuffer.toString());
		return sfrzSjzds;
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
	
	@SuppressWarnings("unchecked")
	public List getDrvList(String sfzmhm) throws Exception {
		if(null != sfzmhm){
			String sql = "select sfzmhm,xm,cclzrq,zt,yxqz,zjcx,ljjf from ES_DRV_LICENSE@edb.m85 where sfzmhm='"+sfzmhm+"'";
			List list = this.defaultDao.findSQL(sql);
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List getVeglist(String sfzmhm) throws Exception {
		if(null != sfzmhm){
			String sql = "select cast(hphm as varchar2(50)) hphm,cast(hpzl as varchar2(50)) hpzl,cast(sfzmmc as varchar2(50)) sfzmmc,sfzmhm,ccdjrq,clsbdh,fdjh from es_vehicle where sfzmhm='"+sfzmhm+"'";
			List list = this.defaultDao.findSQL(sql);
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List getXphzList(String sfzmhm) throws Exception{
		if(null != sfzmhm){
			String sql = "select sfzmhm,photo_no,czrq from ez_drv_picture where sfzmhm='"+sfzmhm+"'";
			List list = this.defaultDao.findSQL(sql);
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List getdbrList(String sfzhm,String rzjs) throws Exception {
		List list=new ArrayList();
		if(null != sfzhm&&null!=rzjs){
			if("2".equals(rzjs)){
				String sql = "select login_user,login_truename,cid from sfrz_userinfo where login_user='"+sfzhm+"' and yhjs = '4'";
				list = this.defaultDao.findSQL(sql);
			}else{
				String sql = "select login_user,login_truename,cid from sfrz_userinfo where login_user='"+sfzhm+"' and yhjs = '5'";
				list = this.defaultDao.findSQL(sql);
			}
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List getdbr(String sfzhm) throws Exception {
		if(null != sfzhm){
			String sql = "select * from sfrz_userinfo where login_user='"+sfzhm+"'";
			List list = this.defaultDao.findSQL(sql);
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Map getCode(String dmlb) throws Exception {
		String sql = "select dmz,dmsm1 from es_veh_code where dmlb='"+dmlb+"'";
		List list = defaultDao.findSQL(sql);
		Map map = new HashMap();
		for(int i =0; i < list.size() ; i ++){
			Object[] ss = (Object[])list.get(i);
			map.put(ss[0], ss[1]);
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public String getCodeVal(String dmlb,String dmz) throws Exception{
		String sql = "SELECT dmsm1 from es_veh_code WHERE DMLB='"+dmlb+"' and dmz='"+dmz+"'";
		List list = defaultDao.findSQL(sql);
		String restr = "";
		if(null != list && list.size() > 0){
			restr = list.get(0).toString();
		}else{
			restr = dmz;
		}
		return restr;
	}
	
	@SuppressWarnings({ "unchecked" })
	public String getDeptName(String deptid) throws Exception {
		String sql = "select t.org_name from v_veh_org_ycs t where t.org_id='"+deptid+"'";
		List list = defaultDao.findSQL(sql);
		String restr = "";
		if(null != list && list.size() > 0){
			restr = list.get(0).toString();
		}else{
			restr = deptid;
		}
		return restr;
	}
	

	@SuppressWarnings("unchecked")
	public List getUserInfo(String loginuser,String rejsval) throws Exception {
		List list = new ArrayList();
		if(null != loginuser&&null!=rejsval){
			if("4".equals(rejsval)){
				String sql = "select * from sfrz_cjxxb where sfzmhm='"+loginuser+"' and rzjs = '2'" ;
				list=this.defaultDao.findSQL(sql, SfrzCjxxb.class); 
			}else{
				String sql = "select * from sfrz_cjxxb where sfzmhm='"+loginuser+"' and rzjs = '3'" ;
				list=this.defaultDao.findSQL(sql, SfrzCjxxb.class); 
			}
			
			if(list.size()>0&&null!=list){
				for(int i = 0; i < list.size(); i++){
					SfrzCjxxb info =(SfrzCjxxb)list.get(i);
					if(null != info){
						/*if("0".equals(info.getZt())){
							info.setZt("待激活");
						}else if("1".equals(info.getZt())){
							info.setZt("已激活");
						}else if("F".equals(info.getZt())){
							info.setZt("无效");
						}*/
						if("A".equals(info.getRzly())){
							info.setRzly("金融");
						}else if("B".equals(info.getRzly())){
							info.setRzly("面签");
						}else if("C".equals(info.getRzly())){
							info.setRzly("微信");
						}else if("C+B".equals(info.getRzly())){
							info.setRzly("微信+面签");
						}else if("A+B".equals(info.getRzly())){
							info.setRzly("金融+面签");
						}
					}
				}
				return list;
			}
			return null;
		}
		return null;
	
	
	}
	
	public static void main(String[] args) throws Exception {
//		String srcs = "<request><xm>测试</xm><sfzmhm></sfzmhm><xb></xb><zjcx></zjcx><dabh></dabh><jxyh></jxyh></request>";
//		srcs = DESCorder.encryptModeToString(srcs, "oPs9UzqQXka0rd6lw3sN7Ibb");
//		Client client = new Client(new URL("http://100.100.21.62:8081/xxfbpt/services/xxfbptservice?wsdl"));
//		Object[] results = client.invoke("xxptSchuding",new Object[] { "wscgs", "wscgs", "S1001", srcs });
//		System.out.println(results[0]);
		parseXml("");
	}
	
	public FileUploadDao getFileUploadDao() {
		return fileUploadDao;
	}

	public void setFileUploadDao(FileUploadDao fileUploadDao) {
		this.fileUploadDao = fileUploadDao;
	}

	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public SfrzCjxxbDao getSfrzCjxxbDao() {
		return sfrzCjxxbDao;
	}

	public void setSfrzCjxxbDao(SfrzCjxxbDao sfrzCjxxbDao) {
		this.sfrzCjxxbDao = sfrzCjxxbDao;
	}

	public String getsfshList(String sfzmhm, String xm) throws Exception {
		String restr =  sfrzCjxxbDao.getJzzinfo2(sfzmhm, xm);
 		return restr;
	}

	@SuppressWarnings("unchecked")
	public SfrzUserinfo getSfrzA(String sfzmhm) throws Exception {
		String sql = "select * from sfrz_userinfo where login_user='"+sfzmhm+"'" ;
		List list=this.defaultDao.findSQL(sql, SfrzUserinfo.class); 
		if(list.size()==0){
			return null;
		}else{
			SfrzUserinfo sc = (SfrzUserinfo)list.get(0);
			return sc;
		}
	}

	@SuppressWarnings("unchecked")
	public RscDby getRscDby(String sfzmhm) throws Exception {
		String sql = "select * from RSC_DBY where SFZMHM='"+sfzmhm+"' and  scbz='1'" ;
		List list=this.defaultDao.findSQL(sql, RscDby.class); 
		if(list.size()==0){
			return null;
		}else{
			RscDby sc = (RscDby)list.get(0);
			return sc;
		}
	}
	
	@SuppressWarnings("unchecked")
	public RscDbyZjxxb getRscDbyZjxxb(String sfzmhm) throws Exception {
		String sql = "select * from Rsc_Dby_Zjxxb where SFZMHM='"+sfzmhm+"'" ;
		List list=this.defaultDao.findSQL(sql, RscDbyZjxxb.class); 
		if(list.size()==0){
			return null;
		}else{
			RscDbyZjxxb sc = (RscDbyZjxxb)list.get(0);
			return sc;
		}
	}

	public int getYzdbrSfff(String sfzmhm) throws Exception {
		String sql="select count(*) from  dbjg_hmdyh where   dqzt='1' and  Syn_flag !='DW' and sfzmhm='"+sfzmhm+"'";
		int size = defaultDao.getRepositoryBySQLListSize(sql.toString());
		return size;
	}

	@SuppressWarnings("unchecked")
	public List getDbyList(HttpServletRequest request)
			throws Exception {
		List dbybList = new ArrayList();
		StringBuffer sqlQuery =new StringBuffer("select y.xh,y.xm,y.sfzmhm,g.xh gxh,g.jgmc,y.bayxq,y.lrsj,y.cid from RSC_DBY y,RSC_DBJG g " +
				"where y.zzjgdmz=g.zzjgdmz " +
				"and y.dbyzt='-1' and y.cid is not null ");
		String jgmc = request.getParameter("jgmc");
		String sfzmhm = request.getParameter("sfzmhm");
		//身份证明号码
		if(sfzmhm != null && !"".equals(sfzmhm)){
			sqlQuery.append(" and y.sfzmhm = '" + sfzmhm + "' ");
			request.setAttribute("sfzmhm", sfzmhm);
		}else{
			// 姓名
			if (jgmc != null && !"".equals(jgmc)) {
				sqlQuery.append(" and g.jgmc like '%"+jgmc+"%' ");
				request.setAttribute("jgmc", jgmc);
			}
		}
		
		sqlQuery.append(" order by y.lrsj desc ");
		dbybList = this.defaultDao.findSQL(sqlQuery.toString());
		request.setAttribute("dbybList", dbybList);
		return dbybList;
	}

	public Integer updateDby(HttpServletRequest request, String cid,
			String dbyzt, String tbyy) throws Exception {
		RscDbyLog log=new RscDbyLog();
		RscDby dby = (RscDby) defaultDao.getRepository(
				cid, RscDby.class);
		if (null != dby) {
			User user = (User) request.getSession().getAttribute(
					SysConst.USERBEAN);
			dby.setTbyy(tbyy);
			dby.setDbyzt(dbyzt);
			defaultDao.updateRepository(dby);
			log.setBayxq(dby.getBayxq());
			log.setCzip(getLoginIp(request));
			log.setCzlx("审核");
			log.setCzr(user.getName());
			log.setCzsj(new Date());
			log.setDbyxh(dby.getXh());
			log.setDbyzh(dby.getDbyzh());
			log.setDbyzt(dby.getDbyzt());
			log.setDbyzxph(dby.getDbyzxph());
			log.setDbzl(dby.getDbzl());
			log.setDzyx(dby.getDzyx());
			log.setGddh(dby.getGddh());
			log.setScyy(dby.getTbyy());
			log.setSfzmhm(dby.getSfzmhm());
			log.setSfzmmc(dby.getSfzmmc());
			log.setSsdbjg(dby.getSsdbjg());
			log.setXm(dby.getXm()); 
			log.setYddh(dby.getYddh());
			log.setYzbm(dby.getYzbm());
			log.setZpbz(dby.getZpbz());
			log.setZsdz(dby.getZsdz());
			this.defaultDao.addRepository(log);
			return 0;
		}
		return 1;
	}
	





}
