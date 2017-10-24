package tn.esprit.Apollo.algorithme;


import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileGenerator {
	 
	private static File root = new File("D:\\");
	private final String fileName ="Audit.txt";
	 
	public void trace(String data) throws IOException
	{
		Path fullPath = new File(root, fileName).toPath();
		// make sure file exists
		Files.createDirectories(fullPath.getParent());
		
		try (BufferedWriter bw = Files.newBufferedWriter(fullPath))
		{
			bw.write(data);
            bw.write("Hello World");
            bw.newLine();
		}
 
	}
	 
	
}
