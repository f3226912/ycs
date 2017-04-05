package com.ycszh.ssh.action.queryrelate;

import org.apache.log4j.Logger;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.service.queryrelate.QueryRealtedService;

/**
 * 
 * @author liudongyuan
 * 
 */
public class QueryRelatedAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private QueryRealtedService queryRealtedService;

	private static final Logger logger = Logger
			.getLogger(QueryRelatedAction.class);

	//在线驾驶证补证换证
	public String qrelate1(){
		try {
			queryRealtedService.queryResult1(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qrelate1";
	}
	//驾驶人联系方式变更
	public String qrelate2(){
		try {
			queryRealtedService.queryResult2(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qrelate2";
	}
	//机动车联系方式变更
	public String qrelate3(){
		try {
			queryRealtedService.queryResult3(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qrelate3";
	}
	//补换机动车行驶证
	public String qrelate4(){
		try {
			queryRealtedService.queryResult4(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qrelate4";
	}
	//补换检验合格标志
	public String qrelate5(){
		try {
			queryRealtedService.queryResult5(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qrelate5";
	}
	//机动车业务外网预约
	public String qrelate6(){
		try {
			queryRealtedService.queryResult6(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qrelate6";
	}
	
	public String showDetailInfoByLsh(){
		String result="";
		try {
			String showType=request.getParameter("showType")==null?"":request.getParameter("showType").trim();
			if(showType.equals("")){
				result= "error";
			}else{
				result=queryRealtedService.showDetailInfo(request);
			}
		} catch (Exception e) {
			result="error";
		}
		
		return result;
	}
	
	//导出到excel
	/*public String qrelate_exportToExcel(){
		try {
			queryRealtedService.queryResult(request);
			queryRealtedService.exportToExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}*/
	
	public QueryRealtedService getQueryRealtedService() {
		return queryRealtedService;
	}

	public void setQueryRealtedService(QueryRealtedService queryRealtedService) {
		this.queryRealtedService = queryRealtedService;
	}

	public static Logger getLogger() {
		return logger;
	}

}
