package nlp.frba.utn.documents.actions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import nlp.frba.utn.documents.domain.DocumentForLoad;
import nlp.frba.utn.documents.domain.Student;
import nlp.frba.utn.documents.utils.FileUtils;
import nlp.frba.utn.documents.utils.GeneralUtils;
import nlp.frba.utn.documents.utils.GsonUtils;

public class DocumentLoader {
	
	public static void loadDocuments() {
		int records = 0;
		
		System.out.println("[STARTED]");
		Reader reader;
		try {
			reader = Files.newBufferedReader(FileUtils.getInstance().getFilePath("data.csv"));
	        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
	        
	        String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
            	List<Student> students = new ArrayList<Student>();
            	
            	String names_s = nextRecord[2];
            	String ids_s = nextRecord[1];
            	for(int i = 0 ; i < names_s.split(";").length ; i++) {
            		if(GeneralUtils.getValueOrNull(names_s.split(";")[i]) != null) {
                		String id = "";
                		try {
                    		id = ids_s.split(";")[i];						
    					} catch (Exception e) {
    					}
                		students.add(new Student(GeneralUtils.getValueOrNull(names_s.split(";")[i]), GeneralUtils.getValueOrNull(id)));
            		}
            	}
            	
            	DocumentForLoad d = new DocumentForLoad(
            			GeneralUtils.getValueOrNull(nextRecord[0]),
            			students,
            			GeneralUtils.getValueOrNull(nextRecord[3]) == null ? null : Integer.parseInt(nextRecord[3]),
            			GeneralUtils.getValueOrNull(nextRecord[4]),
            			GeneralUtils.getValueOrNull(nextRecord[5]),
            			GeneralUtils.getValueOrNull(nextRecord[6]),
            			GeneralUtils.getValueOrNull(nextRecord[7])
            		);
            	
            	String json = GsonUtils.getGson().toJson(d).toString();
            	
            	URL url = new URL("http://18.231.174.90:6570/documents");
    	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
    	        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    	        con.setRequestMethod("POST");
    	        con.setDoOutput(true);
    	        con.getOutputStream().write(json.getBytes("UTF-8"));
    	        
    	        InputStream stream = null;
    	        if(con.getResponseCode() < 400) {
    	        	stream = con.getInputStream();
    	        } else {
    	        	stream = con.getErrorStream();
    	        }
    	        ByteArrayOutputStream responseBody = new ByteArrayOutputStream();
    	        byte buffer[] = new byte[1024];
    	        int bytesRead = 0;
    	        while ((bytesRead = stream.read(buffer)) > 0) {
    	            responseBody.write(buffer, 0, bytesRead);
    	        }
    	        
    	        records ++;
    	        if(records % 10 == 0) {
    	        	System.out.println("[" + records + "]");
    	        }
    	        
    	        if(con.getResponseCode() != 201) {
    	        	System.out.println("ERROR " + con.getResponseCode());
    	        	System.out.println(json);
    	        }
            }
            
            if(records % 10 != 0) {
	        	System.out.println("[" + records + "]");
            }
        	System.out.println("[FINISHED]");
            
            csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		}
	}

}