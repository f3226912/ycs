package com.ycszh.ssh.dao.dydj;

import java.io.File;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.dydj.DydjYhUser;

public interface DydjYHUserDao extends BaseDao<DydjYhUser, Integer> {
	public int uploadDydyYhphoto( File file,DydjYhUser yhUser) throws Exception;
	public byte[] getImageBlob(Integer id) throws Exception;
	public int editeBlobByByte(DydjYhUser yhUser, byte[] byte_array) throws Exception;
}
