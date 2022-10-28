package mainApplication;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mainApplication.CustomerClass;

public class MainApplication 
{

	 private static HashMap<String, String> customerCredentials = 
			new HashMap<String, String>();
	 private static HashMap<String, CustomerClass>  userSessions = 
			 new HashMap<String, CustomerClass>();

	public static void main(String[] args) 
	{
		
		loadCustomerData();
		
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
			
			int option = integerInput(); 
			
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
		
		//remove this?
		//customerCredentials.put(username, password);
		
		//remove \n??
		String credentialsData = username + ", " + password + "\n";
		
		fileWriterMethod("credentialsFile.txt", credentialsData);
		
		System.out.println("Happy banking!");
		
		CustomerClass newCustomer = 
				new CustomerClass(customerName, username, password);
		
		//remove this?
		/*
		String directory = "C:\\Users\\parthsalat\\eclipse-workspace"
				+ "\\BankingApplication\\customerObjects\\" + username 
				+ ".txt"; 
		
		//fileWriterMethod("sessionsFile.txt", username + "::");
		
		writeObjectToFile(directory, newCustomer);
		*/
		bankingOptionsLoop(newCustomer);
		
		
	}
	
	private static void logIn()
	{
		loadCustomerData();
		
		System.out.println("Enter username:");
		String username = stringInput();
		
		if(customerCredentials.containsKey(username) == false)
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
					logOut(newCustomer);
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
	
	private static void logOut(CustomerClass newCustomer)
	{
		String directoryString = "C:\\Users\\parthsalat\\eclipse-workspace"
				+ "\\BankingApplication\\customerObjects\\" 
				+ newCustomer.getUsername();
				//+ ".txt"; 
		
		try
		{
		//This is used to clear the contents of the file:
			new PrintWriter(directoryString).close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("No file exists for this user!");
		}
		
		writeObjectToFile(directoryString, newCustomer); 
		
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
	
	private static void fileWriterMethod(String fileName, String data)
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
	
	private static void loadCustomerData()
	{
		loadCustomerCredentials();
		loadCustomerSessions();
		
	}
	
	private static void loadCustomerCredentials()
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
				
				customerCredentials.put(usernameMatcher.group(), 
						passwordMatcher.group());
			}
			
			
		}
		catch(IOException e)
		{
			System.out.println("Encountered an IO exception while reading from file!");
		}
	}
	
	private static void loadCustomerSessions()
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
			 
			 userSessions.put(fileName, customerObject);
	
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
	
	private static void writeObjectToFile(String fileName, CustomerClass object)
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
	
	
}

