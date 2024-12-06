package com.tsri.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;


public class Utils {
	//private static Logger logger = Logger.getLogger("");

	/*private static String classname(String classname) {
		String temp = classname + "                   ";
		temp = temp.substring(0, 15);
		return temp;
	}
	public static void log(String userid, String classname, String info) {
		System.out.println("["+userid+"]["+ classname(classname) +"] " + info);
		logger.info("["+userid+"]["+ classname(classname) +"] " + info);
	}*/
	
	
	
	public static String checkNull(String data) {
		if (data == null)	data = "";
		else				data = data.trim();
		return data;
	}	
	public static String checkNull(String data, String defaultValue) {
		if (data == null)	data = "";
		else				data = data.trim();
		if (data.equals(""))
			data = defaultValue;
		return data;
	}		
	
	
	
	public static String sqlValT(String value) {
		if (value==null || value.equals(""))
			return "Null";
		else
			return "'"+value.replaceAll("'", "")+"'";
	}
	public static String sqlValD(String value) {
		if (value==null || value.equals(""))
			return "Null";
		else
			return value;
	}	
	
	
	
	public static int toInt(String data, int defaultVal) {
		int value = 0;
		if (data.contains("."))
			try { value = (int)Math.round(Double.parseDouble(data)); }
			catch(Exception e) { value = defaultVal; }
		else
			try { value = Integer.parseInt(data); }
			catch(Exception e) { value = defaultVal; }
		return value;
	}
	
	public static java.util.Date parseDateTime(String dtstr, String format) {
		java.util.Date dt = null;
		SimpleDateFormat dfm = new SimpleDateFormat( format, Locale.ENGLISH );
		try  { dt = dfm.parse( dtstr ); } catch(Exception e) {}
		return dt;
	}
	public static String toDateFormat(java.util.Date dt, String format) {
		String dateFormat = "";
		SimpleDateFormat dfm = new SimpleDateFormat( format, Locale.ENGLISH );
		try  { dateFormat = dfm.format( dt ); } catch(Exception e) {}
		return dateFormat;
	}
	public static String toDateFormat(java.sql.Date dt, String format) {
		String dateFormat = "";
		SimpleDateFormat dfm = new SimpleDateFormat( format, Locale.ENGLISH );
		try {  dateFormat = dfm.format( dt ); } catch(Exception e) {}
		return dateFormat;
	}
	
	public static String toDecimalFormat(double number) {
		java.text.DecimalFormat numberFormat = new java.text.DecimalFormat("#,###.00");
		return numberFormat.format(number);
	}
	
	
	
    public static String createTextFile(String fullname, String content) throws IOException {    	
    	if(content==null)  content = "";
    	else               content = content.trim();
    	
    	byte[] data1 = content.getBytes("UTF-8");
		try {
			  FileOutputStream outFile = new FileOutputStream(fullname);
			  outFile.write(data1);
			  outFile.flush();
			  outFile.close();
			  outFile = null;
		} finally {
			  //writeLog(username,"writeFile - writing data to file");
		}	
		return fullname;
    }    
    public static String readTextFile(String fullname, String encoding) {
		String data = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullname),encoding));
			String line = "";
			while((line = br.readLine()) != null) {
				data += "\r" + line;
			}
			br.close();
			br = null;
		}catch(Exception e) {
			System.out.println("FILE ERROR " + e.getMessage());
		}			
		return data.trim();   	
    }    
	
    
    
	/*public static String getJSONString(org.json.JSONObject requestParam, String fieldName) {
		String fieldValue = "";
		try  { fieldValue = requestParam.getString(fieldName);  }
		catch(Exception e) { System.out.println(fieldName + " not found"); }
		return fieldValue.trim();
	}
	public static int getJSONInteger(org.json.JSONObject requestParam, String fieldName) {
		int fieldValue = 0;
		try  { fieldValue = requestParam.getInt(fieldName);  }
		catch(Exception e) { System.out.println(fieldName + " not found"); }
		return fieldValue;
	}
	
	
	public static String getJSONString(org.json.simple.JSONObject requestParam, String fieldName) {
		String fieldValue = "";
		try  { fieldValue = (String)requestParam.get(fieldName);  }
		catch(Exception e) { System.out.println(fieldName + " not found"); }
		return fieldValue.trim();
	}
	public static int getJSONInteger(org.json.simple.JSONObject requestParam, String fieldName) {
		int fieldValue = 0;
		try  { fieldValue = (int)requestParam.get(fieldName);  }
		catch(Exception e) { System.out.println(fieldName + " not found"); }
		return fieldValue;
	}*/	
	
    
	
	/*public static String getRequestBody(HttpServletRequest request) {
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		try {
			java.io.BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				stringBuffer.append(line);
			}				
		}
		catch(Exception e) {}	
		return stringBuffer.toString();
	}*/
	
}
