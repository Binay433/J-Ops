package com.xchanging.ops.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import com.xchanging.ops.model.OpsDocument;
import com.xchanging.ops.model.PublicHoliday;
import com.xchanging.ops.model.RelDoc;
import com.xchanging.ops.model.User;
import com.xchanging.ops.model.UserProfile;
import com.xchanging.ops.service.DocumentService;
import com.xchanging.ops.service.PublicHolidayService;
import com.xchanging.ops.service.ServicePointsService;
import com.xchanging.ops.service.UserService;

public class CommonUtils {

	private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	static UserService userService;
	static PublicHolidayService holidayService;
	static ServicePointsService servicePointservice;
	
	
	public UserService getUserService() {
		return userService;
	}

	public static void setUserService(UserService userServ) {
		userService = userServ;
	}
	

	public static ServicePointsService getServicePointservice() {
		return servicePointservice;
	}

	public static void setServicePointservice(
			ServicePointsService servicePointservice) {
		CommonUtils.servicePointservice = servicePointservice;
	}

	public static PublicHolidayService getHolidayService() {
		return holidayService;
	}

	public static void setHolidayService(PublicHolidayService holidayService) {
		CommonUtils.holidayService = holidayService;
	}

	public static User getCurrentUser() {
		User user = null;
		try {
			UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			if(userService!=null){
				user = userService.findByLoginId(userDetail.getUsername());	
				if(user!=null){
					Integer points =servicePointservice.findUserServicePoints(user);
					user.setServicepoint(points);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public static OpsDocument saveDocument(MultipartFile multipartFile, DocumentService documentService,
			OpsDocument document) throws IOException {
		document.setName(multipartFile.getOriginalFilename());
		document.setType(getFileExtension(multipartFile));
		document.setContent(multipartFile.getBytes());
		documentService.save(document);
		return document;
	}
	
	public static RelDoc decorateDoc(MultipartFile multipartFile, 
			RelDoc document,String location) throws IOException {
		UUID uuid = UUID.randomUUID();
		document.setLocation(location+uuid+"."+getFileExtension(multipartFile));
		document.setName(multipartFile.getOriginalFilename());
		document.setExt(getFileExtension(multipartFile));
		return document;
	}

	public static OpsDocument getBasicDocument(String type) {
		OpsDocument document = new OpsDocument();
		document.setUser(getCurrentUser());
		document.setParentType(type);
		return document;

	}

	private static String getFileExtension(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	public static boolean isLoggedIn() {
		boolean isLoggedIn = false;
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (null != obj && !"anonymousUser".equals(obj.toString())) {
			isLoggedIn = true;
		}
		return isLoggedIn;
	}

	public static boolean isUser(List<UserProfile> profiles) {
		for(UserProfile profile :profiles){
			if("USER".equalsIgnoreCase(profile.getType())){
				return true;
			}
		}
		return false;
	}

	public static boolean isAdmin(Set<UserProfile> profiles) {
		for(UserProfile profile :profiles){
			if("ADMIN".equalsIgnoreCase(profile.getType())){
				return true;
			}
		}
		return false;
	}

	public static boolean isOps(Set<UserProfile> profiles) {
		for(UserProfile profile :profiles){
			if("OPS".equalsIgnoreCase(profile.getType())){
				return true;
			}
		}
		return false;
	}

	public static String readFile(File file) {
		BufferedReader br = null;
		String content = "";
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(file));
			while ((sCurrentLine = br.readLine()) != null) {
				content += sCurrentLine;
			}

		} catch (IOException e) {
			e.printStackTrace();
			logger.info("fillUpMailTemplate() error" + e);
			logger.error("fillUpMailTemplate() error" + e.getMessage());
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return content;
	}

	public static String getYesterday() {
		String daySTR = "";
		String yearSTR = "";
		String monthSTR = "";

		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate() - 1;
		if (day < 10) {
			daySTR = "0" + day;
		} else {
			daySTR += day;
		}
		if (month < 10) {
			monthSTR = "0" + month;
		} else {
			monthSTR += month;
		}
		yearSTR += year;
		return yearSTR + "-" + monthSTR + "-" + daySTR;
	}

	public static void main(String arg[]) {
		// geDatesInString(new Date(), new Date(116,02,15));
		// stringToDate("20160509");
		// calcDaysTilToday(new Date());
		// calculateSLARequired(new Date(),100,100,98);
		// createSpreadSheet();
		monthDayNames(2016, 2);
	}

	public static List<String> geDatesInString(Date from, Date to) {

		from = dateWithoutTime(from);
		to = dateWithoutTime(to);
		java.util.Calendar aCalendar;
		Date testDate;

		aCalendar = java.util.Calendar.getInstance();
		aCalendar.setTime(from);
		testDate = from;
		List<String> resultList = new ArrayList<String>();

		while ((testDate.before(to) || testDate.equals(to))) {
			resultList.add(returnDateToString(testDate));
			aCalendar.add(java.util.Calendar.DATE, 1);
			testDate = aCalendar.getTime();
		}
		return resultList;
	}

	
	public static List<String> getEnglishDates(List<String> dates){
		List<String> result = new ArrayList<String>();
		for(String date: dates){
			String yr = date.substring(0,4);
			String mon=date.substring(5,7);
			String day=date.substring(8,10);
			result.add(day+"-"+monthArray()[Integer.parseInt(mon)-1]+"-"+yr);
		}
		return result;
	}
	
	public static String getEnglishDate(Date date){
			String strDate =returnString(date);
			String yr = strDate.substring(0,4);
			String mon=strDate.substring(4,6);
			String day=strDate.substring(6,8);
		return day+"-"+monthArray()[Integer.parseInt(mon)-1]+"-"+yr;
	}
	
	
	public static String returnString(Date date) {
		String year = "" + (date.getYear() + 1900);
		String month = "" + (date.getMonth() + 1);
		if (month.length() < 2) {
			month = "0" + month;
		}
		String day = "" + (date.getDate());
		if (day.length() < 2) {
			day = "0" + day;
		}
		return year + month + day;
	}

	public static String returnDateToString(Date date) {
		String returnString = "NA";
		if (date != null) {
			String year = "" + (date.getYear() + 1900);
			String month = "" + (date.getMonth() + 1);
			if (month.length() < 2) {
				month = "0" + month;
			}
			String day = "" + (date.getDate());
			if (day.length() < 2) {
				day = "0" + day;
			}
			returnString = year + "-" + month + "-" + day;
		}
		return returnString;
	}

	public static Date dateWithoutTime(Date date) {
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDate();
		return new Date(year, month, day);
	}

	public static List<String> getWeekDays(Date date) {
		Calendar aCalendar;
		aCalendar = java.util.Calendar.getInstance();
		aCalendar.setTime(date);
		aCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date monday = aCalendar.getTime();
		aCalendar.add(java.util.Calendar.DATE, 6);
		Date sunday = aCalendar.getTime();
		return geDatesInString(monday, sunday);
	}

	public static Date getCustomDate(Date date, int var) {
		Calendar aCalendar;
		aCalendar = java.util.Calendar.getInstance();
		aCalendar.setTime(date);
		aCalendar.add(java.util.Calendar.DATE, var);
		return aCalendar.getTime();

	}

	public static Date stringToDate(String dateInString) {
		dateInString = dateInString.replaceAll("-", "");
		int year = Integer.parseInt(dateInString.substring(0, 4));
		int mon = Integer.parseInt(dateInString.substring(4, 6));
		int day = Integer.parseInt(dateInString.substring(6, 8));
		return new Date(year - 1900, mon - 1, day);
	}

	public static String[] monthArray() {
		String[] month = new String[12];

		month[0] = "JAN";
		month[1] = "FEB";
		month[2] = "MAR";
		month[3] = "APR";
		month[4] = "MAY";
		month[5] = "JUN";
		month[6] = "JUL";
		month[7] = "AUG";
		month[8] = "SEP";
		month[9] = "OCT";
		month[10] = "NOV";
		month[11] = "DEC";
		return month;
	}

	public static String[] lastThreeDays() {
		String[] days = new String[3];
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DATE, -1);
		Date day1 = cal.getTime();
		cal.add(Calendar.DATE, -1);
		Date day2 = cal.getTime();
		cal.add(Calendar.DATE, -1);
		Date day3 = cal.getTime();
		days[0] = returnDateToString(day1);
		days[1] = returnDateToString(day2);
		days[2] = returnDateToString(day3);

		return days;
	}

	private static int calcWorkDays(Date date) {
		int year = date.getYear() + 1900;
		int month = date.getMonth();
		Calendar cal = new GregorianCalendar(year, month, 1);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		do {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
				// System.out.println(cal.get(Calendar.DAY_OF_MONTH));
				days = days - 1;
			}
			cal.add(Calendar.DAY_OF_YEAR, 1);
		} while (cal.get(Calendar.MONTH) == month);
		return days;
	}

	private static int calcDaysTilToday(Date date) {
		int year = date.getYear() + 1900;
		int month = date.getMonth();
		int today = date.getDate();
		Calendar cal = new GregorianCalendar(year, month, 1);
		int days = 0;
		
		do {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (day != Calendar.SATURDAY && day != Calendar.SUNDAY) {
				// System.out.print(cal.get(Calendar.DAY_OF_WEEK));
				days = days + 1;
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		} while (cal.get(Calendar.DAY_OF_MONTH) < today);
		return days;
	}

	
	private static boolean isPublicHoliday(List<PublicHoliday> pubHolidays,int month, int day){
		for(PublicHoliday holiday :pubHolidays){
			if(holiday.getMonth()==month && holiday.getDay()==day){
				return true;
			}
		}
		return false;
	}
	
	
	public static List<String[]> monthDayNames(int year, int month) {
		List<PublicHoliday> holidays = holidayService.findByYear(year);
		List<String[]> dayNames = new ArrayList<String[]>();
		String[] dayName;
		Calendar cal = null;
		int day = 1;
		do {
			cal = new GregorianCalendar(year, month, day);
			if (!sayDayName(cal.getTime()).equalsIgnoreCase("SATURDAY")
					&& !sayDayName(cal.getTime()).equalsIgnoreCase("SUNDAY")) {
				//if(!isPublicHoliday(holidays,month+1,day)){
					dayName = new String[2];
					dayName[0] = "" + day;
					dayName[1] = sayDayName(cal.getTime());
					dayNames.add(dayName);					
				//}
			}
			day++;
		} while (day <= cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
		return dayNames;
	}

	private static String sayDayName(Date d) {
		DateFormat f = new SimpleDateFormat("EEEE");
		try {
			return f.format(d);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static Double[] calculateSLARequired(Date date, double totalRecords, double passed, double sla, 
			double current,Double[] goodDayVals) {
		double balancePercentRequired = sla;
		double slaForecast=0;
		if(totalRecords >0.0){
		int maxWorkDays = calcWorkDays(date);
		int tilToday = calcDaysTilToday(date);
		double requiredPerDay = totalRecords / tilToday;
		double expectedTotRecords = requiredPerDay * maxWorkDays;
		double expectedTotPass = (expectedTotRecords * sla) / 100;
		double balancePassRequired = expectedTotPass - passed;
		double balanceRecord = expectedTotRecords - totalRecords;
		balancePercentRequired = (balancePassRequired / balanceRecord) * 100;
	
		slaForecast = ((passed+(goodDayVals[1] *(maxWorkDays-tilToday)))/(totalRecords+(goodDayVals[0] *(maxWorkDays-tilToday)))) * 100;
		}
		
		Double[] results = new Double[]{balancePercentRequired,slaForecast};
		return results;
	}

	public static void createSpreadSheet(List<List<String>> trans, List<List<String>> trans2) {
		WritableWorkbook wworkbook = null;
		try {
			//DecimalFormat df = new DecimalFormat("###.##");
			String doc_location=System.getProperty("DOC_LOCATION");
			String file_name=System.getProperty("sla_attachment");
			wworkbook = Workbook.createWorkbook(new File(doc_location+file_name));
			WritableSheet master = wworkbook.createSheet("Master", 0);
			WritableCellFormat cellFormat = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 10));
			cellFormat.setBorder(Border.ALL,BorderLineStyle.DOUBLE, Colour.BLACK);
			cellFormat.setBackground(Colour.WHITE);
			cellFormat.setAlignment(Alignment.CENTRE);
			WritableCellFormat cellFormat2 = cellFormat;			
			cellFormat2.setWrap(true);
			master.setColumnView(5, 25);
			master.setColumnView(1, 25);
			master.setColumnView(2, 60);
			// decide color
			master.addCell(new Label(0, 0, "S.No", cellFormat));
			master.addCell(new Label(1, 0, "Name", cellFormat));
			master.addCell(new Label(2, 0, "Description", cellFormat2));
			master.addCell(new Label(3, 0, "Target", cellFormat));
			master.addCell(new Label(4, 0, "Current Month", cellFormat));
			master.addCell(new Label(5, 0, "Avg SLA required for remaining month to go Green", cellFormat2));
			master.addCell(new Label(6, 0, "SLA Forecast", cellFormat2));
			/*master.addCell(new Label(6, 0, "day1", cellFormat));
			master.addCell(new Label(7, 0, "day2", cellFormat));*/

			// change

			Iterator<List<String>> entries = trans.iterator();
			int count = 0;
			WritableCellFormat cellFormatrow = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 10));
			cellFormatrow.setBorder(Border.ALL,BorderLineStyle.DOUBLE, Colour.BLACK);
			cellFormatrow.setAlignment(Alignment.CENTRE);
			WritableCellFormat cellFormat3 = cellFormatrow;

			cellFormat3.setWrap(true);
			while (entries.hasNext()) {
				count++;
				List<String> record = entries.next();

			
				master.addCell(new Label(0, count, record.get(0), cellFormatrow));	
				master.addCell(new Label(1, count, record.get(1), cellFormat3));
				master.addCell(new Label(2, count, record.get(2), cellFormatrow));
				master.addCell(new Label(3, count, record.get(3), cellFormatrow));
				if(!("W").equals(record.get(6)) && !record.get(4).equals("NA") && record.size() > 8){
				master.addCell(new Label(4, count, record.get(4), decideCurrentCellColor(record.get(8))));
				}else if(!("W").equals(record.get(6)) && !record.get(4).equals("NA") && record.size() >= 8){
					master.addCell(new Label(4, count, record.get(4), decideCurrentCellColor(record.get(7))));
				}else{
					master.addCell(new Label(4, count, "NA", cellFormatrow));
				}
				//master.addCell(new Label(3, count, record.get(3), decideCurrentCellColor(record.get(7))));
				//master.addCell(new Label(4, count, record.get(4), decideCurrentCellColor(record.get(8))));
				master.addCell(new Label(5, count, record.get(5), cellFormatrow));
				if(!("W").equals(record.get(6)) && record.size() > 7){
				String color ="W";	
					if(!"0".equals(record.get(6))){
						color=record.get(7);
					}
				master.addCell(new Label(6, count, record.get(6), decideCurrentCellColor(color) ) );
				}else{
					master.addCell(new Label(6, count, "NA", cellFormatrow));
				}
				//master.addCell(new Label(7, count, record.get(7), decideCurrentCellColor(record.get(9))));*/

			}
			
			
			WritableSheet month = wworkbook.createSheet("Month", 1);
			WritableCellFormat cellFormat1 = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 10));
			cellFormat1.setBackground(Colour.LIGHT_TURQUOISE);
			WritableCellFormat cellFormat21 =cellFormat1;
			cellFormat21.setBackground(null);
			
			WritableCellFormat cellFormat211 = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 10));
			
