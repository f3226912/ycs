package com.ycszh.ssh.action.veh;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.drv.Yujing;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspb;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspbTemp;
import com.ycszh.ssh.hbm.veh.DbjgZjxxb;
import com.ycszh.ssh.hbm.veh.TemporaryLicense;
import com.ycszh.ssh.hbm.veh.VehPodbsp;
import com.ycszh.ssh.hbm.veh.VehicleTempMidTest;
import com.ycszh.ssh.hbm.yanche.PdasmycSjzd;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidIn;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidOut;
import com.ycszh.ssh.service.drv.SlgDrvService;
import com.ycszh.ssh.service.drv.SlgSpxxService;
import com.ycszh.ssh.service.dydj.IDydjService;
import com.ycszh.ssh.service.veh.SlgVehService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.ExportExcelBean;
import com.ycszh.util.ExportTools;
import com.ycszh.util.JsonUtil;
import com.ycszh.util.StringUtil;

@SuppressWarnings("serial")
public class SlgVehAction extends BaseAction {

	private SlgVehService slgVehService;
	private SlgSpxxService slgService;
	private SlgDrvService slgDrvService;
	private IDydjService dydjService;
	private static final Logger logger = Logger.getLogger(SlgVehAction.class);
	private DbjgZjxxb dbZjxxb;
	private DydjYwsbspb dydjYwsbspb;
	private VehicleTempMidTest ptdo;
	private VehicleTempMidIn ptdoin;
	private TemporaryLicense tpLicense;
	//private String dtpzRoot;
	
	private String result;
	private int currentpage = 1;
	private List<DbjgZjxxb> SlCxList = new ArrayList<DbjgZjxxb>();
	//导出实体
    private ExportExcelBean eeb;
    /**
     * 列表显示信息
     */
    private Map<String, Object> parmsMap = new LinkedHashMap<String, Object>();

	/**
	 * 上传文件实体对象
	 */
	private File file1;
	private File file2;
	private File file01;
	private File file02;

	/**
	 * 上传文件类型
	 */
	private String file1ContentType;
	private String file2ContentType;
	private String file01ContentType;
	private String file02ContentType;

	/**
	 * 上传文件名
	 */
	private String file1FileName;
	private String file2FileName;
	private String file01FileName;
	private String file02FileName;
	
	//新加上传实体对象
	private File gpyfile0;
	private File gpyfile1;
	private File gpyfile2;
	private File gpyfile3;
	private File gpyfile4;
	private File gpyfile5;
	private File gpyfile6;
	private File gpyfile7;
	//新加上传文件类型
	private String gpyfile0ContentType;
	private String gpyfile1ContentType;
	private String gpyfile2ContentType;
	private String gpyfile3ContentType;
	private String gpyfile4ContentType;
	private String gpyfile5ContentType;
	private String gpyfile6ContentType;
	private String gpyfile7ContentType;
	//新加上传文件名
	private String gpyfile0FileName;
	private String gpyfile1FileName;
	private String gpyfile2FileName;
	private String gpyfile3FileName;
	private String gpyfile4FileName;
	private String gpyfile5FileName;
	private String gpyfile6FileName;
	private String gpyfile7FileName;
	
