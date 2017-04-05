package com.ycszh.ssh.action.gjgggl;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgclsb;
import com.ycszh.ssh.hbm.gjgggl.BusVehicleBase;
import com.ycszh.ssh.service.gjgggl.GjclycxxglService;
import com.ycszh.util.JsonUtil;

import common.Logger;

/**
 * 公交客运车辆验车信息管理
 * 
 * @author ldy
 * 
 */
public class GjclycxxglAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(GjclycxxglAction.class);
	private GjclycxxglService gjclycxxglService;
	private BusGgjgclsb busGgjgclsb;

	public String getYcxxInital() throws Exception {
		gjclycxxglService.getBusGgjgclsbTotalList(request);
		return "gjclycxx";
	}

	public String getYcxxInitalByPch() throws Exception {
		gjclycxxglService.getBusGgjgclsbByPch(request);
		return "gjclycxxByPch";
	}

	/**
	 * 获取打印数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getYcxxInitalByPch_dy() throws Exception {
		gjclycxxglService.getBusGgjgclsbByPch_dy(request);
		return "gjclycxxByPch_dy";
	}

	/**
	 * 验车部门调配(初始化数据)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getycxxInital_tp() throws Exception {
		gjclycxxglService.getBusGgjgclsbTotalList_fp(request);
		return "gjclycxx_fp";
	}

	public String getycxxInital_tp_pch() throws Exception {
		String ycbm = request.getParameter("ycbm") == null ? "" : request.getParameter("ycbm").trim();

		// 验车部门
		Map<String, String> ycbmMap = gjclycxxglService.getBusSzjdMap(null, "YYBM", ycbm);
		Map<String, String> tempYcbmMap = gjclycxxglService.getBusSzjdMap(ycbm, null, null);

		// Map<String, String> ycbmMap = gjclycxxglService.getBmMap(null, ycbm,
		// false);
		// Map<String, String> tempYcbmMap = gjclycxxglService.getBmMap(ycbm,
		// null, false);

		String ycbmmc = tempYcbmMap.get(ycbm) == null ? "" : tempYcbmMap.get(ycbm).trim();
		gjclycxxglService.getBusGgjgclsbTotalList_fp_pch(request);
		request.setAttribute("ycbmMap", ycbmMap);
		request.setAttribute("ycbm", ycbm);
		request.setAttribute("ycbmmc", ycbmmc);
		return "gjclycxx_fp_pch";
	}

	public String InitalUpdateDataByPch() throws Exception {
		// 查询退办原因
		Map<String, String> tbyyMap = gjclycxxglService.getBusSzjdMap(null, "TTBY", null);
		request.setAttribute("tbyyMap", tbyyMap);
		return "updateBusGgjgclsbByPch";
	}

	/**
	 * 验车部门调配(按批次号)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getInitalUpdateDataByPch_fp() throws Exception {
		String ycbm = request.getParameter("ycbm") == null ? "" : request.getParameter("ycbm").trim();
		// 验车部门
		Map<String, String> ycbmMap = gjclycxxglService.getBusSzjdMap(null, "YYBM", ycbm);
		// Map<String, String> ycbmMap = gjclycxxglService.getBmMap(ycbm, null,
		// false);
		request.setAttribute("ycbmMap", ycbmMap);
		return "updateBusGgjgclsbByPch_fp_pch";
	}

	public String InitalUpdateDataByLsh() throws Exception {
		// 查询退办原因
		Map<String, String> tbyyMap = gjclycxxglService.getBusSzjdMap(null, "TTBY", null);
		request.setAttribute("tbyyMap", tbyyMap);
		return "updateBusGgjgclsbByLsh";
	}

	public String InitalTbyy() throws Exception {
		// 查询退办原因
		Map<String, String> tbyyMap = gjclycxxglService.getBusSzjdMap(null, "TTBY", null);
		request.setAttribute("tbyyMap", tbyyMap);
		return "initalTbyy";
	}

	/**
	 * 多个批次号批量审核
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgclsbByPch() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = gjclycxxglService.updateBusGgjgclsbByPch(request);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();

		return NONE;
	}

	/**
	 * 多批次调配审核部门
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgclsbByPch_fp() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = gjclycxxglService.updateBusGgjgclsbByPch_fp(request);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();

		return NONE;
	}

	public String tbBusGgjgclsbByPch() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = gjclycxxglService.tbBusGgjgclsbByPch(request);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();

		return NONE;
	}

	/**
	 * 多个流水号批量审核
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgclsbByLsh() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = gjclycxxglService.updateBusGgjgclsbByLsh(request);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();

		return NONE;
	}

	/**
	 * 修改广告喷涂申报
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgclsb() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = gjclycxxglService.updateBusGgjgclsb(request, busGgjgclsb);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();

		return NONE;
	}

	public String getYcPic() throws Exception {

		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			OutputStream out = response.getOutputStream();
			String lsh = request.getParameter("lsh") == null ? "" : request.getParameter("lsh").trim();
			String position = request.getParameter("position") == null ? "" : request.getParameter("position").trim();
			Blob image = gjclycxxglService.getYcPic(lsh, position);
			if (image != null && image.length() > 0) {
				byte[] byte_array = null;
				int length = (int) image.length();
				byte_array = image.getBytes(1, length);
				response.setContentType("image/jpeg");
				for (int i = 0; i < byte_array.length; i++) {
					out.write(byte_array[i]);
				}
			}
		} catch (Exception e) {

		}

		return NONE;
	}

	public String getBusVehicleBaseJson() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			String gjgsid = request.getParameter("gjgsid") == null ? "" : request.getParameter("gjgsid").trim();
			String hphm = request.getParameter("hphm") == null ? "" : request.getParameter("hphm").trim();
			String hpzl = request.getParameter("hpzl") == null ? "" : request.getParameter("hpzl").trim();
			String hphmStr = URLDecoder.decode(hphm, "UTF-8");
			List<BusVehicleBase> busVehicleBases = gjclycxxglService.getBusVehicleBase(gjgsid, hphmStr, hpzl, "A");
			if (busVehicleBases != null && busVehicleBases.size() > 0) {
				BusVehicleBase bus = busVehicleBases.get(0);
				bus.setXszzp(null);
				result = JsonUtil.bean2json(bus);
			} else {
				result = "-查无公交车辆数据";
			}
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();

		return NONE;
	}

	public String getBusGgjgclsbByLsh() throws Exception {
		String result = "gjclycxxgl_detail";
		String isShow = request.getParameter("isShow") == null ? "" : request.getParameter("isShow").trim();
		if (isShow.equals("true")) {
			result = "gjclycxxgl_detail_show";
		}
		gjclycxxglService.getBusGgjgclsbByLsh(request);
		return result;
	}

	public GjclycxxglService getGjclycxxglService() {
		return gjclycxxglService;
	}

	public void setGjclycxxglService(GjclycxxglService gjclycxxglService) {
		this.gjclycxxglService = gjclycxxglService;
	}

	public static Logger getLogger() {
		return logger;
	}

	public BusGgjgclsb getBusGgjgclsb() {
		return busGgjgclsb;
	}

	public void setBusGgjgclsb(BusGgjgclsb busGgjgclsb) {
		this.busGgjgclsb = busGgjgclsb;
	}

}
