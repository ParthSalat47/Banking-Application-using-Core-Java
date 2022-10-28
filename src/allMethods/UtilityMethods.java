package allMethods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
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
		
		Pattern pat = Pattern.compile("\\D");
		Matcher mat = pat.matcher(inputString); 
		
		//To fire up regex and return boolean:
		return mat.find();
		
	}

	public static boolean checkCustomerName(String inputString)
	{
		/*
		 * Checks if string comprises of only alphabets
		 * 
		 * Returns true if all characters are alphabets. 
		 */
		
		Pattern pat = Pattern.compile("^[A-Za-z ]+$");
		Matcher mat = pat.matcher(inputString); 
		
		//To fire up regex and return boolean:
		return mat.find();
		
		
		
	}
	
	
	
}
