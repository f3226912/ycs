package com.ycszh.ssh.dao.drv;

import java.io.File;

public interface SlgDrvFileUploadDao{
	
	public String uploadFile(File file) throws Exception;
	
	public String uploadFileBybyte(byte[] bytes) throws Exception;

}
