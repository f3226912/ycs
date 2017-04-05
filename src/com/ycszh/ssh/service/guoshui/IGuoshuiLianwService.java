package com.ycszh.ssh.service.guoshui;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.guoshui.FpEsc;
import com.ycszh.ssh.hbm.guoshui.FpXc;
import com.ycszh.ssh.hbm.guoshui.FpXcgzs;

public interface IGuoshuiLianwService {
	
	/**
	 * 查询新车发票
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<FpXc> getFpxcList(HttpServletRequest request) throws Exception;
	
	public Integer getFpxcCount(HttpServletRequest request) throws Exception;
	
	/**
	 * 查询新车购置税
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<FpXcgzs> getFpxcgzsList(HttpServletRequest request) throws Exception;
	
	public Integer getFpxcgzsCount(HttpServletRequest request) throws Exception;
	
	/**
	 * 查询二手车发票
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<FpEsc> getFpEscList(HttpServletRequest request) throws Exception;
	
	public Integer getFpEscCount(HttpServletRequest request) throws Exception;
	
	
	public Integer insertGuoShui(Object obj, HttpServletRequest request) throws Exception;
	
	/**
	 * 根据 发票代码，发票类型 查询单笔
	 * @param dateType 数据增量类型
	 * @param fpdm 发票代码
	 * @param fpbh 发票编号
	 * @param czdm 操作代码
	 * @param czxm 操作姓名
	 * @param czbm 操作部门
	 * @param cznr 操作内容
	 * @param czip 操作ip
	 * @param czrq 操作日期
	 * @return
	 * @throws Exception
	 */
	public Object getGuoShui(String dataType, String fpdm, String fphm) throws Exception;

	/**
	 * 根据 开始，结束时间 查询集合
	 * @param dataType 数据增量类型
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param czxm 操作姓名
	 * @param czbm 操作部门
	 * @param cznr 操作内容
	 * @param czip 操作ip
	 * @param czrq 操作日期
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Integer selGuoShuiList(String dataType, String startDate, String endDate, String czxm, String czbm, String cznr, String czip, String czrq) throws Exception;

}
