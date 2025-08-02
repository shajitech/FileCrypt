package filecrypt;

import java.util.Scanner;

import security.User;
import vaultsystem.FolderManager;


public class Main {
	public static void main(String[] args) {
		User user = new User();
		Scanner sc = new Scanner(System.in);
		FolderManager vault = new FolderManager();
		System.out.println("\nWelcome to FileCrypt: A secure File Manager!!\n" );
		while(true) {
			System.out.println("1. Create new user");
			System.out.println("2. Login user account");
			System.out.println("3. Show users");
			System.out.println("4. Exit App");
			
			System.out.print("\nEnter your choice: ");
			int choice = sc.nextInt();

			
			switch(choice) {
				case 1:
					vault.createUser();
					vault.addFolder(vault.getUsername(), vault.basePath, false);
					vault.dashboard();
					break;
				case 2:
					vault.loginUser();
					vault.dashboard();
					break;
				case 3:
					System.out.println(vault.users.keySet());
					break;
				case 4: 
					System.out.println("Thankyou For using our app \nExiting....");
					System.exit(0);
				default:
					System.out.println("Enter a valid choice!!");
			}
		}
	}
}
