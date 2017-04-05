package com.ycszh.ssh.service.veh;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.dydj.DydjYwsbspb;
import com.ycszh.ssh.hbm.veh.DbjgZjxxb;
import com.ycszh.ssh.hbm.veh.TemporaryLicense;
import com.ycszh.ssh.hbm.veh.VehPodbsp;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidIn;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidOut;

public interface SlgVehService {
	
	/**
	 * 增加或者更新机动车采集信息
	 * @param dbZjxxb
	 * @param request
	 * @param file1
	 * @param file2
	 * @param file01
	 * @param file02
	 * @return
	 * @throws Exception
	 */
	public Integer insertOrUpdateSlgDrvXxcjb(DbjgZjxxb dbZjxxb, DydjYwsbspb dydjYwsbspb, HttpServletRequest request,File file1,File file2,File file01,File file02,File gpyfile0,File gpyfile1,File gpyfile2,File gpyfile3,File gpyfile4,File gpyfile5,File gpyfile6,File gpyfile7) throws Exception;
	
	/**
	 * 根据流水号查询in表数据
	 * @param lsh
	 * @param request
	 * @return
	 */
	public VehicleTempMidIn getVehicleMidInByLsh(String lsh, HttpServletRequest request) throws Exception;
	
	/**
	 * 根据流水号调用临牌接口
	 * @param lsh
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public TemporaryLicense getTemporaryLicenseByLsh(String lsh, HttpServletRequest request) throws Exception;
	
	/**
	 *  根据流水号查询out表数据
	 * @param lsh
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public VehicleTempMidOut getVehMidOutByLsh(String lsh, HttpServletRequest request) throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getRepositories(String hql) throws Exception;
		
	//受理查询
	public List<DbjgZjxxb> getSlCxList(HttpServletRequest request, int currentpage, String cztype)throws Exception;
	
	//受理详细查询
	public DbjgZjxxb getDbjgZjxxb(HttpServletRequest request,String id)throws Exception;
	
	/**
	 * 查询备案信息
	 * @param request
	 * @param sfzmhm
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getBajl(HttpServletRequest request, String sfzmhm) throws Exception;
	
	/**
	 * 查询配偶代办审批
	 * @return
	 * @throws Exception
	 */
	public List<VehPodbsp> getPoDbspList(HttpServletRequest request, String czsfzhm, String dbsfzhm, String hphm, String hpzl) throws Exception;
	
	/**
	 * 黑名单
	 * @param request
	 * @param sfzmhm 身份证明号码
	 * @param rkyy 入库原因
	 * @param sdlx 锁定类型(1:日期有效止,2:为永久锁定)
	 * @param sdyxqz 锁定有效期止(锁定类型为1时有值)
	 * @param sdzt 锁定状态(1:有效锁定2:无效锁定)
	 * @return
	 * @throws Exception
	 */
	public String getIsBlack(HttpServletRequest request, String sfzmhm, String xm, String rkyy, String sdlx, String sdyxqz, String sdzt) throws Exception;
	
	/**
	 * 黑名单锁定
	 * @param request
	 * @param sfzmhm
	 * @return
	 * @throws Exception
	 */
	public String getIsBlackByFun(HttpServletRequest request, String ywlx, String ywzl, String hphm, String hpzl, String dsrsfzmhm, String dbrsfzmhm) throws Exception;
	
	/**
	 * 受理岗强制预约
	 * @return
	 * @throws Exception
	 */
	public String getIsYuyue(HttpServletRequest request, String xml)throws Exception;
	
	/**
	 * 验车
	 * @param request
	 * @return 1-可以受理  
	 * 		   2-不能受理，提示机动车管理科只能受理审验科验车的流水号数据
	 * 		   3-不能受理，提示只能受理本部门验车的流水号数据,请核对验车部门
	 * 		   0-不能受理，登录异常
	 * @throws Exception
	 */
	public String getIsyanche(HttpServletRequest request) throws Exception;
	
	/**
	 * 验证是否需要指标
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getIsneedzb(HttpServletRequest request) throws Exception;
	
	/**
	 * 验证指标函数
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getIsyanzzb(HttpServletRequest request) throws Exception;
	
	/**
	 * 查询车辆限购数据字典--指标分类
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getClxgSzjdList(HttpServletRequest request) throws Exception;
	
	/**
	 * 查询车辆类型
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getCllxList(String dmlb, String dmz) throws Exception;
	
	/**
	 * 调取统一版流水号信息
	 * @return
	 * @throws Exception
	 */
	public Object[] getTyblshinfo(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取证件信息表lsh
	 * @return
	 * @throws Exception
	 */
	public String getZjxxblsh() throws Exception;
	
	/**
	 * 查询ES_VEH_FLOW信息
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getEsvehflow(HttpServletRequest request) throws Exception;
	
	/**
	 * 查询车辆档案信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getEsVehicle(HttpServletRequest request) throws Exception;
	
	/**
	 * 车辆使用性质
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getClsyxzList(HttpServletRequest request, String xtlb, String dmlb) throws Exception;
	
	/**
	 * 查询限购车型
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getXglxList(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取车辆限购数据字典信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getClxgsjzd2List(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取指标信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getZblist(HttpServletRequest request) throws Exception;
	
	/**
	 * 受理成功弹出提示信息
	 * @param request
	 * @param lsh
	 * @param hphm
	 * @param hpzl
	 * @return
	 * @throws Exception
	 */
	public String getAlterinfo(HttpServletRequest request, String ywlx, String ywyy, String lsh, String hphm, String hpzl) throws Exception;
	
	/**
	 * 查询机动车受理信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public DbjgZjxxb getDbjgzjxxByCondition(HttpServletRequest request) throws Exception;
	
	/**
	 * 验证是否本部门数据
	 * @return 1-是本部门或下级部门  0-不是本部门
	 * @throws Exception
	 */
	public String getIsSamedept(HttpServletRequest request) throws Exception;
	
	/**
	 * 查询机动车受理信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<DbjgZjxxb> getZjxxbList(HttpServletRequest request) throws Exception;
	
	/**
	 * 业务退办
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String tbZbinfo(HttpServletRequest request) throws Exception;
	
	/**
	 * 退办验证
	 * @param request
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public String getTbyanz(HttpServletRequest request) throws Exception;

	/**
	 * 机动车审核验证
	 * @param request
	 * @param shlsh
	 * @param hphm
	 * @param hpzl
	 * @return
	 * @throws Exception
	 */
	public Integer vehShenheCheck(HttpServletRequest request, String shlsh,
			String hphm, String hpzl)throws Exception;

	/**
	 * 机动车业务审核
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public Integer vehShenhe(HttpServletRequest request, String shlsh,
			String shjg, String cjid,String hphm,String hpzl) throws Exception;
	
	/**
	 * 查询车辆类型（'K3%' OR 'K4%'）
	 */
	@SuppressWarnings("unchecked")
	public List getZrCllxList() throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getDtpzByYwlx(HttpServletRequest request) throws Exception;
	
}
