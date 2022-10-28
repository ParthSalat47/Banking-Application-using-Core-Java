package mainApplication;

import java.io.Serializable;
import java.util.*;

public class CustomerClass implements Serializable
{

	private static final long serialVersionUID = 1L; 
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
	
	public String getUsername()
	{
		return this.customerUsername;
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
	
	

}
