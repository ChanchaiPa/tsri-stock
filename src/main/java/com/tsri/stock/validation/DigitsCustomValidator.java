package com.tsri.stock.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DigitsCustomValidator implements ConstraintValidator<DigitsCustom, String> {
	private int integer;
	private int fraction;
	
    @Override
    public void initialize(DigitsCustom digitsCustom) {
        this.integer  = digitsCustom.integer();
        this.fraction = digitsCustom.fraction();
    }	

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		value = value.trim();
		if (value.length() == 0)
			return true;
		value = value.replaceAll(",", "");
		
		int idxPoint = value.indexOf(".");
		if (idxPoint == -1) {
			return checkNumber(value);
		}
		else {
			String value1 = value.substring(0, idxPoint);
			String value2 = value.substring(idxPoint+1);
			if (value2.length() == 0)
				return false;
			return checkDecimal(value1, value2);
		}
	}


	private boolean checkNumber(String value) {
		try { 
			long _value = Long.parseLong(value); 
			if (value.length() > integer)
				return false;			
		}
		catch(Exception e) 	{ 
			return false; 
		}		
		return true;
	}


	private boolean checkDecimal(String value1, String value2) {
		try { 
			long _value1 = Long.parseLong(value1); 
			if (value1.length() > integer)
				return false;		
			
			long _value2 = Long.parseLong(value2); 
			if (_value2 == 0)
				return true;
			else
				if (value2.length() > fraction)
					return false;					
		}
		catch(Exception e) 	{ 
			return false; 
		}		
		
		return true;
	}	

}
