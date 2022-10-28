package allMethods;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import mainApplication.CustomerClass;

public class BankingOptions {

	public static void bankingOptionsLoop(CustomerClass newCustomer)
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
			
			int option =  UtilityMethods.integerInput();
			
			switch(option)
			{
				case 1:
				{
					BankingOptions.balanceInquiry(newCustomer);
					break;
				}
				case 2:
				{
					BankingOptions.accountHistory(newCustomer);
					break;
				}
				case 3:
				{
					BankingOptions.withdrawMoney(newCustomer);
					break;
				}
				case 4:
				{
					BankingOptions.depositMoney(newCustomer);
					break;
				}
				case 5:
				{
					BankingOptions.logOut(newCustomer);
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

	public static void balanceInquiry(CustomerClass newCustomer)
	{
		System.out.println("Balance amount in your account: "
				+ newCustomer.getBalanceAmount());
	}

	
	public static void accountHistory(CustomerClass newCustomer)
	{
		ArrayList<String> accountHistoryType = 
				newCustomer.getAccountHistoryType();
		ArrayList<Integer> accountHistoryData = 
				newCustomer.getAccountHistoryData();
		
		//todo: special message for no history
		
		if(accountHistoryType == null)
		{
			System.out.println("No history to show.");
			return;
		}
		
		for(int i=0; i<accountHistoryData.size(); i++)
		{
			System.out.println(accountHistoryType.get(i) + " : "
					+ accountHistoryData.get(i));
		}
		
	}

	public static void withdrawMoney(CustomerClass newCustomer)
	{
		//todo: provide option to exit from between
		
		int currentBalance = newCustomer.getBalanceAmount();
		int withdrawAmount=0; 	//setting 0 as a default value
		
		if(currentBalance==0)
		{
			System.out.println("You don't have any balance in your account. ");
			return;
		}
		
		System.out.println("Enter amount to withdraw:");
		
		withdrawAmount = UtilityMethods.integerInput();
		
		while(withdrawAmount > currentBalance || withdrawAmount <= 0)
		{
			System.out.println("Please enter amount between 0 and "
					+ currentBalance);
			withdrawAmount = UtilityMethods.integerInput();
		}
		
		int newBalanceAmount = currentBalance - withdrawAmount;
		newCustomer.setBalanceAmount(newBalanceAmount);
		newCustomer.setAccountHistory("Withdraw", withdrawAmount);
		System.out.println("Successfully withdrawn!");
		
	}

	public static void depositMoney(CustomerClass newCustomer)
	{
		//todo: provide option to exit from between
		
		int currentBalance = newCustomer.getBalanceAmount();
		
		System.out.println("Enter an amount to deposit in your account: ");
		int toDeposit = UtilityMethods.integerInput();
		
		while(toDeposit<=0)
		{
			System.out.println("Please enter an amount greater than 0.");
			toDeposit = UtilityMethods.integerInput();
		}
		
		newCustomer.setBalanceAmount(currentBalance + toDeposit);
		newCustomer.setAccountHistory("Deposit", toDeposit);
		System.out.println("Successfully deposited!");
	}

	public static void logOut(CustomerClass newCustomer)
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
		
		UtilityMethods.writeObjectToFile(directoryString, newCustomer); 
		
	}

}