	/**
	 * 初始化编辑机动车受理通用版页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initEditPage() throws Exception {
		logger.info("SlgVehAction method initEditPage()......");
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
		request.setAttribute("lsh", "0");
		return "insert";
	}
	
	/**
	 * 初始化编辑转出恢复业务和退办业务
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initJdcslPage() throws Exception {
		logger.info("SlgVehAction method initJdcslPage()......");
		List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
		try {
			hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");

			 // 身份证明 对应表 PDASMYC_SJZD
			 List sfzmList =
			 slgVehService.getRepositories(" from PdasmycSjzd where dmlb = '19' order by dmz");
			 request.setAttribute("sfzmList", sfzmList);
			 //取得指标类型
			 List zblxList = this.slgVehService.getClxgSzjdList(request);
			 request.setAttribute("zblxList", zblxList);
			 
			 String page = request.getParameter("page");
			 if("zchf".equals(page)){
				 request.setAttribute("title", "转出/注销恢复业务");
			 }else if("tbyw".equals(page)){
				 request.setAttribute("title", "退办业务");
			 }
			 request.setAttribute("pageforward", page);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		request.setAttribute("hpzlList", hpzlList);
		request.setAttribute("editType", "新增");
		request.setAttribute("editTypeXx", "采集");
		request.setAttribute("lsh", "0");
		return "edit_zchf";
	}

	public String selSlgVehXxcjList() {

		return "list";
	}

	public String editSlgVehXxcj() throws Exception {
		if (dbZjxxb != null) {
			String cjid = dbZjxxb.getId();
			response.setCharacterEncoding("GBK");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			try {
				//业务类型是临时号牌
				//如果业务类型是临时号牌,就保存车辆识别代码
				if("O".equals(dbZjxxb.getYwlx())){
					StringBuffer sb = new StringBuffer();
					String lshval = request.getParameter("lshval");
					if(null != lshval && !"".equals(lshval)){
						sb.append("流水号:"+lshval);
						sb.append(",");
						
						dbZjxxb.setLsh(lshval);
					}
					sb.append("车主姓名:"+dbZjxxb.getDsrxm());
					sb.append(",");
					sb.append("身份证号码:"+dbZjxxb.getDsrsfzmhm());
					sb.append(",");
					sb.append("居住地址:"+dbZjxxb.getDsrLxdz().trim());
					sb.append(",");
					sb.append("电话号码:"+dbZjxxb.getDsrLxdh());
					sb.append(",");
					sb.append("车辆识别代号:"+ptdo.getClsbdh());
					sb.append(",");
					sb.append("车辆型号:"+ptdo.getClxh());
					sb.append(",");
					sb.append("中文品牌:"+ptdo.getClpp1());
					sb.append(",");
					sb.append("发动机号:"+ptdo.getFdjh());
					sb.append(",");
					sb.append("载客人数:"+ptdo.getHdzk());
					dbZjxxb.setBz(sb.toString());
					
					dbZjxxb.setClsbdh(ptdo.getClsbdh());
				}else if("I".equals(dbZjxxb.getYwlx())){
					dbZjxxb.setClsbdh(ptdo.getClsbdh());
				}
				// 添加信息
				Integer resultIntger = this.slgVehService
						.insertOrUpdateSlgDrvXxcjb(dbZjxxb, dydjYwsbspb, request, file1,
								file2, file01, file02,gpyfile0,gpyfile1,gpyfile2,gpyfile3,gpyfile4,gpyfile5,gpyfile6,gpyfile7);
				out.println("parent.closechuli();");
				if (resultIntger > 0) {
					// 审批修改
					String dsrreset = request.getParameter("dsrchkreset");
					String dbrreset = request.getParameter("dbrchkreset");
					String lsh = request.getParameter("lshval");
					String sfzmmc = dbZjxxb.getDsrsfzmmc();
					String ismyself = dbZjxxb.getBllx();
					String cjsj = DateUtil.date2String(dbZjxxb.getLrsj(),
							"yyyy-MM-dd HH:mm:ss");
					String isduka = dbZjxxb.getDsrSfzCardno1();
					String isduzjjgz = dbZjxxb.getDsrZzjgZh1();
					if (sfzmmc != null
							&& ("A".equals(sfzmmc) || "M".equals(sfzmmc))
							&& StringUtil.isNull(isduka)) {
						String sfzmhm = dbZjxxb.getDsrsfzmhm();
						String xm = dbZjxxb.getDsrxm();
						this.slgService.updateSlgSpxx(request, sfzmhm, xm,
								"veh", "0", cjsj);
					}
					// 组织机构证审批修改
					// if(sfzmmc != null && "B".equals(sfzmmc) &&
					// StringUtil.isNull(isduzjjgz)){
					// String sfzmhm = dbZjxxb.getDsrsfzmhm();
					// String xm = dbZjxxb.getDsrxm();
					// this.slgService.updateSlgSpxx(request, sfzmhm, xm, "veh",
					// "0", cjsj);
					// }
					if ("2".equals(ismyself)) {
						String sfzmmcdbr = dbZjxxb.getDbrsfzmmc();
						String isdukadbr = dbZjxxb.getDbrSfzCardno1();
						String isduzjjgzdbr = dbZjxxb.getDbrZzjgZh1();
						if (sfzmmcdbr != null
								&& ("A".equals(sfzmmcdbr) || "M"
										.equals(sfzmmcdbr))
								&& StringUtil.isNull(isdukadbr)) {
							String sfzmhmdbr = dbZjxxb.getDbrsfzmhm();
							String xmdbr = dbZjxxb.getDbrxm();
							this.slgService.updateSlgSpxx(request, sfzmhmdbr,
									xmdbr, "veh", "1", cjsj);
						}
						// 组织机构证审批修改
						// if(sfzmmcdbr != null && "B".equals(sfzmmcdbr) &&
						// StringUtil.isNull(isduzjjgzdbr)){
						// String sfzmhmdbr = dbZjxxb.getDbrsfzmhm();
						// String xmdbr = dbZjxxb.getDbrxm();
						// this.slgService.updateSlgSpxx(request, sfzmhmdbr,
						// xmdbr, "veh", "1", cjsj);
						// }
					}

					if (null != cjid && !"".equals(cjid)) {
						out.println("alert('修改成功!');");
						out.println("parent.window.location.href = '"
								+ request.getContextPath()
								+ "/drv/initSlgDrvXxcjbList.action';");
					} else {
						// out.println("if(window.confirm('采集成功!是否要马上打印采集信息?')){window.open('print.action?slgDrvXxcjb.cjid="+slgDrvXxcjb.getCjid()+"');}");
						String alterinfo = this.slgVehService.getAlterinfo(request, dbZjxxb.getYwlx(), dbZjxxb.getYwyy(), lsh, dbZjxxb.getHphm(), dbZjxxb.getHpzl());
						out.println("alert('采集成功!"+alterinfo+"');");
						out.println("parent.clearform(1, "+dsrreset+", "+dbrreset+", "+ismyself+");");
						out.println("parent.closechuli();");
					}
				} else {
					out.println("alert('编辑失败!')");
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
		} else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}

	@SuppressWarnings("unchecked")
	public String getVehicleByLsh() throws Exception {
		VehicleTempMidIn ptdo = null;
		List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
		try {
			hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");
			// 登记信息和技术参数
			String lshval = request.getParameter("lsh");
			String type = request.getParameter("type");
			if(!StringUtil.isNull(type) && "in".equals(type)){
				ptdo = this.slgVehService.getVehicleMidInByLsh(lshval, request);
			}else if(!StringUtil.isNull(type) && "out".equals(type)){
				VehicleTempMidOut ptdoMidOut = this.slgVehService.getVehMidOutByLsh(lshval, request);
				ptdo = new VehicleTempMidIn();
				List propertyList = new ArrayList();
				propertyList.add("refeshFlag");
				propertyList.add("refeshMsg");
				propertyList.add("refeshDate");
				propertyList.add("dbyzh");
				propertyList.add("tyzxh");
				ptdo = (VehicleTempMidIn)getXclog(ptdo, ptdoMidOut, propertyList); //VehicleTempMidOut实体转VehicleTempMidIn表实体
			}
			// 身份证明 对应表 PDASMYC_SJZD
			List sfzmList = slgVehService
					.getRepositories(" from PdasmycSjzd where dmlb = '19' order by dmz");
			request.setAttribute("sfzmList", sfzmList);

			// 取得住所邮政编码
			List yzbmList = slgVehService
					.getRepositories(" from PdasmycSjzd where dmlb = '50' order by dmz");
			request.setAttribute("yzbmList", yzbmList);

			// 取得使用性质
			List syxzList = slgVehService
					.getRepositories(" from PdasmycSjzd where dmlb = '3' order by dmz");
			request.setAttribute("syxzList", syxzList);

			// 取得所有权
			List syqList = slgVehService
					.getRepositories(" from PdasmycSjzd where dmlb = '5' order by dmz");
			request.setAttribute("syqList", syqList);

			// 取得获得方式
			List hdfsList = slgVehService
					.getRepositories(" from PdasmycSjzd where dmlb = '1' order by dmz");
			request.setAttribute("hdfsList", hdfsList);

			// 取得号牌种类
			List hpzlLists = slgVehService
					.getRepositories(" from PdasmycSjzd where dmlb = '7' order by dmz");
			request.setAttribute("hpzlLists", hpzlLists);

			// 承检单位
			List cjdwList = slgVehService
					.getRepositories(" from PdasmycSjzd where dmlb = '57' order by dmz");
			request.setAttribute("cjdwList", cjdwList);

			// 取得车辆类型
			List cllxList = slgVehService
					.getRepositories(" from PdasmycSjzd where dmlb = '4' order by dmz");
			request.setAttribute("cllxList", cllxList);

			// 车身颜色
			List csysList = slgVehService
					.getRepositories(" from PdasmycSjzd where dmlb = '8' order by dmz");
			request.setAttribute("csysList", csysList);

			// 国产/进口
			List gcjkList = slgVehService
					.getRepositories(" from PdasmycSjzd where dmlb = '12' order by dmz");
			request.setAttribute("gcjkList", gcjkList);

			// 制造国
			List zzgList = slgVehService
					.getRepositories(" from PdasmycSjzd where dmlb = '31' order by dmz");
			request.setAttribute("zzgList", zzgList);

			// 燃料种类
			List rlzlList = slgVehService
					.getRepositories(" from PdasmycSjzd where dmlb = '9' order by dmz");
			request.setAttribute("rlzlList", rlzlList);

			// 转向形式
			List zxxsList = slgVehService
					.getRepositories(" from PdasmycSjzd where dmlb = '16' order by dmz");
			request.setAttribute("zxxsList", zxxsList);

			request.setAttribute("ptdo", ptdo);
			request.setAttribute("hpzlList", hpzlList);
			request.setAttribute("editType", "新增");
			request.setAttribute("editTypeXx", "采集");
			request.setAttribute("lsh", "1");
			request.setAttribute("lshval", lshval);
			request.setAttribute("type", type);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw e;
		}
		return "lshselect";
	}
	
	@SuppressWarnings("unchecked")
	public String getVehicleByLshAjax() throws Exception{
		// 登记信息和技术参数
		String lshval = request.getParameter("lsh");
		String type = request.getParameter("type");
		try {
			if(!StringUtil.isNull(type) && "in".equals(type)){
				ptdoin = this.slgVehService.getVehicleMidInByLsh(lshval, request);
			}else if(!StringUtil.isNull(type) && "out".equals(type)){
				VehicleTempMidOut ptdoMidOut = this.slgVehService.getVehMidOutByLsh(lshval, request);
				ptdoin = new VehicleTempMidIn();
				List propertyList = new ArrayList();
				propertyList.add("refeshFlag");
				propertyList.add("refeshMsg");
				propertyList.add("refeshDate");
				propertyList.add("dbyzh");
				propertyList.add("tyzxh");
				ptdoin = (VehicleTempMidIn)getXclog(ptdoin, ptdoMidOut, propertyList); //VehicleTempMidOut实体转VehicleTempMidIn表实体
			}
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
			result = exceptionstr;
			return "mustlsh";
		}
		return "vehtempinbylsh";
	}
	
	/**
	 * 根据流水号查询VehicleTempMidIn
	 * @return
	 * @throws Exception
	 */
	public String getVehicleTempMidInByLsh() throws Exception{
		String lsh = request.getParameter("lsh");
		try {
			if(!StringUtil.isNull(lsh)){
				ptdoin = this.slgVehService.getVehicleMidInByLsh(lsh, request);
			}else {
				ptdoin = null;
			}
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
			result = exceptionstr;
			return "mustlsh";
		}
		return "vehtempinbylsh";
	}
	