			cellFormat211.setBorder(Border.ALL,BorderLineStyle.DOUBLE, Colour.BLACK);
			cellFormat211.setAlignment(Alignment.CENTRE);
			cellFormat1=cellFormat211;
			Iterator<List<String>> xlsEntries = trans2.iterator();
			int rowCount=0;
			while (xlsEntries.hasNext()) {				
				List<String> xlsRecord = xlsEntries.next();
				for (int i=0;i<xlsRecord.size();i++){
					if(i==0){
						month.setColumnView(i, 30);	
						month.addCell(new Label(i, rowCount, xlsRecord.get(i), cellFormat211));
					}else{
						month.setColumnView(i, 15);	
						if(xlsRecord.get(i).contains("(") && xlsRecord.get(i).contains(")")){
							String cellColor =xlsRecord.get(i).substring(xlsRecord.get(i).indexOf("(")+1, xlsRecord.get(i).indexOf(")"));
							month.addCell(new Label(i, rowCount, xlsRecord.get(i).substring(0, xlsRecord.get(i).indexOf("(")), decideCurrentCellColor(cellColor)));																					
						}else{
							month.addCell(new Label(i, rowCount, xlsRecord.get(i), cellFormat211));																					
						}

					}/*else if ((i+2)<xlsRecord.size() && xlsRecord.get(0).contains("-P")){
						month.setColumnView(i, 15);	
						xlsRecord.get(1);//AMBER
						xlsRecord.get(2);//GREEN
						
						month.addCell(new Label(i, rowCount, xlsRecord.get(i+2), decidedFinalCellColor(xlsRecord.get(i),xlsRecord.get(i+1),xlsRecord.get(i+2))));
					}else{
						month.setColumnView(i, 15);	
						if(xlsRecord.get(i).contains("(") && xlsRecord.get(i).contains(")")){
							String cellColor =xlsRecord.get(i).substring(xlsRecord.get(i).indexOf("(")+1, xlsRecord.get(i).indexOf(")"));
							month.addCell(new Label(i, rowCount, xlsRecord.get(i).substring(0, xlsRecord.get(i).indexOf("(")), decideCurrentCellColor(cellColor)));																					
						}else{
							month.addCell(new Label(i, rowCount, xlsRecord.get(i), cellFormat211));																					
						}

					}*/
					
				}
				rowCount++;
			}
			
			

