package com.ycszh.ssh.dao.xyc;

import java.util.Date;

public interface XycVehDao {
	//删除嫌疑车信息,调取存储过程
	public String deleteXyc(String xh,String lrrdm,String lrrmc,String lrrbm,String lrimac,String lrip) throws Exception;
	
	//添加嫌疑车信息
	public String InsertXyc(String yylx,String ywyy,String syr,String sfzmmc,String sfzmhm,String clsbdh,String clxh,String zwppxh,String hphm,String hpzl,String xyyy,String lsh,String lybz,Integer zt,String bz,String zrfs,String lrrdm,String lrrmc,String lrrbm,String lrrbmKj,String lrimac,String lrip,String czrdm,String czrxm,String czrbm,String czmac,String czip) throws Exception;
	
	//修改嫌疑车信息
	public String UpdateXyc(String xh,String yylx,String ywyy,String syr,String sfzmmc,String sfzmhm,String clsbdh,String clxh,String zwppxh,String hphm,String hpzl,String xyyy,String lsh,String lybz,Integer zt,String bz,String zrfs,String czrdm,String czrxm,String czrbm,String czmac,String czip) throws Exception;
}
