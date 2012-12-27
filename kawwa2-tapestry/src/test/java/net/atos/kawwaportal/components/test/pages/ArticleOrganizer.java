package net.atos.kawwaportal.components.test.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.json.JSONObject;

import net.atos.kawwaportal.components.data.articleOrganizer.ArticleDetail;
import net.atos.kawwaportal.components.data.articleOrganizer.ArticleDetailImpl;
import net.atos.kawwaportal.components.data.articleOrganizer.ImageResourceTypeEnum;
import net.atos.kawwaportal.components.data.articleOrganizer.TabArticleDetail;
import net.atos.kawwaportal.components.data.articleOrganizer.TabArticleDetailImpl;

public class ArticleOrganizer {
	
	@Persist
	private Map<String, Class> mapArticletoClass;
	
	@Property
	@SuppressWarnings("unused")
	private List<TabArticleDetail> tabArticleList;
	
	@Property
	@SuppressWarnings("unused")
	private JSONObject tabOptions;
	
	@SetupRender
	public void setupRender() {
		
		tabOptions = new JSONObject();
		
		tabArticleList = generateInitialData();
	}
	
	@OnEvent(value="serveArticleSuggesterArticleClicked")
	public Object catchArticleSuggesterEvent(String ref) {
		
		return mapArticletoClass.get(ref);
	}
	
	public List<TabArticleDetail> generateInitialData() {
		
		ArrayList<TabArticleDetail> tabList = new ArrayList<TabArticleDetail>();
		mapArticletoClass = new HashMap<String, Class>();
		
		//Creation a tag
		TabArticleDetailImpl tabArticle1 = new TabArticleDetailImpl();
		tabArticle1.setRef("#1");
		tabArticle1.setLabel("Check Out Components");
		tabArticle1.setOpened(false);
		
		//Creation of the list of Articles
		tabArticle1.setArticles(new ArrayList<ArticleDetail>());
		
		ArticleDetailImpl Article11 = new ArticleDetailImpl();
		Article11.setRef("#11");
		Article11.setLabel("GoToProductQuantity");
		Article11.setImage("/net/atos/kawwaportal/components/theme/img/k-theme1/bt_prev.png");
		Article11.setImageResourceType(ImageResourceTypeEnum.ASSET_RESOURCE);
		Article11.setCategory("CheckOutCategory");
		
		mapArticletoClass.put("#11", ProductQuantity.class);
		tabArticle1.getArticles().add(Article11);
		
		
		ArticleDetailImpl Article12 = new ArticleDetailImpl();
		Article12.setRef("#12");
		Article12.setLabel("GoToDeliveryForm");
		Article12.setImage("/net/atos/kawwaportal/components/theme/img/k-theme1/bt_prev_off.png");
		Article12.setImageResourceType(ImageResourceTypeEnum.ASSET_RESOURCE);
		Article12.setCategory("CheckOutCategory");
		
		mapArticletoClass.put("#12", DeliveryForm.class);
		tabArticle1.getArticles().add(Article12);
		
		
		tabList.add(tabArticle1);
		
		
		//Creation a tag
		TabArticleDetailImpl tabArticle2 = new TabArticleDetailImpl();
		tabArticle2.setRef("#2");
		tabArticle2.setLabel("Rating Components");
		tabArticle2.setOpened(true);
		
		//Creation of the list of Articles
		tabArticle2.setArticles(new ArrayList<ArticleDetail>());
		
		ArticleDetailImpl Article21 = new ArticleDetailImpl();
		Article21.setRef("#21");
		Article21.setLabel("GoToratingForm");
		Article21.setImage("/net/atos/kawwaportal/components/theme/img/k-theme1/bt_next.png");
		Article21.setImageResourceType(ImageResourceTypeEnum.ASSET_RESOURCE);
		Article21.setCategory("RatingCategory");
		
		mapArticletoClass.put("#21", RatingForm.class);
		tabArticle2.getArticles().add(Article21);
		
		
		ArticleDetailImpl Article22 = new ArticleDetailImpl();
		Article22.setRef("#22");
		Article22.setLabel("GoToratingReviews");
		Article22.setImage("/net/atos/kawwaportal/components/theme/img/k-theme1/bt_next_off.png");
		Article22.setImageResourceType(ImageResourceTypeEnum.ASSET_RESOURCE);
		Article22.setCategory("RatingCategory");
		
		mapArticletoClass.put("#22", RatingReviews.class);
		tabArticle2.getArticles().add(Article22);
		
		
		tabList.add(tabArticle2);
		
		return tabList;
	}

}
