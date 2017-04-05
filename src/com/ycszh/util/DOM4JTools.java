package com.ycszh.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * @包名:com.ycszh.util
 * @文件名:DOM4JTools.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class DOM4JTools {
	// patterm="/projectDescription/projects/project";
	/**
	 * 读取网站设置
	 */
	public static List readXml(String filePath, String patterm) {
		SAXReader reader = new SAXReader();
		
		try {
			Document doc = reader.read(new File(filePath));

			List projects = doc.selectNodes(patterm);

			Iterator it = projects.iterator();
			while (it.hasNext()) {
				Element elm = (Element) it.next();
			
				String name = elm.elementText("Name");
				String owner = elm.elementText("Owner");
				String paramUrl = elm.elementText("ParamUrl");
				String description = elm.elementText("Description");

				
				// System.out.println("Name-------" + name);
				// System.out.println("Owner-------" + owner);
				// System.out.println("ParamUrl-------" + paramUrl);
				// System.out.println("Description-------" + description);

			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public static void writeXml(String filePath) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("Proxys");// 创建根节点

	}

	/**
	 * xml是指xml文档如： <?xml version="1.0" encoding="UTF-8"?> <single>
	 * <parent>parentname</parent> <child>childname</child> </single>
	 * 
	 * @param xml
	 */
	public static void getAllNodes(String xml, String patterm) {
		try {
			Document authtmp = DocumentHelper.parseText(xml);
			List<Element> list = authtmp.selectNodes(patterm);
			for (int j = 0; j < list.size(); j++) {
				Element node = list.get(j);
				nodeByNodes(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void nodeByNodes(Element node) {
		if (node.element("node") != null) {
			String name = node.attributeValue("Name");
			String owner = node.attributeValue("Owner");
			String paramUrl = node.attributeValue("ParamUrl");
			String description = node.attributeValue("Description");

			System.out.println(name);
			for (Iterator i = node.elementIterator("node"); i.hasNext();) {
				Element newNode = (Element) i.next();
				nodeByNodes(newNode);
			}
		} else {
			String id = node.attributeValue("id");
			String name = node.attributeValue("name");
			System.out.print(id + "-------");
			System.out.println(name);
		}
	}

	public static void main(String[] args) {
		DOM4JTools dom = new DOM4JTools();
		DOM4JTools.readXml("G:\\worksplace\\proxy\\Web\\WEB-INF\\proxy.xml",
				"/Proxys/Proxy");
	}
}
