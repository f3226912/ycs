package com.ycszh.ssh.dao.gjgggl;

import java.io.File;

public interface GjclycxxglDao {

	/**
	 * 上传图片
	 * @param zqFile
	 * @param yhFile
	 * @return
	 * @throws Exception
	 */
	public String uploadPic(String lsh, File zqFile, File yhFile) throws Exception;

}
