package com.ycszh.servlet;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jason
 * 
 *         该方法能保证主存中的i值是准确的 （每次运行完后 i的最终值是0） 能保证最大开启IO资源数不会大于 IOsize+1
 *         (偶尔出现正常阻塞线线程比被notify唤醒的线程抢先获得线程对象锁)
 */
public class PrintImagBean {
	private volatile int i = 0;
	final int IOsize = 3;

	public void writeImage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		synchronized (this) {
			if (i >= IOsize) {
				try {
					// System.out.println("xh:"+request.getParameter("xh")+"==========wait=========="+i);
					wait();
					// System.out.println("xh:"+request.getParameter("xh")+"==========continue=========="+i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			i++;
			// System.out.println("xh:"+request.getParameter("xh")+"======exist I/O stream===="+i);
		}
		try {
			// 模拟取数据
			Thread.sleep(500);
			// 输出文件
			// FileInputStream fis=new FileInputStream(
			// request.getSession().getServletContext().getRealPath("/pic1.JPG"));
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			BufferedImage bufferedImage = null;
			InputStream in = null;
			String tpid = request.getParameter("tpid") == null ? "" : request
					.getParameter("tpid").trim();
			if (!tpid.equals("")) {
				System.out.println(123123);
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("dbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 100.100.21.33)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 100.100.21.34)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = tpdb)(FAILOVER_MODE =(TYPE = SELECT)(METHOD = BASIC)(RETRIES = 180)(DELAY = 5))))", "VehOffice","off123");
				if (conn != null) {
					String sql = "";
					sql = "select photo from ez_xxd_print_photo t where t.tpid='" + tpid + "'";
					st = conn.createStatement();
					rs = st.executeQuery(sql);
					if (rs.next()) {
						Blob blob = rs.getBlob(1);
						response.setContentType("image/jpeg");
						in = blob.getBinaryStream();
						bufferedImage = ImageIO.read(in);
					}
				}
			}
			response.reset();
			ServletOutputStream sos = response.getOutputStream();
			ImageIO.write(bufferedImage, "JPEG", sos);
			sos.flush();
			response.flushBuffer();
			sos.close();
			in.close();
			if (null != rs) {
				rs.close();
			}
			if (null != st) {
				st.close();
			}
			if (null != conn) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/**
			 * 放于此位置的原因： 不管是否一直等待，等睡眠时间一过，就将线程唤起；防止线程一直阻塞等待页面出现白屏。
			 */
			synchronized (this) {
				i--;
				notify();
				// System.out.println("xh:"+request.getParameter("xh")+"==========notify=========="+i);
			}
		}

	}
}
