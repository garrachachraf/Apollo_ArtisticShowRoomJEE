package tn.esprit.Apollo.Api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import tn.esprit.Apollo.persistence.Media;
import tn.esprit.Apollo.persistence.MediaType;
import tn.esprit.Apollo.persistence.User;
import tn.esprit.Apollo.services.MediaServiceRemote;


@Path("/upload")
public class UploadFileService {

	private final String UPLOADED_FILE_PATH = "/tmp/";
	@EJB
	MediaServiceRemote mediaservice ;
	@POST
	@Consumes("multipart/form-data")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response uploadFile(MultipartFormDataInput input) {

		String fileName = "";
		Media m = new Media();
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("uploadedFile");

		for (InputPart inputPart : inputParts) {

			try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);
				//System.out.println(fileName + " test : "+allowedMIMEType(header)+ "  "+ allowedExt(fileName));
				
					//convert the uploaded file to inputstream
					InputStream inputStream = inputPart.getBody(InputStream.class,null);
					
					byte [] bytes = IOUtils.toByteArray(inputStream);
					if (allowedMIMEType(header) && allowedExt(fileName)){
					//constructs upload file path
						m = detectType(m, fileName);
					fileName = UUID.randomUUID().toString()+fileName;
					m.setPath(fileName);
					fileName = fileName + UPLOADED_FILE_PATH;
					writeFile(bytes,fileName);
					
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
		mediaservice.AddMediafile(m);
		return Response.status(200)
				.entity(mediaservice.FindByPath(m.getPath())).build();

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
	
	private Media detectType(Media m , String filename){
		int position = filename.lastIndexOf(".");
		String ext = filename.substring(position+1);
		if (ext.equals("mp3")) {
			m.setType(MediaType.music);
		} else {
			m.setType(MediaType.photo);
		}
		
		
		return m;
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