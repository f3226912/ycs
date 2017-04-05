package com.ycszh.ssh.action.gjgggl;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.service.gjgggl.GgzxxglService;
import common.Logger;

/**
 * 广告证信息管理管理
 * 
 * @author ldy
 * 
 */
public class GgzxxglAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(GgzxxglAction.class);
	private GgzxxglService ggzxxglService;

	/**
	 * 打印广告证
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getBusGgjgclsb() throws Exception {
		ggzxxglService.getBusGgjgclsb(request);
		return "ggzdy";
	}

	/**
	 * 正常打印广告证
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addBusCertifyInfo() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = ggzxxglService.addBusCertifyInfo(request);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}

		out.write(result);
		return NONE;
	}

	/**
	 * 广告证错证重打
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getBusGgjgclsb_cd() throws Exception {
		ggzxxglService.getBusGgjgclsb_cd(request);
		return "ggzdy_cd";
	}

	/**
	 * 错证重打
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addBusCertifyCzcd() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = ggzxxglService.addBusCertifyCzcd(request);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}

		out.write(result);
		return NONE;
	}

	/**
	 * 获取要注销或恢复的广告证数据集合
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getZxOrHfData() throws Exception {
		ggzxxglService.getZxData(request);
		return "zxorhf";
	}

	/**
	 * 注销或恢复广告证
	 * 
	 * @return
	 * @throws Exception
	 */
	public String zxOrfhBusCertifyInfo() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = ggzxxglService.zxOrfhBusCertifyInfo(request);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}

		out.write(result);
		return NONE;
	}

	public String getBusGgjgclsbByLsh() throws Exception {
		ggzxxglService.getBusGgjgclsbByLsh(request);
		return "ggzbylsh";
	}

	/**
	 * 获取备案证图片
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getBazPic() throws Exception {
		try {

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			OutputStream os = response.getOutputStream();
			String lsh = request.getParameter("lsh") == null ? "" : request.getParameter("lsh").trim();
			Map<String, Object> bazMap = ggzxxglService.initalBazData(request, lsh);
			byte[] byte_array = ggzxxglService.createBazPic(bazMap);
			if (byte_array != null && byte_array.length > 0) {
				response.setContentType("image/jpeg");
				for (int i = 0; i < byte_array.length; i++) {
					os.write(byte_array[i]);
				}
			}
			if (os != null) {
				os.flush();
				os.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return NONE;
	}

	public String getImage() throws Exception {
		try {

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			OutputStream os = response.getOutputStream();

			String lsh = request.getParameter("lsh") == null ? "" : request.getParameter("lsh").trim();
			String position = request.getParameter("position") == null ? "" : request.getParameter("position").trim();

			byte[] byte_array = ggzxxglService.getImage(lsh, position);
			if (byte_array != null && byte_array.length > 0) {
				response.setContentType("image/jpeg");
				for (int i = 0; i < byte_array.length; i++) {
					os.write(byte_array[i]);
				}
			}
			if (os != null) {
				os.flush();
				os.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return NONE;
	}

	public GgzxxglService getGgzxxglService() {
		return ggzxxglService;
	}

	public void setGgzxxglService(GgzxxglService ggzxxglService) {
		this.ggzxxglService = ggzxxglService;
	}

	public static Logger getLogger() {
		return logger;
	}

}
