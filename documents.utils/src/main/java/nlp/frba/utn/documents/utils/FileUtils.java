package nlp.frba.utn.documents.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    
    public Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
          .filter(file -> !file.isDirectory())
          .map(File::getName)
          .collect(Collectors.toSet());
    }
	
	public String renameFile(String filepath, String filename, String newfilepath) {
		String extension = "";
		int i = filename.lastIndexOf('.');
		if (i > 0) {
		    extension = filename.substring(i+1);
		}
		
		String newFileName = UUID.randomUUID().toString() + "." + extension;
		
		try {
			File source = new File(filepath + filename);
			Files.copy( source.toPath(), source.toPath().resolveSibling(newfilepath + newFileName));
			return newFileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
