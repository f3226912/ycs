package com.ycszh.ssh.service.guoshui.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.ycszh.ssh.dao.guoshui.IGuoshuiLianwDao;
import com.ycszh.ssh.hbm.guoshui.FpEsc;
import com.ycszh.ssh.hbm.guoshui.FpEscLog;
import com.ycszh.ssh.hbm.guoshui.FpXc;
import com.ycszh.ssh.hbm.guoshui.FpXcLog;
import com.ycszh.ssh.hbm.guoshui.FpXcgzs;
import com.ycszh.ssh.hbm.guoshui.FpXcgzsLog;
import com.ycszh.ssh.service.guoshui.IGuoshuiLianwService;
import com.ycszh.util.Client4GuoShui;

public class GuoshuiLianwServiceImpl implements IGuoshuiLianwService {
	
	private IGuoshuiLianwDao guoShuiLianwDao;
	private Client4GuoShui gs = new Client4GuoShui();
	private final static Logger log = Logger.getLogger(GuoshuiLianwServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<FpEsc> getFpEscList(HttpServletRequest request)
			throws Exception {
		List<FpEsc> fpescList = null;
		String fpdm = request.getParameter("cgfpdm");
		String fphm = request.getParameter("cgfphm");
		String mfczxm = request.getParameter("mfczxm");
		String mfsfzmhm = request.getParameter("mfsfzmhm");
		String cjh = request.getParameter("cjh");
		if((fpdm == null || "".equals(fpdm)) && (fphm == null || "".equals(fphm)) 
				&& (mfczxm == null || "".equals(mfczxm)) && (mfsfzmhm == null || "".equals(mfsfzmhm))
				&& (cjh == null || "".equals(cjh))){
			return null;
		}else{
			StringBuffer hql = new StringBuffer(" from FpEsc xc where 1=1 ");
			if(fpdm != null && !"".equals(fpdm)){
				hql.append(" and xc.fpdm = '"+fpdm+"' ");
				request.setAttribute("cgfpdm", fpdm);
			}
			if(fphm != null && !"".equals(fphm)){
				hql.append(" and xc.fphm = '"+fphm+"' ");
				request.setAttribute("cgfphm", fphm);
			}
			if(mfczxm != null && !"".equals(mfczxm)){
				hql.append(" and xc.mfxm = '"+mfczxm+"' ");
				request.setAttribute("mfczxm", mfczxm);
			}
			if(mfsfzmhm != null && !"".equals(mfsfzmhm)){
				hql.append(" and xc.mfid = '"+mfsfzmhm+"' ");
				request.setAttribute("mfsfzmhm", mfsfzmhm);
			}
			if(cjh != null && !"".equals(cjh)){
				hql.append(" and xc.cjh = '"+cjh+"' ");
				request.setAttribute("cjh", cjh);
			}
			fpescList = this.guoShuiLianwDao.getRepositories(hql.toString());
			request.setAttribute("fpescList", fpescList);
		}
		return fpescList;
	}
	
	public Integer getFpEscCount(HttpServletRequest request) throws Exception{
		int count = 0;
		String fpdm = request.getParameter("cgfpdm");
		String fphm = request.getParameter("cgfphm");
		String mfczxm = request.getParameter("mfczxm");
		String mfsfzmhm = request.getParameter("mfsfzmhm");
		String cjh = request.getParameter("cjh");
		if((fpdm == null || "".equals(fpdm)) && (fphm == null || "".equals(fphm)) 
				&& (mfczxm == null || "".equals(mfczxm)) && (mfsfzmhm == null || "".equals(mfsfzmhm))
				&& (cjh == null || "".equals(cjh))){
			return 0;
		}else{
			StringBuffer hql = new StringBuffer("select count(0) from Fp_Esc xc where 1=1 ");
			if(fpdm != null && !"".equals(fpdm)){
				hql.append(" and xc.fpdm = '"+fpdm+"' ");
				request.setAttribute("cgfpdm", fpdm);
			}
			if(fphm != null && !"".equals(fphm)){
				hql.append(" and xc.fphm = '"+fphm+"' ");
				request.setAttribute("cgfphm", fphm);
			}
			if(mfczxm != null && !"".equals(mfczxm)){
				hql.append(" and xc.mfxm = '"+mfczxm+"' ");
				request.setAttribute("mfczxm", mfczxm);
			}
			if(mfsfzmhm != null && !"".equals(mfsfzmhm)){
				hql.append(" and xc.mfid = '"+mfsfzmhm+"' ");
				request.setAttribute("mfsfzmhm", mfsfzmhm);
			}
			if(cjh != null && !"".equals(cjh)){
				hql.append(" and xc.cjh = '"+cjh+"' ");
				request.setAttribute("cjh", cjh);
			}
			count = this.guoShuiLianwDao.getRepositoryBySQLListSize(hql.toString());
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<FpXcgzs> getFpxcgzsList(HttpServletRequest request)
			throws Exception {
		List<FpXcgzs> fpxcgzsList = null;
		String fpdm = request.getParameter("cgfpdm");
		String fphm = request.getParameter("cgfphm");
		String cjh = request.getParameter("cjh");
		String fdjhm = request.getParameter("fdjhm");
		String wszmbh = request.getParameter("wszmbh");
		String cxlx = request.getParameter("cxlx");
		StringBuffer hql = new StringBuffer(" from FpXcgzs xc where 1=1 ");
		if(cxlx != null && "changgui".equals(cxlx)){
			if((fpdm == null || "".equals(fpdm)) && (fphm == null || "".equals(fphm)) 
					&& (cjh == null || "".equals(cjh)) && (fdjhm == null || "".equals(fdjhm))){
				return null;
			}else{
				if(fpdm != null && !"".equals(fpdm)){
					hql.append(" and xc.fpdm = '"+fpdm+"' ");
					request.setAttribute("cgfpdm", fpdm);
				}
				if(fphm != null && !"".equals(fphm)){
					hql.append(" and xc.fphm = '"+fphm+"' ");
					request.setAttribute("cgfphm", fphm);
				}
				if(cjh != null && !"".equals(cjh)){
					hql.append(" and xc.cjh = '"+cjh+"' ");
					request.setAttribute("cjh", cjh);
				}
				if(fdjhm != null && !"".equals(fdjhm)){
					hql.append(" and xc.fdjh = '"+fdjhm+"' ");
					request.setAttribute("fdjhm", fdjhm);
				}
				fpxcgzsList = this.guoShuiLianwDao.getRepositories(hql.toString());
				request.setAttribute("fpxcgzsList", fpxcgzsList);
			}
			
		}else if("wanshui".equals(cxlx)){
			if(wszmbh != null && !"".equals(wszmbh)){
				hql.append(" and xc.gzszmbh = '"+wszmbh+"' ");
				fpxcgzsList = this.guoShuiLianwDao.getRepositories(hql.toString());
				request.setAttribute("fpxcgzsList", fpxcgzsList);
				request.setAttribute("wszmbh", wszmbh);
				
			}else{
				return null;
			}
		}else{
			return null;
		}
		
		
		return fpxcgzsList;
	}
	
	public Integer getFpxcgzsCount(HttpServletRequest request) throws Exception{
		int count = 0;
		String fpdm = request.getParameter("cgfpdm");
		String fphm = request.getParameter("cgfphm");
		String cjh = request.getParameter("cjh");
		String fdjhm = request.getParameter("fdjhm");
		if((fpdm == null || "".equals(fpdm)) && (fphm == null || "".equals(fphm)) 
				&& (cjh == null || "".equals(cjh)) && (fdjhm == null || "".equals(fdjhm))){
			return 0;
		}else{
			StringBuffer hql = new StringBuffer("select count(0) from FP_XCGZS xc where 1=1 ");
			if(fpdm != null && !"".equals(fpdm)){
				hql.append(" and xc.fpdm = '"+fpdm+"' ");
				request.setAttribute("cgfpdm", fpdm);
			}
			if(fphm != null && !"".equals(fphm)){
				hql.append(" and xc.fphm = '"+fphm+"' ");
				request.setAttribute("cgfphm", fphm);
			}
			if(cjh != null && !"".equals(cjh)){
				hql.append(" and xc.cjh = '"+cjh+"' ");
				request.setAttribute("cjh", cjh);
			}
			if(fdjhm != null && !"".equals(fdjhm)){
				hql.append(" and xc.fdjh = '"+fdjhm+"' ");
				request.setAttribute("fdjhm", fdjhm);
			}
			count = this.guoShuiLianwDao.getRepositoryBySQLListSize(hql.toString());
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<FpXc> getFpxcList(HttpServletRequest request) throws Exception {
		List<FpXc> fpxcList = null;
		String fpdm = request.getParameter("cgfpdm");
		String fphm = request.getParameter("cgfphm");
		String mfczxm = request.getParameter("mfczxm");
		String mfsfzmhm = request.getParameter("mfsfzmhm");
		String cjh = request.getParameter("cjh");
		String fdjhm = request.getParameter("fdjhm");
		if((fpdm == null || "".equals(fpdm)) && (fphm == null || "".equals(fphm)) 
				&& (mfczxm == null || "".equals(mfczxm)) && (mfsfzmhm == null || "".equals(mfsfzmhm))
				&& (cjh == null || "".equals(cjh)) && (fdjhm == null || "".equals(fdjhm))){
			return null;
		}else{
			StringBuffer hql = new StringBuffer(" from FpXc xc where 1=1 ");
			if(fpdm != null && !"".equals(fpdm)){
				hql.append(" and xc.fpdm = '"+fpdm+"' ");
				request.setAttribute("cgfpdm", fpdm);
			}
			if(fphm != null && !"".equals(fphm)){
				hql.append(" and xc.fphm = '"+fphm+"' ");
				request.setAttribute("cgfphm", fphm);
			}
			if(mfczxm != null && !"".equals(mfczxm)){
				hql.append(" and xc.mfxm = '"+mfczxm+"' ");
				request.setAttribute("mfczxm", mfczxm);
			}
			if(mfsfzmhm != null && !"".equals(mfsfzmhm)){
				hql.append(" and xc.mfid = '"+mfsfzmhm+"' ");
				request.setAttribute("mfsfzmhm", mfsfzmhm);
			}
			if(cjh != null && !"".equals(cjh)){
				hql.append(" and xc.cjh = '"+cjh+"' ");
				request.setAttribute("cjh", cjh);
			}
			if(fdjhm != null && !"".equals(fdjhm)){
				hql.append(" and xc.fdjh = '"+fdjhm+"' ");
				request.setAttribute("fdjhm", fdjhm);
			}
			fpxcList = this.guoShuiLianwDao.getRepositories(hql.toString());
			request.setAttribute("fpxcList", fpxcList);
		}
		return fpxcList;
	}
	
	public Integer getFpxcCount(HttpServletRequest request) throws Exception{
		int count = 0;
		String fpdm = request.getParameter("cgfpdm");
		String fphm = request.getParameter("cgfphm");
		String mfczxm = request.getParameter("mfczxm");
		String mfsfzmhm = request.getParameter("mfsfzmhm");
		String cjh = request.getParameter("cjh");
		String fdjhm = request.getParameter("fdjhm");
		if((fpdm == null || "".equals(fpdm)) && (fphm == null || "".equals(fphm)) 
				&& (mfczxm == null || "".equals(mfczxm)) && (mfsfzmhm == null || "".equals(mfsfzmhm))
				&& (cjh == null || "".equals(cjh)) && (fdjhm == null || "".equals(fdjhm))){
			return 0;
		}else{
			StringBuffer hql = new StringBuffer("select count(0) from FP_XC xc where 1=1 ");
			if(fpdm != null && !"".equals(fpdm)){
				hql.append(" and xc.fpdm = '"+fpdm+"' ");
				request.setAttribute("cgfpdm", fpdm);
			}
			if(fphm != null && !"".equals(fphm)){
				hql.append(" and xc.fphm = '"+fphm+"' ");
				request.setAttribute("cgfphm", fphm);
			}
			if(mfczxm != null && !"".equals(mfczxm)){
				hql.append(" and xc.mfxm = '"+mfczxm+"' ");
				request.setAttribute("mfczxm", mfczxm);
			}
			if(mfsfzmhm != null && !"".equals(mfsfzmhm)){
				hql.append(" and xc.mfid = '"+mfsfzmhm+"' ");
				request.setAttribute("mfsfzmhm", mfsfzmhm);
			}
			if(cjh != null && !"".equals(cjh)){
				hql.append(" and xc.cjh = '"+cjh+"' ");
				request.setAttribute("cjh", cjh);
			}
			if(fdjhm != null && !"".equals(fdjhm)){
				hql.append(" and xc.fdjh = '"+fdjhm+"' ");
				request.setAttribute("fdjhm", fdjhm);
			}
			count = this.guoShuiLianwDao.getRepositoryBySQLListSize(hql.toString());
		}
		return count;
	}
	
	public Integer insertGuoShui(Object obj, HttpServletRequest request)
		throws Exception {
		try {
			this.guoShuiLianwDao.addObj(obj, request);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public Object getGuoShui(String dataType, String fpdm, String fphm) throws Exception {
		
		String returnStr = gs.webserviceInvoke4Query(dataType, fpdm, fphm);
		System.out.println(returnStr);
		if(returnStr.length() > 0){
			String xmlData = ParseGuoshuiXML.jiequXML(returnStr);
			if("XCFPStr.CGS.SJYY".equals(dataType)){ 
				//新车发票数据单笔实时读取
				List<FpXc> fpxcList = null;
				fpxcList = ParseGuoshuiXML.xcfpXMLSingleParse(xmlData.replaceAll("&lt;","<").replaceAll("&gt;",">"), "XCFPStr.CGS.SJYY");
				//记录日志
				FpXc fpxc = null;
				if(fpxcList != null && fpxcList.size() > 0){
					fpxc = fpxcList.get(0);
					System.out.println("新车发票数据单笔实时读取:"+fpxc.getFpdm()+","+fpxc.getFphm());
				}
				return fpxc;
			}else if("ESCFPStr.CGS.SJYY".equals(dataType)){
				//二手车发票数据单笔实时读取
				List<FpEsc> escList = null;
				escList = ParseGuoshuiXML.escXMLSingleParse(xmlData.replaceAll("&lt;","<").replaceAll("&gt;",">"));
				FpEsc fpEsc = null;
				if(escList != null && escList.size() > 0){
					fpEsc = escList.get(0);
					System.out.println("二手车发票数据单笔实时读取:"+fpEsc.getFpdm()+","+fpEsc.getFphm());
				}			
				return fpEsc;
			}else if("CgsFpStr.CGS.SJYY".equals(dataType)){
				//新车购置税数据单笔实时读取 
				List<FpXcgzs> xcgzsList = null;
				xcgzsList = ParseGuoshuiXML.xcgzsXMLSingleParse(xmlData.replaceAll("&lt;","<").replaceAll("&gt;",">"));
				FpXcgzs xcgzs = null;
				if(xcgzsList != null && xcgzsList.size() > 0){
					xcgzs = xcgzsList.get(0);
				}			
				return xcgzs;
			}
			return null;
		}else{
			return null;
		}
	}
	
	/**
	 * -1  表示没有结果
	 * -2  表示超时
	 * -3  异常
	 * 正数表示插入的记录数量
	 */
	@SuppressWarnings("unchecked")
	public Integer selGuoShuiList(String dataType, String startDate,
			String endDate, String czxm, String czbm, String cznr,
			String czip, String czrq) throws Exception {
		try{
			int resultCount = -1;
			
			//String returnStr = gs.webserviceInvoke4Sync(dataType, startDate, endDate);
			String returnStr = "";
			if(returnStr.length() > 0){
				String xmlData = ParseGuoshuiXML.jiequXML(returnStr);
				
				// 如果超时，返回 -2
				if("wait response timeout!".equals(xmlData)){
					resultCount = -2;
					return resultCount;
				}
				
				if("XcFpZLStr.CGS.SJYY".equals(dataType)){ 
					//新车发票数据增量同步
					List<FpXc> fpxcList = null;
					fpxcList = ParseGuoshuiXML.xcfpXMLSingleParse(xmlData.replaceAll("&lt;","<").replaceAll("&gt;",">"), "XcFpZLStr.CGS.SJYY");
					FpXc fpxc = null;
					FpXcLog fpXcLog = null;
					
					if(fpxcList != null){
						int size = fpxcList.size();
						for(int i = 0; i < size; i++){
							fpxc = fpxcList.get(i);
							String hql = "SELECT xc FROM FpXc xc WHERE xc.fpdm = '" + fpxc.getFpdm() + "' AND xc.fphm = '" + fpxc.getFphm() + "'";
							//System.out.println("FPDM: "+fpxc.getFpdm()+"FPHM: "+fpxc.getFphm());
							FpXc fpxcEmp = (FpXc) this.guoShuiLianwDao.getObj(hql);
							fpXcLog = new FpXcLog();
							if(fpxcEmp != null){
								//如果存在，则更新
								String fpxcId = fpxcEmp.getId();
								//getXclog(fpxcEmp,fpxc);
								fpxc.setId(fpxcId);
								this.guoShuiLianwDao.updateObj(fpxc);
								fpXcLog.setCznr("U");
							}else{
								//如果不存在，则添加
								this.guoShuiLianwDao.addObj(fpxc, null);
								fpXcLog.setCznr("I");
							}
							System.out.println(i+"、新车发票数据单笔实时读取:"+fpxc.getFpdm()+","+fpxc.getFphm());
							//添加到数据库
							fpXcLog = (FpXcLog)getXclog(fpXcLog, fpxc);
							fpXcLog.setCzxm(czxm);
							fpXcLog.setCzbm(czbm);
							fpXcLog.setCzip(czip);
							fpXcLog.setCzrq(new Date());
							//
							this.guoShuiLianwDao.addObj(fpXcLog, null);
							resultCount++;
							fpxc = null;
						}
						fpxcList = null;
					}
				}else if("EscFpZlStr.CGS.SJYY".equals(dataType)){
					//二手车发票数据增量同步
					List<FpEsc> escList = null;
					escList = ParseGuoshuiXML.escXMLSingleParse(xmlData.replaceAll("&lt;","<").replaceAll("&gt;",">"));
					FpEscLog escLog = null;
					if(escList != null && escList.size() > 0){
						int size = escList.size();
						for(int i = 0; i < size; i++){
							FpEsc fpEsc = new FpEsc();
							fpEsc = escList.get(i);
							String hql = "SELECT esc FROM FpEsc esc WHERE esc.fpdm = '" + fpEsc.getFpdm() + "' AND esc.fphm = '" + fpEsc.getFphm() + "'";
							FpEsc fpescEmp = (FpEsc) this.guoShuiLianwDao.getObj(hql);
							escLog = new FpEscLog();
							if(fpescEmp != null){
								//如果存在，则更新
								String fpEscId = fpescEmp.getId();
								//getXclog(fpescEmp,fpEsc);
								fpEsc.setId(fpEscId);
								this.guoShuiLianwDao.updateObj(fpEsc);
								escLog.setCznr("U");
							}else{
								//如果不存在，则添加
								this.guoShuiLianwDao.addObj(fpEsc, null);
								escLog.setCznr("I");
							}
							System.out.println(i+"、二手车发票数据单笔实时读取:"+fpEsc.getFpdm()+","+fpEsc.getFphm());
							escLog = (FpEscLog)getXclog(escLog, fpEsc);
							escLog.setCzxm(czxm);
							escLog.setCzbm(czbm);
							escLog.setCzip(czip);
							escLog.setCzrq(new Date());
							this.guoShuiLianwDao.addObj(escLog, null);
							resultCount++;
						}
						escList = null;
					}
					
				}else if("XcgzsStr.CGS.SJYY".equals(dataType)){
					//新车购置税数据增量同步
					List<FpXcgzs> xcgzsList = null;
					xcgzsList = ParseGuoshuiXML.xcgzsXMLSingleParse(xmlData.replaceAll("&lt;","<").replaceAll("&gt;",">"));
					FpXcgzs xcgzs = null;
					FpXcgzsLog xcgzsLog = null;
					if(xcgzsList != null && xcgzsList.size() > 0){
						int size = xcgzsList.size();
						for(int i = 0; i < size; i++){
							xcgzs = xcgzsList.get(i);
							String hql = "SELECT xcgzs FROM FpXcgzs xcgzs WHERE xcgzs.gzszmbh = '" + xcgzs.getGzszmbh() + "'";
							FpXcgzs fpxcgzsEmp = (FpXcgzs) this.guoShuiLianwDao.getObj(hql);
							xcgzsLog = new FpXcgzsLog();
							if(fpxcgzsEmp != null){
								xcgzs.setGzszmbh(fpxcgzsEmp.getGzszmbh());
								//如果存在，则更新
								//fpxcgzsEmp = (FpXcgzs)getXclog(fpxcgzsEmp, xcgzs); 
								this.guoShuiLianwDao.updateObj(xcgzs);
								xcgzsLog.setCznr("U");
							}else{
								//如果不存在，则添加
								this.guoShuiLianwDao.addObj(xcgzs, null);
								xcgzsLog.setCznr("I");
							}
							System.out.println(i+"、新车购置税数据单笔实时读取 :"+xcgzs.getFpdm()+","+xcgzs.getFphm());
							
							xcgzsLog = (FpXcgzsLog)getXclog(xcgzsLog, xcgzs);
							xcgzsLog.setCzxm(czxm);
							xcgzsLog.setCzbm(czbm);
							xcgzsLog.setCzip(czip);
							
							xcgzsLog.setCzrq(new Date());
							this.guoShuiLianwDao.addObj(xcgzsLog, null);
							resultCount++;
						}
						xcgzsList = null;
					}
				}
			}else{
				log.error("返回XML为空，查询时间为："+startDate+"。查询类型："+dataType+"。");
				return -3;
			}
			return resultCount;
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return -3;
		}
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
	
	public IGuoshuiLianwDao getGuoShuiLianwDao() {
		return guoShuiLianwDao;
	}

	public void setGuoShuiLianwDao(IGuoshuiLianwDao guoShuiLianwDao) {
		this.guoShuiLianwDao = guoShuiLianwDao;
	}
	
}
