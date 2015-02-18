package awl.frontsolutions.components;

import awl.frontsolutions.entities.ChoosenTheme;
import awl.frontsolutions.entities.Panier;
import awl.frontsolutions.services.FileSystemIndexer;
import awl.frontsolutions.services.MailService;
import awl.frontsolutions.treeDescription.TreeNode;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;
import org.got5.tapestry5.jquery.ImportJQueryUI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@ImportJQueryUI(value={"jquery.ui.tabs","jquery.ui.button","jquery.ui.accordion", "jquery.ui.position", "jquery.ui.dialog"})
@Import(library={"context:js/plugins/ZeroClipboard.js","context:js/ui/jquery.ui.panel.awl.js"})
public class Layout
{
	
    /** The page title, for the <title> element and the <h1> element. */
	@Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String title;

	@Property
	@Parameter 
	private String activeMenu;
	
    @Property
    private String pageName;

    @Property
    private boolean loggedUserExists;

    @Property
    @Validate(value="required")
    private String bodycontact;

    @Property
    @Validate(value="required")
    private String namecontact;

    @Property
    @Validate(value="required,email")
    private String emailcontact;

    @SessionState
    private ChoosenTheme choosen;

    @SessionState
    private Panier panier;

    @Inject
    private ComponentResources resources;
    
    @Inject
    private AssetSource as;
    
    @Inject
	private FileSystemIndexer indexer;

    @Inject
    private MailService mail;

    public String getClassForPageName()
    {
      return resources.getPageName().equalsIgnoreCase(pageName)
             ? "current_page_item"
             : null;
    }
    
    public Boolean isIndex(){return title.equalsIgnoreCase("Index");}

    public String getCurrentYear(){
    	DateFormat sdf = new SimpleDateFormat("yyyy");
    	return sdf.format(new Date());
    }

    public String getLogoUrl(){
    	return as.getContextAsset("img/"+choosen.getDir()+"/logo_Graphikawwa-trans.png", null).toClientURL();
    }
    public String getFaviconUrl(){
    	return as.getContextAsset("img/"+choosen.getDir()+"/favicon.png", null).toClientURL();
    }
    
    public String getId(){
    	return isIndex() ? "home" : "";
    }

    
	public TreeNode getRoot() {
		return indexer.getFileStructure();
	}
	public String getTwitterImg(){
		
		return as.getContextAsset("img/"+choosen.getDir()+"/tweetexe.png", null).toClientURL();
		
	}

	public void onSubmit(){
		StringBuilder sb = new StringBuilder();
		sb.append("Name : " + namecontact).append("\n");
		sb.append("Message : ").append("\n");
		sb.append(bodycontact);
		
		mail.sendMailToAdmin(emailcontact, "Contact Form", sb.toString());
		
	}

}
