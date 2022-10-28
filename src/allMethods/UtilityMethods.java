package allMethods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.regex.*;

import mainApplication.CustomerClass;

public class UtilityMethods {
	
	public static int integerInput()
	{
		/*
		 * Returns zero in case of an exception.
		 */
		
		int value=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try
		{
			value = Integer.parseInt(br.readLine());
		}
		catch (IOException e)
		{
			System.out.println("Encountered an IO exception while taking integer input.");
			return 0;
		}
		catch(NumberFormatException e)
		{
			System.out.println("The key you pressed doesn't qualify as a 'Number'. ");
		}

		return value;
	}
	
	public static String stringInput()
	{
		/*
		 * Returns null in case of an exception.
		 */
		
		String value=null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try
		{
			value = br.readLine();		
			
		}
		catch (IOException e)
		{
			System.out.println("Encountered an IO exception while taking a string input.");
			return null;
		}

		return value;
	}
	
	public static void fileWriterMethod(String fileName, String data)
	{
		try(
				FileWriter writer = new FileWriter(fileName);  
			    BufferedWriter buffer = new BufferedWriter(writer); )
		{
			buffer.write(data);  
		} 
		catch (IOException e)
		{
			System.out.println("Encountered an IO exception while writing to a file!");
		}
	}
	
	public static void writeObjectToFile(String fileName, CustomerClass object)
	{
		try {
			FileOutputStream f = new FileOutputStream(new File(fileName));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(object); 
			
			System.out.println("\n\n");
			
			o.close();
			f.close();
		}
		catch(IOException e)
		{
			System.out.println("Encountered an IO exception while writing "
					+ "object to file.");
		}
	
	}
	
	public static boolean checkCustomerUsername(String inputString)
	{
		/*
		 * Checks if a string is made up of only numbers and no
		 * alphabets.
		 * 
		 * Returns true if at least one alphabet exists.
		 */
		
		//Pattern digitPattern = Pattern.compile("\\D");
		Pattern symbolPattern = Pattern.compile("^[A-Za-z0-9]+$");
		Pattern spacePattern = Pattern.compile("^[^ ]+$");
		Pattern alphabetPattern = Pattern.compile("[A-Za-z]{1}");
		Pattern lengthPattern = Pattern.compile("[A-Za-z0-9]{4}");
		
		//Matcher digitMatcher = digitPattern.matcher(inputString); 
		Matcher symbolMatcher = symbolPattern.matcher(inputString);
		Matcher spaceMatcher = spacePattern.matcher(inputString);
		Matcher alphabetMatcher = alphabetPattern.matcher(inputString);
		Matcher lengthMatcher = lengthPattern.matcher(inputString);
		
		//To fire up regex and return boolean:
		return (spaceMatcher.find() & lengthMatcher.find() & 
				alphabetMatcher.find() & symbolMatcher.find());
		
	}

	public static boolean checkCustomerName(String inputString)
	{
		/*
		 * Checks if string comprises of only alphabets and no spaces
		 * 
		 * Returns true if all characters are alphabets. 
		 */
		
		Pattern pat = Pattern.compile("^[A-Za-z ]+$");
		Pattern spacePattern = Pattern.compile("^[^ ]+$");
		
		Matcher mat = pat.matcher(inputString);
		Matcher spaceMatcher = spacePattern.matcher(inputString);
		
		//To fire up regex and return boolean:
		return (mat.find() & spaceMatcher.find()); 
		
	}
	
	public static HashMap<Boolean, String> checkCustomerPassword(String inputString) {
		HashMap<Boolean, String> customerPasswordResult = 
				new HashMap<Boolean, String>();
		
		Pattern digitPattern = Pattern.compile("\\d");
		Pattern spacePattern = Pattern.compile("^[^ ]+$");
		Pattern upperCasePattern = Pattern.compile("[A-Z]");
		Pattern lowerCasePattern = Pattern.compile("[a-z]");
		Pattern lengthPattern = Pattern.compile(".{4}");
		
		Matcher digitMatcher = digitPattern.matcher(inputString); 
		Matcher spaceMatcher = spacePattern.matcher(inputString);
		Matcher upperCaseMatcher = upperCasePattern.matcher(inputString);
		Matcher lowerCaseMatcher = lowerCasePattern.matcher(inputString);
		Matcher lengthMatcher = lengthPattern.matcher(inputString);
		
		if(!digitMatcher.find())	
		{
			customerPasswordResult.put(false, "Password should contain at least 1 digit.");
			return customerPasswordResult;
		}
		
		if(!spaceMatcher.find())
		{
			customerPasswordResult.put(false, "Password should not contain spaces.");
			return customerPasswordResult;
		}
		
		if(!upperCaseMatcher.find())
		{
			customerPasswordResult.put(false, "Password should contain at least 1 uppercase"
					+ " alphabet.");
			return customerPasswordResult;
		}
		
		if(!lowerCaseMatcher.find())
		{
			customerPasswordResult.put(false, "Password should contain at least 1 lowercase"
					+ " alphabet.");
			return customerPasswordResult;
		}
		
		if(!lengthMatcher.find())
		{
			customerPasswordResult.put(false, "Password length should be at least 4 characters.");
			return customerPasswordResult;
		}
		
		customerPasswordResult.put(true, "Good password!");
		return customerPasswordResult;
	}
	
	
	
}
