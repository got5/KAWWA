package awl.frontsolutions.pages;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Import(stack="themestack")
public class Angular {

	@Inject private Messages messages;
	
	public String getReadmore(){ return "";}
	
	public List<String> getSnippet() throws MalformedURLException{
		List<String> snippet = new ArrayList<String>();
		
		
		snippet.add("<head>");
		snippet.add("	<script src=\"http://got5.github.io/KAWWA/grunt-scripts/jquery-1.8.3.js\"></script>");
        snippet.add("	<script src=\"http://got5.github.io/KAWWA/grunt-scripts/angular.js\"></script>");
        snippet.add("	<script src=\"http://got5.github.io/KAWWA/grunt-scripts/jquery.ui.core.js\"></script>");
        snippet.add("	<script src=\"http://got5.github.io/KAWWA/grunt-scripts/jquery.ui.widget.js\"></script>");
        snippet.add("	<script src=\"http://got5.github.io/KAWWA/grunt-scripts/kawwa-directives-full.js\"></script>");
        snippet.add("</head>");


		
		return snippet;
	}
}
