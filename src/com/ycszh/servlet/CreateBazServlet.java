package com.ycszh.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ycszh.util.DateUtil;

/**
 * 生成备案证
 * 
 * @author ldy
 * 
 */
public class CreateBazServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			
			Date s=new Date();
			
			Date e=new Date();
			
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			OutputStream out = response.getOutputStream();
			String lsh = request.getParameter("lsh") == null ? "" : request.getParameter("lsh").trim();
			if (!lsh.equals("")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager
						.getConnection(
								"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 100.100.21.33)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 100.100.21.34)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = tpdb)(FAILOVER_MODE =(TYPE = SELECT)(METHOD = BASIC)(RETRIES = 180)(DELAY = 5))))",
								"VehOffice", "off123");
				e=new Date();
				System.out.println("***************allTimeGgz1:"+(e.getTime()-s.getTime())/1000.0);
				if (conn != null) {

					Map<String, Object> bazMap = initalBazData(request, conn, lsh);
					byte[] image = createBazPic(bazMap);

					out.write(image);
				}
				e=new Date();
				System.out.println("***************allTimeGgz2:"+(e.getTime()-s.getTime())/1000.0);
			}
			
			
			
			e=new Date();
			System.out.println("***************allTimeGgz3:"+(e.getTime()-s.getTime())/1000.0);

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
					conn=null;
				} catch (SQLException e) {
					conn=null;
					// e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 初始化备案证数据
	 * 
	 * @param request
	 * @param lsh
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> initalBazData(HttpServletRequest request, Connection conn, String lsh) throws Exception {

		String path = request.getSession().getServletContext().getRealPath("/");

		String sparator = File.separator;
		// 样板图片地址
		// String sampleFilePath = path +
		// "\\pages\\gjgggl\\ggzxxgl\\pic\\sample.jpg";
		String sampleFilePath = path + sparator + "pages" + sparator + "gjgggl" + sparator + "ggzxxgl" + sparator + "pic" + sparator + "sample.jpg";
		// System.out.println("filepath:" + sampleFilePath);

		lsh = checkStrNullAndReturn(lsh);
		Map<String, Object> bazMap = new HashMap<String, Object>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		if (conn != null && !conn.isClosed()) {
			String sql = " select a.hphm,a.hpzl,a.bgyxts from BUS_GGJGCLSB a where a.lsh='" + lsh + "' ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			String hphm = "";
			String hpzl = "";
			// 变更有效天数
			String bgyxts = "";
			if (rs.next()) {
				hphm = checkStrNullAndReturn(rs.getString("hphm"));
				hpzl = checkStrNullAndReturn(rs.getString("hpzl"));
				bgyxts = checkStrNullAndReturn(rs.getString("bgyxts"));
			}

			if (!hphm.equals("") && !hpzl.equals("") && !bgyxts.equals("")) {

				String sql_bus = " select a.fdjh,a.clsbdh,a.jdcsyr from BUS_VEHICLE_BASE a where a.hphm='" + hphm + "' and a.hpzl='" + hpzl + "' ";
				ps = conn.prepareStatement(sql_bus);
				rs = ps.executeQuery();
				if (rs.next()) {

					String fdjhStr = checkStrNullAndReturn(rs.getString("fdjh"));
					String clsbdhStr = checkStrNullAndReturn(rs.getString("clsbdh"));
					String jdcsyrStr = checkStrNullAndReturn(rs.getString("jdcsyr"));

					// 翻译号牌种类
					String hpzlStr = "";
					String sql_hpzl = " select a.dmsm1 from es_veh_code a where a.dmlb='07' and a.dmz='" + hpzl + "' ";
					ps = conn.prepareStatement(sql_hpzl);
					rs = ps.executeQuery();
					if (rs.next()) {
						hpzlStr = checkStrNullAndReturn(rs.getString("dmsm1"));
					}

					// 查询广告业务图片
					String sql_photo = "select a.l_pic,a.r_pic from BUS_GGJGCLSB_PHOTO a where a.lsh='" + lsh + "' ";

					ps = conn.prepareStatement(sql_photo);
					rs = ps.executeQuery();
					if (rs.next()) {
						Blob zqPic = rs.getBlob("l_pic");
						Blob yhPic = rs.getBlob("r_pic");

						// 查询制广告证时间
						String sql_ysq = " select a.zzrq from BUS_CERTIFY_INFO a where a.lsh='" + lsh + "' ";
						PreparedStatement ps_yxq = conn.prepareStatement(sql_ysq);
						ResultSet rs_yxq = ps_yxq.executeQuery();
						String yxq = "";
						if (rs_yxq.next()) {
							// 制证日期
							Date zzrq = rs_yxq.getDate("zzrq");

							if (zzrq != null) {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(zzrq);
								calendar.add(Calendar.DATE, Integer.parseInt(bgyxts));
								yxq = checkStrNullAndReturn(DateUtil.date2String(calendar.getTime(), "yyyy-MM-dd"));

							}
						}
						bazMap.put("hphm", hphm);
						bazMap.put("hpzl", hpzlStr);
						bazMap.put("fdjh", fdjhStr);
						bazMap.put("clsbdh", clsbdhStr);
						bazMap.put("jdcsyr", jdcsyrStr);
						bazMap.put("yxq", yxq);
						bazMap.put("lsh", lsh);

						// 左前图片
						bazMap.put("zqPic", zqPic);
						// 右后图片
						bazMap.put("yhPic", yhPic);
						// 备案证模板图片路径
						bazMap.put("sampleFilePath", sampleFilePath);

					}

				}

			}
		}

		return bazMap;
	}

	/**
	 * 生成备案证图片
	 * 
	 * @param bazMap
	 * @return
	 * @throws Exception
	 */
	public byte[] createBazPic(Map<String, Object> bazMap) throws Exception {

		byte[] result = null;

		try {
			if (bazMap != null && bazMap.size() > 0) {
				String hphm = checkStrNullAndReturn(bazMap.get("hphm"));
				String hpzl = checkStrNullAndReturn(bazMap.get("hpzl"));
				String fdjh = checkStrNullAndReturn(bazMap.get("hpzl"));
				String clsbdh = checkStrNullAndReturn(bazMap.get("clsbdh"));
				String jdcsyr = checkStrNullAndReturn(bazMap.get("jdcsyr"));
				String yxq = checkStrNullAndReturn(bazMap.get("yxq"));
				String lsh = checkStrNullAndReturn(bazMap.get("lsh"));
				// 备案证模板图片路径
				String sampleFilePath = checkStrNullAndReturn(bazMap.get("sampleFilePath"));
				// 左前图片
				Object tempZqPic = bazMap.get("zqPic");
				// 右后图片
				Object tempYhPic = bazMap.get("yhPic");

				if (!hphm.equals("") && !hpzl.equals("") && !lsh.equals("") && !sampleFilePath.equals("") && tempZqPic != null && tempYhPic != null) {
					BufferedImage buffImage = ImageIO.read(new File(sampleFilePath));
					if (buffImage != null) {
						Graphics g = buffImage.getGraphics();
						Font font = new Font("宋体", Font.BOLD, 20);
						g.setFont(font);
						Color c = new Color(0, 0, 0);
						g.setColor(c);
						// 号牌号码
						g.drawString(hphm, 140, 190);
						// 号牌种类
						g.drawString(hpzl, 420, 190);
						// 发动机号
						g.drawString(fdjh, 140, 240);
						// 车架号
						g.drawString(clsbdh, 390, 240);
						// 机动车所有人
						g.drawString(jdcsyr, 170, 290);
						// 有效期
						g.drawString(yxq, 140, 335);
						// 流水号
						g.drawString(lsh, 100, 387);

						Blob zqPic = (Blob) tempZqPic;
						Blob yhPic = (Blob) tempYhPic;

						// 左前图片
						Image img_zq = null;
						// 右后图片
						Image img_yh = null;
						try {
							// 左前图片
							img_zq = ImageIO.read(zqPic.getBinaryStream());
							// 右后图片
							img_yh = ImageIO.read(yhPic.getBinaryStream());

						} catch (Throwable e) {
							e.printStackTrace();
						}

						g.drawImage(img_zq, 50, 575, 278, 192, null);
						g.drawImage(img_yh, 340, 575, 278, 192, null);

						ByteArrayOutputStream outImg = new ByteArrayOutputStream();
						ImageIO.write(buffImage, "jpg", outImg);

						result = outImg.toByteArray();

					}

				}

			}
		} catch (Exception e) {
			System.out.println("wrongwrongwrong");
			e.printStackTrace();
		}

		return result;
	}

	public String checkStrNullAndReturn(Object str) {
		String returnStr = "";
		try {
			returnStr = str == null ? "" : str.toString().trim();
		} catch (Exception e) {
			return "";
		}
		return returnStr;
	}

}
