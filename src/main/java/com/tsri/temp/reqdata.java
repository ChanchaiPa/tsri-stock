package com.tsri.temp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class reqdata {

	public static void main(String[] args) {
		String fullname = "D:\\WebApplication\\jee-2021-09\\workspace3\\tsri-stock\\src\\main\\java\\com\\tsri\\temp\\reqdata.txt";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullname), "UTF-8"));
			String line = "";
			while((line = br.readLine()) != null) {
				if (line.trim().length() == 0)
					continue;						
				if (line.charAt(0) == '\t')
					continue;				
				
				String[] temp = line.split("\\|");
				String fieldtype 	= temp[0];
				String fieldname	= temp[1]; 
				String nullflag		= temp[2];
				int colsize	= Integer.parseInt(temp[3]);
				int flcsize = Integer.parseInt(temp[4]);
				
				System.out.println("@Getter @Setter");
				if (nullflag.equals("0"))
					System.out.println("@NotBlank(message = notBlankMessage)");
				
				if (fieldtype.contains("char")) {
					System.out.println("@Size(max = "+colsize+", message = sizeMessage + \" {max}\")  //" + fieldtype + "("+colsize+")");
				}
				else
				if (fieldtype.contains("int")) {
					System.out.println("@Digits(integer = "+ (colsize) +", fraction = 0, message = digitsMessage)  //" + fieldtype + "("+colsize+")");	
				}
				else
				if (fieldtype.equals("decimal") || fieldtype.equals("double")) {
					System.out.println("@DigitsCustom(integer = "+ (colsize-flcsize) +", fraction = "+ flcsize +", message = digitsMessage)  //" + fieldtype + "("+colsize+","+flcsize+")");	
				}
				else
				if (fieldtype.equals("date") || fieldtype.equals("datetime")) {
					//	
				}
				else {
					System.out.println(fieldname + " Unsupport...");
					System.exit(0);
				}
				
				System.out.println("private String	"+fieldname+";");
				System.out.println("");
			}
			br.close();
			br = null;			
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("FILE ERROR " + e.getMessage());
		}
	}

}
