package com.eviltester.seleniumSimplified.calculate;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


import com.eviltester.seleniumSimplified.pageObjectModel.CalculateForm;
import com.eviltester.seleniumSimplified.testClasses.SeleniumSimplifiedTest;

@RunWith(Parameterized.class)
public class CalculateTwoNumbersTestsTabFile extends SeleniumSimplifiedTest{

	static CalculateForm calculate;
	
	private String number1;
	private String function;
	private String number2;
	private String answer;
	
    public CalculateTwoNumbersTestsTabFile(String num1, String function, String num2, String answer){
    	this.number1 = num1;
    	this.function = function;
    	this.number2 = num2;
    	this.answer = answer;
    }	

    @Parameters
    public static Collection data() throws NumberFormatException, IOException {
    	return readTabDelimFile("src\\test\\resources\\datafiles\\calculate2numbers.tab");
    }	
    
  public static Collection<String[]> readTabDelimFile(String filename) throws NumberFormatException, IOException {
		
	  // based on code from http://progzoo.net/ht05IO/320TDF.xml
	  
    	ArrayList<String[]> lines = new ArrayList<String[]>();
    	
    	   BufferedReader fh = new BufferedReader(new FileReader(filename));
    		    String s;
    		    while ((s=fh.readLine())!=null){
    		      String f[] = s.split("\t");
    		      lines.add(f);
    		    }
    		    fh.close();
    		        		    
    		    return lines;
	}    
	

	
	@Test
	public void test_calculate_two_values(){	
		calculate = new CalculateForm(selenium);
		calculate.open();
		
		calculate.setNumber1(this.number1);
		calculate.setFunction(this.function);
		calculate.setNumber2(this.number2);
		
		calculate = calculate.doCalculation();
		
		assertEquals(this.answer,calculate.getAnswer());
	}    
    
	
}
