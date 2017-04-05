package com.ycszh.ssh.service.drv;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface CldzdacxService {
	public byte[] getImageBlob(String tpm) throws Exception;
	public List getImageInfo(HttpServletRequest request, String hphm, String hpzl) throws Exception;
}
