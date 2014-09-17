package awl.frontsolutions.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Import;

@Import(stack="themestack")
public class IeFix {
	
	public String getReadmore(){ return "";}
	
	public List<String> getSnippet(){
		List<String> snippet = new ArrayList<String>();
		
		snippet.add("// Identifies IE version");
		snippet.add("var ie6 = (navigator.appName == \"Microsoft Internet Explorer\" && parseInt(navigator.appVersion) == 4 && navigator.appVersion.indexOf(\"MSIE 6.0\") != -1);");
		snippet.add("var ie7 = (navigator.appName == &quot;Microsoft Internet Explorer&quot; &amp;&amp; parseInt(navigator.appVersion) == 4 &amp;&amp; navigator.appVersion.indexOf(&quot;MSIE 7.0&quot;) != -1);");
		snippet.add("var ie8 = (navigator.appName == &quot;Microsoft Internet Explorer&quot; &amp;&amp; parseInt(navigator.appVersion) == 4 &amp;&amp; navigator.appVersion.indexOf(&quot;MSIE 8.0&quot;) != -1);");
		snippet.add("if (ie6 || ie7) {");
		snippet.add("    inputFix();");
		snippet.add("    fixStructure();");
		snippet.add("}");
		snippet.add("if (ie8) {");
		snippet.add("    fixStructure();");
		snippet.add("}");
		
		return snippet;
	}
}
