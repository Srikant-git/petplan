package seleniumUtilities;

import petplan.support.Xls_Reader;

import java.util.Hashtable;

/**
 * Common Method to read data from Excel and it can be extended to class for each Application
 * @author CignitiTeam
 *
 */
public class ConfigureTestData{	

	public static int i = 0;
	public static String globalsheetName = null;
	public static int gTestCaseStartRowNum = 0;
	public static String gTestCaseDesc = null;
	
	/**
	 * Function to get data from xls sheet in 2 dimensional array
	 *
	 * @param testCase - testCase name
	 * @param xls      - Xls_Reader Object
	 * @return 2 dimensional array
	 */
	public static Hashtable<String, String> getDataByRowNo(String testCase, Xls_Reader xls,
									 String sheetName,int intRowNo) {
		i = 0;
//		listofTestCaseDescription.clear();
		globalsheetName = sheetName;
		
//		LOG.info("******getData*******: " + testCase);
		// find the test in xls
		// find number of cols in test
		// number of rows in test
		// put the data in hashtable and put hashtable in object array
		// return object array
		int testCaseStartRowNum = 0;
		// iterate through all rows from the sheet Test Data
		for (int rNum = 1; rNum <= xls.getRowCount(sheetName); rNum++) {
			// to identify testCase starting row number
			if (testCase.equals(xls.getCellData(sheetName, 0, rNum))) {
				testCaseStartRowNum = rNum;
				break;
			}
		}
		gTestCaseStartRowNum = testCaseStartRowNum;

//		LOG.info("Test Starts from row -> " + testCaseStartRowNum);
		// total cols
		int colStartRowNum = testCaseStartRowNum + 1;
		int cols = 0;
		// Get the total number of columns for which test data is present
		while (!xls.getCellData(sheetName, cols, colStartRowNum).equals("")) {
			cols++;
		}

//		LOG.info("Total cols in test -> " + cols);
		// rows
		int rowStartRowNum = testCaseStartRowNum + 2;
		int rows = 0;

		// Get the total number of rows for which test data is present
		while (!xls.getCellData(sheetName, 0, (rowStartRowNum + rows)).equals(
				"")) {
			rows++;
		}

		Object[][] data = new Object[rows][1];
		Hashtable<String, String> table = null;
		//listofTestCaseDescription = new ArrayList<String>();
		// print the test data
		rowStartRowNum=testCaseStartRowNum+2+(intRowNo-1);
		//System.out.println(rowStartRowNum);
		int rNum=rowStartRowNum;
		//for (int rNum = rowStartRowNum; rNum < (rows + rowStartRowNum); rNum++) {
			table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < cols; cNum++) {
				table.put(xls.getCellData(sheetName, cNum, colStartRowNum),
						xls.getCellData(sheetName, cNum, rNum));

				// This is temporarily logic, need to be remove later.
//				if ((xls.getCellData(sheetName, cNum, colStartRowNum).equalsIgnoreCase("TestCase_Description"))) {
//					gTestCaseDesc = xls.getCellData(sheetName, cNum, rNum);
//					listofTestCaseDescription.add(gTestCaseDesc);
//				}
				//System.out.print(xls.getCellData(sheetName, cNum, rNum) + " - ");
			}
			data[rNum - rowStartRowNum][0] = table;
			// LOG.info();
		//}
		return table;// dummy
	}

}
