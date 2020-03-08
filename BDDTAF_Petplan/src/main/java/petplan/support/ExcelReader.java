package petplan.support;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import petplan.datafactory.DataConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

//import com.ctis.accelerators.ActionEngine;

/**
 * Framework level reusable methods to get "row count, column count" and to read data from excel cells
 * @author CignitiTeam
 *
 */

@SuppressWarnings("all")
public class ExcelReader {

//	public ActionEngine ae;
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;

	private Workbook workbook = null;
	private Sheet sheet = null;
	private Row row = null;
	private Column col = null;
	private Cell cell = null;
	private String sheetName;

	public ExcelReader(String path) {
		//LOG.info("Im in Exc elcel readder class");
		//LOG.info(path);
		this.path = path;
		try {
			Path filePath = Paths.get(path);
			String fileExt = getFileExtension(filePath.getFileName().toString());

			if(fileExt.equalsIgnoreCase("xls")) {
				workbook = new HSSFWorkbook(new FileInputStream(new File(path)));
			} else if (fileExt.equalsIgnoreCase("xlsx"))
				workbook = new XSSFWorkbook(new FileInputStream(new File(path)));

			//fis = new FileInputStream(path);
			//workbook = new HSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			//this.sheetName = sheetName;
			//	LOG.info(this.sheetName);
			fis.close();
			//LOG.info("Im in Exc elcel readder class Ending");

		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public static void main(String[] args) throws  Throwable{
		System.out.println("Test main");
		System.out.println(DataConfig.PROJECT_TESTDATA_DIR);
		ExcelReader excelReader = new ExcelReader(DataConfig.PROJECT_TESTDATA_DIR);
		Map<String, String> dataTable = excelReader.getDataArrayBySheet("SpiGeneric","");
		for(Map.Entry<String,String> entry : dataTable.entrySet()){
			System.out.println("Key: " + entry.getKey());
			System.out.println("Value: " + entry.getValue());
		}

	}

	// returns the data from a cell
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";
			int index = workbook.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);

			for (int i = 0; i < row.getLastCellNum(); i++) {
				//LOG.info(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}
			if (col_Num == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum);
			if (row == null)
				return "";
			cell = row.getCell(col_Num);

			if (cell == null)
				return "";
			//LOG.info(cell.getCellType());
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

				String cellText = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText =
							(String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
							cal.get(Calendar.MONTH) + 1 + "/" +
							cellText;

					//LOG.info(cellText);

				}

				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist in xls";
		}
	}

	// returns the row count in a sheet

	/**
	 * @param sheetName
	 * @return
	 */

	// returns the data from a cell
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";

			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
					|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

