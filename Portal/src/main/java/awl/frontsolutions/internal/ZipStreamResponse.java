package awl.frontsolutions.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.services.Response;

public class ZipStreamResponse implements StreamResponse {

	private ByteArrayOutputStream out;
	
	private String filename; 
	
	
	
	public ZipStreamResponse(ByteArrayOutputStream out) {
		super();
		this.out = out;
		this.filename = "kawwa_components";
	}

	public String getContentType() {
		return "application/zip";
	}

	public InputStream getStream() throws IOException {
		return new ByteArrayInputStream(out.toByteArray());
	}

	public void prepareResponse(Response response) {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		response.setHeader("Content-Disposition","attachment; filename=\""+filename+".zip\"");

	}

}
