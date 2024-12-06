package com.tsri.stock.adapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.tsri.stock.exception.AdapterParsingException;


public class X {
	public static String checkNull(String data) {
		if (data == null)	return "";
		else				return data.trim();
	}
	
	
	private static SimpleDateFormat dateFormatObj = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	//********************
	public static String Date2String(Date x) {
		if (x == null)	
			return "";
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(x);
		//int year = calendar.get(Calendar.YEAR);
		//if (year < 2499 ) 
		//	calendar.set(Calendar.YEAR, year + 543);
		return dateFormatObj.format(calendar.getTime());
	}
	
	public static Date String2Date(String x) {
		if (x == null || x.trim().equals(""))	return null;
		else									x = x.trim();
		
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFormatObj.parse(x));
			//int year = calendar.get(Calendar.YEAR);
			//if (year > 2499 ) 
			//	calendar.set(Calendar.YEAR, year - 543);
			return calendar.getTime();
		}
		catch(Exception e) {
			throw new AdapterParsingException("Parse date " + x);
		}
	}
	
	
	private static SimpleDateFormat datetimeFormatObj = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
	//********************
	public static String DateTime2String(Date x) {
		if (x == null)	
			return "";
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(x);
		//int year = calendar.get(Calendar.YEAR);
		//if (year < 2499 ) 
		//	calendar.set(Calendar.YEAR, year + 543);
		return datetimeFormatObj.format(calendar.getTime());
	}	
	
	public static Date String2DateTime(String x) {
		if (x == null || x.trim().equals(""))	return null;
		else									x = x.trim();
		
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(datetimeFormatObj.parse(x));
			//int year = calendar.get(Calendar.YEAR);
			//if (year > 2499 ) 
			//	calendar.set(Calendar.YEAR, year - 543);
			return calendar.getTime();
		}
		catch(Exception e) { 
			throw new AdapterParsingException("Parse date " + x);
		}
	}
	
	
	//********************
	public static String Double2StringI(Double x) {
		if (x == null)	return "0";
		else			return Integer.toString( x.intValue() );
	}
	
	public static String Double2StringM(Double x) {
		if (x == null)	return "";
		else			return String.format("%.2f", x);	//String.format("%,.2f", x);
	}	

	public static Double String2Double(String x) {
		if (x == null || x.trim().equals(""))	return null;
		else									x = x.trim().replaceAll(",", "");
		
		try 				{ return Double.parseDouble(x); }
		catch(Exception e) 	{ 
			throw new AdapterParsingException("Parse double " + x);
		}
	}
	
	
	//********************
	public static String Integer2String(Integer x) {
		if (x == null)	return "0";
		else			return x.toString();
	}
	
	public static Integer String2Integer(String x) {
		if (x == null || x.trim().equals(""))	return null;
		else									x = x.trim();		
		
		try 				{ return Integer.parseInt(x); }
		catch(Exception e) 	{ 
			throw new AdapterParsingException("Parse int " + x);
		}
	}
	
	
	//********************
	public static String Null2String(String x) {
		if (x == null)	return "";
		else			return x.trim();
	}
	
	public static String String2Null(String x) {
		if (x != null && !x.trim().equals(""))	return x.trim();
		else									return null;
	}
	
	
	//********************
	public static String Boolean2String(boolean x) {
		if (x)	return "t";
		else	return "f";
	}
	public static boolean String2Boolean(String x) {
		if (x == null)	x = "";
		if (x.trim().equals("t"))	return true;
		else						return false;
	}
	
}
