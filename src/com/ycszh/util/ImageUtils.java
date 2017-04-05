package com.ycszh.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.struts2.views.freemarker.tags.FileModel;

import net.spy.memcached.protocol.GetCallbackWrapper;

public final class ImageUtils {
	/**
	 * 图片水印
	 * 
	 * @param pressImg
	 *            水印图片
	 * @param targetImg
	 *            目标图片
	 * @param x
	 *            修正值 默认在中间
	 * @param y
	 *            修正值 默认在中间
	 * @param alpha
	 *            透明度
	 */
	public final static void pressImage(String pressImg, String targetImg,
			int x, int y, float alpha, float degree) {
		try {
			File img = new File(targetImg);
			Image src = ImageIO.read(img);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			g.rotate(Math.toRadians(degree), (double) wideth / 2,
					(double) height / 2);
			// 水印文件
			Image src_biao = ImageIO.read(new File(pressImg));
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			g.drawImage(src_biao, wideth - wideth_biao - x, height
					- height_biao - y, wideth_biao, height_biao, null);
			// 水印文件结束
			g.dispose();
			ImageIO.write(image, "jpg", img);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图片水印
	 * 
	 * @param pressImg
	 *            水印图片
	 * @param targetImg
	 *            目标图片
	 * @param x
	 *            修正值 默认在中间
	 * @param y
	 *            修正值 默认在中间
	 * @param alpha
	 *            透明度
	 */
	public final static File pressImageTest(File img, int x,
			int y, float alpha, float degree) {
		try {
			//正式环境
			//String pressImg="D:/Program Files (x86)/IBM/WebSphere/AppServer/profiles/AppSrv01/installedApps/WIN-NBC6A8FCJBFNode01Cell/ycszhyw.ear/ycszhyw.war/images/tuzhan.gif";
			//测试环境
			String pressImg="D:/workspace/ycszhyw/WebRoot/images/tuzhan.gif";
			BufferedImage src = ImageIO.read(img);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			// BufferedImage image = new BufferedImage(wideth, height,
			// BufferedImage.TYPE_INT_RGB);
			Graphics2D g = (Graphics2D) src.getGraphics();
			// g.drawImage(src, 0, 0, wideth, height, null);

			// g.rotate(Math.toRadians(degree),(double) wideth / 2, (double)
			// height / 2);
			// 水印文件
			Image src_biao = ImageIO.read(new File(pressImg));
			int wideth_biao = 150;
			int height_biao = 150;
			// g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
			// alpha));
			g.drawImage(src_biao, wideth - wideth_biao - x, height
					- height_biao - y, wideth_biao, height_biao, null);
			// 水印文件结束
			g.dispose();

			ImageIO.write(src, "png", img);
			return img;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param targetImg
	 *            目标图片
	 * @param fontName
	 *            字体名称
	 * @param fontStyle
	 *            字体样式
	 * @param color
	 *            字体颜色
	 * @param fontSize
	 *            字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度
	 */
	public static File pressText2(String pressText, File picPath,
			String fontName, int fontStyle, Color color, int fontSize, int x,
			int y, float alpha) {
		try {
			File img=ImageUtils.pressImageTest(picPath,100,200,0,0);
			BufferedImage src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			Graphics2D g = (Graphics2D) src.getGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.drawString(pressText, (width - (getLength(pressText) * fontSize))
					/ 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write(src, "jpg", img);
			return img;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public final static void pressImage(File img, File file02) {
		try {
			Image src = ImageIO.read(img);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			g.rotate(Math.toRadians(-22.5), (double) wideth / 2,
					(double) height / 2);
			// 水印文件
			Image src_biao = ImageIO.read(file02);
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					0.1f));
			
			g.drawImage(src_biao, wideth - wideth_biao - 0, height
					- height_biao - 0, wideth_biao, height_biao, null);
			// 水印文件结束
			g.dispose();
			ImageIO.write(image, "jpg", img);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param targetImg
	 *            目标图片
	 * @param fontName
	 *            字体名称
	 * @param fontStyle
	 *            字体样式
	 * @param color
	 *            字体颜色
	 * @param fontSize
	 *            字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度
	 */
	public static void pressText(String pressText, String targetImg,
			String fontName, int fontStyle, Color color, int fontSize, int x,
			int y, float alpha) {
		try {
			File img = new File(targetImg);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			g.drawString(pressText, (width - (getLength(pressText) * fontSize))
					/ 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write(image, "jpg", img);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 缩放
	 * 
	 * @param filePath
	 *            图片路径
	 * @param height
	 *            高度
	 * @param width
	 *            宽度
	 * @param bb
	 *            比例不对时是否需要补白
	 */
	@SuppressWarnings("static-access")
	public static void resize(String filePath, int height, int width, boolean bb) {
		try {
			double ratio = 0.0; // 缩放比例
			File f = new File(filePath);
			BufferedImage bi = ImageIO.read(f);
			Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue()
							/ bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform
						.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {
				BufferedImage image = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				g.dispose();
				itemp = image;
			}
			ImageIO.write((BufferedImage) itemp, "jpg", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		// String s = System.getProperty("user.dir");
		// System.out.println(s + "//WebRoot//images//shuiyin.png");
		// File f = new File(s + "//WebRoot//images//shuiyin.png");
		// System.out.println(f.lastModified());
		// System.out.println(new
		// Date(Long.valueOf("1371541746320")).toLocaleString());
		pressImage("C://0.png", "C://1.jpg", 0, 0, 0.1f, -22.5f);
		// pressText("我是文字水印", "G://imgtest//test1.jpg", "黑体", 36, Color.white,
		// 80, 0, 0, 0.3f);
		// resize("C://0.png", 100, 200, true);
	}

	public static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}
}
