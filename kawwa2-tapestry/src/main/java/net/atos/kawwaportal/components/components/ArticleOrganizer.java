package net.atos.kawwaportal.components.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tapestry5.ComponentEventCallback;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.internal.util.Holder;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import net.atos.kawwaportal.components.data.articleOrganizer.ArticleDetail;
import net.atos.kawwaportal.components.data.articleOrganizer.TabArticleDetail;

/**
 * <p>
 * 		This Component is used to call tab serie containing image-links of articles. On click on image, the component will return to the calling page the ref of the link clicked.
 * 
 * 		<br/><br/>
 * 
 *  	To use it, call the component with '&lt;div t:type="kawwa2/ArticleOrganizer" t:id="organizer" t:tabArticleList="tabArticleList" t:tabOptions="tabOptions"/&gt;'.
 * </p>
 * 
 * <p>
 * 		There is parameters to call for the form (These two parameter must be persisted by the component caller):
 * 		<ul>
 * 			<li>t:tabArticleList="&lt;&lt;!&gt;&gt;" ==> Replace &lt;&lt;!&gt;&gt; by the list of TabArticleDetail that will define the tabs and articles displayed.</li>
 * 			<li>t:tabOptions="&lt;&lt;!&gt;&gt;" ==> Replace &lt;&lt;!&gt;&gt; by the JSONObject that will configure the tab component.</li>
 * 		</ul>
 * </p>
 * 
 * @tapestrydoc
 * @component_version 1.0
 */

public class ArticleOrganizer {

	@Parameter
	@Property
	private List<TabArticleDetail> tabArticleList;
	
	@Parameter
	@Property
	private JSONObject tabOptions;
	
	@Property
	private TabArticleDetail currentTabArticle;
	
	@Property
	private ArticleDetail currentArticle;
	
	private Map<String,String> TabArticleTempIdMap;
	
	@Inject
    private AssetSource assetsource; 
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Inject
    private ComponentResources resources;
	
	@Inject
	private Messages messages;	
	
	public boolean getTabArticleNotEmpty() {
		
		return tabArticleList != null && tabArticleList.size() != 0;
	}
	
	public String setCurrentTabArticleTempId() {
		
		String currentId = javaScriptSupport.allocateClientId(resources);
		TabArticleTempIdMap.put(currentTabArticle.getRef(), currentId);
		
		return "#" + currentId;
	}
	
	public String getCurrentTabArticleTempId() {
		
		return TabArticleTempIdMap.get(currentTabArticle.getRef());
	}
	
	public String getCurrentTabArticleLabel() {
		
		String currentLabel;
		
		if(messages.contains(currentTabArticle.getRef()))
			currentLabel = messages.get(currentTabArticle.getRef());
		else if(currentTabArticle.getLabel() != null)
			currentLabel = currentTabArticle.getLabel();
		else
			currentLabel = currentTabArticle.getRef();
		
		return currentLabel;
	}
	
	public boolean getCurrentArticleListNotEmpty() {
		
		List<ArticleDetail> currentList = currentTabArticle.getArticles();
		
		return currentList != null && currentList.size() != 0;
	}
	
	public List<ArticleDetail> getCurrentArticleList() {
		
		return currentTabArticle.getArticles();
	}
	
	public String getCurrentArticleContext() {
		
		return currentArticle.getRef();
	}
	
	public String getCurrentArticleImageURL() {
		
		String currentImageURL = "";
		
		if(currentArticle.getImageResourceType() != null) {
			switch(currentArticle.getImageResourceType()) {
				case URL_RESOURCE :
					currentImageURL = currentArticle.getImage();
					break;
				case ASSET_RESOURCE :
					try {currentImageURL = assetsource.getClasspathAsset(currentArticle.getImage()).toClientURL();}
					catch(RuntimeException e) {e.printStackTrace();}
					break;
			}
		}
		return currentImageURL;
	}
	
	public String getCurrentArticleAltenative() {
		
		String currentAlternative = "";
		
		if(messages.contains(currentArticle.getRef()))
			currentAlternative = messages.get(currentArticle.getRef());
		else if(currentArticle.getLabel() != null)
			currentAlternative = currentArticle.getLabel();
		
		return currentAlternative;
	}
	
	private void checkPreOpenedTab() {
		
		Integer count = 0;
		String selected = null;
		
		for(TabArticleDetail tabArticleDetail : tabArticleList) {
			
			if(tabArticleDetail.isOpened())
				selected = count.toString();
			
			count++;
		}
		
		if(selected != null)
			tabOptions.put("selected", selected);
	}
	
	@SetupRender
	@SuppressWarnings("unused")
	private void setupRender() {
		
		if(tabOptions == null) tabOptions = new JSONObject();
		if(!tabOptions.has("selected"))
			checkPreOpenedTab();
		
		TabArticleTempIdMap = new HashMap<String, String>();
	}
	
	@OnEvent(value = EventConstants.ACTION, component = "article")
	@SuppressWarnings("unused")
	private Object onActionArticleClick(String ref) {
		
	final Holder<Object> holder = new Holder<Object>();
	resources.triggerEvent("serveArticleSuggesterArticleClicked",
							new Object[] {ref},
							new ComponentEventCallback<Object>() {
								public boolean handleResult(Object result) {
									holder.put(result);
									return false;
								}
							});

        return holder.get() == null ? this : holder.get();
	}
}
