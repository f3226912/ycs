package com.ycszh.ssh.service.drv.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.dao.drv.CldzdacxDao;
import com.ycszh.ssh.service.drv.CldzdacxService;

public class CldzdacxServiceImpl implements CldzdacxService{
	
	private CldzdacxDao cldzdacxDao;
	@SuppressWarnings("unchecked")
	public List getImageInfo(HttpServletRequest request, String hphm, String hpzl) throws Exception{
		hphm = request.getParameter("hphm");
		hpzl = request.getParameter("hpzl");
		request.setAttribute("hphm", hphm);
		request.setAttribute("hpzl", hpzl);
		List imgList = null;
		imgList = this.cldzdacxDao.getImageInfo(hphm, hpzl);
		return imgList;
	}
	public byte[] getImageBlob(String tpm) throws Exception {
		byte[] b = null;
		b = this.cldzdacxDao.getImageBlob(tpm);
		return b;
	}
	public CldzdacxDao getCldzdacxDao() {
		return cldzdacxDao;
	}
	public void setCldzdacxDao(CldzdacxDao cldzdacxDao) {
		this.cldzdacxDao = cldzdacxDao;
	}
	
}
