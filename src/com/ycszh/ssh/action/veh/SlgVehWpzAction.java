package com.ycszh.ssh.action.veh;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.action.dydj.DydjServiceAction;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspb;
import com.ycszh.ssh.hbm.veh.DbjgZjxxb;
import com.ycszh.ssh.hbm.veh.DbjgZjxxbWzp;
import com.ycszh.ssh.hbm.veh.EsVehicle;
import com.ycszh.ssh.hbm.veh.SlgSjzdWzp;
import com.ycszh.ssh.hbm.veh.VehicleTempMidTest;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidIn;
import com.ycszh.ssh.service.drv.SlgDrvService;
import com.ycszh.ssh.service.drv.SlgSpxxService;
import com.ycszh.ssh.service.dydj.IDydjService;
import com.ycszh.ssh.service.veh.SlgVehWpzService;
import com.ycszh.ssh.service.veh.SlgVehService;
import com.ycszh.util.ExportExcelBean;
import com.ycszh.util.ExportTools;

@SuppressWarnings("serial")
public class SlgVehWpzAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(DydjServiceAction.class);
	private SlgVehService slgVehService;
	private SlgSpxxService slgService;
	private SlgDrvService slgDrvService;
	private IDydjService dydjService;
	private SlgVehWpzService jdcslWpzService;
	private EsVehicle esvehicle;
	private DbjgZjxxbWzp dbZjxxb;		//受理表（证件信息）
	private DydjYwsbspb dydjYwsbspb;
	private VehicleTempMidTest ptdo;	//登记信息、技术参数值
	private VehicleTempMidIn ptdoin;
	private int currentpage = 1;
	private List<DbjgZjxxbWzp> SlCxList = new ArrayList<DbjgZjxxbWzp>();
	//导出实体
    private ExportExcelBean eeb;
    //列表显示信息
    private Map<String, Object> parmsMap = new LinkedHashMap<String, Object>();
	/**
	 * 上传文件实体对象
	 */
	private File file1;
	private File file2;

	/**
	 * 上传文件类型
	 */
	private String file1ContentType;
	private String file2ContentType;
	
	//======================================================机动车受理——无拍照
	// 受理查询详细页面
	@SuppressWarnings("unchecked")
	public String initSlCx() throws Exception {
		//初始化
		
		 // 身份证明 对应表 PDASMYC_SJZD
		 List sfzmList =
		 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '19' order by dmz");
		 request.setAttribute("sfzmList", sfzmList);
		 // 取得住所邮政编码
		 List yzbmList =
		 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '50' order by dmz");
		 request.setAttribute("yzbmList", yzbmList);
		 // 取得使用性质
		 List syxzList =
		 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '3' order by dmz");
		 request.setAttribute("syxzList", syxzList);
		 // 取得所有权
		 List syqList =
		 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '5' order by dmz");
		 request.setAttribute("syqList", syqList);
		 // 取得获得方式
		 List hdfsList =
		 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '1' order by dmz");
		 request.setAttribute("hdfsList", hdfsList);
		 // 取得号牌种类
		 List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
		 hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");
		 request.setAttribute("hpzlList", hpzlList);
		 // 承检单位
		 List cjdwList =
		 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '57' order by dmz");
		 request.setAttribute("cjdwList", cjdwList);
		 // 取得车辆类型
		 List cllxList =
		 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '4' order by dmz");
		 request.setAttribute("cllxList", cllxList);
		 // 车身颜色
		 List csysList =
		 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '8' order by dmz");
		 request.setAttribute("csysList", csysList);
		 // 国产/进口
		 List gcjkList =
		 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '12' order by dmz");
		 request.setAttribute("gcjkList", gcjkList);
		 // 制造国
		 List zzgList =
		 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '31' order by dmz");
		 request.setAttribute("zzgList", zzgList);
		 // 燃料种类
		 List rlzlList =
		 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '9' order by dmz");
		 request.setAttribute("rlzlList", rlzlList);
		 // 转向形式
		 List zxxsList =
		 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '16' order by dmz");
		 request.setAttribute("zxxsList", zxxsList);
		//业务办理部门
		List list = this.slgDrvService.getDeptList("", "C34702A8FED97CBFE040007F0100339B", "");
		if(list == null){
			list = new ArrayList();
		}
		request.setAttribute("deptList", list);
		
		//获得证件信息
		request.setAttribute("editType", "查看");
		jdcslWpzService.getSlxqWpz(request);
		
		return "slxq";
	}
	
	
	
	// 受理查询页面
	@SuppressWarnings("unchecked")
	public String initSlCxList_wpz() throws Exception {
		try {
			logger.info("SlgVehWpzAction method initSlCxList_wpz....");
			//获取号牌种类（字典表）
			List<SlgSjzdWzp> hpzlList = new ArrayList<SlgSjzdWzp>();
			hpzlList = this.jdcslWpzService.getYwlxYwyy_wpz(request, "", "", "7", "", "");
			request.setAttribute("hpzlList", hpzlList);	
			//获取业务类型
			List<SlgSjzdWzp> ywlxList = new ArrayList<SlgSjzdWzp>();
			ywlxList = this.jdcslWpzService.getYwlxYwyy_wpz(request, "", "", "JDCYWSL", "VEH_YWLX", "");
			request.setAttribute("ywlxList", ywlxList);
			//获取业务原因
			List<SlgSjzdWzp> ywyyList = new ArrayList<SlgSjzdWzp>();
			ywyyList = this.jdcslWpzService.getYwlxYwyy_wpz(request, "", "", "JDCYWSL", "VEH_YWYY", "");
			request.setAttribute("ywyyList", ywyyList);
			//获取经办人部门信息
			List list = this.jdcslWpzService.getDeptList("", "C34702A8FED97CBFE040007F0100339B", "");
			request.setAttribute("deptList", list);
			//获取受理信息
			this.setSlCxList(this.jdcslWpzService.getSlCxListWpz(request, currentpage, "query"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "spcxlist";
	}
	
	
	/**
	 * 机动车受理导出
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getSlgVehExport() throws Exception{
		logger.info("SlgVehWpzAction method getSlgVehExport....");
		response.setContentType("text/html;charset=UTF-8"); 
		List<DbjgZjxxbWzp> zjxxList = this.jdcslWpzService.getSlCxListWpz(request, currentpage, "export");
		Object exportData = request.getAttribute("exportData");
		List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
		hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");
		request.setAttribute("hpzlList", hpzlList);
		
		List<SlgSjzd> ywlxList = new ArrayList<SlgSjzd>();
		ywlxList = this.slgDrvService.getYwlxYwyy(request, "", "", "JDCYWSL", "VEH_YWLX", "");
		request.setAttribute("ywlxList", ywlxList);
		
		List list = this.slgDrvService.getDeptList("", "C34702A8FED97CBFE040007F0100339B", "");
		request.setAttribute("deptList", list);
		if(exportData != null && !"".equals(exportData)){
			return "spcxlist";
		}
		eeb = new ExportExcelBean();
		if(zjxxList != null && zjxxList.size() > 0){
			
			Map<String, String> hpzlMap = new HashMap<String, String>();
			if (hpzlList != null && hpzlList.size() > 0) {
				for (int i = 0; i < hpzlList.size(); i++) {
					SlgSjzd sjzd = (SlgSjzd) hpzlList.get(i);
					hpzlMap.put(sjzd.getDmz(), sjzd.getDmms1());
				}
			}
			
			for(int i = 0; i < zjxxList.size(); i++){
				DbjgZjxxbWzp zjxx = zjxxList.get(i);
				String hpzlval = (hpzlMap.get(zjxx.getHpzl()) == null || "".equals(hpzlMap.get(zjxx.getHpzl())))?zjxx.getHpzl():hpzlMap.get(zjxx.getHpzl());
				zjxxList.get(i).setHpzl(hpzlval);
			}
			
			eeb.setFileName("机动车受理信息列表");
			eeb.setFileTitle("机动车受理信息列表");
			eeb.setList(zjxxList);
			parmsMap.put("lsh", "业务流水号");
			parmsMap.put("hphm", "号牌号码");
			parmsMap.put("hpzl", "号牌种类");
			parmsMap.put("ywlx", "业务类型");
			parmsMap.put("lrsj", "录入时间");
			parmsMap.put("dsrSfzCardno1", "当事人1_身份证号码");
			parmsMap.put("dsrSfzCardname1", "当事人1_身份证姓名");
			parmsMap.put("dsrSfzCardsex1", "当事人1_性别");
			parmsMap.put("dsrSfzCardaddress1", "当事人1_身份证上地址");
			parmsMap.put("dsrZzjgZh1", "当事人1_组织机构代码");
			parmsMap.put("dsrZzjgFrdb1", "当事人1_单位法人");
			parmsMap.put("dsrZzjgYyzz1", "当事人1_营业执照");
			parmsMap.put("dsrZzjgDwmc1", "当事人1_单位名称");
			parmsMap.put("dsrZzjgDz1", "当事人1_地址");
			parmsMap.put("dsrZzjgNjrq1", "当事人1_年检日期");
			
			parmsMap.put("dsrZzjgNjyxq1", "当事人1_年检有效期");
			
			parmsMap.put("dbrSfzCardno1", "代办人1_身份证号码");
			parmsMap.put("dbrSfzCardname1", "代办人1_身份证姓名");
			parmsMap.put("dbrSfzCardsex1", "代办人1_性别");
			parmsMap.put("dbrSfzCardaddress1", "代办人1_身份证上地址");
			parmsMap.put("dbrZzjgZh1", "代办人1_组织机构代码");
			parmsMap.put("dbrZzjgFrdb1", "代办人1_单位法人");
			parmsMap.put("dbrZzjgYyzz1", "代办人1_营业执照");
			parmsMap.put("dbrZzjgDwmc1", "代办人1_单位名称");
			parmsMap.put("dbrZzjgDz1", "代办人1_地址");
			parmsMap.put("dbrZzjgNjrq1", "代办人1_年检日期");
			parmsMap.put("dbrZzjgNjyxq1", "代办人1_年检有效期");
			
			parmsMap.put("dbrSfzCardno2", "代办人2_身份证号码");
			parmsMap.put("dbrSfzCardname2", "代办人2_身份证姓名");
			parmsMap.put("dbrSfzCardsex2", "代办人2_性别");
			parmsMap.put("dbrSfzCardaddress2", "代办人2_身份证上地址");
			parmsMap.put("dbrZzjgZh2", "代办人2_组织机构代码");
			parmsMap.put("dbrZzjgFrdb2", "代办人2_单位法人");
			parmsMap.put("dbrZzjgYyzz2", "代办人2_营业执照");
			parmsMap.put("dbrZzjgDwmc2", "代办人2_单位名称");
			parmsMap.put("dbrZzjgDz2", "代办人2_地址");
			parmsMap.put("dbrZzjgNjrq2", "代办人2_年检日期");
			parmsMap.put("dbrZzjgNjyxq2", "代办人2_年检有效期");
			
			parmsMap.put("dsrSfzmhm", "当事人身份证明号码");
			parmsMap.put("dsrXm", "当事人姓名");
			parmsMap.put("dbrSfzmhm", "代办人身份证明号码");
			parmsMap.put("dbrXm", "代办人身份证明姓名");
			
			eeb.setParmsMap(parmsMap);
			ExportTools.exportExcel(response, eeb);
		}else{
			request.setAttribute("exportData", "当前导出数据为空！");  
			return "spcxlist";
		}
		return NONE;
	}
	
	
	/**
	 * 添加机动车受理信息
	 */
	public String editSlgVehXxcj_wpz() throws Exception{
		logger.info("SlgVehWpzAction method editSlgVehXxcj_wpz....");
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		try {
			int retult = jdcslWpzService.insertJdcslWpz(request, dbZjxxb, ptdo, file1, file2);
			if(retult==1){
				String dsrreset = request.getParameter("dsrchkreset");
				String dbrreset = request.getParameter("dbrchkreset");
				String lsh = request.getParameter("lshval");
				String ismyself = dbZjxxb.getBllx();
				String alterinfo = this.slgVehService.getAlterinfo(request, dbZjxxb.getYwlx(), dbZjxxb.getYwyy(), lsh, dbZjxxb.getHphm(), dbZjxxb.getHpzl());
				out.println("alert('采集成功!"+alterinfo+"');");
				out.println("parent.clearform(1, "+dsrreset+", "+dbrreset+", "+ismyself+");");
				out.println("parent.closechuli();");
			}else{
				out.println("alert('采集失败!');");
				out.println("parent.closechuli();");
			}
		} catch (Exception e) {
			out.println("parent.closechuli();");
			e.printStackTrace();
			logger.error(e);
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			String estr = e.getMessage();
			estr = estr.replaceAll("\r", "");
			estr = estr.replaceAll("\n", "");
			estr = estr.replaceAll("\t", "");
			estr = estr.replaceAll("\f", "");
			estr = estr.replaceAll("\b", "");
			String exceptionstr = "异常信息:" + estr + "</br>文件名:"
					+ stackTraceElement.getFileName() + "</br>行数:"
					+ stackTraceElement.getLineNumber() + "</br>方法名:"
					+ stackTraceElement.getMethodName();
			out.println("parent.exception('" + exceptionstr + "');");
		}
		out.println("</script>");
		return null;
	}
	
	
	//初始化机动车受理页面
	@SuppressWarnings("unchecked")
	public String initJdcslPage_wpz() throws Exception{
		logger.info("SlgVehWpzAction method initEditPage()......");
		List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
		try {
			hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");

			 // 身份证明 对应表 PDASMYC_SJZD
			 List sfzmList =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '19' order by dmz");
			 request.setAttribute("sfzmList", sfzmList);
			
			 // 取得住所邮政编码
			 List yzbmList =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '50' order by dmz");
			 request.setAttribute("yzbmList", yzbmList);
			
			 // 取得使用性质
			 List syxzList =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '3' order by dmz");
			 request.setAttribute("syxzList", syxzList);
			
			 // 取得所有权
			 List syqList =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '5' order by dmz");
			 request.setAttribute("syqList", syqList);
			
			 // 取得获得方式
			 List hdfsList =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '1' order by dmz");
			 request.setAttribute("hdfsList", hdfsList);
			
			 // 取得号牌种类
			 List hpzlLists =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '7' order by dmz");
			 request.setAttribute("hpzlLists", hpzlLists);
			
			 // 承检单位
			 List cjdwList =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '57' order by dmz");
			 request.setAttribute("cjdwList", cjdwList);
			
			 // 取得车辆类型
			 List cllxList =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '4' order by dmz");
			 request.setAttribute("cllxList", cllxList);
			
			 // 车身颜色
			 List csysList =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '8' order by dmz");
			 request.setAttribute("csysList", csysList);
			
			 // 国产/进口
			 List gcjkList =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '12' order by dmz");
			 request.setAttribute("gcjkList", gcjkList);
			
			 // 制造国
			 List zzgList =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '31' order by dmz");
			 request.setAttribute("zzgList", zzgList);
			
			 // 燃料种类
			 List rlzlList =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '9' order by dmz");
			 request.setAttribute("rlzlList", rlzlList);
			
			 // 转向形式
			 List zxxsList =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '16' order by dmz");
			 request.setAttribute("zxxsList", zxxsList);
			 
			//业务办理部门
			List list = this.slgDrvService.getDeptList("", "C34702A8FED97CBFE040007F0100339B", "");
			if(list == null){
				list = new ArrayList();
			}
			request.setAttribute("deptList", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		request.setAttribute("hpzlList", hpzlList);
		request.setAttribute("editType", "新增");
		request.setAttribute("editTypeXx", "采集");
		return "initJdc_wpz";
	} 
	
	
	/**
	 * 根据号牌号码、种类获取机动车登记信息和技术参数
	 */
	public String getJdcInfo() throws Exception{
		logger.info("SlgVehWpzAction method getJdcInfo...");
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			
			//EsVehicle01 ptdo = jdcslWpzService.finjdcslInfo(request);
			//request.setAttribute("es", ptdo);
			esvehicle = jdcslWpzService.finjdcslInfo(request);
			
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
			logger.error(e);
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			String estr = e.getMessage();
			if(estr != null){
				estr = estr.replaceAll("\r", "");
				estr = estr.replaceAll("\n", "");
				estr = estr.replaceAll("\t", "");
				estr = estr.replaceAll("\f", "");
				estr = estr.replaceAll("\b", "");
				exceptionstr += estr + "</br>文件名:"
						+ stackTraceElement.getFileName() + "</br>行数:"
						+ stackTraceElement.getLineNumber() + "</br>方法名:"
						+ stackTraceElement.getMethodName();
			}else{
				exceptionstr += " 获取连接异常";
			}
		}
		return "jsonBean";
	}

	//加载业务类型
	public String getYwlxYwyy() throws Exception{
		List<SlgSjzdWzp> slgSjzds = new ArrayList<SlgSjzdWzp>();
		String dmz = request.getParameter("dmz");
		String dmms2 = request.getParameter("dmms2");
		String dmlb = request.getParameter("dmlb");
		String veh_drv = request.getParameter("veh_drv");
		String isflag = request.getParameter("flag");
		try {
			slgSjzds = this.jdcslWpzService.getYwlxYwyy_wpz(request, dmz, dmms2, dmlb, veh_drv, isflag);
			if(slgSjzds != null && slgSjzds.size() > 0){
				JSONArray array=new JSONArray(slgSjzds);
				response.setContentType("text/json;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				PrintWriter out=response.getWriter();
				out.println(array.toString());
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}




	public SlgVehService getSlgVehService() {
		return slgVehService;
	}
	public void setSlgVehService(SlgVehService slgVehService) {
		this.slgVehService = slgVehService;
	}
	public SlgSpxxService getSlgService() {
		return slgService;
	}
	public void setSlgService(SlgSpxxService slgService) {
		this.slgService = slgService;
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
	public SlgVehWpzService getJdcslWpzService() {
		return jdcslWpzService;
	}
	public void setJdcslWpzService(SlgVehWpzService jdcslWpzService) {
		this.jdcslWpzService = jdcslWpzService;
	}
	public EsVehicle getEsvehicle() {
		return esvehicle;
	}
	public void setEsvehicle(EsVehicle esvehicle) {
		this.esvehicle = esvehicle;
	}
	public DbjgZjxxbWzp getDbZjxxb() {
		return dbZjxxb;
	}
	public void setDbZjxxb(DbjgZjxxbWzp dbZjxxb) {
		this.dbZjxxb = dbZjxxb;
	}
	public DydjYwsbspb getDydjYwsbspb() {
		return dydjYwsbspb;
	}
	public void setDydjYwsbspb(DydjYwsbspb dydjYwsbspb) {
		this.dydjYwsbspb = dydjYwsbspb;
	}
	public VehicleTempMidTest getPtdo() {
		return ptdo;
	}
	public void setPtdo(VehicleTempMidTest ptdo) {
		this.ptdo = ptdo;
	}
	public VehicleTempMidIn getPtdoin() {
		return ptdoin;
	}
	public void setPtdoin(VehicleTempMidIn ptdoin) {
		this.ptdoin = ptdoin;
	}
	public File getFile1() {
		return file1;
	}
	public void setFile1(File file1) {
		this.file1 = file1;
	}
	public File getFile2() {
		return file2;
	}
	public void setFile2(File file2) {
		this.file2 = file2;
	}
	public String getFile1ContentType() {
		return file1ContentType;
	}
	public void setFile1ContentType(String file1ContentType) {
		this.file1ContentType = file1ContentType;
	}
	public String getFile2ContentType() {
		return file2ContentType;
	}
	public void setFile2ContentType(String file2ContentType) {
		this.file2ContentType = file2ContentType;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public List<DbjgZjxxbWzp> getSlCxList() {
		return SlCxList;
	}
	public void setSlCxList(List<DbjgZjxxbWzp> slCxList) {
		SlCxList = slCxList;
	}
	
	

	
	
	//=======================================================================
	
}
