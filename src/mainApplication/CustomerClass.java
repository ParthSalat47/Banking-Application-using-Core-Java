package mainApplication;

import java.util.*;

public class CustomerClass 
{

	private String customerName;
	private String customerUsername;
	private String customerPassword;
	private int balanceAmount;
	private ArrayList<String> accountHistoryType = 
			new ArrayList<String>();
	private ArrayList<Integer> accountHistoryData = 
			new ArrayList<Integer>();
	
	//constructor
	//Note: it had to be public
	public CustomerClass(String customerName, String customerID, String customerPassword)
	{
		this.customerName =customerName;
		this.customerUsername =customerID;
		this.customerPassword = customerPassword;
	}
	
	public CustomerClass(String customerID, String customerPassword)
	{
		this.customerUsername =customerID;
		this.customerPassword = customerPassword;
	}
	
	public int getBalanceAmount()
	{
		return this.balanceAmount;
	}
	
	public void setBalanceAmount(int amount)
	{
		this.balanceAmount = amount;
	}
	
	public void setAccountHistory(String type, Integer data)
	{
		this.accountHistoryType.add(type);
		this.accountHistoryData.add(data);
	}
	
	public ArrayList<String> getAccountHistoryType()
	{
		return accountHistoryType;
	}
	
	public ArrayList<Integer> getAccountHistoryData()
	{
		return accountHistoryData;
	}
	
	/*
	public void getAccountHistory(String type, Integer data)
	{
		this.accountHistory.put(type, data);
	}
	*/
	
	
	public static void main(String[] args) 
	{
		

	}
	
	

}
