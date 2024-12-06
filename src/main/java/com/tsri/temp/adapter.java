package com.tsri.temp;

import java.io.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class adapter {

	public static void mainx(String[] args) {
		String tableData = "assetData";
		String tableEntity = "assetEntity";
		String fullname = "D:\\WebApplication\\jee-2021-09\\workspace3\\tsri-stock\\src\\main\\java\\com\\tsri\\temp\\adapter.txt";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullname), "UTF-8"));
			String line = "";
			while((line = br.readLine()) != null) {
				if (line.trim().length() == 0)
					continue;	
				if (line.charAt(0) == '\t')
					continue;	
				
				String[] temp = line.split("\\|");
				String type = temp[0];
				String field= temp[1]; 
					   field= field.substring(0,1).toUpperCase() + field.substring(1);
				String data = "";
				
				if (type.contains("char")) 
					data = tableData + ".set"+ field +"(  X.Null2String("+ tableEntity +".get"+ field +"())  );";
				if (type.contains("int")) 
					data = tableData + ".set"+ field +"(  X.Integer2String("+ tableEntity +".get"+ field +"())  );";
				if (type.equals("decimal") || type.equals("double")) 
					data = tableData + ".set"+ field +"(  X.Double2String("+ tableEntity +".get"+ field +"())  );";
				if (type.equals("date"))
					data = tableData + ".set"+ field +"(  X.Date2String("+ tableEntity +".get"+ field +"())  );";
				if (type.equals("datetime"))
					data = tableData + ".set"+ field +"(  X.DateTime2String("+ tableEntity +".get"+ field +"())  );";				
				System.out.println(data);
			}
			br.close();
			br = null;
		}catch(Exception e) {
			System.out.println("FILE ERROR " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		String tableEntity = "assetEntity";
		String tableData = "assetData";
		String fullname = "D:\\WebApplication\\jee-2021-09\\workspace3\\tsri-stock\\src\\main\\java\\com\\tsri\\temp\\adapter.txt";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullname), "UTF-8"));
			String line = "";
			while((line = br.readLine()) != null) {
				if (line.trim().length() == 0)
					continue;	
				if (line.charAt(0) == '\t')
					continue;
				
				String[] temp = line.split("\\|");
				String type = temp[0];
				String field= temp[1]; 
					   field= field.substring(0,1).toUpperCase() + field.substring(1);
				String data = "";
				
				if (type.contains("char")) 
					data = tableEntity + ".set"+ field +"(  X.String2Null("+ tableData +".get"+ field +"())  );";
				if (type.contains("int")) 
					data = tableEntity + ".set"+ field +"(  X.String2Integer("+ tableData +".get"+ field +"())  );";
				if (type.equals("decimal") || type.equals("double")) 
					data = tableEntity + ".set"+ field +"(  X.String2Double("+ tableData +".get"+ field +"())  );";
				if (type.equals("date"))
					data = tableEntity + ".set"+ field +"(  X.String2Date("+ tableData +".get"+ field +"())  );";
				if (type.equals("datetime"))
					data = tableEntity + ".set"+ field +"(  X.String2DateTime("+ tableData +".get"+ field +"())  );";				
				System.out.println(data);
			}
			br.close();
			br = null;
		}catch(Exception e) {
			System.out.println("FILE ERROR " + e.getMessage());
		}
	}	

	
	public static void mainz(String[] args) {
		BCryptPasswordEncoder x = new BCryptPasswordEncoder();
		System.out.println(x.encode("Nueng@123"));
	}
	
}
