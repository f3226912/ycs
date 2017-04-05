package com.ycszh.ssh.service.jsrdzda;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.jsrdzda.DzdaJszDaxxb;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszDaxxbPhoto;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszDaxxbPhotoLog;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszSjzd;
import com.ycszh.ssh.hbm.jsrdzda.EsDrvLicense;

public interface JsrdzdaService {
	/**
	 * 获取所有用户信息
	 * @param request
	 * @return List<User>
	 */
	public List<DzdaJszDaxxb> getJsrUserList(HttpServletRequest request,String temp) throws Exception;
	/**
	 * 解锁（清空）当前用户下的锁定记录
	 * @param request
	 * @throws Exception
	 */
	public  void updateLockByCurrentUser(HttpServletRequest request,String temp)  throws Exception;

	/***
	 * 锁定15条记录
	 */
	public void lockRecoredByUser(HttpServletRequest request,String temp) throws Exception;
	public void lockRecordByCurrentUsr(HttpServletRequest request) throws Exception;
	public List<DzdaJszDaxxb> getLockBySfz(HttpServletRequest request) throws Exception;
	
	/**
	 * 驾校初审/复核
	 * 更新 DZDA_JSZ_DAXXB表、
       DZDA_JSZ_DAXXB_PHOTO 表及日志表 含图片信息更改
       DZDA_JSZ_DAXXB_SH 表 的CJZT=1  退办要更新tbyy字段、更新7项审核人信息及日志表
	 * @param daxxb
	 * @param photo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public  DzdaJszSjzd jsrDaFirstCheck(DzdaJszDaxxb daxxb,List<DzdaJszDaxxbPhoto> photos,HttpServletRequest request,String type) throws Exception;
	
	/**
	 * 驾校补审
                 只能审核上一次审核不通过的图片类型
                 对于上一次审核通过的文件只能查看
      update  DZDA_JSZ_DAXXB cjzt=B  where cjzt='CT'  或者 
      update  DZDA_JSZ_DAXXB cjzt=1  where cjzt='JT'  
      update  DZDA_JSZ_DAXXB_PHOTO zt=1  where  zt='CT' or zt='JT'
	 * @param photo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public  List<DzdaJszDaxxbPhotoLog> jsrDaBuCheck(DzdaJszDaxxb daxxb,List<DzdaJszDaxxbPhoto> photos,HttpServletRequest request) throws Exception;
	/**
	 * 根据cjxh查询某一驾驶人的档案照片
	 * @param cjxh
	 * @return
	 * @throws Exception
	 */
	public List<DzdaJszDaxxbPhoto> getPhotoListByCjxh(String cjxh,String ywlx) throws Exception;
	/**
	 * 根据采集序号和资料类型获取图片列表中对应的序号
	 * @param request
	 * @return
	 */
	public Integer getPhotoXhByInfo(HttpServletRequest request) throws  Exception;
	/**
	 * 根据采集序号图片列表
	 * @param request
	 * @return
	 */
	public List<DzdaJszDaxxbPhoto> getPhotosByxh(String cjxh) throws  Exception;
	/**
	 * 根据序号获取图片信息
	 * @param request
	 * @return
	 */
	public DzdaJszDaxxbPhoto getPhotoInfoByxh(HttpServletRequest request) throws  Exception;
	/**
	 * 根据采集序号获取驾驶人信息
	 * @param cjxh
	 * @return
	 * @throws Exception
	 */
	public DzdaJszDaxxb getJsrCjInfo(String cjxh) throws Exception;
    /**
	 * 影像采集_数据字典表(List)
	 * @param
	 * dmlb ='YWLX' ：业务类型
	 * dmlb ='TBYY_PHOTO' ：图片退办原因
	 * dmlb ='ZJCX' ：准驾车型
	 * 
	 */
	public List<DzdaJszSjzd> getJszSjzdList(String dmlb) throws Exception;
	/**
	 * 用二进制插入blob字段,修改图片信息
	 */
	public int editeBlobByByte(String xh, byte[] byte_array,String table,String type) throws Exception; 
	/**
	 * 根据xh获取图片信息
	 * @param xh
	 * @throws Exception
	 */
	public byte[] getImageLBlob(String xh,String table) throws Exception;
	public byte[] getImageLogBlob(String id) throws Exception;
	/**
	 * 图片加上退办原因水印
	 * @param bazMap
	 * @return
	 * @throws Exception
	 */
	public void createBazPic(String tbyy,String xh,String table) throws Exception;
	/**
	 * 根据采集序号获取驾驶人信息count(*)
	 * @param cjxh
	 * @return
	 * @throws Exception
	 */
	 public int getJsrCjInfoCount(String cjxh) throws Exception; 
	/**
	 * 代办机构_数据字典表
	 * @param
	 *  dmlb ='YWLX' ：业务类型
	 *  dmlb ='TBYY_PHOTO' ：图片退办原因
	 *  dmlb ='ZJCX' ：准驾车型
	 * DzdaJszSjzd
	 */
    public Map<String, String> getJszSjzdDict(String dmlb) throws Exception;
    /**
     * 获取车管需要审核的信息
     * 条件cjzt:1 or B
     */
	public List<DzdaJszDaxxb> getcgCheckList(HttpServletRequest request,String temp) throws Exception;
	/**
	 *  根据邮政编码查询驾驶估值档案信息
	 * @param yzbm
	 * @return
	 * @throws Exception
	 */
	public DzdaJszDaxxb getDzdaJszInfo(String yzbm) throws Exception;
	/**
	 * 获得每一个姓名，已锁定数量，待补审的量
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List getStatList(HttpServletRequest request) throws Exception;
	/***
	 * 驾驶证档案卷宗查询
	 * @return
	 * @throws Exception
	 */
	public EsDrvLicense getJsrBasicInfo(HttpServletRequest request) throws Exception;
}
