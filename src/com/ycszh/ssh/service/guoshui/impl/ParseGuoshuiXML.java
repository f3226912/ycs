package com.ycszh.ssh.service.guoshui.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.ycszh.ssh.hbm.guoshui.FpEsc;
import com.ycszh.ssh.hbm.guoshui.FpXc;
import com.ycszh.ssh.hbm.guoshui.FpXcgzs;
import com.ycszh.util.DateUtil;

/**
 * @包名:com.example.ssh.service.impl
 * @文件名:ParseGuoshuiXML.java
 * @作者:wy E-mail:wyong@szjst.com.cn
 * @创建日期:2013-5-15
 * @描述:
 * @版本:V 1.0
 */
public class ParseGuoshuiXML {
	
	/**
	 * 新车发票Xml解析
	 * @param xml 要解析的xml字符串
	 * @param dataType 数据增量同步（XcFpZLStr.CGS.SJYY） 和 单笔实时读取（XCFPStr.CGS.SJYY）
	 * @return 解析出的 新车发票 实体集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<FpXc> xcfpXMLSingleParse (String xml, String dataType) throws Exception{
		FpXc fpxc = null;
		if("wait response timeout!".equals(xml)){
			throw new Exception("国税接口异常，请稍后查询!");
		}
		List<FpXc> fpxcList = new ArrayList<FpXc>();
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		String nodeName = "";
		if("XcFpZLStr.CGS.SJYY".equals(dataType)){
			nodeName = "jdcxcFpxx";
		}else{
			nodeName = "xcFpxx";
		}
		Iterator iterList = root.elementIterator(nodeName);
		if(iterList.hasNext()){
			while(iterList.hasNext()){
				Element element = (Element)iterList.next();
				Iterator iter = element.elementIterator("item");
				while(iter.hasNext()){
					fpxc = new FpXc();
					Element e = (Element)iter.next();
					String fpdm = e.elementTextTrim("fpdm");
					String fphm = e.elementTextTrim("fphm");
					Date kprq = DateUtil.string2Date(e.elementTextTrim("kprq"), "yyyy-MM-dd");
					String mfxm = e.elementTextTrim("ghfmc");
					String mfid = e.elementTextTrim("ghfzjhm");
					String cpxh = e.elementTextTrim("cpxh");
					String cjh = e.elementTextTrim("cjh"); // 单笔 clsbdhcjhm
					String fdjh = e.elementTextTrim("fdjhm");
					String hgzh = e.elementTextTrim("hgzh");
					String jkzmsh = e.elementTextTrim("jkzmsh");
					Long jshj = null;
					String jshjstr = e.elementTextTrim("jshj");
					if(!"".equals(jshjstr) && jshjstr != null && !"null".equals(jshjstr)){
						jshj = Long.parseLong(jshjstr);
					}
					String xhdw = e.elementTextTrim("xhfmc");
					String nsrsbh = e.elementTextTrim("nsrsbh"); // 单笔 xhfsbh
					fpxc.setFpdm(fpdm);
					fpxc.setFphm(fphm);
					fpxc.setKprq(kprq);
					fpxc.setMfxm(mfxm);
					fpxc.setMfid(mfid);
					fpxc.setCpxh(cpxh);
					fpxc.setCjh(cjh);
					fpxc.setFdjh(fdjh);
					fpxc.setHgzh(hgzh);
					fpxc.setJkzmsh(jkzmsh);
					fpxc.setJshj(jshj);
					fpxc.setXhdw(xhdw);
					fpxc.setNsrsbh(nsrsbh);
					
					fpxcList.add(fpxc);
				}
				
			}
		}else{
			return null;
		}
		
		return fpxcList;
	}
	
	/**
	 * 二手车发票Xml解析
	 * @param xml 要解析的xml字符串
	 * @return 解析出的 二手车发票 实体集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<FpEsc> escXMLSingleParse(String xml) throws Exception{
		FpEsc esc = null;
		if("wait response timeout!".equals(xml)){
			throw new Exception("国税接口异常，请稍后查询!");
		}
		List<FpEsc> escList = new ArrayList<FpEsc>();
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		Iterator iterList = root.elementIterator("jdcescFpxx");
		if(iterList.hasNext()){
			while(iterList.hasNext()){
				Element element = (Element)iterList.next();
				System.out.println("element:"+element.getName());
				Iterator iter = element.elementIterator("item");
				while(iter.hasNext()){
					esc = new FpEsc();
					Element e = (Element)iter.next();
					String fpdm = e.elementTextTrim("fpdm");
					String fphm = e.elementTextTrim("fphm");
					Date kprq = DateUtil.string2Date(e.elementTextTrim("kprq"), "yyyy-MM-dd");
					String mfxm = e.elementTextTrim("ghfmc");
					String mfid = e.elementTextTrim("ghfzjhm");
					String mflx = e.elementTextTrim("ghfdh");
					String mfxm1 = e.elementTextTrim("xhfmc");
					String mfid1 = e.elementTextTrim("xhfzjhm");
					String mfl1 = e.elementTextTrim("xhfdh");
					String cph = e.elementTextTrim("cpzh");
					String djzsbh = e.elementTextTrim("djzh");
					String cjh = e.elementTextTrim("cjhm");
					String hjje = e.elementTextTrim("cjhjje");
					String jydw = e.elementTextTrim("jypmdw");
					String escsc = e.elementTextTrim("escsc");
					String nsrsbh = e.elementTextTrim("escscnsrsbh");
					esc.setFpdm(fpdm);
					esc.setFphm(fphm);
					esc.setKprq(kprq);
					esc.setMfxm(mfxm);
					esc.setMfid(mfid);
					esc.setMflx(mflx);
					esc.setMfxm1(mfxm1);
					esc.setMfid1(mfid1);
					esc.setMfl1(mfl1);
					esc.setCph(cph);
					esc.setDjzsbh(djzsbh);
					esc.setCjh(cjh);
					esc.setHjje(hjje);
					esc.setJydw(jydw);
					esc.setEscsc(escsc);
					esc.setNsrsbh(nsrsbh);
					
					escList.add(esc);
				}
				
			}
		}else{
			return null;
		}
		return escList;
	}
	
	/**
	 * 新车购置税Xml解析
	 * @param xml 要解析的xml字符串
	 * @return 解析出的 新车购置税 实体集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<FpXcgzs> xcgzsXMLSingleParse(String xml) throws Exception{
		List<FpXcgzs> cxgzsList = new ArrayList<FpXcgzs>();
		FpXcgzs xcgzs = null;
		if("wait response timeout!".equals("国税接口异常，请稍后查询!")){
			throw new Exception(xml);
		}
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		Iterator iterList = root.elementIterator("jdcxcGzsxx");
		if(iterList.hasNext()){
			while(iterList.hasNext()){
				Element element = (Element)iterList.next();
				System.out.println("element:"+element.getName());
				Iterator iter = element.elementIterator("item");
				while(iter.hasNext()){
					xcgzs = new FpXcgzs();
					Element e = (Element)iter.next();
					String gzszmbh = e.elementTextTrim("wszmdm");
					String nsrmc = e.elementTextTrim("nsrmc");
					String cpxh = e.elementTextTrim("cpxh");
					String cjh = e.elementTextTrim("cjh");
					String fdjh = e.elementTextTrim("fdjhm");
					String msbz = e.elementTextTrim("msbz");
					String fpdm = e.elementTextTrim("fpdm");
					String fphm = e.elementTextTrim("fphm");
					xcgzs.setGzszmbh(gzszmbh);
					xcgzs.setNsrmc(nsrmc);
					xcgzs.setCpxh(cpxh);
					xcgzs.setCjh(cjh);
					xcgzs.setFdjh(fdjh);
					xcgzs.setMsbz(msbz);
					xcgzs.setFpdm(fpdm);
					xcgzs.setFphm(fphm);
					cxgzsList.add(xcgzs);
				}
				
			}
		}else{
			return null;
		}
		return cxgzsList;
	}
	
	/**
	  * xml文件截取（截取出数据xml字符串）
	  * @param xml 要截取的xml字符串
	  * @return 返回数据字符串
	  * @throws DocumentException
	  */
	 @SuppressWarnings("unchecked")
	 public static String jiequXML(String xml) throws DocumentException{
	  //SAXReader saxReader = new SAXReader();
	  //Document document = saxReader.read(new StringReader(xml));
	  StringBuffer xmlBuffer = new StringBuffer("");
//	  Document document = DocumentHelper.parseText(xml);
//	  Element root = document.getRootElement();
//	  // 先判断接口返回值是否正常
//	  
//	  if(root.element("RESULT") != null){
//		  System.out.println("接口异常："+root.element("RESULT").elementTextTrim("ERROR"));
//		  if(root.element("RESULT").elementTextTrim("ERROR").length() > 0){
//			  return root.element("RESULT").elementTextTrim("ERROR");
//		  }
//	  }
//	  
//	  xmlBuffer.append(root.elementTextTrim("processReturn"));
	  xml = "<SOAP-RESULT><RESULT>"+xml+"</RESULT></SOAP-RESULT>";
	  Document document = DocumentHelper.parseText(xml);
	  Element root = document.getRootElement();
	  // 先判断接口返回值是否正常
	  Element RESULT = root.element("RESULT");
	  if(RESULT != null){
		  if(RESULT.element("ERROR") != null){
			  if(RESULT.elementTextTrim("ERROR").length() > 0){
				  System.out.println("接口异常："+RESULT.elementTextTrim("ERROR"));
				  return root.element("RESULT").elementTextTrim("ERROR");
			  }
		  }
	  }
	  
	  xmlBuffer.append(RESULT.element("processResponse").elementTextTrim("processReturn"));
	  
	  
	  // 去掉 没写对的注释
	  return xmlBuffer.toString().replaceAll("<!—查询的返回信息 -->", "");
	 } 
	 
	 /**
	  * 取得head节点下的信息
	  * @param xml
	  * @param nodeName
	  * @return
	  */
	 public static String jiexiXml(String xml,String nodeName) throws DocumentException{
		 Document document = DocumentHelper.parseText(xml);
		 Element root = document.getRootElement();
		 Element head = root.element("head");
		 return head.elementText(nodeName);
	 }

}
