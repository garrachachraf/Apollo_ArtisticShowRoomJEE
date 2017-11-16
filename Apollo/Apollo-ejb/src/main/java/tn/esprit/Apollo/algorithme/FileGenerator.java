package tn.esprit.Apollo.algorithme;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FileGenerator {
	 
	private  File root = new File("D:\\");
	private  String fileName ="Audit.txt";
	
	
	public FileGenerator(String fileName) {
		this.fileName = fileName ;
	}
	
	public FileGenerator(String fileName , String path) {
		this.fileName = fileName;
		this.root = new File(path);
	}
	
	public FileGenerator() {
	}
	
	public void writeOnTextFile(Audit data) throws IOException 
	{
		/*Path fullPath = new File(root, fileName).toPath();
		// make sure file exists
		Files.createDirectories(fullPath.getParent());*/

        ObjectMapper mapperObj = new ObjectMapper();
         

            String jsonStr = mapperObj.writeValueAsString(data);
 
		
		    FileWriter fw = new FileWriter("D:\\Audit.txt",true); //the true will append the new data
		    fw.write("*******************************");
		    fw.write("\r\n");
			fw.write(jsonStr);
			fw.write("\r\n");
			fw.close();
		
	}
	
	public void writeOnJsonFile(Object obj) throws IOException 
	{
		
  		ObjectMapper mapper = new ObjectMapper();
  		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
  		File f = new File("D:\\audit.json");
  		if(f.exists() && !f.isDirectory()) { 
  	        try {
  	            // Serialize Java object info JSON file.

  	    		 writer.writeValue(f, obj);
  	        } catch (IOException e) {
  	            e.printStackTrace();
  	        }
  		    // do something
  		}

	}
	 
	
}
