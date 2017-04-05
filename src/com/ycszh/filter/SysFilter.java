   package com.ycszh.filter;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xml.sax.InputSource;
import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.webservice.BaseService;
import com.ycszh.webservice.BaseServiceImplService;
import com.iaspec.clientCommon.passport.controller.PassportController;
import com.iaspec.common.constant.memcached.MemcachedKeyConstant;
import com.iaspec.common.constant.sys.SysConstant;
import com.iaspec.common.entity.entityenum.system.ResourceTypeEnum;
import com.iaspec.common.entity.system.auth.Resource;
import com.iaspec.common.memcache.IMemClient;
import com.iaspec.common.service.system.auth.UserLoginService;

/**
 * @author dengsiqi E-mail:dengsiqi@vip.qq.com
 * @version 创建时间：2012-4-8 下午04:58:29 SysFilter.java类说明
 */
public class SysFilter implements Filter {

	public void destroy() {

	}
	private Set<String> exclude = new HashSet<String>();
	
	
	/***************************************************      如果是单机开发测试,以下代码要注释掉          ***********************/
//	private BaseServiceImplService baseServiceImpl = new BaseServiceImplService();
//	private BaseService baseService = baseServiceImpl.getBaseServiceImplPort();
//	private ApplicationContext ac = new ClassPathXmlApplicationContext(
//			"applicationContext.xml");
//
//	private UserLoginService userLoginService = (UserLoginService) ac
//			.getBean("userLoginServiceImpl");
//	private IMemClient defaultMemClient = (IMemClient) ac
//			.getBean("defaultMemClient");
//	protected PassportController passportController = (PassportController) ac
//			.getBean("passportController");
//	protected SysConstant sysConstant = (SysConstant)ac.getBean("sysConstant");
	/***************************************************      如果是单机开发测试,以上代码要注释掉          ***********************/

	private User user;
	private List<Resource> resources;


	public User getUser() {
		return user;
	}

