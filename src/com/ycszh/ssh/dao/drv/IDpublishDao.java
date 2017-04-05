package com.ycszh.ssh.dao.drv;

import java.util.List;

public interface IDpublishDao {
	
	/**
	 * 获取档案信息集合
	 * @param hm   号码
	 * @param type 类别(1身份证明号码2档案编号)
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List getDaxxInfo(String hm, String type) throws Exception;

}
