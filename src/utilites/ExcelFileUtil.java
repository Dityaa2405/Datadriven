package utilites;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
XSSFWorkbook wb;
//constructor for reading excel path
public ExcelFileUtil(String Excelpath) throws Throwable
{
	FileInputStream fi = new FileInputStream(Excelpath);
	wb = new XSSFWorkbook(fi);
	
}
//method for counting no of rows in a sheet
public int rowCount(String sheetName)
{
	return wb.getSheet(sheetName).getLastRowNum();
	
}

//methods for reading data from cells
public String getCellData(String sheetname,int row,int column) 
{
	String data = "";
	if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
	{
		int cellData = (int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
		data = String.valueOf(cellData);
			}
else 
{
	data =wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
}	
	return data;
}
//method for writing status into another workbook
public void setCelldata(String sheetname,int row, int column, String Status, String writeExcel)throws Throwable
{
	//get sheet from wb
	XSSFSheet ws = wb.getSheet(sheetname);
	//get row from sheet
	XSSFRow rowNum = ws.getRow(row); 
	//create cell in row
	XSSFCell cell = rowNum.createCell(column);
	//write status in cell
	cell.setCellValue(Status);
	if (Status.equalsIgnoreCase("pass")) {
		
	XSSFCellStyle style = wb.createCellStyle();
	XSSFFont font = wb.createFont();
	font.setColor(IndexedColors.GREEN.getIndex());
	font.setBold(true);
	style.setFont(font);
	rowNum.getCell(column).setCellStyle(style);
	
	}
	else if(Status.equalsIgnoreCase("Fail"))
	{
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setColor(IndexedColors.RED .getIndex());
		font.setBold(true);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
			
	}

	else if(Status.equalsIgnoreCase("blocked"))
	{
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setColor(IndexedColors.BLUE .getIndex());
		font.setBold(true);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
			
	}
FileOutputStream fo = new FileOutputStream(writeExcel);
wb.write(fo);
		
}
}