				String cellText = String.valueOf(cell.getNumericCellValue())
						.replaceFirst(".0", "");

				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR)))
							.substring(2);
					cellText = cal.get(Calendar.MONTH) + 1 + "/"
							+ cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;

					// LOG.info(cellText);

				}

				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum
					+ " does not exist  in xls";
		}
	}

	public int getRowCount(String sheetName) {
//		LOG.info("Im in Exc elcel readder getrow count class");
		int index = workbook.getSheetIndex(sheetName);// (arg0)getSheetIndex
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			//LOG.info("row number is "+number);
			return number;
		}
	}

	public String getData(String rowName, String colName) throws Throwable {
		//	LOG.info("Im in Exc elcel readder get data class");
		int rowNum = -1;
		try {

			int index = workbook.getSheetIndex(sheetName);
			int rowNumber = -1;
			int colNumber = -1;
			boolean flag = false;
			if (index == -1)
				return "";
			sheet = workbook.getSheetAt(index);

			for (int i = 0; i < sheet.getPhysicalNumberOfRows(); ) {
				try {
					row = sheet.getRow(i);

					if (row.getCell(0).toString().equalsIgnoreCase(rowName)) {
						rowNumber = i;

						break;
					}
					i = i + 1;
				} catch (NullPointerException e) {
					continue;
				}
			}
			row = sheet.getRow(rowNumber);
			for (int j = 0; j <= row.getPhysicalNumberOfCells(); j++) {
				try {
					if (row.getCell(j).toString().equalsIgnoreCase(colName)) {
						colNumber = j;
						break;
					}
				} catch (NullPointerException e) {
					continue;
				}
			}
			if (colNumber == -1) {
				//failureReport(rowName,"Unable to find the column with name"+colName);
//				this.reporter.failureReport(rowName, "Unable to find the column with name" + colName);
			}
			row = sheet.getRow(rowNumber);
			if (row == null)
				return "";
			cell = row.getCell(colNumber);
			if (cell == null)
				return "";

			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue().trim();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
					|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

				String cellText = String.valueOf(cell.getNumericCellValue())
						.replaceFirst(".0", "");

				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR)))
							.substring(2);
					cellText = cal.get(Calendar.MONTH) + 1 + "/"
							+ cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;

					// LOG.info(cellText);

				}

				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue()).trim();
		} catch (Exception e) {

			//failureReport("Excel Data Reading", "row "+rowName+" or column "+colName +" does not exist in xls");
//			this.reporter.failureReport("Excel Data Reading", "row " + rowName + " or column " + colName + " does not exist in xls");
			//TestEngineWeb.LOG.error(e.toString());

			return "";
		}
	}

	public String[][] getDataArrayBySheet(String sheetName) throws Throwable {
		int rows = -1;
		int columns = -1;
		String[][] data = null;
		try {

			int index = workbook.getSheetIndex(sheetName);
//			LOG.info(index);
			boolean flag = false;
			if (index == -1) {
//				LOG.info("Unable to find sheet with name  " + sheetName);
//				this.reporter.failureReport("Excel Data Reading", "Unable to find sheet with name  " + sheetName);
				return null;
			}
			sheet = workbook.getSheetAt(index);
			rows = sheet.getPhysicalNumberOfRows();
//			LOG.info("No.of rows" + rows);
			columns = sheet.getRow(0).getLastCellNum();
			data = new String[rows - 1][columns];
//			LOG.info("data1 : " + data.toString());
			for (int i = 1; i < rows; i++) {

				for (int j = 0; j < columns; j++) {
					try {
						row = sheet.getRow(i);
						data[i - 1][j] = row.getCell(j).toString();
						//    LOG.info(data[i-1][j]);
					} catch (NullPointerException e) {
						break;
					}
				}
			}
//			LOG.info("data:" + data);
			return data;
		} catch (Exception e) {
//			this.reporter.failureReport("Excel Data Reading", "Unable to get the data from the sheet " + sheetName);
			//TestEngineWeb.LOG.error(e.toString());
			return null;
		}
	}

	public Map<String, String> getDataArrayBySheet(String sheetName , String testcase) throws Throwable {
		int rows = -1;
		int columns = -1;
		//Object[][] data = null;
		HashMap<String, String> dataTable = null;
		try {

			int index = workbook.getSheetIndex(sheetName);
//			LOG.info(index);
			boolean flag = false;
			if (index == -1) {
//				LOG.info("Unable to find sheet with name  " + sheetName);
//				this.reporter.failureReport("Excel Data Reading", "Unable to find sheet with name  " + sheetName);
				return null;
			}
			sheet = workbook.getSheetAt(index);
			rows = sheet.getPhysicalNumberOfRows();
//			LOG.info("No.of rows" + rows);
			columns = sheet.getRow(0).getLastCellNum();
//			data = new Object[rows][1];
//			LOG.info("data1 : " + data.toString());
			for (int i = 1; i < rows; i++) {
//				Map<Object, Object> dataTable = new HashMap<>();
				dataTable = new HashMap<>();

				for (int j = 0; j < columns; j++) {
					try {
						dataTable.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i).getCell(j).toString());
//						data[i - 1][j] = row.getCell(j).toString();
						//    LOG.info(data[i-1][j]);
					} catch (NullPointerException e) {
						break;
					}
				}
//				data[i][0] = dataTable;
			}
//			LOG.info("data:" + data);
			return dataTable;
		} catch (Exception e) {
//			this.reporter.failureReport("Excel Data Reading", "Unable to get the data from the sheet " + sheetName);
			//TestEngineWeb.LOG.error(e.toString());
			return null;
		}
	}

	private String getFileExtension(String name) {
		int lastIndexOf = name.lastIndexOf(".");
		if (lastIndexOf == -1) {
			return ""; // empty extension
		}
		return name.substring(lastIndexOf + 1);
	}

}

