package awl.frontsolutions.components.documentation;

import awl.frontsolutions.entities.AngularDocumentation;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class AngularDocumentationComponent extends DocumentComponent{

	@Parameter(required = true)
	private Map<String, AngularDocumentation> angular;

	/**
	 * Variable used by the loop component, in order to store the current
	 * directory
	 */
	@Property
	private String directory;

	/**
	 * Index of the Loop Component
	 */
	@Property
	private Integer directoryIndex;

	/**
	 * Methods used to display Angular source codes
	 */

	public TreeSet<String> getAngularDirectory() {
		return new TreeSet(angular.keySet());
	}

	public String getForewordsAngular() {
		return angular.get(directory).getForewords();
	}

	public String getReadMoreAngularHtml() {
		return angular.get(directory).getReadmeHtml();
	}

	public String getReadMoreAngularJs() {
		return angular.get(directory).getReadmeJs();
	}

	public String getSnippetAngularHtml() {
		return angular.get(directory).getHtml();
	}
	public String getSnippetAngularJS() {
		return angular.get(directory).getJs();
	}

	public List<String> getEscapedSnippetAngularHtml() {
		return angular.get(directory).getEscapedHtml();
	}
	public List<String> getEscapedSnippetAngularJS() {
		return angular.get(directory).getEscapedJs();
	}

	public String getDirectoryWithouNumber() {
		return directory.substring(directory.indexOf("_") + 1);
	}

	public Boolean getLastDirectory() {
		return !(directoryIndex == (getAngularDirectory().size() - 1));
	}

	public String getDirectoryName() {
		return reFormat(directory);
	}

	private String reFormat(String path) {

		String name = path.substring(path.indexOf("_") + 1);

		StringBuilder result = new StringBuilder();
		result.append(name.charAt(0));
		for (int i = 1; i < name.length(); i++) {
			if (Character.isUpperCase(name.charAt(i)))
				result.append(" ");
			result.append(name.charAt(i));
		}

		return result.toString();
	}
}
