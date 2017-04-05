package com.ycszh.util;

import javax.resource.spi.IllegalStateException;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

public class Client4GuoShui
{
	
	public static final String WS_URL = "http://10.42.0.116:7001/front/services/QueryServices";
	public static final String NAME_SPACE = "http://webservice.tech.xinyi.com";
	
	/**
	 * 调用接口 增量同步
	 * @throws Exception 
	 */
	public String webserviceInvoke4Sync(String method,String start,String end) throws Exception{
		//接口请求参数
		String param = "<ns1:process xmlns:ns1=\"BusinessProxyService\"><bizId xsi:type=\"xsd:string\">"+method+"</bizId><businessXML xsi:type=\"xsd:string\">&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;&lt;condition&gt;&lt;item&gt;&lt;name&gt;cjsj_q&lt;/name&gt;&lt;caption&gt;&#x91C7;&#x96C6;&#x65F6;&#x95F4;&#x8D77;&lt;/caption&gt;&lt;value&gt;"+start+"&lt;/value&gt;&lt;/item&gt;&lt;item&gt;&lt;name&gt;cjsj_z&lt;/name&gt;&lt;caption&gt;&#x91C7;&#x96C6;&#x65F6;&#x95F4;&#x6B62;&lt;/caption&gt;&lt;value&gt;"+end+"&lt;/value&gt;&lt;/item&gt;&lt;/condition&gt;</businessXML></ns1:process>";
		String fwbh = "NFW630013515";//服务编号
		//信义部门间交换平台服务认证参数
		String username = "GS";
		String password= "GS_ZY";
		String soapAction = "";
		//国税接口认证参数
		String soapAuth = "Basic cm9vdDowMDAwMDA=";
		String attach = "";
		return query(fwbh, username, password, attach, param, soapAction, soapAuth);
	}
	/**
	 * * 调用接口 实时查询
	 * @param method 请求方法
	 * @param code  发票代码
	 * @param number  发票编号
	 * @return
	 * @throws Exception
	 */
	public String webserviceInvoke4Query(String method,String code,String number) throws Exception{
		//接口请求参数
		String param = "<ns1:process xmlns:ns1=\"BusinessProxyService\"><bizId xsi:type=\"xsd:string\">"+method+"</bizId><businessXML xsi:type=\"xsd:string\">&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;&lt;condition&gt;&lt;item&gt;&lt;name&gt;fpdm&lt;/name&gt;&lt;caption&gt;&#x91C7;&#x96C6;&#x65F6;&#x95F4;&#x8D77;&lt;/caption&gt;&lt;value&gt;"+code+"&lt;/value&gt;&lt;/item&gt;&lt;item&gt;&lt;name&gt;fphm&lt;/name&gt;&lt;caption&gt;&#x91C7;&#x96C6;&#x65F6;&#x95F4;&#x6B62;&lt;/caption&gt;&lt;value&gt;"+number+"&lt;/value&gt;&lt;/item&gt;&lt;/condition&gt;</businessXML></ns1:process>";
		String fwbh = "NFW630013515";//服务编号
		//信义部门间交换平台服务认证参数
		String username = "GS";
		String password= "GS_ZY";
		String soapAction = "";
		//国税接口认证参数
		String soapAuth = "Basic cm9vdDowMDAwMDA=";
		String attach = "";
		return query(fwbh, username, password, attach, param, soapAction, soapAuth);
	}
	/**
	 * 数据文件包解析
	 */
	public void dataFileParse(){
		
	}
    /**
     * 数据入库
     */
    public void data2Db(){
    	
    }
	/**
	 * 
	 * @param fwbh 服务编号
	 * @param username 用户名
	 * @param password 密码
	 * @param attach <ATTACH><YHXM>使用该接口的用户姓名</YHXM><YHZH>使用该接口的系统账号</YHZH></ATTACH>
	 * @param param XML 格式的字符串，<SOAP-QUERY>参见系统</SOAP-QUERY>
	 * @param soapAction 如服务未作说明，可以为空
	 * @param soapAuth 如服务未作说明，可以为空
	 * @return
	 * @throws Exception
	 */
	public static String query(String fwbh, String username, String password, String attach, String param, String soapAction, String soapAuth) throws Exception
	{

		if ( fwbh == null || fwbh.trim().length() < 1 )
		{
			throw new IllegalArgumentException("[FWBH] IS NOT BE EMPTY !");
		}
		if ( param == null || param.trim().length() < 1 )
		{
			throw new IllegalArgumentException("[PARAM] IS NOT BE EMPTY !");
		}
		
		return excute(WS_URL,"", "", "query", new String[]{"fwbh","username","password","attach","param","soapAction","soapAuth"}, 
				new Object[]{fwbh,username,password,attach,param,soapAction,soapAuth},
				new QName[]{ Constants.XSD_STRING, Constants.XSD_STRING, Constants.XSD_STRING, Constants.XSD_STRING,
				Constants.XSD_STRING, Constants.XSD_STRING, Constants.XSD_STRING});
	}
	/**
	 * Webservice客户端调用服务方法
	 * @param url:Webservice服务URL
	 * @param methodName：Webservice服务接口方法名
	 * @param args：Webservice服务接口方法参数名
	 * @param argsValue：Webservice服务接口方法参数值
	 * @return String
	 */
	private static String excute(String url,String soapActionUri,String nameSpace,String methodName,String[] args,Object[] argsValue,QName[] argType) throws Exception{
		try {
			//
			org.apache.axis.client.Service service = new org.apache.axis.client.Service();
			Call call = (Call) service.createCall();
			//设置webservice请求地址
			call.setTargetEndpointAddress(url);
			//注册webservice请求方法
			call.setOperationName(new QName((nameSpace == null ? url : nameSpace),methodName));

			//注册webservice接口参数
			for(int i=0;i<args.length;i++){
				call.addParameter(args[i], argType[i], ParameterMode.IN);
			}
			//设置webservice请求地址
			call.setSOAPActionURI(soapActionUri == null ? url : soapActionUri);
			//设置返回类型
			call.setReturnType(Constants.XSD_STRING);

			//给方法传递参数，并且调用方法
			return (String)call.invoke(argsValue);

		} catch (Exception e)
		{
			
			throw new IllegalStateException("WEBSERVICE接口调用异常，请联系管理员",e);

		}
	}
	public static void main(String[] args) throws Exception {
		Client4GuoShui client = new Client4GuoShui();
		//接口ID及说明的调用参数
		
		/*1.增量同步
		XcFpZLStr.CGS.SJYY  1.4.4	新车发票数据增量同步
		EscFpZlStr.CGS.SJYY   1.4.5	二手车发票数据增量同步
		XcgzsStr.CGS.SJYY     1.4.6	新车购置税数据增量同步
		*/
		// 开始时间   结束时间
		String response = client.webserviceInvoke4Sync("XcgzsStr.CGS.SJYY", "2014-11-16", "2014-11-16");
		
		/*2.实时读取
		XCFPStr.CGS.SJYY    1.4.1	新车发票数据单笔实时读取
		ESCFPStr.CGS.SJYY   1.4.2	二手车发票数据单笔实时读取
		CgsFpStr.CGS.SJYY   1.4.3	新车购置税数据单笔实时读取*/
		// 发票代码   发票编号
//		String response = client.webserviceInvoke4Query("ESCFPStr.CGS.SJYY", "144001324160", "00740004");
//		response = new String(response.getBytes	(),"UTF-8");
		System.out.println(response);
		//System.out.println(response.replaceAll("&lt;","<").replaceAll("&gt;",">"));
	}
}
