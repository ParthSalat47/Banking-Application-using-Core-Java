package mainApplication;

import java.io.*;
import java.util.*;

public class MainApplication 
{
	
	private HashMap<String, String> usernamePasswords = new HashMap<String, String>();

	public static void main(String[] args) 
	{
		
		
		try
		{
			whileLoop();
		}
		catch(IOException e) 
		{
			System.out.println("Encountered an IOException!");
		}
		
		
		
		

	}
	
	private static void whileLoop() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true)
		{	
			System.out.println("Press appropriate key: "
					+ "\n1. Sign up"
					+ "\n2. Log in");
			
			if(Integer.parseInt(br.readLine())==1)
			{
				signUp();
				bankingOptionsLoop();
			}
			
			else if(Integer.parseInt(br.readLine())==2)
			{
				logIn();
				bankingOptionsLoop();
			}
			
			else 
			{
				System.out.println("Please enter either 1 or 2.");
			}
			
			
			
			//break;
		}
	}
	
	private static void signUp()
	{
		//System.out.println("Signed UP!!!!");
		
	}
	
	private static void logIn()
	{
		
	}
	
	private static void bankingOptionsLoop()
	{
		
	}

}

