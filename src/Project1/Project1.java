/**
 * Author: Kenniece Harris
 * Course: COP3530
 * Project#: 1
 * Title: Project1_N01503765
 * Due Date: 09/16/2022
 * 
 *  Creates an array of containing countries from the countries1.csv file where user can choose whether
 *  to sort the array by name, CFR, GDPPC, find a country by a give name or print the kendall's coefficient 
 *  
 */

package Project1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class Project1 {

	
	public static Country[] countryArray = new Country[145];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  System.out.println("Array Searches and Sorts");
      getUserInput();
      
      
      
      
	}
	
	public static void getUserInput() {
		System.out.print("Please enter the name of the csv file:");
	    Scanner scnr = new Scanner(System.in);
	    String csvFileName = scnr.nextLine();
	    System.out.println("");
	    parseCsvFile(csvFileName);

	}
	
	public static void parseCsvFile(String fileName) {
		
		FileReader newFile;
	      Scanner scnr2;
			try {
				newFile = new FileReader(fileName);
				 scnr2 = new Scanner(newFile);
				
				int count = 0;
				while(scnr2.hasNext())
				{
					  String word = scnr2.nextLine();
					  if(count > 0)
					  {
						  
						  String[] newLine = word.split(",");
						  countryArray[count - 1] = new Country(newLine[0],newLine[1],newLine[2],newLine[3], newLine[4], newLine[5], newLine[6]);
						  
					  }

					count++;
				}
				
				
				System.out.println("There were 145 records read.");
				System.out.println(countryArray.length);
				printUserInput();
			} 
			
			catch (FileNotFoundException e) {
				System.out.println("Try Again. Please enter a valid csv file name.");
			}
	      
		
	}
	
	public static void printUserInput() {
		
		
		
		System.out.println("Please make a selection:");
	    System.out.println("1. Print a Countries Report");
	    System.out.println("2. Sort by Name");
	    System.out.println("3. Sort by Case Fatality Rate");
	    System.out.println("4. Sort by GDP per capita");
	    System.out.println("5. Find and print a given country");
	    System.out.println("6. Print Kendall's tau matrix");
	    System.out.println("7. Quit Program");
	    System.out.println("");
	    System.out.print("Enter your choice: ");
	    
	    Scanner input = new Scanner(System.in);
		String userInput = input.next();
		boolean exit = false;
		System.out.println("");
		 
		while(!exit)
	      {
		    	
		    	if(userInput.equals("quit"))
		    	{
		    		 exit = true;
		    		 break;
		    	}
		    	
		    	else if((Integer.parseInt(userInput) < 0) || (Integer.parseInt(userInput) > 6))
			    {
			    	
			    	while((Integer.parseInt(userInput) < 0) || (Integer.parseInt(userInput) > 6)) {
			    		System.out.print("Invalid choice! Enter 1-7:");
			    		userInput = input.next();
			    	}
			    	
			    	System.out.println("");
			    }
		    	
		    	else {
				    
				    int i = Integer.parseInt(userInput); 
				    
				    
				    
				   
				    switch(i) {
				    
				      
				      case 1: printOptionOne(); break;
				      case 2: printOptionTwo();  break;
				      case 3: printOptionThree();break;
				      case 4: printOptionFour(); break;
				      case 5: System.out.print("Enter a Country Name: "); userInput = input.next(); if(input.hasNextLine()) { userInput +=  input.nextLine();}; printOptionFive(userInput); break;
				      case 6: printOptionSix(); break;
				      
				    }
				    
				    System.out.println("Please make a selection:");
				    System.out.println("1. Print a Countries Report");
				    System.out.println("2. Sort by Name");
				    System.out.println("3. Sort by Case Fatality Rate");
				    System.out.println("4. Sort by GDP per capita");
				    System.out.println("5. Find and print a given country");
				    System.out.println("6. Print Kendall's tau matrix");
				    System.out.println("7. Quit Program");
				    System.out.println("");
				    System.out.print("Enter your choice: ");
				    
				 
				    userInput = input.next();
				    System.out.println("");
				    }
		    	
	      }

		
	}
	
	public static void printOptionOne() {
		
		for(int i = 0; i < countryArray.length + 1; i++) {
			
			if(i == 0) {
				System.out.println("Name   Capitol   GPPC   CFR   CaseRate   DeathRate   PopDensity");
				System.out.println("----------------------------------------------------------------");
			}
			
			else {
				String country = countryArray[i-1].getCountryData();
				System.out.println(country);
			}
			
		}
		
	}
	
	public static void printOptionTwo() {
		int inner;
		int outer;
		for( outer = 1; outer < countryArray.length; outer++){ 
			
			Country current = countryArray[outer];
			inner = outer - 1; 
			 
			
				while(inner >= 0 && (countryArray[inner].getName()).compareTo(current.getName()) > 0) { 
					
					
					countryArray[inner + 1] = countryArray[inner];
					
					inner--; 
				} 
						
				
			
			countryArray[inner + 1] = current;
			
		} 
		
		
		
	}
	
	public static void printOptionThree() {
		int num = countryArray.length;
		
		for(int outer = 0; outer < num - 1; outer++) {
			int lowest = outer;
				for(int inner = outer + 1; inner < num; inner++) {
					
					if(countryArray[inner].getCFR() < countryArray[lowest].getCFR()){
					lowest = inner;
					}
				}
				
				if(lowest != outer) {
				Country current = countryArray[lowest];
				countryArray[lowest] = countryArray[outer];
				countryArray[outer] = current;
				}
			}
	}
	
	public static void printOptionFour() {
		
		for(int outer = 0; outer < countryArray.length - 1; outer++) {
			for(int inner = countryArray.length - 1; inner > outer; inner--) {
				if(countryArray[inner].getGDPPC() < countryArray[inner -1].getGDPPC()) {
					Country current = countryArray[inner];
					countryArray[inner] = countryArray[inner - 1];
					countryArray[inner - 1] = current;
				}
			}
		}
		
	}
	
	public static void printOptionFive(String countryName) {
		boolean sorted = false;
		
		
		for(int i = 1 ; i < countryArray.length; i++)
		{
		     if(countryArray[i -1].getName().compareTo(countryArray[i].getName()) < 0) {
		    	 sorted = true;
		     }
		      else {
		    	  sorted = false;
		    	  break;
		        }   
		}
		
		
		
		Country country = countryArray[0];
		boolean found = false;
		if (sorted == false)
		{
			
			System.out.println("Sequential Search is Used");
			
			int j = 0;
			
			while(j < countryArray.length) {
				if(countryArray[j].getName().compareTo(countryName) == 0) {
				    country = countryArray[j];
				    found = true;
					break;
				}
					
				j++;
			}
				
			if(found != true){
				System.out.printf("Error: country %s not found.%n", countryName);
			}
		}
		
		
		else if (sorted == true ){
			System.out.println("Binary Search is Used");
			
			int lowerBound = 0;
			int upperBound = countryArray.length - 1;
	        
			
			while(lowerBound <= upperBound) {
				int mid = (lowerBound + upperBound)/ 2;
				if(countryArray[mid].getName().compareTo(countryName) == 0) {
					 country = countryArray[mid];
					 found = true;
					 break;
				}
				
				else if(countryArray[mid].getName().compareTo(countryName) > 0) {
					upperBound = mid - 1;
				}
				
				else if (countryArray[mid].getName().compareTo(countryName) < 0) {
					lowerBound = mid + 1;
				}
				
				
			}
			
		}
		
		if(found == true) {
		System.out.println("Name: " + String.format("%3s", country.getName()));
		System.out.println("Capital: " + String.format("%3s", country.getCapital()));
		System.out.println("GDPPC: " + String.format("%3.3f", country.getGDPPC()));
		System.out.println("CFR: " + String.format("%3.6f", country.getCFR()));
		System.out.println("CaseRate: " + String.format("%3.3f", country.getCaseRate()));
		System.out.println("DeathRate: " + String.format("%3.3f", country.getDeathRate()));
		System.out.println("PopDensity: " + String.format("%3.3f", country.getPopDensity()));
		}   
	}
	
	public static void printOptionSix() {
		
		Country[] arrayCaseRate = new Country[145];
		Country[] arrayGDPPC = new Country[145];
		Country[] arrayDeathRate  = new Country[145];
		Country[] arrayPopDensity = new Country[145];
		
		
		covidCaseRateSorted();
	    for(int i = 0; i < countryArray.length; i++) {
		     arrayCaseRate[i] = countryArray[i];
	  }
	    
	    
	   printOptionFour();
	   for(int i = 0; i < countryArray.length; i++) {
		     arrayGDPPC[i] = countryArray[i];
	  }
	    
	   covidDeathRateSorted();
	    for(int i = 0; i < countryArray.length; i++) {
		     arrayDeathRate[i] = countryArray[i];
	  }
	    
	    popDensitySorted();
	    for(int i = 0; i < countryArray.length; i++) {
		     arrayPopDensity[i] = countryArray[i];
	  }
	   
	   
	    int agree1 = 0;
	    int agree2 = 0;
	    int agree3 = 0;
	    int agree4 = 0;
	    int disagree1 = 0;
	    int disagree2 = 0;
	    int disagree3 = 0;
	    int disagree4 = 0;
	 
	    int[] rank1 = new int[145];
	    int[] rank2 = new int[145];
	    int[] rank3 = new int[145];
	    int[] rank4 = new int[145];
	    
	    for(int i = 0; i < countryArray.length; i++) {
	    	String gdppcCountry = arrayGDPPC[i].getName();
	    	String popDensityCountry = arrayPopDensity[i].getName();
	    	for(int j = 0; j < countryArray.length; j++) {
	    		String caseRateCountry = arrayCaseRate[j].getName();
	    		String deathRateCountry = arrayDeathRate[j].getName();
	    		if(gdppcCountry.compareTo(caseRateCountry) == 0) {
	    			rank1[i] = j;
	    			
	    		}
	    		
	    		if(gdppcCountry.compareTo(deathRateCountry) == 0) {
	    			rank2[i] = j;
	    			
	    		}
	    		
	    		if(popDensityCountry.compareTo(caseRateCountry) == 0) {
	    			rank3[i] = j;
	    			
	    		}
	    		
	    		if(popDensityCountry.compareTo(deathRateCountry) == 0) {
	    			rank4[i] = j;
	    			
	    		}
	    	}	
	    }
	    
	    for(int i = 0; i < rank1.length; i++) {
	    	int num1 = rank1[i];
	    	int num2 = rank2[i];
	    	int num3 = rank3[i];
	    	int num4 = rank4[i];
	    	for(int j = i + 1; j < rank1.length; j++)
	    	{
	    		int value1 = rank1[j];
	    		int value2 = rank2[j];
	    		int value3 = rank3[j];
	    		int value4 = rank4[j];
	    		
	    		if(num1 < value1) {agree1++;}
	    		else {disagree1++;}
	    		
	    		if(num2 < value2) {agree2++;}
	    		else {disagree2++;}
	    		
	    		if(num3 < value3) {agree3++;}
	    		else {disagree3++;}
	    		
	    		if(num4 < value4) {agree4++;}
	    		else {disagree4++;}
	    		
	    	}
	    }
	    
	    
	    double calculateTau1 = agree1 - disagree1;
	    double calculateTau2 = agree2 - disagree2;
	    double calculateTau3 = agree3 - disagree3;
	    double calculateTau4 = agree4 - disagree4;
	    
	     
	    calculateTau1 /= agree1 + disagree1;
	    calculateTau2 /=  agree2 + disagree2;
	    calculateTau3 /= agree3 + disagree3;
	    calculateTau4 /= agree4 + disagree4;
	    
	    System.out.println("--------------------------------------------------------------------------");
	    System.out.println("|                         |         GDPPC         |      PopDensity      |");
	    System.out.println("--------------------------------------------------------------------------");
	    System.out.printf("|     Covid Case Rate     |      %.4f           |       %.4f         |%n",calculateTau1, calculateTau3);
	    System.out.println("--------------------------------------------------------------------------");
	    System.out.printf("|    Covid Death Rate     |      %.4f           |       %.4f        |%n", calculateTau2, calculateTau4);
	    System.out.println("--------------------------------------------------------------------------");
	    
	}
	
	
	public static void covidDeathRateSorted() {
		
     int num = countryArray.length;
		
		for(int outer = 0; outer < num - 1; outer++) {
			int lowest = outer;
				for(int inner = outer + 1; inner < num; inner++) {
					
					if(countryArray[inner].getDeathRate() < countryArray[lowest].getDeathRate()){
					lowest = inner;
					}
				}
				
				if(lowest != outer) {
				Country current = countryArray[lowest];
				countryArray[lowest] = countryArray[outer];
				countryArray[outer] = current;
				}
			}
		
	}
	
    public static void covidCaseRateSorted() {
    	
     int num = countryArray.length;
		
		for(int outer = 0; outer < num - 1; outer++) {
			int lowest = outer;
				for(int inner = outer + 1; inner < num; inner++) {
					
					if(countryArray[inner].getCaseRate() < countryArray[lowest].getCaseRate()){
					lowest = inner;
					}
				}
				
				if(lowest != outer) {
				Country current = countryArray[lowest];
				countryArray[lowest] = countryArray[outer];
				countryArray[outer] = current;
				}
			}
    	
    }
    
    public static void popDensitySorted() {
    	
        int num = countryArray.length;
   		
   		for(int outer = 0; outer < num - 1; outer++) {
   			int lowest = outer;
   				for(int inner = outer + 1; inner < num; inner++) {
   					
   					if(countryArray[inner].getPopDensity() < countryArray[lowest].getPopDensity()){
   					lowest = inner;
   					}
   				}
   				
   				if(lowest != outer) {
   				Country current = countryArray[lowest];
   				countryArray[lowest] = countryArray[outer];
   				countryArray[outer] = current;
   				}
   			}
       	
       }
}
