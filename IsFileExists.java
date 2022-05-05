package com.taohs.tool;

import java.io.File;

public class IsFileExists {
    private static String filename ;

    public static void main(String[] args) {
        for (int i = 0; i < files.length ; i++) {
            filename = FilesList.files[i];
            try {
                File file = new File(filename);
                if (!file.exists())
                    System.out.println((i+1) + file.getPath() + file.getName() + ":" + file.exists());
                //System.out.println(filename);
            }catch (Exception e)
            {
                System.out.println("Error"+filename);
            }
        }
    }
	
    public static String[] files = {
		"C:\\Test\\test1.txt";
		"C:\\Test\\test2.txt";
    };
}
