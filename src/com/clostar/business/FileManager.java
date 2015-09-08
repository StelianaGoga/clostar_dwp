package com.clostar.business;

import java.io.File;
import java.io.IOException;

public class FileManager {

	public static void createUserDirectory(String dir) {
		new File(dir).mkdir();
	}

	public static void removeUserDirectory(String dir) {
		File directory = new File(dir);

    	if(directory.exists()) {
           try {
               delete(directory);
           }
           catch(IOException e) {
               e.printStackTrace();
           }
        }
	}

	public static void delete(File file) throws IOException{	 
    	if(file.isDirectory()) {
    		if(file.list().length == 0) {
    		   file.delete();
    		   System.out.println("Directory is deleted : " 
                       			+ file.getAbsolutePath());
    		}
    		else {
        	   String files[] = file.list();
     
        	   for (String temp : files) {
        		   File fileDelete = new File(file, temp);
        		   delete(fileDelete);
        	   }

        	   if(file.list().length == 0) {
        		   file.delete();
        		   System.out.println("Directory is deleted : " 
                                                  + file.getAbsolutePath());
        	   }
    		}	
    	}
    	else {
    		file.delete();
    		System.out.println("File is deleted : " + file.getAbsolutePath());
    	}
    }
}
