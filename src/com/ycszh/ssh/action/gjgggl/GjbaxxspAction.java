package com.ycszh.ssh.action.gjgggl;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.util.List;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgBase;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgdlr;
import com.ycszh.ssh.hbm.gjgggl.BusVehicleBase;
import com.ycszh.ssh.service.gjgggl.GjbaxxspService;
import common.Logger;

/**
 * 公交客运公司备案信息审批管理
 * 
 * @author ldy
 * 
 */
public class GjbaxxspAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(GjbaxxspAction.class);
	private GjbaxxspService gjbaxxspService;
	private BusVehicleBase bus;

	public String clbaglInital() throws Exception {

		List<BusVehicleBase> busVehicleBases = gjbaxxspService.getBusVehicleBaseList(request);
		request.setAttribute("busVehicleBases", busVehicleBases);
		return "clbagl";
	}

	public String getBusVehicleBase() throws Exception {

		BusVehicleBase busVehicleBase = gjbaxxspService.getBusVehicleBase(request);
		request.setAttribute("busVehicleBase", busVehicleBase);

		return "clbagl_detail";
	}

	public String getXszPic() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		OutputStream out = response.getOutputStream();
		String xh = request.getParameter("xh") == null ? "" : request.getParameter("xh").trim();
		Blob image = gjbaxxspService.getXszPic(xh);
		if (image != null && image.length() > 0) {
			byte[] byte_array = null;
			int length = (int) image.length();
			byte_array = image.getBytes(1, length);
			response.setContentType("image/jpeg");
			for (int i = 0; i < byte_array.length; i++) {
				out.write(byte_array[i]);
			}
		}
		return NONE;
	}

	public String updateBusVehicleBase() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = gjbaxxspService.updateBusVehicleBase(bus, request);
		} catch (Exception e) {
			result = "-操作失败:" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();
		return NONE;
	}

	public String cancelBusVehicleBase() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = gjbaxxspService.cancelBusVehicleBase(request);
		} catch (Exception e) {
			result = "-操作失败:" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();
		return NONE;
	}

	/**
	 * 广告机构初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ggjgInital() throws Exception {

		List<BusGgjgBase> busGgjgBases = gjbaxxspService.getBusGgjgBaseList(request);
		request.setAttribute("busGgjgBases", busGgjgBases);
		return "ggjgbagl";
	}

	public String updateBusGgjgBase() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = gjbaxxspService.updateBusGgjgBase(request);
		} catch (Exception e) {
			result = "-操作失败:" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();
		return NONE;
	}

	public String updateBusGgjgBaseByCzlx() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = gjbaxxspService.updateBusGgjgBaseByCzlx(request);
		} catch (Exception e) {
			result = "-操作失败:" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();
		return NONE;
	}

	/**
	 * 广告机构代办人初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ggjgDbrInital() throws Exception {

		List<BusGgjgdlr> busGgjgdlrs = gjbaxxspService.ggjgDbrList(request);
		List<BusGgjgBase> busGgjgBases = gjbaxxspService.getBusGgjgBases(null, null, null);
		request.setAttribute("busGgjgdlrs", busGgjgdlrs);
		request.setAttribute("busGgjgBases", busGgjgBases);
		return "dbrybagl";
	}

	public String updateGgjgDbryByCzlx() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = gjbaxxspService.updateGgjgDbryByCzlx(request);
		} catch (Exception e) {
			result = "-操作失败:" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();
		return NONE;
	}

	public GjbaxxspService getGjbaxxspService() {
		return gjbaxxspService;
	}

	public void setGjbaxxspService(GjbaxxspService gjbaxxspService) {
		this.gjbaxxspService = gjbaxxspService;
	}

	public static Logger getLogger() {
		return logger;
	}

	public BusVehicleBase getBus() {
		return bus;
	}

	public void setBus(BusVehicleBase bus) {
		this.bus = bus;
	}

}
