package vaultsystem;

import security.*;
import vaultsystem.FileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FolderManager extends User {
	
	Scanner sc = new Scanner(System.in);
	Encryption crypt = new Encryption();
	FileManager files = new FileManager();
	final public static String basePath = "Data\\";
	
	
//Folder crud after login page
	
	public void dashboard() {
		String basepath_user = basePath + getUsername() + "\\";
		while(true) {
			System.out.println("----------------------------\n");
			System.out.println(getUsername()+"'s Dashboard:\n");
			System.out.println("1. Add Folder");
			System.out.println("2. Open Folder");
			System.out.println("3. Delete Folder");
			System.out.println("4. Logout");
			
			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				System.out.println("Enter the Folder name to create:");
				addFolder(sc.nextLine(),basepath_user+"\\", true);
				
				break;
				
			case 2:
			    if(viewFolders(basepath_user)) {
			    	System.out.println("Enter a folder name to open:");
				    openFolder(sc.nextLine(), basepath_user);
			    }
			    else {
			    	System.out.println("Add a Folder first.");
			    }
			    break;

			case 3:
				viewFolders(basepath_user);
				System.out.println("\nEnter the Folder name to delete:");
				delFolder(sc.nextLine(), basepath_user);
				
				break;
				
			case 4:
				return;
			}
		}
		
	}
	
	public void addFolder(String foldername,String path, boolean print) {
		
		File folder = new File(path + foldername);
		
		try {
			if(print) {
				if(folder.mkdir()) {
					System.out.println("\nFolder "+foldername+" created Successfully");
				}
				else {
					System.out.println("\nThe folder is already exists");
				}
			}
			else {
				folder.mkdir();
			}
			
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public boolean viewFolders(String path) {
		File getFolders = new File(path);
		File folders[] = getFolders.listFiles(File::isDirectory);
		
		if(folders != null && folders.length > 0) {
//			int count = 1;
			for(File folder : folders) {
				System.out.println( "- " + folder.getName());
//				count++;
			}	
			return true;
		}
		else {
			System.out.println("No folder found!!");
			return false;
		}
		
	}
	
	public void openFolder(String foldername, String path) {
		File folder = new File(path + foldername);
	    
	    if (folder.exists() && folder.isDirectory()) {
	        System.out.println("Folder '" + foldername + "' opened!");
	        files.fileCRUD(foldername, path); 
	    } else {
	        System.out.println("Folder does not exist.");
	    }
	}
	
	public void delFolder(String foldername, String path) {
		File folder = new File(path + foldername);
		if(folder.delete()){
			System.out.println(foldername + "was deleted successfully");
		}
		else {
			System.out.println("This folder is not exist");
		}
	}
	
}
	




