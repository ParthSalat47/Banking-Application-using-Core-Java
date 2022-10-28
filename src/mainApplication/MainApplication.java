package mainApplication;

import java.io.*;
import java.util.*;

import allMethods.BankingOptions;
import allMethods.LoadingData;
import allMethods.UtilityMethods;
import mainApplication.CustomerClass;


public class MainApplication 
{

	 public static HashMap<String, String> customerCredentials = 
			new HashMap<String, String>();
	 public static HashMap<String, CustomerClass>  userSessions = 
			 new HashMap<String, CustomerClass>();

	public static void main(String[] args) 
	{
		
		LoadingData.loadCustomerData();
		
		try
		{
			homeScreen();
		}
		catch(IOException e) 
		{
			System.out.println("Encountered an IOException!");
		}
		finally
		{
			System.out.println("\nThank you for using our Banking Application!");
		}
		
		

	}
	
	private static void homeScreen() throws IOException
	{
		
		while(true)
		{	
			System.out.println("\nPress appropriate key: "
					+ "\n1. Sign up"
					+ "\n2. Log in"
					+ "\n3. Exit");
			
			int option = UtilityMethods.integerInput(); 
			
			switch(option)
			{
				case 1: 
				{
					signUp();
					break;
				}
				case 2: 
				{
					logIn();
					break;
				}
				case 3: 
				{
					return;
				}
				default:	
				{
					System.out.println("Please enter a number between 1 and 3.");
				}
			}
			
			
		}
	}
	
	private static void signUp()
	{
		System.out.println("Enter your name:");
		String customerName = UtilityMethods.stringInput();
		while(UtilityMethods.checkCustomerName(customerName)==false)
		{
			System.out.println("Names should only contain alphabets "
					+ "and spaces");
			System.out.println("\nEnter your name:");
			customerName = UtilityMethods.stringInput();
		}
		
		//todo: take input only till space; Regex: ^[^ ]+$
		System.out.println("Enter username:");
		String username = UtilityMethods.stringInput();
		while(UtilityMethods.checkCustomerUsername(username) == false)
		{
			System.out.println("Please enter atleast 1 alphabet in username");
			System.out.println("\nEnter username:");
			username = UtilityMethods.stringInput();
		}
		
		while(customerCredentials.containsKey(username))
		{
			System.out.println("Username already exists! Please enter a different "
					+ "username:");
			username = UtilityMethods.stringInput();
		}
		
		System.out.println("Enter password:");
		String password = UtilityMethods.stringInput();
		
		//remove \n??
		String credentialsData = username + ", " + password + "\n";
		
		UtilityMethods.fileWriterMethod("credentialsFile.txt", credentialsData);
		
		CustomerClass newCustomer = 
				new CustomerClass(customerName, username, password);
		
		BankingOptions.logOut(newCustomer);    
		
		System.out.println("Please login to use our services:");
		logIn();
		
		
	}
	
	private static void logIn()
	{
		LoadingData.loadCustomerData();
		
		System.out.println("Enter username:");
		String username = UtilityMethods.stringInput();
		
		if(customerCredentials.containsKey(username) == false)
		{
			System.out.println("No such username found. Please sign up or "
					+ "enter correct username.");
			return;
		}
		
		System.out.println("Enter password:");
		String password = UtilityMethods.stringInput();
		
		String storedPassword = customerCredentials.get(username);
		
		if(storedPassword.contentEquals(password))
		{
			CustomerClass currentCustomer = userSessions.get(username);
			
			BankingOptions.bankingOptionsLoop(currentCustomer);
		}
		else 
		{
			System.out.println("Incorrect password! "
					+ "Please try logging in again.");
		}
		
	}
	
	
}

