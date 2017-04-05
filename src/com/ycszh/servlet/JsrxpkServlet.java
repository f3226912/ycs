package com.ycszh.servlet;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 驾驶人相片库查询
 * 
 * @author lqx
 * 
 */
@SuppressWarnings("serial")
public class JsrxpkServlet extends HttpServlet {

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
//			OutputStream out = response.getOutputStream();
			String xphzbh = request.getParameter("xphzbh") == null ? "" : request.getParameter("xphzbh").trim();
			if (!xphzbh.equals("")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager
						.getConnection(
								"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)(HOST =10.42.7.17)(PORT =1521)) (ADDRESS = (PROTOCOL = TCP)(HOST =10.42.7.15)(PORT =1521)) ) (CONNECT_DATA = (SID =dowdb) ))","cgs", "cheguan69370");
				String sql ="";
				if (conn != null) {
					sql = "select * from PHOTO_T where photo_no='"+ xphzbh +"' ";
					System.out.println(sql);
				}
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				if (rs.next()) {
					/*// 方式1
					Blob blob = rs.getBlob(2);	// 在weblogic环境下读取BLOB的值（下标从1开始）				
					InputStream in = blob.getBinaryStream();	// 生成输入流
					BufferedImage bimage = null;			// 缓存图片对象
					BufferedInputStream ins = new BufferedInputStream(in);	// 生成缓存输入流
					bimage = ImageIO.read(ins);	// 由ImageIO对象读成缓存图片对象
					ServletOutputStream sos = response.getOutputStream();	// 取得输出流，把图片输出到客户端
					// 如果想把图片生成本地图片文件可以这样写
					//FileOutputStream sos = new FileOutputStream(new File("C:/cs.jpg"));
					BufferedOutputStream bos = new BufferedOutputStream(sos);	//生成输出缓存
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);// 生成JPEG图片对象
					encoder.encode(bimage);	// 按格式把图片流进行编码
					bos.close();
					sos.close();*/
					
					// 方式2，直接ImageIO写出来
					Blob blob = rs.getBlob(2);
					InputStream in = blob.getBinaryStream();
					BufferedImage bimage = null;	
					BufferedInputStream ins = new BufferedInputStream(in);
					bimage = ImageIO.read(ins);
					ImageIO.write(bimage, "JPEG", response.getOutputStream());
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