	public List<Resource> getResources() {
		return resources;
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		String path = request.getContextPath();
		String uri = request.getRequestURI();
		String can = request.getQueryString();
		if(null != can && !"".equals(can)){
			uri = uri + "?" + can;
		}
		/*if (request.getMethod().equalsIgnoreCase("get")) {
			this.encoding(request);
		}*/
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		session.setAttribute("contextPath", path);
		if (allow(path, uri, exclude)) {
			chain.doFilter(request, response);
			return;
		}
		
		
		/***************************************************      如果是正式发布环境,以下代码要注释掉          ***********************/
		User user = new User();
		user.setId("e4e4439c3e12323a013e225215b53579");
		user.setName("051960");
		user.setYgid("e4e4439c3e12323a013e225215b53579");
		user.setYgxm("陈淳");
		user.setBmid("C34702A8FFF67CBFE040007F0100339B");
		user.setBmmc("机动车管理科民警");
//		user.setBmid("C34702A8FEDB7CBFE040007F0100339B");
//		user.setBmmc("口岸大队");
		
		session.setAttribute(SysConst.USERBEAN, user);
		
		chain.doFilter(request, response);
		return;
		/***************************************************      如果是正式发布环境,以上代码要注释掉          ***********************/

		
		/***************************************************      如果是单机开发测试,以下代码要注释掉          ***********************/
		/*
		try {

			//** 通用user判断 *//*
			// 得到登陆用户ID 测试时固定死
			//String userId = "e4e4439c39dc6da90139fb63f2da070a";
			String userId = passportController.getLoginedMemberId(request,response);
			
			//System.out.println("userId:" + userId);

			// 如果没有登陆用户 返回到首页
			if (userId == null) {
				//response.sendRedirect("http://ycs.cgs.jt.szs.gd/ycs");
				response.getWriter().println("<script>window.top.location.replace('http://ycs.cgs.jt.szs.gd/ycs')</script>");
				return;
			}

			// 先从 session 中取得用户
			User user = (User) session.getAttribute(SysConst.USERBEAN);

			// 如果是第一次登陆的用户 需要从新加载用户信息
			if (user == null) {
				//System.out.println("user对象为空");
				// 调用web service 得到需要的数据
				user = readXml("<?xml version=\"1.0\" encoding=\"utf-8\" ?><root><head><servCode>05</servCode><synData>COMMONINFO_M005</synData></head><body><queryCondition><yhid>"
						+ userId + "</yhid></queryCondition></body></root>");
				// user = passportController.getLoginedMember(request,response);
				
				// 如果根据userId 取得不到 用户对象，返回到登陆界面去
				if (user == null){
					//response.sendRedirect("http://ycs.cgs.jt.szs.gd/ycs");
					response.getWriter().println("<script>window.top.location.replace('http://ycs.cgs.jt.szs.gd/ycs')</script>");
					return;
				}
				//System.out.println("用户姓名:" + user.getYgxm());
				// 保存用户信息
				session.setAttribute(SysConst.USERBEAN, user);
				session.setAttribute("jszws", "4");
			}
			//System.out.println("用户姓名222:" + user.getYgxm());
			// 防止session 未被清空，有其他用户登陆
			if (!userId.equals(user.getId())) {
				// 获取登录用户 如果为null 返回首页
				//user = passportController.getLoginedMember(request,response);
				user = readXml("<?xml version=\"1.0\" encoding=\"utf-8\" ?><root><head><servCode>05</servCode><synData>COMMONINFO_M005</synData></head><body><queryCondition><yhid>"
						+ userId + "</yhid></queryCondition></body></root>");
				if (user == null){
					//response.sendRedirect("http://ycs.cgs.jt.szs.gd/ycs");
					response.getWriter().println("<script>window.top.location.replace('http://ycs.cgs.jt.szs.gd/ycs')</script>");
					return;
				}
				// 保存用户信息
				session.setAttribute(SysConst.USERBEAN, user);
				session.setAttribute("jszws", "4");
			}
			
			//System.out.println("username:" + user.getName());

			// 取得用户权限 先通过 memcache
			resources = (List<Resource>) defaultMemClient
					.get(MemcachedKeyConstant.AUTH_MEMCACHE_ALL_KEY_SUFF
							+ sysConstant.getSystemId() + "_" + user.getId());

			// 如果 memcache 为空
			if (resources == null) {
				//System.out.println("memcache 中不存在该用户的权限,从web Service 中取");
				// 从数据库取
				resources = userLoginService.findResourceBy(sysConstant
						.getSystemId(), user.getId(), null,
						new ResourceTypeEnum[0]);
			}
			// 放入memcache中
			defaultMemClient.set(
					MemcachedKeyConstant.AUTH_MEMCACHE_ALL_KEY_SUFF
							+ sysConstant.getSystemId() + "_" + user.getId(),
					MemcachedKeyConstant.AUTH_TIME_OUT, resources);

			// 判断用户是否有此页面的权限
			//System.out.println("用户访问的权限："+uri);
			for (Resource rs : resources) {
				if (rs != null) {
					if (rs.getPath() != null) {
						String temppath = path + rs.getPath();
						//System.out.println("拼接权限："+temppath);
						if(temppath.equals(uri)){
							//System.out.println("用户有该权限");
							chain.doFilter(request, response);
							return;
						}
					}
				}
			}
			String tempmenu[] = {
					"/drv/initInertSlgDrvXxcjb.action",
					"/drv/initSlgDrvXxcjbList.action",
					"/drv/initSlgDrvXxcjbList2.action?isnew=1",
					"/yujing/slgSpxx_initSlgSpxxList.action",
					"/yujing/yujing_initYujingList.action",
					"/drv/insertSlgDrvXxcjb.action?ywlx=XXMSHZ",
					"/pages/guoshui/guoshuilist.jsp",
					"/pages/dzda/dzda.jsp",
					"/yanche/chbase_initPdasmycChbaseList.action",
					"/yanche/chdlr_initPdasmycChdlrList.action",
					"/yanche/vehpcb_initPdasmycVehPcbList.action",
					"/yanche/vehpcb_initYcrwList.action",
					"/yanche/vehpcb_initYcrwList4.action",
					"/pages/yanche/yccx.jsp",
					"/yanche/vehpcb_initVDataCheckList.action",
					"/yanche/vehpcb_initPdasmycVehPcbList2.action",
					"/yanche/vehpcb_initPdasmycVehPcbList3.action",
					"/yanche/vehpcb_initPdasmycVehPcbList4.action",
					"/yanche/lsgl_initYwlsglVehFlowList.action?type=1&gw=1001&ld=0",
					"/yanche/lsgl_initYwlsglVehFlowList.action?type=2&gw=1001&ld=0",
					"/yanche/lsgl_initYwlsglVehFlowList.action?type=2&gw=1001&ld=1",
					"/yanche/lsgl_initFlowInfo_thyj.action?type=2&gw=1001&ld=1",
					"/yanche/lsgl_initYwlsglVehFlowList.action?type=1&gw=2001&ld=0",
					"/yanche/lsgl_initYwlsglVehFlowList.action?type=2&gw=2001&ld=0",
					"/yanche/lsgl_initYwlsglVehFlowList.action?type=2&gw=2001&ld=1",
					"/bfc/bfc_initBfcJbxxbList.action",
					"/veh/veh_initEditPage.action",
					"/veh/veh_initSlCxList.action",
					"/veh/vehSpxx_initSlgSpxxList.action",
					"/pages/veh/jdccjlist.jsp",
					"/ydwt/ydwt_businessManage.action",
					"/ydwt/ydwt_declareAndQuitStat.action",
					"/ydwt/ydwt_ydwtPosQuitStat.action",
					"/ydwt/ydwt_getUsersList.action",
					"/ydwt/ydwt_initWarnYdwt.action",
					"/veh/vehSpxx_initVehPodbSpList.action",
					"/dydj/dydj_getYHUsersList.action",
					"/dydj/dydj_getUsersList.action",
					"/dydj/yhsl_initDydjList.action?ywlx=EE&ywyy=EE:A,EE:B",
					"/dydj/yhsl_initWarnDydj.action",
					"/pages/yujing/loading.jsp",
					"/blacklist/black_getBlackList.action",
					"/blacklist/black_getDictList.action",
					"/qrelate/qrelate_qrelate1.action",
					"/qrelate/qrelate_qrelate2.action",
					"/qrelate/qrelate_qrelate3.action",
					"/qrelate/qrelate_qrelate4.action",
					"/qrelate/qrelate_qrelate5.action",
					"/qrelate/qrelate_qrelate6.action",
					"/jdcbg/jdcbg_contactBgQuery.action?cs=2",
					"/jdcbg/jdcbg_jdcBgQuery.action?bs=2",
					"/jdcbg/jdcbg_contactBgQuery.action?cs=2",
					"/cljstj/cljstj_cljstjShList.action?qc=2",
					"/ddc/ddc_getvehTypeElectroList.action",
					"/bfc/bfcsp_getBfcTsspbList.action?jb=3",
					"/bfc/bfcsp_getBfcTsspbList.action?jb=2",
					"/bfc/bfcsp_getBfcTsspbList.action?jb=1",
					"/bfc/bfjg_getBfcJgskbList.action",
					"/bfc/bfcsp_bfcSelList.action",
					
					"/gjgg/gjgg_clbaglInital.action",
					"/gjgg/gjgg_ggjgInital.action",
					"/gjgg/ycgg_getYcxxInital.action?qx=cj",
					"/gjgg/ycgg_getYcxxInital.action?qx=kj",
					"/gjgg/ycgg_getycxxInital_tp.action",
					"/gjgg/ggz_getBusGgjgclsb.action",
					"/gjgg/ggz_getBusGgjgclsb_cd.action",
					"/gjgg/ggz_getZxOrHfData.action?qx=cj",
					"/gjgg/ggz_getZxOrHfData.action?qx=kj",
					"/gjgg/xtgl_getBusBaseInital.action",
					"/gjgg/xtgl_getSzzdInital.action",
					"/gjgg/tjcx_getLscx.action",
					"/gjgg/tjcx_getGgzdacx.action",
					"/gjgg/tjcx_getShqktj.action?qx=cj",
					"/gjgg/tjcx_getShqktj.action?qx=kj",
					"/gjgg/tjcx_getZjffqktj.action?qx=cj",
					"/gjgg/tjcx_getZjffqktj.action?qx=kj",
					"/pdaimei/imei_getTPdaYcimei.action"
					"/pdaimei/imei_getTPdaYcimei.action",
					"/jsrdzda/jsrdzda_initJsrCjList.action",
					"/jsrdzda/jsrdzda_initCgCheckJsrList.action",
					"/sfrz/sfrz_initEditPage.action",
					"/sfrz/sfrz_initSfrzCjxxbList.action"
					};
			for(String menu : tempmenu){
				String temppath = path + menu;
				if(temppath.equals(uri)){
					//response.sendRedirect("http://ycs.cgs.jt.szs.gd/ycs");
					response.getWriter().println("<script>window.top.location.replace('http://ycs.cgs.jt.szs.gd/ycs')</script>");
					return;
				}
			}

			chain.doFilter(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		/***************************************************      如果是单机开发测试,以上代码要注释掉          ***********************/

	}

	
	/***************************************************      如果是单机开发测试,以下代码要注释掉          ***********************/
	/*
	private User readXml(String xml) {

		User user = new User();

		try {

			String returnStr = baseService.callService(xml);

			SAXReader reader = new SAXReader();
			StringReader read = new StringReader(returnStr);
			InputSource source = new InputSource(read);
			Document document;
			document = reader.read(source);
			Element returnElement = document.getRootElement();

			// 得到头部节点 判断返回是否成功，否 返回登陆页面。
			Element head = returnElement.element("head");

			Element code = head.element("code");
			
			// 如果返回非成功消息，返回NULL
			if(!code.getText().equals("1")){
				return null;
			}
			
			// System.out.println("code = "+code.getText());
			@SuppressWarnings("unused")
			Element message = head.element("message");
			// System.out.println("message = "+message.getText());

			// 得到内容节点
			Element body = returnElement.element("body");
			Element elements = body.element("elements").element("element");

			user.setId(elements.element("yhid").getText());
			user.setName(elements.element("yhm").getText());
			user.setYgid(elements.element("ygid").getText());
			user.setYgxm(elements.element("ygxm").getText());
			user.setBmid(elements.element("bmid").getText());
			user.setBmmc(elements.element("bmmc").getText());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
	*/
	/***************************************************      如果是单机开发测试,以上代码要注释掉          ***********************/
	
	/**
	 * GET提交方式中文编码过滤
	 * 
	 * @param request
	 */
	private void encoding(HttpServletRequest request) {
		Iterator<?> iter = request.getParameterMap().values().iterator();
		while (iter.hasNext()) {
			String[] parames = (String[]) iter.next();
			for (int i = 0; i < parames.length; i++) {
				try {
					parames[i] = new String(parames[i].getBytes("ISO-8859-1"),
							"UTF-8");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * URL地址过滤
	 * 
	 * @param path
	 * @param uri
	 * @param urls
	 * @return boolean
	 */
	private boolean allow(String path, String uri, Set<String> urls) {
		for (String url : urls) {
			if (url.startsWith("*")) {
				if (uri.endsWith(url.substring(1))) {
					return true;
				}
			} else if (url.endsWith("*")) {
				if (uri.startsWith(url.substring(0, url.length() - 1))) {
					return true;
				}
			} else {
				if (uri.equals(path + url)) {
					return true;
				}
			}
		}
		return false;
	}

	public void init(FilterConfig config) throws ServletException {
		String values = config.getInitParameter("exclude");
		if (!"".equals(values) && values != null) {
			String[] params = values.split("\\|");
			for (String param : params) {
				exclude.add(param);
			}
		}
	}
}