	/**
	 * 根据流水号调用临牌接口
	 * @return
	 * @throws Exception
	 */
	public String getTemporaryLicenseByLsh() throws Exception{
		String lsh = request.getParameter("lsh");
		try {
			if(!StringUtil.isNull(lsh)){
				tpLicense = this.slgVehService.getTemporaryLicenseByLsh(lsh, request);
			}else {
				tpLicense = null;
			}
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
			logger.error(e);
			StackTraceElement stackTraceElement = e.getStackTrace()[33];
			String estr = e.getMessage();
			if(estr != null){
				estr = estr.replaceAll("\r", "");
				estr = estr.replaceAll("\n", "");
				estr = estr.replaceAll("\t", "");
				estr = estr.replaceAll("\f", "");
				estr = estr.replaceAll("\b", "");
				//exceptionstr += estr + "</br>文件名:"
				exceptionstr += "调取临牌预约数据接口异常!" + "</br>文件名:"
						+ stackTraceElement.getFileName() + "</br>行数:"
						+ stackTraceElement.getLineNumber() + "</br>方法名:"
						+ stackTraceElement.getMethodName();
						//+ "getTemporaryLicenseByLsh";
			}else{
				exceptionstr += " 获取连接异常";
			}
			result = exceptionstr;
			return "mustlsh";
		}
		return "temporaryLicenseByLsh";
	}

