package com.ycszh.util;

import java.io.File;
import java.util.Date;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.WritableFont;

/**
 * @包名:com.ycszh.util
 * @文件名:JxlTools.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class JxlTools {
	public static void writeExcel(File f) throws Exception {
		jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(f);
		jxl.write.WritableSheet ws = wwb.createSheet("TestSheet1", 0);
		jxl.write.Label labelC = new jxl.write.Label(1, 0, "我爱中国");
		ws.addCell(labelC);
		jxl.write.WritableFont wfc = new jxl.write.WritableFont(
				WritableFont.ARIAL, 20, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.GREEN);
		jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(
				wfc);
		wcfFC.setBackground(jxl.format.Colour.RED);
		labelC = new jxl.write.Label(6, 0, "中国爱我a", wcfFC);
		ws.addCell(labelC);
		// 写入Exel工作表
		wwb.write();
		// 关闭Excel工作薄对象
		wwb.close();
	}

	public static void readExcel(File file, String sheet) throws Exception {

		Workbook wb = Workbook.getWorkbook(file);
		Sheet st = wb.getSheet(sheet);
		int rows = st.getRows();
		int cols = st.getColumns();
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				if (j == 12) {
					Date date = null;

					Cell cell = st.getCell(j, i);
					System.out.println(cell.getType());
					if (cell.getType() == CellType.DATE) {
						DateCell dateCell = (DateCell) cell;
						date = dateCell.getDate();
						System.out.println("1" + date);
					} else if (cell.getType() == CellType.LABEL) {
						date = DateUtil.string2Date((st.getCell(j, i))
								.getContents());
						System.out.println("2" + date);
					} else {
						date = null;

					}

					// }else{
					// System.out.print((st.getCell(j, i)).getContents()+"#^#");
				}

			}
			System.out.println("");
		}

	}

	// 最好写一个这样的main方法来测试一下你的这个class是否写好了。
	public static void main(String[] args) throws Exception {
		// File f = new
		// File("D:\\Tomcat 6.0\\webapps\\amds\\excel\\diamond.xls");

		File f = new File("/diamond.xls");
		// f.createNewFile();
		writeExcel(f);
		// readExcel(f, "sheet1");
	}
}
