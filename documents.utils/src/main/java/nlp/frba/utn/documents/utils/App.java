package nlp.frba.utn.documents.utils;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App 
{
    public static void main( String[] args )
    {
    	DocumentLoader.loadDocuments();
    }
    
    public static Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
          .filter(file -> !file.isDirectory())
          .map(File::getName)
          .collect(Collectors.toSet());
    }
}
