package com.ycszh.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 查询左前和右后广告喷涂业务图片
 * 
 * @author ldy
 * 
 */
public class ImageServlet extends HttpServlet {

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
			String position = request.getParameter("position") == null ? "" : request.getParameter("position").trim();
			if (!lsh.equals("") && !position.equals("")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager
						.getConnection(
								"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 100.100.21.33)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 100.100.21.34)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = tpdb)(FAILOVER_MODE =(TYPE = SELECT)(METHOD = BASIC)(RETRIES = 180)(DELAY = 5))))",
								"VehOffice", "off123");
				e=new Date();
				System.out.println("***************allTime1:"+(e.getTime()-s.getTime())/1000.0);
				if (conn != null) {
					String sql = "";

					if (position.equals("left")) {
						sql = " select a.L_PIC from BUS_GGJGCLSB_PHOTO a where a.lsh='" + lsh + "' ";
					} else {
						sql = " select a.R_PIC from BUS_GGJGCLSB_PHOTO a where a.lsh='" + lsh + "' ";
					}

					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(sql);
					e=new Date();
					System.out.println("***************allTime2:"+(e.getTime()-s.getTime())/1000.0);
					
					if (rs.next()) {
						Blob image = rs.getBlob(1);

						if (image != null && image.length() > 0) {
							byte[] byte_array = null;
							int length = (int) image.length();
							byte_array = image.getBytes(1, length);
							response.setContentType("image/jpeg");
							for (int i = 0; i < byte_array.length; i++) {
								out.write(byte_array[i]);
							}
						}

					}
					e=new Date();
					System.out.println("***************allTime3:"+(e.getTime()-s.getTime())/1000.0);
				}
			}

			if (out != null) {
				out.flush();
				out.close();
			}
			
			e=new Date();
			System.out.println("***************allTime4:"+(e.getTime()-s.getTime())/1000.0);

		} catch (Throwable e) {
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

}
