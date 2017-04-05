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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ycszh.util.gjgggl.GetBarCode;

/**
 * 生成邮政编码一维码
 * 
 * @author lzj
 * 
 */
@SuppressWarnings("serial")
public class CreateYzbmServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			OutputStream out = response.getOutputStream();
			String yzbm = request.getParameter("yzbm") == null ? "" : request.getParameter("yzbm").trim();
			if (!yzbm.equals("")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@100.100.21.101:1521:edb","dpublish", "pub9513");
				if (conn != null) {
					Map<String, Object> bazMap = initalBazData(request, conn, yzbm);
					byte[] image = createBazPic(bazMap);
					out.write(image);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					 e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 初始化
	 * @param request
	 * @param lsh
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> initalBazData(HttpServletRequest request, Connection conn, String yzbm) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("/");
		String sparator = File.separator;
		// 样板图片地址
		// String sampleFilePath = path +
		// "\\pages\\gjgggl\\ggzxxgl\\pic\\sample.jpg";
		String sampleFilePath = path + sparator + "pages" + sparator + "jsrdzda" + sparator + "cg"+sparator+"sample.jpg";
		yzbm = checkStrNullAndReturn(yzbm);
		Map<String, Object> bazMap = new HashMap<String, Object>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (conn != null && !conn.isClosed()) {
			String sql = "select cjxh from dzda_jsz_daxxb where cjzt='1' and yzbm='" + yzbm + "' ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			String cjxh="";
			if (rs.next()) {
				cjxh = checkStrNullAndReturn(rs.getString("cjxh"));
			}
			if (!cjxh.equals("") && !cjxh.equals("")) {
				bazMap.put("yzbm", yzbm);
				// 备案证模板图片路径
				bazMap.put("sampleFilePath", sampleFilePath);
			}
		}
		return bazMap;
	}
	/**
	 * 生成图片
	 * 
	 * @param bazMap
	 * @return
	 * @throws Exception
	 */
	public byte[] createBazPic(Map<String, Object> bazMap) throws Exception {
		byte[] result = null;
		ByteArrayOutputStream outImg =null;
		try {
			if (bazMap != null && bazMap.size() > 0) {
				String yzbm = checkStrNullAndReturn(bazMap.get("yzbm"));
				//一维码证模板图片路径
				String sampleFilePath = checkStrNullAndReturn(bazMap.get("sampleFilePath"));
				if (!yzbm.equals("") && !yzbm.equals("") ) {
					BufferedImage buffImage = ImageIO.read(new File(sampleFilePath));
					if (buffImage != null) {
						Graphics g = buffImage.getGraphics();
						Font font = new Font("宋体", Font.BOLD, 20);
						g.setFont(font);
						Color c = new Color(0, 0, 0);
						g.setColor(c);
						//g.drawString(yzbm, 50, 50);
						// 流水号
						Image img_code= new GetBarCode().createImage(yzbm, "CODE39", "270", "65");//一维码
						g.drawImage(img_code,7,3,270, 58, null);
						 outImg = new ByteArrayOutputStream();
						ImageIO.write(buffImage, "jpg", outImg);
						result = outImg.toByteArray();
					}
				}
			}
		} catch (Exception e) {			
			System.out.println("错误");
			e.printStackTrace();
		}finally{
			if(outImg!=null){
				outImg.close();
			}
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
