package generic;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {

	public static String geXlData(String path,String sheet,int r,int c) {
		String v="";
		try
		{
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			v=wb.getSheet(sheet).getRow(r).getCell(c).toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	public static int geXlRowCount(String path,String sheet) {
		int count=0;
		try
		{
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			count=wb.getSheet(sheet).getLastRowNum();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