	/**
	 * 判断是否需要根据流水号方式采集
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getYwlxMustLsh() throws Exception {
		String ywlx = request.getParameter("ywlx");
		if (ywlx != null && !"".equals(ywlx)) {
			List<SlgSjzd> sjzdList = this.slgDrvService.getYwlxYwyy(request, ywlx, "",
					"JDCYWSL", "VEH_YWLX", "");
			if (sjzdList != null && sjzdList.size() > 0) {
				SlgSjzd slgSjzd = sjzdList.get(0);
				result = slgSjzd.getBz();
			} else {
				result = "0";
			}
		}
		return "mustlsh";
	}

	// 受理查询页面
	@SuppressWarnings("unchecked")
	public String initSlCxList() throws Exception {
		logger.info("SlgVehAction method initSlCxList....");
		
		List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
		hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");
		request.setAttribute("hpzlList", hpzlList);
		
		List<SlgSjzd> ywlxList = new ArrayList<SlgSjzd>();
		ywlxList = this.slgDrvService.getYwlxYwyy(request, "", "", "JDCYWSL", "VEH_YWLX", "");
		request.setAttribute("ywlxList", ywlxList);
		
		List list = this.slgDrvService.getDeptList("", "C34702A8FED97CBFE040007F0100339B", "");
		request.setAttribute("deptList", list);
		
		//List<SlgSjzd> ywyyList = new ArrayList<SlgSjzd>();
		//ywyyList = this.slgDrvService.getYwlxYwyy("", "", "JDCYWSL", "VEH_YWYY");
		//request.setAttribute("ywyyList", ywyyList);
		
		this.setSlCxList(this.slgVehService.getSlCxList(request, currentpage, "query"));

		/*Map<String, String> sjzdMap = new HashMap<String, String>();
		if (ywyyList != null && ywyyList.size() > 0) {
			for (int i = 0; i < ywyyList.size(); i++) {
				SlgSjzd sjzd = (SlgSjzd) ywyyList.get(i);
				sjzdMap.put(sjzd.getDmz(), sjzd.getDmms1());
			}
		}
		if (this.getSlCxList() != null && this.getSlCxList().size() > 0) {
			for (DbjgZjxxb ss : this.getSlCxList()) {
				String ywyy = ss.getYwyy();
				if (ywyy != null && !ywyy.equals("")) {
					ywyy = ywyy.replaceAll(" ", "");
					ywyy = ywyy.replaceAll("'", "");
					ywyy = ywyy.replaceAll("\"", "");
					ywyy = ywyy.replaceAll("，", ",");
					if (ywyy.indexOf(",") > 1) {
						String[] ywyyNames = ywyy.split(",");
						String yw = "";
						for (int i = 0; i < ywyyNames.length; i++) {
							if (sjzdMap.get(ywyyNames[i])!=null&&!sjzdMap.get(ywyyNames[i]).equals("")){
								yw = yw + sjzdMap.get(ywyyNames[i]) + ";";
							}
						}
						ss.setYwyy(yw);
					} else {
						String yy = sjzdMap.get(ywyy);
						ss.setYwyy(yy);
					}
				}
			}
		}*/
		return "spcxlist";
	}
	
	/**
	 * 机动车受理导出
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getSlgVehExport() throws Exception{
		response.setContentType("text/html;charset=UTF-8"); 
		List<DbjgZjxxb> zjxxList = this.slgVehService.getSlCxList(request, currentpage, "export");
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
				DbjgZjxxb zjxx = zjxxList.get(i);
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
			
			parmsMap.put("dsrsfzmhm", "当事人身份证明号码");
			parmsMap.put("dsrxm", "当事人姓名");
			parmsMap.put("dbrsfzmhm", "代办人身份证明号码");
			parmsMap.put("dbrxm", "代办人身份证明姓名");
			
			eeb.setParmsMap(parmsMap);
			ExportTools.exportExcel(response, eeb);
		}else{
			request.setAttribute("exportData", "当前导出数据为空！");  
			return "spcxlist";
		}
		return NONE;
	}

	// 受理查询详细页面
	@SuppressWarnings("unchecked")
	public String initSlCx() throws Exception {
		logger.info("SlgVehAction method initSlCx....");
		String dnjgid=request.getParameter("id");
		VehicleTempMidIn midIn=null;
		DydjYwsbspbTemp dydjYwsbspb = new DydjYwsbspbTemp();

		// 身份证明 对应表 PDASMYC_SJZD
		List sfzmList = slgVehService
				.getRepositories(" from PdasmycSjzd where dmlb = '19' order by dmz");
		request.setAttribute("sfzmList", sfzmList);

		// 取得住所邮政编码
		List yzbmList = slgVehService
				.getRepositories(" from PdasmycSjzd where dmlb = '50' order by dmz");
		request.setAttribute("yzbmList", yzbmList);

		// 取得使用性质
		List syxzList = slgVehService
				.getRepositories(" from PdasmycSjzd where dmlb = '3' order by dmz");
		request.setAttribute("syxzList", syxzList);

		// 取得所有权
		List syqList = slgVehService
				.getRepositories(" from PdasmycSjzd where dmlb = '5' order by dmz");
		request.setAttribute("syqList", syqList);

		// 取得获得方式
		List hdfsList = slgVehService
				.getRepositories(" from PdasmycSjzd where dmlb = '1' order by dmz");
		request.setAttribute("hdfsList", hdfsList);

		// 取得号牌种类
		List hpzlLists = slgVehService
				.getRepositories(" from PdasmycSjzd where dmlb = '7' order by dmz");
		request.setAttribute("hpzlLists", hpzlLists);

		// 承检单位
		List cjdwList = slgVehService
				.getRepositories(" from PdasmycSjzd where dmlb = '57' order by dmz");
		request.setAttribute("cjdwList", cjdwList);

		// 取得车辆类型
		List cllxList = slgVehService
				.getRepositories(" from PdasmycSjzd where dmlb = '4' order by dmz");
		request.setAttribute("cllxList", cllxList);

		// 车身颜色
		List csysList = slgVehService
				.getRepositories(" from PdasmycSjzd where dmlb = '8' order by dmz");
		request.setAttribute("csysList", csysList);

		// 国产/进口
		List gcjkList = slgVehService
				.getRepositories(" from PdasmycSjzd where dmlb = '12' order by dmz");
		request.setAttribute("gcjkList", gcjkList);

		// 制造国
		List zzgList = slgVehService
				.getRepositories(" from PdasmycSjzd where dmlb = '31' order by dmz");
		request.setAttribute("zzgList", zzgList);

		// 燃料种类
		List rlzlList = slgVehService
				.getRepositories(" from PdasmycSjzd where dmlb = '9' order by dmz");
		request.setAttribute("rlzlList", rlzlList);

		// 转向形式
		List zxxsList = slgVehService
				.getRepositories(" from PdasmycSjzd where dmlb = '16' order by dmz");
		request.setAttribute("zxxsList", zxxsList);
		
		//号牌种类
		List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
		hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "" , "");
		request.setAttribute("hpzlList", hpzlList);
		
		//业务类型
		List<SlgSjzd> ywlxList = new ArrayList<SlgSjzd>();
		ywlxList = this.slgDrvService.getYwlxYwyy(request, "", "", "JDCYWSL", "VEH_YWLX", "");
		request.setAttribute("ywlxList", ywlxList);
		
		//业务办理部门
		List list = this.slgDrvService.getDeptList("", "C34702A8FED97CBFE040007F0100339B", "");
		if(list == null){
			list = new ArrayList();
		}
		request.setAttribute("deptList", list);
		
		if (dnjgid != null || !"".equals(dnjgid)) {
			this.setDbZjxxb(slgVehService.getDbjgZjxxb(request,dnjgid));
			//查询抵押登记信息
			String lsh = dbZjxxb.getLsh();
			if(!StringUtil.isNull(lsh)){
				String first = lsh.substring(0, 1);
				if("D".equals(first)){
					dydjYwsbspb = dydjService.getDydjYwwbspByLsh(request, lsh);
				}
			}
			
//			//数据字典翻译
//			Map<String, String> sjzdMap = new HashMap<String, String>();
//			if (ywlxList != null && ywlxList.size() > 0) {
//				for (int i = 0; i < ywlxList.size(); i++) {
//					SlgSjzd sjzd = (SlgSjzd) ywlxList.get(i);
//					sjzdMap.put(sjzd.getDmz(), sjzd.getDmms1());
//				}
//			}
//			if (this.getDbZjxxb() != null && !this.getDbZjxxb().equals("")) {
//					String ywlx = this.getDbZjxxb().getYwlx();
//					if (ywlx != null && !ywlx.equals("")) {
//						ywlx = ywlx.replaceAll(" ", "");
//						ywlx = ywlx.replaceAll("'", "");
//						ywlx = ywlx.replaceAll("\"", "");
//						ywlx = ywlx.replaceAll("，", ",");
//						String yy = sjzdMap.get(ywlx);
//						this.getDbZjxxb().setYwlx(yy);
//					}
//			}
			//查询所有业务类型
			Map<String, String> ywlxMap = new HashMap<String, String>();
			if (ywlxList != null && ywlxList.size() > 0) {
				for (int i = 0; i < ywlxList.size(); i++) {
					SlgSjzd sjzd = (SlgSjzd) ywlxList.get(i);
					ywlxMap.put(sjzd.getDmz(), sjzd.getDmms1());
				}
			}
			
			//查询所有业务原因
			List<SlgSjzd> ywyyList = new ArrayList<SlgSjzd>();
			ywyyList = this.slgDrvService.getYwlxYwyy(request, "", "", "JDCYWSL", "VEH_YWYY" , "");
			Map<String, String> ywyyMap = new HashMap<String, String>();
			if (ywyyList != null && ywyyList.size() > 0) {
				for (int i = 0; i < ywyyList.size(); i++) {
					SlgSjzd sjzd = (SlgSjzd) ywyyList.get(i);
					ywyyMap.put(sjzd.getDmz(), sjzd.getDmms1());
				}
			}
			
			DbjgZjxxb zjxx = this.getDbZjxxb();
			String ywlxval = "";
			String[] ywlxarr = null;
			String[] ywyyarr = null;
			if(!StringUtil.isNull(zjxx.getYwlx()) && !StringUtil.isNull(zjxx.getYwyy())){
				if("A".equals(zjxx.getYwlx()) && "A:A".equals(zjxx.getYwyy())){
					this.getDbZjxxb().setYwlx("注册登记");
				}
				if("B".equals(zjxx.getYwlx()) && "B:B".equals(zjxx.getYwyy())){
					this.getDbZjxxb().setYwlx("转移登记(市内过户)");
				}
				if("B".equals(zjxx.getYwlx()) && "B:C".equals(zjxx.getYwyy())){
					this.getDbZjxxb().setYwlx("转移登记(市外过户)");
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
			this.getDbZjxxb().setYwlx(ywlxval);
			
			if (this.getDbZjxxb() != null) {
				if(this.getDbZjxxb().getLsh()!=null&&!this.getDbZjxxb().getLsh().equals("")){
					midIn = this.slgVehService.getVehicleMidInByLsh(this.getDbZjxxb().getLsh(), request);
					
					if(midIn == null){
						midIn = new VehicleTempMidIn();
					}
                    request.setAttribute("ptdo", midIn);
				}
				//测试下czj	
				if(midIn == null){
					VehicleTempMidIn midIn1 = new VehicleTempMidIn();
					midIn1.setClsbdh(dbZjxxb.getClsbdh() );
					request.setAttribute("ptdo", midIn1);
				}
					
				String cznr = request.getParameter("cznr");
				if("sh".equals(cznr)){
					request.setAttribute("editType2", "审核");
				}
				request.setAttribute("editType", "查看");
				request.setAttribute("editTypeXx", "详细");
				request.setAttribute("lshval", this.getDbZjxxb().getLsh());
				request.setAttribute("dydjYwsbspb", dydjYwsbspb);
				return "view";
			} else {
				request.setAttribute("errorTip", "没有该受理信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该受理信息!");
			return "Exception";
		}
	}
	
	/**
	 * 查询是否有备案记录
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String selDbjgBdy() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String sfzmhm = request.getParameter("sfzmhm");
			if(!StringUtil.isNull(sfzmhm)){
				List list = this.slgVehService.getBajl(request, sfzmhm);
				if(list != null && list.size() > 0){
					out.println("0");
					out.flush();
					out.close();
				}else{
					out.println("1");
					out.flush();
					out.close();
				}
			}else{
				out.println("1");
				out.flush();
				out.close();
			}
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 查询配偶审批
	 */
	public String getPoDbspList() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String czsfzhm = request.getParameter("czsfzhm");
			String dbsfzhm = request.getParameter("dbsfzhm");
			String hphm = request.getParameter("hphm");
			String hpzl = request.getParameter("hpzl");
			List<VehPodbsp> list = this.slgVehService.getPoDbspList(request, czsfzhm, dbsfzhm, hphm, hpzl);
			if(list != null && list.size() > 0){
				out.println("0");
				out.flush();
				out.close();
			}else{
				out.println("1");
				out.flush();
				out.close();
			}
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 机动车受理黑名单
	 * @return
	 * @throws Exception
	 */
	public String getIsBlack() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String result = this.slgVehService.getIsBlack(request, "", "", "", "", "", "");
			out.println(result);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 机动车受理黑名单 函数方式
	 * @return
	 * @throws Exception
	 */
	public String getIsBlackByFun() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String result = this.slgVehService.getIsBlackByFun(request, "", "", "", "", "", "");
			out.println(result);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 验证是否预约
	 * @return
	 * @throws Exception
	 */
	public String getIsYuyue() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String result = this.slgVehService.getIsYuyue(request, "");
			out.println(result);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/***
	 * 验车
	 * @return
	 * @throws Exception
	 */
	public String getIsyanche() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String result = this.slgVehService.getIsyanche(request);
			out.println(result);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/***
	 * 验证是否需要指标
	 * @return
	 * @throws Exception
	 */
	public String getIsneedzb() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String result = this.slgVehService.getIsneedzb(request);
			out.println(result);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/***
	 * 验证指标函数
	 * @return
	 * @throws Exception
	 */
	public String getIsyanzzb() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String result = this.slgVehService.getIsyanzzb(request);
			out.println(result);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 读取车辆限购数据字典——指标分类
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getClxgsjzdList() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			List list = this.slgVehService.getClxgSzjdList(request);
			String jsonString = JsonUtil.list2json(list);
			out.println(jsonString);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 读取车辆类型
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getCllxList() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			List list = this.slgVehService.getCllxList("4", "");
			String jsonString = JsonUtil.list2json(list);
			out.println(jsonString);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 新加查询车辆类型（'K3%' OR 'K4%'条件）
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getZrCllxList() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			List list = this.slgVehService.getZrCllxList();
			String jsonString = JsonUtil.list2json(list);
			out.println(jsonString);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 读取统一版流水号信息
	 * @return
	 * @throws Exception
	 */
	public String getTyblshinfo() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			Object[] objs = this.slgVehService.getTyblshinfo(request);
			String jsonString = JsonUtil.array2json(objs);
			out.println(jsonString);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 生成证件信息表流水号
	 * @return
	 * @throws Exception
	 */
	public String getZjxxblsh() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String lsh = this.slgVehService.getZjxxblsh();
			String jsonString = JsonUtil.string2json(lsh);
			out.println(jsonString);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 根据流水号查询流水信息
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getEsvehflow() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			List list = this.slgVehService.getEsvehflow(request);
			if(list != null && list.size() > 0 ){
				Object[] obj = (Object[])list.get(0);
				String jsonString = JsonUtil.array2json(obj);
				out.println(jsonString);
			}else{
				out.println("-1");
			}
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 根据号牌号码和号牌种类查询档案信息
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getEsvehicle() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			List list = this.slgVehService.getEsVehicle(request);
			if(list != null && list.size() > 0 ){
				Object[] objs = (Object[])list.get(0);
				String jsonString = JsonUtil.array2json(objs);
				out.println(jsonString);
			}else{
				out.println("-1");
			}
			
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 获取车辆使用性质
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getClsyxzList() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String xtlb = request.getParameter("xtlb");
			String dmlb = request.getParameter("dmlb");
			List list = this.slgVehService.getClsyxzList(request, xtlb, dmlb);
			String jsonString = JsonUtil.list2json(list);
			out.println(jsonString);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 获取指标号
	 * @return
	 * @throws Exception
	 */
	public String getZblist() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			//String jsonString = "{\"total\":239,\"rows\":[ {\"zbh\":\"001\",\"gzh\":\"A001\"},{\"zbh\":\"002\",\"gzh\":\"A002\"}] }";
			String jsonString = this.slgVehService.getZblist(request);
			out.println(jsonString);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 获取车辆限购类型
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getClxglxList() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			List list = this.slgVehService.getXglxList(request);
			String jsonString = JsonUtil.list2json(list);
			out.println(jsonString);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 获取车辆限购数据字典
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getClxgsjzd2List() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			List list = this.slgVehService.getClxgsjzd2List(request);
			String jsonString = JsonUtil.list2json(list);
			out.println(jsonString);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 查询机动车受理信息
	 * @return
	 * @throws Exception
	 */
	public String getDbjgzjxxInfo() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			DbjgZjxxb zjxx = this.slgVehService.getDbjgzjxxByCondition(request);
			if(zjxx != null){
				String jsonString = JsonUtil.bean2json(zjxx);
				out.println(jsonString);
			}else{
				out.println("-1");
			}
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 判断是否是本部门
	 * @return
	 * @throws Exception
	 */
	public String getIsSamedept() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String result = this.slgVehService.getIsSamedept(request);
			String jsonString = JsonUtil.string2json(result);
			out.println(jsonString);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 业务退办list
	 * @return
	 * @throws Exception
	 */
	public String getZjxxbList() throws Exception{
		List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
		hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");
		request.setAttribute("hpzlList", hpzlList);
		List<DbjgZjxxb> zjxxList = this.slgVehService.getZjxxbList(request);
		request.setAttribute("zjxxList", zjxxList);
		return "zbxxList";
	}
	
	/**
	 * 退办业务
	 * @return
	 * @throws Exception
	 */
	public String tbZbinfo() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String result = this.slgVehService.tbZbinfo(request);
			String jsonString = JsonUtil.string2json(result);
			out.println(jsonString);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 退办验证
	 * @param request
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public String getTbyanz() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String result = this.slgVehService.getTbyanz(request);
			String jsonString = JsonUtil.string2json(result);
			out.println(jsonString);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Object getXclog(Object obj1, Object obj2, List list) throws Exception{
		if(obj1 == null){
			return null;
		}
		if(obj2 == null){
			return null;
		}
		try {
			Field[] fields = obj2.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				if(list != null){
					if(list.contains(name)){
						continue;
					}
				}
				Field field = obj1.getClass().getDeclaredField(name);
				field.set(obj1, fields[i].get(obj2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		return obj1;
	}
	
	/**
	 * 机动车业务审核验证
	 * @return
	 * @throws Exception
	 */
	public String vehShenheCheck() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String shlsh = request.getParameter("shlsh");
			String hphm = request.getParameter("hphm");
			String hpzl = request.getParameter("hpzl");
			Integer result = this.slgVehService.vehShenheCheck(request, shlsh, hphm, hpzl);
			out.println(result);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 机动车业务审核
	 * @return
	 * @throws Exception
	 */
	public String vehShenhe() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String shlsh = request.getParameter("shlsh");
			String shjg = request.getParameter("shjg");
			String cjid = request.getParameter("cjid");
			String hphm = request.getParameter("hphm");
			String hpzl = request.getParameter("hpzl");
			Integer result = slgVehService.vehShenhe(request, shlsh, shjg,cjid,hphm,hpzl);
			out.println(result);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 根据业务类型获取态图拍摄区域的值
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void getDtpzByYwlx() throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			List list = this.slgVehService.getDtpzByYwlx(request);
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for(int i=0;i<list.size();i++) {
			       Object[] o = (Object[]) list.get(i);
			       sb.append("{");
			       sb.append("\"" + "id" + "\"" + ":" + "\"" + o[0] + "\"" + ",");
			       sb.append("\"" + "ywlxid" + "\"" + ":" + "\"" + o[1] + "\"" + ",");
			       sb.append("\"" + "ywlxms" + "\"" + ":" + "\"" + o[2] + "\"" + ",");
			       sb.append("\"" + "title" + "\"" + ":" + "\"" + o[3] + "\"" + ",");
			       sb.append("\"" + "url" + "\"" + ":" + "\"" + o[4] + "\"");
			       sb.append("}");
			       if(i!=list.size()-1){
			    	    sb.append(",");
			       }
			 }
			sb.append("]");
			//JsonUtil
			out.println(sb.toString());
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
	}
	
	public List<DbjgZjxxb> getSlCxList() {
		return SlCxList;
	}

	public void setSlCxList(List<DbjgZjxxb> slCxList) {
		SlCxList = slCxList;
	}

	public static Logger getLogger() {
		return logger;
	}

	public SlgVehService getSlgVehService() {
		return slgVehService;
	}

	public void setSlgVehService(SlgVehService slgVehService) {
		this.slgVehService = slgVehService;
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

	public File getFile01() {
		return file01;
	}

	public void setFile01(File file01) {
		this.file01 = file01;
	}

	public File getFile02() {
		return file02;
	}

	public void setFile02(File file02) {
		this.file02 = file02;
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

	public String getFile01ContentType() {
		return file01ContentType;
	}

	public void setFile01ContentType(String file01ContentType) {
		this.file01ContentType = file01ContentType;
	}

	public String getFile02ContentType() {
		return file02ContentType;
	}

	public void setFile02ContentType(String file02ContentType) {
		this.file02ContentType = file02ContentType;
	}

	public String getFile1FileName() {
		return file1FileName;
	}

	public void setFile1FileName(String file1FileName) {
		this.file1FileName = file1FileName;
	}

	public String getFile2FileName() {
		return file2FileName;
	}

	public void setFile2FileName(String file2FileName) {
		this.file2FileName = file2FileName;
	}

	public String getFile01FileName() {
		return file01FileName;
	}

	public void setFile01FileName(String file01FileName) {
		this.file01FileName = file01FileName;
	}

	public String getFile02FileName() {
		return file02FileName;
	}

	public void setFile02FileName(String file02FileName) {
		this.file02FileName = file02FileName;
	}

	public DbjgZjxxb getDbZjxxb() {
		return dbZjxxb;
	}

	public void setDbZjxxb(DbjgZjxxb dbZjxxb) {
		this.dbZjxxb = dbZjxxb;
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

	public VehicleTempMidTest getPtdo() {
		return ptdo;
	}

	public void setPtdo(VehicleTempMidTest ptdo) {
		this.ptdo = ptdo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public VehicleTempMidIn getPtdoin() {
		return ptdoin;
	}

	public void setPtdoin(VehicleTempMidIn ptdoin) {
		this.ptdoin = ptdoin;
	}
	
	public TemporaryLicense getTpLicense() {
		return tpLicense;
	}

	public void setTpLicense(TemporaryLicense tpLicense) {
		this.tpLicense = tpLicense;
	}

	public DydjYwsbspb getDydjYwsbspb() {
		return dydjYwsbspb;
	}

	public void setDydjYwsbspb(DydjYwsbspb dydjYwsbspb) {
		this.dydjYwsbspb = dydjYwsbspb;
	}

	public IDydjService getDydjService() {
		return dydjService;
	}

	public void setDydjService(IDydjService dydjService) {
		this.dydjService = dydjService;
	}

	public ExportExcelBean getEeb() {
		return eeb;
	}

	public void setEeb(ExportExcelBean eeb) {
		this.eeb = eeb;
	}

	public Map<String, Object> getParmsMap() {
		return parmsMap;
	}

	public void setParmsMap(Map<String, Object> parmsMap) {
		this.parmsMap = parmsMap;
	}
	
	public File getGpyfile0() {
		return gpyfile0;
	}

	public void setGpyfile0(File gpyfile0) {
		this.gpyfile0 = gpyfile0;
	}

	public File getGpyfile1() {
		return gpyfile1;
	}

	public void setGpyfile1(File gpyfile1) {
		this.gpyfile1 = gpyfile1;
	}

	public File getGpyfile2() {
		return gpyfile2;
	}

	public void setGpyfile2(File gpyfile2) {
		this.gpyfile2 = gpyfile2;
	}

	public File getGpyfile3() {
		return gpyfile3;
	}

	public void setGpyfile3(File gpyfile3) {
		this.gpyfile3 = gpyfile3;
	}

	public File getGpyfile4() {
		return gpyfile4;
	}

	public void setGpyfile4(File gpyfile4) {
		this.gpyfile4 = gpyfile4;
	}

	public File getGpyfile5() {
		return gpyfile5;
	}

	public void setGpyfile5(File gpyfile5) {
		this.gpyfile5 = gpyfile5;
	}

	public File getGpyfile6() {
		return gpyfile6;
	}

	public void setGpyfile6(File gpyfile6) {
		this.gpyfile6 = gpyfile6;
	}

	public File getGpyfile7() {
		return gpyfile7;
	}

	public void setGpyfile7(File gpyfile7) {
		this.gpyfile7 = gpyfile7;
	}

	public String getGpyfile0ContentType() {
		return gpyfile0ContentType;
	}

	public void setGpyfile0ContentType(String gpyfile0ContentType) {
		this.gpyfile0ContentType = gpyfile0ContentType;
	}

	public String getGpyfile1ContentType() {
		return gpyfile1ContentType;
	}

	public void setGpyfile1ContentType(String gpyfile1ContentType) {
		this.gpyfile1ContentType = gpyfile1ContentType;
	}

	public String getGpyfile2ContentType() {
		return gpyfile2ContentType;
	}

	public void setGpyfile2ContentType(String gpyfile2ContentType) {
		this.gpyfile2ContentType = gpyfile2ContentType;
	}

	public String getGpyfile3ContentType() {
		return gpyfile3ContentType;
	}

	public void setGpyfile3ContentType(String gpyfile3ContentType) {
		this.gpyfile3ContentType = gpyfile3ContentType;
	}

	public String getGpyfile4ContentType() {
		return gpyfile4ContentType;
	}

	public void setGpyfile4ContentType(String gpyfile4ContentType) {
		this.gpyfile4ContentType = gpyfile4ContentType;
	}

	public String getGpyfile5ContentType() {
		return gpyfile5ContentType;
	}

	public void setGpyfile5ContentType(String gpyfile5ContentType) {
		this.gpyfile5ContentType = gpyfile5ContentType;
	}

	public String getGpyfile6ContentType() {
		return gpyfile6ContentType;
	}

	public void setGpyfile6ContentType(String gpyfile6ContentType) {
		this.gpyfile6ContentType = gpyfile6ContentType;
	}

	public String getGpyfile7ContentType() {
		return gpyfile7ContentType;
	}

	public void setGpyfile7ContentType(String gpyfile7ContentType) {
		this.gpyfile7ContentType = gpyfile7ContentType;
	}

	public String getGpyfile0FileName() {
		return gpyfile0FileName;
	}

	public void setGpyfile0FileName(String gpyfile0FileName) {
		this.gpyfile0FileName = gpyfile0FileName;
	}

	public String getGpyfile1FileName() {
		return gpyfile1FileName;
	}

	public void setGpyfile1FileName(String gpyfile1FileName) {
		this.gpyfile1FileName = gpyfile1FileName;
	}

	public String getGpyfile2FileName() {
		return gpyfile2FileName;
	}

	public void setGpyfile2FileName(String gpyfile2FileName) {
		this.gpyfile2FileName = gpyfile2FileName;
	}

	public String getGpyfile3FileName() {
		return gpyfile3FileName;
	}

	public void setGpyfile3FileName(String gpyfile3FileName) {
		this.gpyfile3FileName = gpyfile3FileName;
	}

	public String getGpyfile4FileName() {
		return gpyfile4FileName;
	}

	public void setGpyfile4FileName(String gpyfile4FileName) {
		this.gpyfile4FileName = gpyfile4FileName;
	}

	public String getGpyfile5FileName() {
		return gpyfile5FileName;
	}

	public void setGpyfile5FileName(String gpyfile5FileName) {
		this.gpyfile5FileName = gpyfile5FileName;
	}

	public String getGpyfile6FileName() {
		return gpyfile6FileName;
	}

	public void setGpyfile6FileName(String gpyfile6FileName) {
		this.gpyfile6FileName = gpyfile6FileName;
	}

	public String getGpyfile7FileName() {
		return gpyfile7FileName;
	}

	public void setGpyfile7FileName(String gpyfile7FileName) {
		this.gpyfile7FileName = gpyfile7FileName;
	}
	
}
