package awl.frontsolutions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.junit.Test;


public class PatternTest {

	@Test
	public void testPattern() throws IOException{
		InputStream cssFile = new FileInputStream("d:\\style.css");
		String css = IOUtils.toString(cssFile);
		cssFile.close();
		String urlParam = "carousel";
		String rgxp = "/\\* CB/\\s*"+urlParam+"\\s*\\*/\\s*(.*)\\s*/\\* CE/\\s*"+urlParam+"\\s*\\*/";
		Pattern p = Pattern.compile(rgxp,Pattern.DOTALL );
		System.out.println("begin");
		Matcher m = p.matcher(css);
		while(m.find()) {
			System.out.println(m.group(1));
		}
		System.out.println("end");
		
		
	}
	
}
