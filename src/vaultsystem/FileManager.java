package vaultsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import security.Encryption;

public class FileManager {
	Scanner sc = new Scanner(System.in);
	Encryption crypt = new Encryption();
	
//	FILE METHODS
	
	public void fileCRUD(String foldername, String path) {
		while(true) {
			String filePath = path + foldername;
			System.out.println("----------------------------\n");
			System.out.println(foldername +":\n");
			System.out.println("1. Add File");
			System.out.println("2. Open File");
			System.out.println("3. Edit File (On construction)");
			System.out.println("4. Delete File");
			System.out.println("5. Back");
			
			System.out.println("\nEnter your choice: ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				System.out.println("Enter the File name to create: ");
				String fileName = sc.nextLine();
				if(addFile(fileName ,filePath)) {
					filePath =  filePath + "\\" + fileName + ".txt";
					System.out.println("Enter the content to encrypt:");
					addContent(sc.nextLine(), filePath);
					System.out.println("File " + fileName + " was added & encrypted successfully");
				}
				break;
				
			case 2:
				if(viewFiles(filePath)) {
					System.out.println("Enter a File name to view content:");
				    openFile(sc.nextLine(), filePath);
			    }
			    else {
			    	System.out.println("Add a File first.");
			    }
			    
				break;
			case 3:
					viewFiles(filePath);
					System.out.println("Enter a File name to edit content:");
					fileName = sc.nextLine();
					openFile(fileName, filePath);
					String fulFilePath = filePath + "\\" + fileName;
					System.out.println("Enter edited context: ");
					addContent(sc.nextLine(), fulFilePath);
				
				break;
			case 4:
				viewFiles(filePath);
				System.out.println("\nEnter the File name to delete:");
				delFile(sc.nextLine(), filePath);
				break;
				
			case 5:
				return;
			}
		}

	}
	
public boolean addFile(String filename,String path) {
		
		File file = new File(path + "\\" + filename + ".txt");
		
		
			
				if(!file.exists()) {
					try {
					file.createNewFile();
					}
					catch(Exception e){
						System.out.println(e);
					}
					return true;
				}
				else {
					System.out.println("The file is already exists");
					return false;
				}			
	}


public void addContent(String content, String filePath) {
	try {
		FileWriter file = new FileWriter(filePath);
		BufferedWriter filecontent = new BufferedWriter(file);
		
		content = crypt.encrypt(content, 10);
		filecontent.write(content);
		filecontent.close();
		
		
	}
	catch(Exception e) {
		System.out.println(e);
	}
	
}
	
public boolean viewFiles(String path) {
		File getFolders = new File(path);
		File folders[] = getFolders.listFiles(File::isFile);
		
		if(folders != null && folders.length > 0) {
			for(File folder : folders) {
				System.out.println( "- " + folder.getName());
			}	
			return true;
		}
		else {
			System.out.println("No File found!!");
			return false;
		}
	}


public void openFile(String fileName, String path ) {
	try {
		FileReader file = new FileReader(path + "\\" +fileName );
		BufferedReader fileContent = new BufferedReader(file);
		String line;
		while((line = fileContent.readLine()) !=null) {
			line = crypt.decrypt(line , 10);
			System.out.println("\n" + line);
		}
		fileContent.close();
	}
	catch(Exception e){
		System.out.println(e);
	}
	
}

public void delFile(String filename, String path) {
	File file = new File(path + "\\" + filename);
	if(file.delete()) {
		System.out.println(filename + " was deleted successfully");
	}
	else {
		System.out.println("This file is not exist");
	}
	
	
}

public void editFile() {
	
}


}


