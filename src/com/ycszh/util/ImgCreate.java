package com.ycszh.util;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgCreate {
	/**
	 * 生成水印
	 * @param str 字符串
	 * @param filePath  水印图片生成路径
	 * @param width	水印图片宽度
	 * @param height 水印图片高度
	 * @return 
	 */
    public String create(String str, String filePath, int width, int height) {
        /*String fileName = System.currentTimeMillis() + ".jpg";*/
    	String fileName = "syzt.jpg";
        String path = filePath + "/" + fileName;
        File file = new File(path);
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setBackground(Color.WHITE);
        //设置整张图片透明度0-1.0 透明度
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,1f));
        g2.clearRect(0, 0, width, height);
        Font font = new Font("华文行楷", Font.BOLD, 17);
        g2.setFont(font);
        g2.setPaint(Color.RED);
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(str, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString(str, (int) x, (int) baseY);
        try {
            ImageIO.write(bi, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getPath();
    }
}
