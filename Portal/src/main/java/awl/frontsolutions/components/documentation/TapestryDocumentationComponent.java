package awl.frontsolutions.components.documentation;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

import awl.frontsolutions.entities.TapestryDocumentation;

public class TapestryDocumentationComponent extends DocumentComponent{

	@Parameter(required = true)
	private Map<String, TapestryDocumentation> tapestry;

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
	 * Methods used to display Tapestry source codes
	 */

	public TreeSet<String> getTapestryDirectory() {
		return new TreeSet(tapestry.keySet());
	}

	public String getForewordsTapestry() {
		return tapestry.get(directory).getForewords();
	}

	public String getReadMoreTapestryTml() {
		return tapestry.get(directory).getReadmeTemplate();
	}

	public String getReadMoreTapestryJava() {
		return tapestry.get(directory).getReadmeJava();
	}

	public String getReadMoreTapestryJs() {
		return tapestry.get(directory).getReadmeJs();
	}

	public String getSnippetTapestryTml() {
		return tapestry.get(directory).getTemplate();
	}

	public String getSnippetTapestryJava() {
		return tapestry.get(directory).getJava();
	}

	public String getSnippetTapestryJS() {
		return tapestry.get(directory).getJs();
	}

	public List<String> getEscapedSnippetTapestryTml() {
		return tapestry.get(directory).getEscapedTemplate();
	}

	public List<String> getEscapedSnippetTapestryJava() {
		return tapestry.get(directory).getEscapedJava();
	}

	public List<String> getEscapedSnippetTapestryJS() {
		return tapestry.get(directory).getEscapedJs();
	}

	public String getDirectoryWithouNumber() {
		return directory.substring(directory.indexOf("_") + 1);
	}

	public Boolean getLastDirectory() {
		return !(directoryIndex == (getTapestryDirectory().size() - 1));
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
