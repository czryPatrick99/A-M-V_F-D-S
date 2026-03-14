package util;

import java.io.File;
import java.io.IOException;


 //Utility for file-related operations.
public class FileUtil {

    public static final String DATA_DIR = "data";

    
     //Ensure the data directory exists. Creates it if necessary.
    
    public static void ensureDataDirectoryExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    
     // Get the full path for a data file.
    public static String getDataFilePath(String fileName) {
        return DATA_DIR + File.separator + fileName;
    }
}