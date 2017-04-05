package com.ycszh.ssh.dao.gjgggl.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import oracle.sql.BLOB;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ycszh.ssh.dao.gjgggl.GjclycxxglDao;

public class GjclycxxglDaoImpl extends HibernateTemplate implements GjclycxxglDao {
	/**
	 * 上传图片
	 * 
	 * @param zqFile
	 * @param yhFile
	 * @return
	 * @throws Exception
	 */
	public String uploadPic(String lsh, File zqFile, File yhFile) throws Exception {
		String result = "";
		if (lsh != null && !lsh.trim().equals("")) {
			if (zqFile == null || yhFile == null) {
				if (zqFile.isFile() && yhFile.isFile()) {
					Session session = getSession();
					if (session != null && session.isConnected()) {
						Connection conn = session.connection();
						conn.setAutoCommit(false);
						Statement st = conn.createStatement();
						st.executeUpdate("update BUS_GGJGCLSB_PHOTO a set a.l_pic=empty_blob(),a.r_pic=empty_blob() where a.lsh='" + lsh + "'");

						ResultSet rs = st.executeQuery("select a.l_pic,a.r_pic from BUS_GGJGCLSB_PHOTO a where a.lsh='" + lsh + "' ");

						OutputStream l_os = null;
						OutputStream r_os = null;

						if (rs.next()) {
							BLOB l_blob = (BLOB) rs.getBlob("l_pic");
							BLOB r_blob = (BLOB) rs.getBlob("l_pic");

							l_os = l_blob.getBinaryOutputStream();
							r_os = r_blob.getBinaryOutputStream();

							BufferedImage l_image = scPic(zqFile);
							BufferedImage r_image = scPic(yhFile);

							ImageIO.write(l_image, "JPG", l_os);
							ImageIO.write(r_image, "JPG", r_os);
						} else {
							result = "-操作失败:无法获取要操作的流水号(" + lsh + ")数据";
						}

						if (l_os != null && r_os != null) {
							l_os.flush();
							l_os.close();
							r_os.flush();
							r_os.close();
						}

					} else {
						result = "-操作失败:获取数据库连接异常";
					}
				} else {
					result = "-操作失败:非法图片文件";
				}

			} else {
				result = "-操作失败:图片文件为空";
			}
		} else {
			result = "-操作失败:流水号为空";
		}
		return result;
	}

	public BufferedImage scPic(File file) throws Exception {

		BufferedImage image = null;

		// 压缩率
		double scale = 1;

		// 文件类型
		String fileType = "";
		if (file != null) {
			fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);
		}

		Image img = ImageIO.read(file);
		// 文件大小(字节)
		long fileSize = file.length();

		if (fileType.equals("bmp") || fileType.equals("png")) {

			// 4M
			if (fileSize > 5000000) {
				scale = 0.7;
			} else if (fileSize > 4000000) {
				scale = 0.8;
			} else if (fileSize > 3000000) {
				scale = 0.9;
			} else if (fileSize > 2000000) {
				scale = 0.9;
			} else if (fileSize > 1500000) {
				scale = 0.9;
			} else if (fileSize > 1000000) {
				scale = 0.9;
			} else {
				scale = 1;
			}

		} else {

			// 4M
			if (fileSize > 5000000) {
				scale = 0.3;
			} else if (fileSize > 4000000) {
				scale = 0.5;
			} else if (fileSize > 3000000) {
				scale = 0.5;
			} else if (fileSize > 2000000) {
				scale = 0.6;
			} else if (fileSize > 1500000) {
				scale = 0.7;
			} else if (fileSize > 1000000) {
				scale = 0.7;
			} else {
				scale = 1;
			}

		}

		int newWidth;
		int newHeight;

		// 原始图片宽度
		newWidth = img.getWidth(null);
		// 原始图片高度
		newHeight = img.getHeight(null);

		// 压缩后图片宽度
		newWidth = (int) (scale * newWidth);
		// 压缩后图片高度
		newHeight = (int) (scale * newHeight);

		image = new BufferedImage((int) newWidth, (int) newHeight,

		BufferedImage.TYPE_INT_RGB);

		// 根据图片尺寸压缩比得到新图的尺寸
		image.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT), 0, 0, null);

		return image;
	}

}
