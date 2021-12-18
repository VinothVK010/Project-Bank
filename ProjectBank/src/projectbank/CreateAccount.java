package projectbank;

import java.util.Scanner;
import java.util.Random;

public class CreateAccount {

	public static void main(String[] args) {

		System.out.println("hello Nibbas");
		 System.out.println("This is my Class !!!");
		System.out.println("by DarkLord 10");
		
		System.out.println("Enter the AccountHolderName : ");
		System.out.println("Enter the AadharCardNum : ");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		String aadhar= scanner.nextLine();
		
		AccountHolder newAccount = new AccountHolder(name); 
		System.out.println(newAccount);
		
		//newAccount.setName(name);
		
		
		Random random = new Random();
		System.out.println(random.nextInt(10));
		
		
		
	}

}
