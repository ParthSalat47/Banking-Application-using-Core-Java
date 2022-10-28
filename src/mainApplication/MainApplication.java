package mainApplication;

import java.io.*;
import java.util.*;
import mainApplication.CustomerClass;

public class MainApplication 
{
	
	 private static HashMap<String, String> customerCredentials = 
			new HashMap<String, String>();
	 private static HashMap<String, CustomerClass>  userSessions = 
			 new HashMap<String, CustomerClass>();

	public static void main(String[] args) 
	{
		
		
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true)
		{	
			System.out.println("\nPress appropriate key: "
					+ "\n1. Sign up"
					+ "\n2. Log in"
					+ "\n3. Exit");
			
			int option = Integer.parseInt(br.readLine());
			
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
				default:	//change it to give encouraging msg
				{
					System.out.println("Please enter a number between 1 and 3.");
				}
			}
			
			
		}
	}
	
	private static void signUp()
	{
		System.out.println("Enter your name:");
		String customerName = stringInput();
		
		//todo: take input only till space
		System.out.println("Enter username:");
		String username = stringInput();
		
		while(customerCredentials.containsKey(username))
		{
			System.out.println("Username already exists! Please enter a different "
					+ "username:");
			username = stringInput();
		}
		
		System.out.println("Enter password:");
		String password = stringInput();
		
		customerCredentials.put(username, password);
		
		System.out.println("Happy banking!");
		
		CustomerClass newCustomer = 
				new CustomerClass(customerName, username, password);
		
		userSessions.put(username, newCustomer);
		
		bankingOptionsLoop(newCustomer);
		
		
	}
	
	private static void logIn()
	{
		System.out.println("Enter username:");
		String username = stringInput();
		
		while(customerCredentials.containsKey(username) == false)
		{
			System.out.println("No such username found. Please sign up or "
					+ "enter correct username.");
			return;
		}
		
		System.out.println("Enter password:");
		String password = stringInput();
		
		String storedPassword = customerCredentials.get(username);
		
		if(storedPassword.contentEquals(password))
		{
			CustomerClass currentCustomer = userSessions.get(username);
			
			bankingOptionsLoop(currentCustomer);
		}
		else 
		{
			System.out.println("Incorrect password! "
					+ "Please try logging in again.");
		}
		
	}
	
	private static void bankingOptionsLoop(CustomerClass newCustomer)
	{
		while(true)
		{
			System.out.println("\nDASHBOARD\n"
					+ "Choose appropriate option:"
					+ "\n1. Balance Inquiry"
					+ "\n2. Account History"
					+ "\n3. Withdraw money"
					+ "\n4. Deposit money"
					+ "\n5. Log out");
			
			int option =  integerInput();
			
			switch(option)
			{
				case 1:
				{
					balanceInquiry(newCustomer);
					break;
				}
				case 2:
				{
					accountHistory(newCustomer);
					break;
				}
				case 3:
				{
					withdrawMoney(newCustomer);
					break;
				}
				case 4:
				{
					depositMoney(newCustomer);
					break;
				}
				case 5:
				{
					return;
				}
				default:
				{
					System.out.println("Please enter a number between 1 and 5");
					break;
				}
				
			}
				
		}
			
	}
	
	private static void balanceInquiry(CustomerClass newCustomer)
	{
		System.out.println("Balance amount in your account: "
				+ newCustomer.getBalanceAmount());
	}
	
	private static void accountHistory(CustomerClass newCustomer)
	{
		ArrayList<String> accountHistoryType = 
				newCustomer.getAccountHistoryType();
		ArrayList<Integer> accountHistoryData = 
				newCustomer.getAccountHistoryData();
		
		//todo: special message for no history
		
		for(int i=0; i<accountHistoryData.size(); i++)
		{
			System.out.println(accountHistoryType.get(i) + " : "
					+ accountHistoryData.get(i));
		}
		
	}
	
	private static void withdrawMoney(CustomerClass newCustomer)
	{
		//todo: provide option to exit from between
		
		int currentBalance = newCustomer.getBalanceAmount();
		int withdrawAmount=0; 	//setting 0 as a default value
		
		System.out.println("Enter amount to withdraw:");
		
		withdrawAmount = integerInput();
		
		while(withdrawAmount > currentBalance || withdrawAmount < 0)
		{
			System.out.println("Please enter amount between 0 and "
					+ currentBalance);
			withdrawAmount = integerInput();
		}
		
		int newBalanceAmount = currentBalance - withdrawAmount;
		newCustomer.setBalanceAmount(newBalanceAmount);
		newCustomer.setAccountHistory("Withdraw", withdrawAmount);
		System.out.println("Successfully withdrawn!");
		
	}
	
	private static void depositMoney(CustomerClass newCustomer)
	{
		//todo: provide option to exit from between
		
		int currentBalance = newCustomer.getBalanceAmount();
		
		System.out.println("Enter an amount to deposit in your account: ");
		int toDeposit = integerInput();
		
		while(toDeposit<0)
		{
			System.out.println("Please enter an amount greater than 0.");
			toDeposit = integerInput();
		}
		
		newCustomer.setBalanceAmount(currentBalance + toDeposit);
		newCustomer.setAccountHistory("Deposit", toDeposit);
		System.out.println("Successfully deposited!");
	}
	
	private static int integerInput()
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
	
	private static String stringInput()
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

}