			wworkbook.write();
			wworkbook.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			wworkbook = null;
		}

		/*
		 * Workbook workbook = Workbook.getWorkbook(new File("output.xls"));
		 * Sheet sheet = workbook.getSheet(0); Cell cell1 = sheet.getCell(0, 2);
		 * System.out.println(cell1.getContents()); Cell cell2 =
		 * sheet.getCell(3, 4); System.out.println(cell2.getContents());
		 * workbook.close();
		 */
	}

	static String defaultString(String d) {
		String defaultSTR = "";
		if (!d.equals("0")) {
			defaultSTR += d;
		}
		return defaultSTR;
	}

	public static String intToString(double i) {
		String returnVal = "";
		
			returnVal = ""+i;
		
		return returnVal;
	}

	public static void arrangeErrors(BindingResult result, ModelMap model) {

		if (result.getFieldErrors().size() > 0) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError field : fieldErrors) {
				// errors.put(field.getField(),field.getDefaultMessage());
				model.addAttribute(field.getField() + "Err", field.getDefaultMessage());
			}

		}

	}

	public static String returnDateTime(Date d, String t) {
		String dateTime = "NA";
		if (returnDateToString(d).equalsIgnoreCase("NA")) {
			return dateTime;
		} else {
			return returnDateToString(d) + "   " + t;
		}
	}

	public static String getToday() {
		String daySTR = "";
		String yearSTR = "";
		String monthSTR = "";

		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate();
		if (day < 10) {
			daySTR = "0" + day;
		} else {
			daySTR += day;
		}
		if (month < 10) {
			monthSTR = "0" + month;
		} else {
			monthSTR += month;
		}
		yearSTR += year;
		return yearSTR + "-" + monthSTR + "-" + daySTR;
	}

	public static WritableCellFormat decideCurrentCellColor(String record) throws WriteException {
		WritableCellFormat cellFormatrow = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 10));
		cellFormatrow.setBorder(Border.ALL,BorderLineStyle.DOUBLE, Colour.BLACK);
		cellFormatrow.setAlignment(Alignment.CENTRE);
		if (record.equals("A")) {
			cellFormatrow.setBackground(Colour.LIGHT_ORANGE);
		} else if (record.equals("R")) {
			cellFormatrow.setBackground(Colour.RED);
		}else if (record.equals("W")) {
			cellFormatrow.setBackground(Colour.WHITE);
		} else {
			cellFormatrow.setBackground(Colour.LIGHT_GREEN);
		}
		return cellFormatrow;
	}
	
	public static WritableCellFormat decideRowColor() throws WriteException {
		WritableCellFormat cellFormatrow = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 10));
		cellFormatrow.setBorder(Border.ALL,BorderLineStyle.DOUBLE, Colour.BLACK);
		cellFormatrow.setAlignment(Alignment.CENTRE);
		
			cellFormatrow.setBackground(Colour.AQUA);
		
		return cellFormatrow;
	}
	
	
