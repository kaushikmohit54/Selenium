package qaFramework.support;

	import java.io.File;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.Properties;
	import jxl.CellType;
	import jxl.Sheet;
	import jxl.Workbook;
	import jxl.format.Border;
	import jxl.format.BorderLineStyle;
	import jxl.write.Label;
	import jxl.write.WritableCell;
	import jxl.write.WritableCellFormat;
	import jxl.write.WritableFont;
	import jxl.write.WritableWorkbook;

	public class OfficeCommonFunctions {

		public OfficeCommonFunctions()  throws Exception {
			// TODO Auto-generated constructor stub
		}
		// TODO Auto-generated constructor stub
		ReadPropertyFiles objAP = new ReadPropertyFiles();
		Properties pathProps = objAP.Read_FilePath();
		@SuppressWarnings("unused")
		private static WritableCellFormat courier;

		public void writeResultDataToParticularRow(String[] strTestData,
				String strFilePath, String strSheetName, int intRowInput)
						throws Exception {
			// Read the existing file
			Workbook wb = Workbook.getWorkbook(new File(strFilePath));

			// Create a writable workbook with the same name using the workbook
			// object that has been read from file
			WritableWorkbook wwb = Workbook.createWorkbook(new File(strFilePath),
					wb);

			WritableCellFormat wcf = new WritableCellFormat();
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcf.setWrap(true);

			WritableFont courier10pt = new WritableFont(WritableFont.COURIER, 10);
			// Define the cell format
			courier = new WritableCellFormat(courier10pt);
			wcf.setFont(courier10pt);

			for (int intRec = 0; intRec < strTestData.length; intRec++) {

				Label label1 = new Label(intRec, intRowInput, strTestData[intRec],
						wcf);
				wwb.getSheet(strSheetName).addCell(label1);

			}

			// Write the data
			wwb.write();

			wwb.close();
		}

		public String readInformation(String SheetName, int intRow, int intColumn,
				String FILE_PATH) throws Exception {

			Sheet ws = null;

			// Read the existing file
			Workbook wb = Workbook.getWorkbook(new File(FILE_PATH));
			ws = wb.getSheet(SheetName);

			// Read content of the cell
			String strCellContent = ws.getCell(intColumn - 1, intRow - 1)
					.getContents();
			wb.close();

			return strCellContent;
		}


		public void writeResultData(String[] strTestData,String strFilePath,String strSheetName) throws Exception{
			// Read the existing file
			Workbook wb = Workbook.getWorkbook(new File(strFilePath));

			// Create a writable workbook with the same name using the workbook
			// object that has been read from file
			WritableWorkbook wwb = Workbook.createWorkbook(new File(strFilePath), wb);

			// Total number of rows in the sheet
			int intRowCount = wwb.getSheet(strSheetName).getRows();
			for (int intRow = 1; intRow <= intRowCount; intRow++) {

				// Target cell
				WritableCell cell = wwb.getSheet(strSheetName).getWritableCell(1, intRow);
				if (cell.getType() == CellType.EMPTY) {
					WritableCellFormat wcf = new WritableCellFormat();
					wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
					wcf.setWrap(true);

					WritableFont courier10pt = new WritableFont(
							WritableFont.COURIER, 10);
					// Define the cell format
					courier = new WritableCellFormat(courier10pt);
					wcf.setFont(courier10pt);

					for(int intRec=1;intRec<=strTestData.length;intRec++){

						Label label1 = new Label(intRec, intRow, strTestData[intRec-1], wcf);
						wwb.getSheet(strSheetName).addCell(label1);					

					}

					// Write the data
					wwb.write();
					intRow = intRowCount + 1;

				}


			}

			wwb.close();
		}

		public void WriteResultdb_Excel(String strTCId, String strTO,
				String strResult, String strReason, double dbTimeTaken,
				String FILE_PATH, boolean blnwrite, String strstarttime,
				String strDate, long lDateTime, String strbrowser,
				String strversion, String StrSessionId) throws Exception {

			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"MM/dd/yyyy HH:mm:ss");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			// strDate = date.toString();

			//		ReadEnvironment objReadEnvironment = new ReadEnvironment();
			//		EnvDetails = objReadEnvironment.ReadEnvironment();

			// WRITE RESULTS TO EXCEL UNDER ANY CONDITION WITHOUT CHECKING ANY FLAG.

			// Read the existing file
			Workbook wb = Workbook.getWorkbook(new File(FILE_PATH));

			// Create a writable workbook with the same name using the workbook
			// object that has been read from file
			WritableWorkbook wwb = Workbook.createWorkbook(new File(FILE_PATH), wb);

			// Total number of rows in the sheet
			int intRowCount = wwb.getSheet(0).getRows();
			for (int intRow = 4; intRow <= intRowCount; intRow++) {
				// Target cell
				WritableCell cell = wwb.getSheet(0).getWritableCell(1, intRow);

				if (cell.getType() == CellType.EMPTY) {
					WritableCellFormat wcf = new WritableCellFormat();
					wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
					wcf.setWrap(true);

					WritableFont courier10pt = new WritableFont(
							WritableFont.COURIER, 10);
					// Define the cell format
					courier = new WritableCellFormat(courier10pt);
					wcf.setFont(courier10pt);

					Label label0 = new Label(0, intRow, "", wcf);
					wwb.getSheet(0).addCell(label0);

					// Enter the TC ID
					Label label1 = new Label(1, intRow, strTCId, wcf);
					wwb.getSheet(0).addCell(label1);
					// Enter Test Objective
					Label label2 = new Label(2, intRow, strTO, wcf);
					wwb.getSheet(0).addCell(label2);
					// Enter Result
					Label label3 = new Label(3, intRow, strResult, wcf);
					wwb.getSheet(0).addCell(label3);
					// Enter Reason
					Label label4 = new Label(4, intRow, strReason, wcf);
					wwb.getSheet(0).addCell(label4);
					// Enter Time taken
					String strTime = String.valueOf(dbTimeTaken);
					Label label5 = new Label(5, intRow, strTime, wcf);
					wwb.getSheet(0).addCell(label5);

					String strRes = RunON();
					Label label6 = new Label(6, intRow, strRes, wcf);
					wwb.getSheet(0).addCell(label6);

					Label label7 = new Label(7, intRow, strDate, wcf);
					wwb.getSheet(0).addCell(label7);
					// Write the data
					wwb.write();
					intRow = intRowCount + 1;
				}
			}
			wwb.close();
		}
		
		public String RunON() throws Exception {
			String strResult = "Local System";
			//		ReadEnvironment objReadEnvironment = new ReadEnvironment();
			//		EnvDetails = objReadEnvironment.ReadEnvironment();
			//		if (EnvDetails.getProperty("Server").equals("saucelabs.com")) {
			//			strResult = "Sauce Labs";
			//		} else {
			//			strResult = "Local System";
			//		}
			return strResult;
		}

		public void writeToDB(String strCycleEnviMapID, String strTestCaseID,
				String strBuildID, String strResult, String strComments,
				double dblTimeTaken, String strFilePath, String strExecutedDate,
				String strBrowserName) throws Exception {
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // Start JDBC
			String strSqlURL = "jdbc:sqlserver:://172.16.27.8;";
			String strUsername = "";
			String strPassword = "";
			String strInsertSQL;

			try {
				Class.forName(driverName); // LOAD THE DATABASE DRIVER
				Connection conn = DriverManager.getConnection(strSqlURL,strUsername, strPassword);
				// ESTABLISH THE CONNECTION TO THE DATABASE

				Statement stmt = conn.createStatement(); // GET A STATEMENT FOR THE CONNECTION
				strInsertSQL = "Insert into tblAutomationResults "
						+ "(CycleEnviMapID,TestCaseID,BuildID,Result,Comments,TimeTaken,FilePath,ExecutedDate,BrowserName) values "
						+ "('" + strCycleEnviMapID + "','" + strTestCaseID + "','"
						+ strBuildID + "','" + strResult + "','" + strComments
						+ "','" + dblTimeTaken + "','" + strFilePath + "','"
						+ strExecutedDate + "','" + strBrowserName + "')"; // Insert
				// a
				// record*/

				stmt.executeUpdate(strInsertSQL); // EXECUTE THE SQL QUERY AND STORE IN RESULTS SET
				// CLOSE THE RESULT, STATEMENT AND CONNECTION
				stmt.close();
				conn.close();
				System.out.println("Insert to Table was successful");

			}catch (SQLException se) {//// HANDLE THE SQL EXCEPTION
				System.out.println("SQL Exception:");
				// PRINT TILL ALL THE ECEPTIONS ARE RAISED
				while (se != null) {
					System.out.println("State : " + se.getSQLState());
					System.out.println("Message: " + se.getMessage());
					System.out.println("Error : " + se.getErrorCode());
					se = se.getNextException();
				}

			}catch (Exception e) {//// CATCH THE CLASS EXCEPTION	
				System.out.println(e);
			}
		}


		/**
		 * This function is used calculate the Time taken for test case execution
		 */
		public double TimeTaken(Date dtStartDate) {
			Date dtEndDate = new Date();
			double dtTimeTaken = dtEndDate.getTime() - dtStartDate.getTime();
			dtTimeTaken = dtTimeTaken / 1000;
			return dtTimeTaken;
		}

		public static String readAndVerifyDataExcelNew(String strTestData[][],
				String FILE_PATH) throws Exception {
			String strReason = "";
			Sheet ws = null;
			try {
				// Read the existing file
				Workbook wb = Workbook.getWorkbook(new File(FILE_PATH));
				ws = wb.getSheet(0);

				int intRowCount = wb.getSheet(0).getRows();
				int intColCount = wb.getSheet(0).getColumns();
				for (int intRow = 0; intRow < intRowCount; intRow++) {
					for (int intCol = 0; intCol < intColCount; intCol++) {
						// Read content of the cell


						String strCellContent = ws.getCell(intCol, intRow)
								.getContents();
						if (strCellContent.equals(strTestData[intRow][intCol])) {
							System.out.println("Specified Data "
									+ strTestData[intRow][intCol]
											+ " is displayed in the report");
						} else {
							System.out.println("Specified Data "
									+ strTestData[intRow][intCol]
											+ " is NOT displayed in the report");
							strReason =strReason+ "Specified Data "
									+ strTestData[intRow][intCol]
											+ " is NOT displayed in the report";
						}
					}

				}


				if (intRowCount != strTestData.length) {
					strReason = strReason
							+ " All the necessary Data are not displayed " +
							"in the status snapshot report";
				}
				wb.close();
			} catch (Exception e) {
				strReason = e.toString();

			}

			return strReason;
		}

		public int getRowsCountOfExceSheet(String FILE_PATH, String SheetName) throws Exception {
			Sheet ws = null;

			// Read the existing file
			Workbook wb = Workbook.getWorkbook(new File(FILE_PATH));
			ws = wb.getSheet(SheetName);

			// Read content of the cell
			int rowsCount = ws.getRows();
			wb.close();

			return rowsCount;
		}

		public void writeDataToParticularRow(ArrayList<String> testData,
				String filePath, String sheetName, int rowInput)
						throws Exception {
			// Read the existing file
			Workbook wb = Workbook.getWorkbook(new File(filePath));

			// Create a writable workbook with the same name using the workbook
			// object that has been read from file
			WritableWorkbook wwb = Workbook.createWorkbook(new File(filePath),
					wb);

			WritableCellFormat wcf = new WritableCellFormat();
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcf.setWrap(true);

			WritableFont courier10pt = new WritableFont(WritableFont.COURIER, 10);
			// Define the cell format
			courier = new WritableCellFormat(courier10pt);
			wcf.setFont(courier10pt);

			for (int intRec = 0; intRec < testData.size(); intRec++) {

				Label label1 = new Label(intRec, rowInput, testData.get(intRec),
						wcf);
				wwb.getSheet(sheetName).addCell(label1);

			}

			// Write the data
			wwb.write();

			wwb.close();
		}

		public void clearRows(String filePath, String sheetName)
				throws Exception {
			// Read the existing file
			Workbook wb = Workbook.getWorkbook(new File(filePath));

			// Create a writable workbook with the same name using the workbook
			// object that has been read from file
			WritableWorkbook wwb = Workbook.createWorkbook(new File(filePath),
					wb);

			WritableCellFormat wcf = new WritableCellFormat();
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcf.setWrap(true);

			WritableFont courier10pt = new WritableFont(WritableFont.COURIER, 10);
			// Define the cell format
			courier = new WritableCellFormat(courier10pt);
			wcf.setFont(courier10pt);

			int rows = wwb.getSheet(sheetName).getRows();
			for(int intRec = rows; intRec >= 2; intRec--) {
				wwb.getSheet(sheetName).removeRow(intRec);
			}
			// Write the data
			wwb.write();

			wwb.close();
		}
	}
