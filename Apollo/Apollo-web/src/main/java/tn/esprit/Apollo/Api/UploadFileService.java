package tn.esprit.Apollo.Api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;


@Path("/upload")
public class UploadFileService {

	private final String UPLOADED_FILE_PATH = "/tmp/";
	
	@POST
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) {

		String fileName = "";
		
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("uploadedFile");

		for (InputPart inputPart : inputParts) {

			try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);
				System.out.println(fileName + " test : "+allowedMIMEType(header)+ "  "+ allowedExt(fileName));
				
					//convert the uploaded file to inputstream
					InputStream inputStream = inputPart.getBody(InputStream.class,null);

					byte [] bytes = IOUtils.toByteArray(inputStream);
					if (allowedMIMEType(header) && allowedExt(fileName)){
					//constructs upload file path
					fileName = UPLOADED_FILE_PATH + fileName;
					
					writeFile(bytes,fileName);
					
					System.out.println("Done");
				}
				

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return Response.status(200)
				.entity("uploadFile is called, Uploaded file name : " + fileName).build();

	}

	/**
	 * header sample
	 * {
	 * 		Content-Type=[image/png], 
	 * 		Content-Disposition=[form-data; name="file"; filename="filename.extension"]
	 * }
	 **/
	//get uploaded filename, is there a easy way in RESTEasy?
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");
				
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	/*
	 * Check the MIME type of the file
	 * 
	 */
	
	private Boolean allowedMIMEType(MultivaluedMap<String, String> header){
		String[] contentType = header.getFirst("Content-Type").split(";");
		
		String[] allowedtypes = {"image/jpeg","audio/mpeg"} ;
		
		for (int i = 0; i < allowedtypes.length; i++) {
			if (allowedtypes[i].equals(contentType[0])) {
				return true  ;
			}
		}
		return false  ;
	}
	
	/*
	 * Check the extention of the file
	 * 
	 */
	private Boolean allowedExt(String filename){
		String[] allowedExts = {"jpeg","mp3","jpg"} ;
		
		int position = filename.lastIndexOf(".");
		String ext = filename.substring(position+1);
		for (int i = 0; i < allowedExts.length; i++) {
			if (allowedExts[i].contains(ext)) {
				return true  ;
			}
		}
		return false ;
	}
	
	//save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}
	
	
}