public static WritableCellFormat decidedFinalCellColor(String color) throws WriteException {
		
		WritableCellFormat cellFormatrow = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 10));
		cellFormatrow.setBorder(Border.ALL,BorderLineStyle.DOUBLE, Colour.BLACK);
		cellFormatrow.setAlignment(Alignment.CENTRE);
		if ("R".equals(color)) {
			cellFormatrow.setBackground(Colour.RED);
		}else if("W".equals(color)){
			cellFormatrow.setBackground(Colour.WHITE);
		}else if ("A".equals(color)) {
			cellFormatrow.setBackground(Colour.LIGHT_ORANGE);
		} else if("G".equals(color)){
			cellFormatrow.setBackground(Colour.LIGHT_GREEN);
		}
		return cellFormatrow;
	}
	
	
	
	
	
	public static WritableCellFormat decidedFinalCellColor(String amber,String green,String perc) throws WriteException {
		
		double amberTarget=0;
		double greenTarget=0;
		double percentage=0;
		if(!amber.isEmpty()){
			amberTarget = new BigDecimal(amber).doubleValue();
		}
		if(!green.isEmpty()){
			greenTarget = new BigDecimal(green).doubleValue();
		}
		if(!perc.isEmpty()){
			percentage = new BigDecimal(perc).doubleValue();
		}
		WritableCellFormat cellFormatrow = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 10));
		cellFormatrow.setBorder(Border.ALL,BorderLineStyle.DOUBLE, Colour.BLACK);
		cellFormatrow.setAlignment(Alignment.CENTRE);
		if (!perc.isEmpty() && !"0.0".equals(perc) && (percentage<amberTarget)) {
			cellFormatrow.setBackground(Colour.RED);
		}else if(perc.isEmpty() || "0.0".equals(perc)){
			cellFormatrow.setBackground(Colour.WHITE);
		}else if (percentage>amberTarget && percentage<greenTarget) {
			cellFormatrow.setBackground(Colour.LIGHT_ORANGE);
		} else if(percentage>greenTarget){
			cellFormatrow.setBackground(Colour.LIGHT_GREEN);
		}
		return cellFormatrow;
	}
	
	  public static char decideColorForXLS(double percent, double amber, double green){
		  char colColor = 0;
		  if(percent > amber && percent < green){
				colColor ='A';
			}else if(percent < amber){
				colColor ='R';
			}else{
				colColor ='G';
			}
		  return colColor;
	  }
	  
	  
	  public static boolean isBeforeToday(Date date){
		  SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
		  Date date1 =null;
		  Date currentDate=null;
		  try{
		    date1=sdf.parse(sdf.format(date));
		   currentDate= sdf.parse(sdf.format(new Date()));
		  }catch(ParseException pe){
			  pe.printStackTrace();
		  }
		   return date1.before(currentDate);
	  }
	  
	  
	  public static List<Integer[]> pages(long count, int max){
		  List pages = new ArrayList<Integer[]>();
		  int startItem = 0;
		  int endItem =max-1;
		  for(int i=0; i<count; i++){
			  if(i==endItem || (count-1)==i){
				  if(count==i){
					  endItem=i;  
				  }
				  pages.add( new Integer[] {startItem, endItem});
				  endItem=endItem+max;
				  startItem =i+1;
			  }
			 
			  
		  }
		  return pages;
	  }
	  
	  
	  public static void saveOnDisk(MultipartFile file,String location) 
	  {    
	      File convFile = new File(location);

	      FileOutputStream fos = null; 
	      try{
		      convFile.createNewFile(); 
	    	  fos = new FileOutputStream(convFile); 
	    	  fos.write(file.getBytes());
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }finally{
	    	  try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	      }
	      
	  }

}
