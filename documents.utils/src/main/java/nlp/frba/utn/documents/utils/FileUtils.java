package nlp.frba.utn.documents.utils;

import java.io.File;
import java.nio.file.Path;

public class FileUtils {
    
    public Path getFilePath(String filename) {
    	File file = new File(
			"src/main/resources/" + filename
		);
    	return file.toPath();
    }
    
    static FileUtils singleton = null;
    
    public static FileUtils getInstance() {
    	if(singleton == null) {
    		singleton = new FileUtils();
    	}
    	return singleton;
    }
    
    public static String getValueOrNull(String s) {
    	return s.length() == 0 ? null : s;
    }

}
