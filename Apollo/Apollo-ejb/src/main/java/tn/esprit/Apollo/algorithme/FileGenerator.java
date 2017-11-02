package tn.esprit.Apollo.algorithme;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public void writeOnTextFile(String data) throws IOException
	{
		Path fullPath = new File(root, fileName).toPath();
		// make sure file exists
		Files.createDirectories(fullPath.getParent());
		
		try (BufferedWriter bw = Files.newBufferedWriter(fullPath))
		{
			bw.write("**********************");
			bw.write(data);
            bw.write("**********************");
            bw.newLine();
		}
	}
	
	public void writeOnJsonFile(Object obj) throws IOException 
	{
		
		ObjectMapper mapper = new ObjectMapper();

        File file = new File("D:\\audit.json");
        try {
            // Serialize Java object info JSON file.
            mapper.writeValue(file, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	 
	
}
