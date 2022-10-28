package allMethods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mainApplication.CustomerClass;
import mainApplication.MainApplication;

public class LoadingData {

	public static void loadCustomerData()
	{
		LoadingData.loadCustomerCredentials();
		LoadingData.loadCustomerSessions();
		
	}

	public static void loadCustomerCredentials()
	{
		//this function is working perfectly
		
		try(
				FileReader fr=new FileReader("credentialsFile.txt");    
		        BufferedReader br=new BufferedReader(fr) )
		{
			String line;
			
			while((line = br.readLine()) != null)
			{
				//extracts everything before the 1st comma
				Pattern usernamePattern = Pattern.compile("^[^,]*");
				Matcher usernameMatcher = usernamePattern.matcher(line); 
				
				Pattern passwordPattern = Pattern.compile("[^, ]*$");
				Matcher passwordMatcher = passwordPattern.matcher(line); 
				
				//it's required for matcher.group()
				usernameMatcher.find();	
				passwordMatcher.find();
				
				//System.out.println(usernameMatcher.group() + " +12345+ " 
				//+ passwordMatcher.group());
				
				MainApplication.customerCredentials.put(usernameMatcher.group(), 
						passwordMatcher.group());
			}
			
			
		}
		catch(IOException e)
		{
			//Commenting it out so that it fails silently:
			//System.out.println("Encountered an IO exception while reading from file!");
		}
	}

	public static void loadCustomerSessions()
	{
		String directoryString = "C:\\Users\\parthsalat\\eclipse-workspace"
				+ "\\BankingApplication\\customerObjects\\";// + username; 
		
		File directoryFile = new File(directoryString);
		 File[] directoryListing = directoryFile.listFiles();
		
		 for(File singleTextFile : directoryListing)
		 {
			 try(
					 FileInputStream fi = new FileInputStream(singleTextFile);
						ObjectInputStream oi = new ObjectInputStream(fi); 
					 )
			{
			 CustomerClass customerObject = (CustomerClass) oi.readObject();
				 
			 String fileName = singleTextFile.getName();
			 
			 MainApplication.userSessions.put(fileName, customerObject);
	
			}
			catch(IOException e)
			{
				System.out.println("Encountered an IO exception while reading from file!");
			}
			 catch(ClassNotFoundException c)
			 {
			 	c.printStackTrace();
			 }
		 }
		 
		
	}

}
