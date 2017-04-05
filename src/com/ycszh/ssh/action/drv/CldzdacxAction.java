package com.ycszh.ssh.action.drv;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.service.drv.CldzdacxService;

@SuppressWarnings("serial")
public class CldzdacxAction extends BaseAction {

	private CldzdacxService cldzdacxService;
	private static final Logger log = Logger.getLogger(CldzdacxAction.class);
	private InputStream inputStream;
	@SuppressWarnings("unchecked")
	private List imgList;
	
	public String initImageList() throws Exception{
		log.info("CldzdacxAction method initImageList...............");
		this.setImgList(this.cldzdacxService.getImageInfo(request, "", ""));
		return "imaList";
	}
	
	/**
	 * 显示流程图片
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public void showImage() throws Exception{
		PrintWriter out = null;
		String img = request.getParameter("img");
		/*************************非单机测试以下代码注释去掉*****************************************************/
		byte[] byte_array = this.cldzdacxService.getImageBlob(img);
		/*************************非单机测试以上代码注释去掉*****************************************************/
		
		/*************************非单机测试以下代码注释*****************************************************/
		/*String filepath  = "D:\\images\\cwxx.jpg";
		inputStream = new FileInputStream(new File(filepath));
		if(inputStream == null){
			try {
				out = response.getWriter();
				out.write("error");  
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				out.flush();
				out.close();
			}
		}else{
			byte[] b = new byte[1024];  
			int len = -1;  
			ServletOutputStream sos = null;
			try {
				sos = response.getOutputStream();
				while ((len = inputStream.read(b, 0, 1024)) != -1) { 
					sos.write(b, 0, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(sos != null){
					try {
						sos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}*/
		/*************************非单机测试以上代码注释*****************************************************/
		
		/*************************非单机测试以下代码注释去掉*****************************************************/
		try {
			if(null != byte_array){
				
				//int length = (int) blob.length();
				//byte[] byte_array = blob.getBytes(1, length);
				response.setContentType("image/jpeg");
				ServletOutputStream sos = response.getOutputStream();
				for (int i = 0; i < byte_array.length; i++) {
					sos.write(byte_array[i]);
				}
				sos.close();
				byte_array = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(byte_array != null){
				byte_array = null;
			}
		}
		/*************************非单机测试以上代码注释去掉*****************************************************/
		
	}

	public CldzdacxService getCldzdacxService() {
		return cldzdacxService;
	}

	public void setCldzdacxService(CldzdacxService cldzdacxService) {
		this.cldzdacxService = cldzdacxService;
	}

	public List getImgList() {
		return imgList;
	}

	public void setImgList(List imgList) {
		this.imgList = imgList;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	

}
