package cn.itproject.crm.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {
	public static DecimalFormat decimalFormat = new DecimalFormat("#");
	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String getStringCellValue(Cell cell) {
		String string = null;
		if (cell==null) {
			return null;
		}
		//如果是数字类型并且是日期格式
		if (cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC && HSSFDateUtil.isCellDateFormatted(cell)) {
			Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
			string = dateFormat.format(date);
//			System.out.println("date:"+string);
		}else if (cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC) {				//数字类型
			string = decimalFormat.format(cell.getNumericCellValue());
//			System.out.println("number:"+string);
		}else if (cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN) {				//布尔类型
			boolean v = cell.getBooleanCellValue();
//			System.out.println("boolean:"+v);
			string = String.valueOf(v);
		}else {
//			System.out.println(cell.getStringCellValue());
			string = cell.getStringCellValue();
		}
		
		//去空格
		if (string!=null) {
			string = string.trim();
		}
		return string;
	}
}
