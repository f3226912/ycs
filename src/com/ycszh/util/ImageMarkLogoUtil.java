package com.ycszh.util;

import java.awt.AlphaComposite; 
import java.awt.Color; 
import java.awt.Font; 
import java.awt.Graphics2D; 
import java.awt.Image; 
import java.awt.RenderingHints; 
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.InputStream; 
import java.io.OutputStream; 

import javax.imageio.ImageIO; 
import javax.swing.ImageIcon; 

import org.junit.Test;

public class ImageMarkLogoUtil {
    // 水印透明度      
    private static float alpha = 1f; 
    // 水印横向位置     
    private static int positionWidth = 230; 
    // 水印纵向位置     
    private static int positionHeight = 105; 
    // 水印文字字体     
    private static Font font = new Font("华文行楷", Font.BOLD, 21); 
    // 水印文字颜色     
    private static Color color = Color.red; 
    
    
    @Test
    public void text(){   	
    	 /*//水印文字底图路径
    	 String srcImgPath = "F:\\zhang.jpg";
    	 //水印文字图片生成图路径
    	 String targerUrl = "F:/zhang_text.jpg";
    	 //旋转水印图底图路径（白底）
    	 String xzImgPath = "F:/gaizhang.jpg";
    	 //旋转水印图生成图路径
    	 String xzTargerUrl = "F:/xuanzuang.jpg"; */    	
    	 //水印文字底图路径
         String srcImgPath = "c:/yc_img/zhang.jpg";
    	 //水印文字图片生成图路径
    	 String targerUrl = "c:/yc_img/zhang_text.jpg";
    	 //旋转水印图底图路径（白底）
    	 String xzImgPath = "c:/yc_img/gaizhang.jpg";
    	 //旋转水印图生成图路径
    	 String xzTargerUrl = "c:/yc_img/xuanzuang.jpg"; 
    	 //水印文字
         String logoText = ""; 
         //给图片生成水印文字
         markImageByText(logoText, srcImgPath, targerUrl,null);
         //将水印图片转动15度
         //水印图片，原图片，生成目标地址，旋转角度
         markImageByIcon(targerUrl, xzImgPath, xzTargerUrl, +15);
    }
    
   
    
    @Test
    public void text11(){
    	//在图片上添加水印文字
    	markImageByText("罗国军", "F:/zhang.jpg", "F:\111.JPG", null);
    }
    /** 
     *  
     * @param alpha  
     *          水印透明度 
     * @param positionWidth  
     *          水印横向位置 
     * @param positionHeight  
     *          水印纵向位置 
     * @param font  
     *          水印文字字体 
     * @param color  
     *          水印文字颜色 
     */
    public static void setImageMarkOptions(float alpha , int positionWidth , int positionHeight ,Font font,Color color){ 
        if(alpha!=0.0f)ImageMarkLogoUtil.alpha = alpha; 
        if(positionWidth!=0)ImageMarkLogoUtil.positionWidth = positionWidth; 
        if(positionHeight!=0)ImageMarkLogoUtil.positionHeight = positionHeight; 
        if(font!=null)ImageMarkLogoUtil.font = font; 
        if(color!=null)ImageMarkLogoUtil.color = color; 
    } 
    
    /** 
     * 给图片添加水印图片、可设置水印图片旋转角度 
     *  
     * @param iconPath 
     *            水印图片路径 
     * @param srcImgPath 
     *            源图片路径 
     * @param targerPath 
     *            目标图片路径 
     * @param degree 
     *            水印图片旋转角度 
     * @return
     * 			  生成图片路径
     */
    public static String markImageByIcon(String iconPath, String srcImgPath, 
            String targerPath, Integer degree) { 
        OutputStream os = null; 
        try { 
            Image srcImg = ImageIO.read(new File(srcImgPath)); 
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), 
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB); 
            // 1、得到画笔对象             
            Graphics2D g = buffImg.createGraphics(); 
            // 2、设置对线段的锯齿状边缘处理             
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null); 
            // 3、设置水印旋转             
            if (null != degree) { 
                g.rotate(Math.toRadians(degree),(double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2); 
            } 
            // 4、水印图片的路径 水印图片一般为gif或者png的，这样可设置透明度             
            ImageIcon imgIcon = new ImageIcon(iconPath); 
  
            // 5、得到Image对象。             
            Image img = imgIcon.getImage(); 
              
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha)); 
            
            // 6、水印图片的位置             
            g.drawImage(img, 30, 30, null); 
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); 
            // 7、释放资源             
            g.dispose(); 
              
            // 8、生成图片             
            os = new FileOutputStream(targerPath); 
            ImageIO.write(buffImg, "JPG", os); 
  
            System.out.println("图片完成添加水印图片"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                if (null != os) 
                    os.close(); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        } 
        return targerPath; 
    } 
    
    /** 
     * 给图片添加水印文字、可设置水印文字的旋转角度 
     * @param logoText 		
     * 				 水印文字
     * @param srcImgPath
     * 				 源图片路径 
     * @param targerPath 
     * 				目标图片路径
     * @param degree 
     * 				文字旋转角度
     * @return
     * 				生成图片路径
     */
    public static String markImageByText(String logoText, String srcImgPath, 
            String targerPath, Integer degree) { 
        InputStream is = null; 
        OutputStream os = null; 
        try { 
            // 1、源图片             
            Image srcImg = ImageIO.read(new File(srcImgPath)); 
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB); 
  
            // 2、得到画笔对象             
            Graphics2D g = buffImg.createGraphics(); 
            // 3、设置对线段的锯齿状边缘处理             
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null); 
            // 4、设置水印旋转             
            if (null != degree) { 
                g.rotate(Math.toRadians(degree),(double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2); 
            } 
            // 5、设置水印文字颜色             
            g.setColor(color); 
            // 6、设置水印文字Font             
            g.setFont(font); 
            // 7、设置水印文字透明度             
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.8f)); 
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)  
            
            FontRenderContext context = g.getFontRenderContext();
            Rectangle2D bounds = font.getStringBounds(logoText, context);
            double x = (positionWidth - bounds.getWidth()) / 2;
            double y = (positionHeight - bounds.getHeight()) / 2;
            double ascent = -bounds.getY();
            double baseY = y + ascent;
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.drawString(logoText, (int) x, (int) baseY);
            
            /*
            g.drawString(logoText, positionWidth, positionHeight); */
            // 9、释放资源             
            g.dispose(); 
            // 10、生成图片             
            os = new FileOutputStream(targerPath); 
            ImageIO.write(buffImg, "JPG", os); 
            System.out.println("图片完成添加水印文字");            
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                if (null != is) 
                    is.close(); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
            try { 
                if (null != os) 
                    os.close(); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            } 
        } 
        return targerPath;
    } 
}
