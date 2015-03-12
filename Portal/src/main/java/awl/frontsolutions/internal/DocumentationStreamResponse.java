package awl.frontsolutions.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.services.Response;

public class DocumentationStreamResponse implements StreamResponse {

	public File file;



	public DocumentationStreamResponse(String filepath) {
		super();
		this.file = new File(filepath);
	}

	@Override
	public String getContentType() {
		if(file.getName().endsWith(".pdf")){
			return "application/pdf";
		}else if(file.getName().endsWith(".txt")){
			return "text/plain";
		}
		return "application/octet-stream";
	}

	@Override
	public InputStream getStream() throws IOException {
		return new FileInputStream(file);
	}

	@Override
	public void prepareResponse(Response response) {
		response.setHeader("Content-Disposition", "attachment; filename="+file.getName());

	}

}
