package awl.frontsolutions.treeDescription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.internal.TapestryInternalUtils;

import awl.frontsolutions.entities.ComponentContent;

public class TreeNode implements Serializable{

	private String nodeName;

	private NodeType nodeType;

	private List<TreeNode> children;

	private int level;

	private String path;
	
	private String relatifPath;
	
	private String urlParam;

	private String version;
	
	private String[] tags;
	
	private String css;
	
	private TreeNode parent;
	
	private ComponentContent content;

	public TreeNode() {
		super();
		children = new ArrayList<TreeNode>();
		level = 0;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public NodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String toString() {
		return nodeName+" ("+nodeType.toString()+")";
	}

	public void addChild(TreeNode tn) {
		tn.setLevel(level + 1);
		children.add(tn);
	}

	public void setLevel(int l) {
		this.level = l;
//		for (TreeNode tn : children) {
//			// tn.setLevel(l+1);
//		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrlParam() {
		return urlParam;
	}

	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}

	public String[] getTags() {
		return tags;
	}
	
	public boolean containsTag(String tag){
		return ArrayUtils.contains(tags, tag);
	}

	public void setTags(String tags) {
		this.tags = TapestryInternalUtils.splitAtCommas(tags);
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public ComponentContent getContent() {
		return content;
	}

	public void setContent(ComponentContent content) {
		this.content = content;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getRelatifPath() {
		return relatifPath;
	}

	public void setRelatifPath(String relatifPath) {
		this.relatifPath = relatifPath;
	}

	public boolean hasChild(String urlParam2) {
		boolean conditionPerso = false;
		if(nodeType.equals(NodeType.COMPONENT)){
			conditionPerso = urlParam.equalsIgnoreCase(urlParam2);
		}
		boolean conditionEnfants = false;
		for (TreeNode c : children) {
			conditionEnfants = conditionEnfants || c.hasChild(urlParam2);
		}
		
		
		return conditionPerso || conditionEnfants;
	}
	
	
	public String getCleanName(){
		return toCamelCase(nodeName, true);
	}
	
	private String toCamelCase(String value, boolean startWithLowerCase) {
		String[] strings = StringUtils.split(value.toLowerCase(), "_");
		for (int i = startWithLowerCase ? 1 : 0; i < strings.length; i++){
			strings[i] = StringUtils.capitalize(strings[i]);
		}
		return StringUtils.join(strings);
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
	
	
	

}
