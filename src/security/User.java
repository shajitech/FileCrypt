package security;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class User { 
	Scanner sc = new Scanner(System.in);
	Encryption crypt = new Encryption();
	public HashMap<String, String> users = new HashMap<>();
	
	private String username;
	private String password;
	
	
	public User() {
		retrieveUser();
	}
	
	
	// METHODS
	
	public String getUsername() {
		return username;
	}
	
	public void createUser() {
			
		while(true) {
			System.out.println("---------------------- \nCREATE ACCOUNT:-\n");
			System.out.println("Enter the Username:");
		    username = sc.next();
		    if(users.containsKey(username)) {
		    	System.out.println("This username already exist!!");
		    }
		    else {
		    	while(true) {
					System.out.println("Enter the password:");
					password = sc.next();
					System.out.println("Re-enter the password:");
					String repass = sc.next();
					if(password.equals(repass)) {
						System.out.println("User "+ username + " created successfully!!");
						users.put(username, password);
						saveUsers(username, password);
						return;
					}
					else {
						System.out.println("Password doesn't match");
					}	
			    }	
		    }
		}	
	}
	
	public boolean loginvalid() {
		return users.containsKey(username) && users.get(username).equals(password);
	}
	
	
	public void loginUser() {
		while(true) {
			System.out.println("-------------------------\n LOGIN PAGE:-\n");
			System.out.println("Enter your username:");
			username = sc.next();
			
			System.out.println("Enter your password:");
			password = sc.next();
			
			if(loginvalid()) {
				System.out.println("Login successful!!");
				break;
			}
			else {
				System.out.println("Invalid username or password");
			}
		}
	}
	
	// this method is used to save username pass from hashmap to User.txt
	public void saveUsers(String username, String password) {
		
		password = crypt.encrypt(password, 7);
		
		try {
			FileWriter usersFile = new FileWriter( "Data\\users.txt", true);
			BufferedWriter users = new BufferedWriter(usersFile);
			
			users.write(username + " : " + password);
			users.newLine();
			users.close();
		}
		catch(Exception e) {
			System.out.println("Something went wrong!!\n" + e);
		}
		
	}
	
	public void retrieveUser() {
		File file = new File("Data\\users.txt");
		
		if(!file.exists()) {
			return;
		}
		
		try {
			FileReader usersFile = new FileReader("Data\\users.txt");
			BufferedReader users = new BufferedReader(usersFile);
			String userpass;
			while((userpass = users.readLine()) != null) {
				String part[] = userpass.split(":");
				
				if(part.length == 2) {
					String username = part[0].trim();
					String password = part[1].trim();
					
					password = crypt.decrypt(password, 7);
					this.users.put(username, password);
				}
			}
			
			users.close();
			usersFile.close();
		}
		catch(Exception e) {
			System.out.println("Error occurs while retrieving user login info\n" + e);
		}
	}
	
	
}
